package co.edu.unbosque.ElecSys.HistorialActividad.DTOHis;

import java.sql.Date;
import java.sql.Time;

public class HistorialActividadDTO {

    private int idHistorial;
    private int idTrabajador;
    private String moduloSistema;
    private String accionRealizada;
    private Date fechaRealizacion;
    private Time hora;

    public HistorialActividadDTO(int idHistorial, int idTrabajador, String moduloSistema, String accionRealizada, Date fechaRealizacion, Time hora) {
        this.idHistorial = idHistorial;
        this.idTrabajador = idTrabajador;
        this.moduloSistema = moduloSistema;
        this.accionRealizada = accionRealizada;
        this.fechaRealizacion = fechaRealizacion;
        this.hora = hora;
    }

    public HistorialActividadDTO() {

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
