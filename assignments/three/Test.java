package three;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Inderpreet Dhillon Class for testing the Point class
 */
public final class Test {

	public static void main(String[] args) {
		// Scanner for keyboard input
		Scanner keyb = new Scanner(System.in);

		// ArrayList to hold Shape objects
		ArrayList<Shape> createdObjects = new ArrayList<Shape>();

		// Sentinel for loop
		boolean userEnded = false;

		while (!userEnded) {
			// Display options to the user
			printMenu();

			// Get input from the user
			System.out.print("Enter choice: ");
			int choice = keyb.nextInt();

			switch (choice) {
			case 1:
				// Store the choice of shape
				System.out.print("Circle (c) or Rectangle (r): ");

				// Get the shape from the user, make it lower case and store the
				// first character
				char shapeLetter = keyb.nextLine().toLowerCase().charAt(0);

				// Determine which shape to instantiate
				if (shapeLetter == 'c') {
					System.out.println("Circle");
				} else if (shapeLetter == 'r') {
					System.out.println("Rectangle");
				}
				break;
			case 2:
				// Move shape
				break;
			case 3:
				// Change shape size
				break;
			case 4:
				// Print shape info
				break;
			case 5:
				// Translate shape
				break;
			case 6:
				// Find distance between shapes
				break;
			case 7:
				// Destroy shape
				break;
			case 8:
				// Print all shapes
				break;
			case 9:
				// Make the loop end
				userEnded = true;
				System.out.println("Stopping...");
				break;
			default:
				System.out.println("Invalid choice!\n");
				break;
			}
		}

		// Finished with Scanner, close it
		keyb.close();
	}

	public static void printMenu() {
		System.out.println("1) Create new shape");
		System.out.println("2) Move shape to new location");
		System.out.println("3) Change shape size");
		System.out.println("4) Display shape info");
		System.out.println("5) Translate shape");
		System.out.println("6) Find distance between shapes");
		System.out.println("7) Destroy shape");
		System.out.println("8) Print all shapes");
		System.out.println("9) Quit");
	}
}
