package co.edu.unbosque.ElecSys.Contrato.ServicioCon;

import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoDTO;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionDTO;

import java.util.List;

public interface ContratoInterface {
    public String agregarContrato(ContratoDTO contrato);
    //public CotizacionDTO buscarCotizacion(int id);
    public String borrarContato(int id);
    public List<ContratoDTO> listarcontratos();
    public String actualizarContrato(int id, ContratoDTO contratodto);
}
