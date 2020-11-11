package consoleApp.Util;

public class QueryUtilities 
{
	public static final String GET_ACCOUNT = "select id, email, password, role from login.account where (email = ?);";
	public static final String GET_ACCOUNT_WITH_ID = "select id, email, password, role from login.account where (id = ?);";
	public static final String INSERT_ACCOUNT = "INSERT INTO login.account(id, email, password, role) VALUES(nextval('current_account_value'),?,?,?)";
	public static final String LOGIN_ACCOUNT = "select id, email, password, role from login.account where (email = ? and password = ?);";
	public static final String PENDING_ACCOUNTS = "select id, accountid, balance, is_approved from banking.member where (is_approved is null);";
	
	public static final String INSERT_MEMBER = "INSERT INTO banking.member(id, accountid, balance, is_approved) VALUES(nextval('current_member_value'),?,?,null)";
	public static final String LOGIN_MEMBER = "select id, accountid, balance, is_approved from banking.member where (id = ?);";
	public static final String LOGIN_MEMBER_WITH_ACCOUNTID = "select id, accountid, balance, is_approved from banking.member where (accountid = ?);";
	public static final String SET_BALANCE_MEMBER = "update banking.member set balance = ? where id = ?;";
	public static final String APPROVE_MEMBER = "update banking.member set is_approved = true where id = ?;";
	public static final String REJECT_MEMBER = "update banking.member set is_approved = false where id = ?;";
	
	public static final String GET_WITHDRAWALS = "select id, member_id, amount, timestamp from banking.withdrawal;";
	public static final String INSERT_WITHDRAWAL = "INSERT INTO banking.withdrawal(id, member_id, amount, timestamp) VALUES(nextval('current_withdrawal_value'),?,?,?)";

	public static final String GET_DEPOSITS = "select id, member_id, amount, timestamp from banking.deposit;";
	public static final String INSERT_DEPOSIT = "INSERT INTO banking.deposit(id, member_id, amount, timestamp) VALUES(nextval('current_deposit_value'),?,?,?)";
	
	public static final String GET_TRANSFERS = "select id, sender_member_id, receiver_member_id, amount, timestamp, is_approved from banking.transfer;";
	public static final String GET_PENDING_TRANSFERS = "select id, sender_member_id, receiver_member_id, amount, timestamp, is_approved from banking.transfer where (is_approved is null) and (receiver_member_id = ?);";
	public static final String INSERT_TRANSFER = "INSERT INTO banking.transfer(id, sender_member_id, receiver_member_id, amount, timestamp, is_approved) VALUES(nextval('current_transfer_value'),?,?,?,?,null)";
	public static final String UPDATE_APPROVAL_TRANSFER = "update banking.transfer set is_approved = ? where id = ?;";
}