package co.edu.unbosque.ElecSys.CuentaPorPagar.ControladorCuen;

import co.edu.unbosque.ElecSys.CuentaPorPagar.DTOCuen.CuentaPorPagarDTO;
import co.edu.unbosque.ElecSys.CuentaPorPagar.ServicioCuen.CuentaPorPagarImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas_pagar")
public class CuentaPorPagarControlador {

    @Autowired
    private CuentaPorPagarImplService cuentaPorPagarService;

    @PostMapping("/agregar")
    public String agregarCuenta(@RequestBody CuentaPorPagarDTO cuentaPorPagarDTO){
        return cuentaPorPagarService.agregarCuentaPagar(cuentaPorPagarDTO);
    }

    @GetMapping("/listar")
    public List<CuentaPorPagarDTO> listarCuentas(){
        return cuentaPorPagarService.listarCuentasPagar();
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarCuenta(@PathVariable int id){
        return cuentaPorPagarService.borrarCuentaPagar(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarCuenta(@PathVariable int id, @RequestBody CuentaPorPagarDTO dto){
        return cuentaPorPagarService.actualizarCuenta(id, dto);
    }
}
