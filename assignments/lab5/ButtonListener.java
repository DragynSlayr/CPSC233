package lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener {

	private View view;

	private GreenHouse greenHouseThread;
	private TemperatureController temperatureThread;
	private HumidityController humidityThread;
	private MoistureController soilMoistureThread;
	private FileHandler handler;
	private boolean temperatureClosed, humidityClosed, moistureClosed,
			threadsStarted;

	public ButtonListener(View view) {
		this.view = view;

		temperatureClosed = !view.temperatureInputFrame.isVisible();
		humidityClosed = !view.humidityInputFrame.isVisible();
		moistureClosed = !view.soilMoistureInputFrame.isVisible();

		handler = new FileHandler(view);

		greenHouseThread = new GreenHouse(view, handler);
		temperatureThread = new TemperatureController(view, greenHouseThread);
		humidityThread = new HumidityController(view, greenHouseThread);
		soilMoistureThread = new MoistureController(view, greenHouseThread);
	}

	private void viewSetEnabled(boolean enabled) {
		view.temperatureButton.setEnabled(enabled);
		view.humidityButton.setEnabled(enabled);
		view.soilMoistureButton.setEnabled(enabled);

		view.startButton.setEnabled(enabled);
		view.stopButton.setEnabled(!enabled);
		view.loadButton.setEnabled(enabled);

		view.temperatureOutput.setEditable(enabled);
		view.humidityOutput.setEditable(enabled);
		view.soilMoistureOutput.setEditable(enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pushed = (JButton) e.getSource();
		switch (pushed.getName()) {
		case "change_temperature":
			temperatureClosed = false;
			view.setVisible(view.temperatureInputFrame, true);
			break;
		case "change_humidity":
			humidityClosed = false;
			view.setVisible(view.humidityInputFrame, true);
			break;
		case "change_soil_moisture":
			moistureClosed = false;
			view.setVisible(view.soilMoistureInputFrame, true);
			break;
		case "finalize_temperature":
			temperatureClosed = true;
			view.setVisible(view.temperatureInputFrame, false);
			break;
		case "finalize_humidity":
			humidityClosed = true;
			view.setVisible(view.humidityInputFrame, false);
			break;
		case "finalize_soil_moisture":
			moistureClosed = true;
			view.setVisible(view.soilMoistureInputFrame, false);
			break;
		case "finalize_simulation":
			view.setVisible(view.rateInputFrame, false);
			viewSetEnabled(false);

			greenHouseThread.update();
			temperatureThread.update();
			humidityThread.update();
			soilMoistureThread.update();

			if (!threadsStarted) {
				handler.saveStartingValues();
				greenHouseThread.start();
				temperatureThread.start();
				humidityThread.start();
				soilMoistureThread.start();
				threadsStarted = true;
			} else {
				greenHouseThread.setPaused(false);
				temperatureThread.setPaused(false);
				humidityThread.setPaused(false);
				soilMoistureThread.setPaused(false);
			}
			break;
		case "start_simulation":
			if ((temperatureClosed && humidityClosed) && moistureClosed) {
				view.setVisible(view.rateInputFrame, true);
			}
			break;
		case "stop_simulation":
			viewSetEnabled(true);

			temperatureThread.setPaused(true);
			humidityThread.setPaused(true);
			soilMoistureThread.setPaused(true);
			greenHouseThread.setPaused(true);

			view.airConditionerIndicator.setIcon(view.OFF);
			view.furnaceIndicator.setIcon(view.OFF);
			view.humidifierIndicator.setIcon(view.OFF);
			view.sprinklerIndicator.setIcon(view.OFF);
			break;
		case "load_simulation":
			view.logArea.setText(handler.loadSimulation());
			view.setVisible(view.logOutputFrame, true);
			view.logOutputFrame.setSize(view.logOutputFrame.getWidth(), 600);
			break;
		default:
			System.err.println(e.getActionCommand());
			break;
		}
	}
}
