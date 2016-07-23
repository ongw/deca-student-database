import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.border.LineBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class FlightDetailDataBar.
 */
public class FlightDetailDataBar extends JPanel{

	/** The event. */
	private Event event;
	
	/** The check box. */
	private JCheckBox checkBox = new JCheckBox(); 

	/**
	 * Instantiates a new flight detail data bar.
	 *
	 * @param flight the flight
	 * @param flightList the flight list
	 * @param data the data
	 * @param currentViewList the current view list
	 * @param studentList the student list
	 */
	public FlightDetailDataBar (Flight flight, List<Flight> flightList, JPanel data, List<Flight> currentViewList, List<Student> studentList) {
		this.event = event;
		this.setLayout(new WrapLayout());
		this.setBorder(new EmptyBorder(-2, 0, 0, 0));
		this.setSize(950, 1);

		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(940,30));

		if (flight.getSize() != 0) {
			JLabel title;
			if (flight.getEvent().equals(Event.NULL_EVENT))
				title = new JLabel("No Event");
			else title = new JLabel(flight.getEvent().getName() + " (" + flight.getEvent().getAcronym() + ")");
			title.setFont(new Font(title.getFont().getName(), Font.BOLD, title.getFont().getSize()));
			titlePanel.add(title);
			this.add(titlePanel);

			JPanel namePanel = new JPanel();
			namePanel.setLayout(new WrapLayout());
			namePanel.setBorder(new LineBorder(Color.BLACK));
			this.setSize(940, 1);

			if (!flight.getEvent().getType().equals("Team") && !flight.getEvent().getType().equals("Written/Presentation")) {
				for (int i = 0; i < flight.getSize(); i++) {
					JPanel name = new JPanel();
					name.setPreferredSize(new Dimension(930,30));
					JLabel student = new JLabel(flight.getStudentList().get(i).toString());
					name.add(student);
					namePanel.add(name);
					if (i != flight.getSize()-1)
					namePanel.add(horizontalSeparator());
				}
			}
			else {
				for (int i = 0; i < flight.getSize(); i++) {
					JPanel name = new JPanel();
					name.setPreferredSize(new Dimension(930,30));
					JLabel student = new JLabel(flight.getStudentList().get(i).toString());
					for (int j = 0; j < studentList.size(); j++) {
						if (studentList.get(j).getName().equals(flight.getStudentList().get(i).getPartnerName()) &&  
								studentList.get(j).getPartnerName().equals(flight.getStudentList().get(i).getName())) {
							student = new JLabel(flight.getStudentList().get(i).toString() + " & " + studentList.get(j).toString());
						}
					}
					name.add(student);
					namePanel.add(name);
					if (i != flight.getSize()-1)
						namePanel.add(horizontalSeparator());
				}
			}
			this.add(namePanel);
		}
		else this.setVisible(false);
	}

	/**
	 * Horizontal separator.
	 *
	 * @return the j separator
	 */
	public static JSeparator horizontalSeparator() {
		JSeparator separator = new JSeparator();
		separator.setBorder(BorderFactory.createLineBorder(Color.gray));
		separator.setPreferredSize(new Dimension(940, 1));
		return separator;
	}
}
