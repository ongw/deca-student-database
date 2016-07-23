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
 * The Class StudentDetailDataBar.
 */
public class StudentDetailDataBar extends JPanel{

	/** The student. */
	private Student student;
	
	/** The check box. */
	private JCheckBox checkBox = new JCheckBox(); 
	
	/** The priority. */
	private JCheckBox priority = new JCheckBox();

	/**
	 * Instantiates a new student event data bar which holds information about a student's name, grade,
	 * partners, and payment date, and .
	 * 
	 * @param student the student
	 * @param studentList the student list
	 * @param data the data
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public StudentDetailDataBar(Student student, List<Student> studentList, JPanel data, List<Student> currentViewList, List<Event> eventList) {
		this.student = student;
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
		this.setBorder(new EmptyBorder(-2, 0, 0, 0));
		this.setPreferredSize(new Dimension(950, 40));

		ImageIcon selected = new ImageIcon( getClass().getResource("fillstar.png"));
		ImageIcon unselected = new ImageIcon( getClass().getResource("emptystar.png"));
		priority.setSelectedIcon(selected);
		priority.setIcon(unselected);
		priority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				student.setPriorityStatus(priority.isSelected());
			}
		});
		priority.setSelected(student.getPriorityStatus());

		JPanel select = new JPanel(new FlowLayout( FlowLayout.CENTER, -1, 1));
		select.add(checkBox);
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				student.setSelected(checkBox.isSelected());
			}
		});
		select.add(priority);
		select.setBorder(new EmptyBorder(0, 7, 0, 7));
		select.setPreferredSize(new Dimension(70, 30));
		this.add(select);
		this.add(verticalSeparator());

		JLabel nameLabel = new JLabel(student.getName());
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		namePanel.setPreferredSize(new Dimension(210, 30));
		namePanel.add(nameLabel);
		this.add(namePanel);
		this.add(verticalSeparator());

		JLabel gradeLabel = new JLabel(String.valueOf(student.getGrade()));
		JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		gradePanel.setPreferredSize(new Dimension(50, 30));
		gradePanel.add(gradeLabel);
		this.add(gradePanel);
		this.add(verticalSeparator());

		JLabel partnerLabel = new JLabel(student.getPartnerName());
		JPanel partnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		partnerPanel.setPreferredSize(new Dimension(210, 30));
		partnerPanel.add(partnerLabel);
		this.add(partnerPanel);
		this.add(verticalSeparator());

		JLabel emailLabel = new JLabel(student.getEmail());
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		emailPanel.setPreferredSize(new Dimension(230, 30));
		emailPanel.add(emailLabel);
		this.add(emailPanel);
		this.add(verticalSeparator());

		JLabel paymentLabel = new JLabel();
		if (student.getPaymentStatus())
			paymentLabel.setText("Y");
		else paymentLabel.setText("N");
		JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		paymentPanel.setPreferredSize(new Dimension(50, 30));
		paymentPanel.add(paymentLabel);
		this.add(paymentPanel);
		this.add(verticalSeparator());

		JLabel dateLabel = new JLabel("");
		if (student.getPaymentStatus() == true) {
			dateLabel = new JLabel(student.getDay() + "/" + 
					student.getMonth() + "/" + student.getYear());
		}
		JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		datePanel.setPreferredSize(new Dimension(118, 30));
		datePanel.add(dateLabel);
		this.add(datePanel);

		JSeparator horizontalSeparator = new JSeparator();
		horizontalSeparator.setBorder(BorderFactory.createLineBorder(Color.black));
		horizontalSeparator.setPreferredSize(new Dimension(960, 1));

		this.add(horizontalSeparator);
		this.addMouseListener(new RightClickStudentListener(studentList,data, student, currentViewList, eventList));
	}

	/**
	 * Gets the student.
	 *
	 * @return the student
	 */
	public Student getStudent() {
		return this.student;
	}

	/**
	 * Flip selected.
	 */
	public void flipSelected() {
		student.setSelected(!student.getSelected());
		checkBox.setSelected(student.getSelected());
	}

	/**
	 * Flip priority.
	 */
	public void flipPriority() {
		student.setPriorityStatus(!student.getPriorityStatus());
		priority.setSelected(student.getPriorityStatus());;
	}

	/**
	 * Vertical separator.
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
