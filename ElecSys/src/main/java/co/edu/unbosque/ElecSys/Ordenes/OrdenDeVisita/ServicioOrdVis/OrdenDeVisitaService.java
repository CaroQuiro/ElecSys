package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ServicioOrdVis;

import co.edu.unbosque.ElecSys.AutenticacionSeguridad.SeguridadAut.CryptoUtil;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis.OrdenDeVisitaDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.EntidadOrdVis.OrdenDeVisitaEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenDeVisitaService implements OrdenDeVisitaInterface{

    @Autowired
    private OrdenVisitaRepository ordenVisitaRepository;

    /* =====================================================
       AGREGAR ORDEN DE VISITA
       ===================================================== */
    @Override
    public String agregarOrdenVisita(OrdenDeVisitaDTO dto) {
        try {
            OrdenDeVisitaEntidad orden = new OrdenDeVisitaEntidad();
            orden.setId_lugar(dto.getIdLugar());
            orden.setId_cliente(dto.getIdCliente());
            orden.setId_trabajador(dto.getIdTrabajador());
            orden.setFecha_realizacion(dto.getFechaRealizacion());
            orden.setDescripcion(CryptoUtil.encriptar(dto.getDescripcion()));
            orden.setEstado(dto.getEstado());

            ordenVisitaRepository.save(orden);

            return "Orden de visita creada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear la orden de visita";
        }
    }

    /* =====================================================
       EDITAR ORDEN DE VISITA
       ===================================================== */
    @Override
    public String editarOrdenVisita(int idOrdenAnt, OrdenDeVisitaDTO ordenNueva) {
        try {
            OrdenDeVisitaEntidad orden = ordenVisitaRepository
                    .findById(idOrdenAnt)
                    .orElse(null);

            if (orden == null) {
                return "La orden de visita no existe";
            }

            orden.setId_lugar(ordenNueva.getIdLugar());
            orden.setId_cliente(ordenNueva.getIdCliente());
            orden.setId_trabajador(ordenNueva.getIdTrabajador());
            orden.setFecha_realizacion(ordenNueva.getFechaRealizacion());
            orden.setDescripcion(CryptoUtil.encriptar(ordenNueva.getDescripcion()));
            orden.setEstado(ordenNueva.getEstado());

            ordenVisitaRepository.save(orden);

            return "Orden de visita actualizada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al actualizar la orden de visita";
        }
    }

    /* =====================================================
       BORRAR ORDEN DE VISITA
       ===================================================== */
    @Override
    public String borrarOrdenVisita(int idOrden) {
        try {
            if (!ordenVisitaRepository.existsById(idOrden)) {
                return "La orden de visita no existe";
            }

            ordenVisitaRepository.deleteById(idOrden);
            return "Orden de visita eliminada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al eliminar la orden de visita";
        }
    }

    /* =====================================================
       LISTAR TODAS LAS ÓRDENES DE VISITA
       ===================================================== */
    @Override
    public List<OrdenDeVisitaDTO> listarOrdenVisita() {
        return ordenVisitaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    /* =====================================================
       LISTAR ÓRDENES DE VISITA POR CLIENTE
       ===================================================== */
    @Override
    public List<OrdenDeVisitaDTO> listarOrdenVisitaPorCliente(int idCliente) {
        return ordenVisitaRepository.findById_cliente(idCliente)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    /* =====================================================
       BUSCAR ORDEN DE VISITA POR ID
       ===================================================== */
    @Override
    public OrdenDeVisitaDTO buscarOrdenVisita(int idOrden) {
        return ordenVisitaRepository.findById(idOrden)
                .map(this::mapToDTO)
                .orElse(null);
    }

    /* =====================================================
       MAPPER PRIVADO
       ===================================================== */
    private OrdenDeVisitaDTO mapToDTO(OrdenDeVisitaEntidad entidad) {
        return new OrdenDeVisitaDTO(
                entidad.getId_visita(),
                entidad.getId_lugar(),
                entidad.getId_cliente(),
                entidad.getId_trabajador(),
                entidad.getFecha_realizacion(),
                CryptoUtil.desencriptar(entidad.getDescripcion()),
                entidad.getEstado()
        );
    }

    /* =====================================================
       EXISTE ORDEN DE VISITA
       ===================================================== */
    public boolean existeOrden(int idOrden) {
        return ordenVisitaRepository.existsById(idOrden);
    }
}
