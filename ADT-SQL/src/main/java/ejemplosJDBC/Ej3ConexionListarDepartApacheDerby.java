package ejemplosJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ej3ConexionListarDepartApacheDerby {

	public static void main(String[] args) throws SQLException {
		String dbURL = "jdbc:derby:C:\\Users\\Tarde\\BBDD_ADT\\ejemplo;create=false";
		Connection con = DriverManager.getConnection(dbURL);
		if (con != null) {
			String qry = "SELECT * FROM departamentos";
			Statement stm = con.createStatement();
			boolean val = stm.execute(qry);

			if (val) {
				ResultSet r = stm.getResultSet();
				while (r.next()) {
					System.out.printf("%d, %s, %s %n", r.getInt(1), r.getString(2), r.getString(3));
				}
			}

		}

	}

}