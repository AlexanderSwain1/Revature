package consoleApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import consoleApp.Util.QueryUtilities;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public interface MemberDAO
{
	int createMember(Account account, double initialBalance) throws DataAccessException ;
	
	Member getMember(int id) throws DataAccessException ;
	
	Member getMember(Account account) throws DataAccessException ;
	
	List<Member> getPendingMembers() throws DataAccessException ;

	int setBalance(Member member) throws DataAccessException ;
	
	int approveMember(Member member) throws DataAccessException ;
	
	int rejectMember(Member member) throws DataAccessException ;
}
