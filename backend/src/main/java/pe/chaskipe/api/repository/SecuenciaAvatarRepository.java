package pe.chaskipe.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.SecuenciaSenasAvatar;

public interface SecuenciaAvatarRepository extends JpaRepository<SecuenciaSenasAvatar, Long> {
  Optional<SecuenciaSenasAvatar> findByMensajeId(Long mensajeId);

  boolean existsByMensajeId(Long mensajeId);
}
