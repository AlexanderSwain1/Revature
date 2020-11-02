package consoleApp.Views;

import consoleApp.Program;
import consoleApp.Util.ViewUtilities;
import consoleApp.dao.Account_DatabaseContext;
import consoleApp.dao.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;

public class CreateCustomerAccountView implements View
{
	public View navigate() 
	{
		System.out.print("Enter your email: ");
		String email = Program.consoleScanner.next().trim();//fix
			
		System.out.print("Enter your password: ");
		String password = Program.consoleScanner.next().trim();//fix
		
		System.out.print("Account cannot be created with an empty balance.\nHow much do you want to deposit: ");
		double balance = Double.parseDouble(Program.consoleScanner.next().trim());//fix
		
		Account newAccount = new Account(0, email, password, Account.Role.Customer);
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		try 
		{
			accountDAO.createAccount(newAccount);
		} 
		catch (DataAccessException e) 
		{
			System.out.println(e);
		}
		
		try 
		{
			memberDAO.createMember(newAccount, balance);
		} 
		catch (DataAccessException e) 
		{
			System.out.println(e);
		}
		
		return new MainView();
	}
}
