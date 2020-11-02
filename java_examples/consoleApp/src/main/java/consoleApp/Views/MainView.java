package consoleApp.Views;

import consoleApp.Program;//fix
import consoleApp.Util.ViewUtilities;

public class MainView implements View
{

	public View navigate() 
	{
		System.out.print("Welcome to Bank™.\n" +
					"1) Login.\n" + 
					"2) Create a Customer Account.\n" + 
					"3) Create an Employee Account.\n" + 
					"4) Exit.\n" +
					"How may I help you?: ");
			
		String response = Program.consoleScanner.next().trim();//fix
			
		switch (Integer.parseInt(response))
		{
			case 1:
				return new LoginView();
			case 2:
				return new CreateCustomerAccountView();
			case 3:
				return new CreateEmployeeAccountView();
			case 4:
				return null;
			default:
				ViewUtilities.showInvalidInputMessage();
				return this;
		}
	}
}
