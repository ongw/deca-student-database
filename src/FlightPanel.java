import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The main JPanel for the Flight Object.
 */
public class FlightPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 77525147L; 
	
	/** The event list. */
	private List<Event> eventList = new ArrayList<Event>();
	
	/** The student list. */
	private List<Student> studentList = new ArrayList<Student>();
	
	/** The current view list. */
	private List<Flight> currentViewList = new ArrayList<Flight>();
	
	/** The flight list. */
	private List<Flight> flightList = new ArrayList<Flight>();
	
	/** The flight map. */
	private Map<Event,Flight> flightMap = new HashMap<Event, Flight>();
	
	/**
	 * Instantiates a new flight JPanel which is scrollable.
	 *
	 * @param studentList the student list
	 * @param eventList the event list
	 */
	public FlightPanel(List<Student> studentList, List<Event> eventList) {
		this.eventList = eventList;
		this.studentList = studentList;
		
		JPanel data = new JPanel();
		data.setLayout(new WrapLayout());
		data.setBorder(new EmptyBorder(0,0,0,0));
		data.setSize(960,1);
		JScrollPane scroller = new JScrollPane(data, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(960, 450));
		
		this.add(new FlightMenuBar(flightList, data, currentViewList, studentList));
		this.add(scroller);
		
		this.add(new FlightBaseBar(eventList, data, currentViewList, studentList, flightList, flightMap));
	}
}
