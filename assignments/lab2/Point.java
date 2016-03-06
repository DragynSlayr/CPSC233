package lab2;

/**
 * @author Inderpreet Dhillon 
 * Class for creating and modifying points on a
 *         Cartesian-plane
 */
public class Point {

	// Instance variables
	private double xPosition, yPosition;
	private int objectID;

	// Class variables
	private static int currentID = 1, activeObjects = 0;

	/**
	 * Default constructor, initializes point location to (0.0, 0.0)
	 */
	public Point() {
		// Call designated initializer
		this(0.0, 0.0);
	}

	/**
	 * The designated initializer, set x and y position and assign an ID
	 * 
	 * @param xPosition
	 *            The x position of the point
	 * @param yPosition
	 *            The y position of the point
	 */
	public Point(double xPosition, double yPosition) {
		// Set x and y position
		this.xPosition = xPosition;
		this.yPosition = yPosition;

		// Set object ID and increment current ID
		this.objectID = currentID++;

		// Increment number of active objects
		activeObjects++;
	}

	/**
	 * Set both x and y position of the point
	 * 
	 * @param newX
	 *            The new x coordinate
	 * @param newY
	 *            The new y coordinate
	 */
	public void setPosition(double newX, double newY) {
		this.xPosition = newX;
		this.yPosition = newY;
	}

	/**
	 * Sets the x position of the point
	 * 
	 * @param newX
	 *            The new x coordinate of the point
	 */
	public void setXPosition(double newX) {
		// Call setPosition with the newX and old Y
		this.setPosition(newX, this.yPosition);
	}

	/**
	 * Sets the y position of the point
	 * 
	 * @param newY
	 *            The new y coordinate of the point
	 */
	public void setYPosition(double newY) {
		// Call setPosition with newY and old X
		this.setPosition(this.xPosition, newY);
	}

	/**
	 * Get the x position of the point
	 * 
	 * @return The x coordinate of the point
	 */
	public double getXPosition() {
		return this.xPosition;
	}

	/**
	 * Get the y position of the point
	 * 
	 * @return The y coordinate of the point
	 */
	public double getYPosition() {
		return this.yPosition;
	}

	/**
	 * Move the point by an amount
	 * 
	 * @param deltaX
	 *            The amount to move the x by
	 * @param deltaY
	 *            The amount to move the y by
	 */
	public void move(double deltaX, double deltaY) {
		// Call setPosition with sum of the current position and the change
		this.setPosition(this.xPosition + deltaX, this.yPosition + deltaY);
	}

	/**
	 * Calculate the distance between this point and another
	 * 
	 * @param point
	 *            The other point
	 * @return Distance between this point and the other
	 */
	public double distance(Point point) {
		// Get displacement of second point from first
		double xDisplacement = point.xPosition - this.xPosition;
		double yDisplacement = point.yPosition - this.yPosition;

		// Square both displacements
		xDisplacement *= xDisplacement;
		yDisplacement *= yDisplacement;

		// Square root sum of displacement
		double distance = Math.sqrt(xDisplacement + yDisplacement);

		// Return distance after calculation
		return distance;
	}

	/**
	 * Distance class method, finds distance between two points
	 * 
	 * @param pointOne
	 *            The first point
	 * @param pointTwo
	 *            The second point
	 * @return The distance between the points
	 */
	public static double distance(Point pointOne, Point pointTwo) {
		// Call instance method of one of the points and return the result
		return pointOne.distance(pointTwo);
	}

	/**
	 * Get the ID of a point
	 * 
	 * @return The point ID
	 */
	public int pointID() {
		return this.objectID;
	}

	/**
	 * Get the number of active points
	 * 
	 * @return The number of active points
	 */
	public static int activeObjects() {
		return activeObjects;
	}

	// Called when an object is garbage collected
	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		// Decrement number of active objects
		activeObjects--;
	}

	// Called when an object is being printed
	@Override
	public String toString() {
		// String for point information
		String pointInfo;

		// Store the information of the point, ID and position
		pointInfo = "ID: " + this.pointID() + ", Position: (" + this.xPosition
				+ ", " + this.yPosition + ")";

		// Return information
		return pointInfo;
	}
}
