package co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.ControladorOrdTra;

import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.DTOOrdTra.OrdenDeTrabajoDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeTrabajo.ServicioOrdTra.OrdenDeTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-trabajo")
public class OrdenDeTrabajoControlador {

    @Autowired
    private OrdenDeTrabajoService ordenDeTrabajoService;

    @GetMapping("/listar")
    public List<OrdenDeTrabajoDTO> listarOrdenTrabajo() {
        return ordenDeTrabajoService.listarOrdenTrabajo();
    }

    @PostMapping("/agregar")
    public String agregarOrdenDeTrabajo(@RequestBody OrdenDeTrabajoDTO dto) {
        return ordenDeTrabajoService.agregarOrdenTrabajo(dto);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarOrdenTrabajo(@PathVariable int id) {
        return ordenDeTrabajoService.borrarOrdenTrabajo(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarOrdenDeTrabajo(@PathVariable int id, @RequestBody OrdenDeTrabajoDTO dto) {
        return ordenDeTrabajoService.editarOrdenTrabajo(id, dto);
    }
}

