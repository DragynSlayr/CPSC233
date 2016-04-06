package lab5;

public class HumidityController extends Thread {

	private View view;
	private GreenHouse greenHouse;
	private double currentHumidity, externalChange, minimumHumidity,
			maximumHumidity, humidifierChange;
	private int delay;
	private boolean running, paused;

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
		this.currentHumidity = Double
				.parseDouble(view.humidityOutput.getText());
		this.externalChange = Double
				.parseDouble(view.externalHumidityChangeInput.getText());
		this.minimumHumidity = Double.parseDouble(view.minimumHumidityInput
				.getText());
		this.maximumHumidity = Double.parseDouble(view.maximumHumidityInput
				.getText());
		this.humidifierChange = Double.parseDouble(view.humidifyingRateInput
				.getText());
		this.delay = Integer.parseInt(view.humidityUpdateRateInput.getText());
	}

	@Override
	public void run() {
		this.running = true;
		this.paused = false;
		try {
			while (running) {
				if (!this.paused) {
					// view.humidityOutput.setText(String
					// .valueOf(++this.currentHumidity));
					sleep(delay * 1000);
				}
			}
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
	}
}
