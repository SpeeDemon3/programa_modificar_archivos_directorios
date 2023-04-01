package actividad04.tools;

import java.io.IOException;

/**
 * 
 * @author Antonio Ruiz Benito DAW
 * 
 * Clase que contiene una funcion con un menu para mostrar a un usuario por consola
 * y desde la que se llaman a diferentes funciones para dar funcionalidad
 * al programa
 *
 */
public class Menu {
	
	public static void menuUsuario() throws IOException {
		// Varible para controlar el bucle do while
		boolean control = false;
		// Mensaje por consola de bienvenida al usuario
		System.out.println("Bienvenido al programa de control de de archivos, por favor seleccione una opción:");
		// Con un bucle do while controlo que el menu sigua ejecutandose hasta que el usuario decida lo contrario
		do {
			// Creo el menu que le aparacera al usuario por consola con "\n" genero un salto de linea
			System.out.println(""
					+ "1 - Nuevo Archivo. \n"
					+ "2 - Listar Archivos. \n"
					+ "3 - Muestra un Archivo. \n"
					+ "4 - Borrar un Archivo. \n"
					+ "5 - Renombrar un Archivo. \n"
					+ "6 - Reemplazar caracteres de un Archivo utilizado. \n"
					+ "7 - Salir del programa."
					+ "\n");
			
			// Invoco a la funcion recogerValorUsuario() dentro de la variable
			int opcionUsuario = Datos.recogerValorUsuario();
				
			// Con una estructura swich manejo todas las opciones que puede seleccionar el usuario
			switch(opcionUsuario) {
				// Funcionalidad para crear un nuevo archivo
				case 1:
					// Invoco a la funcion crearArchivo() de la clase ManipularArchivos
					ManipularArchivos.crearArchivo();
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
					break;
				//	Funcionalidad de listar los archivos dentro de la carpeta 'archivos'
				case 2:
					// Invoco a la funcion listarArchivos() de la clase ManipularArchivos
					ManipularArchivos.listarArchivos();
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
					break;
				//	Funcionalidad para mostrar el contenido de un archivo
				case 3:
					// Guardo en datos el array que me devuelve la funcion .listarArchivos()
					String[]datos = ManipularArchivos.listarArchivos();
					// Invoco a la funcion mostrarArchivos()
					// Paso el array datos como parametro a la funcion mostrarArchivos()
					ManipularArchivos.mostrarArchivos(datos);
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
					break;
				// Funcionalidad que permite borrar un archivo de la carpeta 'archivos'
				case 4:
					// Guardo en datos2 el array que me devuelve la funcion .listarArchivos()
					String[]datos2 = ManipularArchivos.listarArchivos();
					// Invoco a la funcion borrarArchivo()
					// Paso el array datos como parametro a la funcion mostrarArchivos()
					ManipularArchivos.borrarArchivo(datos2);
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
					break;
				// Funcionalidad que permite renombrar un archivo dentro de la carpeta 'archivos'
				case 5:
					// Invoco a la funcion renombrarArchivo() de la clase ManipularArchivos
					ManipularArchivos.renombrarArchivo();
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
					break;
				// Funcionalidad que permite sustituir un caracter por otro dentro de un archivo
				case 6:
					// Invoco a la funcion remplazarCaracteres() de la clase ManipularArchivos
					ManipularArchivos.remplazarCaracteres();
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
					break;
				// Funcionalidad para finalizar el programa
				case 7:
					System.out.println("Gracias por utilizar el programa. \n");
					// Cambio el valor de la variable a true para salir del bucle y finalizar el programa
					control = true;
					break;
				// Mensaje por defecto si la opcion no es valida
				default :
					// Informo al usuario que la opcion no es valida
					System.out.println("Opción no valida.");
					// Añado un salto de linea para que quede mas limpio
					System.out.println("");
			}
			
			
		} while(!control); //Mientras la variable control no sea true seguira iterando
		
	}
	


}
