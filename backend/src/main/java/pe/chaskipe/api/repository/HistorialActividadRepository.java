package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.HistorialActividad;

public interface HistorialActividadRepository extends JpaRepository<HistorialActividad, Long> {
  List<HistorialActividad> findByUsuarioIdOrderByFechaHoraDesc(UUID usuarioId);
}
