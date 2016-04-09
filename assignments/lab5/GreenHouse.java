package lab5;

public class GreenHouse extends Thread {

	private View view;
	private volatile boolean running, paused;
	private int delay, currentTime;

	public double currentTemperature, currentHumidity, currentSoilMoisture;
	public double controllerTemperatureChange, controllerHumidityChange,
			controllerSoilMoistureChange;
	private double externalTemperatureChange, externalHumidityChange,
			externalMoistureChange;
	private FileHandler handler;

	public GreenHouse(View view, FileHandler handler) {
		this.view = view;
		this.handler = handler;

		controllerTemperatureChange = 0.0;
		controllerHumidityChange = 0.0;
		controllerSoilMoistureChange = 0.0;

		currentTime = 0;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void update() {
		currentTemperature = Double.parseDouble(view.temperatureOutput
				.getText());
		currentHumidity = Double.parseDouble(view.humidityOutput.getText());
		currentSoilMoisture = Double.parseDouble(view.soilMoistureOutput
				.getText());

		externalTemperatureChange = Double
				.parseDouble(view.externalTemperatureChangeInput.getText());
		externalHumidityChange = Double
				.parseDouble(view.externalHumidityChangeInput.getText());
		externalMoistureChange = Double
				.parseDouble(view.externalMoistureChangeInput.getText());

		delay = Integer.parseInt(view.greenHouseUpdateRateInput.getText());

		// Adjust for delay
		externalTemperatureChange *= delay / 60.0;
		externalHumidityChange *= delay / 60.0;
		externalMoistureChange *= delay / 60.0;
	}

	private double adjustToRange(double value, double lowerLimit,
			double upperLimit) {
		if (value < lowerLimit) {
			return lowerLimit;
		} else if (value > upperLimit) {
			return upperLimit;
		} else {
			return value;
		}
	}

	private void saveSimulation() {
		handler.saveSimulation("Current Time\t" + currentTime + "s");
		handler.saveSimulation(String.format("Current Temperature\t%.2f",
				currentTemperature));
		handler.saveSimulation(String.format("Current Humidity\t%.2f",
				currentHumidity));
		handler.saveSimulation(String.format("Current Soil Moisture\t%.2f",
				currentSoilMoisture));
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
		running = true;
		paused = false;
		try {
			while (running) {
				sleep(delay * 1000);
				if (!paused) {
					double newTemperature, newHumidity, newMoisture;

					newTemperature = currentTemperature
							+ externalTemperatureChange
							+ controllerTemperatureChange;
					newHumidity = currentHumidity + externalHumidityChange
							+ controllerHumidityChange;
					newMoisture = currentSoilMoisture + externalMoistureChange
							+ controllerSoilMoistureChange;

					// Adjust values to be in a realistic range
					newHumidity = adjustToRange(newHumidity, 0.0, 100.0);
					newMoisture = adjustToRange(newMoisture, 0.0, 100.0);

					view.temperatureOutput.setText(String.format("%.2f",
							newTemperature));
					view.humidityOutput.setText(String.format("%.2f",
							newHumidity));
					view.soilMoistureOutput.setText(String.format("%.2f",
							newMoisture));

					currentTemperature = newTemperature;
					currentHumidity = newHumidity;
					currentSoilMoisture = newMoisture;

					currentTime += delay;

					saveSimulation();
				}
			}
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
	}
}
