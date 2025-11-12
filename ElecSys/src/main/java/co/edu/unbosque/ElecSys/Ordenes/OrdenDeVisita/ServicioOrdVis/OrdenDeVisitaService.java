package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ServicioOrdVis;

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

    @Override
    public String agregarOrdenVisita(OrdenDeVisitaDTO dto) {
        OrdenDeVisitaEntidad ordenVisita = new OrdenDeVisitaEntidad(
                dto.getIdVisita(),
                dto.getIdLugar(),
                dto.getIdCliente(),
                dto.getIdTrabajador(),
                dto.getFechaRealizacion(),
                dto.getDescripcion(),
                dto.getEstado()
        );
        try {
            ordenVisitaRepository.save(ordenVisita);
            return "Orden de visita creada con exito";
        }catch (Exception e){
            return "Error al crear orden de visita";
        }
    }

    @Override
    public String editarOrdenVisita(int idOrdenAnt, OrdenDeVisitaDTO ordenNueva) {
        try{
            if(!ordenVisitaRepository.findById(idOrdenAnt).isEmpty()){
                ordenVisitaRepository.deleteById(idOrdenAnt);
                OrdenDeVisitaEntidad ordenVisita = new OrdenDeVisitaEntidad(
                        ordenNueva.getIdVisita(),
                        ordenNueva.getIdLugar(),
                        ordenNueva.getIdCliente(),
                        ordenNueva.getIdTrabajador(),
                        ordenNueva.getFechaRealizacion(),
                        ordenNueva.getDescripcion(),
                        ordenNueva.getEstado()
                );
                ordenVisitaRepository.save(ordenVisita);
                return "La orden de visita se actualizo con exito";
            }else{
                return "El id de la orden de visita no fue encontrada";
            }

        } catch (Exception e) {
            return "Error al actualizar orden de visita";
        }
    }

    @Override
    public String borrarOrdenVisita(int idOrden) {
        try{
            ordenVisitaRepository.deleteById(idOrden);
            return "La orden de visita fue borrada correctamente";
        }catch (Exception e){
            return "Error al borrar la orden de visita";
        }
    }

    @Override
    public List<OrdenDeVisitaDTO> listarOrdenVisita() {
        try {
            List<OrdenDeVisitaEntidad> visitaEntidades = ordenVisitaRepository.findAll();
            List<OrdenDeVisitaDTO> visitaDtos = new ArrayList<>();

            for(OrdenDeVisitaEntidad ordenVisita : visitaEntidades){
                visitaDtos.add(new OrdenDeVisitaDTO(
                        ordenVisita.getIdVisita(),
                        ordenVisita.getIdLugar(),
                        ordenVisita.getIdCliente(),
                        ordenVisita.getIdTrabajador(),
                        ordenVisita.getFechaRealizacion(),
                        ordenVisita.getDescripcion(),
                        ordenVisita.getEstado())
                );
            };
            return visitaDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public List<OrdenDeVisitaDTO> listarOrdenVisitaPorCliente(int idCliente) {
        try {
            List<OrdenDeVisitaEntidad> visitaEntidades = ordenVisitaRepository.findByIdCliente(idCliente);
            List<OrdenDeVisitaDTO> visitaDtos = new ArrayList<>();

            for(OrdenDeVisitaEntidad ordenVisita : visitaEntidades){
                visitaDtos.add(new OrdenDeVisitaDTO(
                        ordenVisita.getIdVisita(),
                        ordenVisita.getIdLugar(),
                        ordenVisita.getIdCliente(),
                        ordenVisita.getIdTrabajador(),
                        ordenVisita.getFechaRealizacion(),
                        ordenVisita.getDescripcion(),
                        ordenVisita.getEstado())
                );
            };
            return visitaDtos;
        } catch (Exception e) {
            return List.of();
        }
    }
    @Override
    public OrdenDeVisitaDTO buscarOrdenVisita(int idOrden) {
        try {
            OrdenDeVisitaEntidad entidadOpt = ordenVisitaRepository.findById(idOrden).orElse(null);

            if(entidadOpt == null){
                return null;
            }else{
                return new OrdenDeVisitaDTO(entidadOpt.getIdVisita(),
                        entidadOpt.getIdLugar(),
                        entidadOpt.getIdCliente(),
                        entidadOpt.getIdTrabajador(),
                        entidadOpt.getFechaRealizacion(),
                        entidadOpt.getDescripcion(),
                        entidadOpt.getEstado());
            }
        } catch (Exception e) {
            return new OrdenDeVisitaDTO();
        }
    }

}
