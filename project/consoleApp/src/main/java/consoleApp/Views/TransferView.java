package consoleApp.Views;

import java.util.Date;
import java.util.Scanner;

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

public class TransferView extends View
{
	
	Account account;
	Member model;
	
	public TransferView(Account account, Member model)
	{
		this.account = account;
		this.model = model;
	}
	
	public View navigate() 
	{
		double amount = ViewUtilities.getDoubleResponse(scanner, "How much are you going to transfer: $");
		if (amount <= 0 || amount > model.getBalance())
		{
			log.info("The amount entered should be greater than zero and less than or equal to your balance.\n");
			return new CustomerAccountView(account);
		}
		
		log.info("Enter the email of the receiver: ");
		String receiver = scanner.next().trim();
		
		
		Transfer_DatabaseContext transferDAO = new Transfer_DatabaseContext();
		Account_DatabaseContext accountDAO = new Account_DatabaseContext();
		Member_DatabaseContext memberDAO = new Member_DatabaseContext();
		
		try 
		{
			Account receiverAccount = accountDAO.getAccount(receiver);
			if (receiverAccount != null)
			{
				Member receiverMember = memberDAO.getMember(receiverAccount);
				if (receiverMember != null)
				{
					if (receiverMember.getIsApproved() == null)
					{
						log.info("A decision has not been made on this account. Accounts must be approved in order to transfer to the account.\n");
						return new CustomerAccountView(account);
					}
					else if (receiverMember.getIsApproved() )
					{
						transferDAO.createTransfer(new Transfer(0, amount, new Date(), model.getId(), receiverMember.getId(), null));
						log.info("Your transfer for $" + amount + " is awaiting approval.\n");
						return new CustomerAccountView(account);
					}
					else
					{
						log.info("This account was rejected and is under investigation. Cannot transfer to this account.\n");
						return new CustomerAccountView(account);
					}
				}
				else
				{
					log.info("The email referes to an employee account.\n");
					return new CustomerAccountView(account);
				}
			}
			else
			{
				log.info("Account doesn't exist. Please try again.\n");
				return new CustomerAccountView(account);
			}
		} 
		catch (NumberFormatException e)
		{
			log.info("The input you entered is not an amount.\n");
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
		catch (IllegalArgumentException e)
		{
			log.info("The amount should be less than or equal to your balance and greater than zero.\n");
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
		catch (TransferReceiverException e)
		{
			log.info(e.getMessage());
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
		catch (DataAccessException e) 
		{
			log.info("An error has occured. Please try again later.\n");
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
	}
}
