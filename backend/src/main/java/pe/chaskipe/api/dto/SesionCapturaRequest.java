package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record SesionCapturaRequest(
    @NotNull Long conversacionId,
    @NotNull UUID usuarioId,
    @PositiveOrZero Integer duracionSegundos,
    String rutaVideo,
    @NotBlank @Pattern(regexp = "procesando|completado|error") String estadoDeteccion) {}
