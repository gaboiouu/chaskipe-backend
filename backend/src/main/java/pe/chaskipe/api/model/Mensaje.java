package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mensaje")
public class Mensaje {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mensaje_id")
  private Long id;

  @Column(name = "conversacion_id", nullable = false)
  private Long conversacionId;

  @Column(name = "sesion_captura_id")
  private Long sesionCapturaId;

  @Column(nullable = false)
  private String emisor;

  @Column(name = "tipo_contenido", nullable = false)
  private String tipoContenido;

  @Column(nullable = false)
  private String contenido;

  @Column(name = "precision_traduccion")
  private BigDecimal precisionTraduccion;

  @Column(name = "fecha_hora", insertable = false, updatable = false)
  private OffsetDateTime fechaHora;

  protected Mensaje() {}

  public Mensaje(Long c, Long s, String e, String t, String co, BigDecimal p) {
    conversacionId = c;
    sesionCapturaId = s;
    emisor = e;
    tipoContenido = t;
    contenido = co;
    precisionTraduccion = p;
  }

  public Long getId() {
    return id;
  }

  public Long getConversacionId() {
    return conversacionId;
  }

  public Long getSesionCapturaId() {
    return sesionCapturaId;
  }

  public String getEmisor() {
    return emisor;
  }

  public String getTipoContenido() {
    return tipoContenido;
  }

  public String getContenido() {
    return contenido;
  }

  public BigDecimal getPrecisionTraduccion() {
    return precisionTraduccion;
  }

  public OffsetDateTime getFechaHora() {
    return fechaHora;
  }

  public void actualizar(String e, String t, String c, BigDecimal p) {
    emisor = e;
    tipoContenido = t;
    contenido = c;
    precisionTraduccion = p;
  }
}
