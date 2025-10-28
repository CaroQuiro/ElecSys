package co.edu.unbosque.ElecSys.Contrato.ServicioCon;

import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoDTO;
import co.edu.unbosque.ElecSys.Contrato.EntidadCon.ContratoEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoInterface{

    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public String agregarContrato(ContratoDTO contrato) {
        ContratoEntidad nuevocontrato = new ContratoEntidad(
                contrato.getId_contrato(),
                contrato.getId_trabajador(),
                contrato.getSueldo(),
                contrato.getFecha_expedicion(),
                contrato.getFecha_iniciacion(),
                contrato.getId_trabajador_encargado()
        );

        try {
            contratoRepository.save(nuevocontrato);
            return "Contrato Guardado exitosamente";
        }catch (Exception e){
            return "Error al crear el contrato";
        }
    }

    @Override
    public String borrarContato(int id) {
        try {
            contratoRepository.deleteById(id);
            return "Contrato Eliminado";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<ContratoDTO> listarcontratos() {
        List<ContratoEntidad> contrato = contratoRepository.findAll();
        List<ContratoDTO> contratoDTOS = new ArrayList<>();

        for (ContratoEntidad contratos : contrato){
            contratoDTOS.add(new ContratoDTO(
               contratos.getId_contrato(),
               contratos.getId_trabajador(),
                    contratos.getSueldo(),
                    contratos.getFecha_expedicion(),
                    contratos.getFecha_iniciacion(),
                    contratos.getId_trabajador_encargado()
            ));
        }

        return contratoDTOS;
    }

    @Override
    public String actualizarContrato(int id, ContratoDTO contratodto) {
        Optional<ContratoEntidad> contratoExis = contratoRepository.findById(id);
        if (contratoExis.isEmpty()){
            return "Contrato no encontrato para actualizar";
        }else {
            ContratoEntidad entidad = contratoExis.get();

            entidad.setId_trabajador(contratodto.getId_trabajador());
            entidad.setSueldo(contratodto.getSueldo());
            entidad.setFecha_expedicion(contratodto.getFecha_expedicion());
            entidad.setFecha_iniciacion(contratodto.getFecha_iniciacion());
            entidad.setId_trabajador_encargado(contratodto.getId_trabajador_encargado());

            contratoRepository.save(entidad);
            return "Contrato Actualizado Correctamente";
        }
    }
}
