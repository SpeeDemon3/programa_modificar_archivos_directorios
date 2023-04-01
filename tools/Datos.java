package actividad04.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que contienes funciones interactuar con el usuario y recoger valores
 * 
 * @author Antonio Ruiz Benito
 *
 */
public class Datos {

	/**
	 * Funcion que recoge el valor introducido por el usuario y evalua si es un
	 * valor numerico y si esta dentro del rango del 1 al 7, retornando el valor de
	 * tipo int
	 * 
	 * @return Valor numerico introducido por el usuario dentro del rango de 1 - 7
	 * @throws IOException
	 */
	public static int recogerValorUsuario() throws IOException {
		// Inicializo la variable a 0 en la que guardare la opcion escogida por el
		// usuario
		int opcion = 0;
		// Variable de control para condicionar el bucle
		boolean control = false;

		// Controlo las excepciones que pueda provocar el usuario por teclado
		try {

			do {

				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// Guardo el valor introducido por el usuario en la variable opcionUsuario
				String opcionUsuario = br.readLine();
				// Parseo (convierto) el valor introducido de String a int y lo guardo en la
				// variable opcion
				opcion = Integer.parseInt(opcionUsuario);

				// Controlo que si la opcion escogida es menor a 1 o mayor a 7 informo al
				// usuario
				if (opcion < 1 || opcion > 7) {
					// Informo por consola al usuario que esta fuera del rango numerico
					System.out.println("Por favor introduce un valor numérico del 1 al 7." + "\n");
					break; // Salgo de la funcion para que se le vuelva a mostrar el menu al usuario

					// Si el valor introducido es correcto
				} else {
					// Muestro un mensaje al usuario con la opcion escogida
					System.out.println("Perfecto, la opción escogida es: " + opcion + "\n");
					// Cambio la variable de control a true para salir del bucle
					control = true;

				}

			} while (!control); // Mientras la variable control no sea true el bucle seguira iterando

			// Controlo si el usuario introduce un valor que no sea numerico y le muestro un
			// mensaje por consola ayudandole
		} catch (NumberFormatException e) {
			System.out.println("Error!!! Por favor introduce un valor numérico del 1 al 7." + "\n");
		}

		return opcion; // Retorno la opcion escogida por el usuario

	}

}
