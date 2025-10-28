package co.edu.unbosque.ElecSys.Contrato.ControladorCon;

import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoDTO;
import co.edu.unbosque.ElecSys.Contrato.ServicioCon.ContratoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoControlador {

    @Autowired
    private ContratoServiceImpl contratoService;

    @PostMapping("/agregar")
    public String agregarContrato(@RequestBody ContratoDTO dto){
        return contratoService.agregarContrato(dto);
    }

    @GetMapping("/listar")
    public List<ContratoDTO> listarContrato(){
        return contratoService.listarcontratos();
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarContrato(@PathVariable int id){
        return  contratoService.borrarContato(id);
    }
}
