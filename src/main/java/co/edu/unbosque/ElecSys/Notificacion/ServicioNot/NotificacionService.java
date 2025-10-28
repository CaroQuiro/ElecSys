package co.edu.unbosque.ElecSys.Notificacion.ServicioNot;

import co.edu.unbosque.ElecSys.Notificacion.DTONot.NotificacionDTO;
import co.edu.unbosque.ElecSys.Notificacion.EntidadNot.NotificacionEntidad;
import co.edu.unbosque.ElecSys.Notificacion.EnvioEmail.ConfiguracionEmail;
import co.edu.unbosque.ElecSys.Usuario.Cliente.DTOClie.ClienteDTO;
import co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie.ClienteServiceImpl;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.DTOTra.TrabajadorDTO;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra.TrabajadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;




@Service
public class NotificacionService implements NotificacionInterface{

    @Autowired
    NotificacionRepository notificacionRepository;

    @Autowired
    private ConfiguracionEmail configuracionEmail;

    @Autowired
    private TrabajadorServiceImpl trabajadorService;

    @Autowired
    private ClienteServiceImpl clienteService;

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
//        try {
//            List<NotificacionEntidad> notificacionEntidades = notificacionRepository.findAllByIdUsuario(idUsuario);
//            List<NotificacionDTO> notificacionDtos = new ArrayList<>();
//
//            for(NotificacionEntidad notificacion : notificacionEntidades){
//                notificacionDtos.add(new NotificacionDTO(
//                        notificacion.getIdNotificacion(),
//                        notificacion.getIdTrabajador(),
//                        notificacion.getIdCliente(),
//                        notificacion.getTitulo(),
//                        notificacion.getMensaje(),
//                        notificacion.getFechaEnvio(),
//                        notificacion.getFrecuencia(),
//                        notificacion.getTipoDestinatario(),
//                        notificacion.getEstado()));
//            };
//            return notificacionDtos;
//        } catch (Exception e) {
//            return List.of();
//        }
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

    public void revisarYEnviarNotificacionesTrabajadores() {
        List<NotificacionEntidad> todas = notificacionRepository.findAll();
        if (todas.isEmpty()) {
            System.out.println("No hay notificaciones de trabajadores para procesar.");
            return;
        }
        LocalDateTime ahora = LocalDateTime.now();
        java.sql.Date fechaNuevaGuardar = java.sql.Date.valueOf(ahora.toLocalDate());


        List<TrabajadorDTO> trabajadores = trabajadorService.listarTrabajadores();

        for (NotificacionEntidad n : todas) {
            if (n.getEstado().equals("ENVIADO")) {
                if (debeEnviar(n, ahora)) {
                    String nombreUsuario = "";
                    String correo = "";
                    for(TrabajadorDTO i : trabajadores){
                        if(i.getId_trabajador()==n.getIdTrabajador()){
                            nombreUsuario = i.getNombre();
                            correo = i.getCorreo();
                        }
                    }
                    if (nombreUsuario!=null) {
                        configuracionEmail.crearEmail(nombreUsuario, correo, n.getTitulo(), n.getMensaje(), null);
                        configuracionEmail.envioEmail();
                        System.out.println("Correo enviado a: " + nombreUsuario);
                        n.setFechaEnvio(fechaNuevaGuardar);
                    }
                }
            }
        }
    }


    public void revisarYEnviarNotificacionesClientes() {
        List<NotificacionEntidad> todas = notificacionRepository.findAll();
        if (todas.isEmpty()) {
            System.out.println("No hay notificaciones de trabajadores para procesar.");
            return;
        }
        LocalDateTime ahora = LocalDateTime.now();
        java.sql.Date fechaNuevaGuardar = java.sql.Date.valueOf(ahora.toLocalDate());

        List<ClienteDTO> clientes = clienteService.listarClientes();

        for (NotificacionEntidad n : todas) {
            if (n.getEstado().equals("ENVIADO")) {
                if (debeEnviar(n, ahora)) {
                    String nombreUsuario = "";
                    String correo = "";
                    for(ClienteDTO i : clientes){
                        if(i.getId_cliente()==n.getIdCliente()){
                            nombreUsuario = i.getNombre();
                            correo = i.getCorreo();
                        }
                    }
                    if (nombreUsuario!=null) {
                        configuracionEmail.crearEmail(nombreUsuario, correo, n.getTitulo(), n.getMensaje(), null);
                        configuracionEmail.envioEmail();
                        System.out.println("Correo enviado a: " + nombreUsuario);
                        n.setFechaEnvio(fechaNuevaGuardar);
                    }
                }
            }
        }
    }

    private boolean debeEnviar(NotificacionEntidad n, LocalDateTime ahora) {

        java.sql.Date ultimaFecha = n.getFechaEnvio();

        if (ultimaFecha == null) {
            return true;
        }

        LocalDateTime fechaEnvio = ultimaFecha.toLocalDate().atStartOfDay();

        Duration diferencia = Duration.between(fechaEnvio, ahora);

        switch (n.getFrecuencia().toLowerCase()) {
            case "DIARIO":
                return diferencia.toDays() == 1;
            case "SEMANAL":
                return diferencia.toDays() == 7;
            case "MENSUAL":
                return diferencia.toDays() == 30;
            case "ANUAL":
                return diferencia.toDays() == 365;
            default:
                return false;
        }
    }

}
