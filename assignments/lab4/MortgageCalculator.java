package lab4;

public class MortgageCalculator {

	public MortgageCalculator(CalculatorGUI calculatorGUI) {
		ButtonHandler handler = new ButtonHandler(calculatorGUI);
		calculatorGUI.addCalculateButtonListener(handler);
	}

	public static void main(String[] args) {
		CalculatorGUI calculatorGUI = new CalculatorGUI();
		new MortgageCalculator(calculatorGUI);
		calculatorGUI.setVisible(true);
	}
}
