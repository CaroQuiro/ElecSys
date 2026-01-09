package co.edu.unbosque.ElecSys.CuentaPorPagar.EntidadCuen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cuenta_pagar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaPorPagarEntidad {

    @Id
    @Column(name = "id_cuenta_pagar")
    private int id_cuenta_pagar;

    @Column(name = "id_trabajador")
    private int id_trabajador;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_realizacion")
    private Date fecha_realizacion;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "estado")
    private String estado;
}
