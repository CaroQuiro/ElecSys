package co.edu.unbosque.ElecSys.Cotizacion.ControladorCot;

import co.edu.unbosque.ElecSys.Cotizacion.Archivo.Pdf_Cotizacion;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionRequest;
import co.edu.unbosque.ElecSys.Cotizacion.DetalleCotizacion.DTODetCot.DetalleCotizacionDTO;
import co.edu.unbosque.ElecSys.Cotizacion.DetalleCotizacion.ServicioDetCot.DetalleCotizacionService;
import co.edu.unbosque.ElecSys.Cotizacion.ServicioCot.CotizacionServiceImpl;
import co.edu.unbosque.ElecSys.LugarTrabajo.DTOLug.LugarTrabajoDTO;
import co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug.LugarTrabajoService;
import co.edu.unbosque.ElecSys.Usuario.Cliente.DTOClie.ClienteDTO;
import co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.ElecSys.Cotizacion.DTOCot.CotizacionDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionControlador {

    @Autowired
    private CotizacionServiceImpl service;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private DetalleCotizacionService detalleCotizacionService;

    @Autowired
    private LugarTrabajoService lugarTrabajoService;

    @Autowired
    private Pdf_Cotizacion pdfCotizacion;

    @PostMapping("/agregar")
    public ResponseEntity<byte[]> agregarCotizacion(@RequestBody CotizacionRequest solicitud) throws IOException {

        CotizacionDTO cotizacion = solicitud.getCotizacion();
        List<DetalleCotizacionDTO> listaDetalle = solicitud.getDetalleCotizacionDTOS();

        if (cotizacion == null || cotizacion.getId_cliente() <= 0 || cotizacion.getId_lugar() <= 0) {
            System.out.println("No se encontro ni cliente ni lugar asignados para esta cotizacion");
            return ResponseEntity.badRequest().body(null);
        }
        if (service.existirCot(cotizacion.getId_cotizacion())) {
            System.out.println("Ya existe una cotizacion con esa misma ID");
            return ResponseEntity.badRequest().body(null);
        }

        ClienteDTO cliente = clienteService.buscarCliente(cotizacion.getId_cliente());
        LugarTrabajoDTO lugar = lugarTrabajoService.buscarLugar(cotizacion.getId_lugar());

        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal subtotal = BigDecimal.ZERO;


        for (DetalleCotizacionDTO d : listaDetalle) {
            BigDecimal cantidad = new BigDecimal(d.getCantidad());
            BigDecimal valorUnitario = d.getValor_unitario();

            subtotal = cantidad.multiply(valorUnitario);

            if (subtotal.compareTo(BigDecimal.ZERO) > 0) {
                d.setSubtotal(subtotal);
                valorTotal = valorTotal.add(subtotal);
            } else {
                System.out.println("Error al guardar los detalles");
            }
        }

        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Error al crear la cotizacion: valor total no calculado");
            return ResponseEntity.badRequest().body(null);
        }

        CotizacionDTO cotizacionFull = null;
        BigDecimal administracion = BigDecimal.ZERO;
        BigDecimal imprevistos = BigDecimal.ZERO;
        BigDecimal utilidades  = BigDecimal.ZERO;
        BigDecimal Iva = BigDecimal.ZERO;
        BigDecimal total_pagar = BigDecimal.ZERO;

        if (Boolean.TRUE.equals(solicitud.getExistIva())){
            Iva = valorTotal.multiply(new BigDecimal("0.19"));
            total_pagar = valorTotal.add(Iva);

            cotizacionFull = new CotizacionDTO(
                    cotizacion.getId_cotizacion(),
                    cotizacion.getId_trabajador(),
                    cotizacion.getId_cliente(),
                    cotizacion.getId_lugar(),
                    cotizacion.getFecha_realizacion(),
                    cotizacion.getReferencia(),
                    valorTotal,
                    cotizacion.getEstado(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    Iva,
                    total_pagar
            );

            //service.agregarCotizacion(cotizacionFull);
            System.out.println("Cotizacion Guardada con Iva Directo");

        }else{
            administracion = valorTotal.multiply(new BigDecimal("0.07"));
            imprevistos = valorTotal.multiply(new BigDecimal("0.05"));
            utilidades = valorTotal.multiply(new BigDecimal("0.04"));

            Iva = utilidades.multiply(new BigDecimal("0.19"));

            total_pagar = valorTotal.add(administracion).add(imprevistos).add(utilidades).add(Iva);

            cotizacionFull = new CotizacionDTO(
                    cotizacion.getId_cotizacion(),
                    cotizacion.getId_trabajador(),
                    cotizacion.getId_cliente(),
                    cotizacion.getId_lugar(),
                    cotizacion.getFecha_realizacion(),
                    cotizacion.getReferencia(),
                    valorTotal,
                    cotizacion.getEstado(),
                    administracion,
                    imprevistos,
                    utilidades,
                    Iva,
                    total_pagar
            );

            //service.agregarCotizacion(cotizacionFull);
            System.out.println("Cotizacion Guardada con AIU");
        }

//        for (DetalleCotizacionDTO d : listaDetalle){
//            d.setId_cotizacion(cotizacionFull.getId_cotizacion());
//            detalleCotizacionService.agregarDetalleCot(d);
//            System.out.println("DetalleGuardao");
//        }


        byte[] pdf = pdfCotizacion.generarArchivo(cotizacionFull, cliente, lugar, listaDetalle);
        String nombreArchivo = pdfCotizacion.descargarPDF(cotizacionFull, pdf);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nombreArchivo)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
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
