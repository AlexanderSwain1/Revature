package consoleApp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import consoleApp.Util.QueryUtilities;
import consoleApp.exception.DataAccessException;
import consoleApp.exception.TransferReceiverException;
import consoleApp.models.Deposit;
import consoleApp.models.Member;
import consoleApp.models.Transfer;
import consoleApp.models.Withdrawal;

public interface TransferDAO
{
	List<Transfer> getTransfers() throws DataAccessException ;

	int createTransfer(Transfer transfer) throws DataAccessException, TransferReceiverException, IllegalArgumentException;
	
	int updateApproval(Transfer transfer) throws DataAccessException;
	
	void approveTransfer(Transfer transfer) throws DataAccessException, IllegalArgumentException, TransferReceiverException;
	
	void rejectTransfer(Transfer transfer) throws DataAccessException, TransferReceiverException, IllegalArgumentException;
	
	List<Transfer> getPendingTransfers(int receiverMemberId) throws DataAccessException;
}
