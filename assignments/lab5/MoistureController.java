package lab5;

public class MoistureController extends Thread {

	private View view;
	private double currentMoisture, externalChange, minimumMoisture,
			maximumMoisture, sprinklerChange;
	private int delay;
	private boolean running, paused;

	public MoistureController(View view) {
		this.view = view;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void update() {
		this.currentMoisture = Double.parseDouble(view.soilMoistureOutput
				.getText());
		this.externalChange = Double
				.parseDouble(view.externalMoistureChangeInput.getText());
		this.minimumMoisture = Double.parseDouble(view.minimumMoistureInput
				.getText());
		this.maximumMoisture = Double.parseDouble(view.maximumMoistureInput
				.getText());
		this.sprinklerChange = Double.parseDouble(view.moisturizingRateInput
				.getText());
		this.delay = Integer.parseInt(view.soilMoistureUpdateRateInput
				.getText());
	}

	@Override
	public void run() {
		this.running = true;
		this.paused = false;
		try {
			while (running) {
				if (!this.paused) {
					view.soilMoistureOutput.setText(String
							.valueOf(++this.currentMoisture));
					sleep(delay * 1000);
				}
			}
		} catch (InterruptedException ie) {
		}
	}
}
