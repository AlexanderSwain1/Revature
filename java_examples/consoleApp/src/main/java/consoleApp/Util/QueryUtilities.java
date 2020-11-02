package consoleApp.Util;

public class QueryUtilities 
{
	public static final String INSERT_ACCOUNT = "INSERT INTO login.account(id, email, password, role) VALUES(nextval('current_account_value'),?,?,?)";
	public static final String INSERT_MEMBER = "INSERT INTO banking.member(id, accountid, balance, is_approved) VALUES(nextval('current_member_value'),?,?,null)";
	public static final String LOGIN_ACCOUNT = "select id, email, password, role from login.account where (email = ? and password = ?);";
	public static final String GET_ACCOUNT = "select id, email, password, role from login.account where (email = ?);";
	public static final String LOGIN_MEMBER = "select id, accountid, balance, is_approved from banking.member where (accountid = ?);";
	public static final String PENDING_ACCOUNTS = "select id, accountid, balance, is_approved from banking.member where (is_approved is null);";
	public static final String GET_ACCOUNT_WITH_ID = "select id, email, password, role from login.account where (id = ?);";
	public static final String APPROVE_MEMBER = "update banking.member set is_approved = true where id = ?;";
	public static final String REJECT_MEMBER = "update banking.member set is_approved = false where id = ?;";
}