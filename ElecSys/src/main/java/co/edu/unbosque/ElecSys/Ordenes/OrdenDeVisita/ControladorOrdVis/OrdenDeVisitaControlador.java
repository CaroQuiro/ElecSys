package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ControladorOrdVis;

import co.edu.unbosque.ElecSys.Config.Excepcion.InvalidFieldException;
import co.edu.unbosque.ElecSys.Config.Excepcion.ResourceNotFoundException;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis.OrdenDeVisitaDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis.OrdenDeVisitaRequest;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DetalleOrdenVisita.DTODetOrdVis.DetalleOrdenVisitaDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DetalleOrdenVisita.ServicioDetOrdVis.DetalleOrdenVisitaService;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ServicioOrdVis.OrdenDeVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes-visita")
public class OrdenDeVisitaControlador {


    @Autowired
    private OrdenDeVisitaService ordenDeVisitaService;

    @Autowired
    private DetalleOrdenVisitaService detalleOrdenVisitaService;

    /* =====================================================
       LISTAR TODAS LAS ÓRDENES DE VISITA
       ===================================================== */
    @GetMapping("/listar")
    public List<OrdenDeVisitaDTO> listarOrdenesDeVisita() {
        return ordenDeVisitaService.listarOrdenVisita();
    }

    /* =====================================================
       BUSCAR ORDEN DE VISITA POR ID
       ===================================================== */
    @GetMapping("/buscar/{id}")
    public OrdenDeVisitaDTO buscarOrdenDeVisita(@PathVariable int id) {

        OrdenDeVisitaDTO orden = ordenDeVisitaService.buscarOrdenVisita(id);

        if (orden == null)
            throw new ResourceNotFoundException(
                    "No existe la orden de visita con ID: " + id);

        return orden;
    }

    /* =====================================================
       CREAR ORDEN DE VISITA + DETALLES
       ===================================================== */
    @PostMapping("/agregar")
    public String agregarOrdenDeVisita(
            @RequestBody OrdenDeVisitaRequest request) {

        if (request == null || request.getOrden() == null)
            throw new InvalidFieldException(
                    "La solicitud no contiene datos de la orden de visita.");

        OrdenDeVisitaDTO orden = request.getOrden();

        // Validación de IDs obligatorios
        if (orden.getIdCliente() <= 0 ||
                orden.getIdLugar() <= 0 ||
                orden.getIdTrabajador() <= 0)
            throw new InvalidFieldException(
                    "Cliente, lugar o trabajador inválido.");

        // Validación de estado
        List<String> estadosPermitidos =
                List.of("PENDIENTE", "PROGRAMADA", "REALIZADA", "CANCELADA");

        if (orden.getEstado() == null ||
                !estadosPermitidos.contains(orden.getEstado()))
            throw new InvalidFieldException(
                    "Estado inválido. Solo se permite: " + estadosPermitidos);

        // Validación de detalles
        List<DetalleOrdenVisitaDTO> detalles = request.getDetalles();

        if (detalles == null || detalles.isEmpty())
            throw new InvalidFieldException(
                    "Debe enviar al menos un detalle de la visita.");

        for (DetalleOrdenVisitaDTO d : detalles) {

            if (d.getActividad() == null || d.getActividad().isBlank())
                throw new InvalidFieldException(
                        "La actividad del detalle es obligatoria.");
        }

        // Crear orden
        ordenDeVisitaService.agregarOrdenVisita(orden);

        int idOrden = orden.getIdVisita();

        // Crear detalles
        for (DetalleOrdenVisitaDTO d : detalles) {
            d.setIdVisita(idOrden);
            detalleOrdenVisitaService.agregarDetalleOrdVis(d);
        }

        return "Orden de visita y detalles creados correctamente.";
    }

    /* =====================================================
       ACTUALIZAR ORDEN DE VISITA
       ===================================================== */
    @PutMapping("/actualizar/{id}")
    public String actualizarOrdenDeVisita(
            @PathVariable int id,
            @RequestBody OrdenDeVisitaDTO dto) {

        if (!ordenDeVisitaService.existeOrden(id))
            throw new ResourceNotFoundException(
                    "No existe la orden de visita con ID: " + id);

        if (dto.getIdVisita() != id)
            throw new InvalidFieldException(
                    "No se puede cambiar el ID de la orden.");

        return ordenDeVisitaService.editarOrdenVisita(id, dto);
    }

    /* =====================================================
       BORRAR ORDEN DE VISITA + DETALLES
       ===================================================== */
    @DeleteMapping("/borrar/{id}")
    public String borrarOrdenDeVisita(@PathVariable int id) {

        if (!ordenDeVisitaService.existeOrden(id))
            throw new ResourceNotFoundException(
                    "No existe la orden de visita con ID: " + id);

        // Borrar detalles primero
        detalleOrdenVisitaService.listarDetallesPorOrden(id)
                .forEach(d ->
                        detalleOrdenVisitaService.borrarDetalleOrdVis(
                                d.getIdDetalleVisita()));

        return ordenDeVisitaService.borrarOrdenVisita(id)
                + " + detalles eliminados";
    }

    /* =====================================================
       LISTAR DETALLES DE UNA ORDEN DE VISITA
       ===================================================== */
    @GetMapping("/{idOrden}/detalles")
    public List<DetalleOrdenVisitaDTO> listarDetallesPorOrden(
            @PathVariable int idOrden) {

        if (!ordenDeVisitaService.existeOrden(idOrden))
            throw new ResourceNotFoundException(
                    "No existe la orden de visita con ID: " + idOrden);

        return detalleOrdenVisitaService.listarDetallesPorOrden(idOrden);
    }

    /* =====================================================
       AGREGAR DETALLE A UNA ORDEN DE VISITA
       ===================================================== */
    @PostMapping("/{idOrden}/detalles/agregar")
    public String agregarDetalle(
            @PathVariable int idOrden,
            @RequestBody DetalleOrdenVisitaDTO detalle) {

        if (!ordenDeVisitaService.existeOrden(idOrden))
            throw new ResourceNotFoundException(
                    "No existe la orden de visita con ID: " + idOrden);

        if (detalle.getActividad() == null || detalle.getActividad().isBlank())
            throw new InvalidFieldException(
                    "La actividad es obligatoria.");

        detalle.setIdVisita(idOrden);
        return detalleOrdenVisitaService.agregarDetalleOrdVis(detalle);
    }

    /* =====================================================
       ACTUALIZAR DETALLE DE VISITA
       ===================================================== */
    @PutMapping("/detalles/actualizar/{idDetalle}")
    public String actualizarDetalle(
            @PathVariable int idDetalle,
            @RequestBody DetalleOrdenVisitaDTO detalle) {

        DetalleOrdenVisitaDTO actual =
                detalleOrdenVisitaService.buscarDetalle(idDetalle);

        if (actual == null)
            throw new ResourceNotFoundException(
                    "No existe el detalle de visita con ID: " + idDetalle);

        return detalleOrdenVisitaService
                .actualizarDetalleOrdVis(idDetalle, detalle);
    }

    /* =====================================================
       BORRAR DETALLE DE VISITA
       ===================================================== */
    @DeleteMapping("/detalles/borrar/{idDetalle}")
    public String borrarDetalle(@PathVariable int idDetalle) {

        if (!detalleOrdenVisitaService.existeDetalle(idDetalle))
            throw new ResourceNotFoundException(
                    "No existe el detalle de visita con ID: " + idDetalle);

        return detalleOrdenVisitaService.borrarDetalleOrdVis(idDetalle);
    }
}
