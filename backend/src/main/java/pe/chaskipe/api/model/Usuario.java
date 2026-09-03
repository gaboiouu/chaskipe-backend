package pe.chaskipe.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {
  @Id
  @Column(name = "usuario_id")
  private UUID id;

  @Column(nullable = false)
  private String nombres;

  @Column(nullable = false)
  private String apellidos;

  @Column(nullable = false, unique = true)
  private String correo;

  @Column(name = "tipo_usuario", nullable = false)
  private String tipoUsuario;

  @Column(name = "foto_perfil_url")
  private String fotoPerfilUrl;

  @Column(name = "fecha_registro", insertable = false, updatable = false)
  private OffsetDateTime fechaRegistro;

  @Column(nullable = false)
  private boolean activo = true;

  protected Usuario() {}

  public Usuario(
      UUID id, String nombres, String apellidos, String correo, String tipoUsuario, String foto) {
    this.id = id;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.correo = correo;
    this.tipoUsuario = tipoUsuario;
    this.fotoPerfilUrl = foto;
  }

  public UUID getId() {
    return id;
  }

  public String getNombres() {
    return nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public String getCorreo() {
    return correo;
  }

  public String getTipoUsuario() {
    return tipoUsuario;
  }

  public String getFotoPerfilUrl() {
    return fotoPerfilUrl;
  }

  public OffsetDateTime getFechaRegistro() {
    return fechaRegistro;
  }

  public boolean isActivo() {
    return activo;
  }

  public void actualizar(String n, String a, String c, String t, String f, boolean activo) {
    nombres = n;
    apellidos = a;
    correo = c;
    tipoUsuario = t;
    fotoPerfilUrl = f;
    this.activo = activo;
  }
}
