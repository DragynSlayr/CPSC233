package lab5;

public class TemperatureController extends Thread {

	private View view;
	private GreenHouse greenHouse;
	private double currentTemperature, externalChange, idealTemperature,
			furnaceChange, acChange;
	private int delay;
	private boolean running, paused;

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
		this.currentTemperature = Double.parseDouble(view.temperatureOutput
				.getText());
		this.externalChange = Double
				.parseDouble(view.externalTemperatureChangeInput.getText());
		this.idealTemperature = Double.parseDouble(view.idealTemperatureInput
				.getText());
		this.furnaceChange = Double.parseDouble(view.furnaceHeatingRateInput
				.getText());
		this.acChange = Double.parseDouble(view.acCoolingRateInput.getText());
		this.delay = Integer
				.parseInt(view.temperatureUpdateRateInput.getText());
	}

	@Override
	public void run() {
		this.running = true;
		this.paused = false;
		try {
			while (this.running) {
				if (!this.paused) {
					// view.temperatureOutput.setText(String
					// .valueOf(++this.currentTemperature));
					sleep(delay * 1000);
				}
			}
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
	}
}
