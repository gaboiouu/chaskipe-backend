package pe.chaskipe.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.AudioMensaje;

public interface AudioMensajeRepository extends JpaRepository<AudioMensaje, Long> {
  Optional<AudioMensaje> findByMensajeId(Long mensajeId);

  boolean existsByMensajeId(Long mensajeId);
}
