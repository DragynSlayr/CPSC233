package three;

import java.util.Scanner;

/**
 * @author Inderpreet Dhillon 
 * Class for testing the Point class
 */
public class Test {

	public static void main(String[] args) throws Throwable {
		// Scanner for keyboard input
		Scanner keyb = new Scanner(System.in);

		// Create references for the Points
		Point pointOne, pointTwo, pointThree, pointFour, pointFive;

		// Variables to hold x and y position values
		double xPosition, yPosition;

		// Get x and y coordinates of 3 points
		System.out.printf("Enter x coordinate of point %d: ",
				Point.activeObjects() + 1);
		xPosition = keyb.nextDouble();

		System.out.printf("Enter y coordinate of point %d: ",
				Point.activeObjects() + 1);
		yPosition = keyb.nextDouble();

		// Create the first point
		pointOne = new Point(xPosition, yPosition);

		System.out.printf("Enter x coordinate of point %d: ",
				Point.activeObjects() + 1);
		xPosition = keyb.nextDouble();

		System.out.printf("Enter y coordinate of point %d: ",
				Point.activeObjects() + 1);
		yPosition = keyb.nextDouble();

		// Create the second point
		pointTwo = new Point(xPosition, yPosition);

		System.out.printf("Enter x coordinate of point %d: ",
				Point.activeObjects() + 1);
		xPosition = keyb.nextDouble();

		System.out.printf("Enter y coordinate of point %d: ",
				Point.activeObjects() + 1);
		yPosition = keyb.nextDouble();

		// Create the third position
		pointThree = new Point(xPosition, yPosition);

		// Provide a print out of the points
		System.out.println("\nPoints Created");
		System.out.println(pointOne);
		System.out.println(pointTwo);
		System.out.println(pointThree);

		// Variables to hold the delta x and y values
		double xMovement, yMovement;

		// Get amounts to move each move each point by
		System.out.print("\nEnter x displacement: ");
		xMovement = keyb.nextDouble();

		System.out.print("Enter y displacement: ");
		yMovement = keyb.nextDouble();

		// Move all the Points
		pointOne.move(xMovement, yMovement);
		pointTwo.move(xMovement, yMovement);
		pointThree.move(xMovement, yMovement);

		// Print point information
		System.out.println("\nMoved Points");
		System.out.println(pointOne);
		System.out.println(pointTwo);
		System.out.println(pointThree);
		System.out.println("Active Objects: " + Point.activeObjects());

		// Create a default point
		pointFour = new Point();
		System.out.println("\nDefault Point\n" + pointFour);

		// Print distances between points
		System.out.println("\nDistances Between Points");
		System.out.println("1 and 4: " + pointFour.distance(pointOne));
		System.out.println("2 and 4: " + pointFour.distance(pointTwo));
		System.out.println("3 and 4: " + Point.distance(pointFour, pointThree));

		// Calculate average of the point coordinates
		double averageX = (pointOne.getXPosition() + pointTwo.getXPosition() + pointThree
				.getXPosition()) / 3;
		double averageY = (pointOne.getYPosition() + pointTwo.getYPosition() + pointThree
				.getYPosition()) / 3;

		// Create point from average coordinates
		pointFive = new Point(averageX, averageY);
		System.out.println("\nAverage Point\n" + pointFive);
		System.out.println("Active Objects: " + Point.activeObjects());

		// Delete references to first three points
		pointOne = null;
		pointTwo = null;
		pointThree = null;

		// Force garbage collection
		fullGC();

		// Print active points
		System.out.println("\nAfter Deleting 3 Points");
		System.out.println("Active Objects: " + Point.activeObjects());

		// Finished with Scanner, close it
		keyb.close();
	}

	/**
	 * A more aggressive garbage collection
	 */
	public static void fullGC() {
		Runtime rt = Runtime.getRuntime();
		long isFree = rt.freeMemory();
		long wasFree;
		do {
			wasFree = isFree;
			rt.gc();
			rt.runFinalization();
			isFree = rt.freeMemory();
		} while (isFree > wasFree);
	}
}
