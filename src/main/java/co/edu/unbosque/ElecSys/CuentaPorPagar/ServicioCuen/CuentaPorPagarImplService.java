package co.edu.unbosque.ElecSys.CuentaPorPagar.ServicioCuen;

import co.edu.unbosque.ElecSys.CuentaPorPagar.DTOCuen.CuentaPorPagarDTO;
import co.edu.unbosque.ElecSys.CuentaPorPagar.EntidadCuen.CuentaPorPagarEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaPorPagarImplService implements CuentaPorPagarInterface{

    @Autowired
    private CuentasPorPagarRepository cuentasPorPagarRepository;

    @Override
    public String agregarCuentaPagar(CuentaPorPagarDTO cuenta) {
        CuentaPorPagarEntidad nuevaCuenta = new CuentaPorPagarEntidad(
          cuenta.getId_cuenta_cobro(),
          cuenta.getId_trabajador(),
          cuenta.getDescripcion(),
          cuenta.getFecha_realizacion(),
          cuenta.getMonto(),
          cuenta.getEstado()
        );
        try{
            cuentasPorPagarRepository.save(nuevaCuenta);
            return "Cuenta Creada Correctamente";
        } catch (Exception e) {
            return "Error al crear Cuenta " + e.getMessage();
        }
    }

    @Override
    public String borrarCuentaPagar(int id) {
        try {
            cuentasPorPagarRepository.deleteById(id);
            return "Cuenta Eliminada Exitosamente";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<CuentaPorPagarDTO> listarCuentasPagar() {
        List<CuentaPorPagarEntidad> cuenta = cuentasPorPagarRepository.findAll();
        List<CuentaPorPagarDTO> cuentaPorPagarDTOS = new ArrayList<>();

        for (CuentaPorPagarEntidad cuentas : cuenta) {
            cuentaPorPagarDTOS.add(new CuentaPorPagarDTO(
                    cuentas.getId_cuenta_cobro(),
                    cuentas.getId_trabajador(),
                    cuentas.getDescripcion(),
                    cuentas.getFecha_realizacion(),
                    cuentas.getMonto(),
                    cuentas.getEstado()
            ));
        }

        return cuentaPorPagarDTOS;
    }

    @Override
    public String actualizarCuenta(int id, CuentaPorPagarDTO cuentaPorPagarDTO) {

        Optional<CuentaPorPagarEntidad> cuentaExit = cuentasPorPagarRepository.findById(id);
        if (cuentaExit.isEmpty()){
            return "Cuenta No encontrada para actualizar";
        }else {
            CuentaPorPagarEntidad entidad = cuentaExit.get();

            entidad.setId_trabajador(cuentaPorPagarDTO.getId_trabajador());
            entidad.setDescripcion(cuentaPorPagarDTO.getDescripcion());
            entidad.setFecha_realizacion(cuentaPorPagarDTO.getFecha_realizacion());
            entidad.setMonto(cuentaPorPagarDTO.getMonto());
            entidad.setEstado(cuentaPorPagarDTO.getEstado());

            cuentasPorPagarRepository.save(entidad);
            return "Cotizacion Actualizada Correctamente";
        }
    }
}
