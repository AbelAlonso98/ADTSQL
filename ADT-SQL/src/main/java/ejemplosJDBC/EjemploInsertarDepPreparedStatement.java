package ejemplosJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EjemploInsertarDepPreparedStatement {

	public static void main(String[] args) {
		if(args.length !=3) {
			System.err.println("ARGUMENTOS ERRONEOS");
			System.exit(0);
		}
		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
			String dep = args[0];
			String dnombre = args[1];
			String loc = args[2];
			
			String sql = "INSERT INTO departamentos VALUES "+"(?, ?, ?)";
			System.out.println(sql);
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			
			sentencia.setInt(1, Integer.parseInt(dep));
			sentencia.setString(2, dnombre);
			sentencia.setString(3, loc);
			
			int filas= sentencia.executeUpdate();
			System.out.println("Filas afectadas:  " + filas);
			sentencia.close();
			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error de SQL:");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Formato de argumento incorrecto");
			e.printStackTrace();
		}

	}

}
