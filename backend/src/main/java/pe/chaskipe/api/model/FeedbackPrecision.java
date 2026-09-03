package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback_precision")
public class FeedbackPrecision {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "feedback_id")
  private Long id;

  @Column(name = "mensaje_id", nullable = false)
  private Long mensajeId;

  @Column(name = "usuario_id", nullable = false)
  private UUID usuarioId;

  @Column(nullable = false)
  private Short calificacion;

  private String comentario;

  @Column(name = "fecha_hora", insertable = false, updatable = false)
  private OffsetDateTime fechaHora;

  protected FeedbackPrecision() {}

  public FeedbackPrecision(Long m, UUID u, Short c, String co) {
    mensajeId = m;
    usuarioId = u;
    calificacion = c;
    comentario = co;
  }

  public Long getId() {
    return id;
  }

  public Long getMensajeId() {
    return mensajeId;
  }

  public UUID getUsuarioId() {
    return usuarioId;
  }

  public Short getCalificacion() {
    return calificacion;
  }

  public String getComentario() {
    return comentario;
  }

  public OffsetDateTime getFechaHora() {
    return fechaHora;
  }

  public void actualizar(Short c, String co) {
    calificacion = c;
    comentario = co;
  }
}
