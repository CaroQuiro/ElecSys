package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis;

import java.util.Date;

public class OrdenDeVisitaDTO {

    private int id_orden_visita;
    private Date fecha;
    private String descripcion;

    public OrdenDeVisitaDTO(int id_orden_visita, Date fecha, String descripcion) {
        this.id_orden_visita = id_orden_visita;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getId_orden_visita() {
        return id_orden_visita;
    }

    public void setId_orden_visita(int id_orden_visita) {
        this.id_orden_visita = id_orden_visita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
