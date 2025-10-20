package co.edu.unbosque.ElecSys.Contrato.ServicioCon;


import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoDTO;

import java.util.List;

public interface ContratoInterface {

    public String agregarContrato(ContratoDTO dto);

    public String borrarContrato(int idContrato);

    public List<ContratoDTO> listarContrato();

    public List<ContratoDTO> listarContratoPorCliente(int idCliente);

    public ContratoDTO buscarContrato(int idContrato);
}
