package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.EntidadOrdVis;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orden_visita")
public class OrdenDeVisitaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private int idVisita;

    @Column(name = "id_lugar", nullable = false)
    private int idLugar;

    @Column(name = "id_cliente", nullable = false)
    private int idCliente;

    @Column(name = "id_trabajador", nullable = false)
    private int idTrabajador;

    @Column(name = "fecha_realizacion")
    private  Date fechaRealizacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;

    public OrdenDeVisitaEntidad(int idVisita, int idLugar, int idCliente, int idTrabajador, Date fechaRealizacion, String descripcion, String estado){
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

