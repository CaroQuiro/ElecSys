package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.DTODetOrd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenDTO {

    private int idDetalleOrden;
    private int idOrden;
    private String actividad;
    private String observaciones;
    private String duracion;

}
