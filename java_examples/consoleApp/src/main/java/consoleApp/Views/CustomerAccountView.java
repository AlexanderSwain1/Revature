package consoleApp.Views;

import consoleApp.Program;
import consoleApp.Util.ViewUtilities;
import consoleApp.models.Account;

public class CustomerAccountView implements View
{
	private Account model;
	
	public CustomerAccountView(Account account)
	{
		model = account;
	}
	
	public View navigate() 
	{
		System.out.print("Logged in as " + model.getEmail() + ".\n" +
					"1) View Balance.\n" + 
					"2) Withdraw.\n" + 
					"3) Deposit.\n" + 
					"4) Exit.\n" +
					"How may I help you?: ");
			
		String response = Program.consoleScanner.next().trim();//fix
			
		switch (Integer.parseInt(response))
		{
			case 1:
				return this;
			case 2:
				return this;
			case 3:
				return this;
			case 4:
				return null;
			default:
				ViewUtilities.showInvalidInputMessage();
				return this;
		}
	}
}
