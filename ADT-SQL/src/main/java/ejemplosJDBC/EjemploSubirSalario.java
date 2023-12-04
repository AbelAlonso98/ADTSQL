package ejemplosJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploSubirSalario {

	public static void main(String[] args) {
		if(args.length !=2) {
			System.err.println("ARGUMENTOS ERRONEOS");
			System.exit(0);
		}
		String dep = args[0];
		String oficio = args[1];

		
		try {

		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
		Statement sentencia = conexion.createStatement();
		String sql = String.format("SELECT COUNT(*) FROM DEPARTAMENTOS WHERE DEPT_NO = %s", dep);
		ResultSet rs = sentencia.executeQuery(sql);
		rs.next();
		if (rs.getInt(1) == 0) {
			System.err.printf("No existe el departamento: %d%n", dep);
			System.exit(0);
		}
		sql = "SELECT apellido, salario FROM empleados WHERE dept_no = ? AND oficio = ? ORDER BY 1";
		PreparedStatement sentenciaPreparada = conexion.prepareStatement(sql);
		sentenciaPreparada.setInt(1, Integer.parseInt(dep));
		sentenciaPreparada.setString(2, oficio);
		
		rs = sentenciaPreparada.executeQuery();
		while(rs.next())
			System.out.printf("%s => %.2f %n", rs.getString("apellido"), rs.getFloat("salario"));
		rs.close();
		sentenciaPreparada.close();
		conexion.close();
		}catch (SQLException e) {
			System.err.println("Error de SQL");
			e.printStackTrace();
		}catch (NumberFormatException e) {
			System.err.println("Error de formato en argumentos.");
		}
	}

}
