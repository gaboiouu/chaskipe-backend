package pe.chaskipe.api.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.chaskipe.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
  boolean existsByCorreo(String correo);
}
