package consoleApp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import consoleApp.Util.QueryUtilities;
import consoleApp.dao.MemberDAO;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class Member_DatabaseContext implements MemberDAO
{
	private static Logger log = Logger.getLogger(Member_DatabaseContext.class);
	
	@Override
	public int createMember(Account account, double initialBalance) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setInt(1, account.getId());
			preparedStatement.setDouble(2, initialBalance);
				
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
		return result;
	}
	
	@Override
	public Member getMember(int id) throws DataAccessException
	{
		Connection connection = null;
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.LOGIN_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

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
	public Member getMember(Account account) throws DataAccessException
	{
		Connection connection = null;
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.LOGIN_MEMBER_WITH_ACCOUNTID;
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
	public List<Member> getPendingMembers() throws DataAccessException
	{
		Connection connection = null;
		List<Member> result = new ArrayList<Member>();
		
		try 
		{
			connection = DatabaseConnection.getConnection();
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
		return result;
	}

	@Override
	public int setBalance(Member member) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.SET_BALANCE_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, member.getBalance());
			preparedStatement.setInt(2, member.getId());

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
		return result;
	}
	
	@Override
	public int approveMember(Member member) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.APPROVE_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, member.getId());

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
		return result;
	}
	
	@Override
	public int rejectMember(Member member) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.REJECT_MEMBER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, member.getId());

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
		return result;
	}
}
