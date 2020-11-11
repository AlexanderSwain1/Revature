package consoleApp.Test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import consoleApp.models.Account;
import consoleApp.models.Deposit;
import consoleApp.models.Member;
import consoleApp.models.Transaction;
import consoleApp.models.Transfer;
import consoleApp.models.Withdrawal;

//Testing the getter and setter methods
public class ModelTest 
{
	private static Account account;
	private static Deposit deposit;
	private static Member member;
	private static Transaction transaction;
	private static Transfer transfer;
	private static Withdrawal withdrawal;
	
	@BeforeAll
	public static void Initialize()
	{
		account = new Account(0, "", "", Account.Role.Customer);
		deposit = new Deposit(0, 0, new Date(), 0);
		member = new Member(0, 0, 0, false);
		transaction = new Transaction(0, 0, new Date());
		transfer = new Transfer(0, 0, new Date(), 0, 0, false);
		withdrawal = new Withdrawal(0, 0, new Date(), 0);
	}
	
	@Test
	public void AccountTest1()
	{
		account.setId(50);
		assertTrue(account.getId() == 50);
	}
	
	@Test
	public void AccountTest2()
	{
		account.setEmail("person@live.com");
		assertTrue(account.getEmail() == "person@live.com");
	}
	
	@Test
	public void DepositTest1()
	{
		deposit.setId(50);
		assertTrue(deposit.getId() == 50);
	}
	
	@Test
	public void DepositTest2()
	{
		deposit.setAmount(500.00);
		assertTrue(deposit.getAmount() == 500.00);
	}
	
	@Test
	public void MemberTest1()
	{
		member.setId(50);
		assertTrue(member.getId() == 50);
	}
	
	@Test
	public void MemberTest2()
	{
		member.setBalance(500.00);
		assertTrue(member.getBalance() == 500.00);
	}
	
	@Test
	public void TransactionTest1()
	{
		transaction.setId(50);
		assertTrue(transaction.getId() == 50);
	}
	
	@Test
	public void TransactionTest2()
	{
		transaction.setAmount(500.00);
		assertTrue(transaction.getAmount() == 500.00);
	}
	
	@Test
	public void TransferTest1()
	{
		transfer.setId(50);
		assertTrue(transfer.getId() == 50);
	}
	
	@Test
	public void TransferTest2()
	{
		transfer.setAmount(500.00);
		assertTrue(transfer.getAmount() == 500.00);
	}
	
	@Test
	public void WithdrawalTest1()
	{
		withdrawal.setId(50);
		assertTrue(withdrawal.getId() == 50);
	}
	
	@Test
	public void WithdrawalTest2()
	{
		withdrawal.setAmount(500.00);
		assertTrue(withdrawal.getAmount() == 500.00);
	}
}
