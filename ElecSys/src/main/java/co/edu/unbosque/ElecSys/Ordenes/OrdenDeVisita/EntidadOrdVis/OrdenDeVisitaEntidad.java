package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.EntidadOrdVis;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "orden_visita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeVisitaEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private int id_visita;

    @Column(name = "id_lugar", nullable = false)
    private int id_lugar;

    @Column(name = "id_cliente", nullable = false)
    private int id_cliente;

    @Column(name = "id_trabajador", nullable = false)
    private int id_trabajador;

    @Column(name = "fecha_realizacion")
    private  Date fecha_realizacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private String estado;


}

