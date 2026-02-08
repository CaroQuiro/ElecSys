package co.edu.unbosque.ElecSys.Notificacion.ServicioNot;


import co.edu.unbosque.ElecSys.Notificacion.DTONot.NotificacionDTO;
import co.edu.unbosque.ElecSys.Notificacion.EntidadNot.NotificacionDestinatarioEntidad;
import co.edu.unbosque.ElecSys.Notificacion.EntidadNot.NotificacionEntidad;
import co.edu.unbosque.ElecSys.Notificacion.EntidadNot.NotificacionProgramacionEntidad;
import co.edu.unbosque.ElecSys.Notificacion.EnvioEmail.ConfiguracionEmail;
import co.edu.unbosque.ElecSys.Usuario.Cliente.DTOClie.ClienteDTO;
import co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie.ClienteServiceImpl;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.DTOTra.TrabajadorDTO;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra.TrabajadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
public class NotificacionService implements NotificacionInterface {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private NotificacionDestinatarioRepository destinatarioRepository;

    @Autowired
    private NotificacionProgramacionRepository programacionRepository;

    @Autowired
    private ConfiguracionEmail configuracionEmail;

    @Autowired
    private TrabajadorServiceImpl trabajadorService;

    @Autowired
    private ClienteServiceImpl clienteService;

    /* =====================================================
       CREAR NOTIFICACIÓN
       ===================================================== */
    @Override
    public String crearNotificacion(NotificacionDTO dto) {
        try {
            NotificacionEntidad notificacion = new NotificacionEntidad();
            notificacion.setTitulo(dto.getTitulo());
            notificacion.setMensaje(dto.getMensaje());
            notificacion.setTipo(dto.getTipo());       // UNICA | RECURRENTE
            notificacion.setEstado("ACTIVA");
            notificacion.setFechaCreacion(LocalDateTime.now());

            notificacionRepository.save(notificacion);

            return "Notificación creada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear la notificación";
        }
    }

    /* =====================================================
       EDITAR NOTIFICACIÓN
       ===================================================== */
    @Override
    public String editarNotificacion(long idNotificacionAnt, NotificacionDTO notificacionNueva) {
        return notificacionRepository.findById((int) idNotificacionAnt)
                .map(notificacion -> {
                    notificacion.setTitulo(notificacionNueva.getTitulo());
                    notificacion.setMensaje(notificacionNueva.getMensaje());
                    notificacion.setTipo(notificacionNueva.getTipo());
                    notificacionRepository.save(notificacion);
                    return "Notificación editada correctamente";
                })
                .orElse("No se encontró la notificación");
    }

    /* =====================================================
       BORRAR NOTIFICACIÓN
       ===================================================== */
    @Override
    public String borrarNotificacion(long idNotificacion) {
        try {
            notificacionRepository.deleteById((int) idNotificacion);
            return "Notificación eliminada correctamente";
        } catch (Exception e) {
            return "Error al eliminar la notificación";
        }
    }

    /* =====================================================
       LISTAR NOTIFICACIONES
       ===================================================== */
    @Override
    public List<NotificacionDTO> listarNotificaciones() {
        return notificacionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    /* =====================================================
       BUSCAR NOTIFICACIÓN
       ===================================================== */
    @Override
    public NotificacionDTO buscarNotificacion(long idNotificacion) {
        return notificacionRepository.findById((int) idNotificacion)
                .map(this::mapToDTO)
                .orElse(null);
    }

    /* =====================================================
       DESACTIVAR NOTIFICACIÓN
       ===================================================== */
    @Override
    public String desactivarNotificacion(long idNotificacion) {
        return notificacionRepository.findById((int) idNotificacion)
                .map(notificacion -> {
                    notificacion.setEstado("INACTIVA");
                    notificacionRepository.save(notificacion);
                    return "Notificación desactivada";
                })
                .orElse("No se encontró la notificación");
    }

    /* =====================================================
       ENVÍO MANUAL DE NOTIFICACIÓN
       ===================================================== */
    @Override
    public void envioNotificacion(NotificacionDTO dto) {
        NotificacionEntidad notificacion = notificacionRepository
                .findById((int) dto.getIdNotificacion())
                .orElseThrow();

        List<NotificacionDestinatarioEntidad> destinatarios =
                destinatarioRepository.findByNotificacion(notificacion);

        for (NotificacionDestinatarioEntidad d : destinatarios) {

            if (d.isEnviado()) continue;

            String correo;
            String nombre;

            if ("CLIENTE".equals(d.getTipoDestinatario())) {
                var cliente = clienteService.buscarCliente(d.getIdCliente());
                correo = cliente.getCorreo();
                nombre = cliente.getNombre();
            } else {
                var trabajador = trabajadorService.buscarTrabajador(d.getIdTrabajador());
                correo = trabajador.getCorreo();
                nombre = trabajador.getNombre();
            }

            configuracionEmail.crearEmail(
                    nombre,
                    correo,
                    notificacion.getTitulo(),
                    notificacion.getMensaje(),
                    null
            );

            configuracionEmail.envioEmail();

            d.setEnviado(true);
            d.setFechaEnvio(LocalDateTime.now());
            destinatarioRepository.save(d);
        }
    }

    /* =====================================================
       ENVÍO AUTOMÁTICO CADA 24 HORAS
       ===================================================== */
    @Override
    @Scheduled(fixedRate = 86400000) // 24 horas
    public void revisarYEnviarNotificacionesprogramadas() {

        List<NotificacionProgramacionEntidad> programadas =
                programacionRepository.findAll();

        LocalDate hoy = LocalDate.now();

        for (NotificacionProgramacionEntidad prog : programadas) {

            NotificacionEntidad notificacion = prog.getNotificacion();

            if (!"ACTIVA".equals(notificacion.getEstado())) continue;

            if (prog.getFechaFin() != null && hoy.isAfter(ChronoLocalDate.from(prog.getFechaFin()))) continue;

            boolean debeEnviar = prog.getUltimaEjecucion() == null ||
                    prog.getUltimaEjecucion()
                            .toLocalDate()
                            .isBefore(hoy);

            if (!debeEnviar) continue;

            envioNotificacion(mapToDTO(notificacion));

            prog.setUltimaEjecucion(LocalDateTime.now());
            programacionRepository.save(prog);
        }
    }

    /* =====================================================
       MAPPER INTERNO
       ===================================================== */
    private NotificacionDTO mapToDTO(NotificacionEntidad entidad) {
        return new NotificacionDTO(
                entidad.getIdNotificacion(),   // ya es Long → se convierte a long
                entidad.getTitulo(),
                entidad.getMensaje(),
                entidad.getTipo(),
                entidad.getEstado(),
                entidad.getFechaCreacion()     // ya es LocalDateTime
        );
    }

}


