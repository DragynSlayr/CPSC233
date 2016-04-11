package lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Handles all button events
 * 
 * @author Inderpreet Dhillon
 *
 */
public class ButtonListener implements ActionListener {

	// Instance variable
	private View view;

	// Threads for the green house and controllers
	private GreenHouse greenHouseThread;
	private TemperatureController temperatureThread;
	private HumidityController humidityThread;
	private MoistureController soilMoistureThread;

	// Handler for file I/O
	private FileHandler handler;

	// Booleans for monitoring when windows are closed and threads are started
	private boolean temperatureClosed, humidityClosed, moistureClosed,
			threadsStarted;

	/**
	 * Creates a Button Listener object
	 * 
	 * @param view
	 *            The view to interact with
	 */
	public ButtonListener(View view) {
		// Store parameter
		this.view = view;

		// Check if the frames are currently closed
		temperatureClosed = !view.temperatureInputFrame.isVisible();
		humidityClosed = !view.humidityInputFrame.isVisible();
		moistureClosed = !view.soilMoistureInputFrame.isVisible();

		// Create the file handler with the view
		handler = new FileHandler(view);

		// Create all the threads
		greenHouseThread = new GreenHouse(view, handler);
		temperatureThread = new TemperatureController(view, greenHouseThread);
		humidityThread = new HumidityController(view, greenHouseThread);
		soilMoistureThread = new MoistureController(view, greenHouseThread);
	}

	/**
	 * Sets the usability of the view's components
	 * 
	 * @param enabled
	 *            True if view should be usable, False otherwise
	 */
	private void viewSetEnabled(boolean enabled) {
		view.temperatureButton.setEnabled(enabled);
		view.humidityButton.setEnabled(enabled);
		view.soilMoistureButton.setEnabled(enabled);

		view.startButton.setEnabled(enabled);
		view.stopButton.setEnabled(!enabled);
		view.loadButton.setEnabled(enabled);

		view.temperatureOutput.setEditable(enabled);
		view.humidityOutput.setEditable(enabled);
		view.soilMoistureOutput.setEditable(enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the button that was pushed
		JButton pushed = (JButton) e.getSource();

		// Switch based on the assigned name of the button
		switch (pushed.getName()) {
		case "change_temperature":
			// Open the temperature input frame
			temperatureClosed = false;
			view.setVisible(view.temperatureInputFrame, true);
			break;
		case "change_humidity":
			// Open the humidity input frame
			humidityClosed = false;
			view.setVisible(view.humidityInputFrame, true);
			break;
		case "change_soil_moisture":
			// Open the soil moisture input frame
			moistureClosed = false;
			view.setVisible(view.soilMoistureInputFrame, true);
			break;
		case "finalize_temperature":
			// Close the temperature input frame
			temperatureClosed = true;
			view.setVisible(view.temperatureInputFrame, false);
			break;
		case "finalize_humidity":
			// Close the humidity input frame
			humidityClosed = true;
			view.setVisible(view.humidityInputFrame, false);
			break;
		case "finalize_soil_moisture":
			// Close the soil moisture input frame
			moistureClosed = true;
			view.setVisible(view.soilMoistureInputFrame, false);
			break;
		case "finalize_simulation":
			// Start the simulation

			// Close the rate input frame
			view.setVisible(view.rateInputFrame, false);

			// Lock the components of the view
			viewSetEnabled(false);

			// Update the threads values
			greenHouseThread.update();
			temperatureThread.update();
			humidityThread.update();
			soilMoistureThread.update();

			// Save all user-entered values
			handler.saveStartingValues();

			if (!threadsStarted) {
				// Start the threads if they were not previously active
				greenHouseThread.start();
				temperatureThread.start();
				humidityThread.start();
				soilMoistureThread.start();

				// Threads have been started
				threadsStarted = true;
			} else {
				// Un-pause threads if they have already been started at some
				// point
				greenHouseThread.setPaused(false);
				temperatureThread.setPaused(false);
				humidityThread.setPaused(false);
				soilMoistureThread.setPaused(false);
			}
			break;
		case "start_simulation":
			// Check that all 3 input frames have been closed
			if (temperatureClosed && humidityClosed && moistureClosed) {
				// Display update rate input frame
				view.setVisible(view.rateInputFrame, true);
			}
			break;
		case "stop_simulation":
			// Enable all components of the view
			viewSetEnabled(true);

			// Pause each thread
			temperatureThread.setPaused(true);
			humidityThread.setPaused(true);
			soilMoistureThread.setPaused(true);
			greenHouseThread.setPaused(true);

			// Turn off all systems
			view.airConditionerIndicator.setIcon(view.OFF);
			view.furnaceIndicator.setIcon(view.OFF);
			view.humidifierIndicator.setIcon(view.OFF);
			view.sprinklerIndicator.setIcon(view.OFF);
			break;
		case "load_simulation":
			// Load the text from the save file into the log text area
			view.logArea.setText(handler.loadSimulation());

			// Show the log file frame
			view.setVisible(view.logOutputFrame, true);

			// Set the size of the frame
			view.logOutputFrame.setSize(view.logOutputFrame.getWidth() + 10,
					600);

			// Center the frame
			view.logOutputFrame.setLocationRelativeTo(null);
			break;
		default:
			// Print an error when a button hasn't been handled
			System.err.println(e.getActionCommand());
			break;
		}
	}
}
