package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "configuracion_usuario")
public class ConfiguracionUsuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "configuracion_id")
  private Long id;

  @Column(name = "usuario_id", nullable = false, unique = true)
  private UUID usuarioId;

  @Column(name = "voz_seleccionada", nullable = false)
  private String vozSeleccionada = "default";

  @Column(name = "velocidad_audio", nullable = false)
  private BigDecimal velocidadAudio = BigDecimal.ONE;

  @Column(name = "velocidad_avatar", nullable = false)
  private BigDecimal velocidadAvatar = BigDecimal.ONE;

  @Column(name = "subtitulos_activos", nullable = false)
  private boolean subtitulosActivos = true;

  @Column(name = "tamano_fuente", nullable = false)
  private BigDecimal tamanoFuente = BigDecimal.ONE;

  @Column(name = "idioma_senas", nullable = false)
  private String idiomaSenas = "LSP";

  protected ConfiguracionUsuario() {}

  public ConfiguracionUsuario(UUID u) {
    usuarioId = u;
  }

  public Long getId() {
    return id;
  }

  public UUID getUsuarioId() {
    return usuarioId;
  }

  public String getVozSeleccionada() {
    return vozSeleccionada;
  }

  public BigDecimal getVelocidadAudio() {
    return velocidadAudio;
  }

  public BigDecimal getVelocidadAvatar() {
    return velocidadAvatar;
  }

  public boolean isSubtitulosActivos() {
    return subtitulosActivos;
  }

  public BigDecimal getTamanoFuente() {
    return tamanoFuente;
  }

  public String getIdiomaSenas() {
    return idiomaSenas;
  }

  public void actualizar(
      String v, BigDecimal va, BigDecimal vv, boolean s, BigDecimal t, String i) {
    vozSeleccionada = v;
    velocidadAudio = va;
    velocidadAvatar = vv;
    subtitulosActivos = s;
    tamanoFuente = t;
    idiomaSenas = i;
  }
}
