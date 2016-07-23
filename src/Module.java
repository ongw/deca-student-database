import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Main module in which the main method is held.
 */
public class Module extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 77542147L;
	
	/** The student list. */
	private static List<Student> studentList = new ArrayList<Student>();
	
	/** The event list. */
	private static List<Event> eventList = new ArrayList<Event>();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String args []) {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JTabbedPane modulePane = new JTabbedPane();
		modulePane.addTab("<html><body><table width='200'><td align='center'>"
				+ "Students</td></table></body></html>", new StudentPanel(studentList, eventList));

		JPanel eventPanel = new JPanel();
		eventPanel.add(new JLabel("Events"));
		modulePane.addTab("<html><body><table width='200'><td align='center'>"
				+ "Events</td></table></body></html>", new EventPanel(studentList, eventList));

		JPanel flightPanel = new JPanel();
		flightPanel.add(new JLabel("Flights"));
		modulePane.addTab("<html><body><table width='200'><td align='center'>"
				+ "Flights</td></table></body></html>", new FlightPanel(studentList, eventList));

		mainPanel.add(modulePane);

		JFrame frame = new JFrame();
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}