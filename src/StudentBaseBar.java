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
 * The base menu bar for the student Module.
 */
public class StudentBaseBar extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 334454L; 
	
	/**
	 * Instantiates a new student base bar.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param titleBar the title bar
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public StudentBaseBar(List<Student> studentList, JPanel data, JPanel titleBar, List<Student> currentViewList, List<Event> eventList) {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(960,30));
		this.setBorder(new EmptyBorder(0,10,0,10));
		
		JButton settings = new JButton("Settings");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				settings.setEnabled(false);
				StudentSettingsFrame settingsFrame = new StudentSettingsFrame(studentList, data, settings, titleBar, currentViewList, eventList);
			}
		});
		this.add(settings);
		this.add(Box.createHorizontalGlue());
		
		JButton addStudent = new JButton("Add Student");
		addStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				addStudent.setEnabled(false);
				NewStudentFrame addStudentFrame = new NewStudentFrame(studentList, data, addStudent, currentViewList, eventList);
			}
		});
		this.add(addStudent);
	}

}
