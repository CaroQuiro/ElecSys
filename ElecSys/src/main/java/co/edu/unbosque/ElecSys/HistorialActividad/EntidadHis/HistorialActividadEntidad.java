package co.edu.unbosque.ElecSys.HistorialActividad.EntidadHis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "historial_actividad")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}