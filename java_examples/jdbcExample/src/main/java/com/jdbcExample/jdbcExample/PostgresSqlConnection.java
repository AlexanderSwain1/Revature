package com.jdbcExample.jdbcExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresSqlConnection 
{
	private static Connection connection;
	
	private PostgresSqlConnection() {}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url="jdbc:postgresql://localhost:5432/postgres";			
		String username="postgres";
		String password="Gokuisntgoku25!";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
}
