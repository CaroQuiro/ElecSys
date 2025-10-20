package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis;

import java.sql.Date;

public class OrdenDeVisitaDTO {

    private int idVisita;
    private int idLugar;
    private int idCliente;
    private int idTrabajador;
    private Date fechaRealizacion;
    private String descripcion;
    private String estado;

    public OrdenDeVisitaDTO() {}

    public OrdenDeVisitaDTO(int idVisita, int idLugar, int idCliente, int idTrabajador, Date fechaRealizacion, String descripcion, String estado) {
        this.idVisita = idVisita;
        this.idLugar = idLugar;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.fechaRealizacion = fechaRealizacion;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
