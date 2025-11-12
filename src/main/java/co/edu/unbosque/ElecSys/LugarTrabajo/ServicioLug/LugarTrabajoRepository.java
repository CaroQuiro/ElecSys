package co.edu.unbosque.ElecSys.LugarTrabajo.ServicioLug;

import co.edu.unbosque.ElecSys.LugarTrabajo.EntidadLug.LugarTrabajoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LugarTrabajoRepository extends JpaRepository<LugarTrabajoEntidad, Integer> {
}
