import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;

// TODO: Auto-generated Javadoc
/**
 * The JFrame used as the interface to receive data about a new student.
 */
public class NewEventFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 678939L; 
	
	/** The new event. */
	private Event newEvent = new Event();

	/**
	 * Instantiates a new new event frame.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param newEventButton the new event button
	 * @param currentViewList the current view list
	 */
	public NewEventFrame (List<Event> eventList, JPanel data, JButton newEventButton, List<Event> currentViewList) {
		JPanel mainPanel = new JPanel();
		this.setSize(500,280);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		mainPanel.setLayout(new WrapLayout());
		mainPanel.setSize(500,260);

		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(490,35));
		JLabel title = new JLabel("Add New Event");
		titlePanel.add(title);
		mainPanel.add(titlePanel);

		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(490,35));

		JLabel name = new JLabel("Event Name:");
		namePanel.add(name);
		JTextField nameBar = new JTextField(30);
		nameBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				newEvent.setName(nameBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				newEvent.setName(nameBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		namePanel.add(nameBar);
		mainPanel.add(namePanel);

		JPanel acronymPanel = new JPanel();
		acronymPanel.setPreferredSize(new Dimension(490,35));

		JLabel acronym = new JLabel("Event Acronym:");
		acronymPanel.add(acronym);
		JTextField acronymBar = new JTextField(5);
		acronymBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				newEvent.setAcronym(acronymBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				newEvent.setAcronym(acronymBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		acronymPanel.add(acronymBar);

		JLabel flight = new JLabel("\t\tMax Flight Size:");
		acronymPanel.add(flight);
		JComboBox<String> flightBox = new JComboBox<String>();
		for (int i = 1; i <= 10; i++)
			flightBox.addItem(String.valueOf(i));
		flightBox.addItem("None");
		flightBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (flightBox.getSelectedItem().equals("None"))
					newEvent.setFlightSize(Integer.MAX_VALUE);
				else newEvent.setFlightSize(Integer.parseInt((String)flightBox.getSelectedItem()));
			}
		});	
		if (EventSettingsFrame.getDefaultFlightSize() != Integer.MAX_VALUE)
			flightBox.setSelectedIndex(EventSettingsFrame.getDefaultFlightSize()-1);
		else flightBox.setSelectedItem(9);
		acronymPanel.add(flightBox);
		newEvent.setFlightSize(EventSettingsFrame.getDefaultFlightSize());
		mainPanel.add(acronymPanel);

		JPanel eventClusterPanel = new JPanel();
		eventClusterPanel.setPreferredSize(new Dimension(490,35));

		JLabel eventCluster = new JLabel("Event Cluster:");
		eventClusterPanel.add(eventCluster);
		JComboBox<String> clusterBox = new JComboBox<String>();
		clusterBox.addItem("None");
		clusterBox.addItem("Finance");
		clusterBox.addItem("Hospitality and Tourism");
		clusterBox.addItem("Marketing");
		clusterBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newEvent.setCluster((String)clusterBox.getSelectedItem());
			}
		});	
		eventClusterPanel.add(clusterBox);
		newEvent.setCluster("None");
		mainPanel.add(eventClusterPanel);

		JPanel eventTypePanel = new JPanel();
		eventTypePanel.setPreferredSize(new Dimension(490,35));

		JLabel eventType = new JLabel("Event Type:");
		eventTypePanel.add(eventType);
		JComboBox<String> typeBox = new JComboBox<String>();
		typeBox.addItem("None");
		typeBox.addItem("Individual");
		typeBox.addItem("Team");
		typeBox.addItem("Principles");
		typeBox.addItem("Written/Presentation");
		typeBox.addItem("Other");
		typeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newEvent.setType((String)typeBox.getSelectedItem());
			}
		});	
		eventTypePanel.add(typeBox);
		newEvent.setType("None");
		mainPanel.add(eventTypePanel);

		JPanel confirmPanel = new JPanel();
		confirmPanel.setPreferredSize(new Dimension(490,35));

		JLabel errorMessage = new JLabel();
		JPanel errorSpace = new JPanel();
		errorSpace.setPreferredSize(new Dimension(300,35));
		errorSpace.add(errorMessage);
		confirmPanel.add(errorSpace);

		JButton confirm = new JButton("Confirm");
		this.getRootPane().setDefaultButton(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				errorMessage.setText("");
				if (nameBar.getText().isEmpty() || acronymBar.getText().isEmpty())
					errorMessage.setText("Name and Acronym field cannot be empty.");
				else {
					eventList.add(newEvent);
					currentViewList.add(newEvent);
						data.add(new EventDetailDataBar(newEvent,eventList,data, currentViewList));
					data.revalidate();
					newEventButton.setEnabled(true);
					closeFrame();
				}
			}
		});		
		confirmPanel.add(confirm);
		mainPanel.add(confirmPanel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				newEventButton.setEnabled(true);
			}
		});
	}

	/**
	 * Close frame.
	 */
	public void closeFrame() {
		this.setVisible(false);
	}
}


