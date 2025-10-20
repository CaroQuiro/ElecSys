package co.edu.unbosque.ElecSys.Cotizacion.ControladorCot;

import co.edu.unbosque.ElecSys.Cotizacion.ServicioCot.CotizacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionDTO;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionControlador {

    @Autowired
    private CotizacionServiceImpl service;

    @PostMapping("/agregar")
    public String agregarCotizacion(@RequestBody CotizacionDTO cotizacion){
        return  service.agregarCotizacion(cotizacion);
    }

    @GetMapping("/listar")
    public List<CotizacionDTO> listarCotizaciones(){
        return service.listarCotizacion();
    }

    @DeleteMapping("/borrar/{id}")
    public String borrar(@PathVariable int id){
        return service.borrarCotizacion(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @RequestBody CotizacionDTO dto){
        return service.actualizarCot(id, dto);
    }
}
