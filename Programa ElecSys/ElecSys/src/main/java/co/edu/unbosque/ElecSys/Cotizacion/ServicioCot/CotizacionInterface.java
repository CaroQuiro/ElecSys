package co.edu.unbosque.ElecSys.Cotizacion.ServicioCot;

import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionDTO;

import java.util.List;

public interface CotizacionInterface {
    public String agregarCotizacion(CotizacionDTO cotizacion);
    //public CotizacionDTO buscarCotizacion(int id);
    public String borrarCotizacion(int id);
    public List<CotizacionDTO> listarCotizacion();
    public String actualizarCot(int id, CotizacionDTO cotizacion);
}
