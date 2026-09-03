package pe.chaskipe.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "frase_frecuente")
public class FraseFrecuente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "frase_frecuente_id")
  private Long id;

  @Column(nullable = false)
  private String texto;

  private String categoria;

  @Column(name = "orden_uso", nullable = false)
  private Integer ordenUso = 0;

  protected FraseFrecuente() {}

  public FraseFrecuente(String t, String c, Integer o) {
    texto = t;
    categoria = c;
    ordenUso = o;
  }

  public Long getId() {
    return id;
  }

  public String getTexto() {
    return texto;
  }

  public String getCategoria() {
    return categoria;
  }

  public Integer getOrdenUso() {
    return ordenUso;
  }

  public void actualizar(String t, String c, Integer o) {
    texto = t;
    categoria = c;
    ordenUso = o;
  }
}
