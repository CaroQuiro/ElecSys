package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.EntidadOrdTra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "orden_trabajo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeTrabajoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private int id_orden;

    @Column(name = "id_orden_visita")
    private int id_orden_visita;

    @Column(name = "id_lugar", nullable = false)
    private int id_lugar;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;

    @Column(name = "id_trabajador", nullable = false)
    private int id_trabajador;

    @Column(name = "fecha_realizacion")
    private Date fecha_realizacion;

    @Column(name = "estado")
    private String estado;

}
