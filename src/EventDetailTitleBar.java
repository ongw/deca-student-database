import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Title bar for the Event Module.
 */
public class EventDetailTitleBar extends JPanel{

	/**
	 * Instantiates a new event detail title bar.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list as per user settings
	 */
	public EventDetailTitleBar (List<Event> eventList, JPanel data, List<Event> currentViewList) {
		JCheckBox box = new JCheckBox("",true);
		box.setEnabled(false);
		JPanel select = new JPanel(new FlowLayout( FlowLayout.CENTER, 3, 3));
		select.add(box);
		try {
			ImageIcon triangle = new ImageIcon( getClass().getResource("triangle.png"));
			JLabel image = new JLabel(triangle);
			select.add(image);
			select.add(new JLabel(" "));
		}
		catch (NullPointerException e) {
		}
		select.setBorder(BorderFactory.createRaisedBevelBorder());
		select.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				select.setBorder(BorderFactory.createEtchedBorder());
				Component[] component = data.getComponents();
				for (int i = 0; i < component.length; i++) {
					if (component[i] instanceof EventDetailDataBar) {
						((EventDetailDataBar) component[i]).flipSelected();
					}
				}
			}

			public void mouseReleased(MouseEvent me) {
				select.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			public void mouseClicked(MouseEvent me) {
				select.setBorder(BorderFactory.createEtchedBorder());
				Component[] component = data.getComponents();
				for (int i = 0; i < component.length; i++) {
					if (component[i] instanceof StudentDetailDataBar) {
						((StudentDetailDataBar) component[i]).flipSelected();
					}
				}
			}
		});
		box.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				select.setBorder(BorderFactory.createEtchedBorder());
				Component[] component = data.getComponents();
				for (int i = 0; i < component.length; i++) {
					if (component[i] instanceof StudentDetailDataBar) {
						((StudentDetailDataBar) component[i]).flipSelected();
					}
				}
			}

			public void mouseReleased(MouseEvent me) {
				select.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			public void mouseClicked(MouseEvent me) {
				select.setBorder(BorderFactory.createEtchedBorder());
				Component[] component = data.getComponents();
				for (int i = 0; i < component.length; i++) {
					if (component[i] instanceof StudentDetailDataBar) {
						((StudentDetailDataBar) component[i]).flipSelected();
					}
				}
			}
		});
		this.add(select);
		JLabel name = new JLabel("<html><body><table>"
				+ "<td align='center' width ='276'>Event Name</td>"
				+ "<td align='center' width ='126'>Acronym</td>"
				+ "<td align='center' width ='166'>Cluster</td>"
				+ "<td align='center' width ='206'>Type</td>"
				+ "<td align='center' width ='90'>Size</td>"
				+ "</table></body></html>");
		this.add(name);
	}
}

