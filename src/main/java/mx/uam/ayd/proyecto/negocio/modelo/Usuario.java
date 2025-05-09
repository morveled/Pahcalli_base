package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * Entidad de negocio Usuario
 * 
 * @author humbertocervantes
 *
 */
@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;

	private String nombre;
	
	private String apellido;
	
	private int edad;

	private String password;

	@OneToOne
	@JoinColumn(name = "idEmpleado")
	private Empleado empleado;
}
