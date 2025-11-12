package co.edu.unbosque.ElecSys.CuentaPorPagar.DTOCuen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaPorPagarDTO {

    private int id_cuenta_cobro;
    private int id_trabajador;
    private String descripcion;
    private Date fecha_realizacion;
    private BigDecimal monto;
    private String estado;
}
