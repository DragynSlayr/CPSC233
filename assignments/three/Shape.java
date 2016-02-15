package three;

public abstract class Shape {

	private Point origin;

	public Shape() {
		this(0.0, 0.0);
	}

	public Shape(double x, double y) {
		this.origin = new Point(x, y);
	}

	public void setOrigin(double x, double y) {
		this.origin.setPosition(x, y);
	}

	public Point getOrigin() {
		return this.origin;
	}

	public void move(double deltaX, double deltaY) {
		this.origin.move(deltaX, deltaY);
	}

	public double distance(Shape shape) {
		return this.origin.distance(shape.getOrigin());
	}

	public abstract double getArea();

	public abstract double getCircumference();

	public abstract String toString();
}
