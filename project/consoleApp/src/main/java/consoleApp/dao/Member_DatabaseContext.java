package consoleApp.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import consoleApp.Util.QueryUtilities;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class Member_DatabaseContext 
{
	public int createMember(Account account, double initialBalance) throws DataAccessException 
	{
		int result = 0;
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setInt(1, account.getId());
			preparedStatement.setDouble(2, initialBalance);
				
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
		return result;
	}
	
	public Member login(Account account) throws DataAccessException
	{
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.LOGIN_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account.getId());

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next())
			{
				Member result = new Member(resultSet.getInt("id"), 
						resultSet.getInt("accountid"),
						resultSet.getDouble("balance"),
						resultSet.getBoolean("is_approved"));
				if (resultSet.wasNull())
					result.setIsApproved(null);
				return result;
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
	
	//TODO: change array list to list interface, have to upgrade project to java 8
	public ArrayList<Member> getPendingMembers() throws DataAccessException
	{
		ArrayList<Member> result = new ArrayList<Member>();
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.PENDING_ACCOUNTS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				result.add(new Member(resultSet.getInt("id"), 
						resultSet.getInt("accountid"),
						resultSet.getDouble("balance"),
						resultSet.getBoolean("is_approved")));
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
		return result;
	}

	public int approveMember(Member member) throws DataAccessException {
		int result = 0;
		
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.APPROVE_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, member.getId());

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
		return result;
	}
	
	public int rejectMember(Member member) throws DataAccessException {
		int result = 0;
		
		
		try 
		{
			Connection connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.REJECT_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, member.getId());

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
		return result;
	}
}
