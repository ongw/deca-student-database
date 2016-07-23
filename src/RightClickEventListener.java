import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving rightClickEvent events.
 * The class that is interested in processing a rightClickEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addRightClickEventListener<code> method. When
 * the rightClickEvent event occurs, a RightClickEventMenu appears
 *
 * @see RightClickEventEvent
 */
public class RightClickEventListener extends MouseAdapter{
	
	/** The event list. */
	private List<Event> eventList;
	
	/** The data. */
	private JPanel data;
	
	/** The event. */
	private Event event;
	
	/** The current view list. */
	private List<Event> currentViewList;
	
	/**
	 * Instantiates a new right click event listener.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param event the event
	 * @param currentViewList the current view list
	 */
	public RightClickEventListener(List<Event> eventList, JPanel data, Event event, List<Event> currentViewList) {
		this.eventList = eventList;
		this.data = data;
		this.event = event;
		this.currentViewList = currentViewList;
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
       RightClickEventMenu menu = new RightClickEventMenu(eventList, data, event, currentViewList);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
