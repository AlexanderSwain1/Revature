package consoleApp.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection 
{
	public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	public static final String DRIVER = "org.postgresql.Driver";
	
	private DatabaseConnection() 
	{
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName(DRIVER);
		String username = "postgres";
		String password = "Gokuisntgoku25!";
		return DriverManager.getConnection(URL, username, password);
	}

}
