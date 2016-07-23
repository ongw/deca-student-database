import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving rightClickStudent events.
 * The class that is interested in processing a rightClickStudent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addRightClickStudentListener<code> method. When
 * the rightClickStudent event occurs, a RightClickEventMenu appears
 *
 * @see RightClickStudentEvent
 */
public class RightClickStudentListener extends MouseAdapter{
	
	/** The student list. */
	private List<Student> studentList;
	
	/** The data. */
	private JPanel data;
	
	/** The student. */
	private Student student;
	
	/** The current view list. */
	private List<Student> currentViewList;
	
	/** The event list. */
	private List<Event> eventList;
	
	/**
	 * Instantiates a new right click student listener.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param student the student
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public RightClickStudentListener(List<Student> studentList, JPanel data, Student student, List<Student> currentViewList, List<Event> eventList) {
		this.studentList = studentList;
		this.data = data;
		this.student = student;
		this.currentViewList = currentViewList;
		this.eventList = eventList;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e){
        if (SwingUtilities.isRightMouseButton(e))
            doPop(e);
    }

    /**
     * Do pop.
     *
     * @param e the e
     */
    private void doPop(MouseEvent e){
       RightClickStudentMenu menu = new RightClickStudentMenu(studentList, data, student, currentViewList, eventList);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
