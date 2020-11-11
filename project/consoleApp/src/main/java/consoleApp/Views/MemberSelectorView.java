package consoleApp.Views;

import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class MemberSelectorView extends View
{
	
	private Account viewer;
	
	public MemberSelectorView(Account employee)
	{
		this.viewer = employee;
	}
	
	public View navigate() 
	{
		log.info("Enter the email of the customer: ");
		String email = scanner.next().trim();
		
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		Account account = null;
		try 
		{
			account = accountDAO.getAccount(email);
		} 
		catch (DataAccessException e) 
		{
			log.info("An error has occured. Please try again later." + "\n");
			log.error(e.getMessage());
			if (viewer.getRole() == Account.Role.Employee)
				return new EmployeeAccountView(viewer);
			else
				return new CustomerAccountView(viewer);
		}
		
		if (account != null)
		{
			if (account.getRole() == Account.Role.Customer)
			{
				Member member = null;
				
				try 
				{
					member = memberDAO.getMember(account);
				} 
				catch (DataAccessException e) 
				{
					log.info("An error has occured. Please try again later." + "\n");
					log.error(e.getMessage());
				}
				
				return new MemberView(viewer, member);
			}
			
			else
			{
				log.info("Email is not a customer account it is an employee account." + "\n");
				if (viewer.getRole() == Account.Role.Employee)
					return new EmployeeAccountView(viewer);
				else
					return new CustomerAccountView(viewer);
			}
		}
		else
		{
			log.info("Account cannot be found." + "\n");
			if (viewer.getRole() == Account.Role.Employee)
				return new EmployeeAccountView(viewer);
			else
				return new CustomerAccountView(viewer);
		}
		

	}
}
