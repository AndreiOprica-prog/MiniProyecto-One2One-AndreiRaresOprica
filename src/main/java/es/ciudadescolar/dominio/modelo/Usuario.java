package es.ciudadescolar.dominio.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario
{
    private static final long serialVersionUID = 1L;


    @Id // El atributo encontrado debajo de esta sentencia es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fijar la clase como una entidad *solo se una en la clave primaria*
    @Column(name = "id_usuario") // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private Long usuarioId;

    @Column(name = "nombre", nullable = false) // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private String nombre;

    @Column(name = "apellidos", nullable = false) // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private String apellidos;

    @Column(name = "fecha_nacimiento") // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private LocalDate fecha_nacimiento;

    @Column(name = "email", unique = true) // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private String email;
    
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Usuario [usuarioId=" + usuarioId + ", nombre=" + nombre + ", apellidos=" + apellidos
                + ", fecha_nacimiento=" + fecha_nacimiento + ", email=" + email + "]";
    }

    public Usuario(String nombre, String apellidos, LocalDate fecha_nacimiento, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
    }

    public Usuario () {}
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((fecha_nacimiento == null) ? 0 : fecha_nacimiento.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (usuarioId == null) {
            if (other.usuarioId != null)
                return false;
        } else if (!usuarioId.equals(other.usuarioId))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (apellidos == null) {
            if (other.apellidos != null)
                return false;
        } else if (!apellidos.equals(other.apellidos))
            return false;
        if (fecha_nacimiento == null) {
            if (other.fecha_nacimiento != null)
                return false;
        } else if (!fecha_nacimiento.equals(other.fecha_nacimiento))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

}
