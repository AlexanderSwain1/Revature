package consoleApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import consoleApp.Program;
import consoleApp.Util.QueryUtilities;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public interface AccountDAO
{
	int createAccount(Account account) throws DataAccessException ;
	
	Account getAccount(String email) throws DataAccessException ;
	
	Account login(String username, String password) throws DataAccessException ;

	Account getAccount(Member member) throws DataAccessException ;
}
