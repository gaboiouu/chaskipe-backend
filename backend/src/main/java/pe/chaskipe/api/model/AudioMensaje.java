package pe.chaskipe.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "audio_mensaje")
public class AudioMensaje {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "audio_mensaje_id")
  private Long id;

  @Column(name = "mensaje_id", nullable = false, unique = true)
  private Long mensajeId;

  @Column(name = "ruta_audio", nullable = false)
  private String rutaAudio;

  @Column(name = "voz_usada", nullable = false)
  private String vozUsada;

  @Column(nullable = false)
  private java.math.BigDecimal velocidad = java.math.BigDecimal.ONE;

  @Column(name = "duracion_segundos")
  private Integer duracionSegundos;

  protected AudioMensaje() {}

  public AudioMensaje(Long m, String r, String v, java.math.BigDecimal ve, Integer d) {
    mensajeId = m;
    rutaAudio = r;
    vozUsada = v;
    velocidad = ve;
    duracionSegundos = d;
  }

  public Long getId() {
    return id;
  }

  public Long getMensajeId() {
    return mensajeId;
  }

  public String getRutaAudio() {
    return rutaAudio;
  }

  public String getVozUsada() {
    return vozUsada;
  }

  public java.math.BigDecimal getVelocidad() {
    return velocidad;
  }

  public Integer getDuracionSegundos() {
    return duracionSegundos;
  }

  public void actualizar(String r, String v, java.math.BigDecimal ve, Integer d) {
    rutaAudio = r;
    vozUsada = v;
    velocidad = ve;
    duracionSegundos = d;
  }
}
