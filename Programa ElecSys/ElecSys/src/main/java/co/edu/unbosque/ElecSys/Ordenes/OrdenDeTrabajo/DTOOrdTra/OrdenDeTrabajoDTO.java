package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra;

import java.sql.Date;

public class OrdenDeTrabajoDTO {

    private int idOrden;
    private int idOrdenVisita;
    private int idLugar;
    private int idCliente;
    private int idTrabajador;
    private Date fechaRealizacion;
    private String estado;

    public OrdenDeTrabajoDTO() {}

    public OrdenDeTrabajoDTO(int idOrden, int idOrdenVisita, int idLugar, int idCliente, int idTrabajador, Date fechaRealizacion, String estado) {
        this.idOrden = idOrden;
        this.idOrdenVisita = idOrdenVisita;
        this.idLugar = idLugar;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.fechaRealizacion = fechaRealizacion;
        this.estado = estado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdOrdenVisita() {
        return idOrdenVisita;
    }

    public void setIdOrdenVisita(int idOrdenVisita) {
        this.idOrdenVisita = idOrdenVisita;
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

    public java.sql.Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(java.sql.Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
