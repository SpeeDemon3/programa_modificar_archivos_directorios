package actividad04.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * 
 * Clase que contiene funciones para manipular archivos, comprobar si existe una
 * carpeta o crear una carpeta
 * 
 * @author Antonio Ruiz Benito
 *
 */
public class ManipularArchivos {

	/**
	 * Funcion que crea un archivo pidiendo le al usuario el nombre que le desea
	 * ponner al archivo y despues con otra funcion pasandole por parametros el
	 * nombre creado permite editarlo con texto
	 */
	public static void crearArchivo() {

		// Pido al usuario el nombre que desea poner al archivo
		System.out.println("Indica el nombre del archivo que desea crear:");
		// Creo un objeto BufferedReader que utiliza un objeto InputStreamReader para
		// leer los datos que introduzca el usuario
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Asigno a la variable separador la propiedad separator de File para conocer el
		// separador que utilice el sistema
		String separador = File.separator;
		try {
			// Guardo el nombre introducido por el usuario en la variable nombre
			String nombre = br.readLine();
			// Creo una variable de tipo File para crear el archivo pasando le como
			// parametros el metodo crearCarpeta() la variable separador
			// y la variable nombre que contiene el nombre introducido por el usuario
			File nuevoArchivo = new File(crearCarpeta() + separador + nombre);
			// Creo el archivo con el metodo createNewFile()
			nuevoArchivo.createNewFile();
			// Informo al usuario por consola
			System.out.println("El archivo " + nombre + " fue creado con exito." + "\n");
			rellenarConTextoArchivo(nombre);

		} catch (Exception e) {
			// Informo de que hay un error en caso de producirse
			System.out.println("Error!!!");
		}

	}

	/**
	 * Funcion que comprueba si existe una carpeta y en caso de no existir la crea.
	 * 
	 * @return Ruta de la carpeta
	 */
	public static String crearCarpeta() {

		// Asigno a la variable rutaProyecto la ruta absoluta del proyecto con el metodo
		// .getProperty()
		String rutaProyecto = System.getProperty("user.dir");
		// Asigno a la variable separador la propiedad separator de File para conocer el
		// separador que utilice el sistema
		String separador = File.separator;
		// Asigno a la variable rutaCarpeta la ruta que tendra la carpeta "archivos"
		// haciendo uso de las variables rutaProyecto y separador
		String rutaCarpeta = rutaProyecto + separador + "archivos";

		// Creo una variable de tipo File a la que asigno la ruta de la carpeta
		File carpeta = new File(rutaCarpeta);

		// Creo la carpeta "archivos" si no exite comprovandolo con un condicional if y
		// el metodo .exists() para comprobar si existe
		// ya el archivo o no
		if (!carpeta.exists()) {
			// Si no existe con el metodo .mkdir() se creara la carpeta
			carpeta.mkdir();
			// Informo por consola al usuario que la carpeta a sido creada
			System.out.println("La carpeta 'archivos' a sido creada.");
		} else {
			// Informo por consola al usuario que la carpeta ya existe
			System.out.println("La carpeta 'archivos' ya existe.");
		}

		return rutaCarpeta; // Retorno la ruta de la carpeta
	}

