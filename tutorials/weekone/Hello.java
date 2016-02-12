package weekone;

import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		// Declare all variables at the start of methods
		Scanner keyb = new Scanner(System.in);
		String userName, className;

		// Get user input
		System.out.print("Enter your name: ");
		userName = keyb.nextLine();
		System.out.print("Enter your class: ");
		className = keyb.nextLine();

		// Output message
		System.out.println("Hello, " + userName + "!\nWelcome to " + className);

		// Close Scanner object
		keyb.close();
	}

}
