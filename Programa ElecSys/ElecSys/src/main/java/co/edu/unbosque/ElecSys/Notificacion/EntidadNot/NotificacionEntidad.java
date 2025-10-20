package co.edu.unbosque.ElecSys.Notificacion.EntidadNot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "notificacion")
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

    public NotificacionEntidad(int idNotificacion, int idTrabajador, int idCliente, String titulo, String mensaje, Date fechaEnvio, String frecuencia, String tipoDestinatario, String estado) {
        this.idNotificacion = idNotificacion;
        this.idTrabajador = idTrabajador;
        this.idCliente = idCliente;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
        this.frecuencia = frecuencia;
        this.tipoDestinatario = tipoDestinatario;
        this.estado = estado;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
