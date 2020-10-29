package com.jdbcExample.jdbcExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Account a = new Account(5, "the_suckage2@live.com", "password11");
        AccountContext ac = new AccountContext();
        try 
        {
			ac.deletePlayer(a);
		} 
        catch (Exception e) 
        {
        	System.out.println(e.getMessage());
		}
    }
    
    private static void old()
    {
    	System.out.println( "Hello World!" );
        
        Connection connection=null;
		try 
		{
			//Step 1 - Load/Register the Driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver Loaded Successfully");
			
			//Step 2 - Open Connection(url,username,password)
			String url="jdbc:postgresql://localhost:5432/postgres";	//edit connection -> edit driver settings		
			String username="postgres";
			String password="Gokuisntgoku25!";
			connection=DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successfull");
			
			//Step 3 - Create Statement
			Statement statement=connection.createStatement();
			String sql="select id,email,password from login.account";
			System.out.println("Statement Created");
			
			//Step 4 - Execute Query
			ResultSet rs=statement.executeQuery(sql);
			System.out.println("Query Executed");
			
			//Step 5 - Process Results
			while(rs.next()) {
				System.out.print("Id = "+rs.getInt("id"));
				System.out.print(" Email = "+rs.getString("email"));
				System.out.println(" Password = "+rs.getString("password"));
			}
			System.out.println("Results Processed");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println(e);
		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		}
		finally 
		{
			
			try 
			{
				//Step 6 - Close Connection
				if (connection != null) connection.close();
				System.out.println("Connection closed");
			} 
			catch (SQLException e) 
			{
				System.out.println(e);
			}
		}
    }
}
