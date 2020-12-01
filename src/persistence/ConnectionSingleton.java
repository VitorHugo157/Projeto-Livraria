package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
	private static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static final String HOSTNAME = "localhost";
	private static final String DBNAME = "PROJETO_LIVRARIA";
	private static final String USER = "vitorhugo";
	private static final String PASS = "Vitor@123";
	
	private static ConnectionSingleton instancia = null;
	private Connection con;
	
	public static ConnectionSingleton getInstance() { 
		if (instancia == null) { 
			instancia = new ConnectionSingleton();
		}
		return instancia;
	}
	
	private ConnectionSingleton() { 
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() { 
		try {
			if (con == null || con.isClosed() || !con.isValid(500)) { 
				con = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", 
						HOSTNAME, DBNAME, USER, PASS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
