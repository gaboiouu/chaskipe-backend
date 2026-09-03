package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.Conversacion;

public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {
  List<Conversacion> findByUsuarioIdOrderByFechaInicioDesc(UUID usuarioId);
}
