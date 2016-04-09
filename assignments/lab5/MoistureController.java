package lab5;

public class MoistureController extends Thread {

	private View view;
	private GreenHouse greenHouse;
	private double minimumMoisture, maximumMoisture, sprinklerChange;
	private int delay;
	private volatile boolean running, paused;

	public MoistureController(View view, GreenHouse greenHouse) {
		this.view = view;
		this.greenHouse = greenHouse;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void update() {
		minimumMoisture = Double.parseDouble(view.minimumMoistureInput
				.getText());
		maximumMoisture = Double.parseDouble(view.maximumMoistureInput
				.getText());

		sprinklerChange = Double.parseDouble(view.moisturizingRateInput
				.getText());

		delay = Integer.parseInt(view.soilMoistureUpdateRateInput.getText());

		// Adjust for delay
		sprinklerChange *= delay / 60.0;
	}

	@Override
	public void run() {
		running = true;
		paused = false;
		try {
			while (running) {
				sleep(delay * 1000);
				if (!paused) {
					double currentMoisture = greenHouse.currentSoilMoisture;

					if (currentMoisture < minimumMoisture) {
						greenHouse.controllerSoilMoistureChange = sprinklerChange;

						view.sprinklerIndicator.setIcon(view.ON);
					} else if (currentMoisture > maximumMoisture
							|| currentMoisture >= minimumMoisture) {
						greenHouse.controllerSoilMoistureChange = 0.0;

						view.sprinklerIndicator.setIcon(view.OFF);
					}
				}
			}
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
	}
}
