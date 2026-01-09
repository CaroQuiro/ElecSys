package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDeTrabajoDTO {

    private int id_orden;
    private int id_orden_visita;
    private int id_lugar;
    private int id_cliente;
    private int id_trabajador;
    private Date fecha_realizacion;
    private String estado;

}
