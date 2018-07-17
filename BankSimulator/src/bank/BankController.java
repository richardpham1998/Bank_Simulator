/**
 * Connects the interface and the data of the Bank application.
 * 
 * @author Richard Pham
 */

package bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankController {
	
	private BankView theView;
	private BankModel theModel;
	
	//Uses the View and the Model to create the controller.
	public BankController(BankModel theModel, BankView theView)
	{
		this.theModel = theModel;
		this.theView = theView;
		
		//Adds action listeners from the View class.
		this.theView.addDepositListener(new DepositListener());
		this.theView.addWithdrawListener(new WithdrawListener());
		this.theView.addHistoryListener(new HistoryListener());
		this.theView.addInterestListener(new InterestListener());
		this.theView.addCalculatedInterestListener(new CalculatedInterestListener());
	}
	
	//Once the Deposit button is clicked, the deposit amount is added onto the Balance.
	class DepositListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			double depositAmount = 0;
			
			try
			{
				//Tries to make a deposit.
				depositAmount = theView.getDepositAmount();
				
				//Negative numbers will not be accepted.
				if(depositAmount < 0)
				{
					theView.errorMessage("You cannot deposit a negative amount.");
				}
				//Records the deposit in the History.
				else
				{
					theModel.deposit(depositAmount);	
					theModel.addToHistory("Deposited $" + depositAmount + " today.");
				}
				
				//Fixes the balance.
				theView.setBalance(theModel.getBalance());
			}
			
			//Characters that are not numbers will not be accepted.
			catch(NumberFormatException ex)
			{
				theView.errorMessage("Please add a number (do not use commas).");
			}
		}
	}
	
	//Once the Withdraw button is clicked, the withdrawal amount is removed from the Balance.
	class WithdrawListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			double withdrawalAmount = 0;
			
			try
			{
				//Tries to make a withdrawal.
				withdrawalAmount = theView.getWithdrawalAmount();
				
				//Stops the user from withdrawing more than they have.
				if(withdrawalAmount > theModel.getBalance())
				{
					theView.errorMessage("You are trying to withdraw more than you have.");
				}
				//Negative numbers will not be accepted.
				else if(withdrawalAmount < 0)
				{
					theView.errorMessage("You cannot withdraw a negative amount.");
				}
				else
				{
					//Records the withdrawal in the History.
					theModel.withdraw(withdrawalAmount);
					theModel.addToHistory("Withdrew $" + withdrawalAmount + " today.");
				}
				
				//Fixes the balance.
				theView.setBalance(theModel.getBalance());
			}
			//Characters that are not numbers will not be accepted.
			catch(NumberFormatException ex)
			{
				theView.errorMessage("Please add a number (do not use commas).");
			}
		}
	}
	
	//Once the History button is clicked, a new History page is launched.
	class HistoryListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			//Only launch a new History page if the user has either 
			//made a deposit or withdrawal at least once.
			if (!theModel.getHistory().isEmpty())
			{
				theView.activateHistory(theModel.getHistory());
			}
			else
			{
				theView.errorMessage("You haven't done any deposits or withdrawals.");
			}
		}
	}
	
	//Once the Monthly Interest button is clicked, a new page is loaded, allowing the user to calculate
	//the monthly interest.
	class InterestListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			//Only launch a new page is the user has money in their balance.
			if (theModel.getBalance()>0)
			{
				theView.activateInterest();
				theView.setInterestBalance(theModel.getBalance());
			}
			else
			{
				theView.errorMessage("You don't have any money to calculate interest with.");
			}
		}
	}
	
	//On the Monthly Interest page, once the user clicks the Calculate Button, the interest rate is shown.
	class CalculatedInterestListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				//Tries to get the month and percentage.
				int monthAmount = theView.getMonthAmount();
				double percentageAmount = theView.getPercentageAmount();
				
				//Months and/or Percentage rates that are negative will not be accepted.
				if(monthAmount < 0 || percentageAmount < 0)
				{
					theView.errorMessage("One or more of the boxes are negative.");
				}
				//Calculates the interest rate.
				else
				{
					theView.setInterest(theModel.calculateInterestRate(monthAmount,percentageAmount));
				}
			}
			//Months and/or Percentage rates that have non-numeric characters will not be accepted.
			catch(NumberFormatException ex)
			{
				theView.errorMessage("Please add numbers to the boxes (do not use commas).");
			}

		}
	}

}

