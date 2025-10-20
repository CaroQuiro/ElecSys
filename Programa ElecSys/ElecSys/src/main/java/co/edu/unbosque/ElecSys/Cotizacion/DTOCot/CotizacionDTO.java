package co.edu.unbosque.ElecSys.Cotizacion.DTOCot;

import co.edu.unbosque.ElecSys.Cotizacion.EntidadCot.estadoCotizacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionDTO {

    private int id_cotizacion;
    private int id_trabajador;
    private int id_cliente;
    private int id_lugar;
    private LocalDate fecha_realizada;
    private String referencia;
    private BigDecimal valor_total;
    private estadoCotizacion estado;
    private BigDecimal administracion;
    private BigDecimal imprevistos;
    private BigDecimal utilidad;


}
