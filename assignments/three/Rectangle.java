package three;

public class Rectangle extends Shape {

	private double length, width;

	public Rectangle(double length, double width, double x, double y) {
		super(x, y);
		this.length = length;
		this.width = width;
	}

	public Rectangle() {
		this(0.0, 0.0, 0.0, 0.0);
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setSize(double length, double width) {
		this.setLength(length);
		this.setWidth(width);
	}

	public double getLength() {
		return this.length;
	}

	public double getWidth() {
		return this.width;
	}

	@Override
	public double getArea() {
		return this.length * this.width;
	}

	@Override
	public double getCircumference() {
		return (2 * this.length) + (2 * this.width);
	}

	@Override
	public String toString() {
		String rectangleInfo;

		rectangleInfo = "Origin: " + this.getOrigin() + ", Dimensions: "
				+ this.length + " x " + this.width + ", Area: "
				+ this.getArea() + ", Perimeter: " + this.getCircumference();

		return rectangleInfo;
	}

}
