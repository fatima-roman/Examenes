package repasoexamenespoo;

import java.util.HashSet;
import java.util.Set;

public class libro {
	
	private static Set<Integer> idsUsados = new HashSet<>();

	private int id; 
	private int ejemplares;
	private boolean disponibilidad; 
	private String titulo;
	
	public libro(int id, int ejemplares, boolean dispo, String libro) {
		setId(id);
		setEjemplares(ejemplares);
		setDisponibilidad(dispo);
		setTitulo(libro);
		
	}
	
	public libro(int id, String titulo) {
		setId(id);
		setTitulo(titulo);
		this.ejemplares =0;
		this.disponibilidad = false; 
	}
	
	public libro(int id, String titulo, int ejem) {
		setId(id);
		setTitulo(titulo);
		setEjemplares(ejem);
		this.disponibilidad = false; 
	}
	
	public libro(int id, String titulo, boolean dispo) {
		setId(id);
		setTitulo(titulo);
		this.ejemplares =0;
		setDisponibilidad(dispo);
	}
	
	
	//===========================
	//		SETTERS y GETTERS 
	//===========================
	
	public int getId() {
		return id;
	}

	//Verifica Id no repetida 
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

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		try {
			if (ejemplares >= 0) {
				this.ejemplares= ejemplares;
			}else {
				this.ejemplares = 0;
			}
		} catch (ArithmeticException e) {
			System.out.println("Valor no válido, valor por defecto = 0");
		} catch (Exception e) {
			System.out.println("Valor por defecto = 0, error: "+ e);
		}
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		try {
			this.disponibilidad = disponibilidad; 
		} catch (NullPointerException  e) {
			System.out.println("Valor no válido, valor por defecto = false");
		} catch (Exception e) {
			System.out.println("Valor por defecto = false, error: "+ e);
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
