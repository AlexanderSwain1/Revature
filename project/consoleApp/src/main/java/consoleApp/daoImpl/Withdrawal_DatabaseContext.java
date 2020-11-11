package consoleApp.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import consoleApp.Util.QueryUtilities;
import consoleApp.dao.WithdrawalDAO;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Member;
import consoleApp.models.Withdrawal;

public class Withdrawal_DatabaseContext implements WithdrawalDAO
{
	private static Logger log = Logger.getLogger(Withdrawal_DatabaseContext.class);
	
	@Override
	public List<Withdrawal> getWithdrawals() throws DataAccessException
	{
		Connection connection = null;
		List<Withdrawal> result = new ArrayList<Withdrawal>();
		
		try
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.GET_WITHDRAWALS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
				
			while (resultSet.next())
			{
				result.add(new Withdrawal(resultSet.getInt("id"), 
						resultSet.getDouble("amount"),
						resultSet.getDate("timestamp"),
						resultSet.getInt("member_id")));
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
	public int createWithdrawal(Withdrawal withdrawal) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_WITHDRAWAL;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setInt(1, withdrawal.getMemberId());
			preparedStatement.setDouble(2, withdrawal.getAmount());
			preparedStatement.setDate(3, new Date(withdrawal.getTimestamp().getTime()));

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
		//don't need the id for the current feature set
		//withdrawal.setId(getAccount(account.getEmail()).getId());
		return result;
	}
	
	@Override
	public void makeWithdrawal(Withdrawal withdrawal) throws DataAccessException, IllegalArgumentException
	{
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		Member member = memberDAO.getMember(withdrawal.getMemberId());
		
		if (withdrawal.getAmount() <= 0)
			throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
		if (withdrawal.getAmount() > member.getBalance())
			throw new IllegalArgumentException("Insufficient funds");//TODO: insufficient funds exception
		
		createWithdrawal(withdrawal);
		member.setBalance(member.getBalance() - withdrawal.getAmount());
		memberDAO.setBalance(member);
	}
}