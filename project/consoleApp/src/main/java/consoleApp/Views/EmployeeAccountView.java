package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.models.Account;

public class EmployeeAccountView extends View
{
	
	private Account model;
	
	public EmployeeAccountView(Account account)
	{
		model = account;
	}
	
	public View navigate() 
	{
		log.info("\n----------------Employee----------------\n" + 
				"Logged in as " + model.getEmail() +"\n" +
					"1) View Balance of Other Account.\n" + 
					"2) Pending Accounts\n" + 
					"3) View all transactions.\n" + 
					"4) Log out.\n");
			
		int response = ViewUtilities.getIntResponse(scanner);
		
		switch (response)
		{
			case 1:
				return new MemberSelectorView(model);
			case 2:
				return new PendingAccountsView(model);
			case 3:
				return new TransactionsView(model);
			case 4:
				return new MainView();
			default:
				ViewUtilities.showInvalidInputMessage();
				return this;
		}
	}
}
