package com.jdbcExample.jdbcExample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountContext
{
	public int createPlayer(Account account) throws Exception {
		int c = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) 
		{
			String sql = "INSERT INTO login.account(id, email, password) VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account.getId());
			preparedStatement.setString(2, account.getEmail());
			preparedStatement.setString(3, account.getPassword());

			c = preparedStatement.executeUpdate();

		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new Exception("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}

	public int updatePlayerContact(Account account) throws Exception  {
		int c = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) 
		{
			String sql = "update login.account set id = ?, email = ?, password = ? where id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account.getId());
			preparedStatement.setString(2, account.getEmail());
			preparedStatement.setString(3, account.getPassword());
			preparedStatement.setInt(4, account.getId());

			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e); // take off this line when in production
			throw new Exception("Internal error occured.. Kindly contact SYSADMIN");
		}
		return c;
	}

	public void deletePlayer(Account account) throws Exception 
	{
	//	int c = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()) 
		{
			String sql = "delete from login.account where id = ?;";//write here the delete query
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account.getId());
			
			 preparedStatement.executeUpdate();

		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new Exception("Internal error occured.. Kindly contact SYSADMIN");
		}
	//	return c;

	}

	public Account getAccountById(int id) throws Exception 
	{
		Account account = null;
		try (Connection connection = PostgresSqlConnection.getConnection()) 
		{
			String sql = "select  id, email, password from login.account where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
			{
				account = new Account(id, resultSet.getString("email"), resultSet.getString("password"));
			}
			else 
			{
				throw new Exception("Invalid ID!!!... No matching records found for the ID = "+id);
			}
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new Exception("Internal error occured.. Kindly contact SYSADMIN");
		}
		return account;
	}
}
