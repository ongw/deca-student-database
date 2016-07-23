import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Base Bar for the Event Module.
 */
public class EventBaseBar extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 334454L; 
	
	/**
	 * Instantiates a new event base bar.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list as per user selection
	 */
	public EventBaseBar(List<Event> eventList, JPanel data, List<Event> currentViewList) {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(960,30));
		this.setBorder(new EmptyBorder(0,10,0,10));
		
		JButton settings = new JButton("Settings");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				settings.setEnabled(false);
				EventSettingsFrame settingsFrame = new EventSettingsFrame(eventList, data, settings, currentViewList);
			}
		});
		this.add(settings);
		this.add(Box.createHorizontalGlue());
		
		JButton addEvent = new JButton("Add Event");
		addEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				addEvent.setEnabled(false);
				NewEventFrame addEventFrame = new NewEventFrame(eventList, data, addEvent, currentViewList);
			}
		});
		this.add(addEvent);
	}

}
