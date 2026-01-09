package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DetalleOrdenTrabajo.EntidadDetOrdTra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "detalle_orden_trabajo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenTrabajoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_trabajo")
    private int idDetalleTrabajo;

    @Column(name = "id_orden", nullable = false)
    private int idOrden;

    @Column(name = "actividad", nullable = false)
    private String actividad;

    @Column(name = "observaciones", nullable = false)
    private String observaciones;

    @Column(name = "duracion", nullable = false)
    private String duracion;


}
