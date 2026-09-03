package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "conversacion")
public class Conversacion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "conversacion_id")
  private Long id;

  @Column(name = "usuario_id", nullable = false)
  private UUID usuarioId;

  private String titulo;

  @Column(name = "fecha_inicio", insertable = false, updatable = false)
  private OffsetDateTime fechaInicio;

  @Column(name = "fecha_fin")
  private OffsetDateTime fechaFin;

  protected Conversacion() {}

  public Conversacion(UUID u, String t) {
    usuarioId = u;
    titulo = t;
  }

  public Long getId() {
    return id;
  }

  public UUID getUsuarioId() {
    return usuarioId;
  }

  public String getTitulo() {
    return titulo;
  }

  public OffsetDateTime getFechaInicio() {
    return fechaInicio;
  }

  public OffsetDateTime getFechaFin() {
    return fechaFin;
  }

  public void actualizar(String t, OffsetDateTime f) {
    titulo = t;
    fechaFin = f;
  }
}
