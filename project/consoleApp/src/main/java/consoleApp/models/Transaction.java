package consoleApp.models;

import java.util.Date;

public class Transaction 
{
	private int id;
	private double amount;
	private Date timestamp;
	
	public Transaction(int id, double amount, Date timestamp) 
	{
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public double getAmount() 
	{
		return amount;
	}

	public void setAmount(double amount) 
	{
		this.amount = amount;
	}

	public Date getTimestamp() 
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp) 
	{
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString()
	{
		return "Id: " + id + " Amount: $" + amount + " Date: " + timestamp + " ";
	}
}
