package co.edu.unbosque.ElecSys.Contrato.ServicioCon;

import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoDTO;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionDTO;

import java.util.List;

public interface ContratoInterface {
    public ContratoDTO agregarContrato(ContratoDTO contrato);
    public String borrarContato(int id);
    public List<ContratoDTO> listarcontratos();
    public ContratoDTO buscarContrato(int id);
}
