import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// TODO: Auto-generated Javadoc
/**
 * This class creates a new Frame to edit the values of a previously made event.
 */
public class EditEventFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6789398L; 
	
	/** The pre-made event to be edited. */
	private Event eventEdit = new Event();

	/**
	 * Instantiates and creates a new frame as an interface to edit an Event.
	 *
	 * @param eventList the current list of events
	 * @param data the Jpanel used as output
	 * @param event the edited Event
	 * @param currentViewList the current list of events as per the user view settings
	 */
	public EditEventFrame (List<Event> eventList, JPanel data, Event event, List<Event> currentViewList) {
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
		JLabel title = new JLabel("Edit Event");
		titlePanel.add(title);
		mainPanel.add(titlePanel);

		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(490,35));

		JLabel name = new JLabel("Event Name:");
		namePanel.add(name);
		JTextField nameBar = new JTextField(30);
		nameBar.setText(event.getName());
		eventEdit.setName(event.getName());
		nameBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				eventEdit.setName(nameBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				eventEdit.setName(nameBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A, plain text file

			}
		});	
		namePanel.add(nameBar);
		mainPanel.add(namePanel);

		JPanel acronymPanel = new JPanel();
		acronymPanel.setPreferredSize(new Dimension(490,35));

		JLabel acronym = new JLabel("Event Acronym:");
		acronymPanel.add(acronym);
		JTextField acronymBar = new JTextField(5);
		acronymBar.setText(event.getAcronym());
		eventEdit.setAcronym(event.getAcronym());
		acronymBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				eventEdit.setAcronym(acronymBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				eventEdit.setAcronym(acronymBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A, plain text file

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
					eventEdit.setFlightSize(Integer.MAX_VALUE);
				else eventEdit.setFlightSize(Integer.parseInt((String)flightBox.getSelectedItem()));
			}
		});	
		if (event.getFlightSize() != Integer.MAX_VALUE)
			flightBox.setSelectedIndex(event.getFlightSize()-1);
		else flightBox.setSelectedItem(9);
		acronymPanel.add(flightBox);
		eventEdit.setFlightSize(event.getFlightSize());
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
		clusterBox.setSelectedItem(event.getCluster());
		clusterBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eventEdit.setCluster((String)clusterBox.getSelectedItem());
			}
		});	
		eventClusterPanel.add(clusterBox);
		eventEdit.setCluster(event.getCluster());
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
		typeBox.setSelectedItem(event.getType());
		typeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eventEdit.setType((String)typeBox.getSelectedItem());
			}
		});	
		eventTypePanel.add(typeBox);
		eventEdit.setType(event.getType());
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
						event.setName(eventEdit.getName());
						event.setAcronym(eventEdit.getAcronym());
						event.setCluster(eventEdit.getCluster());
						event.setType(eventEdit.getType());
						event.setFlightSize(eventEdit.getFlightSize());
						data.removeAll();
						data.repaint();
						data.revalidate();
							for (int i = 0; i < currentViewList.size(); i++)
								data.add(new EventDetailDataBar(currentViewList.get(i), eventList, data, currentViewList));
						data.repaint();
						data.revalidate();
						closeFrame();
				}
			}
		});		
		confirmPanel.add(confirm);
		mainPanel.add(confirmPanel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				
			}
		});
	}

	/**
	 * Closes the Event Edit frame.
	 */
	public void closeFrame() {
		this.setVisible(false);
	}
}


