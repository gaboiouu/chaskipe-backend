package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "historial_actividad")
public class HistorialActividad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "historial_id")
  private Long id;

  @Column(name = "usuario_id", nullable = false)
  private UUID usuarioId;

  @Column(name = "conversacion_id")
  private Long conversacionId;

  private String icono;

  @Column(nullable = false)
  private String titulo;

  @Column(nullable = false)
  private String tipo;

  @Column(name = "fecha_hora", insertable = false, updatable = false)
  private OffsetDateTime fechaHora;

  protected HistorialActividad() {}

  public HistorialActividad(UUID u, Long c, String i, String t, String ti) {
    usuarioId = u;
    conversacionId = c;
    icono = i;
    titulo = t;
    tipo = ti;
  }

  public Long getId() {
    return id;
  }

  public UUID getUsuarioId() {
    return usuarioId;
  }

  public Long getConversacionId() {
    return conversacionId;
  }

  public String getIcono() {
    return icono;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getTipo() {
    return tipo;
  }

  public OffsetDateTime getFechaHora() {
    return fechaHora;
  }

  public void actualizar(String i, String t, String ti) {
    icono = i;
    titulo = t;
    tipo = ti;
  }
}
