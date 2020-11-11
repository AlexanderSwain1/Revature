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
import consoleApp.dao.TransferDAO;
import consoleApp.exception.DataAccessException;
import consoleApp.exception.TransferReceiverException;
import consoleApp.models.Deposit;
import consoleApp.models.Member;
import consoleApp.models.Transfer;
import consoleApp.models.Withdrawal;

public class Transfer_DatabaseContext implements TransferDAO
{
	private static Logger log = Logger.getLogger(Transfer_DatabaseContext.class);
	
	@Override
	public List<Transfer> getTransfers() throws DataAccessException
	{
		Connection connection = null;
		List<Transfer> result = new ArrayList<Transfer>();
		
		try
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.GET_TRANSFERS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
				
			while (resultSet.next())
			{
				Transfer toAdd = new Transfer(resultSet.getInt("id"), 
						resultSet.getDouble("amount"),
						resultSet.getDate("timestamp"),
						resultSet.getInt("sender_member_id"), 
						resultSet.getInt("receiver_member_id"),
						resultSet.getBoolean("is_approved"));
				if (resultSet.wasNull())
					toAdd.setIsApproved(null);
				result.add(toAdd);
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
	public List<Transfer> getPendingTransfers(int receiverMemberId) throws DataAccessException
	{
		Connection connection = null;
		List<Transfer> result = new ArrayList<Transfer>();
		
		try
		{
			connection = DatabaseConnection.getConnection();
			String sql = QueryUtilities.GET_PENDING_TRANSFERS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, receiverMemberId);
			
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
				
			while (resultSet.next())
			{
				Transfer toAdd = new Transfer(resultSet.getInt("id"), 
						resultSet.getDouble("amount"),
						resultSet.getDate("timestamp"),
						resultSet.getInt("sender_member_id"), 
						resultSet.getInt("receiver_member_id"),
						resultSet.getBoolean("is_approved"));
				if (resultSet.wasNull())
					toAdd.setIsApproved(null);
				result.add(toAdd);
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
	public int createTransfer(Transfer transfer) throws DataAccessException, TransferReceiverException, IllegalArgumentException
	{
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		Member senderMember = memberDAO.getMember(transfer.getSenderMemberId());
		
		if (transfer.getSenderMemberId() == transfer.getReceiverMemberId())
			throw new TransferReceiverException("Cannot transfer money to yourself.");
		if (transfer.getAmount() <= 0)
			throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
		if (transfer.getAmount() > senderMember.getBalance())
			throw new IllegalArgumentException("Insufficient funds");//TODO: insufficient funds exception
		
		//take money out of the sender's account
		senderMember.setBalance(senderMember.getBalance() - transfer.getAmount());
		memberDAO.setBalance(senderMember);
		
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.INSERT_TRANSFER;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setInt(1, transfer.getSenderMemberId());
			preparedStatement.setInt(2, transfer.getReceiverMemberId());
			preparedStatement.setDouble(3, transfer.getAmount());
			preparedStatement.setDate(4, new Date(transfer.getTimestamp().getTime()));

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
	public int updateApproval(Transfer transfer) throws DataAccessException
	{
		int result = 0;
		Connection connection = null;
		
		try 
		{
			connection = DatabaseConnection.getConnection();
			String sqlCommand = QueryUtilities.UPDATE_APPROVAL_TRANSFER;
			PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			preparedStatement.setBoolean(1, transfer.getIsApproved());
			preparedStatement.setInt(2, transfer.getId());

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
	public void approveTransfer(Transfer transfer) throws DataAccessException, IllegalArgumentException, TransferReceiverException
	{
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		Member senderMember = memberDAO.getMember(transfer.getSenderMemberId());
		Member receiverMember = memberDAO.getMember(transfer.getReceiverMemberId());
		
		if (transfer.getSenderMemberId() == transfer.getReceiverMemberId())
			throw new TransferReceiverException("Cannot transfer money to yourself.");
		if (transfer.getAmount() <= 0)
			throw new IllegalArgumentException("Withdrawal amount must be greater than 0");
		if (transfer.getAmount() > senderMember.getBalance())
			throw new IllegalArgumentException("Insufficient funds");//TODO: insufficient funds exception
		
		transfer.setIsApproved(true);
		updateApproval(transfer);
		
		//put money in the receiver's account
		//senderMember.setBalance(senderMember.getBalance() - transfer.getAmount());
		receiverMember.setBalance(receiverMember.getBalance() + transfer.getAmount());
		memberDAO.setBalance(receiverMember);
		//memberDAO.setBalance(senderMember);
	}
	public void rejectTransfer(Transfer transfer) throws DataAccessException, TransferReceiverException, IllegalArgumentException
	{
		//put money back in the sender's account
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		Member senderMember = memberDAO.getMember(transfer.getSenderMemberId());
		senderMember.setBalance(senderMember.getBalance() + transfer.getAmount());
		memberDAO.setBalance(senderMember);
		
		//update approval
		transfer.setIsApproved(false);
		updateApproval(transfer);
	}
}
