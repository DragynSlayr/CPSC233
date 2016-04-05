package lab5;

public class Controller {

	public Controller(View view) {
		ButtonListener buttonListener = new ButtonListener(view);

		view.temperatureButton.addActionListener(buttonListener);
		view.humidityButton.addActionListener(buttonListener);
		view.soilMoistureButton.addActionListener(buttonListener);

		view.setTemperatureButton.addActionListener(buttonListener);
		view.setHumidityButton.addActionListener(buttonListener);
		view.setSoilMoistureButton.addActionListener(buttonListener);

		view.finalizeButton.addActionListener(buttonListener);

		view.startButton.addActionListener(buttonListener);
		view.stopButton.addActionListener(buttonListener);
		view.saveButton.addActionListener(buttonListener);
		view.loadButton.addActionListener(buttonListener);
	}

	public static void main(String[] args) {
		View view = new View();

		new Controller(view);

		view.setVisible(view.mainFrame, true);
	}
}
