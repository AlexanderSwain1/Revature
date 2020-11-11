package consoleApp.models;

import java.util.Date;

public class Transfer extends Transaction
{
	private int senderMemberId;
	private int receiverMemberId;
	private Boolean isApproved;
	
	public Transfer(int id, double amount, Date timestamp, int senderMemberId, int receiverMemberId, Boolean isApproved) 
	{
		super(id, amount, timestamp);
		this.senderMemberId = senderMemberId;
		this.receiverMemberId = receiverMemberId;
		this.isApproved = isApproved;
	}

	public int getSenderMemberId() 
	{
		return senderMemberId;
	}

	public void setSenderMemberId(int senderMemberId) 
	{
		this.senderMemberId = senderMemberId;
	}

	public int getReceiverMemberId() 
	{
		return receiverMemberId;
	}

	public void setReceiverMemberId(int receiverMemberId) 
	{
		this.receiverMemberId = receiverMemberId;
	}
	
	public Boolean getIsApproved()
	{
		return isApproved;
	}
	
	public void setIsApproved(Boolean value)
	{
		isApproved = value;
	}
	
	@Override
	public String toString()
	{
		return "[Transfer]" + super.toString() + "Sender ID: " + senderMemberId + " ReceiverId: " + receiverMemberId + " IsApproved: " + isApproved;
	}
}
