package co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.ServicioDetalleActividad;

import co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.DTODetalleActividad.DetalleActividadDTO;
import co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.EntidadDetalleActividad.DetalleActividadEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleActividadService implements DetalleActividadInterface{

    @Autowired
    private DetalleActividadRepository detalleActividadRepository;

    /**
     * @param dto
     * @return
     */
    @Override
    public String agregarDetalleActividad(DetalleActividadDTO dto) {
        DetalleActividadEntidad detalleActEntidad = new DetalleActividadEntidad(
                dto.getIdDetalleActividad(),
                dto.getIdHistorial(),
                dto.getCampoAfectado(),
                dto.getValorAnterior(),
                dto.getValorNuevo()
        );
        try {
            detalleActividadRepository.save(detalleActEntidad);
            return "Detalle de actividad guardado correctamente";
        } catch (Exception e) {
            return "Error al guardar el detalle de la actividad";
        }
    }

    /**
     * @param idHistorial
     * @return
     */
    @Override
    public List<DetalleActividadDTO> listarDetalleActividadPorIdHistorial(int idHistorial) {
        try {
            List<DetalleActividadEntidad> detalleActEntidades = detalleActividadRepository.findAllByIdHistorial(idHistorial);
            List<DetalleActividadDTO> detalleActDtos = new ArrayList<>();

            for(DetalleActividadEntidad detalleActividad : detalleActEntidades){
                detalleActDtos.add(new DetalleActividadDTO(
                        detalleActividad.getIdDetalleActividad(),
                        detalleActividad.getIdHistorial(),
                        detalleActividad.getCampoAfectado(),
                        detalleActividad.getValorAnterior(),
                        detalleActividad.getValorNuevo()
                ));
            };
            return detalleActDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idDetalleActividad
     * @return
     */
    @Override
    public DetalleActividadDTO buscarDetalleActividad(int idDetalleActividad) {
        try {
            DetalleActividadEntidad entidadOpt = detalleActividadRepository.findById(idDetalleActividad).orElse(null);

            if(entidadOpt == null){
                return null;
            }else{
                return new DetalleActividadDTO(entidadOpt.getIdDetalleActividad(),
                        entidadOpt.getIdHistorial(),
                        entidadOpt.getCampoAfectado(),
                        entidadOpt.getValorAnterior(),
                        entidadOpt.getValorNuevo());
            }
        } catch (Exception e) {
            return new DetalleActividadDTO();
        }
    }
}
