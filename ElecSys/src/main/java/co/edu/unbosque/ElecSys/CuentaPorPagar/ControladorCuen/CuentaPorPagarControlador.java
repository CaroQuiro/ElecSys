package co.edu.unbosque.ElecSys.CuentaPorPagar.ControladorCuen;

import co.edu.unbosque.ElecSys.CuentaPorPagar.DTOCuen.CuentaPorPagarDTO;
import co.edu.unbosque.ElecSys.CuentaPorPagar.ServicioCuen.CuentaPorPagarImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ElecSys.Config.Excepcion.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas_pagar")
public class CuentaPorPagarControlador {

    @Autowired
    private CuentaPorPagarImplService cuentaPorPagarService;

    private static final List<String> ESTADOS_VALIDOS =
            List.of("PAGADO", "PENDIENTE", "EN_PROCESO");

    // ----------------------------------------------------
    // AGREGAR
    // ----------------------------------------------------
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarCuenta(@RequestBody CuentaPorPagarDTO dto) {

        // Validación de ID duplicado (PK)
        if (cuentaPorPagarService.existeCuenta(dto.getId_cuenta_pagar())) {
            throw new DuplicateResourceException("Ya existe una cuenta con el ID: " + dto.getId_cuenta_pagar());
        }

        // Validar campos obligatorios
        if (dto.getId_trabajador() <= 0) {
            throw new InvalidFieldException("El id_trabajador es obligatorio y debe ser mayor que cero.");
        }

        if (dto.getMonto() == null || dto.getMonto().doubleValue() <= 0) {
            throw new InvalidFieldException("El monto debe ser mayor que 0.");
        }

        // Validar estado
        if (dto.getEstado() == null || !ESTADOS_VALIDOS.contains(dto.getEstado())) {
            throw new InvalidFieldException("Estado inválido. Debe ser: " + ESTADOS_VALIDOS);
        }

        String mensaje = cuentaPorPagarService.agregarCuentaPagar(dto);
        return ResponseEntity.ok(mensaje);
    }

    // ----------------------------------------------------
    // LISTAR
    // ----------------------------------------------------
    @GetMapping("/listar")
    public ResponseEntity<List<CuentaPorPagarDTO>> listarCuentas() {
        List<CuentaPorPagarDTO> lista = cuentaPorPagarService.listarCuentasPagar();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay cuentas por pagar registradas.");
        }

        return ResponseEntity.ok(lista);
    }

    // ----------------------------------------------------
    // BORRAR
    // ----------------------------------------------------
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarCuenta(@PathVariable int id) {

        if (!cuentaPorPagarService.existeCuenta(id)) {
            throw new ResourceNotFoundException("No existe la cuenta por pagar con ID: " + id);
        }

        String mensaje = cuentaPorPagarService.borrarCuentaPagar(id);
        return ResponseEntity.ok(mensaje);
    }

    // ----------------------------------------------------
    // ACTUALIZAR
    // ----------------------------------------------------
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCuenta(
            @PathVariable int id,
            @RequestBody CuentaPorPagarDTO dto) {

        if (!cuentaPorPagarService.existeCuenta(id)) {
            throw new ResourceNotFoundException("No existe la cuenta por pagar con ID: " + id);
        }

        // Validaciones
        if (dto.getId_cuenta_pagar() != id) {
            throw new InvalidFieldException("El id_cuenta_pagar no se puede modificar.");
        }

        if (dto.getMonto() == null || dto.getMonto().doubleValue() <= 0) {
            throw new InvalidFieldException("El monto debe ser mayor que 0.");
        }

        if (dto.getEstado() == null || !ESTADOS_VALIDOS.contains(dto.getEstado())) {
            throw new InvalidFieldException("Estado inválido. Debe ser: " + ESTADOS_VALIDOS);
        }

        String mensaje = cuentaPorPagarService.actualizarCuenta(id, dto);
        return ResponseEntity.ok(mensaje);
    }

    // ----------------------------------------------------
    // BUSCAR POR ID
    // ----------------------------------------------------
        @GetMapping("/buscar/{id}")
        public ResponseEntity<CuentaPorPagarDTO> buscarCuenta(@PathVariable int id) {

            CuentaPorPagarDTO dto = cuentaPorPagarService.buscarCuenta(id);

            if (dto == null) {
                throw new ResourceNotFoundException("No existe la cuenta por pagar con ID: " + id);
            }

            return ResponseEntity.ok(dto);
        }

}
