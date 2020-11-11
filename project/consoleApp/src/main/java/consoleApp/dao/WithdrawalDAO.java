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
import consoleApp.models.Member;
import consoleApp.models.Withdrawal;

public interface WithdrawalDAO
{
	List<Withdrawal> getWithdrawals() throws DataAccessException ;
	
	int createWithdrawal(Withdrawal withdrawal) throws DataAccessException ;
	
	void makeWithdrawal(Withdrawal withdrawal) throws DataAccessException ;
}