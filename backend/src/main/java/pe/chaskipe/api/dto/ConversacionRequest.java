package pe.chaskipe.api.dto;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ConversacionRequest(
    @NotNull UUID usuarioId, String titulo, OffsetDateTime fechaFin) {}
