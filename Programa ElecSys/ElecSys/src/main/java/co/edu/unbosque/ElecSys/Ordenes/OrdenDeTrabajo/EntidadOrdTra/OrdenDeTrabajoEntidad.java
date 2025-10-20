package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.EntidadOrdTra;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orden_trabajo")
public class OrdenDeTrabajoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private int idOrden;

    @Column(name = "id_orden_visita", nullable = false)
    private int idOrdenVisita;

    @Column(name = "id_lugar", nullable = false)
    private int idLugar;

    @Column(name = "id_cliente", nullable = false)
    private int idCliente;

    @Column(name = "id_trabajador", nullable = false)
    private int idTrabajador;

    @Column(name = "fecha_realizacion")
    private Date fechaRealizacion;

    @Column(name = "estado")
    private String estado;

    public OrdenDeTrabajoEntidad(int idOrden, int idOrdenVisita, int idLugar, int idCliente, int idTrabajador, Date fechaRealizacion, String estado) {
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

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
