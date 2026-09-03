package pe.chaskipe.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import pe.chaskipe.api.model.Usuario;

public record UsuarioResponse(
    UUID usuarioId,
    String nombres,
    String apellidos,
    String correo,
    String tipoUsuario,
    String fotoPerfilUrl,
    OffsetDateTime fechaRegistro,
    boolean activo) {
  public static UsuarioResponse from(Usuario u) {
    return new UsuarioResponse(
        u.getId(),
        u.getNombres(),
        u.getApellidos(),
        u.getCorreo(),
        u.getTipoUsuario(),
        u.getFotoPerfilUrl(),
        u.getFechaRegistro(),
        u.isActivo());
  }
}
