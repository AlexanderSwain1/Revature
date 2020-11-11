package consoleApp.Views;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.daoImpl.Transfer_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.exception.TransferReceiverException;
import consoleApp.models.Account;
import consoleApp.models.Member;
import consoleApp.models.Transfer;

public class TransferApprovalView extends View
{
	private Account account;
	private Member member;
	private Transfer model;
	
	public TransferApprovalView(Account account, Member member, Transfer model)
	{
		this.account = account;
		this.member = member;
		this.model = model;
	}

	@Override
	public View navigate() 
	{
		Transfer_DatabaseContext transferDAO = new Transfer_DatabaseContext();
		
		log.info("\n---------------Transfer Approval---------------\n" + 
				"Approve or reject new transfer: $" + model.getAmount() + "\n" + 
				"1) Approve\n" + 
				"2) Reject\n" + 
				"3) Cancel\n");
		
		int response = ViewUtilities.getIntResponse(scanner);
		
		switch (response)
		{
			case 1:
				try 
				{
					transferDAO.approveTransfer(model);
				} 
				catch (DataAccessException e) 
				{
					log.info("An error has occured when approving member. Please try again.\n");
					log.error(e.getMessage());
				}
				catch (TransferReceiverException e)
				{
					log.info("An error has occured. Transfer is going to the same person.\n");
					log.error(e.getMessage());
				}
				return new CustomerAccountView(account);
				
			case 2:
				try 
				{
					transferDAO.rejectTransfer(model);
				} 
				catch (TransferReceiverException e)
				{
					log.info("An error has occured. Transfer is going to the same person.\n");
					log.error(e.getMessage());
				}
				catch (DataAccessException e) 
				{
					log.info("An error has occured when rejecting member. Please try again.\n");
					log.error(e.getMessage());
				}
				return new CustomerAccountView(account);
				
			case 3:
				return new CustomerAccountView(account);
				
			default:
				ViewUtilities.showInvalidInputMessage();
				return new CustomerAccountView(account);
		}
	}
}
