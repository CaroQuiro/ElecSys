package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.ServicioOrdTra;

import co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug.LugarTrabajoRepository;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra.OrdenDeTrabajoDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.EntidadOrdTra.OrdenDeTrabajoEntidad;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.EntidadOrdVis.OrdenDeVisitaEntidad;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ServicioOrdVis.OrdenVisitaRepository;
import co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie.ClienteRepository;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra.TrabajadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenDeTrabajoService implements OrdenDeTrabajoInterface{

    @Autowired
    private OrdenDeTrabajoRepository ordenTrabajoRepository;

    /* =====================================================
       AGREGAR ORDEN DE TRABAJO
       ===================================================== */
    @Override
    public String agregarOrdenTrabajo(OrdenDeTrabajoDTO dto) {
        try {
            OrdenDeTrabajoEntidad orden = new OrdenDeTrabajoEntidad();
            orden.setId_orden_visita(dto.getId_orden_visita()); // puede ser 0 o null según lógica
            orden.setId_lugar(dto.getId_lugar());
            orden.setId_cliente(dto.getId_cliente());
            orden.setId_trabajador(dto.getId_trabajador());
            orden.setFecha_realizacion(dto.getFecha_realizacion());
            orden.setEstado(dto.getEstado());

            ordenTrabajoRepository.save(orden);

            return "Orden de trabajo creada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear la orden de trabajo";
        }
    }

    /* =====================================================
       EDITAR ORDEN DE TRABAJO
       ===================================================== */
    @Override
    public String editarOrdenTrabajo(int idOrdenAnt, OrdenDeTrabajoDTO ordenNueva) {
        try {
            OrdenDeTrabajoEntidad orden = ordenTrabajoRepository
                    .findById(idOrdenAnt)
                    .orElse(null);

            if (orden == null) {
                return "La orden de trabajo no existe";
            }

            orden.setId_orden_visita(ordenNueva.getId_orden_visita());
            orden.setId_lugar(ordenNueva.getId_lugar());
            orden.setId_cliente(ordenNueva.getId_cliente());
            orden.setId_trabajador(ordenNueva.getId_trabajador());
            orden.setFecha_realizacion(ordenNueva.getFecha_realizacion());
            orden.setEstado(ordenNueva.getEstado());

            ordenTrabajoRepository.save(orden);

            return "Orden de trabajo actualizada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar la orden de trabajo";
        }
    }

    /* =====================================================
       BORRAR ORDEN DE TRABAJO
       ===================================================== */
    @Override
    public String borrarOrdenTrabajo(int idOrden) {
        try {
            if (!ordenTrabajoRepository.existsById(idOrden)) {
                return "La orden de trabajo no existe";
            }

            ordenTrabajoRepository.deleteById(idOrden);
            return "Orden de trabajo eliminada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar la orden de trabajo";
        }
    }

    /* =====================================================
       LISTAR TODAS LAS ÓRDENES
       ===================================================== */
    @Override
    public List<OrdenDeTrabajoDTO> listarOrdenTrabajo() {
        return ordenTrabajoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    /* =====================================================
       LISTAR ÓRDENES POR CLIENTE
       ===================================================== */
    @Override
    public List<OrdenDeTrabajoDTO> listarOrdenTrabajoPorCliente(int idCliente) {
        return ordenTrabajoRepository.findById_cliente(idCliente)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    /* =====================================================
       BUSCAR ORDEN POR ID
       ===================================================== */
    @Override
    public OrdenDeTrabajoDTO buscarOrdenTrabajo(int idOrden) {
        return ordenTrabajoRepository.findById(idOrden)
                .map(this::mapToDTO)
                .orElse(null);
    }

    /* =====================================================
       MAPPER PRIVADO
       ===================================================== */
    private OrdenDeTrabajoDTO mapToDTO(OrdenDeTrabajoEntidad entidad) {
        return new OrdenDeTrabajoDTO(
                entidad.getId_orden(),
                entidad.getId_orden_visita(),
                entidad.getId_lugar(),
                entidad.getId_cliente(),
                entidad.getId_trabajador(),
                entidad.getFecha_realizacion(),
                entidad.getEstado()
        );
    }

    /* =====================================================
       EXISTE ORDEN
       ===================================================== */
    public boolean existeOrden(int idOrden) {
        return ordenTrabajoRepository.existsById(idOrden);
    }
}
