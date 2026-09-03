package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.SesionCaptura;

public interface SesionCapturaRepository extends JpaRepository<SesionCaptura, Long> {
  List<SesionCaptura> findByConversacionIdOrderByFechaHoraAsc(Long conversacionId);
}
