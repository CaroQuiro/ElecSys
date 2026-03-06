package co.edu.unbosque.ElecSys.Contrato.ControladorCon;

import co.edu.unbosque.ElecSys.Config.Excepcion.ResourceNotFoundException;
import co.edu.unbosque.ElecSys.Contrato.ArchivoContrato.Pdf_Contrato;
import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoDTO;
import co.edu.unbosque.ElecSys.Contrato.DTOCon.ContratoRequest;
import co.edu.unbosque.ElecSys.Contrato.ServicioCon.ContratoServiceImpl;
import co.edu.unbosque.ElecSys.CuentaPorPagar.DTOCuen.CuentaPorPagarDTO;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.DTOTra.TrabajadorDTO;
import co.edu.unbosque.ElecSys.Usuario.Trabajador.ServicioTra.TrabajadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/contratos")
public class ContratoControlador {

    @Autowired
    private ContratoServiceImpl contratoService;

    @Autowired
    private TrabajadorServiceImpl trabajadorService;

    @Autowired
    private Pdf_Contrato pdfContrato;

    @PostMapping("/agregar")
    public ResponseEntity<byte[]> agregarContrato(@RequestBody ContratoRequest solicitud) throws IOException {

        try {
            if (solicitud == null) {
                throw new IllegalArgumentException("La solicitud es obligatoria");
            }

            if (solicitud.getContrato() == null) {
                throw new IllegalArgumentException("El contrato es obligatorio");
            }

            if (solicitud.getFecha_nacimiento() == null) {
                throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
            }

            if (solicitud.getEdad() < 18) {
                throw new IllegalArgumentException("El trabajador debe ser mayor de edad");
            }

            if (solicitud.getEstadoCivil() == null || solicitud.getEstadoCivil().isBlank()) {
                throw new IllegalArgumentException("El estado civil es obligatorio");
            }

            ContratoDTO dto = solicitud.getContrato();

            TrabajadorDTO trabajador =
                    trabajadorService.buscarTrabajador(dto.getId_trabajador());

            if (trabajador == null) {
                throw new IllegalArgumentException("Trabajador no encontrado");
            }

            TrabajadorDTO encargado =
                    trabajadorService.buscarTrabajador(dto.getId_trabajador_encargado());

            if (encargado == null) {
                throw new IllegalArgumentException("Encargado no encontrado");
            }

            ContratoDTO contratoGuardado = contratoService.agregarContrato(dto);

            byte[] pdf = pdfContrato.generarContrato(contratoGuardado, trabajador, encargado, solicitud);

            String nombreArchivo = pdfContrato.descargarPDF(contratoGuardado, trabajador, pdf);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error al crear contrato".getBytes());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContratoDTO>> listarContrato() {
        List<ContratoDTO> lista = contratoService.listarcontratos();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarContrato(@PathVariable int id){
        return  contratoService.borrarContato(id);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ContratoDTO> buscarContrato(@PathVariable int id) {
        ContratoDTO dto = contratoService.buscarContrato(id);

        if (dto == null) {
            throw new ResourceNotFoundException("No existe la cuenta por pagar con ID: " + id);
        }

        return ResponseEntity.ok(dto);
    }


}
