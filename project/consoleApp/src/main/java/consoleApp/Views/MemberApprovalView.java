package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class MemberApprovalView extends View
{
	private Account employeeAccount;
	private Member model;
	
	public MemberApprovalView(Account employeeAccount, Member model)
	{
		this.model = model;
		this.employeeAccount = employeeAccount;
	}
	
	public View navigate() 
	{
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		Account account = null;
		try 
		{
			account = accountDAO.getAccount(model);
		}
		catch (Exception e)
		{
			log.info("An error has occured. Please try again later.\n");
			log.error(e.getMessage());
		}
		
		log.info("\n--------------------------------------------\n" + 
				"Approve or reject new user: " + account.getEmail() + "\n" + 
				"1) Approve\n" + 
				"2) Reject\n" + 
				"3) Cancel\n");
		
		int response = ViewUtilities.getIntResponse(scanner);
		
		switch (response)
		{
			case 1:
				try 
				{
					memberDAO.approveMember(model);
				} 
				catch (DataAccessException e) 
				{
					log.info("An error has occured when approving member. Please try again.\n");
					log.error(e.getMessage());
				}
				return new EmployeeAccountView(employeeAccount);
				
			case 2:
				try 
				{
					memberDAO.rejectMember(model);
				} 
				catch (DataAccessException e) 
				{
					log.info("An error has occured when rejecting member. Please try again.\n");
					log.error(e.getMessage());
				}
				return new EmployeeAccountView(employeeAccount);
				
			case 3:
				return new PendingAccountsView(employeeAccount);
				
			default:
				ViewUtilities.showInvalidInputMessage();
				return new PendingAccountsView(employeeAccount);
		}
	}
}
