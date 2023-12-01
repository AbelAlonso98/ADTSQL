package ejemplosJDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemploDatabaseMetadata {

	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
			DatabaseMetaData dbmd = conexion.getMetaData();
			ResultSet resul = null;
			
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url  = dbmd.getURL();
			String usuario = dbmd.getUserName();
			
			System.out.println("INFORMACION SOBRE LA BASE DE DATOS");
			System.out.println("==================================");
			System.out.printf("Nombre:  %s %n", nombre);
			System.out.printf("Driver:  %s %n", driver);
			System.out.printf("URL:  %s %n", url);
			System.out.printf("Usuario:  %s %n", usuario);
			
			resul = dbmd.getTables("ejemplo1", "ejemplo1", null, null);
			while(resul.next()) {
				String catalogo = resul.getString(1);
				String tabla = resul.getString(3);
				String tipo = resul.getString(4);
				System.out.printf("%s - Catalogo: %s, nombre: %s %n", tipo, catalogo, tabla);
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
