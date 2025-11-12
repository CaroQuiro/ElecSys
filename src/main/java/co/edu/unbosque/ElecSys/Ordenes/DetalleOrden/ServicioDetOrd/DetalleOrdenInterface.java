package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.ServicioDetOrd;

import co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.DTODetOrd.DetalleOrdenDTO;

import java.util.List;

public interface DetalleOrdenInterface {

    public String agregarDetalleOrden(DetalleOrdenDTO dto);

    public String editarDetalleOrden(int idOrdenAnt, DetalleOrdenDTO ordenNueva);

    public String borrarDetalleOrden(int idOrden);

    public List<DetalleOrdenDTO> listarDetalleOrdenPorIdOrden();

    public DetalleOrdenDTO buscarDetalleOrden(int idOrden);
}