	/**
	 * Funcion que permite rellenar un documento con texto
	 * 
	 * @param documento -> Nombre del documento que desea editar
	 */
	public static void rellenarConTextoArchivo(String documento) {

		// Asigno a la variable rutaProyecto la ruta absoluta del proyecto con el metodo
		// .getProperty()
		String rutaProyecto = System.getProperty("user.dir");
		// Asigno a la variable separador la propiedad separator de File para conocer el
		// separador que utilice el sistema
		String separador = File.separator;
		// Asigno a la variable rutaDocumento toda la ruta hasta el archivo a editar
		String rutaDocumento = rutaProyecto + separador + "archivos" + separador + documento;

		// Asigno a la variable de tipo File toda la ruta hasta el archivo que sera
		// editado
		File archivo = new File(rutaDocumento);

		try {
			// Creo un stream hacia el archivo que se escribira
			FileWriter fw = new FileWriter(archivo);
			// Creo un Buffer de escritura hacia el Stream creado
			BufferedWriter bw = new BufferedWriter(fw);

			// Muestro al usuario el documento que desea editar
			System.out.println("Puede editar el archivo " + documento + ":" + "\n");
			// Creo una variable BufferedReader para recoger los datos que introduzca el
			// usuario
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// Guardo los datos introducidos en la variable texto
			String texto = br.readLine();
			// Con .write() escribo en el buffer para despues escribir en el archivo
			bw.write(texto);
			// Con .flush() escribo los datos restantes del buffer al archivo
			bw.flush();
			// Con .close() cierro el buffer escribiendo antes los datos restantes que
			// queden en el buffer
			bw.close();
			// Informo al usuario de que el archivo a sido editado correctamente
			System.out.println("El archivo " + documento + " ha sido editado correctamente.");

			// Controlo por si sucediera algun fallo e informo al usuario
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al intentar actualizar el archivo " + documento);

		}

	}

	/**
	 * Funcion que retorna la ruta hasta la carpeta archivos.
	 * 
	 * @return -> Devuelve la ruta de la carpeta
	 */
	public static String obtenerRutaCarpeta() {
		// Utilizo .getProperty("user.id") para guardar la ruta absoluta hasta el
		// proyecto en la variable rutaProyecto
		String rutaProyecto = System.getProperty("user.dir");
		// Utilizo File.separator para guardar el separador que utilice el sistema
		String separador = File.separator;
		// Guardo la ruta de la carpeta "archivos" en la variable rutaCarpeta
		String rutaCarpeta = rutaProyecto + separador + "archivos";

		return rutaCarpeta; // Retorno la ruta hasta la carpeta "archivos"
	}

	/**
	 * Funcion que lee y muestra por consola los archivos que contiene la carpeta
	 * 'archivos'
	 * 
	 * @return -> Retorna un array con las rutas absolutas de cada archivo
	 */
	public static String[] listarArchivos() {
		// Creo una variable de tipo File en la que introduzco el metodo
		// obtenerRutaCarpeta() para pasarle la ruta de la carpeta 'archivos'
		File carpeta = new File(obtenerRutaCarpeta());
		// Utilizo un array para guardar las rutas de los archivos, lo declaro a null
		// para poder inicializarlo despues con la longitud
		// del array listado
		String[] rutasArchivos = null;
		// Con un condicional if y el metodo .exists() compruebo si la carpeta existe
		if (carpeta.exists() == true) {
			// Si existe, creo una array de tipo String al que le paso la variable carpeta
			// con el metodo .list()
			// para obtener el nombre de los ficheros contenidos dentro de la carpeta
			// 'archivos'
			String[] listado = carpeta.list();
			// Inicializo el array con la longitud que tenga el array listado
			rutasArchivos = new String[listado.length];
			// Creo una variable de tipo int que utilizare para poder enumerar los archivos
			// dentro del bucle for empezando por 1
			int numero = 1;

			System.out.println("Archivos que contien la carpeta: \n");
			// Con el bucle for recorro el array para ir mostrando el nombre de los ficheros
			// y enumerarlos con la variable numero
			for (int i = 0; i < listado.length; i++) {
				// Imprimo por consola cada archivo del listado
				System.out.println(numero + ". " + listado[i]);
				// Sumo 1 a la variable numero en cada iteracion
				numero++;
				// Creo una variable File pasando le por parametros el directorio y el nombre
				// del archivo actual
				File rutas = new File(carpeta, listado[i]);
				// Con el metodo getAbsolutePath() obtengo la ruta absoluta de cada archivo
				rutasArchivos[i] = rutas.getAbsolutePath();
				// Imprimo las rutas para comprobar que funciona
				System.out.println(rutasArchivos[i] + "\n");
			}
		} else {
			// Si la carpeta no existe informo al usuario
			System.out.println("Error, la carpeta no existe.");
		}
		// Genero un espacio para que quede mas limpio
		System.out.println();

		return rutasArchivos; // Retorno el array con las rutas absolutas de los archivos

	}

