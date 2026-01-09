package co.edu.unbosque.ElecSys.HistorialActividad.ServicioHis;

import co.edu.unbosque.ElecSys.HistorialActividad.DTOHis.HistorialActividadDTO;
import co.edu.unbosque.ElecSys.HistorialActividad.EntidadHis.HistorialActividadEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistorialActividadService implements HistorialActividadInterface{

    @Autowired
    private HistorialActividadRepository historialActividadRepository;


    /**
     * @param dto
     * @return
     */
    @Override
    public String agregarHistorialActividad(HistorialActividadDTO dto) {
        HistorialActividadEntidad historialActi = new HistorialActividadEntidad(
                dto.getIdHistorial(),
                dto.getIdTrabajador(),
                dto.getModuloSistema(),
                dto.getAccionRealizada(),
                dto.getFechaRealizacion(),
                dto.getHora()
                );
        try {
            historialActividadRepository.save(historialActi);
            return "El historial de actividad fue creado correctamente";
        } catch (Exception e) {
            return "Hubo un error al crear el historial de actividad";
        }
    }

    /**
     * @return
     */
    @Override
    public List<HistorialActividadDTO> listarHistorialActividad() {
        try {
            List<HistorialActividadEntidad> historialActEntidades = historialActividadRepository.findAll();
            List<HistorialActividadDTO> historialActDtos = new ArrayList<>();

            for(HistorialActividadEntidad historialActividad : historialActEntidades){
                historialActDtos.add(new HistorialActividadDTO(
                        historialActividad.getIdHistorial(),
                        historialActividad.getIdTrabajador(),
                        historialActividad.getModuloSistema(),
                        historialActividad.getAccionRealizada(),
                        historialActividad.getFechaRealizacion(),
                        historialActividad.getHora()
                ));
            };
            return historialActDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idTrabajador
     * @return
     */
    @Override
    public List<HistorialActividadDTO> listarHistorialActividadPorIdTrabajador(int idTrabajador) {
        try {
            List<HistorialActividadEntidad> historialActEntidades = historialActividadRepository.findAllByIdTrabajador(idTrabajador);
            List<HistorialActividadDTO> historialActDtos = new ArrayList<>();

            for(HistorialActividadEntidad historialActividad : historialActEntidades){
                historialActDtos.add(new HistorialActividadDTO(
                        historialActividad.getIdHistorial(),
                        historialActividad.getIdTrabajador(),
                        historialActividad.getModuloSistema(),
                        historialActividad.getAccionRealizada(),
                        historialActividad.getFechaRealizacion(),
                        historialActividad.getHora()
                ));
            };
            return historialActDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idHistorial
     * @return
     */
    @Override
    public HistorialActividadDTO buscarHistorialActividad(int idHistorial) {
        try {
            HistorialActividadEntidad entidadOpt = historialActividadRepository.findById(idHistorial).orElse(null);

            if(entidadOpt == null){
                return null;
            }else{
                return new HistorialActividadDTO(entidadOpt.getIdHistorial(),
                        entidadOpt.getIdTrabajador(),
                        entidadOpt.getModuloSistema(),
                        entidadOpt.getAccionRealizada(),
                        entidadOpt.getFechaRealizacion(),
                        entidadOpt.getHora());
            }
        } catch (Exception e) {
            return new HistorialActividadDTO();
        }
    }

    @Override
    public List<HistorialActividadDTO> listarHistorialActividadPorFecha(Date fecha) {
        try {
            List<HistorialActividadEntidad> entidades =
                    historialActividadRepository.findByFechaRealizacion(fecha);

            if (entidades == null || entidades.isEmpty()) {
                return List.of(); // lista vacía
            }

            return entidades.stream().map(entidad ->
                    new HistorialActividadDTO(
                            entidad.getIdHistorial(),
                            entidad.getIdTrabajador(),
                            entidad.getModuloSistema(),
                            entidad.getAccionRealizada(),
                            entidad.getFechaRealizacion(),
                            entidad.getHora()
                    )
            ).toList();

        } catch (Exception e) {
            throw new RuntimeException("Error buscando historial por fecha: " + e.getMessage());
        }
    }


    @Override
    public List<HistorialActividadDTO> listarHistorialActividadPorAccion(String accion) {
        try {
            List<HistorialActividadEntidad> entidades =
                    historialActividadRepository.findByAccionRealizadaContainingIgnoreCase(accion);

            if (entidades == null || entidades.isEmpty()) {
                return List.of(); // lista vacía
            }

            return entidades.stream().map(entidad ->
                    new HistorialActividadDTO(
                            entidad.getIdHistorial(),
                            entidad.getIdTrabajador(),
                            entidad.getModuloSistema(),
                            entidad.getAccionRealizada(),
                            entidad.getFechaRealizacion(),
                            entidad.getHora()
                    )
            ).toList();

        } catch (Exception e) {
            throw new RuntimeException("Error buscando historial por acción: " + e.getMessage());
        }
    }

}
