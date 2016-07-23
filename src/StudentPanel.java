import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The main JPanel for the student module.
 */
public class StudentPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 752147L; 
	
	/** The student list. */
	private List<Student> studentList = new ArrayList<Student>();
	
	/** The current view list. */
	private List<Student> currentViewList = new ArrayList<Student>();
	
	/** The event list. */
	private List<Event> eventList;
	
	/**
	 * Instantiates a new student panel.
	 *
	 * @param studentList the student list
	 * @param eventList the event list
	 */
	public StudentPanel(List<Student> studentList, List<Event> eventList) {
		this.studentList = studentList;
		this.eventList = eventList;
		
		JPanel data = new JPanel();
		data.setLayout(new WrapLayout());
		data.setBorder(new EmptyBorder(0,0,0,0));
		data.setSize(960,1);
		JScrollPane scroller = new JScrollPane(data, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension(960, 400));
		
		this.add(new StudentMenuBar(studentList, data, currentViewList, eventList));
		JPanel titlePanel = new JPanel(new CardLayout());
		titlePanel.add(new StudentDetailTitleBar(studentList, data, currentViewList), "Detail");
		titlePanel.add(new StudentEventTitleBar(studentList, data, currentViewList), "Event");
		this.add(titlePanel);
		this.add(scroller);
		
		this.add(new StudentBaseBar(studentList, data, titlePanel, currentViewList, eventList));
	}
}