	/**
	 * Funcion que muestra por consola el contenido de un archivo dentro de la
	 * carpeta archivo permitiendo al usuario seleccionar el archivo que desea ver
	 * 
	 * @param arrayDatos -> Array que contiene las rutas absolutas de los archivos
	 *                   de la carpeta 'archivos'
	 */
	public static void mostrarArchivos(String[] arrayDatos) {

		// Pido al usuario por consola que numero de archivo desea seleccionar
		System.out.println("Indica el número de archivo que desea revisar:");

		try {
			// Utilizo la clase BufferedReader para trabajar con los datos del usuario y los
			// datos del archivo
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// Asigno el valor introducido por el usuario en la variable archivo con el
			// metodo readLine()
			String archivoEscogido = br.readLine();

			// Creo una variable de tipo int en la que guardo el valor introducido por el
			// usuario parseandolo de String a int
			// con parseInt()
			int opcion = (int) Integer.parseInt(archivoEscogido);
			// A la variable opcion le resto 1 para que cuadre con la opcion escogida dentro
			// del array haciendo que 1 = 0
			opcion--;

			// Creo una variable de tipo FileReader a la que le paso por parametro la opcion
			// (posicion) que a escogido el usuario
			FileReader fr = new FileReader(arrayDatos[opcion]);
			// Almaceno los datos dentro del Stream dentro de la variable br2 pasandole como
			// parametro la variable fr
			BufferedReader br2 = new BufferedReader(fr);
			// Creo una variable de tipo String para guardar la lectura del archivo
			String linea;

			System.out.println("------------------------------");
			// Leo todas las lineas del archivo y las almaceno en la variable linea hasta
			// que se llegue al final del archivo
			// Imprimo por consola el archivo seleccionado utilizando un bucle while
			while ((linea = br2.readLine()) != null) {
				System.out.println(linea);
			}
			System.out.println("------------------------------");

			// Cierro el buffer para que no se produzcan errores en un futuro
			br2.close();

			// Controlo si se produce algun error
		} catch (Exception e) {
			System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
		}

	}

