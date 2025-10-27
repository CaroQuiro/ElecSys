package co.edu.unbosque.ElecSys.Notificacion.ServicioNot;

import co.edu.unbosque.ElecSys.Notificacion.DTONot.NotificacionDTO;
import co.edu.unbosque.ElecSys.Notificacion.EntidadNot.NotificacionEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionService implements NotificacionInterface{

    @Autowired
    NotificacionRepository notificacionRepository;

    /**
     * @param dto
     * @return
     */
    @Override
    public String crearNotificacion(NotificacionDTO dto) {
        NotificacionEntidad notificacion = new NotificacionEntidad(
                dto.getIdNotificacion(),
                dto.getIdTrabajador(),
                dto.getIdCliente(),
                dto.getTitulo(),
                dto.getMensaje(),
                dto.getFechaEnvio(),
                dto.getFrecuencia(),
                dto.getTipoDestinatario(),
                dto.getEstado()
        );
        try {
            notificacionRepository.save(notificacion);
            return "Se creo la notificacion correctamente";
        } catch (Exception e) {
            return "Hubo un error al crear la notificacion";
        }
    }

    /**
     * @param idNotificacionAnt
     * @param notificacionNueva
     * @return
     */
    @Override
    public String editarNotificacion(int idNotificacionAnt, NotificacionDTO notificacionNueva) {
        try {
            if(!notificacionRepository.findById(idNotificacionAnt).isEmpty()){
                notificacionRepository.deleteById(idNotificacionAnt);
                NotificacionEntidad notificacion = new NotificacionEntidad(
                        notificacionNueva.getIdNotificacion(),
                        notificacionNueva.getIdTrabajador(),
                        notificacionNueva.getIdCliente(),
                        notificacionNueva.getTitulo(),
                        notificacionNueva.getMensaje(),
                        notificacionNueva.getFechaEnvio(),
                        notificacionNueva.getFrecuencia(),
                        notificacionNueva.getTipoDestinatario(),
                        notificacionNueva.getEstado()
                );
                notificacionRepository.save(notificacion);
                return "La notificacion se a actualizado correctamente";
            }else{
                return "La notificacion no fue encontrada";
            }
        } catch (Exception e) {
            return "Hubo un error al actualizar la notificacion";
        }

    }

    /**
     * @param idNotificacion
     * @return
     */
    @Override
    public String borrarNotificacion(int idNotificacion) {
        try {
            notificacionRepository.deleteById(idNotificacion);
            return "La notificacion se borro correctamente";
        } catch (Exception e) {
            return "Hubo un error al borrar la notificacion";
        }

    }

    /**
     * @return
     */
    @Override
    public List<NotificacionDTO> listarNotificaciones() {
        try {
            List<NotificacionEntidad> notificacionEntidades = notificacionRepository.findAll();
            List<NotificacionDTO> notificacionDtos = new ArrayList<>();

            for(NotificacionEntidad notificacion : notificacionEntidades){
                notificacionDtos.add(new NotificacionDTO(
                        notificacion.getIdNotificacion(),
                        notificacion.getIdTrabajador(),
                        notificacion.getIdCliente(),
                        notificacion.getTitulo(),
                        notificacion.getMensaje(),
                        notificacion.getFechaEnvio(),
                        notificacion.getFrecuencia(),
                        notificacion.getTipoDestinatario(),
                        notificacion.getEstado()));
            };
            return notificacionDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @param idUsuario
     * @return
     */
//    @Override
//    public List<NotificacionDTO> listarNotificacionesPor(int idUsuario) {
////        try {
////            List<NotificacionEntidad> notificacionEntidades = notificacionRepository.findAllByIdUsuario(idUsuario);
////            List<NotificacionDTO> notificacionDtos = new ArrayList<>();
////
////            for(NotificacionEntidad notificacion : notificacionEntidades){
////                notificacionDtos.add(new NotificacionDTO(
////                        notificacion.getIdNotificacion(),
////                        notificacion.getIdTrabajador(),
////                        notificacion.getIdCliente(),
////                        notificacion.getTitulo(),
////                        notificacion.getMensaje(),
////                        notificacion.getFechaEnvio(),
////                        notificacion.getFrecuencia(),
////                        notificacion.getTipoDestinatario(),
////                        notificacion.getEstado()));
////            };
////            return notificacionDtos;
////        } catch (Exception e) {
////            return List.of();
////        }
//    }

    /**
     * @param idNotificacion
     * @return
     */
    @Override
    public NotificacionDTO buscarNotificacion(int idNotificacion) {
        try {
            NotificacionEntidad notificacionEntidad = notificacionRepository.findById(idNotificacion).orElse(null);
            if(notificacionEntidad==null){
                return null;
            }else {
                return new NotificacionDTO(
                        notificacionEntidad.getIdNotificacion(),
                        notificacionEntidad.getIdTrabajador(),
                        notificacionEntidad.getIdCliente(),
                        notificacionEntidad.getTitulo(),
                        notificacionEntidad.getMensaje(),
                        notificacionEntidad.getFechaEnvio(),
                        notificacionEntidad.getFrecuencia(),
                        notificacionEntidad.getTipoDestinatario(),
                        notificacionEntidad.getEstado());

            }
        } catch (Exception e) {
            return null;
        }
    }
}
