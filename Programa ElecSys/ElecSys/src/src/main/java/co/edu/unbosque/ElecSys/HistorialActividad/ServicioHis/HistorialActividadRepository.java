package co.edu.unbosque.ElecSys.HistorialActividad.ServicioHis;

import co.edu.unbosque.ElecSys.HistorialActividad.EntidadHis.HistorialActividadEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialActividadRepository extends JpaRepository<HistorialActividadEntidad, Integer> {
    List<HistorialActividadEntidad> findAllByIdTrabajador(int idTrabajador);
}
