package co.edu.unbosque.ElecSys.Cotizacion.ControladorCot;

import co.edu.unbosque.ElecSys.Cotizacion.Archivo.Pdf_Cotizacion;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.AIUDTO;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionRequest;
import co.edu.unbosque.ElecSys.Cotizacion.DetalleCotizacion.DTODetCot.DetalleCotizacionDTO;
import co.edu.unbosque.ElecSys.Cotizacion.DetalleCotizacion.ServicioDetCot.DetalleCotizacionService;
import co.edu.unbosque.ElecSys.Cotizacion.ServicioCot.CotizacionServiceImpl;
import co.edu.unbosque.ElecSys.LugarTrabajo.DTOLug.LugarTrabajoDTO;
import co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug.LugarTrabajoService;
import co.edu.unbosque.ElecSys.Usuario.Cliente.DTOClie.ClienteDTO;
import co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie.ClienteServiceImpl;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionDTO;
import co.edu.unbosque.ElecSys.Config.Excepcion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cotizaciones")
public class CotizacionControlador {

    @Autowired
    private CotizacionServiceImpl service;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private DetalleCotizacionService detalleService;

    @Autowired
    private LugarTrabajoService lugarService;

    @Autowired
    private Pdf_Cotizacion pdfService;


    // --------------------------------------------------
    //               AGREGAR COTIZACIÓN + PDF
    // --------------------------------------------------
    @PostMapping("/agregar")
    public ResponseEntity<byte[]> agregarCotizacion(@RequestBody CotizacionRequest solicitud) throws IOException {

        if (solicitud.getCotizacion() == null)
            throw new InvalidFieldException("La solicitud no contiene datos de cotización.");

        CotizacionDTO cot = solicitud.getCotizacion();

        // Validaciones de IDs
        if (cot.getId_cliente() <= 0 || cot.getId_lugar() <= 0)
            throw new InvalidFieldException("Cliente o lugar inválido.");

        ClienteDTO cliente = clienteService.buscarCliente(cot.getId_cliente());
        if (cliente == null)
            throw new ResourceNotFoundException("El cliente no existe.");

        LugarTrabajoDTO lugar = lugarService.buscarLugar(cot.getId_lugar());
        if (lugar == null)
            throw new ResourceNotFoundException("El lugar no existe.");

        // Validación de detalles
        List<DetalleCotizacionDTO> detalles = solicitud.getDetalleCotizacionDTOS();
        if (detalles == null || detalles.isEmpty())
            throw new InvalidFieldException("Debe enviar al menos un detalle.");

        // -------------------------------
        // Cálculo del valor total
        // -------------------------------
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (DetalleCotizacionDTO d : detalles) {

            if (d.getCantidad() <= 0)
                throw new InvalidFieldException("La cantidad debe ser mayor que cero.");

            if (d.getValor_unitario().compareTo(BigDecimal.ZERO) <= 0)
                throw new InvalidFieldException("El valor unitario debe ser mayor a cero.");

            BigDecimal subtotal =
                    d.getValor_unitario().multiply(new BigDecimal(d.getCantidad()));

            d.setSubtotal(subtotal);
            valorTotal = valorTotal.add(subtotal);
        }

        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidFieldException("El valor total debe ser mayor a cero.");

        // -------------------------------
        // Cálculo de IVA o AIU
        // -------------------------------
        CotizacionDTO cotFull;
        BigDecimal administracion = BigDecimal.ZERO;
        BigDecimal imprevistos = BigDecimal.ZERO;
        BigDecimal utilidades = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        BigDecimal totalPagar = BigDecimal.ZERO;

        if (Boolean.TRUE.equals(solicitud.getExistIva())) {

            iva = valorTotal.multiply(new BigDecimal("0.19"));
            totalPagar = valorTotal.add(iva);

            cotFull = new CotizacionDTO(
                    cot.getId_cotizacion(),
                    cot.getId_trabajador(),
                    cot.getId_cliente(),
                    cot.getId_lugar(),
                    cot.getFecha_realizacion(),
                    cot.getReferencia(),
                    valorTotal,
                    cot.getEstado(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    iva,
                    totalPagar
            );

        } else {
            AIUDTO aiu = solicitud.getAiudto();

            BigDecimal adminPct = BigDecimal.ZERO;
            BigDecimal imprePct = BigDecimal.ZERO;
            BigDecimal utilPct  = BigDecimal.ZERO;

            if (aiu != null) {
                if (aiu.getAdministracion() != null)
                    adminPct = aiu.validarPorcentaje(aiu.getAdministracion());

                if (aiu.getImprevistos() != null)
                    imprePct = aiu.validarPorcentaje(aiu.getImprevistos());

                if (aiu.getUtilidad() != null)
                    utilPct = aiu.validarPorcentaje(aiu.getUtilidad());
            }

            administracion = valorTotal.multiply(adminPct);
            imprevistos = valorTotal.multiply(imprePct);
            utilidades = valorTotal.multiply(utilPct);
            iva = utilidades.multiply(new BigDecimal("0.19"));

            totalPagar = valorTotal
                    .add(administracion)
                    .add(imprevistos)
                    .add(utilidades)
                    .add(iva);

            cotFull = new CotizacionDTO(
                    cot.getId_cotizacion(),
                    cot.getId_trabajador(),
                    cot.getId_cliente(),
                    cot.getId_lugar(),
                    cot.getFecha_realizacion(),
                    cot.getReferencia(),
                    valorTotal,
                    cot.getEstado(),
                    administracion,
                    imprevistos,
                    utilidades,
                    iva,
                    totalPagar
            );
        }

        // Guardar cotización
        cotFull = service.agregarCotizacion(cotFull);

        // Guardar detalles
        for (DetalleCotizacionDTO d : detalles) {
            d.setId_cotizacion(cotFull.getId_cotizacion());
            detalleService.agregarDetalleCot(d);
        }

        // Generar PDF con excepción personalizada
        byte[] pdf;

        try {
            pdf = pdfService.generarArchivo(cotFull, cliente, lugar, detalles);
        } catch (Exception e) {
            throw new PdfGenerationException("Error al generar el PDF: " + e.getMessage());
        }

        String nombreArchivo = pdfService.descargarPDF(cotFull, pdf);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }


    // --------------------------------------------------
    // LISTAR TODAS LAS COTIZACIONES
    // --------------------------------------------------
    @GetMapping("/listar")
    public List<CotizacionDTO> listarCotizaciones() {
        return service.listarCotizacion();
    }


    // --------------------------------------------------
    // BUSCAR POR ID
    // --------------------------------------------------
    @GetMapping("/buscar/{id}")
    public CotizacionDTO buscarCotizacion(@PathVariable int id) {
        CotizacionDTO cot = service.buscarCotizacion(id);
        if (cot == null)
            throw new ResourceNotFoundException("No existe la cotización con ID: " + id);
        return cot;
    }


    // --------------------------------------------------
    // OBTENER DETALLES DE UNA COTIZACIÓN
    // --------------------------------------------------
    @GetMapping("/{id}/detalles")
    public List<DetalleCotizacionDTO> obtenerDetalles(@PathVariable int id) {
        if (!service.existirCot(id))
            throw new ResourceNotFoundException("No existe la cotización con ID: " + id);

        return detalleService.listarDetallesCot()
                .stream()
                .filter(d -> d.getId_cotizacion() == id)
                .toList();
    }


    // --------------------------------------------------
    // BORRAR COTIZACIÓN + DETALLES
    // --------------------------------------------------
    @DeleteMapping("/borrar/{id}")
    public String borrar(@PathVariable int id) {

        if (!service.existirCot(id))
            throw new ResourceNotFoundException("No existe la cotización con ID: " + id);

        detalleService.listarDetallesCot()
                .stream()
                .filter(d -> d.getId_cotizacion() == id)
                .forEach(d -> detalleService.borrarDetalleCot(d.getId_detalle_cotizacion()));

        return service.borrarCotizacion(id) + " + detalles eliminados";
    }


    // --------------------------------------------------
    // ACTUALIZAR COTIZACIÓN
    // --------------------------------------------------
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @RequestBody CotizacionDTO dto) {

        if (dto.getId_cotizacion() != id)
            throw new InvalidFieldException("No se puede cambiar el ID de la cotización.");

        List<String> estados = List.of("ACTIVO", "PENDIENTE", "RECHAZADO");

        if (!estados.contains(dto.getEstado()))
            throw new InvalidFieldException("Estado inválido. Solo se permite: " + estados);

        return service.actualizarCot(id, dto);
    }


