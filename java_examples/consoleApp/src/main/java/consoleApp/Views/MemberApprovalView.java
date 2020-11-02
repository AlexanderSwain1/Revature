package consoleApp.Views;

import java.util.ArrayList;

import consoleApp.Program;
import consoleApp.dao.Account_DatabaseContext;
import consoleApp.dao.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class MemberApprovalView implements View
{
	private Account employeeAccount;
	private Member model;
	
	public MemberApprovalView(Account employeeAccount, Member model)
	{
		this.model = model;
		this.employeeAccount = employeeAccount;
	}
	
	public View navigate() 
	{
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		Account account = null;
		try 
		{
			account = accountDAO.getAccount(model);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		System.out.print("Approve or reject new user: " + account.getEmail() + "\n" + 
				"1) Approve\n" + 
				"2) Reject\n");
		
		System.out.print("Please Choose an Option: ");
		int response = Integer.parseInt(Program.consoleScanner.next().trim());//fix
		
		try
		{
			if (response == 1)
				memberDAO.approveMember(model);
			else if (response == 2)
				memberDAO.rejectMember(model);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return new EmployeeAccountView(employeeAccount);
	}
}
