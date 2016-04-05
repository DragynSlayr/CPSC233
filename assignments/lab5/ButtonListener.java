package lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener {

	private View view;

	private TemperatureController temperatureThread;
	private HumidityController humidityThread;
	private MoistureController soilMoistureThread;
	private boolean temperatureClosed, humidityClosed, moistureClosed,
			threadsStarted;

	public ButtonListener(View view) {
		this.view = view;

		this.temperatureClosed = true;
		this.humidityClosed = true;
		this.moistureClosed = true;

		this.temperatureThread = new TemperatureController(this.view);
		this.humidityThread = new HumidityController(this.view);
		this.soilMoistureThread = new MoistureController(this.view);
	}

	private void viewSetEnabled(boolean enabled) {
		view.temperatureButton.setEnabled(enabled);
		view.humidityButton.setEnabled(enabled);
		view.soilMoistureButton.setEnabled(enabled);

		view.startButton.setEnabled(enabled);
		view.saveButton.setEnabled(enabled);
		view.loadButton.setEnabled(enabled);

		view.temperatureOutput.setEditable(enabled);
		view.humidityOutput.setEditable(enabled);
		view.soilMoistureOutput.setEditable(enabled);

		view.stopButton.setEnabled(!enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pushed = (JButton) e.getSource();
		switch (pushed.getName()) {
		case "change_temperature":
			this.temperatureClosed = false;
			view.setVisible(view.temperatureInputFrame, true);
			break;
		case "change_humidity":
			this.humidityClosed = false;
			view.setVisible(view.humidityInputFrame, true);
			break;
		case "change_soil_moisture":
			this.moistureClosed = false;
			view.setVisible(view.soilMoistureInputFrame, true);
			break;
		case "finalize_temperature":
			this.temperatureClosed = true;
			view.setVisible(view.temperatureInputFrame, false);
			break;
		case "finalize_humidity":
			this.humidityClosed = true;
			view.setVisible(view.humidityInputFrame, false);
			break;
		case "finalize_soil_moisture":
			this.moistureClosed = true;
			view.setVisible(view.soilMoistureInputFrame, false);
			break;
		case "finalize_simulation":
			view.setVisible(view.rateInputFrame, false);
			viewSetEnabled(false);

			temperatureThread.update();
			humidityThread.update();
			soilMoistureThread.update();

			if (!this.threadsStarted) {
				temperatureThread.start();
				humidityThread.start();
				soilMoistureThread.start();
				this.threadsStarted = true;
			} else {
				temperatureThread.setPaused(false);
				humidityThread.setPaused(false);
				soilMoistureThread.setPaused(false);
			}
			break;
		case "start_simulation":
			if ((this.temperatureClosed && this.humidityClosed)
					&& this.moistureClosed) {
				view.setVisible(view.rateInputFrame, true);
			}
			break;
		case "stop_simulation":
			viewSetEnabled(true);

			temperatureThread.setPaused(true);
			humidityThread.setPaused(true);
			soilMoistureThread.setPaused(true);
			break;
		case "save_simulation":
			System.out.println(pushed.getName());
			break;
		case "load_simulation":
			System.out.println(pushed.getName());
			break;
		default:
			System.err.println(e.getActionCommand());
			break;
		}
	}
}
