package lab4;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorGUI {

	// Instance variables for the GUI
	private JFrame frame;
	private JTextField principalField, interestField, amortizationField,
			monthlyPaymentField, totalInterestField, principalInterestField,
			interestPrincipalRatioField, yearlyInterestField,
			monthlyInterestField, yearsAmortizationField;
	private JButton calculateButton;

	public CalculatorGUI() {
		// Create a JFrame for the calculator
		frame = new JFrame("Mortgage Calculator");

		// Create a JPanel for the contents of the JFrame
		JPanel mainPanel = new JPanel();

		// Set the layout to a 12 x 2 grid with 10 pixel spacing between
		// elements
		mainPanel.setLayout(new GridLayout(12, 2, 10, 10));

		// Create a border with 5 pixel spacing to top and bottom and 10 pixel
		// spacing on left and right
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		// Create labels and text fields for the 3 input fields
		principalField = new JTextField("0.0");
		JLabel principalLabel = new JLabel("Principal in dollars");

		interestField = new JTextField("0.0");
		JLabel interestLabel = new JLabel("Annual Interest percentage");

		amortizationField = new JTextField("0");
		JLabel amortizationLabel = new JLabel("Amortization in months");

		// Create labels and text fields for the 7 output fields, these fields
		// are not editable
		monthlyPaymentField = new JTextField("0.0");
		monthlyPaymentField.setEditable(false);
		JLabel monthlyPaymentLabel = new JLabel("Monthly Payment");

		totalInterestField = new JTextField("0.0");
		totalInterestField.setEditable(false);
		JLabel totalInterestLabel = new JLabel("Total Interest");

		principalInterestField = new JTextField("0.0");
		principalInterestField.setEditable(false);
		JLabel principalInterestLabel = new JLabel("Principal + Interest");

		interestPrincipalRatioField = new JTextField("0.0");
		interestPrincipalRatioField.setEditable(false);
		JLabel interestPrincipalRatioLabel = new JLabel(
				"Interest : Principal Ratio");

		yearlyInterestField = new JTextField("0.0");
		yearlyInterestField.setEditable(false);
		JLabel yearlyInterestLabel = new JLabel("Yearly Interest");

		monthlyInterestField = new JTextField("0.0");
		monthlyInterestField.setEditable(false);
		JLabel monthlyInterestLabel = new JLabel("Monthly Interest");

		yearsAmortizationField = new JTextField("0.0");
		yearsAmortizationField.setEditable(false);
		JLabel yearsAmortizationLabel = new JLabel("Amortization in Years");

		// Create a button
		calculateButton = new JButton("Calculate Mortgage");

		// Add inputs and labels for them
		mainPanel.add(principalLabel);
		mainPanel.add(principalField);
		mainPanel.add(interestLabel);
		mainPanel.add(interestField);
		mainPanel.add(amortizationLabel);
		mainPanel.add(amortizationField);

		// Add empty labels to create a small break between cells
		mainPanel.add(new JLabel());
		mainPanel.add(new JLabel());

		// Add all labels and text fields for output
		mainPanel.add(monthlyPaymentLabel);
		mainPanel.add(monthlyPaymentField);
		mainPanel.add(totalInterestLabel);
		mainPanel.add(totalInterestField);
		mainPanel.add(principalInterestLabel);
		mainPanel.add(principalInterestField);
		mainPanel.add(interestPrincipalRatioLabel);
		mainPanel.add(interestPrincipalRatioField);
		mainPanel.add(yearlyInterestLabel);
		mainPanel.add(yearlyInterestField);
		mainPanel.add(monthlyInterestLabel);
		mainPanel.add(monthlyInterestField);
		mainPanel.add(yearsAmortizationLabel);
		mainPanel.add(yearsAmortizationField);

		// Add the button
		mainPanel.add(calculateButton);

		// Add the panel to the frames content pain
		frame.getContentPane().add(mainPanel);

		// Add a window listener to the frame to stop the program when the
		// window closes
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
	}

	/**
	 * Sets the visibility of the frame
	 * 
	 * @param isVisible
	 *            true to show the frame, false to hide
	 */
	public void setVisible(boolean isVisible) {
		// Resize the frame to the size of the contents
		frame.pack();

		// Disable resizing of the frame
		frame.setResizable(false);

		// Center the frame to the screen
		frame.setLocationRelativeTo(null);

		// Set the visibility
		frame.setVisible(isVisible);
	}

	/**
	 * Assigns a button listener to the calculate button
	 * 
	 * @param listener
	 *            The listener to use for the button
	 */
	public void addCalculateButtonListener(ActionListener listener) {
		this.calculateButton.addActionListener(listener);
	}

	/**
	 * Gets the text from the Principal field
	 * 
	 * @return Text from Principal field
	 */
	public String getPrincipal() {
		return principalField.getText();
	}

	/**
	 * Gets the text from the Interest field
	 * 
	 * @return Text from Interest field
	 */
	public String getInterest() {
		return interestField.getText();
	}

	/**
	 * Gets the text from the Amortization field
	 * 
	 * @return Text from Amortization field
	 */
	public String getAmortization() {
		return amortizationField.getText();
	}

	/**
	 * Sets the text of the Monthly Payment field
	 */
	public void setMonthlyPayment(String monthlyPayment) {
		this.monthlyPaymentField.setText(monthlyPayment);
	}

	/**
	 * Sets the text of the Total Interest field
	 */
	public void setTotalInterest(String totalInterest) {
		this.totalInterestField.setText(totalInterest);
	}

	/**
	 * Sets the text of the Principal Interest field
	 */
	public void setPrincipalInterest(String principalInterest) {
		this.principalInterestField.setText(principalInterest);
	}

	/**
	 * Sets the text of the Interest Principal Ratio field
	 */
	public void setInterestPrincipalRatio(String interestPrincipalRatio) {
		this.interestPrincipalRatioField.setText(interestPrincipalRatio);
	}

	/**
	 * Sets the text of the Yearly Interest field
	 */
	public void setYearlyInterest(String yearlyInterest) {
		this.yearlyInterestField.setText(yearlyInterest);
	}

	/**
	 * Sets the text of the Monthly Interest field
	 */
	public void setMonthlyInterest(String monthlyInterest) {
		this.monthlyInterestField.setText(monthlyInterest);
	}

	/**
	 * Sets the text of the Years Amortization field
	 */
	public void setYearsAmortization(String yearsAmortization) {
		this.yearsAmortizationField.setText(yearsAmortization);
	}
}
