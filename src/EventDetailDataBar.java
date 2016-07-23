import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Year;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The panel which holds/outputs the data for a particular event.
 */
public class EventDetailDataBar extends JPanel{

	/** The event. */
	private Event event;
	
	/** The check box. */
	private JCheckBox checkBox = new JCheckBox(); 

	/**
	 * Instantiates a new data bar given an event.
	 *
	 * @param event the event
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list
	 */
	public EventDetailDataBar (Event event, List<Event> eventList, JPanel data, List<Event> currentViewList) {
		this.event = event;
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
		this.setBorder(new EmptyBorder(-2, 0, 0, 0));
		this.setPreferredSize(new Dimension(950, 40));

		JPanel select = new JPanel(new FlowLayout( FlowLayout.CENTER, -1, 1));
		select.add(checkBox);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				event.setSelected(checkBox.isSelected());
			}
		});
		select.setBorder(new EmptyBorder(0, 0, 0, 0));
		select.setPreferredSize(new Dimension(50, 30));
		this.add(select);
		this.add(verticalSeparator());

		JLabel nameLabel = new JLabel(event.getName());
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		namePanel.setPreferredSize(new Dimension(325, 30));
		namePanel.add(nameLabel);
		this.add(namePanel);
		this.add(verticalSeparator());
		
		JLabel acronymLabel = new JLabel(event.getAcronym());
		JPanel acronymPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		acronymPanel.setPreferredSize(new Dimension(75, 30));
		acronymPanel.add(acronymLabel);
		this.add(acronymPanel);
		this.add(verticalSeparator());
		
		JLabel clusterLabel = new JLabel(event.getCluster());
		JPanel clusterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		clusterPanel.setPreferredSize(new Dimension(210, 30));
		clusterPanel.add(clusterLabel);
		this.add(clusterPanel);
		this.add(verticalSeparator());
		
		JLabel typeLabel = new JLabel(event.getType());
		JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		typePanel.setPreferredSize(new Dimension(210, 30));
		typePanel.add(typeLabel);
		this.add(typePanel);
		this.add(verticalSeparator());
		
		JLabel sizeLabel = new JLabel();
		if (event.getFlightSize() == Integer.MAX_VALUE)
			sizeLabel = new JLabel("None");
		else sizeLabel = new JLabel(String.valueOf(event.getFlightSize()));
		JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		sizePanel.setPreferredSize(new Dimension(75, 30));
		sizePanel.add(sizeLabel);
		this.add(sizePanel);
		this.add(verticalSeparator());

		JSeparator horizontalSeparator = new JSeparator();
		horizontalSeparator.setBorder(BorderFactory.createLineBorder(Color.black));
		horizontalSeparator.setPreferredSize(new Dimension(960, 1));

		this.add(horizontalSeparator);
		this.addMouseListener(new RightClickEventListener(eventList,data, event, currentViewList));
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event getEvent() {
		return this.event;
	}

	/**
	 * Flip the selected variable.
	 */
	public void flipSelected() {
		event.setSelected(!event.getSelected());
		checkBox.setSelected(event.getSelected());
	}

	/**
	 * Creates a vertical separator.
	 *
	 * @return the j separator
	 */
	public static JSeparator verticalSeparator() {
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		separator.setPreferredSize(new Dimension(1, 30));
		return separator;
	}
}
