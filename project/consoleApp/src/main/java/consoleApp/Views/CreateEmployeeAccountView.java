package consoleApp.Views;

import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;

public class CreateEmployeeAccountView extends View
{
	
	public View navigate() 
	{
		log.info("\n-------------Create Employee-------------\n");
		log.info("Enter your email: ");
		String email = scanner.next().trim();
		
		log.info("Enter your password: ");
		String password = scanner.next().trim();
		
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		
		try 
		{
			accountDAO.createAccount(new Account(0, email, password, Account.Role.Employee));
			log.info("Your new account has been created.\n");
		} 
		catch (DataAccessException e) 
		{
			log.info("Account's email is already in use. Account registration failed." + "\n");
			log.error(e.getMessage());
		}
		return new MainView();
	}
}
