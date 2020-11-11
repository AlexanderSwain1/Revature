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
import consoleApp.dao.DepositDAO;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Deposit;
import consoleApp.models.Member;
import consoleApp.models.Withdrawal;

public class Deposit_DatabaseContext  implements DepositDAO
{
	private static Logger log = Logger.getLogger(Deposit_DatabaseContext.class);

	@Override
	public List<Deposit> getDeposits() throws DataAccessException
	{
		Connection connection = null;
		List<Deposit> result = new ArrayList<Deposit>();
		
		try
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.GET_DEPOSITS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
				
			while (resultSet.next())
			{
				result.add(new Deposit(resultSet.getInt("id"), 
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
	public int createDeposit(Deposit deposit) throws DataAccessException 
	{
		int result = 0;
		Connection connection = null;
		
		try 
			{
			connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_DEPOSIT;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setInt(1, deposit.getMemberId());
			preparedStatement.setDouble(2, deposit.getAmount());
			preparedStatement.setDate(3, new Date(deposit.getTimestamp().getTime()));
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
	public void MakeDeposit(Deposit deposit) throws DataAccessException, IllegalArgumentException
	{
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		Member member = memberDAO.getMember(deposit.getMemberId());
		
		if (deposit.getAmount() <= 0)
			throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
			
		createDeposit(deposit);
		member.setBalance(member.getBalance() + deposit.getAmount());
		memberDAO.setBalance(member);
	}
}
