package ejercicios2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class Ejercicio2_11 {

	public static void main(String[] args) {
		String dnombre;
		try {
			if (args.length != 1)
				System.err.println("HAY QUE INTRODUCIR UN ARGUMENTO: NUMDEP");
			else {
				Connection conexion = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
				// En caso de que emp_no no sea un numero, se trata mas abajo.
				int dept_no = Integer.valueOf(args[0]);
				// =========Comprobaciones=========
				// Existe el departamento
				Statement sentencia = conexion.createStatement();
				String sql = String.format("SELECT COUNT(*), dnombre FROM DEPARTAMENTOS WHERE DEPT_NO = %s", dept_no);
				ResultSet rs = sentencia.executeQuery(sql);
				rs.next();
				if (rs.getInt(1) == 0) {
					System.err.printf("No existe el departamento: %d%n", dept_no);
					System.exit(0);
				}

				// Saco el nombre del departamento de la query anterior.
				dnombre = rs.getString(2);
				System.out.println("============================================");
				System.out.printf("DEPARTAMENTO: %d ==> %s%n", dept_no, dnombre);
				System.out.println("============================================");

				// Cambio la query por una prepared y busco lo que me interesa
				sql = "SELECT apellidos, oficio, salario FROM empleados" + " WHERE dept_no = ?";
				PreparedStatement sentencia2 = conexion.prepareStatement(sql);
				sentencia2.setInt(1, dept_no);
				rs = sentencia2.executeQuery();
				int count = 0;
				// Recorro el resultset imprimiendo los resultados
				while (rs.next()) {
					count++;
					System.out.printf("%s, %s, %.02f%n", rs.getString(1), rs.getString(2), rs.getFloat(3));
				}
				if (count == 0) {
					System.out.printf("Departamento %d no tiene empleados%n", dept_no);
					System.exit(0);
				}
				System.out.println("============================================");

				// Uso otra query para calcular el salario medio y cuantos trabajadores hay
				sql = "SELECT AVG(salario), COUNT(*) FROM empleados WHERE dept_no= ?";
				sentencia2 = conexion.prepareStatement(sql);
				sentencia2.setInt(1, dept_no);
				rs = sentencia2.executeQuery();
				rs.next(); // solo uso un next puesto que se que AVG y COUNT solo devuelven una linea
				float avgSalario = rs.getFloat(1);
				DecimalFormat formato = new DecimalFormat("##,###.00");
				System.out.printf("Salario medio: %s%n", formato.format(avgSalario)); // Imprimo usando el decimal
																						// format
				System.out.printf("Numero de trabajadores: %d%n", rs.getInt(2));
				sentencia.close();
				conexion.close();
			}
		} catch (SQLException e) {
			System.err.println("Error de SQL:");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// Si algun argumento que habia de ser un numero no lo es, se recoge aqui.
			System.err.println("===\tFormato incorrecto\t\t===");
			System.err.println("===\tEl num dep ha de ser un numero\t===");
		}

	}

}
