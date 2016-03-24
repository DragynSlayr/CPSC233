package lab4;

public class Mortgage {

	// Instance variables
	private double principal, interest, compoundingFrequency, paymentFrequency;
	private int amortization;

	/**
	 * Creates a new Mortgage object
	 * 
	 * @param principal
	 *            The amount loaned, in dollars
	 * @param interest
	 *            The annual interest rate, in percent
	 * @param amortization
	 *            The mortgage length, in months
	 */
	public Mortgage(double principal, double interest, int amortization) {
		// Store parameters
		this.principal = principal;
		this.amortization = amortization;

		// Convert from percent to decimal and store
		this.interest = interest / 100;

		// Initialize variables
		this.compoundingFrequency = 2.0;
		this.paymentFrequency = 12.0;
	}

	/**
	 * Calculates interest factor
	 * 
	 * @return The interest factor of the mortgage
	 */
	private double calculateInterestFactor() {
		// The base and power calculations
		double base = (interest / compoundingFrequency) + 1.0;
		double power = compoundingFrequency / paymentFrequency;

		// Return the result of the interest factor formula
		return Math.pow(base, power) - 1.0;
	}

	/**
	 * Calculates the principal + interest per month
	 * 
	 * @return The blended monthly payment based on the formula
	 */
	public double getBlendedMonthlyPayment() {
		// Calculate interest factor for the formula
		double interestFactor = calculateInterestFactor();

		// Calculate both parts of the fraction
		double numerator = this.principal * interestFactor;
		double denominator = 1.0 - Math.pow(interestFactor + 1.0, -1.0
				* amortization);

		// Return the quotient
		return numerator / denominator;
	}

	/**
	 * Calculates the total interest and principal at the end of the mortgage
	 * 
	 * @return The blended monthly payment over the length of the mortgage
	 */
	public double getTotalInterestAndPrincipal() {
		return getBlendedMonthlyPayment() * amortization;
	}

	/**
	 * Calculates the total interest paid at the end of the mortgage
	 * 
	 * @return The total interest paid
	 */
	public double getTotalInterest() {
		return getTotalInterestAndPrincipal() - principal;
	}

	/**
	 * Gets the total interest to principal ratio
	 * 
	 * @return The quotient of total interest and principal
	 */
	public double getInterestPrincipalRatio() {
		return getTotalInterest() / principal;
	}

	/**
	 * Gets the average interest paid per month of the mortgage
	 * 
	 * @return Total interest divided by length of amortization
	 */
	public double getAverageInterestPerMonth() {
		return getTotalInterest() / amortization;
	}

	/**
	 * Gets the average interest paid per year of the mortgage
	 * 
	 * @return The average monthly interest scaled to a year
	 */
	public double getAverageInterestPerYear() {
		return getAverageInterestPerMonth() * 12.0;
	}

	/**
	 * The length of the amortization in years
	 * 
	 * @return The amortization (months) divided by 12
	 */
	public double getAmortizationInYears() {
		return amortization / 12.0;
	}
}
