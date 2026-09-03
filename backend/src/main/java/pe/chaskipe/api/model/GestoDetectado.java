package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gesto_detectado")
public class GestoDetectado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gesto_detectado_id")
  private Long id;

  @Column(name = "sesion_captura_id", nullable = false)
  private Long sesionCapturaId;

  @Column(nullable = false)
  private String etiqueta;

  @Column(name = "confianza_deteccion", nullable = false)
  private BigDecimal confianzaDeteccion;

  @Column(name = "timestamp_ms", nullable = false)
  private Integer timestampMs;

  protected GestoDetectado() {}

  public GestoDetectado(Long s, String e, BigDecimal c, Integer t) {
    sesionCapturaId = s;
    etiqueta = e;
    confianzaDeteccion = c;
    timestampMs = t;
  }

  public Long getId() {
    return id;
  }

  public Long getSesionCapturaId() {
    return sesionCapturaId;
  }

  public String getEtiqueta() {
    return etiqueta;
  }

  public BigDecimal getConfianzaDeteccion() {
    return confianzaDeteccion;
  }

  public Integer getTimestampMs() {
    return timestampMs;
  }

  public void actualizar(String e, BigDecimal c, Integer t) {
    etiqueta = e;
    confianzaDeteccion = c;
    timestampMs = t;
  }
}
