package co.edu.unbosque.ElecSys.LugarTrabajo.ControladorLug;

import co.edu.unbosque.ElecSys.LugarTrabajo.DTOLug.LugarTrabajoDTO;
import co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug.LugarTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lugares-trabajo")
public class LugarTrabajoControlador {

    @Autowired
    private LugarTrabajoService lugarTrabajoService;

    @GetMapping("/listar")
    public List<LugarTrabajoDTO> listarLugaresDeTrabajo() {
        return lugarTrabajoService.listarLugar();
    }

    @PostMapping("/agregar")
    public String agregarLugarDeTrabajo(@RequestBody LugarTrabajoDTO dto) {
        return lugarTrabajoService.crearLugar(dto);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarLugarDeTrabajo(@PathVariable int id) {
        return lugarTrabajoService.borrarLugar(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarLugarDeTrabajo(@PathVariable int id, @RequestBody LugarTrabajoDTO dto) {
        return lugarTrabajoService.editarLugar(id, dto);
    }
}