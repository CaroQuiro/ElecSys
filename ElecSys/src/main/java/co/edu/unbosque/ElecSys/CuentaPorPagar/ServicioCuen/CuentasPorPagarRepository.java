package co.edu.unbosque.ElecSys.CuentaPorPagar.ServicioCuen;

import co.edu.unbosque.ElecSys.CuentaPorPagar.EntidadCuen.CuentaPorPagarEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentasPorPagarRepository extends JpaRepository<CuentaPorPagarEntidad, Integer> {
}
