package co.edu.unbosque.ElecSys.Notificacion.EntidadNot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionEntidad {

    @Id
    @Column(name = "id_notificacion")
    private int idNotificacion;

    @Column(name = "id_trabajador")
    private int idTrabajador;

    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha_envio")
    private Date fechaEnvio;

    @Column(name = "frecuencia")
    private String frecuencia;

    @Column(name = "tipo_destinatario")
    private String tipoDestinatario;

    @Column(name = "estado")
    private String estado;


}
