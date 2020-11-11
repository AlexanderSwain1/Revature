package consoleApp.Views;

import java.util.Date;

import consoleApp.View.View;
import consoleApp.daoImpl.Withdrawal_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Member;
import consoleApp.models.Withdrawal;

public class WithdrawalView extends View
{
	
	Account account;
	Member model;
	
	public WithdrawalView(Account account, Member model)
	{
		this.account = account;
		this.model = model;
	}
	
	public View navigate() 
	{
		log.info("How much are you going to withdraw: $");
		String amount = scanner.next().trim();//fix
		
		Withdrawal_DatabaseContext withdrawalDAO = new Withdrawal_DatabaseContext();
		
		try 
		{
			double parsed = Double.parseDouble(amount);
			withdrawalDAO.makeWithdrawal(new Withdrawal(0, parsed, new Date(), model.getId()));
			log.info("You withdrew $" + parsed + ".\n");
			return new CustomerAccountView(account);
		} 
		catch (NumberFormatException e)
		{
			log.info("The input you entered is not an amount.\n");
			log.error(e.getMessage());
			return this;
		}
		catch (IllegalArgumentException e)
		{
			log.info("The amount should be less than or equal to your balance and greater than zero.\n");
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
		catch (DataAccessException e) 
		{
			log.info("An error has occured. Please try again later.\n1");
			log.error(e.getMessage());
			return new CustomerAccountView(account);
		}
	}
}
