package lab5;

/**
 * This class adds functionality to the View
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Controller {

	/**
	 * Default constructor, adds functionality to the view
	 * 
	 * @param view
	 *            The view to control
	 */
	public Controller(View view) {
		// A listener for the buttons in the view
		ButtonListener buttonListener = new ButtonListener(view);

		// Add the listener to all the buttons of the view
		view.temperatureButton.addActionListener(buttonListener);
		view.humidityButton.addActionListener(buttonListener);
		view.soilMoistureButton.addActionListener(buttonListener);

		view.setTemperatureButton.addActionListener(buttonListener);
		view.setHumidityButton.addActionListener(buttonListener);
		view.setSoilMoistureButton.addActionListener(buttonListener);

		view.finalizeButton.addActionListener(buttonListener);

		view.startButton.addActionListener(buttonListener);
		view.stopButton.addActionListener(buttonListener);
		view.loadButton.addActionListener(buttonListener);
	}

	/**
	 * Main method for program
	 * 
	 * @param args
	 *            Command line arguments, unused
	 */
	public static void main(String[] args) {
		// Create a new View
		View view = new View();

		// Create the controller, using the View
		new Controller(view);

		// Show the main frame of the view
		view.setVisible(view.mainFrame, true);
	}
}
