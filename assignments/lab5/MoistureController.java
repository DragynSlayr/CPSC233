package lab5;

/**
 * The Thread for monitoring and adjusting green house soil moisture
 * 
 * @author Inderpreet Dhillon
 *
 */
public class MoistureController extends Thread {

	// Instance Variables
	private View view;
	private GreenHouse greenHouse;

	// Variables used while running
	private double minimumMoisture, maximumMoisture, sprinklerChange;
	private int delay;

	// State of the Thread, volatile means this variables is accessible to a
	// single thread at a time
	private volatile boolean paused;

	/**
	 * Creates a Moisture Controller object
	 * 
	 * @param view
	 *            The view to receive values from
	 * @param greenHouse
	 *            The green house to monitor
	 */
	public MoistureController(View view, GreenHouse greenHouse) {
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
		minimumMoisture = Double.parseDouble(view.minimumMoistureInput
				.getText());
		maximumMoisture = Double.parseDouble(view.maximumMoistureInput
				.getText());
		sprinklerChange = Double.parseDouble(view.moisturizingRateInput
				.getText());
		delay = Integer.parseInt(view.soilMoistureUpdateRateInput.getText());

		// Adjust change for thread delay
		sprinklerChange *= delay / 60.0;
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
					double currentMoisture = greenHouse.currentSoilMoisture;

					// React to the control value
					if (currentMoisture < minimumMoisture) {
						// Modify the change in the green house
						greenHouse.controllerSoilMoistureChange = sprinklerChange;

						// Display on indicator
						view.sprinklerIndicator.setIcon(view.ON);
					} else if (currentMoisture > maximumMoisture) {
						// Reset the change in the green house
						greenHouse.controllerSoilMoistureChange = 0.0;

						// Display an off indicator
						view.sprinklerIndicator.setIcon(view.OFF);
					}
				}
			}
		} catch (InterruptedException ie) {
			// Display the error
			System.err.println(ie.getMessage());
		}
	}
}
