package co.edu.unbosque.ElecSys.HistorialActividad.DTOHis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialActividadDTO {

    private int idHistorial;
    private int idTrabajador;
    private String moduloSistema;
    private String accionRealizada;
    private Date fechaRealizacion;
    private Time hora;

}
