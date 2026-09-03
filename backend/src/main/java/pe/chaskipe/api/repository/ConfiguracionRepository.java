package pe.chaskipe.api.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.ConfiguracionUsuario;

public interface ConfiguracionRepository extends JpaRepository<ConfiguracionUsuario, Long> {
  Optional<ConfiguracionUsuario> findByUsuarioId(UUID usuarioId);

  boolean existsByUsuarioId(UUID usuarioId);
}
