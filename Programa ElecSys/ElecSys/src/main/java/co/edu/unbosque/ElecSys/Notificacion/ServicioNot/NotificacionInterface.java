package co.edu.unbosque.ElecSys.Notificacion.ServicioNot;

import co.edu.unbosque.ElecSys.Notificacion.DTONot.NotificacionDTO;

import java.util.List;

public interface NotificacionInterface {

    public String crearNotificacion(NotificacionDTO dto);

    public String editarNotificacion(int idNotificacionAnt, NotificacionDTO notificacionNueva);

    public String borrarNotificacion(int idNotificacion);

    public List<NotificacionDTO> listarNotificaciones();

    //public List<NotificacionDTO> listarNotificacionesPor(int idUsuario);

    public NotificacionDTO buscarNotificacion(int idNotificacion);
}
