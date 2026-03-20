package repasoexamenespoo;

import java.util.HashSet;
import java.util.Set;

public class socios {
	
	private static Set<Integer> idsUsados = new HashSet<>();
	
	private int id; 
	private int telefono; 
	private int prestamo; 
	private String nombre; 
	private String apellido; 

	public socios(int id, int telefono, int prestamo, String nombre, String apellido) {
		setApellido(apellido);
		setId(id);
		setNombre(nombre);
		setPrestamo(prestamo);
		setTelefono(telefono);
	}
	public socios(int id, int telefono, String nombre, String apellido) {
		setApellido(apellido);
		setId(id);
		setNombre(nombre);
		this.prestamo = 5;
		setTelefono(telefono);
	}
	public socios(int id, int telefono, String nombre) {
		this.apellido = "";
		setId(id);
		setNombre(nombre);
		this.prestamo = 5;
		setTelefono(telefono);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
	    if (id <= 0) {
	        throw new IllegalArgumentException("ID debe ser positivo");
	    }
	    if (idsUsados.contains(id)) {
	        throw new IllegalArgumentException("ID " + id + " ya existe");
	    }
	    this.id = id;
	    idsUsados.add(id);
	}

	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		try {
			if (telefono >=0 && String.valueOf(telefono).length() == 9 ) {
				this.telefono= telefono;
			}else {
				System.out.println("Teléfono por defecto = 111 222 333");
				this.telefono = 111222333;
			}
		} catch (Exception e) {
			System.out.println("Cerrando programa. Error: "+ e);
		}
	}
	public int getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(int prestamo) {
		if (prestamo >= 0) {
			this.prestamo = prestamo;
		}else {
			System.out.println("Prestamo por defecto = 5");
			this.prestamo = 5; 
		}
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre= nombre; 
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	//====================
	// 		MÉTODOS
	//====================
	
	/**
	 * Borra los ids de libros para poder volver a usarlo
	 * @param id del libro a borrar 
	 */
	public static void borrarLibro(int id) {
		idsUsados.remove(id);
	}
}
