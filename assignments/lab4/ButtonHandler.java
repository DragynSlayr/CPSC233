package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

	// Reference to the GUI
	private CalculatorGUI calculatorGUI;

	/**
	 * Creates a new Button Handler
	 * 
	 * @param calculatorGUI
	 *            The view to modify with the handler
	 */
	public ButtonHandler(CalculatorGUI calculatorGUI) {
		// Store parameter
		this.calculatorGUI = calculatorGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get all the of the values that the user entered
		double principal = Double.parseDouble(calculatorGUI.getPrincipal());
		double interest = Double.parseDouble(calculatorGUI.getInterest());
		int amortization = Integer.parseInt(calculatorGUI.getAmortization());

		// Create a mortgage object with the values from the user
		Mortgage mortgage = new Mortgage(principal, interest, amortization);

		// Calculate values for the output fields
		double blendedMonthly = mortgage.getBlendedMonthlyPayment();
		double totalInterest = mortgage.getTotalInterest();
		double totalInterestAndPrincipal = mortgage
				.getTotalInterestAndPrincipal();
		double interestPrincipalRatio = mortgage.getInterestPrincipalRatio();
		double yearlyInterest = mortgage.getAverageInterestPerYear();
		double monthlyInterest = mortgage.getAverageInterestPerMonth();
		double amortizationInYears = mortgage.getAmortizationInYears();

		// Format the calculated values and set the output fields to use them
		calculatorGUI
				.setMonthlyPayment(String.format("$ %.2f", blendedMonthly));
		calculatorGUI.setTotalInterest(String.format("$ %.2f", totalInterest));
		calculatorGUI.setPrincipalInterest(String.format("$ %.2f",
				totalInterestAndPrincipal));
		calculatorGUI.setInterestPrincipalRatio(String.format("%.4f",
				interestPrincipalRatio));
		calculatorGUI
				.setYearlyInterest(String.format("$ %.2f", yearlyInterest));
		calculatorGUI.setMonthlyInterest(String.format("$ %.2f",
				monthlyInterest));
		calculatorGUI.setYearsAmortization(String.format("%.2f",
				amortizationInYears));

		// Destroy the mortgage object
		mortgage = null;
	}
}
