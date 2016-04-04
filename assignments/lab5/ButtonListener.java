package lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener {

	private View view;
	private Model model;

	private Thread temperatureThread, humidityThread, soilMoistureThread;
	private boolean temperatureClosed, humidityClosed, moistureClosed;

	public ButtonListener(View view, Model model) {
		this.view = view;
		this.model = model;

		this.temperatureClosed = true;
		this.humidityClosed = true;
		this.moistureClosed = true;
	}

	private void viewSetEnabled(boolean enabled) {
		view.temperatureButton.setEnabled(enabled);
		view.humidityButton.setEnabled(enabled);
		view.soilMoistureButton.setEnabled(enabled);

		view.startButton.setEnabled(enabled);
		view.saveButton.setEnabled(enabled);
		view.loadButton.setEnabled(enabled);

		view.temperatureOutput.setEnabled(enabled);
		view.humidityOutput.setEnabled(enabled);
		view.soilMoistureOutput.setEnabled(enabled);

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

			// TODO Start threads
			break;
		case "start_simulation":
			if ((this.temperatureClosed && this.humidityClosed)
					&& this.moistureClosed) {
				view.setVisible(view.rateInputFrame, true);
			}
			break;
		case "stop_simulation":
			viewSetEnabled(true);

			// TODO Stop threads
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
