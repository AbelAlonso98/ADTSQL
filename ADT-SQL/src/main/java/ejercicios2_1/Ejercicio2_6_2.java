package ejercicios2_1;

import java.sql.*;

public class Ejercicio2_6_2 {
	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "AbelAlonso", "AbelAlonso");
			String query = "SELECT apellidos, salario, departamentos.dnombre FROM empleados"
					+ " JOIN departamentos ON (departamentos.dept_no = empleados.dept_no)"
					+ " WHERE salario=(SELECT max(salario) FROM empleados);";
			Statement sentencia = conexion.createStatement();
			boolean valor = sentencia.execute(query);
			if (valor) {
				ResultSet rs = sentencia.getResultSet();
				while (rs.next())
					System.out.printf("%s, %d, %s %n", rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(3));
			}
		} catch (SQLException e) {
			System.err.println("Error de SQL:");
			e.printStackTrace();
		}
	}
}
