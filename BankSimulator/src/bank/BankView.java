/**
 * Provides the interface for the Bank application.
 * 
 * @author Richard Pham
 */

package bank;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankView extends JFrame {
	
	//Interface for the Main Page
	private JLabel blank0 = new JLabel("    ");
	private JLabel welcomeMessage = new JLabel("Welcome to the Bank Simulator!");
	private JLabel blank1 = new JLabel("   ");
	private JLabel dollarSign1 = new JLabel("$");
	private JTextField depositAmount = new JTextField(8);
	private JButton deposit = new JButton("Deposit");
	private JLabel blank2 = new JLabel("   ");
	private JLabel dollarSign2 = new JLabel("$");
	private JTextField withdrawalAmount = new JTextField(8);
	private JButton withdraw = new JButton("Withdraw");
	private JLabel balance = new JLabel("Balance:");
	private JLabel balanceAmount = new JLabel("$0.00");
	
	//Interface for the Interest Rate Page
	private JButton interestRate = new JButton("Monthly Interest Rate");
	private JLabel interestRateWelcome = new JLabel("Calculate Monthly Interest Rate:");
	private JLabel percentage = new JLabel("Percentage:");
	private JTextField percentageAmount = new JTextField(10);
	private JLabel percentSign = new JLabel("%");
	private JLabel time = new JLabel("Months:");
	private JTextField timeAmount = new JTextField(10);
	private JButton calculatedInterestAmount = new JButton("Calculate Interest:");
	private JLabel calculatedInterest = new JLabel("$0.00");
	private JLabel interestBalance = new JLabel("Balance:");
	private JLabel interestBalanceAmount = new JLabel("$0.00");
	
	//Interface for the History Page
	private JButton history = new JButton("Check History");
	private	JLabel recentHistory = new JLabel("Recent History:");
	private JLabel blank3 = new JLabel("    ");
	private JLabel blank4 = new JLabel("   ");

	//Creates the main Page
	BankView()
	{
		//Creates the page itself.
		JPanel bankPanel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250,250);
		this.setResizable(false);
		
		//Adds buttons, labels, and text boxes onto the page.
		bankPanel.add(blank0);
		bankPanel.add(welcomeMessage);
		bankPanel.add(blank1);
		bankPanel.add(dollarSign1);
		bankPanel.add(depositAmount);
		bankPanel.add(deposit);
		bankPanel.add(blank2);
		bankPanel.add(dollarSign2);
		bankPanel.add(withdrawalAmount);
		bankPanel.add(withdraw);
		bankPanel.add(balance);
		bankPanel.add(balanceAmount);
		bankPanel.add(interestRate);
		bankPanel.add(history);
		
		this.add(bankPanel);
	}
	
	//Takes value written in deposit box.
	public double getDepositAmount()
	{
		return Double.parseDouble(depositAmount.getText());
	}
	
	//Takes value written in withdraw box.
	public double getWithdrawalAmount()
	{
		return Double.parseDouble(withdrawalAmount.getText());
	}
	
	//Returns the balance.
	public Double getBalance()
	{
		//Removes the $ in front of the value
		String rawBalance = balanceAmount.getText().substring(1, balanceAmount.getText().length());
		
		return Double.parseDouble(rawBalance);
	}
	
	//Modifies the balance.
	public void setBalance(double solution)
	{
		//Rounds the solution
		solution = Math.round(solution * 100.0) / 100.0;
		
		balanceAmount.setText("$" + Double.toString(solution));
	}
	
	//Returns the percentage amount.
	public double getPercentageAmount()
	{
		return Double.parseDouble(percentageAmount.getText());
	}
	
	//Returns the number of months.
	public int getMonthAmount()
	{
		return Integer.parseInt(timeAmount.getText());
	}
	
	//Returns the interest rate.
	public Double getInterest()
	{
		//Removes the $ in front of the value
		String rawInterest = calculatedInterest.getText().substring(1, calculatedInterest.getText().length());
		
		return Double.parseDouble(rawInterest);
	}
	
	//Modifies the interest rate.
	public void setInterest(double solution)
	{
		//Rounds the solution
		solution = Math.round(solution * 100.0) / 100.0;
		
		calculatedInterest.setText("$" + Double.toString(solution));
	}
	
	//Modifies the balance in the Interest page.
	public void setInterestBalance(double solution)
	{
		interestBalanceAmount.setText("$" + Double.toString(solution));
	}
	
	//Launches the History page.
	public void activateHistory(ArrayList<String> input)
	{
		//Creates the page itself.
		JFrame historyFrame = new JFrame();
		JPanel historyPanel = new JPanel();
		historyFrame.setSize(250,1000);
		historyFrame.setResizable(false);
		
		//Adds labels to the page.
		historyPanel.add(blank3);
		historyPanel.add(recentHistory);
		historyPanel.add(blank4);
		
		//Adds the list of recent transactions onto the page.
		for(int i = input.size()-1; i >=0 ; i--)
		{
			JLabel action = new JLabel(input.get(i));
			historyPanel.add(action);
		}

		historyFrame.add(historyPanel);
		historyFrame.setVisible(true);
	}
	
	//Launches the Interest page.
	public void activateInterest()
	{
		//Creates the page itself.
		JFrame interestFrame = new JFrame();
		JPanel interestPanel = new JPanel();
		interestFrame.setSize(250,250);
		interestFrame.setResizable(false);
		
		//Adds buttons, labels, and text boxes onto the page.
		interestPanel.add(interestRateWelcome);
		interestPanel.add(percentage);
		interestPanel.add(percentageAmount);
		interestPanel.add(percentSign);
		interestPanel.add(time);
		interestPanel.add(timeAmount);
		interestPanel.add(calculatedInterestAmount);
		interestPanel.add(calculatedInterest);
		interestPanel.add(interestBalance);
		interestPanel.add(interestBalanceAmount);

		interestFrame.add(interestPanel);
		interestFrame.setVisible(true);
	}
	
	void addDepositListener(ActionListener listenForDepositButton)
	{
		deposit.addActionListener(listenForDepositButton);
	}
	
	void addWithdrawListener(ActionListener listenForWithdrawButton)
	{
		withdraw.addActionListener(listenForWithdrawButton);
	}
	
	void addHistoryListener(ActionListener listenForHistoryButton)
	{
		history.addActionListener(listenForHistoryButton);
	}
	
	void addInterestListener(ActionListener listenForInterestButton)
	{
		interestRate.addActionListener(listenForInterestButton);
	}
	
	void addCalculatedInterestListener(ActionListener listenForCalculateInterestButton)
	{
		calculatedInterestAmount.addActionListener(listenForCalculateInterestButton);
	}
	
	//Shows error messages.
	void errorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
