package co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra;

import co.edu.unbosque.ElecSys.Usuario.Trabajador.DTOTra.TrabajadorDTO;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.EntidadTra.TrabajadorEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorInterface{

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public String agregarTrabajador(TrabajadorDTO trabajadorDTO) {
        TrabajadorEntidad nuevoTrabajador = new TrabajadorEntidad(
          trabajadorDTO.getId_trabajador(),
          trabajadorDTO.getNombre(),
          trabajadorDTO.getTelefono(),
          trabajadorDTO.getDireccion(),
                trabajadorDTO.getCorreo(),
                trabajadorDTO.getTipo_usuario(),
                trabajadorDTO.getPassword()
        );

        try{
            trabajadorRepository.save(nuevoTrabajador);
            return "Trabajador Creado Exitosamente";
        } catch (Exception e) {
            return "Error al crear Trabajador";
        }
    }

    @Override
    public TrabajadorDTO buscarTrabajador(int id) {
        return null;
    }

    @Override
    public String borrarTrabajador(int id) {
        try {
            trabajadorRepository.deleteById(id);
            return "Trabajador Eliminado Exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<TrabajadorDTO> listarTrabajadores() {
            List<TrabajadorEntidad> trabajador = trabajadorRepository.findAll();
            List<TrabajadorDTO> trabajadorDTOS = new ArrayList<>();

            for (TrabajadorEntidad trabajadores : trabajador){
                trabajadorDTOS.add(new TrabajadorDTO(
                   trabajadores.getId_trabajador(),
                   trabajadores.getNombre(),
                   trabajadores.getTelefono(),
                   trabajadores.getDireccion(),
                   trabajadores.getCorreo(),
                   trabajadores.getTipo_usuario(),
                   trabajadores.getPassword()
                ));
            }

            return trabajadorDTOS;
    }

    @Override
    public String actualizarTrabajador(int id, TrabajadorDTO trabajadorDTO) {
        Optional<TrabajadorEntidad> trabajadorExit = trabajadorRepository.findById(id);
        if (trabajadorExit.isEmpty()){
            return "Trabajador no encontrado para actualizar";
        } else {
            TrabajadorEntidad entidad = trabajadorExit.get();

            entidad.setNombre(trabajadorDTO.getNombre());
            entidad.setTelefono(trabajadorDTO.getTelefono());
            entidad.setDireccion(trabajadorDTO.getDireccion());
            entidad.setCorreo(trabajadorDTO.getCorreo());
            entidad.setPassword(trabajadorDTO.getPassword());

            trabajadorRepository.save(entidad);
            return "Trabajador Actualizado Exitosamente";
        }
    }
}
