package lab5;

public class HumidityController extends Thread {

	private View view;
	private GreenHouse greenHouse;
	private double minimumHumidity, maximumHumidity, humidifierChange;
	private int delay;
	private volatile boolean running, paused;

	public HumidityController(View view, GreenHouse greenHouse) {
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
		minimumHumidity = Double.parseDouble(view.minimumHumidityInput
				.getText());
		maximumHumidity = Double.parseDouble(view.maximumHumidityInput
				.getText());

		humidifierChange = Double.parseDouble(view.humidifyingRateInput
				.getText());

		delay = Integer.parseInt(view.humidityUpdateRateInput.getText());

		// Adjust for delay
		humidifierChange *= delay / 60.0;
	}

	@Override
	public void run() {
		running = true;
		paused = false;
		try {
			while (running) {
				sleep(delay * 1000);
				if (!paused) {
					double currentHumidity = greenHouse.currentHumidity;

					if (currentHumidity < minimumHumidity) {
						greenHouse.controllerHumidityChange = humidifierChange;

						view.humidifierIndicator.setIcon(view.ON);
					} else if (currentHumidity > maximumHumidity
							|| currentHumidity >= minimumHumidity) {
						greenHouse.controllerHumidityChange = 0.0;

						view.humidifierIndicator.setIcon(view.OFF);
					}
				}
			}
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
	}
}
