package co.edu.unbosque.ElecSys.Cotizacion.DetalleCotizacion.EntidadDetCot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_cotizacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCotizacionEntidad {

    @Id
    @Column(name = "id_detalle_cotizacion")
    private int id_detalle_cotizacion;

    @Column(name = "id_cotizacion")
    private int id_cotizacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "valor_unitario")
    private BigDecimal valor_unitario;

    @Column(name = "subtotal")
    private BigDecimal subtotal;
}
