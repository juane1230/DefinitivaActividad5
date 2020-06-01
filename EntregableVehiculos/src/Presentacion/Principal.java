package Presentacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Dominio.*;

class excepcionUsuario extends Exception {
}
class excepcionPassword extends Exception {
}
class excepcionFueraRango extends Exception {
}

public class Principal { 
		// METODO MAIN
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;

		// CARGAR DATOS 

		System.out.println("Bienvenido a su administrador de vehiculos\n");
		// CARGAMOS EL METODO LOGGIN EMPLEADOS 
		LogginEmpleado();

		// MENU PRINCIPAL
		do {
			try {
				System.out.println("\nMenú");
				System.out.println("1.  Mostrar todos los vehículos");
				System.out.println("2.  Buscar un vehículo");
				System.out.println("3.  Añadir un vehículo");
				System.out.println("4.  Modificar un vehículo");
				System.out.println("5.  Eliminar un vehículo");
				System.out.println("6.  Eliminar todos los vehículos");
				System.out.println("7.  Mostrar todos los extras");
				System.out.println("8.  Buscar un extra");
				System.out.println("9.  Insertar un extra");
				System.out.println("10. Modificar un extra");
				System.out.println("11. Eliminar un extra");
				System.out.println("12. Eliminar todos los extras");
				System.out.println("13. Log out");
				opcion = sc.nextInt();

				System.out.print("\n");
				// SWITCH  PARA ELEGIR METODOS A REALIZAR
				switch (opcion) {
				case 1:
					mostrarVehiculos();
					break;
				case 2:
					buscarVehiculo();
					break;
				case 3:
					añadirvehiculo();
					break;
				case 4:
					modificarVehiculo();
					break;
				case 5:
					eliminarVehiculo();
					break;
				case 6:
					eliminarTodasLosVehiculos();
					break;
				case 7:
					mostrarExtras();
					break;
				case 8:
					buscarExtra();
					break;
				case 9:
					añadirExtra();
					break;
				case 10:
					modificarExtra();
					break;
				case 11:
					eliminarUnExtras();
					break;
				case 12:
					eliminarTodosLosExtras();
					break;
				case 13:
					System.out.println("Hasta pronto");
					break;
				default:
					System.out.println("Introduce un número de 1 a 13");
				}

			} catch (InputMismatchException e) {
				System.err.println("Introduce un número");
				sc.nextLine();
			}
		} while (opcion != 13);
	}
	// METODO MOSTRAS VEHICULOS
	
	private static void mostrarVehiculos() throws ClassNotFoundException {
		boolean sinVehiculos = true;
		//CREAMOS OBJETO TIPO VEHICULO
		Vehiculo mostrarVehiculo = new Turismo();
		// ARRAYLIST VEHICULOS Y LLAMAMOS AL METODO LEER TODOS DE TURISMOS
		ArrayList<Vehiculo> Vehiculos = mostrarVehiculo.leerTodos();
		
		//RECORREMOS EL ARRAY PARA IMPRIMIR TODOS LOS TURISMOS
		for (int i = 0; i < Vehiculos.size(); i++) {
			System.out.println(Vehiculos.get(i).toString());
			sinVehiculos = false;
		}
		//CAMBIAMOS EL TIPO DE TURISMO A TIPO CAMION
		mostrarVehiculo = new Camion();
		Vehiculos = mostrarVehiculo.leerTodos();
		//RECORREMOS ARRAYLIST DE CAMIONES PARA IMPRIMIR TODOS
		for (int i = 0; i < Vehiculos.size(); i++) {
			System.out.println(Vehiculos.get(i).toString());
			sinVehiculos = false;
		}
		if (sinVehiculos) {
			System.out.println("No existen vehiculos");

		}
	}
	// METODO BUSCAR VEHCULO
	
	private static void buscarVehiculo() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Indica la matricula");
		String Matricula = sc.next();
		Vehiculo leerCamiones = new Camion();
		//LLAMAMOS AL METODO DE LEER TURIMOS PARA ENCONTRAR LA MATRICULA QUE HEMOS INSERTADO
		leerCamiones = leerCamiones.leerVehiculo(Matricula);
		Vehiculo leerTurismos = new Turismo();
		//LAMAMOS AL METODO LEER CAMIONES PARA ENCONTRAR A MATRICULA QUE HEMOS INSERTADO
		leerTurismos = leerTurismos.leerVehiculo(Matricula);
		if (leerCamiones != null) {
			System.out.println(leerCamiones.toString());
		} else if (leerTurismos != null) {
			System.out.println(leerTurismos.toString());
		} else {
			System.out.printf("No existe el vehiculo con la matricula %s\n", Matricula);
		}

	}
	// METODO AÑADIR VEHICULO
	private static void añadirvehiculo() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		boolean seguir = false;
		String Matricula = "";
		Extra newextra = new Extra();
		do {
			seguir = false;
			System.out.println("Introduzca matricula");
			Matricula = sc.next();
			
			//CREAMOS OBJETO DE TIPO VEHICULO/TURISMO
			Vehiculo existeTurismo = new Turismo();
			
			// LLAMAMOS AL METODO LEER VEHICULO DE LA CLASE TURISMOS Y LE PASAMOS MATRICULA
			existeTurismo = existeTurismo.leerVehiculo(Matricula);
			
			//CREAMOS OBJETO DE TIPO VEHICULO/CAMION
			Vehiculo existeCamion = new Camion();
			
			// LLAMAMOS AL METODO LEER VEHICULO DE LA CLASE CAMION Y LE PASAMOS MMATRICULA
			existeCamion = existeCamion.leerVehiculo(Matricula);
			
			
			if (existeTurismo != null) {
				// SI LA MATRICULA PUESTA ES IGUAL QUE LA QUE SE DESEA  SALTA MENSAJE DE MATRICULA REPETIDA
				if (existeTurismo.getMatricula().equals(Matricula)) {
					System.out.println("Matricula repetida");
					seguir = true;
				}
			}

			if (existeCamion != null) {
				// SI LA MATRICULA PUESTA ES IGUAL QUE LA QUE SE DESEA  SALTA MENSAJE DE MATRICULA REPETIDA
				if (existeCamion.getMatricula().equals(Matricula)) {
					System.out.println("Matricula repetida");
					seguir = true;
				}
			}
		} while (seguir);
		System.out.println("Introduzca marca");
		sc.nextLine();
		String marca = sc.nextLine();
		System.out.println("Introduzca modelo");
		String modelo = sc.nextLine();
		System.out.println("Introduzca color");
		String color = sc.nextLine();
		int precio = 0;
		do {
			seguir = false;
			try {
				System.out.println("Introduzca el precio");
				precio = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
		int opcion = 0;
		do {
			try {
				System.out.println("¿Es turismo o camion?\n1. Turismo\n2. Camion");
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
				sc.nextLine();
			}
		} while (opcion != 1 && opcion != 2);
		int extra = 0;
		int numpuertas = 0;
		if (opcion == 1) {

			do {
				seguir = false;
				try {
					
					//METODO MOSTRAS EXTRA
					mostrarExtras();
					System.out.println("Introduzca el extra que desee");
					extra = sc.nextInt();
					sc.nextLine();
					newextra = new Extra();
					newextra = newextra.leerExtra(extra);
					if (newextra == null) {
						seguir = true;
						System.out.println("El extra indicado no existe");

					}
					System.out.println("introduce el numero de puertas");
					numpuertas=sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);

			Vehiculo newVehiculo = new Turismo(Matricula, marca, modelo, color, precio, numpuertas, newextra);
			newVehiculo.insertarVehiculo();

		}
		double capacarga = 0;
		if (opcion == 2) {

			do {
				seguir = false;
				try {
					System.out.println("Introduzca el capacidad de carga");
					capacarga = sc.nextDouble();
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			//CREAMOS OBJETO DE TIPO CAMION PARA INSERTAR LOS DATOS POR ORDEN
			Vehiculo newVehiculo = new Camion(Matricula, marca, modelo, color, precio, capacarga);
			newVehiculo.insertarVehiculo();
		}
	}
	//METODO MODIFICAR VEHICULO
	private static void modificarVehiculo() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		
		//CREAMOS DOS OBJETOS DE TIPO CAMION Y TURISMO
		Vehiculo modTurismo = new Turismo();
		Vehiculo modCamion = new Camion();

		System.out.println("Indica la matricula");
		String Matricula = sc.next();
		
		//METODOS DE LEER DE CAMIO Y TURISMO
		modTurismo = modTurismo.leerVehiculo(Matricula);
		modCamion = modCamion.leerVehiculo(Matricula);
		
		if (modTurismo != null || modCamion != null) {
			System.out.println("¿Qué deseas modificar?");
			System.out.println("1. Matricula");
			System.out.println("2. Marca");
			System.out.println("3. Modelo");
			System.out.println("4. Color");
			System.out.println("5. Precio");
			if (modTurismo != null) {
				System.out.println("6. Extras");
				System.out.println("7. Numero de puertas");
			}
			if (modCamion != null) {
				System.out.println("6. Capacidad de carga");
			} 
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				boolean repetido = false;
				do {
					repetido = false;
					System.out.println("Introduce la nueva matricula");
					String matricula = sc.next();
					Vehiculo existeTurismo = new Turismo();
					existeTurismo = existeTurismo.leerVehiculo(matricula);
					Vehiculo existeCamion = new Camion();
					existeCamion = existeCamion.leerVehiculo(matricula);
					if (existeTurismo != null) {
						
						//SI LA MATRICULA EXISTE SALTA EL MENSAJE DE MATRICULA REPETIDA
						if (existeTurismo.getMatricula().equals(matricula)) {
							System.out.println("Matricula repetido");
							repetido = true;
						}
					} else if (existeCamion != null) {
						//SI LA MATRICULA EXISTE SALTA EL MENSAJE DE MATRICULA REPETIDA
						if (existeCamion.getMatricula().equals(matricula)) {
							System.out.println("Matricula repetido");
							repetido = true;
						}
					} else {
						//MODIFICAMOS LA MATRICULA O DE TURISMO O DEL CAMION ELEGIDO
						if (modTurismo != null) {
							modTurismo.setMatricula(matricula);
						}
						if (modCamion != null) {
							modCamion.setMatricula(matricula);
						}
					}
				} while (repetido);
				break;
			case 2:
				
				// CREAMOS DOS OBJETPS PARA MODIFICAR LA MARCA O DE CAMION O DE TURISMO
				Vehiculo existeTurismo = new Turismo();
				existeTurismo = existeTurismo.leerVehiculo(Matricula);
				Vehiculo existeCamion = new Camion();
				existeCamion = existeCamion.leerVehiculo(Matricula);
				System.out.println("Introduce la nueva marca");
				sc.nextLine();
				String marca = sc.nextLine();
				// SI ES EL TURISMOS, MODIFICAMOS LA MARCA DEL TURISMO
				if (existeTurismo != null) {
					existeTurismo.setMarca(marca);
				}
				
				//SI ES EL CAMION, MODIFICAMO LA MARCA DEL CAMION
				if (existeCamion != null) {
					existeCamion.setMarca(marca);
				}
				break;
				
			case 3:
				//CASO PARA MODIFCAR EL MODELO
				System.out.println("Introduce el nuevo modelo");
				sc.nextLine();
				String modelo = sc.nextLine();
				
				// SI ES TURISMO, MODIFICAMOS EL MODELO DEL TURISMO
				if (modTurismo != null) {
					modTurismo.setModelo(modelo);
				}
				
				// SI ES CAMION, SE MODIFICA EL CAMION
				if (modCamion != null) {
					modCamion.setModelo(modelo);
				}
				break;
			case 4:
				
				// CASO PARA MODIFICAR EL COLOR
				System.out.println("Introduce el nuevo color");
				sc.nextLine();
				String color = sc.nextLine();
				//SI ES TURISMO SE CAMBIA EL COLOR
				if (modTurismo != null) {
					modTurismo.setColor(color);
				}
				//SI ES CAMION SE CAMBIA EL COLOR
				if (modCamion != null) {
					modCamion.setColor(color);
				}
				break;
			case 5:
				boolean seguir = false;
				do {
					seguir = false;
					try {
						// CASO PARA CAMBIAR EL PRECIO
						System.out.println("Introduce el nuevo precio");
						int precio = sc.nextInt();
						
						if (modTurismo != null) {
							modTurismo.setPrecio(precio);
						}
						if (modCamion != null) {
							modCamion.setPrecio(precio);
						}

					} catch (InputMismatchException e) {
						System.err.println("Introduzce solo números");
						sc.nextLine();
						seguir = true;
					}
				} while (seguir);
				break;
			case 6:
				if (modTurismo != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							//METODO PARA MOSTRAR LOS EXTRAS
							mostrarExtras();
							System.out.println("Introduce el nuevo extra");
							int extra = sc.nextInt();
							// CREAMOS UN OBJETO DE TIPO EXTRA
							Extra modExtra = new Extra();
							modExtra = modExtra.leerExtra(extra);
							//SI NO EXISTE SALTA EL MENSAJE DE QUE NO EXISTE
							if (modExtra == null) {
								seguir = true;
								System.out.println("El extra no existe");

							} else {
								((Turismo) modTurismo).setExtra(modExtra);
								}
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
				}
				if (modCamion != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							
							System.out.println("Introduce la nueva capacidad de carga");
							double capacarga = sc.nextDouble();
							//MODIFICAMOS LA CARGA DEL CAMION
							((Camion) modCamion).setCarga(capacarga);;

						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
				}
				break;
			case 7:
				if (modTurismo != null) {
					seguir = false;
					do {
						seguir = false;
						try {
							System.out.println("Introduce el nuevo numero de puertas");
							int numpuertas = sc.nextInt();
							//MODIFICAMOS LAS PUERTAS DEL TURISMO
							((Turismo) modTurismo).setPuertas(numpuertas);

						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					
				}
				break;

			}
			// SI ES TURISMOS ACTUALIZAMOS LA MATRICULA
			if (modTurismo != null) {
				modTurismo.actualizar(Matricula);
				// SI ES CAMION ACTUALIZAMOS LA MATRICULA
			} else if (modCamion != null) {
				modCamion.actualizar(Matricula);

			}

		} else {
			System.out.printf("No existe el vehiculo con matricula %s\n", Matricula);

		}

	}
	//METODO ELIMINAR VEHICULO
	private static void eliminarVehiculo() throws ClassNotFoundException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Indica la matricula");
		String matricula = sc.next();
		// CREAMOS OBJETO DE TIPO TURISMO
		Vehiculo delTurismo = new Turismo();
		delTurismo = delTurismo.leerVehiculo(matricula);
		//CREAMOS OBJETO DE TIPO CAMION
		Vehiculo delCamion = new Camion();
		delCamion = delCamion.leerVehiculo(matricula);
		// SI ES TURISMOS SALTA EL METODO DE ELIMINAR
		if (delTurismo != null) {
			delTurismo.eliminar();
			// SI ES CAMION SALTA EL METODO DE ELIMIN
		} else if (delCamion != null) {
			delCamion.eliminar();
		} else {
			System.out.printf("No existe el vehiculo con matricula %s\n", matricula);
		}

	}
	// METODO ELMINAR TODOS LOS VEHICULOS
	private static void eliminarTodasLosVehiculos() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//CREAMOS OBEJTOS DE TIPO VEHICULO Y TIPO CAMION Y LLAMAMOS A SUS METODOS DE ELIMINAR
		Vehiculo eliminarCamiones = new Camion();
		Vehiculo eliminarTurismos = new Turismo();
		eliminarCamiones.eliminarTodo();
		eliminarTurismos.eliminarTodo();

	}
	// METODO MOSTRAR EXTRA
	private static void mostrarExtras() throws ClassNotFoundException {
		boolean sinExtras = true;
		Extra buscarExtras = new Extra();
		ArrayList<Extra> extras = buscarExtras.leerTodosExtras();
		for (int i = 0; i < extras.size(); i++) {
			System.out.println(extras.get(i).toString());
			sinExtras = false;
		}

		if (sinExtras) {
			System.out.println("No existen extras");

		}
	}
		//METODO BUSCAR EXTRA
	private static void buscarExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Indica el Id de extra");
		int identificador = sc.nextInt();
		Extra leerExtra = new Extra();
		leerExtra = leerExtra.leerExtra(identificador);

		if (leerExtra != null) {
			System.out.println(leerExtra.toString());
		} else {
			System.out.printf("No existe el extra con la identificador %s\n", identificador);
		}
	}
	//METODO AÑADIR EXTRA
	private static void añadirExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		boolean seguir = false;
		int ident = 0;
		do {
			seguir = false;
			System.out.println("Introduzca el identificador de extra");
			ident = sc.nextInt();
			Extra existeExtras = new Extra();
			existeExtras = existeExtras.leerExtra(ident);

			if (existeExtras != null) {
					// SI EL ID DE EXTRA EXISTE SALTA MENSAJE DE REPETIDO
				if (existeExtras.getId()== ident) {
					System.out.println("identificador repetido ingresa otro");
					seguir = true;
				}
			}

		} while (seguir);

		System.out.println("Introduzca la descripcion");
		sc.nextLine();
		String descExtra = sc.nextLine();
		// SE INSERTA LA DESCRIPCION
		Extra newExtra = new Extra(ident, descExtra);
		newExtra.insertar();
	}
		//MODIFICAR EXTRA
	private static void modificarExtra() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		Extra modExtra = new Extra();

		System.out.println("Indica el identificador");
		int ident = sc.nextInt();
		// CREAMOS OBJETO DE TIPO EXTRA PARA LEER
		modExtra = modExtra.leerExtra(ident);
			//PREGUNTAMOS QUE SE DESEA MODIFICAR
		if (modExtra != null) {
			System.out.println("¿Qué deseas modificar?");
			System.out.println("1. identificador");
			System.out.println("2. Descripción");
			int opcion = sc.nextInt();
			switch (opcion) {

			case 1:
				boolean repetido = false;
				do {
					repetido = false;
					System.out.println("Introduce el nuevo identificador");
					int Id = sc.nextInt();
					Extra existeExtra = new Extra();
					existeExtra = existeExtra.leerExtra(Id);

					if (existeExtra != null) {
						if (existeExtra.getId() == Id) {
							System.out.println("identificador repetido");
							repetido = true;
						}
					} else {
						modExtra.setId(Id);
					}
				} while (repetido);
				break;

			case 2:
				System.out.println("Introduce la descripcion");
				sc.nextLine();
				String descripcion = sc.nextLine();
				
				// LLAMAMOS AL METODO PARA AJUSTAR A NUEVA DESCRIPCION
				modExtra.setDescripcion(descripcion);
				break;

			}
			modExtra.actualizar(ident);
		} else {
			System.out.printf("No existe el Extra con el identificador %d\n", ident);

		}
	}
		//METODO ELIMINAR UN EXTRRA
	private static void eliminarUnExtras() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Indica el identificador");
		int ident = sc.nextInt();
		Extra delExtra = new Extra();
		delExtra = delExtra.leerExtra(ident);

		if (delExtra != null) {
			// SI EL EXTRA EXISTE LLAMA AL METODO ELIMINAR EXTRA
			delExtra.eliminar();

		} else {
			System.out.printf("No existe el extra con el identificador %d\n", ident);
		}
	}
	// METODO ELIMINAR TODOS LOS EXTRAS
	private static void eliminarTodosLosExtras() throws ClassNotFoundException {
		Extra eliminarTodosExtras = new Extra();
		
		// CREAMOS OBJETO DE TIPO EXTRA PARA LLAMAR AL METODO DE ELIMINAR TODOS LOS EXTRAS
		eliminarTodosExtras.eliminartTodosExtra();

	}
		//METODO LOGIN 
	public static void LogginEmpleado() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		boolean logincorr= false;
		while (logincorr == false) {			
		
		System.out.println("Indica el Loggin");
		String loggin = sc.next();
		System.out.println("Indica su contraseña");
		String password = sc.next();
		Empleado leerEmpleado = new Empleado();
		
		leerEmpleado = leerEmpleado.leerEmpleado(loggin, password);
		

		if (leerEmpleado != null) {
			System.out.println(leerEmpleado.toString());
			logincorr= true;
			System.out.printf("\nSe a logueado correctamente Empleado con loggin %s y con password %s \n", loggin, password);
		} else if (leerEmpleado== null) {
			System.out.printf("\nNo existe la Empleado con el loggin %s o con password %s \n", loggin, password);
		}
		}
	}

}
