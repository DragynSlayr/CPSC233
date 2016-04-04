package lab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener {

	private View view;
	private Model model;

	public ButtonListener(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pushed = (JButton) e.getSource();
		switch (pushed.getName()) {
		case "change_temperature":
			view.setVisible(view.temperatureInputFrame, true);
			break;
		case "change_humidity":
			view.setVisible(view.humidityInputFrame, true);
			break;
		case "change_soil_moisture":
			view.setVisible(view.soilMoistureInputFrame, true);
			break;
		case "set_temperature":
			System.out.println(pushed.getName());
			break;
		case "set_humidity":
			System.out.println(pushed.getName());
			break;
		case "set_soil_moisture":
			System.out.println(pushed.getName());
			break;
		case "finalize_temperature":
			System.out.println(pushed.getName());
			break;
		case "finalize_humidity":
			System.out.println(pushed.getName());
			break;
		case "finalize_soil_moisture":
			System.out.println(pushed.getName());
			break;
		case "finalize_simulation":
			System.out.println(pushed.getName());
			break;
		case "start_simulation":
			view.setVisible(view.rateInputFrame, true);
			break;
		case "stop_simulation":
			System.out.println(pushed.getName());
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
