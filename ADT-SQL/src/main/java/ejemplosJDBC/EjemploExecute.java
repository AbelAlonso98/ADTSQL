package ejemplosJDBC;

import java.sql.*;

public class EjemploExecute {

	public static void main(String[] args) {
		try {
		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
		String sql = "SELECT * FROM departamentos";
//		String sql = "UPDATE departamentos SET dnombre = LOWER(dnombre)";
		Statement sentencia = conexion.createStatement();
		boolean valor = sentencia.execute(sql);
		
		if(valor) {
			ResultSet rs = sentencia.getResultSet();
			while(rs.next())
				System.out.printf("%d, %s, %s%n", rs.getInt(1), rs.getString(2), rs.getString(3));
			rs.close();
		}
		else {
			int f = sentencia.getUpdateCount();
			System.out.printf("Filas afectadas: %d%n", f);
		}
		sentencia.close();
		conexion.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
