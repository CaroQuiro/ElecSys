package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ControladorOrdVis;

import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis.OrdenDeVisitaDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ServicioOrdVis.OrdenDeVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes-visita")
public class OrdenDeVisitaControlador {

    @Autowired
    private OrdenDeVisitaService ordenDeVisitaService;

    @GetMapping("/listar")
    public List<OrdenDeVisitaDTO> listarOrdenesDeVisita() {
        return ordenDeVisitaService.listarOrdenVisita();
    }

    @PostMapping("/agregar")
    public String agregarOrdenDeVisita(@RequestBody OrdenDeVisitaDTO dto) {
        return ordenDeVisitaService.agregarOrdenVisita(dto);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarOrdenDeVisita(@PathVariable int id) {
        return ordenDeVisitaService.borrarOrdenVisita(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarOrdenDeVisita(@PathVariable int id, @RequestBody OrdenDeVisitaDTO dto) {
        return ordenDeVisitaService.editarOrdenVisita(id, dto);
    }
}
