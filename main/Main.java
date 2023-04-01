package actividad04.main;

import java.io.IOException;

import actividad04.tools.Menu; // Importo el package tools para poder usar las funciones de la clase Menu

/**
 * 
 * @author Antonio Ruiz Benito DAW
 * 
 *         Clase principal donde se ejecutara el programa
 *
 */
public class Main {
	/**
	 * Funcion principal del programa donde se ejecutara
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Invoco a la funcion menuUsuario() de la clase Menu
		Menu.menuUsuario();

		System.out.println("Ejercicio realizado por Antonio Ruiz Benito.");

	}

}
