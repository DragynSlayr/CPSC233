package three;

public class Circle extends Shape {

	private double radius;

	public Circle(double radius, double x, double y) {
		super(x, y);
		this.radius = radius;
	}

	public Circle() {
		this(0.0, 0.0, 0.0);
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return this.radius;
	}

	@Override
	public double getArea() {
		return Math.PI * (this.radius * this.radius);
	}

	@Override
	public double getCircumference() {
		return (2 * Math.PI * this.radius);
	}

	@Override
	public String toString() {
		String circleInfo;

		circleInfo = "Origin: " + this.getOrigin() + ", Radius: " + this.radius
				+ ", Area: " + this.getArea() + ", Perimeter: "
				+ this.getCircumference();

		return circleInfo;
	}

}
