package consoleApp.Views;

import consoleApp.Program;
import consoleApp.Util.ViewUtilities;
import consoleApp.models.Account;

public class EmployeeAccountView implements View
{
	private Account model;
	
	public EmployeeAccountView(Account account)
	{
		model = account;
	}
	
	public View navigate() 
	{
		System.out.print("Logged in as lexxas@live.com.\n" +
					"1) View Account Balance.\n" + 
					"2) Pending Accounts\n" + 
					"3) Log out.\n" + 
					"How may I help you?: ");
			
		String response = Program.consoleScanner.next().trim();//fix
			
		switch (Integer.parseInt(response))
		{
			case 1:
				return this;
			case 2:
				return new PendingAccountsView(model);
			case 3:
				return new MainView();
			default:
				ViewUtilities.showInvalidInputMessage();
				return this;
		}
	}
}
