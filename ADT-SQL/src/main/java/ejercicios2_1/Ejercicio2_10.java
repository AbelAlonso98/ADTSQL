package ejercicios2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Ejercicio2_10 {

	public static void main(String[] args) {
		try {
			if (args.length != 7)
				System.err.println(
						"HAY QUE INTRODUCIR 7 ARGUMENTOS:\n\t(EMP_NO, APELLIDO, OFICIO, DIR, SALARIO, COMISION Y DEPT_NO)");
			else {
				Connection conexion = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/ejemplo1? UseSSL=true & serverTimezone =UTC", "root", "4327");
				
				// En caso de que emp_no, salario, comision o dept_no no sean un numero salta una
				// NumberFormatException que es tratada más abajo.
				int emp_no = Integer.valueOf(args[0]);
				String apellido = args[1];
				String oficio = args[2];
				String dir = args[3];
				int salario = Integer.valueOf(args[4]);
				int comision = Integer.valueOf(args[5]);
				int dept_no = Integer.valueOf(args[6]);


				// =========Comprobaciones=========
				// Salario > 0
				if (salario < 0) {
					System.err.println("El salario ha de ser superior a 0€.");
					System.exit(0);
				}
				// Existe el departamento
				Statement sentencia = conexion.createStatement();
				String sql = String.format("SELECT COUNT(*) FROM DEPARTAMENTOS WHERE DEPT_NO = %s", dept_no);
				ResultSet rs = sentencia.executeQuery(sql);
				rs.next();
				if (rs.getInt(1) == 0) {
					System.err.printf("No existe el departamento: %d%n", dept_no);
					System.exit(0);
				}
				// Existe el director
				sql = String.format("SELECT COUNT(*) FROM EMPLEADOS WHERE DIR = %s", dir);
				rs = sentencia.executeQuery(sql);
				rs.next();
				if (rs.getInt(1) == 0) {
					System.err.printf("No existe el director: %d%n", dir);
					System.exit(0);
				}
				// Apellido != null
				if (apellido == null || apellido.equals("")) {
					System.err.println("El apellido no puede ser null.");
					System.exit(0);
				}
				// Oficio != null
				if (oficio == null || oficio.equals("")) {
					System.err.println("El oficio no puede ser null.");
					System.exit(0);
				}
				// Ya existe el empleado
				sql = String.format("SELECT COUNT(*) FROM EMPLEADOS WHERE EMP_NO = %s", emp_no);
				rs = sentencia.executeQuery(sql);
				rs.next();
				if (rs.getInt(1) > 0) {
					System.err.printf("Ya existe el empleado: %d%n", emp_no);
					System.exit(0);
				}

				// ========= Si todas las comprobaciones son correctas =========
				sql = String.format("INSERT INTO empleados VALUES (%d, '%s', '%s', '%s', '%s', '%d', '%d', '%d')",
						emp_no, apellido, oficio, dir, LocalDate.now().toString(), salario, comision, dept_no);
				System.out.println("Parametros correctos, ejecutando:");
				System.out.println(sql);

				sentencia.executeUpdate(sql);
				sentencia.close();
				conexion.close();
			}
		} catch (SQLException e) {
			System.err.println("Error de SQL:");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// Si algun argumento que habia de ser un numero no lo es, se recoge aqui.
			System.err.println("===Formato numero incorrecto===");
		}

	}

}
