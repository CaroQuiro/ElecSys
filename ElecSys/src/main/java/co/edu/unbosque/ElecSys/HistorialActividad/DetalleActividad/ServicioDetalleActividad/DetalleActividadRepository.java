package co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.ServicioDetalleActividad;

import co.edu.unbosque.ElecSys.HistorialActividad.DetalleActividad.EntidadDetalleActividad.DetalleActividadEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleActividadRepository extends JpaRepository<DetalleActividadEntidad, Integer> {
    List<DetalleActividadEntidad> findAllByIdHistorial(int idHistorial);
}
