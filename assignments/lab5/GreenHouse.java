package lab5;

public class GreenHouse extends Thread {

	// Instance variables
	private View view;
	private FileHandler handler;

	// Variables used while running
	private int delay, currentTime;

	// Doubles to hold current stats of the simulation
	public double currentTemperature, currentHumidity, currentSoilMoisture;

	// Doubles to hold the change internal changes
	public double controllerTemperatureChange, controllerHumidityChange,
			controllerSoilMoistureChange;

	// Doubles to hold external changes
	private double externalTemperatureChange, externalHumidityChange,
			externalMoistureChange;

	// State of the Thread, volatile means this variables is accessible to a
	// single thread at a time
	private volatile boolean paused;

	/**
	 * Create a GreenHouse object
	 * 
	 * @param view
	 * @param handler
	 */
	public GreenHouse(View view, FileHandler handler) {
		// Store parameters
		this.view = view;
		this.handler = handler;

		// Initialize internal changes to 0.0
		controllerTemperatureChange = 0.0;
		controllerHumidityChange = 0.0;
		controllerSoilMoistureChange = 0.0;

		// Initialized elapsed time of the system
		currentTime = 0;
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
		// Get current state after user input
		currentTemperature = Double.parseDouble(view.temperatureOutput
				.getText());
		currentHumidity = Double.parseDouble(view.humidityOutput.getText());
		currentSoilMoisture = Double.parseDouble(view.soilMoistureOutput
				.getText());

		// Get external changes after use input
		externalTemperatureChange = Double
				.parseDouble(view.externalTemperatureChangeInput.getText());
		externalHumidityChange = Double
				.parseDouble(view.externalHumidityChangeInput.getText());
		externalMoistureChange = Double
				.parseDouble(view.externalMoistureChangeInput.getText());

		// Get the delay of the thread
		delay = Integer.parseInt(view.greenHouseUpdateRateInput.getText());

		// Adjust external changes for thread delay
		externalTemperatureChange *= delay / 60.0;
		externalHumidityChange *= delay / 60.0;
		externalMoistureChange *= delay / 60.0;
	}

	/**
	 * Forces a value between two limits
	 * 
	 * @param value
	 *            The value to check
	 * @param lowerLimit
	 *            The lowest possible value
	 * @param upperLimit
	 *            The max possible value
	 * @return The value if it is between the limits, otherwise the closest
	 *         limit is returned
	 */
	private double adjustToRange(double value, double lowerLimit,
			double upperLimit) {
		if (value < lowerLimit) {
			// Return the lower limit if the value is too low
			return lowerLimit;
		} else if (value > upperLimit) {
			// Return the upper limit if the value is too high
			return upperLimit;
		} else {
			// Return the value if it is range
			return value;
		}
	}

	/**
	 * Saves the current values of the simulation
	 */
	private void saveSimulation() {
		// Save current time
		handler.saveSimulation("Current Time\t" + currentTime + "s");

		// Save current stats after formatting to 2 decimals
		handler.saveSimulation(String.format("Current Temperature\t%.2f",
				currentTemperature));
		handler.saveSimulation(String.format("Current Humidity\t%.2f",
				currentHumidity));
		handler.saveSimulation(String.format("Current Soil Moisture\t%.2f",
				currentSoilMoisture));

		// Get and save whether each system is on or off based on the current
		// indicator
		handler.saveSimulation("AC On\t"
				+ view.airConditionerIndicator.getIcon().equals(view.ON));
		handler.saveSimulation("Furnace On\t"
				+ view.furnaceIndicator.getIcon().equals(view.ON));
		handler.saveSimulation("Humidifier On\t"
				+ view.humidifierIndicator.getIcon().equals(view.ON));
		handler.saveSimulation("Sprinkler On\t"
				+ view.sprinklerIndicator.getIcon().equals(view.ON));
		handler.saveSimulation("");
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
					// Add the external and internal changes to the current
					// temperature
					currentTemperature += externalTemperatureChange
							+ controllerTemperatureChange;

					// Add the external and internal changes to the current
					// humidity
					currentHumidity += externalHumidityChange
							+ controllerHumidityChange;

					// Add the external and internal changes to the current soil
					// moisture
					currentSoilMoisture += externalMoistureChange
							+ controllerSoilMoistureChange;

					// Adjust values to be in a realistic range
					currentHumidity = adjustToRange(currentHumidity, 0.0, 100.0);
					currentSoilMoisture = adjustToRange(currentSoilMoisture,
							0.0, 100.0);

					// Format and set values
					view.temperatureOutput.setText(String.format("%.2f",
							currentTemperature));
					view.humidityOutput.setText(String.format("%.2f",
							currentHumidity));
					view.soilMoistureOutput.setText(String.format("%.2f",
							currentSoilMoisture));

					// Increment elapsed time
					currentTime += delay;

					// Save the values of the simulation
					saveSimulation();
				}
			}
		} catch (InterruptedException ie) {
			// Display the error
			System.err.println(ie.getMessage());
		}
	}
}
