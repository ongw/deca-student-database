import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;


// TODO: Auto-generated Javadoc
/**
 * The drop down menu that appears when the ser right clicks on an event.
 */
public class RightClickEventMenu  extends JPopupMenu{

	/**
	 * Instantiates a new right click event menu which allows the user to edit or delete.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param event the event
	 * @param currentViewList the current view list
	 */
	public RightClickEventMenu(List<Event> eventList, JPanel data, Event event, List<Event> currentViewList) {
		JMenuItem delete = new JMenuItem("Delete Event");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eventList.remove(event);
				currentViewList.remove(event);
				EventMenuBar.resetView(eventList, data, currentViewList);
				data.removeAll();
				data.repaint();
				data.revalidate();
				for (int i = 0; i < currentViewList.size(); i++)
					data.add(new EventDetailDataBar(currentViewList.get(i), eventList, data, currentViewList));
				data.repaint();
				data.revalidate();
			}
		});
		this.add(delete);

		JMenuItem edit = new JMenuItem("Edit Event");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				EditEventFrame frame = new EditEventFrame(eventList, data, event, currentViewList);
			}
		});
		this.add(edit);
	}

}
