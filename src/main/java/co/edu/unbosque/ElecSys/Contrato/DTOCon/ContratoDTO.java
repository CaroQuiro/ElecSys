package co.edu.unbosque.ElecSys.Contrato.DTOCon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {

    private int id_contrato;
    private int id_trabajador;
    private BigDecimal sueldo;
    private Date fecha_expedicion;
    private Date fecha_iniciacion;
    private int id_trabajador_encargado;
}
