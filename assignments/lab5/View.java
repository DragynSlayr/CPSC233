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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class that creates the GUI of the program
 * 
 * @author Inderpreet Dhillon
 *
 */
public class View {

	// All the frames that the view uses
	public JFrame mainFrame, temperatureInputFrame, humidityInputFrame,
			soilMoistureInputFrame, rateInputFrame, logOutputFrame;

	// Images to use for the on and off indicators for each system
	// getClass().getResource() gets the respective resource relative to this
	// class' location
	// In this case the resource is in the same location as the class
	public final ImageIcon ON = new ImageIcon(getClass().getResource("on.png")),
			OFF = new ImageIcon(getClass().getResource("off.png"));

	// The button's for the view
	public JButton temperatureButton, humidityButton, soilMoistureButton;
	public JButton startButton, stopButton, loadButton, finalizeButton;
	public JButton setTemperatureButton, setHumidityButton,
			setSoilMoistureButton;

	// Text Fields for the view
	public JTextField temperatureOutput, humidityOutput, soilMoistureOutput;
	public JTextField temperatureUpdateRateInput, humidityUpdateRateInput,
			soilMoistureUpdateRateInput, greenHouseUpdateRateInput;
	public JTextField externalTemperatureChangeInput, idealTemperatureInput,
			furnaceHeatingRateInput, acCoolingRateInput;
	public JTextField externalHumidityChangeInput, minimumHumidityInput,
			maximumHumidityInput, humidifyingRateInput;
	public JTextField externalMoistureChangeInput, minimumMoistureInput,
			maximumMoistureInput, moisturizingRateInput;
	public JTextArea logArea;

	// Labels to hold on or off images
	public JLabel furnaceIndicator, airConditionerIndicator,
			humidifierIndicator, sprinklerIndicator;

