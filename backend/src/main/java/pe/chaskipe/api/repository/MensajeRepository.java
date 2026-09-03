package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
  List<Mensaje> findByConversacionIdOrderByFechaHoraAsc(Long conversacionId);
}
