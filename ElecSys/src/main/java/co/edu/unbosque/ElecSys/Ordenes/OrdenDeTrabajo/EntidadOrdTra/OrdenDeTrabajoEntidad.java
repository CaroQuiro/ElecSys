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

}
