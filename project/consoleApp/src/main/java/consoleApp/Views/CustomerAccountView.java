package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;

public class CustomerAccountView extends View
{
	
	private Account model;
	
	public CustomerAccountView(Account account)
	{
		model = account;
	}
	
	public View navigate() 
	{
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		Member member = null;
		try 
		{
			member = memberDAO.getMember(model);
		}
		catch (DataAccessException e) 
		{
			log.info("An error has occured. Please try again later." + "\n");
			log.error(e.getMessage());
		}
		
		log.info("\n-----------------Member-----------------\n" + 
					"Logged in as " + model.getEmail() + ".\n" +
					"Account balance: $" + member.getBalance() + "\n" + 
					"1) Withdraw.\n" + 
					"2) Deposit.\n" + 
					"3) Transfer.\n" + 
					"4) Approve or Reject Transfer.\n" + 
					"5) View other Account.\n" +
					"6) Exit.\n");
			
		int response = ViewUtilities.getIntResponse(scanner);
			
		switch (response)
		{
			case 1:
				return new WithdrawalView(model, member);
			case 2:
				return new DepositView(model, member);
			case 3:
				return new TransferView(model, member);
			case 4:
				return new PendingTransfersView(model, member);
			case 5:
				return new MemberSelectorView(model);
			case 6:
				return new MainView();
			default:
				ViewUtilities.showInvalidInputMessage();
				return this;
		}
	}
}
