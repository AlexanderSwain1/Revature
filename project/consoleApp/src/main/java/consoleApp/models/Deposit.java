package consoleApp.models;

import java.util.Date;

public class Deposit extends Transaction
{
	private int memberId;

	public Deposit(int id, double amount, Date timestamp, int memberId) 
	{
		super(id, amount, timestamp);
		this.memberId = memberId;
	}

	public int getMemberId() 
	{
		return memberId;
	}

	public void setMemberId(int memberId) 
	{
		this.memberId = memberId;
	}
	
	@Override
	public String toString()
	{
		return "[Deposit]" + super.toString() + "Member ID : " + memberId;
	}
}
