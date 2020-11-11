package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class LoginView extends View
{
	public View navigate() 
	{
		
		log.info("Enter your email: ");
		String email = scanner.next().trim();
			
		log.info("Enter your password: ");
		String password = scanner.next().trim();
		
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		try 
		{
			Account account = accountDAO.login(email, password);
			if (account != null)//figure out error handling here
			{
				if (account.getRole() == Account.Role.Customer)
				{
					Member member = memberDAO.getMember(account);
					if (member != null)
					{
						if (member.getIsApproved() == null)
						{
							log.info("Account is not approved by an employee.\n");
							return new MainView();
						}
						else if (member.getIsApproved())
							return new CustomerAccountView(account);
						else if (!member.getIsApproved())//fix
						{
							log.info("Your account has been rejected. Deposits are non-refundable.\n");
							return new MainView();
						}
					}
					else
						log.info("An internal error has occured.\n");
				}
				else
					return new EmployeeAccountView(account);
			}

			ViewUtilities.showInvalidUserCredentialMessage();
		} 
		catch (DataAccessException e) 
		{
			log.info("An error has occured. Please try again later.\n");
			log.error(e.getMessage());
		}
		
		return new MainView();
	}
}