	/**
	 * Creates the View
	 */
	public View() {
		// Create the Frame for the update rates of threads
		rateInputFrame = createRateInputFrame();

		// Create the Frame for displaying log files
		logOutputFrame = createLogOutputFrame();

		// Create the Frames for value input
		temperatureInputFrame = createTemperatureInputFrame();
		humidityInputFrame = createHumidityInputFrame();
		soilMoistureInputFrame = createSoilMoistureInputFrame();

		// Create the main frame
		mainFrame = new JFrame("Green House Simulator");

		// Create the main panel and give it a border
		JPanel mainPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Create the panels that are nested in the main panel
		JPanel parametersPanel = createParametersPanel();
		JPanel outputPanel = createOutputPanel();
		JPanel statusPanel = createStatusPanel();
		JPanel controlPanel = createControlPanel();

		// Add the panels to the main panel
		mainPanel.add(parametersPanel);
		mainPanel.add(outputPanel);
		mainPanel.add(statusPanel);
		mainPanel.add(controlPanel);

		// Add the main panel to the frame
		mainFrame.add(mainPanel);

		// Set the program to stop when the window is closed
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				super.windowClosing(we);
				System.exit(0);
			}
		});
	}

	/**
	 * Sets a frames visibility
	 * 
	 * @param frame
	 *            The frame to change the visibility of
	 * @param visible
	 *            True if the frame should be visible, False otherwise
	 */
	public void setVisible(JFrame frame, boolean visible) {
		if (visible) {
			// Automatically resize the frame to it's contents
			frame.pack();

			// Stop the frame from being resized by the user
			frame.setResizable(false);

			// Center the frame in the window
			frame.setLocationRelativeTo(null);
		}
		// Set the frame's visibility
		frame.setVisible(visible);
	}

	/**
	 * Creates a panel with buttons that can access environment input fields
	 * 
	 * @return A panel for accessing climate input
	 */
	private JPanel createParametersPanel() {
		// Create the panel and give it a border with a title
		JPanel parametersPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		parametersPanel.setBorder(BorderFactory
				.createTitledBorder("Parameters"));

		// Create the buttons for the panel and assign a name to each
		temperatureButton = new JButton("Temperature");
		temperatureButton.setName("change_temperature");

		humidityButton = new JButton("Humidity");
		humidityButton.setName("change_humidity");

		soilMoistureButton = new JButton("Soil Moisture");
		soilMoistureButton.setName("change_soil_moisture");

		// Add the buttons to the panel
		parametersPanel.add(temperatureButton);
		parametersPanel.add(humidityButton);
		parametersPanel.add(soilMoistureButton);

		// Return the panel
		return parametersPanel;
	}

	/**
	 * Creates a panel with fields for starting climate input and running
	 * climate output
	 * 
	 * @return A panel for current climate
	 */
	private JPanel createOutputPanel() {
		// Create a panel and give it a border with a title
		JPanel outputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		outputPanel
				.setBorder(BorderFactory.createTitledBorder("Current State"));

		// Create labels
		JLabel temperatureLabel = new JLabel("Temperature");
		JLabel humidifierLabel = new JLabel("% Humidity");
		JLabel soilMoistureLabel = new JLabel("% Soil Moisture");

		// Create the fields
		temperatureOutput = new JTextField("20");
		humidityOutput = new JTextField("35");
		soilMoistureOutput = new JTextField("45");

		// Add the labels and fields to the panel
		outputPanel.add(temperatureLabel);
		outputPanel.add(temperatureOutput);
		outputPanel.add(humidifierLabel);
		outputPanel.add(humidityOutput);
		outputPanel.add(soilMoistureLabel);
		outputPanel.add(soilMoistureOutput);

		// Return the panel
		return outputPanel;
	}

	/**
	 * Creates a panel for climate control systems indicators
	 * 
	 * @return A panel with indicators for each system
	 */
	private JPanel createStatusPanel() {
		// Create a panel and set a titled border
		JPanel statusPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		statusPanel.setBorder(BorderFactory.createTitledBorder("Status"));

		// Create labels
		JLabel furnaceLabel = new JLabel("Furnace");
		JLabel airConditionerLabel = new JLabel("Air Conditioner");
		JLabel humidifierLabel = new JLabel("Humidifier");
		JLabel sprinklerLabel = new JLabel("Sprinkler");

		// Create indicator labels, all set to show 'off' image
		furnaceIndicator = new JLabel(OFF);
		airConditionerIndicator = new JLabel(OFF);
		humidifierIndicator = new JLabel(OFF);
		sprinklerIndicator = new JLabel(OFF);

		// Add the labels to the panel
		statusPanel.add(furnaceLabel);
		statusPanel.add(furnaceIndicator);
		statusPanel.add(airConditionerLabel);
		statusPanel.add(airConditionerIndicator);
		statusPanel.add(humidifierLabel);
		statusPanel.add(humidifierIndicator);
		statusPanel.add(sprinklerLabel);
		statusPanel.add(sprinklerIndicator);

		// Return the panel
		return statusPanel;
	}

	/**
	 * Creates a panel with components to control the simulation
	 * 
	 * @return A panel with simulation controls
	 */
	private JPanel createControlPanel() {
		// Create the panel and set a titled border
		JPanel controlPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

		// Create buttons and assign a name to each
		startButton = new JButton("Start");
		startButton.setName("start_simulation");

		stopButton = new JButton("Stop");
		stopButton.setName("stop_simulation");

		loadButton = new JButton("Load");
		loadButton.setName("load_simulation");

		// Disable the stop button
		stopButton.setEnabled(false);

		// Add all the buttons to the panel
		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		controlPanel.add(loadButton);

		// Return the panel
		return controlPanel;
	}

	/**
	 * Create a panel for temperature controls
	 * 
	 * @return A panel for the temperature controls
	 */
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

		externalTemperatureChangeInput = new JTextField("3");
		idealTemperatureInput = new JTextField("20");
		furnaceHeatingRateInput = new JTextField("5");
		acCoolingRateInput = new JTextField("4");

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

		externalHumidityChangeInput = new JTextField("-2");
		minimumHumidityInput = new JTextField("30");
		maximumHumidityInput = new JTextField("40");
		humidifyingRateInput = new JTextField("12");

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

		externalMoistureChangeInput = new JTextField("-1");
		minimumMoistureInput = new JTextField("40");
		maximumMoistureInput = new JTextField("50");
		moisturizingRateInput = new JTextField("3");

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

	/**
	 * Create the Frame that will take input for thread update frequency
	 * 
	 * @return A Frame for thread update frequency input
	 */
	private JFrame createRateInputFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		inputPanel.setBorder(BorderFactory
				.createTitledBorder("Update Rate in Seconds"));

		JLabel temperatureLabel = new JLabel("Temperature");
		JLabel humidityLabel = new JLabel("Humidity");
		JLabel soilMoistureLabel = new JLabel("Soil Moisture");
		JLabel greenHouseLabel = new JLabel("Green House");

		temperatureUpdateRateInput = new JTextField("3");
		humidityUpdateRateInput = new JTextField("3");
		soilMoistureUpdateRateInput = new JTextField("3");
		greenHouseUpdateRateInput = new JTextField("3");

		inputPanel.add(temperatureLabel);
		inputPanel.add(temperatureUpdateRateInput);
		inputPanel.add(humidityLabel);
		inputPanel.add(humidityUpdateRateInput);
		inputPanel.add(soilMoistureLabel);
		inputPanel.add(soilMoistureUpdateRateInput);
		inputPanel.add(greenHouseLabel);
		inputPanel.add(greenHouseUpdateRateInput);

		frame.add(inputPanel);

		finalizeButton = new JButton("Start with Parameters");
		finalizeButton.setName("finalize_simulation");
		finalizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		frame.add(finalizeButton);

		return frame;
	}

	/**
	 * Create the Frame that will display log files
	 * 
	 * @return A Frame for showing saved simulations
	 */
	private JFrame createLogOutputFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel inputPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		logArea = new JTextArea();
		logArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(logArea);

		inputPanel.add(scrollPane);
		frame.add(inputPanel);

		return frame;
	}
}
