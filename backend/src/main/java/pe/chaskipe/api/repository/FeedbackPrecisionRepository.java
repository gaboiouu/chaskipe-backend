package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.FeedbackPrecision;

public interface FeedbackPrecisionRepository extends JpaRepository<FeedbackPrecision, Long> {
  List<FeedbackPrecision> findByMensajeId(Long mensajeId);

  List<FeedbackPrecision> findByUsuarioIdOrderByFechaHoraDesc(UUID usuarioId);
}
