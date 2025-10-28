package co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug;

import co.edu.unbosque.ElecSys.LugarTrabajo.DTOLug.LugarTrabajoDTO;

import java.util.List;

public interface LugarTrabajoInterface {

    public String crearLugar(LugarTrabajoDTO lugar);
    public String editarLugar(int idAnterior, LugarTrabajoDTO lugar);
    public String borrarLugar(int idLugar);
    public List<LugarTrabajoDTO> listarLugar();
    public LugarTrabajoDTO buscarLugar(int idLugar);

}
