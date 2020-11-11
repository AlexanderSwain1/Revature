package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;

public class CreateCustomerAccountView extends View
{
	public View navigate() 
	{
		log.info("\n-------------Create Customer-------------\n");
		log.info("Enter your email: ");
		String email = scanner.next().trim();
		
		log.info("Enter your password: ");
		String password = scanner.next().trim();
		
		log.info("Accounts cannot be created with an empty balance.\n");
		double balance = ViewUtilities.getDoubleResponse(scanner, "How much do you want to deposit: $");
		
		Account newAccount = new Account(0, email, password, Account.Role.Customer);
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		try 
		{
			accountDAO.createAccount(newAccount);
			try 
			{
				memberDAO.createMember(newAccount, balance);
				log.info("Your new account is awaiting approval.\n");
			} 
			catch (DataAccessException e) 
			{
				log.info("An error has occured. Please try again" + "\n");
				log.error(e.getMessage());
			}
		} 
		catch (DataAccessException e) 
		{
			log.info("Account's email is already in use. Account registration failed." + "\n");
			log.error(e.getMessage());
		}
		
		return new MainView();
	}
}
