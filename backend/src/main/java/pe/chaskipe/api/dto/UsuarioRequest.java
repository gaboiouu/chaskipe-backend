package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record UsuarioRequest(
    @NotNull UUID usuarioId,
    @NotBlank String nombres,
    @NotBlank String apellidos,
    @NotBlank @Email String correo,
    @NotBlank @Pattern(regexp = "sordo_mudo|oyente|ambos") String tipoUsuario,
    String fotoPerfilUrl,
    Boolean activo) {}
