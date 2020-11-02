package consoleApp.Views;

import consoleApp.Program;
import consoleApp.dao.Account_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;

public class CreateEmployeeAccountView implements View
{
	public View navigate() 
	{
		System.out.print("Enter your email: ");
		String email = Program.consoleScanner.next().trim();//fix
			
		System.out.print("Enter your password: ");
		String password = Program.consoleScanner.next().trim();//fix
		
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		
		try 
		{
			accountDAO.createAccount(new Account(0, email, password, Account.Role.Employee));
		} 
		catch (DataAccessException e) 
		{
			System.out.println(e);
		}
		return new MainView();
	}
}
