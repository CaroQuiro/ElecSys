package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.DTODetOrd;

public class DetalleOrdenDTO {

    private int idDetalleOrden;
    private int idOrden;
    private String actividad;
    private String observaciones;
    private String duracion;

    public DetalleOrdenDTO() {}

    public DetalleOrdenDTO(int idDetalleOrden, int idOrden, String actividad, String observaciones, String duracion) {
        this.idDetalleOrden = idDetalleOrden;
        this.idOrden = idOrden;
        this.actividad = actividad;
        this.observaciones = observaciones;
        this.duracion = duracion;
    }

    public int getIdDetalleOrden() {
        return idDetalleOrden;
    }

    public void setIdDetalleOrden(int idDetalleOrden) {
        this.idDetalleOrden = idDetalleOrden;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
}
