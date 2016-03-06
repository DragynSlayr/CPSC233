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
		ArrayList<Shape> shapes = new ArrayList<Shape>();

		// Sentinel for loop
		boolean userEnded = false;

		while (!userEnded) {
			// Display options to the user
			System.out.println("\n1) Create new shape");
			System.out.println("2) Change origin of shape");
			System.out.println("3) Change shape dimensions");
			System.out.println("4) Display shape info");
			System.out.println("5) Move shape");
			System.out.println("6) Find distance between shapes");
			System.out.println("7) Destroy shape");
			System.out.println("8) Print all created shapes");
			System.out.println("9) Quit");

			// Get input from the user
			System.out.print("Enter choice: ");
			int choice = Integer.parseInt(keyb.nextLine());
			
			System.out.println();

			switch (choice) {
			case 1:
				System.out.print("Create Circle (c) or Rectangle (r): ");

				// Get the shape from the user, make it lower case and store the
				// first character
				char shapeLetter = keyb.nextLine().toLowerCase().charAt(0);

				// Determine which shape to instantiate
				if (shapeLetter == 'c') {
					// Variables to hold the Circle's properties
					double radius, x, y;

					System.out.println("Enter properties of the Circle");

					// Store the properties of the Circle
					System.out.print("Radius: ");
					radius = Double.parseDouble(keyb.nextLine());

					System.out.print("X-coordinate: ");
					x = Double.parseDouble(keyb.nextLine());

					System.out.print("Y-coordinate: ");
					y = Double.parseDouble(keyb.nextLine());

					// Check that the radius is non-negative
					if (radius > 0.0) {
						// Add a new Circle using the values from the user
						shapes.add(new Circle(radius, x, y));
					} else {
						System.out.println("Radius cannot be negative");
					}
				} else if (shapeLetter == 'r') {
					// Variables to hold the Rectangle's properties
					double length, width, x, y;

					System.out.println("Enter properties of the Rectangle");

					// Store the properties of the Rectangle
					System.out.print("Length: ");
					length = Double.parseDouble(keyb.nextLine());

					System.out.print("Width: ");
					width = Double.parseDouble(keyb.nextLine());

					System.out.print("X-coordinate: ");
					x = Double.parseDouble(keyb.nextLine());

					System.out.print("Y-coordinate: ");
					y = Double.parseDouble(keyb.nextLine());

					// Check that length and width are non-negative
					if (length > 0.0 && width > 0.0) {
						// Add a new Rectangle using the values from the user
						shapes.add(new Rectangle(length, width, x, y));
					} else {
						System.out
								.println("Both length and width must be positive");
					}
				} else {
					System.out.println("Invalid choice!");
				}
				break;
			case 2:
				// Ask for the index of the shape that will be moved
				System.out.print("Enter position of shape to move: ");

				// Store the index, assuming that the user starts counting from
				// 1
				int index = Integer.parseInt(keyb.nextLine()) - 1;

				// Variables to hold the new coordinates
				double newX,
				newY;

				System.out.println("Enter the new coordinates for the shape");

				// Store the amount of change in each direction
				System.out.print("X-coordinate: ");
				newX = Double.parseDouble(keyb.nextLine());

				System.out.print("Y-coordinate: ");
				newY = Double.parseDouble(keyb.nextLine());

				try {
					// Set the shapes position
					shapes.get(index).setOrigin(newX, newY);
				} catch (Exception e) {
					System.out.println("No shape at that position!");
				}
				break;
			case 3:
				// Ask for the index of the shape that will be resized
				System.out.print("Enter position of shape to resize: ");

				// Store the index, assuming that the user starts counting from
				// 1
				index = Integer.parseInt(keyb.nextLine()) - 1;

				try {
					// Check whether the shape is a Circle or Rectangle
					if (shapes.get(index) instanceof Rectangle) {
						// Variables to hold new dimensions
						double length, width;

						System.out.println("Enter new dimensions");

						// Store the new dimensions from the user
						System.out.print("New length: ");
						length = Double.parseDouble(keyb.nextLine());

						System.out.print("New width: ");
						width = Double.parseDouble(keyb.nextLine());

						// Cast the shape at the index to Rectangle
						Rectangle r = (Rectangle) shapes.get(index);
						r.setSize(length, width);

						// Put the resized Rectangle into the list
						shapes.set(index, r);
					} else if (shapes.get(index) instanceof Circle) {
						// Variable to hold new dimension
						double radius;

						System.out.println("Enter new dimension");

						// Store the new radius from the user
						System.out.print("New radius: ");
						radius = Double.parseDouble(keyb.nextLine());

						// Cast the shape at the index to Circle
						Circle c = (Circle) shapes.get(index);
						c.setRadius(radius);

						// Put the resized Circle into the list
						shapes.set(index, c);
					} else {
						// Shape may have been destroyed
						System.out.println("No shape at that index!");
					}
				} catch (Exception e) {
					System.out.println("No shape at that index!");
				}
				break;
			case 4:
				// Ask for the index of the shape that will be printed
				System.out.print("Enter position of shape to print: ");

				// Store the index, assuming that the user starts counting from
				// 1
				index = Integer.parseInt(keyb.nextLine()) - 1;

				try {
					// Print the shape at the index
					System.out.println(shapes.get(index));
				} catch (Exception e) {
					System.out.println("No shape at that index!");
				}
				break;
			case 5:
				// Ask for the index of the shape that will be moved
				System.out.print("Enter position of shape to move: ");

				// Store the index, assuming that the user starts counting from
				// 1
				index = Integer.parseInt(keyb.nextLine()) - 1;

				// Variables to hold the change in coordinates
				double deltaX,
				deltaY;

				System.out.println("Enter the amount to move the shape by");

				// Store the amount of change in each direction
				System.out.print("X-movement: ");
				deltaX = Double.parseDouble(keyb.nextLine());

				System.out.print("Y-movement: ");
				deltaY = Double.parseDouble(keyb.nextLine());

				try {
					// Move the shape at the index
					shapes.get(index).move(deltaX, deltaY);
				} catch (Exception e) {
					System.out.println("No shape at that index!");
				}
				break;
			case 6:
				// Variables to store the indices of the two shapes
				int shapeIndex1,
				shapeIndex2;

				// Ask for the indices of the shapes to find distance between
				System.out.print("Enter position of first shape: ");

				// Store the index, assuming that the user starts counting from
				// 1
				shapeIndex1 = Integer.parseInt(keyb.nextLine()) - 1;

				// Ask for the indices of the shapes to find distance between
				System.out.print("Enter position of second shape: ");

				// Store the index, assuming that the user starts counting from
				// 1
				shapeIndex2 = Integer.parseInt(keyb.nextLine()) - 1;

				try {
					// Get the two shapes
					Shape shapeOne = shapes.get(shapeIndex1);
					Shape shapeTwo = shapes.get(shapeIndex2);

					// Calculate distance
					double distance = shapeOne.distance(shapeTwo);

					// Print the distance between the shapes
					System.out.printf(
							"The distance between shapes %d and %d is %f\n",
							(++shapeIndex1), (++shapeIndex2), distance);
				} catch (Exception e) {
					System.out.println("One or more indexes are invalid!");
				}
				break;
			case 7:
				// Ask for the index of the shape that will be destroyed
				System.out.print("Enter position of shape to destroy: ");

				// Store the index, assuming that the user starts counting from
				// 1
				index = Integer.parseInt(keyb.nextLine()) - 1;

				try {
					// Set the reference to null
					shapes.set(index, null);

					// Notify user
					System.out.printf("Shape at %d destroyed!\n", ++index);
				} catch (Exception e) {
					System.out.println("Index not valid!");
				}
				break;
			case 8:
				// Upcast the Shape ArrayList to an Object array
				Object[] shapeArray = shapes.toArray();

				// Traverse array and print each Shape
				for (int i = 0; i < shapeArray.length; i++) {
					//Print active objects, i.e. not destroyed
					if(shapeArray[i] != null) {
						System.out.printf("%d: %s\n", (i + 1), shapeArray[i]);
					}
				}
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
}
