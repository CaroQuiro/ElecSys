package co.edu.unbosque.ElecSys.Usuario.Cliente.ServicioClie;

import co.edu.unbosque.ElecSys.Usuario.Cliente.EntidadClie.ClienteEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntidad, Integer> {
}
