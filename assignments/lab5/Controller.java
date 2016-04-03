package lab5;

public class Controller {

	public Controller(View view) {

	}

	public static void main(String[] args) {
		View view = new View();

		new Controller(view);

		view.setVisible(true);
	}
}
