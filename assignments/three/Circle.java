package three;

/**
 * Class for representing a circle
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Circle extends Shape {

	// Instance variable
	private double radius;

	/**
	 * Create a Circle object with specified values
	 * 
	 * @param radius
	 *            The radius of the Circle
	 * @param x
	 *            The x coordinate of the location
	 * @param y
	 *            The y coordinate of the location
	 */
	public Circle(double radius, double x, double y) {
		// Call the Shape class' construction
		super(x, y);

		// Set the radius of the Circle
		this.radius = radius;
	}

	/**
	 * Create a Circle with default values
	 */
	public Circle() {
		// Call designated constructor with no radius, or location
		this(0.0, 0.0, 0.0);
	}

	/**
	 * Set the radius of the Circle
	 * 
	 * @param radius
	 *            The new radius for the Circle
	 */
	public void setRadius(double radius) {
		// Update the value of the radius
		this.radius = radius;
	}

	/**
	 * Get the radius of the Circle
	 * 
	 * @return The radius of this Circle
	 */
	public double getRadius() {
		// Return the radius
		return this.radius;
	}

	@Override
	public double getArea() {
		// Calculate area of the Circle using the formula
		double area = Math.PI * (this.radius * this.radius);

		// Return the area
		return area;
	}

	@Override
	public double getCircumference() {
		// Calculate circumference of the Circle using the formula
		double circumference = (2 * Math.PI * this.radius);

		// Return the circumference
		return circumference;
	}

	@Override
	public String toString() {
		//String to hold Circle information
		String circleInfo;

		//Fill the string with information
		circleInfo = "Origin: " + this.getOrigin() + ", Radius: " + this.radius
				+ ", Area: " + this.getArea() + ", Cicumference: "
				+ this.getCircumference();

		//Return the String representation of the Circle
		return circleInfo;
	}

}
