package co.edu.unbosque.ElecSys.HistorialActividad.ServicioHis;

import co.edu.unbosque.ElecSys.HistorialActividad.DTOHis.HistorialActividadDTO;

import java.util.List;

public interface HistorialActividadInterface {

    public String agregarHistorialActividad(HistorialActividadDTO dto);

    public List<HistorialActividadDTO> listarHistorialActividad();

    public List<HistorialActividadDTO> listarHistorialActividadPorIdTrabajador(int idTrabajador);

    public HistorialActividadDTO buscarHistorialActividad(int idHistorial);
}
