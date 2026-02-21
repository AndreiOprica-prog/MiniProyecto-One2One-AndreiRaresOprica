package es.ciudadescolar.dominio.modelo;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id // El atributo encontrado debajo de esta sentencia es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fijar la clase como una entidad *solo se una en la clave primaria*
    @Column(name = "id_perfil") // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private Integer id_perfil;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String contrasenia;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", unique = true, nullable = false)
	private Usuario usuario;

    public Integer getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(Integer id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil(String username, String contrasenia, Usuario usuario) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.usuario = usuario;
    }

    public Perfil() {}

    @Override
    public String toString() {
        return "Perfil [id_perfil=" + id_perfil + ", username=" + username + ", contrasenia=" + contrasenia
                + ", usuario=" + usuario + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_perfil == null) ? 0 : id_perfil.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
        Perfil other = (Perfil) obj;
        if (id_perfil == null) {
            if (other.id_perfil != null)
                return false;
        } else if (!id_perfil.equals(other.id_perfil))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (contrasenia == null) {
            if (other.contrasenia != null)
                return false;
        } else if (!contrasenia.equals(other.contrasenia))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }

}
