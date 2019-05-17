package Laboral;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
			//Creamos el fichero empleados y leemos de el cada linea(registro)
			
			File file = new File("empleados.txt");
			Scanner sc = new Scanner(file);
			registro1 = sc.nextLine();
			String[] campo1 = registro1.split(",");
			
			
			//Creamos el empleado con cada uno de los campos del registro
			empleado1 = new Empleado(campo1[0], campo1[1], campo1[2].charAt(0), Integer.parseInt(campo1[3]),Integer.parseInt(campo1[4]));
			
			registro2 = sc.nextLine();
			String[] campo2 = registro2.split(",");

			empleado2 = new Empleado(campo2[0], campo2[1].charAt(0), campo2[2]);
			
			

			System.out.println(escribe(empleado1, empleado2));
			
			
			//Creamos el archivo binario e insertamos con BufferedWriter
			
			File fileBin = new File("salario.dat");
			FileWriter fw = new FileWriter(fileBin);
			BufferedWriter bf = new BufferedWriter(fw);
			bf.write(campo1[1] + ", " + Nomina.sueldo(empleado1) + "€" + "\n" + campo2[2] + ", " + Nomina.sueldo(empleado2) + "€");
			bf.close();
			fw.close();
			
			
			BBDD crud = new BBDD();
			
			// aumenta en 1 el año del segundo empleado
			
			crud.updateAnyos();
			
			//comento crearCampoSalario porque da error cuando ejecutas despues de crearlo por primera vez
//			crud.crearCampoSalario();
		
			Empleado empleado3 = new Empleado("Juanma", "5435445A", 'M', 2, 3);
			
			//damos de alta al empleado3
			crud.altaEmpleado(empleado3);
			
			//creamos fichero y introducimos los registros a la bbdd
			File fileEmpleados = new File("empleadosNuevos.txt");
			crud.altaEmpleado(fileEmpleados);
			
			//creamos un fichero de backup y guardamos todos los registros de la bbdd en el fichero
			File datosEmpleados = new File("DatosEmpleados.txt");
			
			crud.guardaDatosEnFichero(datosEmpleados);
			
			
			
//			System.out.println(escribe(empleado1, empleado2));
			
			
		}catch(SQLException sql) {
			sql.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
}
