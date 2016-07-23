import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;

// TODO: Auto-generated Javadoc
/**
 * This class creates a new Frame to edit the values of a previously made student.
 */
public class EditStudentFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 678839L;
	
	/** The Constant nullDate, used for the option of no date of payment in student creation. */
	private static final LocalDate nullDate = LocalDate.MAX;
	
	/** The Student object to be edited. */
	private Student studentEdit = new Student();

	/**
	 * Instantiates a new frame that allows the user to edit a pre-existing student.
	 *
	 * @param studentList the current student list
	 * @param data the JPanel which acts as main user output
	 * @param student the student to be edited
	 * @param currentViewList the current student as per the user settings
	 * @param eventList the current list of events
	 */
	public EditStudentFrame (List<Student> studentList, JPanel data, Student student, List<Student> currentViewList, List<Event> eventList ) {
		JPanel mainPanel = new JPanel();
		this.setSize(700,260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		mainPanel.setLayout(new WrapLayout());
		mainPanel.setSize(700,260);

		JLabel title = new JLabel("Edit Student");
		mainPanel.add(title);

		JPanel nameEmailPanel = new JPanel();
		nameEmailPanel.setPreferredSize(new Dimension(790,35));

		JLabel name = new JLabel("Name:");
		nameEmailPanel.add(name);
		JTextField nameBar = new JTextField(20);
		nameBar.setText(student.getName());
		studentEdit.setName(student.getName());
		nameBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				studentEdit.setName(nameBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				studentEdit.setName(nameBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		nameEmailPanel.add(nameBar);

		JLabel email = new JLabel("\t\tEmail:");
		nameEmailPanel.add(email);
		JTextField emailBar = new JTextField(20);
		emailBar.setText(student.getEmail());
		studentEdit.setEmail(student.getEmail());
		emailBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				studentEdit.setEmail(emailBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				studentEdit.setEmail(emailBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		nameEmailPanel.add(emailBar);
		mainPanel.add(nameEmailPanel);

		JPanel gradePartnerPanel = new JPanel();
		gradePartnerPanel.setPreferredSize(new Dimension(790,35));

		JLabel grade = new JLabel("Grade:");
		gradePartnerPanel.add(grade);
		JComboBox<Integer> gradeBox = new JComboBox<Integer>();
		gradeBox.addItem(new Integer(9));
		gradeBox.addItem(new Integer(10));
		gradeBox.addItem(new Integer(11));
		gradeBox.addItem(new Integer(12));
		gradeBox.setSelectedItem(student.getGrade());
		gradeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				studentEdit.setGrade((int)gradeBox.getSelectedItem());
			}
		});	
		gradePartnerPanel.add(gradeBox);
		studentEdit.setGrade(student.getGrade());

		JLabel priority = new JLabel("\t\tPriority:");
		ButtonGroup priorityGroup = new ButtonGroup();
		gradePartnerPanel.add(priority);
		JRadioButton priorityY = new JRadioButton("Y");
		priorityY.setSelected(student.getPriorityStatus());
		priorityY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				studentEdit.setPriorityStatus(true);
			}
		});	
		priorityGroup.add(priorityY);
		gradePartnerPanel.add(priorityY);
		JRadioButton priorityN = new JRadioButton("N");
		priorityN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				studentEdit.setPriorityStatus(false);
			}
		});	
		priorityGroup.add(priorityN);
		gradePartnerPanel.add(priorityN);
		if (student.getPriorityStatus())
			priorityY.setSelected(true);
		else if  (!student.getPriorityStatus())
			priorityN.setSelected(true);
		studentEdit.setPriorityStatus(student.getPriorityStatus());

		JLabel partner = new JLabel("\t\tPartner:");
		gradePartnerPanel.add(partner);
		JTextField partnerBar = new JTextField(20);
		partnerBar.setText(student.getPartnerName());
		studentEdit.setPartnerName(student.getPartnerName());
		partnerBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				studentEdit.setPartnerName(partnerBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				studentEdit.setPartnerName(partnerBar.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A
			}
		});	
		gradePartnerPanel.add(partnerBar);
		mainPanel.add(gradePartnerPanel);

		JPanel datePaymentPanel = new JPanel();
		datePaymentPanel.setPreferredSize(new Dimension(790,35));

		JYearChooser yearInput = new JYearChooser();
		yearInput.setStartYear(0);
		yearInput.setEndYear(LocalDate.now().getYear());

		Integer[] days = new Integer[31];
		for (int i = 1; i <= 31; i++)
			days[i-1] = i; 

		JComboBox<Integer> dayInput = new JComboBox<Integer>(days);

		JMonthChooser monthInput = new JMonthChooser();
		monthInput.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				int monthLength = LocalDate.of(yearInput.getYear(), monthInput.getMonth()+1, 1).lengthOfMonth();
				while (dayInput.getItemCount() < monthLength)
					dayInput.addItem(dayInput.getItemCount()+1);
				while (dayInput.getItemCount() > monthLength)
					dayInput.removeItem(dayInput.getItemCount());
				studentEdit.setYear(yearInput.getYear());
				studentEdit.setMonth(monthInput.getMonth()+1);
				studentEdit.setDay((int)dayInput.getSelectedItem());
			}
		});

		yearInput.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				studentEdit.setYear(yearInput.getYear());
				studentEdit.setMonth(monthInput.getMonth()+1);
				studentEdit.setDay((int)dayInput.getSelectedItem());
			}
		});
		dayInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				studentEdit.setYear(yearInput.getYear());
				studentEdit.setMonth(monthInput.getMonth()+1);
				studentEdit.setDay((int)dayInput.getSelectedItem());
			}
		});


		if (student.getPaymentStatus()) {
			yearInput.setYear(student.getYear());
			dayInput.setSelectedItem(student.getDay());
			monthInput.setMonth(student.getMonth()-1);
		}
		else {
			yearInput.setYear(LocalDate.now().getYear());
			dayInput.setSelectedItem(LocalDate.now().getDayOfMonth());
			monthInput.setMonth(LocalDate.now().getMonthValue()-1);
		}

		JLabel payment = new JLabel("\t\tPayment:");
		ButtonGroup paymentGroup = new ButtonGroup();
		datePaymentPanel.add(payment);
		JRadioButton paymentY = new JRadioButton("Y");
		paymentY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dayInput.setEnabled(true);
				monthInput.setEnabled(true);
				yearInput.setEnabled(true);
				studentEdit.setYear(yearInput.getYear());
				studentEdit.setMonth(monthInput.getMonth()+1);
				studentEdit.setDay((int)dayInput.getSelectedItem());
				studentEdit.setPaymentStatus(true);
			}
		});	
		paymentGroup.add(paymentY);
		datePaymentPanel.add(paymentY);
		JRadioButton paymentN = new JRadioButton("N");
		paymentN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dayInput.setEnabled(false);
				monthInput.setEnabled(false);
				yearInput.setEnabled(false);
				studentEdit.setYear(yearInput.getYear());
				studentEdit.setMonth(monthInput.getMonth()+1);
				studentEdit.setDay((int)dayInput.getSelectedItem());
				studentEdit.setPaymentStatus(false);
			}
		});	
		paymentGroup.add(paymentN);
		datePaymentPanel.add(paymentN);
		if (student.getPaymentStatus())
			paymentY.doClick();
		else if  (!student.getPaymentStatus())
			paymentN.doClick();
		studentEdit.setPaymentStatus(student.getPaymentStatus());

		JLabel date = new JLabel("\t\tDate of Payment:");
		datePaymentPanel.add(date);
		datePaymentPanel.add(dayInput);
		datePaymentPanel.add(monthInput);
		datePaymentPanel.add(yearInput);
		studentEdit.setYear(yearInput.getYear());
		studentEdit.setMonth(monthInput.getMonth()+1);
		studentEdit.setDay((int)dayInput.getSelectedItem());
		mainPanel.add(datePaymentPanel);

		JPanel eventChoicePanel = new JPanel();
		eventChoicePanel.setPreferredSize(new Dimension(790,35));

		JLabel event1 = new JLabel("Event Choice 1:");
		JComboBox<Event> event1Box = new JComboBox<Event>();
		event1Box.addItem(Event.NULL_EVENT);
		for (int i = 0; i < eventList.size(); i++)
			event1Box.addItem(eventList.get(i));
		event1Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				studentEdit.setChoice1((Event)event1Box.getSelectedItem());
			}
		});	
		event1Box.setEditable(true);
		eventChoicePanel.add(event1);
		eventChoicePanel.add(event1Box);
		studentEdit.setChoice1(student.getChoice1());

		JLabel event2 = new JLabel("\t\tEvent Choice 2:");
		JComboBox<Event> event2Box = new JComboBox<Event>();
		event2Box.addItem(Event.NULL_EVENT);
		for (int i = 0; i < eventList.size(); i++)
			event2Box.addItem(eventList.get(i));
		event2Box.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e){
				studentEdit.setChoice2((Event)event2Box.getSelectedItem());
			}
		});	
		event2Box.setEditable(true);
		eventChoicePanel.add(event2);
		eventChoicePanel.add(event2Box);
		studentEdit.setChoice2(student.getChoice2());

		JLabel event3 = new JLabel("\t\tEvent Choice 3:");
		JComboBox<Event> event3Box = new JComboBox<Event>();
		event3Box.addItem(Event.NULL_EVENT);
		for (int i = 0; i < eventList.size(); i++)
			event3Box.addItem(eventList.get(i));
		event1Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				studentEdit.setChoice3((Event)event3Box.getSelectedItem());
			}
		});	
		event3Box.setEditable(true);
		eventChoicePanel.add(event3);
		studentEdit.setChoice3(student.getChoice3());
		eventChoicePanel.add(event3Box);
		mainPanel.add(eventChoicePanel);

		JPanel confirmPanel = new JPanel();
		confirmPanel.setPreferredSize(new Dimension(790,35));

		JLabel errorMessage = new JLabel();
		JPanel errorSpace = new JPanel();
		errorSpace.setPreferredSize(new Dimension(500,35));
		errorSpace.add(errorMessage);
		confirmPanel.add(errorSpace);

		JButton confirm = new JButton("Confirm");
		this.getRootPane().setDefaultButton(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				errorMessage.setText("");
				if (nameBar.getText().isEmpty())
					errorMessage.setText("Name field cannot be empty.");

				else {
					student.setName(studentEdit.getName());
					student.setGrade(studentEdit.getGrade());
					student.setPriorityStatus(studentEdit.getPriorityStatus());
					student.setPartnerName(studentEdit.getPartnerName());
					student.setEmail(studentEdit.getEmail());
					student.setPaymentStatus(studentEdit.getPaymentStatus());
					student.setYear(studentEdit.getYear());
					student.setMonth(studentEdit.getMonth());
					student.setDay(studentEdit.getDay());
					student.setChoice1(studentEdit.getChoice1());
					student.setChoice2(studentEdit.getChoice2());
					student.setChoice3(studentEdit.getChoice3());
					data.removeAll();
					data.repaint();
					data.revalidate();
					if (StudentSettingsFrame.getCurrentView())
						for (int i = 0; i < currentViewList.size(); i++)
							data.add(new StudentDetailDataBar(currentViewList.get(i), studentList, data, currentViewList, eventList));
					else 
						for (int i = 0; i < currentViewList.size(); i++)
							data.add(new StudentEventDataBar(currentViewList.get(i), studentList, data, currentViewList, eventList));
					data.repaint();
					data.revalidate();
					closeFrame();
				}
			}
		});		
		confirmPanel.add(confirm);
		mainPanel.add(confirmPanel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {

			}
		});
	}

	/**
	 * Closes the edit frame.
	 */
	public void closeFrame() {
		this.setVisible(false);
	}

}
