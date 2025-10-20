package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.EntidadDetOrd;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_orden")
public class DetalleOrdenEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_orden")
    private int idDetalleOrden;

    @Column(name = "id_orden", nullable = false)
    private int idOrden;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "duracion")
    private String duracion;

    public DetalleOrdenEntidad(int idDetalleOrden, int idOrden, String actividad, String observaciones, String duracion) {
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
