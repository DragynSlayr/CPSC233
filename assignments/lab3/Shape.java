package lab3;

/**
 * A class for representing a shape, must be extended
 * 
 * @author Inderpreet Dhillon
 *
 */
public abstract class Shape {

	// Represents the reference point for each shape
	private Point origin;

	/**
	 * Creates a shape object with default parameters
	 */
	public Shape() {
		// Call the designated constructor
		this(0.0, 0.0);
	}

	/**
	 * Create a shape at the location
	 * 
	 * @param x
	 *            The x coordinate of the location
	 * @param y
	 *            The y coordinate of the location
	 */
	public Shape(double x, double y) {
		// Set the reference point to a new instance of Point
		this.origin = new Point(x, y);
	}

	/**
	 * Set the location of the shape
	 * 
	 * @param x
	 *            The new x position
	 * @param y
	 *            The new y position
	 */
	public void setOrigin(double x, double y) {
		// Call the Point setPosition method
		this.origin.setPosition(x, y);
	}

	/**
	 * Gets the location of the Shape
	 * 
	 * @return The location of the Shape
	 */
	public Point getOrigin() {
		// Return the Point that represents the Shape's location
		return this.origin;
	}

	/**
	 * Translates the Shape by an amount
	 * 
	 * @param deltaX
	 *            The amount to move the x coordinate by
	 * @param deltaY
	 *            The amount to move the y coordinate by
	 */
	public void move(double deltaX, double deltaY) {
		// Call the move method of the Point
		this.origin.move(deltaX, deltaY);
	}

	/**
	 * Gets the distance of a Shape from this one
	 * 
	 * @param shape
	 *            The other Shape
	 * @return The distance between the origin's of each Shape
	 */
	public double distance(Shape shape) {
		// Return the distance between the origin Points
		return this.origin.distance(shape.getOrigin());
	}

	/* Abstract methods must be overridden by sub-classes */

	/**
	 * Get the inner area of the Shape
	 * 
	 * @return The area of the Shape
	 */
	public abstract double getArea();

	/**
	 * Get the length of the sides of the Shape
	 * 
	 * @return The circumference of the Shape
	 */
	public abstract double getCircumference();

	/**
	 * Get a String representation of the Shape
	 */
	public abstract String toString();
}
