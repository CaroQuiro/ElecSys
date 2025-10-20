package co.edu.unbosque.ElecSys.Notificacion.DTONot;

import java.sql.Date;

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

    public NotificacionDTO(int idNotificacion, int idTrabajador, int idCliente, String titulo, String mensaje, Date fechaEnvio, String frecuencia, String tipoDestinatario, String estado) {
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
