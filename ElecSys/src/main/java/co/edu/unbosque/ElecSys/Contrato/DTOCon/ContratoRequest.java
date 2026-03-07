package co.edu.unbosque.ElecSys.Contrato.DTOCon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoRequest {

    private ContratoDTO contrato;
    private LocalDate fecha_nacimiento;
    private String lugar_nacimiento;
    private int edad;
    private String estadoCivil;

}
