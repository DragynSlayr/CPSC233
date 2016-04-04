package lab5;

public class Controller {

	public Controller(View view, Model model) {
		ButtonListener buttonListener = new ButtonListener(view, model);

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
		Model model = new Model();

		new Controller(view, model);

		view.setVisible(view.mainFrame, true);
	}
}
