package ejemplosJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej2ConexionListarDepartSQLite {

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Tarde\\BBDD_ADT\\ejemploSQLite.db");
		String qry = "SELECT * FROM departamentos";
		Statement stm = con.createStatement();
		boolean val = stm.execute(qry);

		if (val) {
			ResultSet r = stm.getResultSet();
			while (r.next()) {
				System.out.printf("Fila %d: %d, %s, %s %n", r.getRow(), r.getInt(1), r.getString(2), r.getString(3));
			}
		}

	}

}
