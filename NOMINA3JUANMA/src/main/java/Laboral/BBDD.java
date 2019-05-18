package Laboral;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BBDD {

	final String URL = "jdbc:mysql://localhost:3306/examen";
	final String USER = "root";
	final String PASS = "root";
	Connection conn;
	
	public BBDD() throws SQLException, ClassNotFoundException {
		conn = DriverManager.getConnection(URL, USER, PASS);
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public void updateAnyos() throws SQLException {
		String query = "update Empleado set anyos = anyos + 1 where dni = '32000031R'";
		Statement st =  this.conn.createStatement();
		st.executeUpdate(query);
		
	}
	
	public void crearCampoSalario() throws SQLException {
		String query = "alter table Empleado add salario integer(10)";
		Statement st =  this.conn.createStatement();
		st.executeUpdate(query);
	}
	
	public void altaEmpleado(Empleado empleado) throws SQLException {
		
		
		String query = "insert into Empleado values(?,?,?,?)";
		
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setInt(2, empleado.anyos);
		ps.setInt(1, empleado.getCategoria());
		ps.setInt(4, Nomina.sueldo(empleado));
		ps.setString(3, empleado.dni);
		ps.execute();
		
	}
	
	public void altaEmpleado(File file) throws FileNotFoundException, SQLException {
		
		String query = "insert into Empleado values(?,?,?,?)";
		PreparedStatement ps = this.conn.prepareStatement(query);
		Scanner sc = new Scanner(file);
		String registro = sc.nextLine();
		String[] campo = registro.split(",");
		while(sc.hasNext()) {
			ps.setInt(1, Integer.parseInt(campo[0]));
			ps.setInt(2, Integer.parseInt(campo[1]));
			ps.setInt(4, Integer.parseInt(campo[3]));
			ps.setString(3, campo[2]);
			ps.execute();
		}
		
	}
	
	public void guardaDatosEnFichero(File file) throws SQLException, IOException {
		String query = "select * from Empleado";
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		FileWriter fw = new FileWriter(file);
		while(rs.next()) {
			fw.write(rs.getInt(1) + "," + rs.getInt(2) + "," + rs.getString(3) + "," + rs.getInt(4) + "\n");
		}
		fw.close();
	}
	
	
	public ResultSet mostrarDatos() throws SQLException {
		String query = "select nombre, Persona.dni, sexo, categoria, anyos from Persona left join Empleado on Persona.dni = Empleado.dni ";
		Statement st = this.conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		return rs;
	}
	
	public ResultSet mostrarSalarioEmpleado(String dni) throws SQLException {
		String query = "select salario from Empleado where dni = ?";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(1, dni);
		ResultSet rs = ps.executeQuery();
		
		return rs;
		
	}
	
	public void actualizaDatos(String dni, int categoria, int anyos) throws SQLException, DatosNoCorrectosException {
		
		String query = "update Empleado set categoria = ?, anyos = ?, salario = ? where dni = ?";
		
		
		Empleado empleado = new Empleado("null", dni, 'X', categoria, anyos);
		
		int sueldo = Nomina.sueldo(empleado);
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setInt(1, categoria);
		ps.setInt(2, anyos);
		ps.setInt(3, sueldo);
		ps.setString(4, dni);
		
		ps.executeUpdate();
		
	}
	
		

}
