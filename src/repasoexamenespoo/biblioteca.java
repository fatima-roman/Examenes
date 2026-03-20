package repasoexamenespoo;

import java.util.Scanner;

public class biblioteca {
	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {

		int opc =0;
		
		try {
			do {
				System.out.println("Qué acción quieres realizar?"
						+ "1. Registrar nuevo libro"
						+ "2. Registrar nuevo socio"
						+ "3. Registar préstamo"
						+ "4. Consultar sistema"
						+ "5. Salir");
				opc = reader.nextInt();
				
				switch (opc) {
				case 1: {
					
					break;
				}
				case 2: {
					
					break;
				}
				case 3: {
					
					break;
				}
				case 4:{
					
					break;
				}
				}
			}while(opc != 5);
			
			
			
		}catch (ArithmeticException e) {
			System.out.println("Valor incorrecto.");
		}catch (NullPointerException e) {
			System.out.println("Valor nulo.");
		} catch (Exception e) {
			System.out.println("Error :" + e);
		}
		
		reader.close();
	}
	
	public static libro registrarLibro() {
		
		System.out.println("Introduzca el id del libro: ");
		int id = reader.nextInt();
		System.out.println("Introduzca el titulo del libro: ");
		String titulo = reader.next();
		System.out.println("Introduzca el número de ejemplares del libro: ");
		int ejemplar = reader.nextInt();
		System.out.println("Introduzca la cantidad de libros disponibles del libro: ");
		boolean dispo = reader.nextInt();
		
		
		return null;	
	}
	
	public static String comprobarNombre() {
		
	}
}
