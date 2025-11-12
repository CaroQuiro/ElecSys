package co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.ServicioDetOrd;

import co.edu.unbosque.ElecSys.Ordenes.DetalleOrden.EntidadDetOrd.DetalleOrdenEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrdenEntidad, Integer> {
    Optional<DetalleOrdenEntidad> findByIdOrden(int idOrden);
}
