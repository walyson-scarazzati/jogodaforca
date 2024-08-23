package pt.jogodaforca.conf;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe implementa conexão banco de dados
 * @author Walyson 
 * @version 1.0
 * @since   2019-12-05
 */
public class Connect {
	public static void main(String[] args) {
		Connect obj_DB_Connection = new Connect();
		Connection connection = null;
		connection = obj_DB_Connection.get_connection();
		System.out.println(connection);
	}

	public Connection get_connection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogodaforca?useSSL=false", "root", "123456");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

}
