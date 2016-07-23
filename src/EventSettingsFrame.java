import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// TODO: Auto-generated Javadoc
/**
 * The JFrame used as the Event module settings window.
 */
public class EventSettingsFrame extends JFrame {
	
	/** The default flight size. */
	private static int defaultFlightSize = 3;

	/**
	 * Instantiates a new event settings frame which allows the user to save, load, export, and change the default flight size.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param settingsButton the settings button
	 * @param currentViewList the current view list
	 */
	public EventSettingsFrame(List<Event> eventList, JPanel data, JButton settingsButton, List<Event> currentViewList) {
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
	this.setSize(350,220);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setVisible(true);
	this.add(mainPanel);
	
	JPanel settingsTitlePanel = new JPanel();
	JLabel settingsTitle = new JLabel("Event Settings");
	settingsTitlePanel.add(settingsTitle);
	mainPanel.add(settingsTitlePanel, Component.CENTER_ALIGNMENT);

	JPanel savePanel = new JPanel();
	JButton save = new JButton("Save Event List");
	save.setPreferredSize(new Dimension(300,30));
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			SaveFrame save = new SaveFrame(data, eventList, null);
		}
	});		
	savePanel.add(save);
	mainPanel.add(savePanel,Component.CENTER_ALIGNMENT);
	
	JPanel loadPanel = new JPanel();
	JButton load = new JButton("Load Event List");
	load.setPreferredSize(new Dimension(300,30));
	load.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			LoadFrame load = new LoadFrame(data, eventList, null);
		}
	});		
	loadPanel.add(load);
	mainPanel.add(loadPanel,Component.CENTER_ALIGNMENT);
	
	JPanel exportPanel = new JPanel();
	JButton export = new JButton("Export to Image");
	export.setPreferredSize(new Dimension(300,30));
	export.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			ExportPngFrame frame = new ExportPngFrame(data);
		}
	});		
	exportPanel.add(export);
	mainPanel.add(exportPanel,Component.CENTER_ALIGNMENT);
	
	JPanel flightPanel = new JPanel();
	JLabel flightLabel = new JLabel("Default Flight Size: ");
	flightPanel.add(flightLabel);
	JComboBox<String> flightBox = new JComboBox<String>();
	for (int i = 1; i <= 10; i++)
		flightBox.addItem(String.valueOf(i));
	flightBox.addItem("None");
	flightBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			if (flightBox.getSelectedItem().equals("None"))
				defaultFlightSize = (Integer.MAX_VALUE);
			else defaultFlightSize = (Integer.parseInt((String)flightBox.getSelectedItem()));
		}
	});	
	if (defaultFlightSize != Integer.MAX_VALUE)
		flightBox.setSelectedIndex(defaultFlightSize-1);
	else flightBox.setSelectedItem(9);
	flightPanel.add(flightBox);
	mainPanel.add(flightPanel,Component.CENTER_ALIGNMENT);
	
	this.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent we) {
		    settingsButton.setEnabled(true);
		  }
		});
	}
	
	/**
	 * Gets the default flight size.
	 *
	 * @return the default flight size
	 */
	public static int getDefaultFlightSize() {
		return defaultFlightSize;
	}
}
