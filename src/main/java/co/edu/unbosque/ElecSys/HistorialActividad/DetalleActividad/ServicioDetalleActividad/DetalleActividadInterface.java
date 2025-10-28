package co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.ServicioDetalleActividad;


import co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.DTODetalleActividad.DetalleActividadDTO;

import java.util.List;

public interface DetalleActividadInterface {

    public String agregarDetalleActividad(DetalleActividadDTO dto);

    public List<DetalleActividadDTO> listarDetalleActividadPorIdHistorial(int idHistorial);

    public DetalleActividadDTO buscarDetalleActividad(int idDetalleActividad);
}
