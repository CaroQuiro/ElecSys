package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DetalleOrdenVisita.EntidadDetOrdVis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_orden_visita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenVisitaEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_visita")
    private int id_detalle_visita;

    @Column(name = "id_visita", nullable = false)
    private int id_visita;

    @Column(name = "actividad", nullable = false)
    private String actividad;

    @Column(name = "observaciones", nullable = false)
    private String observaciones;

    @Column(name = "duracion", nullable = false)
    private String duracion;
}
