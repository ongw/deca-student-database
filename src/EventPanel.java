import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The main JPanel for the Event Module.
 */
public class EventPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7752147L; 
	
	/** The event list. */
	private List<Event> eventList = new ArrayList<Event>();
	
	/** The current view list. */
	private List<Event> currentViewList = new ArrayList<Event>();
	
	/** The student list. */
	private List<Student> studentList = new ArrayList<Student>();
	
	/**
	 * Instantiates a new event panel.
	 *
	 * @param studentList the student list
	 * @param eventList the event list
	 */
	public EventPanel(List<Student> studentList, List<Event> eventList) {
		this.studentList = studentList;
		this.eventList = eventList;
		
		JPanel data = new JPanel();
		data.setLayout(new WrapLayout());
		data.setBorder(new EmptyBorder(0,0,0,0));
		data.setSize(960,1);
		JScrollPane scroller = new JScrollPane(data, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(960, 400));
		
		this.add(new EventMenuBar(eventList, data, currentViewList));
		JPanel titlePanel = new JPanel(new CardLayout());
		titlePanel.add(new EventDetailTitleBar(eventList, data, currentViewList));
		this.add(titlePanel);
		this.add(scroller);
		
		this.add(new EventBaseBar(eventList, data, currentViewList));
	}
}
