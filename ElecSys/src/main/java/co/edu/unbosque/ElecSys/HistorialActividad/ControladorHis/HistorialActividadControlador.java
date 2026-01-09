package co.edu.unbosque.ElecSys.HistorialActividad.ControladorHis;

import co.edu.unbosque.ElecSys.Config.Excepcion.*;
import co.edu.unbosque.ElecSys.HistorialActividad.DTOHis.HistorialActividadDTO;
import co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.DTODetalleActividad.DetalleActividadDTO;
import co.edu.unbosque.ElecSys.HistorialActividad.ServicioHis.HistorialActividadService;
import co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.ServicioDetalleActividad.DetalleActividadService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/internal/historial-actividad")
@CrossOrigin(origins = "http://localhost:8080")  // Solo back puede llamar
public class HistorialActividadControlador {

    @Autowired
    private HistorialActividadService historialService;

    @Autowired
    private DetalleActividadService detalleService;


    // ===================================================
    // DTO para crear historial + detalles
    // ===================================================
    @Data
    public static class HistorialConDetallesDTO {
        private HistorialActividadDTO historial;
        private List<DetalleActividadDTO> detalles;
    }


    // ===================================================
    // 1. AGREGAR HISTORIAL + DETALLES
    // ===================================================
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarHistorialConDetalles(
            @RequestBody HistorialConDetallesDTO dto) {

        HistorialActividadDTO historial = dto.getHistorial();
        List<DetalleActividadDTO> detalles = dto.getDetalles();

        // ===== VALIDACIONES GENERALES =====

        if (historial == null) {
            throw new InvalidFieldException("Debe enviar los datos del historial.");
        }

        if (historialService.buscarHistorialActividad(historial.getIdHistorial()) != null) {
            throw new DuplicateResourceException("Ya existe un historial con el ID: " + historial.getIdHistorial());
        }

        if (historial.getIdTrabajador() <= 0) {
            throw new InvalidFieldException("El idTrabajador es obligatorio.");
        }

        if (historial.getAccionRealizada() == null || historial.getAccionRealizada().isBlank()) {
            throw new InvalidFieldException("La acción realizada es obligatoria.");
        }

        // ===== VALIDACIÓN: Solo se permite 1 detalle =====
        if (detalles != null && detalles.size() > 1) {
            throw new InvalidFieldException("Solo se permite registrar un (1) detalle por historial.");
        }

        // ===== VALIDACIONES DEL DETALLE =====
        if (detalles != null && !detalles.isEmpty()) {

            DetalleActividadDTO det = detalles.get(0);

            if (det.getIdHistorial() != historial.getIdHistorial()) {
                throw new InvalidFieldException("El idHistorial del detalle debe coincidir con el id del historial.");
            }

            if (detalleService.buscarDetalleActividad(det.getIdDetalleActividad()) != null) {
                throw new DuplicateResourceException("Ya existe un detalle con el ID: " + det.getIdDetalleActividad());
            }
        }

        // ===== GUARDAR HISTORIAL =====
        String mensajeHistorial = historialService.agregarHistorialActividad(historial);

        // ===== GUARDAR DETALLE =====
        if (detalles != null && !detalles.isEmpty()) {
            detalleService.agregarDetalleActividad(detalles.get(0));
        }

        return ResponseEntity.ok("Historial y detalle creado correctamente.");
    }




    // ===================================================
    // 2. LISTAR TODO EL HISTORIAL
    // ===================================================
    @GetMapping("/listar")
    public ResponseEntity<List<HistorialActividadDTO>> listarHistorialActividad() {
        List<HistorialActividadDTO> lista = historialService.listarHistorialActividad();

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros de historial.");
        }

        return ResponseEntity.ok(lista);
    }


    // ===================================================
    // 3. BUSCAR HISTORIAL POR ID
    // ===================================================
    @GetMapping("/buscar/{id}")
    public ResponseEntity<HistorialActividadDTO> buscarPorId(@PathVariable int id) {

        HistorialActividadDTO historial = historialService.buscarHistorialActividad(id);

        if (historial == null) {
            throw new ResourceNotFoundException("No existe historial con ID: " + id);
        }

        return ResponseEntity.ok(historial);
    }


    // ===================================================
    // 4. BUSCAR HISTORIAL POR TRABAJADOR
    // ===================================================
    @GetMapping("/buscar/trabajador/{idTrabajador}")
    public ResponseEntity<List<HistorialActividadDTO>> buscarPorTrabajador(
            @PathVariable int idTrabajador) {

        List<HistorialActividadDTO> lista = historialService.listarHistorialActividadPorIdTrabajador(idTrabajador);

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No hay historial de actividades para el trabajador " + idTrabajador
            );
        }

        return ResponseEntity.ok(lista);
    }


    // ===================================================
    // 5. BUSCAR HISTORIAL POR FECHA
    // ===================================================
    @GetMapping("/buscar/fecha/{fecha}")
    public ResponseEntity<List<HistorialActividadDTO>> buscarPorFecha(
            @PathVariable Date fecha) {

        List<HistorialActividadDTO> lista = historialService.listarHistorialActividadPorFecha(fecha);

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay registros en la fecha: " + fecha);
        }

        return ResponseEntity.ok(lista);
    }


    // ===================================================
    // 6. BUSCAR HISTORIAL POR ACCIÓN REALIZADA
    // ===================================================
    @GetMapping("/buscar/accion/{accion}")
    public ResponseEntity<List<HistorialActividadDTO>> buscarPorAccion(
            @PathVariable String accion) {

        List<HistorialActividadDTO> lista = historialService.listarHistorialActividadPorAccion(accion);

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay historial con la acción: " + accion);
        }

        return ResponseEntity.ok(lista);
    }


    // ===================================================
    // 7. LISTAR DETALLES POR ID HISTORIAL
    // ===================================================
    @GetMapping("/detalle/listar/{idHistorial}")
    public ResponseEntity<List<DetalleActividadDTO>> listarDetallePorHistorial(
            @PathVariable int idHistorial) {

        List<DetalleActividadDTO> lista = detalleService.listarDetalleActividadPorIdHistorial(idHistorial);

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No hay detalles para el historial con ID: " + idHistorial
            );
        }

        return ResponseEntity.ok(lista);
    }


    // ===================================================
    // 8. BUSCAR DETALLE POR ID DETALLE
    // ===================================================
    @GetMapping("/detalle/buscar/{idDetalle}")
    public ResponseEntity<DetalleActividadDTO> buscarDetallePorId(
            @PathVariable int idDetalle) {

        DetalleActividadDTO detalle = detalleService.buscarDetalleActividad(idDetalle);

        if (detalle == null) {
            throw new ResourceNotFoundException("No existe detalle con ID: " + idDetalle);
        }

        return ResponseEntity.ok(detalle);
    }
}


