package co.edu.unbosque.ElecSys.Notificacion.ControladorNot;

import co.edu.unbosque.ElecSys.Notificacion.DTONot.NotificacionDTO;
import co.edu.unbosque.ElecSys.Notificacion.EnvioEmail.ConfiguracionEmail;
import co.edu.unbosque.ElecSys.Notificacion.ServicioNot.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionControlador {

    @Autowired
    private NotificacionService notificacionService;

    @Autowired
    private ConfiguracionEmail configuracionEmail;

    @GetMapping("/listar")
    public List<NotificacionDTO> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
    }

    @PostMapping("/agregar")
    public String agregarNotificacion(@RequestBody NotificacionDTO dto) {
        return notificacionService.crearNotificacion(dto);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarNotificacion(@PathVariable int id) {
        return notificacionService.borrarNotificacion(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarNotificacion(@PathVariable int id, @RequestBody NotificacionDTO dto) {
        return notificacionService.editarNotificacion(id, dto);
    }

    @PostMapping("/enviar-correo")
    public String enviarCorreoDePrueba(@RequestParam String nombreUsuario,
                                       @RequestParam String correo,
                                       @RequestParam String asunto,
                                       @RequestParam String mensaje,
                                       @RequestParam(required = false) File[] archivos) {
        try {
            ConfiguracionEmail configuracionEmail = new ConfiguracionEmail();

            configuracionEmail.crearEmail(
                    nombreUsuario,
                    correo,
                    asunto,
                    mensaje,
                    null
            );

            return configuracionEmail.envioEmail();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al enviar el correo: " + e.getMessage();
        }
    }

}
