package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeTrabajoDTO {

    private int idOrden;
    private int idOrdenVisita;
    private int idLugar;
    private int idCliente;
    private int idTrabajador;
    private Date fechaRealizacion;
    private String estado;

}
