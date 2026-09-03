package pe.chaskipe.api.dto;

import pe.chaskipe.api.model.FraseFrecuente;

public record FraseFrecuenteResponse(
    Long fraseFrecuenteId, String texto, String categoria, Integer ordenUso) {
  public static FraseFrecuenteResponse from(FraseFrecuente f) {
    return new FraseFrecuenteResponse(f.getId(), f.getTexto(), f.getCategoria(), f.getOrdenUso());
  }
}
