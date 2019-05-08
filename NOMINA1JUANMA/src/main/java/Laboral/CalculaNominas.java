package Laboral;

public class CalculaNominas {

	
	
	private static String escribe(Empleado empleado1, Empleado empleado2) {
		
		Nomina nomina = new Nomina();
		
		return empleado1.imprime() + " Cobra: " + nomina.sueldo(empleado1) + "€ \n" + empleado2.imprime() + " Cobra: " + nomina.sueldo(empleado2) + "€";
	
	}
	
	public static void main(String[] args) throws DatosNoCorrectosException{
		try{
			Empleado empleado1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado empleado2 = new Empleado("Ada Lovealace", 'F', "32000031R");
			
			System.out.println(escribe(empleado1, empleado2));
			
			empleado2.incrAnyo();
			empleado1.setCategoria(9);
			
			System.out.println(escribe(empleado1, empleado2));
		}catch(DatosNoCorrectosException datos) {
			System.out.println("Datos no correctos");
		}
		
	}

	
	
	
}
