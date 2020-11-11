package consoleApp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import consoleApp.Program;
import consoleApp.Util.QueryUtilities;
import consoleApp.dao.AccountDAO;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class Account_DatabaseContext implements AccountDAO
{
	private static Logger log = Logger.getLogger(Account_DatabaseContext.class);
	
	@Override
	public int createAccount(Account account) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try
		{
			connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(3,  account.getRole().ordinal());

			result = preparedStatement.executeUpdate();

		} 
		catch (ClassNotFoundException e)
		{
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		finally
		{
			try 
			{
				if (connection != null)
					connection.close();
			} 
			catch (SQLException e)
			{
				log.error(e.getMessage());
				throw new DataAccessException("Failed to close connection.");
			}
		}
		account.setId(getAccount(account.getEmail()).getId());
		return result;
	}
	
	@Override
	public Account getAccount(String email) throws DataAccessException 
	{
		Connection connection = null;
		try 
		{
			connection = DatabaseConnection.getConnection();
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
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		finally
		{
			try 
			{
				if (connection != null)
					connection.close();
			} 
			catch (SQLException e)
			{
				log.error(e.getMessage());
				throw new DataAccessException("Failed to close connection.");
			}
		}
		return null;
	}
	
	@Override
	public Account login(String username, String password) throws DataAccessException
	{
		int result = 0;
		Connection connection = null;
		
		try
		{
			connection = DatabaseConnection.getConnection();
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
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		finally
		{
			try 
			{
				if (connection != null)
					connection.close();
			} 
			catch (SQLException e)
			{
				log.error(e.getMessage());
				throw new DataAccessException("Failed to close connection.");
			}
		}
		return null;
	}
	
	@Override
	public Account getAccount(Member member) throws DataAccessException
	{
		int result = 0;
		Connection connection = null;
		
		try
		{
			connection = DatabaseConnection.getConnection();
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
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		catch (SQLException e) 
		{
			log.error(e.getMessage());
			throw new DataAccessException("A failed to execute query on database.");
		}
		finally
		{
			try 
			{
				if (connection != null)
					connection.close();
			} 
			catch (SQLException e)
			{
				log.error(e.getMessage());
				throw new DataAccessException("Failed to close connection.");
			}
		}
		return null;
	}
}
