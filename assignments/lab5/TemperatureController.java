package lab5;

/**
 * The Thread for monitoring and adjusting green house temperature
 * 
 * @author Inderpreet Dhillon
 *
 */
public class TemperatureController extends Thread {

	// Instance Variables
	private View view;
	private GreenHouse greenHouse;

	// Variables used while running
	private double furnaceChange, acChange, minimumTemperature,
			maximumTemperature;
	private int delay;

	// State of the Thread, volatile means this variables is accessible to a
	// single thread at a time
	private volatile boolean paused;

	/**
	 * Creates a Temperature Controller object
	 * 
	 * @param view
	 *            The view to receive values from
	 * @param greenHouse
	 *            The green house to monitor
	 */
	public TemperatureController(View view, GreenHouse greenHouse) {
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
		double idealTemperature = Double.parseDouble(view.idealTemperatureInput
				.getText());
		furnaceChange = Double.parseDouble(view.furnaceHeatingRateInput
				.getText());
		acChange = Double.parseDouble(view.acCoolingRateInput.getText());
		delay = Integer.parseInt(view.temperatureUpdateRateInput.getText());

		// Calculate range of temperature
		minimumTemperature = idealTemperature - 3.0;
		maximumTemperature = idealTemperature + 3.0;

		// Adjust change for thread delay
		furnaceChange *= delay / 60.0;
		acChange *= delay / 60.0;
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
					double currentTemperature = greenHouse.currentTemperature;

					// React to the control value
					if (currentTemperature < minimumTemperature) {
						// Modify the change in the green house
						greenHouse.controllerTemperatureChange = furnaceChange;

						// Display on indicator for furnace and off for AC
						view.furnaceIndicator.setIcon(view.ON);
						view.airConditionerIndicator.setIcon(view.OFF);
					} else if (currentTemperature > maximumTemperature) {
						// Modify the change in the green house
						greenHouse.controllerTemperatureChange = -acChange;

						// Display off indicator for furnace and on for AC
						view.furnaceIndicator.setIcon(view.OFF);
						view.airConditionerIndicator.setIcon(view.ON);
					} else {
						// Stop furnace and AC
						greenHouse.controllerTemperatureChange = 0.0;

						// Display off icon for both furnace and AC
						view.furnaceIndicator.setIcon(view.OFF);
						view.airConditionerIndicator.setIcon(view.OFF);
					}
				}
			}
		} catch (InterruptedException ie) {
			// Display the error
			System.err.println(ie.getMessage());
		}
	}
}
