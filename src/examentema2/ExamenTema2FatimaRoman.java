package examentema2;

import java.util.Random;
import java.util.Scanner;

public class ExamenTema2FatimaRoman {

	public static void main(String[] args) {
		//Abrir el escaner 
		Scanner sc= new Scanner(System.in);
		
		//Abrir el random para numeros y palos aleatorios
		Random rand = new Random();
		
		//Variable para si o no (S/N)
		String siONo = " "; 
		//Variable para sota, caballo, rey
		String cartasDif = " "; 
		//Variable para el palo 
		int palo; 
		//Variable a texto el palo 
		String paloDif;
		//Variable para la carta
		int carta; 
		//Variable para el valor final de la carta
		double valorCarta; 
		//Variable para la suma de puntos por jugador 
		double jugador1=0; 
		double jugador2=0;  
		
		//Damos la bienvenida al jugador 
		System.out.println("Bienvenidos al juego de las siete y media \n\n");
		
		//Hacemos un bucle para que ambos jueguen sin repetir código
		for (int turno =1; turno<=2; turno++) {
			do {
				//Empieza el juego le preguntamos al jugador si desea sacar una carta
				System.out.println("Es el turno del JUGADOR "+turno+" \n¿Desea sacar una carta? (S/N)");
				siONo = sc.nextLine().toUpperCase();
				
				//Muestra los valores correctos 
				if (!siONo.equals("S") && !siONo.equals("N")) {System.out.println("EL VALOR DEBE SER S O N");}
			}while (!siONo.equals("S") && !siONo.equals("N"));
			
			//Si el valor es distinto a S o N da error. No funciona porque el usuario se queda en el bucle hasta que introduzca el valor correcto
			assert siONo.equals("N") || siONo.equals("S"): "El valor debe ser S o N";
			
			//Mientras el jugador responda que desea una carta 
			while (siONo.equals("S")) {
				
				//Generamos un número aleatorio del 1-10 
				carta = rand.nextInt(1,11); 
				
				//Sacamos un número aleatorio del 1-4 correspondientos con los 4 palos
				palo = rand.nextInt(1,5);
				
				//Si el valor de la carta es superior a 7 entonces es sota, caballo o rey y le reasignamos el valor
				valorCarta=switch (carta) {
				case 8: {
					cartasDif = "sota";
					yield 0.5;
				}
				case 9:{
					cartasDif = "caballo";
					yield 0.5;
				}
				case 10:{
					cartasDif = "rey";
					yield 0.5;
				}
				//Si el número no es superior a 7 continua con su valor original 
				default:{
					yield carta;
					}
				};
				
				//Imprimimos la carta con su palo
				switch (palo) {
				case 1: {
					paloDif = " de oro";
					break;
				}
				case 2 :{
					paloDif = " de copas";
					break;
				}
				case 3:{
					paloDif = " de espadas";
					break;
				}
				case 4:{
					paloDif = " de basto";
					break;
				}
				default:{
					paloDif = " ";
				}
				};
				
				//Comprobamos quien esta jugando. Sumamos el valor a el marcador del jugador 
				if (turno == 1) {
					jugador1+=valorCarta;
				}else {
					jugador2+=valorCarta; 
				}
				
				//mostramos los datos del juego
				System.out.println(carta>7 ? "Su carta es " + cartasDif+ paloDif : "Su carta es " + carta+ paloDif);
				System.out.println("Su puntuación total es: "+ (turno==1?jugador1:jugador2));
				
				//si tras la suma el valor es mayor a 7.5 se para el bucle
				if(turno==1 ? jugador1 >=7.6: jugador2 >= 7.6) {
					System.out.println("¡Te has pasado! :(");
					break;
				}else if (turno==1 ? jugador1 ==7.5: jugador2 == 7.5) {
					System.out.println("CALCULADISMO. Fin de tu turno :)");
					break;
				}
				
				do {
					//Volvemos a preguntar para darle otra carta o pasar al siguiente jugador 
					System.out.println("¿Desea sacar otra carta? (S/N)");
					siONo = sc.nextLine().toUpperCase();
					
					//Muestra los valores correctos 
					if (!siONo.equals("S") && !siONo.equals("N")) {System.out.println("EL VALOR DEBE SER S O N");}
				}while (!siONo.equals("S") && !siONo.equals("N"));
				
				
			
			} //fin del while
			
		}//fin del for 
		
		//Imprimimos los puntajes
		System.out.println("\nEl JUGADOR 1 tiene "+ jugador1+ " puntos \nEl JUGADOR 2 tiene "+ jugador2+ " puntos");
		
		//Comprobamos quien ha ganado 
		if (jugador1 >jugador2 && jugador1<7.6 ) {
			System.out.println("GANA EL JUGADOR 1 :)");
		}else if (jugador1 == jugador2) {
			System.out.println("EMPATE  :(");
		}else if (jugador1>7.6 && jugador2>7.6) {
			System.out.println("LOS DOS JUGADORES PERDEIS -_-");
		}else{
			System.out.println("GANA EL JUGADOR 2 :)");
		}
		
		//Cerrar Scanner 
		sc.close();
	}

}
