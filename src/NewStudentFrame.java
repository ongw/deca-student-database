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
 * The Class NewStudentFrame.
 */
public class NewStudentFrame extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 67839L; 
	
	/** The new student. */
	private Student newStudent = new Student();

	/**
	 * Instantiates a new new student frame.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param newStudentButton the new student button
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public NewStudentFrame (List<Student> studentList, JPanel data, JButton newStudentButton, List<Student> currentViewList, List<Event> eventList) {
		JPanel mainPanel = new JPanel();
		this.setSize(700,260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		mainPanel.setLayout(new WrapLayout());
		mainPanel.setSize(700,260);

		JLabel title = new JLabel("Add New Student");
		mainPanel.add(title);

		JPanel nameEmailPanel = new JPanel();
		nameEmailPanel.setPreferredSize(new Dimension(790,35));

		JLabel name = new JLabel("Name:");
		nameEmailPanel.add(name);
		JTextField nameBar = new JTextField(20);
		nameBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				newStudent.setName(nameBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				newStudent.setName(nameBar.getText());
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
		emailBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				newStudent.setEmail(emailBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				newStudent.setEmail(emailBar.getText());
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
		gradeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newStudent.setGrade((int)gradeBox.getSelectedItem());
			}
		});	
		gradePartnerPanel.add(gradeBox);
		newStudent.setGrade(9);

		JLabel priority = new JLabel("\t\tPriority:");
		ButtonGroup priorityGroup = new ButtonGroup();
		gradePartnerPanel.add(priority);
		JRadioButton priorityY = new JRadioButton("Y");
		priorityY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newStudent.setPriorityStatus(true);
			}
		});	
		priorityGroup.add(priorityY);
		gradePartnerPanel.add(priorityY);
		JRadioButton priorityN = new JRadioButton("N");
		priorityN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newStudent.setPriorityStatus(false);
			}
		});	
		priorityGroup.add(priorityN);
		gradePartnerPanel.add(priorityN);
		priorityN.setSelected(true);
		newStudent.setPriorityStatus(false);

		JLabel partner = new JLabel("\t\tPartner:");
		gradePartnerPanel.add(partner);
		JTextField partnerBar = new JTextField(20);
		partnerBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				newStudent.setPartnerName(partnerBar.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				newStudent.setPartnerName(partnerBar.getText());
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
		yearInput.setYear(LocalDate.now().getYear());
		yearInput.setEndYear(LocalDate.now().getYear());
		yearInput.setStartYear(0);

		Integer[] days = new Integer[31];
		for (int i = 1; i <= 31; i++)
			days[i-1] = i; 

		JComboBox<Integer> dayInput = new JComboBox<Integer>(days);
		dayInput.setSelectedItem(LocalDate.now().getDayOfMonth());

		JMonthChooser monthInput = new JMonthChooser();
		monthInput.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				int monthLength = LocalDate.of(yearInput.getYear(), monthInput.getMonth()+1, 1).lengthOfMonth();
				while (dayInput.getItemCount() < monthLength)
					dayInput.addItem(dayInput.getItemCount()+1);
				while (dayInput.getItemCount() > monthLength)
					dayInput.removeItem(dayInput.getItemCount());
				newStudent.setYear(yearInput.getYear());
				newStudent.setMonth(monthInput.getMonth()+1);
				newStudent.setDay((int)dayInput.getSelectedItem());
			}
		});
		monthInput.setMonth(LocalDate.now().getMonthValue()-1);

		yearInput.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				newStudent.setYear(yearInput.getYear());
				newStudent.setMonth(monthInput.getMonth()+1);
				newStudent.setDay((int)dayInput.getSelectedItem());
			}
		});
		dayInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newStudent.setYear(yearInput.getYear());
				newStudent.setMonth(monthInput.getMonth()+1);
				newStudent.setDay((int)dayInput.getSelectedItem());

			}
		});

		JLabel payment = new JLabel("\t\tPayment:");
		ButtonGroup paymentGroup = new ButtonGroup();
		datePaymentPanel.add(payment);
		JRadioButton paymentY = new JRadioButton("Y");
		paymentY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dayInput.setEnabled(true);
				monthInput.setEnabled(true);
				yearInput.setEnabled(true);
				newStudent.setYear(yearInput.getYear());
				newStudent.setMonth(monthInput.getMonth()+1);
				newStudent.setDay((int)dayInput.getSelectedItem());
				newStudent.setPaymentStatus(true);
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
				newStudent.setYear(0);
				newStudent.setMonth(0);
				newStudent.setDay(0);
				newStudent.setPaymentStatus(false);
			}
		});	
		paymentGroup.add(paymentN);
		datePaymentPanel.add(paymentN);
		paymentY.setSelected(true);
		newStudent.setPaymentStatus(true);

		JLabel date = new JLabel("\t\tDate of Payment:");
		datePaymentPanel.add(date);
		datePaymentPanel.add(dayInput);
		datePaymentPanel.add(monthInput);
		datePaymentPanel.add(yearInput);
		newStudent.setYear(yearInput.getYear());
		newStudent.setMonth(monthInput.getMonth()+1);
		newStudent.setDay((int)dayInput.getSelectedItem());
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
				newStudent.setChoice1((Event)event1Box.getSelectedItem());
			}
		});	
		event1Box.setEditable(true);
		eventChoicePanel.add(event1);
		eventChoicePanel.add(event1Box);
		newStudent.setChoice1(Event.NULL_EVENT);

		JLabel event2 = new JLabel("\t\tEvent Choice 2:");
		JComboBox<Event> event2Box = new JComboBox<Event>();
		event2Box.addItem(Event.NULL_EVENT);
		for (int i = 0; i < eventList.size(); i++)
			event2Box.addItem(eventList.get(i));
		event2Box.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e){
				newStudent.setChoice2((Event)event2Box.getSelectedItem());
			}
		});	
		event2Box.setEditable(true);
		eventChoicePanel.add(event2);
		eventChoicePanel.add(event2Box);
		newStudent.setChoice2(Event.NULL_EVENT);

		JLabel event3 = new JLabel("\t\tEvent Choice 3:");
		JComboBox<Event> event3Box = new JComboBox<Event>();
		event3Box.addItem(Event.NULL_EVENT);
		for (int i = 0; i < eventList.size(); i++)
			event3Box.addItem(eventList.get(i));
		event3Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newStudent.setChoice3((Event)event3Box.getSelectedItem());
			}
		});	
		event3Box.setEditable(true);
		eventChoicePanel.add(event3);
		newStudent.setChoice3(Event.NULL_EVENT);
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
				else if ((((newStudent.getChoice1().equals(newStudent.getChoice2())) && !(newStudent.getChoice1().equals(Event.NULL_EVENT)))) ||
				(((newStudent.getChoice1().equals(newStudent.getChoice3())) && !(newStudent.getChoice1().equals(Event.NULL_EVENT)))) ||
				(((newStudent.getChoice2().equals(newStudent.getChoice3())) && !newStudent.getChoice2().equals(Event.NULL_EVENT))))
				errorMessage.setText("Student cannot have 2 indentical choices. Resolve or select \"None\".");	
				else {
					studentList.add(newStudent);
					currentViewList.add(newStudent);
					if (StudentSettingsFrame.getCurrentView())
						data.add(new StudentDetailDataBar(newStudent,studentList,data, currentViewList, eventList));
					else data.add(new StudentEventDataBar(newStudent, studentList, data, currentViewList, eventList));
					data.revalidate();
					newStudentButton.setEnabled(true);
					closeFrame();
				}
			}
		});		
		confirmPanel.add(confirm);
		mainPanel.add(confirmPanel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				newStudentButton.setEnabled(true);
			}
		});
	}

	/**
	 * Close frame.
	 */
	public void closeFrame() {
		this.setVisible(false);
	}
}


