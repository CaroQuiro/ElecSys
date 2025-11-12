package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.EntidadDetOrd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
