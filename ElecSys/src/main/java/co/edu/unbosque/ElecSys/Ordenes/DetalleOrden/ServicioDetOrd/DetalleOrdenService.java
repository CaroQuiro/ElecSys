package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.ServicioDetOrd;

import co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.DTODetOrd.DetalleOrdenDTO;
import co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.EntidadDetOrd.DetalleOrdenEntidad;

import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra.OrdenDeTrabajoDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.EntidadOrdTra.OrdenDeTrabajoEntidad;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetalleOrdenService  implements DetalleOrdenInterface{
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    /**
     * @param dto
     * @return
     */
    @Override
    public String agregarDetalleOrden(DetalleOrdenDTO dto) {
        DetalleOrdenEntidad detalleOrden = new DetalleOrdenEntidad(
                dto.getIdDetalleOrden(),
                dto.getIdOrden(),
                dto.getActividad(),
                dto.getObservaciones(),
                dto.getDuracion()
        );
        try {
            detalleOrdenRepository.save(detalleOrden);
            return "Detalle de orden creada con exito creada con exito";
        }catch (Exception e){
            return "Error al crear detalle de orden";
        }
    }

    /**
     * @param idOrdenAnt
     * @param ordenNueva
     * @return
     */
    @Override
    public String editarDetalleOrden(int idOrdenAnt, DetalleOrdenDTO ordenNueva) {
        try{
            if(!detalleOrdenRepository.findById(idOrdenAnt).isEmpty()){
                detalleOrdenRepository.deleteById(idOrdenAnt);
                DetalleOrdenEntidad detalleOrden = new DetalleOrdenEntidad(
                        ordenNueva.getIdDetalleOrden(),
                        ordenNueva.getIdOrden(),
                        ordenNueva.getActividad(),
                        ordenNueva.getObservaciones(),
                        ordenNueva.getDuracion()
                );
                detalleOrdenRepository.save(detalleOrden);
                return "El detalle de la orden se actualizo con exito";
            }else{
                return "El id del detalle de la orden no fue encontrada";
            }

        } catch (Exception e) {
            return "Error al actualizar detalle de orden";
        }
    }

    /**
     * @param idOrden
     * @return
     */
    @Override
    public String borrarDetalleOrden(int idOrden) {
        try{
            detalleOrdenRepository.deleteById(idOrden);
            return "El detalle de la orden fue borrada correctamente";
        }catch (Exception e){
            return "Hubo un error al intentar borrar el detalle de la orden";
        }
    }

    /**
     * @return
     */
    @Override
    public List<DetalleOrdenDTO> listarDetalleOrdenPorIdOrden() {
        try {
            List<DetalleOrdenEntidad> detalleOrdenEntidades = detalleOrdenRepository.findAll();
            List<DetalleOrdenDTO> detalleOrdenDtos = new ArrayList<>();

            for(DetalleOrdenEntidad detalleOrden : detalleOrdenEntidades){
                detalleOrdenDtos.add(new DetalleOrdenDTO(
                        detalleOrden.getIdDetalleOrden(),
                        detalleOrden.getIdOrden(),
                        detalleOrden.getActividad(),
                        detalleOrden.getObservaciones(),
                        detalleOrden.getDuracion()));
            };
            return detalleOrdenDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idOrden
     * @return
     */
    @Override
    public DetalleOrdenDTO buscarDetalleOrden(int idOrden) {
        try {
            DetalleOrdenEntidad entidadOpt = detalleOrdenRepository.findById(idOrden).orElse(null);

            if(entidadOpt == null){
                return null;
            }else{
                return new DetalleOrdenDTO(entidadOpt.getIdDetalleOrden(),
                        entidadOpt.getIdOrden(),
                        entidadOpt.getActividad(),
                        entidadOpt.getObservaciones(),
                        entidadOpt.getDuracion());
            }
        } catch (Exception e) {
            return new DetalleOrdenDTO();
        }
    }
}
