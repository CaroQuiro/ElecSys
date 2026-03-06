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
    public ContratoDTO agregarContrato(ContratoDTO contrato) {
        ContratoEntidad nuevocontrato = new ContratoEntidad(
                null,
                contrato.getId_trabajador(),
                contrato.getSueldo(),
                contrato.getFecha_expedicion(),
                contrato.getFecha_iniciacion(),
                contrato.getId_trabajador_encargado(),
                contrato.getCargo(),
                contrato.getTipo_contrato(),
                contrato.getEstado()
        );
        try {
            ContratoEntidad contratoGuardado = contratoRepository.save(nuevocontrato);
            contrato.setId_contrato(contratoGuardado.getId_contrato());
            System.out.println("Contrato Guardado exitosamente");
            return contrato;

        }catch (Exception e){
            System.out.println("Error al crear el contrato");
            return null;
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
                    contratos.getId_trabajador_encargado(),
                    contratos.getCargo(),
                    contratos.getTipo_contrato(),
                    contratos.getEstado()
            ));
        }
        return contratoDTOS;
    }

    @Override
    public ContratoDTO buscarContrato(int id) {
        Optional<ContratoEntidad> contratoopt = contratoRepository.findById(id);

        if (contratoopt.isEmpty()){
            return null;
        }
        ContratoEntidad c = contratoopt.get();

        return new ContratoDTO(c.getId_contrato(),
                c.getId_trabajador(),
                c.getSueldo(),
                c.getFecha_expedicion(),
                c.getFecha_iniciacion(),
                c.getId_trabajador_encargado(),
                c.getCargo(),
                c.getTipo_contrato(),
                c.getEstado());
    }

}
