package co.edu.unbosque.ElecSys.Cotizacion.ServicioCot;

import co.edu.unbosque.ElecSys.Cotizacion.EntidadCot.CotizacionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<CotizacionEntidad, Integer> {
}
