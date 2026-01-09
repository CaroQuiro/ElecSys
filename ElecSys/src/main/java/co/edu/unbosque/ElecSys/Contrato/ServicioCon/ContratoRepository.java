package co.edu.unbosque.ElecSys.Contrato.ServicioCon;

import co.edu.unbosque.ElecSys.Contrato.EntidadCon.ContratoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<ContratoEntidad, Integer> {
}
