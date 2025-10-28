package co.edu.unbosque.ElecSys.Notificacion.CalendarioNot;

import co.edu.unbosque.ElecSys.Notificacion.ServicioNot.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificacionCalendario {

    @Autowired
    private NotificacionService notificacionService;

    @Scheduled(fixedRate = 86400000)
    public void revisarNotificaciones() {
        System.out.println("Revisando notificaciones...");
        notificacionService.revisarYEnviarNotificacionesTrabajadores();
        notificacionService.revisarYEnviarNotificacionesClientes();
    }
}
