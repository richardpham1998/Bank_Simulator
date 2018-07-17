/**
 * Runs the Bank application by combining the Model, View, and Controller.
 * 
 * @author Richard Pham
 */

package bank;

public class Application {
	
	public static void main(String[] args)
	{
		BankModel model = new BankModel();
		BankView view = new BankView();
		BankController controller = new BankController(model, view);
		
		view.setVisible(true);
	}

}
