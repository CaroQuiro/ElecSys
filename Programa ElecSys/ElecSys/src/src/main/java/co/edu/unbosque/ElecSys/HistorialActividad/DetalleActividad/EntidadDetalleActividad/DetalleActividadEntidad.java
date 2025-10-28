package co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.EntidadDetalleActividad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_actividad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleActividadEntidad {

    @Id
    @Column(name = "id_detalle_actividad")
    private int idDetalleActividad;

    @Column(name = "id_historial", nullable = false)
    private int idHistorial;

    @Column(name = "campo_afectado", length = 200)
    private String campoAfectado;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_nuevo", columnDefinition = "TEXT")
    private String valorNuevo;

}
