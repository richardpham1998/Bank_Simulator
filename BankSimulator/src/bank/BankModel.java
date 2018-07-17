/**
 * Creates a model for a Bank which allows the user to deposit and withdraw money, 
 * check their current Balance, check their Bank history, and calculate monthly interest rates
 * based on their current Balance.
 * 
 * @author Richard Pham
 */

package bank;

import java.util.ArrayList;

public class BankModel {

	private double balance;
	private ArrayList<String> bankHistory = new ArrayList<String>();

	//Allows the user to add money into the current Balance.
	public void deposit(double input)
	{
		balance = balance + input;
	}
	
	//Allows the user to remove money from the current Balance.
	public void withdraw(double input)
	{
		balance = balance - input;
	}
	
	//Returns the current Balance.
	public double getBalance()
	{
		return balance;
	}
	
	//Reveals a list of recent bank transactions.
	public ArrayList<String> getHistory()
	{
		return bankHistory;
	}
	
	//Every time a user deposits or withdraws money, it is recorded in a String ArrayList.
	public void addToHistory(String input)
	{
		bankHistory.add(input);
	}
	
	//Using I=P*r*t to calculate the interest, where I is interest, P is current money, 
	//r is percentage rate and t is time (in months).
	//Interest calculation is based on http://www.webmath.com/simpinterest.html
	public double calculateInterestRate(double months, double percentage)
	{
		return balance*(months)*(percentage/100);
	}
	
}
