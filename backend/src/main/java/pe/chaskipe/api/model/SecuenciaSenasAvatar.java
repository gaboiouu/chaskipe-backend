package pe.chaskipe.api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "secuencia_senas_avatar")
public class SecuenciaSenasAvatar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "secuencia_senas_id")
  private Long id;

  @Column(name = "mensaje_id", nullable = false, unique = true)
  private Long mensajeId;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "glosa_json", nullable = false, columnDefinition = "jsonb")
  private String glosaJson;

  @Column(name = "duracion_ms")
  private Integer duracionMs;

  protected SecuenciaSenasAvatar() {}

  public SecuenciaSenasAvatar(Long m, String g, Integer d) {
    mensajeId = m;
    glosaJson = g;
    duracionMs = d;
  }

  public Long getId() {
    return id;
  }

  public Long getMensajeId() {
    return mensajeId;
  }

  public String getGlosaJson() {
    return glosaJson;
  }

  public Integer getDuracionMs() {
    return duracionMs;
  }

  public void actualizar(String g, Integer d) {
    glosaJson = g;
    duracionMs = d;
  }
}
