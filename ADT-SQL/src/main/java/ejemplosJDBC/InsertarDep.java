package ejemplosJDBC;

import java.sql.*;

public class InsertarDep {

	public static void main(String[] args) {
		try {
			if (args.length != 3)
				System.err.println("Introduce 3 argumentos: Numero, nombre y localizacion de departamento.");
			else {
				Connection conexion = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
				int dep = Integer.valueOf(args[0]);
				String dnombre = args[1];
				String loc = args[2];
				String sql = String.format("INSERT INTO departamentos VALUES (%d, '%s', '%s')", dep, dnombre, loc);
				System.out.println(sql);
				Statement sentencia = conexion.createStatement();
				int filas = sentencia.executeUpdate(sql);
				System.out.printf("Filas afectadas: %d%n", filas);
				sentencia.close();
				conexion.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Error en el formato de los argumentos:");
			System.err.println("(1) Numero: ha de ser un número entero");
			System.err.println("(2) Nombre: ha de ser un string");
			System.err.println("(3) Localizacion: ha de ser un string");
		}
	}
}
