package consoleApp.Views;

import java.util.ArrayList;
import java.util.List;

import consoleApp.Util.ViewUtilities;
import consoleApp.View.View;
import consoleApp.daoImpl.Account_DatabaseContext;
import consoleApp.daoImpl.Member_DatabaseContext;
import consoleApp.daoImpl.Transfer_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;
import consoleApp.models.Transfer;

public class PendingTransfersView extends View
{
	private Account account;
	private Member member;
	
	public PendingTransfersView(Account account, Member member)
	{
		this.account = account;
		this.member = member;
	}

	@Override
	public View navigate() 
	{
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		Transfer_DatabaseContext transferDAO = new Transfer_DatabaseContext();
		
		//change to List interface, require upgrade to Java 8
		List<Transfer> pendingTransfer = new ArrayList<Transfer>();
		try 
		{
			pendingTransfer = transferDAO.getPendingTransfers(member.getId());
		} 
		catch (DataAccessException e1) 
		{
			log.info("An error has occured. Please try again later.\n");
			log.error(e1.getMessage());
		}
		
		log.info("\n--------------Pending Transfers---------------\n");
		
		if (pendingTransfer.size() > 0)
			for (int i = 0; i < pendingTransfer.size(); i++)
			{
				try 
				{
					Member senderMember = memberDAO.getMember(pendingTransfer.get(i).getSenderMemberId());
					Account senderAccount = accountDAO.getAccount(senderMember);
					log.info((i + 1) + ") From "+ senderAccount.getEmail() + ": $" + pendingTransfer.get(i).getAmount()  + "\n");
				} 
				catch (DataAccessException e) 
				{
					log.info((i + 1) + ") Error retreiving account name: $" + pendingTransfer.get(i).getAmount() + "\n");
				}
			}
		else
		{
			log.info("There are no new transfers\n");
			return new CustomerAccountView(account);
		}
		
		log.info((pendingTransfer.size() + 1) + ") Cancel \n");
		
		int response = ViewUtilities.getIntResponse(scanner) - 1;

		if (response >= 0 && response < pendingTransfer.size())
			return new TransferApprovalView(account, member, pendingTransfer.get(response));
		else if (response == pendingTransfer.size())
			return new CustomerAccountView(account);
		else
		{
			ViewUtilities.showInvalidInputMessage();
			return new CustomerAccountView(account);
		}
	}
}
