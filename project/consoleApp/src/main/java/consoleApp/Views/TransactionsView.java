package consoleApp.Views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import consoleApp.View.View;
import consoleApp.daoImpl.Deposit_DatabaseContext;
import consoleApp.daoImpl.Transfer_DatabaseContext;
import consoleApp.daoImpl.Withdrawal_DatabaseContext;
import consoleApp.exception.DataAccessException;
import consoleApp.models.Account;
import consoleApp.models.Transaction;

public class TransactionsView extends View
{
	
	private Account employeeAccount;
	
	public TransactionsView(Account employeeAccount)
	{
		this.employeeAccount = employeeAccount;
	}

	public View navigate() 
	{
		List<Transaction> transactions = GetTransactions();
		
		log.info("\n-------------------------Transactions-----------------------------\n");
		
		for (Transaction transaction : transactions)
			log.info(transaction.toString() + "\n");
		
		if (transactions.size() == 0)
			log.info("There are currently no transctions\n");
		
		return new EmployeeAccountView(employeeAccount);
	}
	
	private List<Transaction> GetTransactions()
	{
		Withdrawal_DatabaseContext withdrawalDAO = new Withdrawal_DatabaseContext();
		Deposit_DatabaseContext depositDAO = new Deposit_DatabaseContext();
		Transfer_DatabaseContext transferDAO = new Transfer_DatabaseContext();
		List<Transaction> result = new ArrayList<Transaction>();
		
		try
		{
			result.addAll(withdrawalDAO.getWithdrawals());
			result.addAll(depositDAO.getDeposits());
			result.addAll(transferDAO.getTransfers());
			Collections.sort(result, (x, y) -> { return x.getTimestamp().compareTo(y.getTimestamp()); });
		}
		catch(DataAccessException e)
		{
			log.info("An error has occured. Please try again later.\n");
			log.error(e.getMessage());
			result.clear();
		}
		return result;
	}
}
