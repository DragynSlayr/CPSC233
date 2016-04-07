package lab5;

public class TemperatureController extends Thread {

	private View view;
	private GreenHouse greenHouse;
	private double furnaceChange, acChange, minimumTemperature,
			maximumTemperature;
	private int delay;
	private volatile boolean running, paused;

	public TemperatureController(View view, GreenHouse greenHouse) {
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
		double idealTemperature = Double.parseDouble(view.idealTemperatureInput
				.getText());

		minimumTemperature = idealTemperature - 3.0;
		maximumTemperature = idealTemperature + 3.0;

		furnaceChange = Double.parseDouble(view.furnaceHeatingRateInput
				.getText());
		acChange = Double.parseDouble(view.acCoolingRateInput.getText());

		delay = Integer.parseInt(view.temperatureUpdateRateInput.getText());

		// Adjust for delay
		furnaceChange *= delay / 60.0;
		acChange *= delay / 60.0;
	}

	@Override
	public void run() {
		running = true;
		paused = false;
		try {
			while (running) {
				if (!paused) {
					double currentTemperature = greenHouse.currentTemperature;

					if (currentTemperature < minimumTemperature) {
						greenHouse.controllerTemperatureChange += furnaceChange;

						view.furnaceIndicator.setIcon(view.ON);
						view.airConditionerIndicator.setIcon(view.OFF);
					} else if (currentTemperature > maximumTemperature) {
						greenHouse.controllerTemperatureChange -= acChange;

						view.furnaceIndicator.setIcon(view.OFF);
						view.airConditionerIndicator.setIcon(view.ON);
					} else {
						greenHouse.controllerTemperatureChange = 0.0;

						view.furnaceIndicator.setIcon(view.OFF);
						view.airConditionerIndicator.setIcon(view.OFF);
					}

					sleep(delay * 1000);
				}
			}
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
	}
}
