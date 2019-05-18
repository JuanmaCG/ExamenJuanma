package Laboral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CalculaNominas {

	
	
	private static String escribe(Empleado empleado1, Empleado empleado2) {
		
		Nomina nomina = new Nomina();
		
		return empleado1.imprime() + " Cobra: " + nomina.sueldo(empleado1) + "€ \n" + empleado2.imprime() + " Cobra: " + nomina.sueldo(empleado2) + "€";
	
	}
	
	public static void main(String[] args) throws DatosNoCorrectosException, IOException{
		String registro1;
		String registro2;
		Empleado empleado1;
		Empleado empleado2;
		
		try{
//			//Creamos el fichero empleados y leemos de el cada linea(registro)
//			
//			File file = new File("empleados.txt");
//			Scanner sc = new Scanner(file);
//			registro1 = sc.nextLine();
//			String[] campo1 = registro1.split(",");
//			
//			
//			//Creamos el empleado con cada uno de los campos del registro
//			empleado1 = new Empleado(campo1[0], campo1[1], campo1[2].charAt(0), Integer.parseInt(campo1[3]),Integer.parseInt(campo1[4]));
//			
//			registro2 = sc.nextLine();
//			String[] campo2 = registro2.split(",");
//
//			empleado2 = new Empleado(campo2[0], campo2[1].charAt(0), campo2[2]);
//			
//			
//
//			System.out.println(escribe(empleado1, empleado2));
//			
//			
//			//Creamos el archivo binario e insertamos con BufferedWriter
//			
//			File fileBin = new File("salario.dat");
//			FileWriter fw = new FileWriter(fileBin);
//			BufferedWriter bf = new BufferedWriter(fw);
//			bf.write(campo1[1] + ", " + Nomina.sueldo(empleado1) + "€" + "\n" + campo2[2] + ", " + Nomina.sueldo(empleado2) + "€");
//			bf.close();
//			fw.close();
//			
//			
			BBDD crud = new BBDD();
//			
//			// aumenta en 1 el año del segundo empleado
//			
//			crud.updateAnyos();
//			
//			//comento crearCampoSalario porque da error cuando ejecutas despues de crearlo por primera vez
////			crud.crearCampoSalario();
//		
//			Empleado empleado3 = new Empleado("Juanma", "5435445A", 'M', 2, 3);
//			
//			//damos de alta al empleado3
//			crud.altaEmpleado(empleado3);
//			
//			//creamos fichero y introducimos los registros a la bbdd
//			File fileEmpleados = new File("empleadosNuevos.txt");
//			crud.altaEmpleado(fileEmpleados);
//			
//			//creamos un fichero de backup y guardamos todos los registros de la bbdd en el fichero
//			File datosEmpleados = new File("DatosEmpleados.txt");
//			
//			crud.guardaDatosEnFichero(datosEmpleados);
//			
			
			
//			System.out.println(escribe(empleado1, empleado2));
			
			Scanner sc = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			boolean finalizar = false;
			int opcion = 0;
			
			
			while(!finalizar) {
				System.out.println("Introduce una opcion");
				System.out.println("1- Mostrar los registros de la base de datos");
				System.out.println("2- Mostrar salario de un Empleado");
				System.out.println("3- Modificar los datos de los Empleados");
				
				opcion = sc.nextInt();
				
				switch(opcion) {
				case 1:
					ResultSet datos = crud.mostrarDatos();
					
					while(datos.next()) {
						System.out.println(datos.getString(1) + ", " + datos.getString(2) + ", " + datos.getString(3) + ", " + datos.getInt(4) + ", " + datos.getInt(5));
					}
					
					break;
				
				case 2:
					System.out.println("Introduzca un dni");
					String dni = sc2.nextLine();
					ResultSet salario = crud.mostrarSalarioEmpleado(dni);
					
					while(salario.next()) {
						System.out.println("El salario del empleado con dni " + dni + " es " + salario.getInt(1));
					}
					break;
					
				case 3:
					int acabado = 0;
					do {
						
						System.out.println("Introduce el dni del Empleado para actualizar o pulse 0 para finalizar");
						dni = sc2.nextLine();
						if(!dni.equals("0")) {
							System.out.println("Introduce la nueva categoria");
							int categoria = sc.nextInt();
							System.out.println("Introduce los nuevos anos");
							int anyos = sc.nextInt();
							
							crud.actualizaDatos(dni, categoria, anyos);
						}else {
							acabado = 1;
						}
						
						
						
						
					}while(acabado == 0);
					
					break;
				}
				
				
			}
			
			
		}catch(SQLException sql) {
			sql.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
}
