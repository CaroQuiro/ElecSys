package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DetalleOrdenTrabajo.DTODetOrdTra;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenTrabajoDTO {

    private int idDetalleTrabajo;
    private int idOrden;
    private String actividad;
    private String observaciones;
    private String duracion;
}
