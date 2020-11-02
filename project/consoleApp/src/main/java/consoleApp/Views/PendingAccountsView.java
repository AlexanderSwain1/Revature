package consoleApp.Views;

import java.util.ArrayList;

import consoleApp.Program;
import consoleApp.Util.ViewUtilities;
import consoleApp.dao.Account_DatabaseContext;
import consoleApp.dao.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class PendingAccountsView implements View
{
	private Account model;
	
	public PendingAccountsView(Account model)
	{
		this.model = model;
	}

	public View navigate() 
	{
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		//change to List interface, require upgrade to Java 8
		ArrayList<Member> pendingMembers = new ArrayList<Member>();
		try 
		{
			pendingMembers = memberDAO.getPendingMembers();
		} 
		catch (DataAccessException e1) 
		{
			System.out.println(e1.getMessage());
		}
		
		System.out.print("Logged in as lexxas@live.com.\n");
		
		if (pendingMembers.size() > 0)
			for (int i = 0; i < pendingMembers.size(); i++)
			{
				Account account = null;
				try 
				{
				account = accountDAO.getAccount(pendingMembers.get(i));
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
				System.out.println(i + ") " + account.getEmail());
			}
		else
		{
			System.out.println("There are no new accounts");
			return new EmployeeAccountView(model);
		}
		
		System.out.println(pendingMembers.size() + ") Cancel");
		
		System.out.print("Please Choose an Option: ");
		int response = Integer.parseInt(Program.consoleScanner.next().trim());//fix
		

		if (response >= 0 && response < pendingMembers.size())
			return new MemberApprovalView(model, pendingMembers.get(response));
		
		return new EmployeeAccountView(model);
	}
}
