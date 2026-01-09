package co.edu.unbosque.ElecSys.Notificacion.EntidadNot;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion_destinatario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDestinatarioEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion_destinatario")
    private long idNotificacionDestinatario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_notificacion", nullable = false)
    private NotificacionEntidad notificacion;

    @Column(name = "tipo_destinatario", nullable = false)
    private String tipoDestinatario; // CLIENTE | TRABAJADOR

    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "id_trabajador")
    private int idTrabajador;

    @Column(name = "enviado", nullable = false)
    private boolean enviado;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
}

