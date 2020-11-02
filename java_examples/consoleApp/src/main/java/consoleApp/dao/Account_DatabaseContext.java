package consoleApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import consoleApp.Util.QueryUtilities;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class Account_DatabaseContext 
{
	//@Override
	public int createAccount(Account account) throws DataAccessException 
	{
		int result = 0;
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(3,  account.getRole().ordinal());

			result = preparedStatement.executeUpdate();

		} 
		catch (ClassNotFoundException e)
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		account.setId(getAccount(account.getEmail()).getId());
		return result;
	}
	
	//@Override
	public Account getAccount(String email) throws DataAccessException 
	{
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.GET_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setString(1, email);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
			{
				return new Account(resultSet.getInt("id"), 
						resultSet.getString("email"),
						resultSet.getString("password"), 
						Account.Role.values()[resultSet.getInt("role")]);
			}

		} 
		catch (ClassNotFoundException e)
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		return null;
	}
	
	public Account login(String username, String password) throws DataAccessException
	{
		int result = 0;
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.LOGIN_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
			{
				return new Account(resultSet.getInt("id"), 
						resultSet.getString("email"),
						resultSet.getString("password"), 
						Account.Role.values()[resultSet.getInt("role")]);
			}
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		return null;
	}
	public Account getAccount(Member member) throws DataAccessException
	{
		int result = 0;
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.GET_ACCOUNT_WITH_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, member.getAccountId());

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
			{
				return new Account(resultSet.getInt("id"), 
						resultSet.getString("email"),
						resultSet.getString("password"), 
						Account.Role.values()[resultSet.getInt("role")]);
			}
		} 
		catch (ClassNotFoundException e)
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			System.out.println(e); // take off this line when in production
			throw new DataAccessException("A failed to execute query on database.");
		}
		return null;
	}
}
