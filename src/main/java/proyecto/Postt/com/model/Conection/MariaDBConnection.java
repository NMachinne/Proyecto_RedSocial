package proyecto.Postt.com.model.Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnection{
	private static Connection conn = null;
	
	//Solo en fase alpha
	private final static String uri = "jdbc:mariadb://localhost:3306/postt";
	protected final static String user = "root";
	protected final static String password = "";
	
	protected MariaDBConnection() {}
	/**
	 * Método static que devuelve la conexión a la bd
	 * @return la conexión o null en caso de error
	 */
	public static Connection getConnection() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(uri,user,password);
			}catch(SQLException ex) {
				ex.printStackTrace();
				conn = null;
			}
		}
		return conn;
	}
	public static void close() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException ex) {
				
			}
		}
	}
	
}
