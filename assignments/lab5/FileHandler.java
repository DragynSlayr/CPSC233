package lab5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandler {

	private View view;
	private PrintWriter writer;
	private BufferedReader reader;

	public FileHandler(View view) {
		this.view = view;
		File file = new File("save.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			writer = new PrintWriter(new FileWriter(file));
			reader = new BufferedReader(new FileReader(file));
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

	public void saveStartingValues() {
		saveSimulation("~~~~~~~~~~~~~~~~~Starting Values~~~~~~~~~~~~~~~~~");
		saveSimulation("Starting Temperature\t"
				+ view.temperatureOutput.getText());
		saveSimulation("Starting Humidity\t" + view.humidityOutput.getText());
		saveSimulation("Starting Soil Moisture\t"
				+ view.soilMoistureOutput.getText());

		saveSimulation("External Temperature Change\t"
				+ view.externalTemperatureChangeInput.getText() + " per minute");
		saveSimulation("Desired Temperature\t"
				+ view.idealTemperatureInput.getText());
		saveSimulation("Furnace Heating Rate\t"
				+ view.furnaceHeatingRateInput.getText() + " per minute");
		saveSimulation("AC Cooling Rate\t" + view.acCoolingRateInput.getText()
				+ " per minute");

		saveSimulation("External Humidity Change\t"
				+ view.externalHumidityChangeInput.getText() + " per minute");
		saveSimulation("Minimum Humidity\t"
				+ view.minimumHumidityInput.getText());
		saveSimulation("Maximum Humidity\t"
				+ view.maximumHumidityInput.getText() + " per minute");
		saveSimulation("Humidifying Rate\t"
				+ view.humidifyingRateInput.getText() + " per minute");

		saveSimulation("External Soil Moisture Change\t"
				+ view.externalMoistureChangeInput.getText() + " per minute");
		saveSimulation("Minimum Soil Moisture\t"
				+ view.minimumMoistureInput.getText());
		saveSimulation("Maximum Soil Humidity\t"
				+ view.maximumMoistureInput.getText() + " per minute");
		saveSimulation("Moisturizing Rate\t"
				+ view.moisturizingRateInput.getText() + " per minute");

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

	public void saveSimulation(String data) {
		writer.println(data);
		writer.flush();
	}

	public String loadSimulation() {
		try {
			ArrayList<String> lines = new ArrayList<String>();

			String line = reader.readLine();

			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}

			String toReturn = "";

			for (Object o : lines.toArray()) {
				toReturn += String.valueOf(o) + "\n";
			}

			return toReturn;
		} catch (IOException ioe) {
			return ioe.getMessage();
		}
	}

	protected void finalize() throws Throwable {
		writer.close();
		reader.close();
	}
}
