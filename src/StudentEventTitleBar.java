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
 * The main title bar for the student module.
 */
public class StudentEventTitleBar extends JPanel{

	/**
	 * Instantiates a new student event title bar.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param currentViewList the current view list
	 */
	public StudentEventTitleBar (List<Student> studentList, JPanel data, List<Student> currentViewList) {
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
	            	if (component[i] instanceof StudentEventDataBar) {
	            		((StudentEventDataBar) component[i]).flipSelected();
	            	}
	            }
	        }

	        public void mouseReleased(MouseEvent me) {
	            select.setBorder(BorderFactory.createRaisedBevelBorder());
	        }
	    });
		box.addMouseListener(new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
		            select.setBorder(BorderFactory.createEtchedBorder());
		            Component[] component = data.getComponents();
		            for (int i = 0; i < component.length; i++) {
		            	if (component[i] instanceof StudentEventDataBar) {
		            		((StudentEventDataBar) component[i]).flipSelected();
		            	}
		            }
		        }

		        public void mouseReleased(MouseEvent me) {
		            select.setBorder(BorderFactory.createRaisedBevelBorder());
		        }
		});
		this.add(select);
		JLabel name = new JLabel("<html><body><table>"
				+ "<td align='center' width ='230'>Name</td>"
				+ "<td align='center' width ='40'>Grade</td>"
				+ "<td align='center' width ='230'>Partner</td>"
				+ "<td align='center' width ='125'>Event 1</td>"
				+ "<td align='center' width ='125'>Event 2</td>"
				+ "<td align='center' width ='115'>Event 3</td>"
				+ "</table></body></html>");
		this.add(name);
	}
}

