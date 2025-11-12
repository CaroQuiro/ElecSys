package co.edu.unbosque.ElecSys.Usuario.Trabajador.ControladorTra;

import co.edu.unbosque.ElecSys.Usuario.Trabajador.DTOTra.TrabajadorDTO;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra.TrabajadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajador")
public class TrabajadorControlador {

    @Autowired
    private TrabajadorServiceImpl trabajadorService;

    @GetMapping("/listar")
    public List<TrabajadorDTO> listarTrabajadores(){
        return trabajadorService.listarTrabajadores();
    }

    @PostMapping("/agregar")
    public String agregarTrabajador(@RequestBody TrabajadorDTO dto){
        return trabajadorService.agregarTrabajador(dto);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarTrabajador(@PathVariable int id){
        return trabajadorService.borrarTrabajador(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarTrabajador(@PathVariable int id, @RequestBody TrabajadorDTO dto){
        return trabajadorService.actualizarTrabajador(id, dto);
    }
}
