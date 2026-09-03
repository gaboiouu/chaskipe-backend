package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sesion_captura")
public class SesionCaptura {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sesion_captura_id")
  private Long id;

  @Column(name = "conversacion_id", nullable = false)
  private Long conversacionId;

  @Column(name = "usuario_id", nullable = false)
  private UUID usuarioId;

  @Column(name = "fecha_hora", insertable = false, updatable = false)
  private OffsetDateTime fechaHora;

  @Column(name = "duracion_segundos")
  private Integer duracionSegundos;

  @Column(name = "ruta_video")
  private String rutaVideo;

  @Column(name = "estado_deteccion", nullable = false)
  private String estadoDeteccion = "procesando";

  protected SesionCaptura() {}

  public SesionCaptura(Long c, UUID u, Integer d, String r, String e) {
    conversacionId = c;
    usuarioId = u;
    duracionSegundos = d;
    rutaVideo = r;
    estadoDeteccion = e;
  }

  public Long getId() {
    return id;
  }

  public Long getConversacionId() {
    return conversacionId;
  }

  public UUID getUsuarioId() {
    return usuarioId;
  }

  public OffsetDateTime getFechaHora() {
    return fechaHora;
  }

  public Integer getDuracionSegundos() {
    return duracionSegundos;
  }

  public String getRutaVideo() {
    return rutaVideo;
  }

  public String getEstadoDeteccion() {
    return estadoDeteccion;
  }

  public void actualizar(Integer d, String r, String e) {
    duracionSegundos = d;
    rutaVideo = r;
    estadoDeteccion = e;
  }
}
