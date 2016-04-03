package lab5;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {

	private JFrame frame;

	private final ImageIcon ON = new ImageIcon("assignments\\lab5\\on.png"),
			OFF = new ImageIcon("assignments\\lab5\\off.png");

	public JButton temperatureButton, humidityButton, soilMoistureButton,
			startButton, stopButton, saveButton, loadButton;

	public JTextField temperatureOutput, humidityOutput, soilMoistureOutput;

	public JLabel furnaceIndicator, airConditionerIndicator,
			humidifierIndicator, sprinklerIndicator;

	public View() {
		frame = new JFrame("Thermostat Simulator 2016");
		frame.setSize(800, 600);

		JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		JPanel parametersPanel = createParametersPanel();

		JPanel outputPanel = createOutputPanel();

		JPanel statusPanel = createStatusPanel();

		JPanel controlPanel = createControlPanel();

		mainPanel.add(parametersPanel);
		mainPanel.add(outputPanel);
		mainPanel.add(statusPanel);
		mainPanel.add(controlPanel);

		frame.add(mainPanel);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				super.windowClosing(we);
				System.exit(0);
			}
		});
	}

	public void setVisible(boolean visible) {
		frame.pack();

		frame.setResizable(false);

		frame.setLocationRelativeTo(null);

		frame.setVisible(visible);
	}

	private JPanel createParametersPanel() {
		JPanel parametersPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		parametersPanel.setBorder(BorderFactory
				.createTitledBorder("Parameters"));

		temperatureButton = new JButton("Temperature");
		humidityButton = new JButton("Humidity");
		soilMoistureButton = new JButton("Soil Moisture");

		parametersPanel.add(temperatureButton);
		parametersPanel.add(humidityButton);
		parametersPanel.add(soilMoistureButton);

		return parametersPanel;
	}

	private JPanel createStatusPanel() {
		JPanel statusPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		statusPanel.setBorder(BorderFactory.createTitledBorder("Status"));

		JLabel furnaceLabel = new JLabel("Furnace");
		JLabel airConditionerLabel = new JLabel("Air Conditioner");
		JLabel humidifierLabel = new JLabel("Humidifier");
		JLabel sprinklerLabel = new JLabel("Sprinkler");

		furnaceIndicator = new JLabel(OFF);
		airConditionerIndicator = new JLabel(OFF);
		humidifierIndicator = new JLabel(OFF);
		sprinklerIndicator = new JLabel(OFF);

		statusPanel.add(furnaceLabel);
		statusPanel.add(furnaceIndicator);
		statusPanel.add(airConditionerLabel);
		statusPanel.add(airConditionerIndicator);
		statusPanel.add(humidifierLabel);
		statusPanel.add(humidifierIndicator);
		statusPanel.add(sprinklerLabel);
		statusPanel.add(sprinklerIndicator);

		return statusPanel;
	}

	private JPanel createOutputPanel() {
		JPanel outputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

		JLabel temperatureLabel = new JLabel("Temperature");
		JLabel humidifierLabel = new JLabel("Humidifier");
		JLabel soilMoistureLabel = new JLabel("Soil Moisture");

		temperatureOutput = new JTextField("0");
		humidityOutput = new JTextField("0");
		soilMoistureOutput = new JTextField("0");

		temperatureOutput.setEditable(false);
		humidityOutput.setEditable(false);
		soilMoistureOutput.setEditable(false);

		outputPanel.add(temperatureLabel);
		outputPanel.add(temperatureOutput);
		outputPanel.add(humidifierLabel);
		outputPanel.add(humidityOutput);
		outputPanel.add(soilMoistureLabel);
		outputPanel.add(soilMoistureOutput);

		return outputPanel;
	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(4, 1, 10, 10));
		controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");

		stopButton.setEnabled(false);

		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		controlPanel.add(saveButton);
		controlPanel.add(loadButton);

		return controlPanel;
	}
}