	/**
	 * Funcion que permite borrar un archivo de la carpeta 'archivos'
	 * 
	 * @param arrayDatos -> Array que contiene las rutas absolutas de los archivos
	 *                   de la carpeta 'archivos'
	 */
	public static void borrarArchivo(String[] arrayDatos) {

		// Pido al usuario por consola que seleccione el archivo a borrar
		System.out.println("Introduzca el número del archivo que desea borrar:");
		// Con la clase BufferedREader y InputStream leo y guardo los datos del usuario
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			// Guardo el valor introducido por el usuario con el metodo readLine()
			String opcionUsuario = br.readLine();
			// Parseo el valor a tipo int
			int opcion = (int) Integer.parseInt(opcionUsuario) - 1;
			// Creo una variable tipo File y le paso por parametro la posicion del array que
			// selecciona el usuario
			File borrarArchivo = new File(arrayDatos[opcion]);
			// Borro el archivo con el metodo delete() de la clase File
			borrarArchivo.delete();
			// Informo al usuario de que el archivo a sido borrado
			System.out.println("El archivo ha sido borrado con exito. \n");
			// Controlo las excepciones mostrando un mensaje al usuario
		} catch (IOException e) {
			System.out.println("Ocurrió un error al borrar el archivo: " + e.getMessage());

		}

	}

	/**
	 * Funcion que permite renombrar un archivo en la carpeta 'archivos' comprobando
	 * que el nuevo nombre sea valido y encaso de no ser valido le vuelve a mostrar
	 * el menu
	 */
	public static void renombrarArchivo() {

		// Creo una variable de control para utilizar como condicional dentro del bucle
		// do while
		boolean control = false;

		// Con un bucle do while muestro nuevamente el menu si el usuario no introduce
		// un nombre valido
		do {
			// Creo un array de String en el que guardo el array que devuelve el metodo
			// listarArchivos()
			// y ejecuto la funcion listarArchivos()
			String[] archivos = listarArchivos();
			// Imprimo por consola un texto para que el usuario elija el archivo que desea
			// renombrar
			System.out.println("Selecciona el número del archivo que desea renombrar:");
			// Utilizo la clase BufferedReader para leer los datos que introduzca el usuario
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			try {
				// Obtengo la ruta donde esta el archivo a modificar con el metodo
				File carpeta = new File(obtenerRutaCarpeta());
				// Guardo el valor introducido por el usuario con el metodo readLine()
				String opcionUsuario = br.readLine();
				// Parseo el valor introducido de String a tipo int restandole 1 para obtener la
				// posicion correcta dentro del array
				int opcion = (int) Integer.parseInt(opcionUsuario) - 1;

				// Creo una variable de tipo File a la que asigno la ruta absoluta hasta el
				// archivo original
				File archivoOriginal = new File(archivos[opcion]);
				// Pido el nuevo nombre al usuario
				System.out.println("Introduce el nuevo nombre del archivo:");
				// Creo una nueva variable de tipo Buffered para recoger el nuevo nombre que
				// asigne el usuario
				BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
				// Guardo el nuevo nombre que introduce el usuario
				String nuevoNombre = br2.readLine();

				// Compruebo que el nombre sea valido con el condicional if/else if/else
				if (nuevoNombre.matches(".*[/\\\\:*?\"<>|].*")) { // Compruebo si tiene caracteres invalidos
					System.out.println("El nuevo nombre contiene caracteres inválidos.");
				} else if (nuevoNombre.length() > 255) { // Compruebo que no supera la logitud maxima
					System.out.println("El nuevo nombre supera el limite de caracteres validos.");
				} else if (nuevoNombre.isEmpty()) {// Compruebo si no a introducido ningun nombre el metodo isEmpy()
													// verifica si es una cadena vacia
					System.out.println("No has introducido ningun nombre");
				} else {
					// Creo una variable de tipo File para guardar la ruta absoluta del archivo con
					// el nuevo nombre
					File nuevoArchivo = new File(carpeta, nuevoNombre);
					// Con el metodo renameTo() renombro el archivo pasandole el nuevo nombre
					archivoOriginal.renameTo(nuevoArchivo);
					// Informo al usuario que la operacion a salido correctamente
					System.out.println("La operacion se ha realizado correctamente.");
					// Cambio la variable control para salir del bucle
					control = true;
				}
				// Controlo e informo si ocurre algun fallo
			} catch (IOException e) {
				System.out.println("Ocurrió un error al borrar el archivo: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("Debes introducir un valor numérico");
			}

		} while (!control); // Mientras control no sea true el bucle seguira iterando

	}

	/**
	 * Funcion que remplaza un caracter por otro dentro de un archivo seleccionado
	 * por el usuario
	 */
	public static void remplazarCaracteres() {

		// Creo una variable de control para utilizarla como condicion en el bucle do
		// while
		boolean control = false;

		// Creo un bucle do while para poder mostrar el menu hasta que la operacion
		// salga con exito
		do {
			// Creo un array donde introduzco el metodo listarArchivos() que me devolvera el
			// array con las rutas absolutas de los archivos
			// y ejecutara el metodo listarArchivos()
			String[] archivos = listarArchivos();
			// Pido al usuario que archivo desea editar por consola
			System.out.println("Introduce el número de archivo que deseas editar:");
			// Utilizo la clase BufferedReader para leer los datos que introduzca el usuario
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			try {
				// Guardo el valor introducido por el usuario
				String opcionUsuario = br.readLine();
				// Parseo el valor de String a int
				int opcion = (int) Integer.parseInt(opcionUsuario) - 1;
				
				if (opcion < 1 || opcion > archivos.length) {
					System.out.println("El número de archivo seleccionado no existe, por favor vuelva a seleccionar un archivo:");
					continue;
				}
				
				// Creo una variable de tipo File a la que le asigno el archivo elegido por el
				// usuario
				File archivo = new File(archivos[opcion]);
				
				// Compruebo si el archivo existe
				if (comprobarExisteArchivo(archivo) == false) {
					continue;
				}
				
				// Creo una variable de tipo RandomAccessFile para poder editar el archivo
				// pasando le por parametro
				// la posicion del archivo dentro del array y modo de acceso, en este caso de
				// lectura y escritura
				RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
				
				// Creo la variable en la que guardare el caracter original
				String caracter;
				// Con un bucle do while controlo que el usuario solo introduzca un caracter
				do {
					// Pido al usuario que caracter desea remplazar
					System.out.println("Indica que caracter deseas remplazar:");
					// Guardo el caracter introducido
					caracter = br.readLine();
				} while (caracter.length() != 1); // Si el caracter no es igual a 1, se lo vuelvo a pedir
				
				// Creo una variable en la que guardare el nuevo caracter
				String nuevoCaracter;
				// Con un bucle do while controlo que el usuario solo introduzca un caracter
				do {
					// Pido al usuario el nuevo caracter
					System.out.println("Introduce el nuevo caracter :");
					// Guardo el caracter introducido
					nuevoCaracter = br.readLine();
				} while (nuevoCaracter.length() != 1); // Si el caracter no es igual a 1, se lo vuelvo a pedir
				

				// Muevo el puntero a la primera posicion (0) del archivo
				raf.seek(0);

				// Creo una variable para controlar si el archivo se modifica o no
				boolean modificado = false;

				// El bucle while iterara mientras la posicion del puntero del
				// archivo(raf.getFilePointer()) sea menor que la longitud
				// del archivo (raf.length())
				while (raf.getFilePointer() < raf.length()) {
					// Leo el primer byte con readByte() del archivo y lo guardo
					// seguira leyendo en las siguientes iteraciones
					byte b = raf.readByte();
					// Compruevo si el primer byte (convirtiendolo a char) leido es igual al
					// caracter que sedesea remplarzar con el metodo
					// charAt() de la clase String pasandole la primera posicion, en este caso solo
					// hay una al ser un solo caracter
					if ((char) b == caracter.charAt(0)) {
						// Retrocedo un byte para sobrescribir el caracter con getFilePinter() - 1
						raf.seek(raf.getFilePointer() - 1);
						// Escribo el nuevo caracter con writeBytes() pasando le por parametro a la
						// variable nuevoCaracter
						raf.writeBytes(nuevoCaracter);
						// Cambio a true la variable modificado para poder informar al usuario fuera del
						// bucle
						modificado = true;

					}

				}
				
				// Cierro el archivo y libero los recursos del sistema
				raf.close();
				
				// Si la variable modificado es true
				if (modificado == true) {
					// Informo al usuario por consola que la operacion se a realizado
					System.out.println("El caracte " + caracter + " a sido remplazado por " + nuevoCaracter + ".");
					// Cambio la variable control a true para que finalice el bucle do while
					control = true;
				} else { // Si no, informo al usuario que le caracter no existe
					System.out.println("El caracter introducido no existe.");
				}

				// Informo al usuario de que ocurrio un error
			} catch (IOException e) {
				System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
				// Controlo e informo si el usuario introduce una letra en vez de un numnero al
				// seleccionar el archivo
			} catch (NumberFormatException e) {
				System.out.println("Debes introducir un valor numérico valido al seleccionar el archivo.");
			}

		} while (!control); // Mientras la variable control no sea true el bucle seguira iterando

	}
	
	/**
	 * Funcion que comprueba si existe un archivo y si es un archivo realmente 
	 * 
	 * @param archivo -> Direccion de archivo guardado en una variable de tipo file
	 * @return -> Retorna verdadero o falso dependiendo si existe el archivo y realmente es un archivo
	 */
	public static boolean comprobarExisteArchivo(File archivo) {
		// Compruebo si existe el archivo
		boolean existeArchivo = archivo.exists();
		// Compruebo si es un archivo
		boolean esUnArchivo = archivo.isFile();
		
		if (existeArchivo == true && esUnArchivo == true) {
			System.out.println("Archivo seleccionado correctamente.");
		} else {
			System.out.println("El archivo seleccionado no existe.");
			listarArchivos();
		}
		
		return esUnArchivo;
	}
	

}
