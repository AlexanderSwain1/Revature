package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;

public class MainView extends View
{

	public View navigate() 
	{
		log.info("\n-----------Account Login-----------\n" + 
					"Welcome to Bank™.\n" +
					"1) Login.\n" + 
					"2) Create a Customer Account.\n" + 
					"3) Create an Employee Account.\n" + 
					"4) Exit.\n");
			
		int response = ViewUtilities.getIntResponse(scanner);

		switch (response)
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
