package lab5;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {

	public JFrame mainFrame, temperatureInputFrame, humidityInputFrame,
			soilMoistureInputFrame, rateInputFrame;

	private final ImageIcon ON = new ImageIcon("assignments\\lab5\\on.png"),
			OFF = new ImageIcon("assignments\\lab5\\off.png");

	public JButton temperatureButton, humidityButton, soilMoistureButton;
	public JButton startButton, stopButton, saveButton, loadButton,
			finalizeButton;
	public JButton setTemperatureButton, setHumidityButton,
			setSoilMoistureButton;

	public JTextField temperatureOutput, humidityOutput, soilMoistureOutput;
	public JTextField temperatureUpdateRateInput, humidityUpdateRateInput,
			soilMoistureUpdateRateInput;
	public JTextField externalTemperatureChangeInput, idealTemperatureInput,
			furnaceHeatingRateInput, acCoolingRateInput;
	public JTextField externalHumidityChangeInput, minimumHumidityInput,
			maximumHumidityInput, humidifyingRateInput;
	public JTextField externalMoistureChangeInput, minimumMoistureInput,
			maximumMoistureInput, moisturizingRateInput;

	public JLabel furnaceIndicator, airConditionerIndicator,
			humidifierIndicator, sprinklerIndicator;

	public View() {
		rateInputFrame = createRateInputFrame();

		temperatureInputFrame = createTemperatureInputFrame();
		humidityInputFrame = createHumidityInputFrame();
		soilMoistureInputFrame = createSoilMoistureInputFrame();

		mainFrame = new JFrame("Green House Simulator 2016");
		mainFrame.setSize(800, 600);

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

		mainFrame.add(mainPanel);

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				super.windowClosing(we);
				System.exit(0);
			}
		});
	}

	public void setVisible(JFrame frame, boolean visible) {
		frame.pack();

		frame.setResizable(false);

		frame.setLocationRelativeTo(null);

		frame.setVisible(visible);
	}

	private JFrame createRateInputFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		inputPanel.setBorder(BorderFactory
				.createTitledBorder("Update Rate in Seconds"));

		JLabel temperatureLabel = new JLabel("Temperature");
		JLabel humidityLabel = new JLabel("Humidity");
		JLabel soilMoistureLabel = new JLabel("Soil Moisture");

		temperatureUpdateRateInput = new JTextField("3");
		humidityUpdateRateInput = new JTextField("3");
		soilMoistureUpdateRateInput = new JTextField("3");

		inputPanel.add(temperatureLabel);
		inputPanel.add(temperatureUpdateRateInput);
		inputPanel.add(humidityLabel);
		inputPanel.add(humidityUpdateRateInput);
		inputPanel.add(soilMoistureLabel);
		inputPanel.add(soilMoistureUpdateRateInput);

		frame.add(inputPanel);

		finalizeButton = new JButton("Start with Parameters");
		finalizeButton.setName("finalize_simulation");
		finalizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		frame.add(finalizeButton);

		return frame;
	}

	private JFrame createTemperatureInputFrame() {
		JFrame frame = new JFrame("Temperature Controls");
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		JLabel externalRateLabel = new JLabel("External Change per Minute");
		JLabel idealTemperatureLabel = new JLabel("Desired Temperature");
		JLabel furnaceHeatingRateLabel = new JLabel(
				"Furnace Heating rate per Minute");
		JLabel acCoolingRateLabel = new JLabel("A/C Cooling rate per Minute");

		externalTemperatureChangeInput = new JTextField("0");
		idealTemperatureInput = new JTextField("20");
		furnaceHeatingRateInput = new JTextField("0");
		acCoolingRateInput = new JTextField("0");

		inputPanel.add(externalRateLabel);
		inputPanel.add(externalTemperatureChangeInput);
		inputPanel.add(idealTemperatureLabel);
		inputPanel.add(idealTemperatureInput);
		inputPanel.add(furnaceHeatingRateLabel);
		inputPanel.add(furnaceHeatingRateInput);
		inputPanel.add(acCoolingRateLabel);
		inputPanel.add(acCoolingRateInput);

		frame.add(inputPanel);

		setTemperatureButton = new JButton("Save and Close");
		setTemperatureButton.setName("finalize_temperature");
		setTemperatureButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		frame.add(setTemperatureButton);

		return frame;
	}

	private JFrame createHumidityInputFrame() {
		JFrame frame = new JFrame("Humidity Controls");
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		JLabel externalRateLabel = new JLabel("External Change per Minute");
		JLabel minimumHumidityLabel = new JLabel("Minimum Humidity");
		JLabel maximumHumidityLabel = new JLabel("Maximum Humidity");
		JLabel humidifyingRateLabel = new JLabel("Humidifying rate per Minute");

		externalHumidityChangeInput = new JTextField("0");
		minimumHumidityInput = new JTextField("0");
		maximumHumidityInput = new JTextField("100");
		humidifyingRateInput = new JTextField("0");

		inputPanel.add(externalRateLabel);
		inputPanel.add(externalHumidityChangeInput);
		inputPanel.add(minimumHumidityLabel);
		inputPanel.add(minimumHumidityInput);
		inputPanel.add(maximumHumidityLabel);
		inputPanel.add(maximumHumidityInput);
		inputPanel.add(humidifyingRateLabel);
		inputPanel.add(humidifyingRateInput);

		frame.add(inputPanel);

		setHumidityButton = new JButton("Save and Close");
		setHumidityButton.setName("finalize_humidity");
		setHumidityButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		frame.add(setHumidityButton);

		return frame;
	}

	private JFrame createSoilMoistureInputFrame() {
		JFrame frame = new JFrame("Soil Moisture Controls");
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		JLabel externalRateLabel = new JLabel("External Change per Minute");
		JLabel minimumMoistureLabel = new JLabel("Minimum Soil Moisture");
		JLabel maximumMoistureLabel = new JLabel("Maximum Soil Moisture");
		JLabel moisturizingRateLabel = new JLabel(
				"Moisturizing rate per Minute");

		externalMoistureChangeInput = new JTextField("0");
		minimumMoistureInput = new JTextField("0");
		maximumMoistureInput = new JTextField("100");
		moisturizingRateInput = new JTextField("0");

		inputPanel.add(externalRateLabel);
		inputPanel.add(externalMoistureChangeInput);
		inputPanel.add(minimumMoistureLabel);
		inputPanel.add(minimumMoistureInput);
		inputPanel.add(maximumMoistureLabel);
		inputPanel.add(maximumMoistureInput);
		inputPanel.add(moisturizingRateLabel);
		inputPanel.add(moisturizingRateInput);

		frame.add(inputPanel);

		setSoilMoistureButton = new JButton("Save and Close");
		setSoilMoistureButton.setName("finalize_soil_moisture");
		setSoilMoistureButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		frame.add(setSoilMoistureButton);

		return frame;
	}

	private JPanel createParametersPanel() {
		JPanel parametersPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		parametersPanel.setBorder(BorderFactory
				.createTitledBorder("Parameters"));

		temperatureButton = new JButton("Temperature");
		temperatureButton.setName("change_temperature");

		humidityButton = new JButton("Humidity");
		humidityButton.setName("change_humidity");

		soilMoistureButton = new JButton("Soil Moisture");
		soilMoistureButton.setName("change_soil_moisture");

		parametersPanel.add(temperatureButton);
		parametersPanel.add(humidityButton);
		parametersPanel.add(soilMoistureButton);

		return parametersPanel;
	}

	private JPanel createOutputPanel() {
		JPanel outputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		outputPanel
				.setBorder(BorderFactory.createTitledBorder("Current State"));

		JLabel temperatureLabel = new JLabel("Temperature");
		JLabel humidifierLabel = new JLabel("% Humidity");
		JLabel soilMoistureLabel = new JLabel("% Soil Moisture");

		temperatureOutput = new JTextField("0.0");
		humidityOutput = new JTextField("0");
		soilMoistureOutput = new JTextField("0");

		outputPanel.add(temperatureLabel);
		outputPanel.add(temperatureOutput);
		outputPanel.add(humidifierLabel);
		outputPanel.add(humidityOutput);
		outputPanel.add(soilMoistureLabel);
		outputPanel.add(soilMoistureOutput);

		return outputPanel;
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

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel(new GridLayout(4, 1, 10, 10));
		controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

		startButton = new JButton("Start");
		startButton.setName("start_simulation");

		stopButton = new JButton("Stop");
		stopButton.setName("stop_simulation");

		saveButton = new JButton("Save");
		saveButton.setName("save_simulation");

		loadButton = new JButton("Load");
		loadButton.setName("load_simulation");

		stopButton.setEnabled(false);

		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		controlPanel.add(saveButton);
		controlPanel.add(loadButton);

		return controlPanel;
	}
}
