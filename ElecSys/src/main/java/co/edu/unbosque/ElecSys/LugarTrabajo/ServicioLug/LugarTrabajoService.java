package co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug;

import co.edu.unbosque.ElecSys.LugarTrabajo.DTOLug.LugarTrabajoDTO;
import co.edu.unbosque.ElecSys.LugarTrabajo.EntidadLug.LugarTrabajoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LugarTrabajoService implements LugarTrabajoInterface{

    @Autowired
    LugarTrabajoRepository lugarTrabajoRepository;


    /**
     * @param lugar
     * @return
     * private int idLugar;
     *     private String nombreLugar;
     *     private String direccion;
     */
    @Override
    public String crearLugar(LugarTrabajoDTO lugar) {
        LugarTrabajoEntidad lugarTrabajo = new LugarTrabajoEntidad(
                lugar.getIdLugar(),
                lugar.getNombreLugar(),
                lugar.getDireccion()
        );
        try {
            lugarTrabajoRepository.save(lugarTrabajo);
            return "Se creo el lugar correctamente";
        } catch (Exception e) {
            return "Hubo un error al crear el lugar";
        }

    }

    /**
     * @param idAnterior
     * @param lugar
     * @return
     */
    @Override
    public String editarLugar(int idAnterior, LugarTrabajoDTO lugar) {
        try{
            if(!lugarTrabajoRepository.findById(idAnterior).isEmpty()){
                lugarTrabajoRepository.deleteById(idAnterior);
                LugarTrabajoEntidad lugarTrabajo = new LugarTrabajoEntidad(
                        lugar.getIdLugar(),
                        lugar.getNombreLugar(),
                        lugar.getDireccion()
                );
                lugarTrabajoRepository.save(lugarTrabajo);
                return "El lugar se a editado correctamente";
            }else{
                return "La id del lugar no se a encontrado";
            }

        } catch (Exception e) {
            return "Hubo un error al editar el lugar";
        }
    }

    /**
     * @param idLugar
     * @return
     */
    @Override
    public String borrarLugar(int idLugar) {
        try {
            lugarTrabajoRepository.deleteById(idLugar);
            return "El lugar a sido borrado correctamente";
        } catch (Exception e) {
            return "Hubo un error al borrar el lugar";
        }
    }

    /**
     * @return
     */
    @Override
    public List<LugarTrabajoDTO> listarLugar() {
        try {
            List<LugarTrabajoEntidad> lugarEntidades = lugarTrabajoRepository.findAll();
            List<LugarTrabajoDTO> lugarDtos = new ArrayList<>();

            for(LugarTrabajoEntidad lugar : lugarEntidades){
                lugarDtos.add(new LugarTrabajoDTO(
                        lugar.getId_lugar(),
                        lugar.getNombre_lugar(),
                        lugar.getDireccion()));
            };
            return lugarDtos;
        } catch (Exception e) {
            return List.of();
        }
    }

    /**
     * @return
     */
    @Override
    public LugarTrabajoDTO buscarLugar(int idLugar) {
        try {
            LugarTrabajoEntidad lugar = lugarTrabajoRepository.findById(idLugar).orElse(null);
            if(lugar == null){
                return null;
            }else{
                return new LugarTrabajoDTO(
                        lugar.getId_lugar(),
                        lugar.getNombre_lugar(),
                        lugar.getDireccion()
                );
            }
        } catch (Exception e) {
            return null;
        }
    }
}
