package co.edu.unbosque.ElecSys.Notificacion.DTONot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO {

    private int idNotificacion;
    private int idTrabajador;
    private int idCliente;
    private String titulo;
    private String mensaje;
    private Date fechaEnvio;
    private String frecuencia;
    private String tipoDestinatario;
    private String estado;

}
