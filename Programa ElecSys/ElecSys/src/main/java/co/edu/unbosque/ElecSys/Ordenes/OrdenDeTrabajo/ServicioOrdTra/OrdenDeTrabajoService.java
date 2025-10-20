package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.ServicioOrdTra;

import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra.OrdenDeTrabajoDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.EntidadOrdTra.OrdenDeTrabajoEntidad;
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

    /**
     * @param dto
     * @return
     */
    @Override
    public String agregarOrdenTrabajo(OrdenDeTrabajoDTO dto) {
        OrdenDeTrabajoEntidad ordenTrabajo = new OrdenDeTrabajoEntidad(
                dto.getIdOrden(),
                dto.getIdOrdenVisita(),
                dto.getIdLugar(),
                dto.getIdCliente(),
                dto.getIdTrabajador(),
                dto.getFechaRealizacion(),
                dto.getEstado()
        );
        try {
            ordenTrabajoRepository.save(ordenTrabajo);
            return "Orden de trabajo creada con exito";
        }catch (Exception e){
            return "Error al crear orden de trabajo";
        }
    }

    /**
     * @param idOrdenAnt
     * @param ordenNueva
     * @return
     */
    @Override
    public String editarOrdenTrabajo(int idOrdenAnt, OrdenDeTrabajoDTO ordenNueva) {
        try{
            if(!ordenTrabajoRepository.findById(idOrdenAnt).isEmpty()){
                ordenTrabajoRepository.deleteById(idOrdenAnt);
                OrdenDeTrabajoEntidad ordenTrabajo = new OrdenDeTrabajoEntidad(
                        ordenNueva.getIdOrden(),
                        ordenNueva.getIdOrdenVisita(),
                        ordenNueva.getIdLugar(),
                        ordenNueva.getIdCliente(),
                        ordenNueva.getIdTrabajador(),
                        ordenNueva.getFechaRealizacion(),
                        ordenNueva.getEstado()
                );
                ordenTrabajoRepository.save(ordenTrabajo);
                return "La orden de trabajo se actualizo con exito";
            }else{
                return "El id de la orden de trabajo no fue encontrada";
            }

        } catch (Exception e) {
            return "Error al actualizar orden de trabajo";
        }
    }


    /**
     * @param idOrden
     * @return
     */
    @Override
    public String borrarOrdenTrabajo(int idOrden) {
        try{
            ordenTrabajoRepository.deleteById(idOrden);
            return "La orden de trabajo fue borrada correctamente";
        }catch (Exception e){
            return "Error al borrar la orden de trabajo";
        }
    }

    /**
     * @return
     */
    @Override
    public List<OrdenDeTrabajoDTO> listarOrdenTrabajo() {
        try {
            List<OrdenDeTrabajoEntidad> trabajoEntidades = ordenTrabajoRepository.findAll();
            List<OrdenDeTrabajoDTO> trabajoDtos = new ArrayList<>();

            for(OrdenDeTrabajoEntidad ordentrabajo : trabajoEntidades){
                trabajoDtos.add(new OrdenDeTrabajoDTO(
                        ordentrabajo.getIdOrden(),
                        ordentrabajo.getIdOrdenVisita(),
                        ordentrabajo.getIdLugar(),
                        ordentrabajo.getIdCliente(),
                        ordentrabajo.getIdTrabajador(),
                        ordentrabajo.getFechaRealizacion(),
                        ordentrabajo.getEstado())
                );
            };
            return trabajoDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idCliente
     * @return
     */
    @Override
    public List<OrdenDeTrabajoDTO> listarOrdenTrabajoPorCliente(int idCliente) {
        try {
            List<OrdenDeTrabajoEntidad> trabajoEntidades = ordenTrabajoRepository.findByIdCliente(idCliente);
            List<OrdenDeTrabajoDTO> trabajoDtos = new ArrayList<>();

            for(OrdenDeTrabajoEntidad ordentrabajo : trabajoEntidades){
                trabajoDtos.add(new OrdenDeTrabajoDTO(
                        ordentrabajo.getIdOrden(),
                        ordentrabajo.getIdOrdenVisita(),
                        ordentrabajo.getIdLugar(),
                        ordentrabajo.getIdCliente(),
                        ordentrabajo.getIdTrabajador(),
                        ordentrabajo.getFechaRealizacion(),
                        ordentrabajo.getEstado()
                ));
            };
            return trabajoDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idOrden
     * @return
     */
    @Override
    public OrdenDeTrabajoDTO buscarOrdenTrabajo(int idOrden) {
        try {
            OrdenDeTrabajoEntidad entidadOpt = ordenTrabajoRepository.findById(idOrden).orElse(null);

            if(entidadOpt == null){
                return null;
            }else{
                return new OrdenDeTrabajoDTO(entidadOpt.getIdOrden(),
                        entidadOpt.getIdOrdenVisita(),
                        entidadOpt.getIdLugar(),
                        entidadOpt.getIdCliente(),
                        entidadOpt.getIdTrabajador(),
                        entidadOpt.getFechaRealizacion(),
                        entidadOpt.getEstado());
            }
        } catch (Exception e) {
            return new OrdenDeTrabajoDTO();
        }
    }

}
