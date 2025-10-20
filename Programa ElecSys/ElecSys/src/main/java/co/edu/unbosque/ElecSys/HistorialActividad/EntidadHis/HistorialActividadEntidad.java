package co.edu.unbosque.ElecSys.HistorialActividad.EntidadHis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "historial_actividad")
public class HistorialActividadEntidad {

    @Id
    @Column(name = "id_historial")
    private int idHistorial;

    @Column(name = "id_trabajador", nullable = false)
    private int idTrabajador;

    @Column(name = "modulo", nullable = false)
    private String moduloSistema;

    @Column(name = "accion_realizada", nullable = false)
    private String accionRealizada;

    @Column(name = "fecha_realizacion", nullable = false)
    private Date fechaRealizacion;

    @Column(name = "hora", nullable = false)
    private Time hora;

    public HistorialActividadEntidad(int idHistorial, int idTrabajador, String moduloSistema, String accionRealizada, Date fechaRealizacion, Time hora) {
        this.idHistorial = idHistorial;
        this.idTrabajador = idTrabajador;
        this.moduloSistema = moduloSistema;
        this.accionRealizada = accionRealizada;
        this.fechaRealizacion = fechaRealizacion;
        this.hora = hora;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getModuloSistema() {
        return moduloSistema;
    }

    public void setModuloSistema(String moduloSistema) {
        this.moduloSistema = moduloSistema;
    }

    public String getAccionRealizada() {
        return accionRealizada;
    }

    public void setAccionRealizada(String accionRealizada) {
        this.accionRealizada = accionRealizada;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}