    // --------------------------------------------------
    // BORRAR DETALLE
    // --------------------------------------------------
    @DeleteMapping("/borrar/{idCot}/detalle/{idDetalle}")
    public String borrarDetalle(@PathVariable int idCot, @PathVariable int idDetalle) {
        if (!service.existirCot(idCot))
            throw new ResourceNotFoundException("No existe la cotización con ID: " + idCot);

        DetalleCotizacionDTO det = detalleService.listarDetallesCot()
                .stream()
                .filter(d -> d.getId_detalle_cotizacion() == idDetalle)
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("El detalle no existe."));

        if (det.getId_cotizacion() != idCot)
            throw new InvalidFieldException("Ese detalle no pertenece a la cotización indicada.");

        detalleService.borrarDetalleCot(idDetalle);

        return "Detalle eliminado correctamente.";
    }


    // --------------------------------------------------
    // ACTUALIZAR DETALLE
    // --------------------------------------------------
    @PutMapping("/actualizar/{idCot}/detalle/{idDetalle}")
    public String actualizarDetalle(@PathVariable int idCot,
                                    @PathVariable int idDetalle,
                                    @RequestBody DetalleCotizacionDTO dto) {

        System.out.println("ID COTIZACION ACTUAL: " + idCot);

        if (!service.existirCot(idCot))
            throw new ResourceNotFoundException("No existe la cotización con ID: " + idCot);

        DetalleCotizacionDTO actual = detalleService.listarDetallesCot()
                .stream()
                .filter(d -> d.getId_detalle_cotizacion() == idDetalle)
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe el detalle con ID: " + idDetalle));

        if (actual.getId_cotizacion() != idCot)
            throw new InvalidFieldException("El detalle no pertenece a esta cotización.");

        if (dto.getCantidad() <= 0)
            throw new InvalidFieldException("La cantidad debe ser mayor a cero.");

        if (dto.getValor_unitario().compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidFieldException("El valor unitario debe ser mayor a cero.");

        dto.setSubtotal(
                dto.getValor_unitario().multiply(new BigDecimal(dto.getCantidad()))
        );

        return detalleService.actualizarDetalle(idDetalle, dto);
    }

    // --------------------------------------------------
// CREAR DETALLE EN COTIZACIÓN EXISTENTE
// --------------------------------------------------
    @PostMapping("/{idCot}/detalle")
    public ResponseEntity<String> agregarDetalle(
            @PathVariable int idCot,
            @RequestBody DetalleCotizacionDTO dto) {

        if (!service.existirCot(idCot))
            throw new ResourceNotFoundException("No existe la cotización con ID: " + idCot);

        if (dto.getCantidad() <= 0)
            throw new InvalidFieldException("La cantidad debe ser mayor a cero.");

        if (dto.getValor_unitario().compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidFieldException("El valor unitario debe ser mayor a cero.");

        dto.setId_cotizacion(idCot);

        dto.setSubtotal(
                dto.getValor_unitario().multiply(new BigDecimal(dto.getCantidad()))
        );

        detalleService.agregarDetalleCot(dto);

        return ResponseEntity.ok("Detalle agregado correctamente.");
    }

}
