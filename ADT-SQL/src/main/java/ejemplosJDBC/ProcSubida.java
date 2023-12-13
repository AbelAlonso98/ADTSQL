package ejemplosJDBC;

import java.sql.*;

public class ProcSubida {
	public static void main(String[] args) {
		try {
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
			String dep = args[0];
			String subida = args[1];
			String sql = "{ call subida sal (?, ?) } ";
			CallableStatement llamada = conexion.prepareCall(sql);
			llamada.setInt(1, Integer.parseInt(dep));
			llamada.setFloat(2, Float.parseFloat(subida));
			llamada.executeUpdate();
			System.out.println("Subida realizada.");
			llamada.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
