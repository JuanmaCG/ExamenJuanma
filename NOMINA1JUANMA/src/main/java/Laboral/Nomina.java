package Laboral;


public class Nomina implements Biblioteca{
	
	private static final int SUELDO_BASE[] = {50000, 70000,90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

	public int sueldo(Empleado empleado) {
		// TODO Auto-generated method stub
		
		int sueldo;
		
		sueldo = (SUELDO_BASE[empleado.getCategoria() -1]) + (5000 * empleado.anyos);
		
		return sueldo;
	}
	
}
