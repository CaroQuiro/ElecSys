package co.edu.unbosque.ElecSys.LugarTrabajo.EntidadLug;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lugar")
public class LugarTrabajoEntidad {

    @Id
    @Column(name = "id_lugar")
    private int id_lugar;

    @Column(name = "nombre_lugar")
    private String nombre_lugar;

    @Column(name = "direccion")
    private String direccion;

    public LugarTrabajoEntidad(int id_lugar, String nombre_lugar, String direccion) {
        this.id_lugar = id_lugar;
        this.nombre_lugar = nombre_lugar;
        this.direccion = direccion;
    }

    public LugarTrabajoEntidad() {
    }

    public int getId_lugar() {
        return id_lugar;
    }

    public void setId_lugar(int id_lugar) {
        this.id_lugar = id_lugar;
    }

    public String getNombre_lugar() {
        return nombre_lugar;
    }

    public void setNombre_lugar(String nombre_lugar) {
        this.nombre_lugar = nombre_lugar;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
