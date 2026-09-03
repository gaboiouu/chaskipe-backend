package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.GestoDetectado;

public interface GestoDetectadoRepository extends JpaRepository<GestoDetectado, Long> {
  List<GestoDetectado> findBySesionCapturaIdOrderByTimestampMsAsc(Long sesionCapturaId);
}
