package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class MemberView extends View
{
	
	private Account account;
	private Member member;
	
	public MemberView(Account employee, Member member)
	{
		this.account = employee;
		this.member = member;
	}
	
	public View navigate()
	{
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		
		Account memberAccount = null;
		try 
		{
			memberAccount = accountDAO.getAccount(member);
		} 
		catch (DataAccessException e) 
		{
			log.info("Member account doesn't exists.\n");
			log.error(e.getMessage());
			if (account.getRole() == Account.Role.Employee)
				return new EmployeeAccountView(account);
			else
				return new CustomerAccountView(account);
		}
		
		log.info("\n-----------------Member View-----------------\n" + 
				"Viewing " + memberAccount.getEmail() +"'s account\n" +
				"Balance: $" + member.getBalance() + "\n" + 
				"1) Exit.\n");
		
		int response = ViewUtilities.getIntResponse(scanner);
		
		switch (response)
		{
			case 1:
				if (account.getRole() == Account.Role.Employee)
					return new EmployeeAccountView(account);
				else
					return new CustomerAccountView(account);
			default:
				ViewUtilities.showInvalidInputMessage();
				return this;
		}
	}
}
