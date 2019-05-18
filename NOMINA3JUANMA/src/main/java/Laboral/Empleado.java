package Laboral;

public class Empleado extends Persona{
	
	private int categoria;
	public int anyos;
	
	public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
		super(nombre,dni,sexo);
		this.categoria = categoria;
		if(anyos < 0) {
			throw new DatosNoCorrectosException();
		}else {
			this.anyos = anyos;
		}
		
	}
	
	
	
	public Empleado (String nombre, char sexo, String dni) {
		super(nombre, dni, sexo);
		this.categoria = 1;
		this.anyos = 0;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if(categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException();
		}else {
			this.categoria = categoria;
		}
		
	}
	
	public void incrAnyo() {
		this.anyos += 1;
	}

	
	public String imprime() {
		return "Empleado [categoria=" + categoria + ", anyos=" + anyos + ", nombre=" + nombre + ", dni=" + dni
				+ ", sexo=" + sexo + "]";
	}


	
	
	
}
