package weekone;

import java.util.Scanner;

public class Adder {

	public static void main(String[] args) {
		// Declare variables
		Scanner keyb = new Scanner(System.in);
		int firstNumber, secondNumber, sum;

		// Get input
		System.out.print("Enter the first number: ");
		firstNumber = Integer.parseInt(keyb.nextLine());
		System.out.print("Enter the second number: ");
		secondNumber = Integer.parseInt(keyb.nextLine());

		// Calculate sum
		sum = firstNumber + secondNumber;

		// Output sum
		System.out.println(firstNumber + " + " + secondNumber + " = " + sum);

		// Close Scanner
		keyb.close();
	}

}
