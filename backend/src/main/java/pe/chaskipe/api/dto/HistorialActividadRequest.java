package pe.chaskipe.api.dto;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record HistorialActividadRequest(
    @NotNull UUID usuarioId,
    Long conversacionId,
    String icono,
    @NotBlank String titulo,
    @NotBlank String tipo) {}
