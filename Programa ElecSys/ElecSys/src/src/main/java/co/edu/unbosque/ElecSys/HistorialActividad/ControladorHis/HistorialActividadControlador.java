package co.edu.unbosque.ElecSys.HistorialActividad.ControladorHis;

import co.edu.unbosque.ElecSys.HistorialActividad.DTOHis.HistorialActividadDTO;
import co.edu.unbosque.ElecSys.HistorialActividad.ServicioHis.HistorialActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-actividad")
public class HistorialActividadControlador {

    @Autowired
    private HistorialActividadService historialActividadService;

    @GetMapping("/listar")
    public List<HistorialActividadDTO> listarHistorialActividad() {
        return historialActividadService.listarHistorialActividad();
    }

    @PostMapping("/agregar")
    public String agregarHistorialActividad(@RequestBody HistorialActividadDTO dto) {
        return historialActividadService.agregarHistorialActividad(dto);
    }

}
