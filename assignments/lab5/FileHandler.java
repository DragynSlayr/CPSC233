package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {

	// Instance variables
	private View view;
	private PrintWriter writer;

	/**
	 * Creates a File Handler object
	 * 
	 * @param view
	 *            The view to get values from
	 */
	public FileHandler(View view) {
		// Store parameter
		this.view = view;

		// Create a File object
		File file = new File("save.txt");

		try {
			if (!file.exists()) {
				// Create the file if it is not present
				file.createNewFile();
			}

			// Create the writer
			writer = new PrintWriter(new FileWriter(file));
		} catch (IOException ioe) {
			// Display the error
			System.err.println(ioe.getMessage());
		}
	}

	/**
	 * Retrieves and saves values from the View
	 */
	public void saveStartingValues() {
		saveSimulation("~~~~~~~~~~~~~~~~~Starting Values~~~~~~~~~~~~~~~~~");

		// Save starting input values
		saveSimulation("Starting Temperature\t"
				+ view.temperatureOutput.getText());
		saveSimulation("Starting Humidity\t" + view.humidityOutput.getText());
		saveSimulation("Starting Soil Moisture\t"
				+ view.soilMoistureOutput.getText());

		// Save temperature data
		saveSimulation("External Temperature Change\t"
				+ view.externalTemperatureChangeInput.getText() + " per minute");
		saveSimulation("Desired Temperature\t"
				+ view.idealTemperatureInput.getText());
		saveSimulation("Furnace Heating Rate\t"
				+ view.furnaceHeatingRateInput.getText() + " per minute");
		saveSimulation("AC Cooling Rate\t" + view.acCoolingRateInput.getText()
				+ " per minute");

		// Save humidity data
		saveSimulation("External Humidity Change\t"
				+ view.externalHumidityChangeInput.getText() + " per minute");
		saveSimulation("Minimum Humidity\t"
				+ view.minimumHumidityInput.getText());
		saveSimulation("Maximum Humidity\t"
				+ view.maximumHumidityInput.getText() + " per minute");
		saveSimulation("Humidifying Rate\t"
				+ view.humidifyingRateInput.getText() + " per minute");

		// Save soil moisture data
		saveSimulation("External Soil Moisture Change\t"
				+ view.externalMoistureChangeInput.getText() + " per minute");
		saveSimulation("Minimum Soil Moisture\t"
				+ view.minimumMoistureInput.getText());
		saveSimulation("Maximum Soil Humidity\t"
				+ view.maximumMoistureInput.getText() + " per minute");
		saveSimulation("Moisturizing Rate\t"
				+ view.moisturizingRateInput.getText() + " per minute");

		// Save update rates
		saveSimulation("Temperature Update Rate\t"
				+ view.temperatureUpdateRateInput.getText() + " per second");
		saveSimulation("Humidity Update Rate\t"
				+ view.humidifyingRateInput.getText() + " per second");
		saveSimulation("Soil Moisture Update Rate\t"
				+ view.soilMoistureUpdateRateInput.getText() + " per second");
		saveSimulation("Green House Update Rate\t"
				+ view.greenHouseUpdateRateInput.getText() + " per second");

		saveSimulation("");
		saveSimulation("~~~~~~~~~~~~~~~~~Running Values~~~~~~~~~~~~~~~~~");
	}

	/**
	 * Saves data to the file
	 * 
	 * @param data
	 *            The data to save
	 */
	public void saveSimulation(String data) {
		// Print data with a new line
		writer.println(data);

		// Cause all remaining data in the PrintWriter's buffer to be written
		// to the file
		writer.flush();
	}

	/**
	 * Gets all data from the save file
	 * 
	 * @return The data from the save file
	 */
	public String loadSimulation() {
		try {
			// Create a BufferedReader for reading the file
			BufferedReader reader = new BufferedReader(new FileReader(
					"save.txt"));

			// A String to hold the lines of the file
			String lines = "";

			// Read the first line of the file
			String line = reader.readLine();

			// Read lines of the file until you reach the end
			while (line != null) {
				// Append line to current lines
				lines += line + "\n";

				// Read next line
				line = reader.readLine();
			}

			// Close the reader
			reader.close();

			// Return the file contents
			return lines;
		} catch (IOException ioe) {
			// Return the error message if an error is encountered
			return ioe.getMessage();
		}
	}

	protected void finalize() throws Throwable {
		// Close the writer
		writer.close();
	}
}
