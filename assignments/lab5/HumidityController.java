package lab5;

/**
 * The Thread for monitoring and adjusting green house humidity
 * 
 * @author Inderpreet Dhillon
 *
 */
public class HumidityController extends Thread {

	// Instance Variables
	private View view;
	private GreenHouse greenHouse;

	// Variables used while running
	private double minimumHumidity, maximumHumidity, humidifierChange;
	private int delay;

	// State of the Thread, volatile means this variables is accessible to a
	// single thread at a time
	private volatile boolean paused;

	/**
	 * Creates a Humidity Controller object
	 * 
	 * @param view
	 *            The view to receive values from
	 * @param greenHouse
	 *            The green house to monitor
	 */
	public HumidityController(View view, GreenHouse greenHouse) {
		// Store parameters
		this.view = view;
		this.greenHouse = greenHouse;
	}

	/**
	 * Sets the state of this thread
	 * 
	 * @param paused
	 *            True if pausing the thread, False otherwise
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * Updates variables of the thread with values from the view
	 */
	public void update() {
		// Get the necessary values from the view
		minimumHumidity = Double.parseDouble(view.minimumHumidityInput
				.getText());
		maximumHumidity = Double.parseDouble(view.maximumHumidityInput
				.getText());
		humidifierChange = Double.parseDouble(view.humidifyingRateInput
				.getText());
		delay = Integer.parseInt(view.humidityUpdateRateInput.getText());

		// Adjust change for thread delay
		humidifierChange *= delay / 60.0;
	}

	@Override
	public void run() {
		paused = false;
		try {
			// Loop until interrupted
			while (true) {
				// Pause for the delay in seconds
				sleep(delay * 1000);

				// Only run if not paused
				if (!paused) {
					// Get the current control value from the green house
					double currentHumidity = greenHouse.currentHumidity;

					// React to the control value
					if (currentHumidity < minimumHumidity) {
						// Modify the change in the green house
						greenHouse.controllerHumidityChange = humidifierChange;

						// Display on indicator
						view.humidifierIndicator.setIcon(view.ON);
					} else if (currentHumidity > maximumHumidity) {
						// Reset the change in the green house
						greenHouse.controllerHumidityChange = 0.0;

						// Display an off indicator
						view.humidifierIndicator.setIcon(view.OFF);
					}
				}
			}
		} catch (InterruptedException ie) {
			// Display the error
			System.err.println(ie.getMessage());
		}
	}
}
