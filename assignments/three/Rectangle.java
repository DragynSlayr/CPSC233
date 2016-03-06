package three;

/**
 * Class for representing a Rectangle
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Rectangle extends Shape {

	// Instance variables
	private double length, width;

	/**
	 * Create a Rectangle with specified values
	 * 
	 * @param length
	 *            The length of the Rectangle
	 * @param width
	 *            The width of the Rectangle
	 * @param x
	 *            The x coordinate of the location
	 * @param y
	 *            The y coordinate of the location
	 */
	public Rectangle(double length, double width, double x, double y) {
		// Call the Shape class' constructor with the location
		super(x, y);

		// Store the parameters
		this.length = length;
		this.width = width;
	}

	/**
	 * Create a Rectangle with default values
	 */
	public Rectangle() {
		// Call designated constructor
		this(0.0, 0.0, 0.0, 0.0);
	}

	/**
	 * Set the width of this Rectangle
	 * 
	 * @param width
	 *            The new width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Set the length of this Rectangle
	 * 
	 * @param length
	 *            The new length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Set the length and width of this Rectangle
	 * 
	 * @param length
	 *            The new length
	 * @param width
	 *            The new width
	 */
	public void setSize(double length, double width) {
		// Call the set methods of the Rectangle
		this.setLength(length);
		this.setWidth(width);
	}

	/**
	 * Gets the length of this Rectangle
	 * 
	 * @return The side length
	 */
	public double getLength() {
		return this.length;
	}

	/**
	 * Gets the width of this Rectangle
	 * 
	 * @return The side width
	 */
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getArea() {
		// Use the area formula for squares
		double area = this.length * this.width;

		// Return the area
		return area;
	}

	@Override
	public double getCircumference() {
		// Calculate the circumference of the rectangle, the perimeter
		double perimeter = (2 * this.length) + (2 * this.width);

		// Return the perimeter
		return perimeter;
	}

	@Override
	public String toString() {
		// String to hold Rectangle information
		String rectangleInfo;

		// Store the origin, width, length, area and circumference
		rectangleInfo = "Rectangle; Origin: " + this.getOrigin()
				+ ", Dimensions: " + this.length + " x " + this.width
				+ ", Area: " + this.getArea() + ", Perimeter: "
				+ this.getCircumference();

		// Return the Rectangle information
		return rectangleInfo;
	}

}
