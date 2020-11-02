package consoleApp.models;

public class Member 
{
	private int id;
	private int accountId;
	private double balance;
	private Boolean isApproved;
	
	public Member(int id, int accountId, double balance, Boolean isApproved)
	{
		this.id = id;
		this.accountId = accountId;
		this.balance = balance;
		this.isApproved = isApproved;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int value)
	{
		id = value;
	}
	
	public int getAccountId()
	{
		return accountId;
	}
	
	public void setAccountId(int value)
	{
		accountId = value;
	}
	
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double value)
	{
		balance = value;
	}
	
	public Boolean getIsApproved()
	{
		return isApproved;
	}
	
	public void setIsApproved(Boolean value)
	{
		isApproved = value;
	}
	
	
}
