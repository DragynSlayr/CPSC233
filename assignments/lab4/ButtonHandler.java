package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

	private CalculatorGUI calculatorGUI;

	public ButtonHandler(CalculatorGUI calculatorGUI) {
		this.calculatorGUI = calculatorGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		double principal = Double.parseDouble(calculatorGUI.getPrincipal());
		double interest = Double.parseDouble(calculatorGUI.getInterest());
		int amortization = Integer.parseInt(calculatorGUI.getAmortization());

		Mortgage mortgage = new Mortgage(principal, interest, amortization);

		double blendedMonthly = mortgage.getBlendedMonthlyPayment();
		double totalInterest = mortgage.getTotalInterest();
		double totalInterestAndPrincipal = mortgage.getTotalInterestAndPrincipal();
		double interestPrincipalRatio = mortgage.getInterestPrincipalRatio();
		double yearlyInterest = mortgage.getAverageInterestPerYear();
		double monthlyInterest = mortgage.getAverageInterestPerMonth();
		double amortizationInYears = mortgage.getAmortizationInYears();

		calculatorGUI.setMonthlyPayment(String.format("$ %.2f", blendedMonthly));
		calculatorGUI.setTotalInterest(String.format("$ %.2f", totalInterest));
		calculatorGUI.setPrincipalInterest(String.format("$ %.2f",
				totalInterestAndPrincipal));
		calculatorGUI.setInterestPrincipalRatio(String.format("%.4f",
				interestPrincipalRatio));
		calculatorGUI.setYearlyInterest(String.format("$ %.2f", yearlyInterest));
		calculatorGUI.setMonthlyInterest(String.format("$ %.2f", monthlyInterest));
		calculatorGUI.setYearsAmortization(String.format("%.2f", amortizationInYears));

		mortgage = null;
	}
}
