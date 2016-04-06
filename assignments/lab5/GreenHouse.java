package lab5;

public class GreenHouse extends Thread {

	private View view;
	private boolean running, paused;
	private int delay;

	public double temperature, humidity, soilMoisture;

	public GreenHouse(View view) {
		this.view = view;
	}

	public void update() {
		this.temperature = Double.parseDouble(view.temperatureOutput.getText());
		this.humidity = Double.parseDouble(view.humidityOutput.getText());
		this.soilMoisture = Double.parseDouble(view.soilMoistureOutput
				.getText());
		this.delay = Integer.parseInt(view.greenHouseUpdateRateInput.getText());
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public void run() {
		this.running = true;
		this.paused = false;
		try {
			while (this.running) {
				if (!this.paused) {
					view.temperatureOutput.setText(String
							.valueOf(++this.temperature));
					view.humidityOutput
							.setText(String.valueOf(++this.humidity));
					view.soilMoistureOutput.setText(String
							.valueOf(++this.soilMoisture));
					sleep(delay * 1000);
				}
			}
		} catch (InterruptedException ie) {
			System.out.println("Temp broke");
		}
	}
}
