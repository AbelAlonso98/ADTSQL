package ejemplosJDBC;

import java.sql.*;

public class EjemploResultsetMetadata {

	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM departamentos");
			ResultSetMetaData rsmd = rs.getMetaData();
			int nColumnas = rsmd.getColumnCount();
			System.out.printf("Numero de columnas recuperadas: %d%n", nColumnas);
			for (int i = 1; i <= nColumnas; i++) {
				System.out.printf("Columna: %d%n", i);
				System.out.printf("Nombre: %s%n", rsmd.getColumnName(i));
				System.out.printf("Tipo %s%n", rsmd.getColumnTypeName(i));
				System.out.printf("\tPuede ser nula?: %s%n", rsmd.isNullable(i) == 0 ? "SI" : "NO");
				System.out.printf("\tMaximo ancho de la columna: %d%n", rsmd.getColumnDisplaySize(i));
			}
			sentencia.close();
			rs.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
