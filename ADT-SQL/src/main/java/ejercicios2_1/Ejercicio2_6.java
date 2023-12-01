package ejercicios2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2_6 {
	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "AbelAlonso", "AbelAlonso");
			String query = "SELECT apellidos, oficio, salario FROM empleados" + " WHERE dept_no = 120";
			Statement sentencia = conexion.createStatement();
			boolean valor = sentencia.execute(query);
			if (valor) { 
				ResultSet rs = sentencia.getResultSet();
				while (rs.next())
					System.out.printf("Fila %d: %s, %s, %d %n", rs.getRow(), rs.getString(1), rs.getString(2),
							rs.getInt(3), rs.getString(3));
			}

		} catch (SQLException e) {
			System.err.println("Error de SQL:");
			e.printStackTrace();
		}
	}

}
