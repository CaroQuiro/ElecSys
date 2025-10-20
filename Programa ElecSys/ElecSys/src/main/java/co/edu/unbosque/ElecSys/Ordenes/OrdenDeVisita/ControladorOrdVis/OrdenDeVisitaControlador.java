package co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ControladorOrdVis;

import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.DTOOrdVis.OrdenDeVisitaDTO;
import co.edu.unbosque.ElecSys.Ordenes.OrdenDeVisita.ServicioOrdVis.OrdenDeVisitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/ordenes-visita")
public class OrdenDeVisitaControlador {

    @Autowired
    private OrdenDeVisitaService service;

    @PostMapping
    public void crear(@RequestBody OrdenDeVisitaDTO dto) {
        service.guardar(dto);
    }
}
