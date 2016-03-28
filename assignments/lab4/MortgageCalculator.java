package lab4;

/**
 * Provides functionality to the GUI using the the Mortgage
 * 
 * @author Inderpreet Dhillon
 *
 */
public class MortgageCalculator {

	/**
	 * Creates a new MortgageCalculator object that acts as a controller for
	 * Mortgage and GUI
	 * 
	 * @param calculatorGUI
	 *            The GUI to work with
	 */
	public MortgageCalculator(CalculatorGUI calculatorGUI) {
		// Create a handler for the GUI's button
		ButtonHandler handler = new ButtonHandler(calculatorGUI);

		// Assign the listener to the button
		calculatorGUI.addCalculateButtonListener(handler);
	}

	/**
	 * Main method for MortgageCalculator
	 * 
	 * @param args
	 *            Command-line arguments (unused)
	 */
	public static void main(String[] args) {
		// Create the GUI
		CalculatorGUI calculatorGUI = new CalculatorGUI();

		// Create the calculator and allow it to use the GUI
		new MortgageCalculator(calculatorGUI);

		// Make the GUI visible
		calculatorGUI.setVisible(true);
	}
}
