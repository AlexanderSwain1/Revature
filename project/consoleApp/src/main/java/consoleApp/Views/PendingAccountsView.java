package consoleApp.Views;

import java.util.ArrayList;
import java.util.List;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class PendingAccountsView extends View
{
	
	private Account model;
	
	public PendingAccountsView(Account model)
	{
		this.model = model;
	}

	public View navigate() 
	{
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		//change to List interface, require upgrade to Java 8
		List<Member> pendingMembers = new ArrayList<Member>();
		try 
		{
			pendingMembers = memberDAO.getPendingMembers();
		} 
		catch (DataAccessException e1) 
		{
			log.info("An error has occured. Please try again later.\n");
			log.error(e1.getMessage());
		}
		
		log.info("\n-------------Pending Accounts--------------\n");
		
		if (pendingMembers.size() > 0)
			for (int i = 0; i < pendingMembers.size(); i++)
			{
				Account account = null;
				try 
				{
					account = accountDAO.getAccount(pendingMembers.get(i));
					if (account != null)
						log.info((i + 1) + ") " + account.getEmail() + "\n");
					else//should be unreachable
					{
						log.info((i + 1) + ") [Ghost Account]" + "\n");
						log.error("[Ghost Account] There's a record with an id of 0 somewhere");
					}
				}
				catch (DataAccessException e)
				{
					log.info((i + 1) + ") " + "Error retreiving pending account." + "\n");
					log.error(e.getMessage());
				}
			}
		else
		{
			log.info("There are no new members (customer accounts)\n");
			return new EmployeeAccountView(model);
		}
		
		log.info((pendingMembers.size() + 1) + ") Cancel" + "\n");
		
		int response = ViewUtilities.getIntResponse(scanner) - 1;

		if (response >= 0 && response < pendingMembers.size())
			return new MemberApprovalView(model, pendingMembers.get(response));
		else if (response == pendingMembers.size())
			return new EmployeeAccountView(model);
		else
		{
			ViewUtilities.showInvalidInputMessage();
			return new EmployeeAccountView(model);
		}
	}
}
