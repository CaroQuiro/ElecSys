package co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.EntidadDetalleActividad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_actividad")
public class DetalleActividadEntidad {

    @Id
    @Column(name = "id_detalle_actividad")
    private int idDetalleActividad;

    @Column(name = "id_historial", nullable = false)
    private int idHistorial;

    @Column(name = "campo_afectado", length = 200)
    private String campoAfectado;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_nuevo", columnDefinition = "TEXT")
    private String valorNuevo;

    public DetalleActividadEntidad(int idDetalleActividad, int idHistorial, String campoAfectado, String valorAnterior, String valorNuevo) {
        this.idDetalleActividad = idDetalleActividad;
        this.idHistorial = idHistorial;
        this.campoAfectado = campoAfectado;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
    }


    public int getIdDetalleActividad() {
        return idDetalleActividad;
    }

    public void setIdDetalleActividad(int idDetalleActividad) {
        this.idDetalleActividad = idDetalleActividad;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getCampoAfectado() {
        return campoAfectado;
    }

    public void setCampoAfectado(String campoAfectado) {
        this.campoAfectado = campoAfectado;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }
}
