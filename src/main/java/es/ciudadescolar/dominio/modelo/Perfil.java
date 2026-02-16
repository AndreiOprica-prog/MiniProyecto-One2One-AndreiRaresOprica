package es.ciudadescolar.dominio.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil
{
    private static final long serialVersionUID = 1L;

    @Id // El atributo encontrado debajo de esta sentencia es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fijar la clase como una entidad *solo se una en la clave primaria*
    @Column(name = "id_perfil") // Vincular el atributo a la columna *el nombre tiene que ser el de la tabla*
    private Long id_perfil;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String contrasenia;

    @Column(name = "usuario")
	private Long usuario;


}
