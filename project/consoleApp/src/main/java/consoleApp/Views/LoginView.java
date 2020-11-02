package consoleApp.Views;

import consoleApp.Program;
import consoleApp.Util.ViewUtilities;
import consoleApp.dao.Account_DatabaseContext;
import consoleApp.dao.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class LoginView implements View
{
	public View navigate() 
	{
		System.out.print("Enter your email: ");
		String email = Program.consoleScanner.next().trim();//fix
			
		System.out.print("Enter your password: ");
		String password = Program.consoleScanner.next().trim();//fix
		
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		try 
		{
			Account account = accountDAO.login(email, password);
			if (account != null)//figure out error handling here
			{
				if (account.getRole() == Account.Role.Customer)
				{
					Member member = memberDAO.login(account);
					if (member != null)
					{
						if (member.getIsApproved() == null)
						{
							System.out.println("Account is not approved by an employee.");
							return new MainView();
						}
						else if (member.getIsApproved())
							return new CustomerAccountView(account);
						else if (!member.getIsApproved())//fix
						{
							System.out.println("Your account has been rejected. Deposits are non-refundable.");
							return new MainView();
						}
					}
					else
						System.out.println("An internal error has occured.");
				}
				else
					return new EmployeeAccountView(account);
			}

			ViewUtilities.showInvalidUserCredentialMessage();
		} 
		catch (DataAccessException e) 
		{
			System.out.println(e);
		}
		
		return new MainView();
	}
}
