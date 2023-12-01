package ejemplosJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej1ConexionListarDepart {

	public static void main(String[] args) throws SQLException {
		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
		String query = "SELECT * FROM departamentos";
		Statement sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		boolean valor = sentencia.execute(query);

		if (valor) {
			ResultSet rs = sentencia.getResultSet();
			rs.last();
			System.out.println("Número de registros obtenidos: " + rs.getRow());
			rs.beforeFirst();

			while (rs.next())
				System.out.printf("Fila %d: %d, %s, %s %n", rs.getRow(), rs.getInt(1), rs.getString(2),
						rs.getString(3));
		}
	}
}
