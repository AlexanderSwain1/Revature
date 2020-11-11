package consoleApp.Views;

import java.util.Date;

import consoleApp.View.View;
import consoleApp.daoImpl.Deposit_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Deposit;
import consoleApp.models.Member;

public class DepositView extends View
{
	Account account;
	Member model;
	
	public DepositView(Account account, Member model)
	{
		this.account = account;
		this.model = model;
	}
	
	public View navigate() 
	{
		log.info("How much are you going to deposit: $");
		String amount = scanner.next().trim();
		
		Deposit_DatabaseContext depositDAO = new Deposit_DatabaseContext();
		
		try 
		{
			double parsed = Double.parseDouble(amount);
			depositDAO.MakeDeposit(new Deposit(0, parsed, new Date(), model.getId()));
			log.info("You deposited $" + parsed + ".\n");
			return new CustomerAccountView(account);
		} 
		catch (NumberFormatException e)
		{
			log.info("The input you entered is not an amount." + "\n");
			log.error(e.getMessage());
			return this;
		}
		catch (IllegalArgumentException e)
		{
			log.info("The amount should be greater than zero." + "\n");
			log.error(e.getMessage());
			return this;
		}
		catch (DataAccessException e) 
		{
			log.info("An error has occured. Please try again later." + "\n");
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
	}
}