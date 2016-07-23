import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;


// TODO: Auto-generated Javadoc
/**
 * The dropdown menu which appears when a user right clicks on a student data panel.
 */
public class RightClickStudentMenu  extends JPopupMenu{
	
	/**
	 * Instantiates a new right click student menu which allows the user to select whether to edit or delete.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param student the student
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public RightClickStudentMenu(List<Student> studentList, JPanel data, Student student, List<Student> currentViewList, List<Event> eventList) {
	JMenuItem delete = new JMenuItem("Delete Student");
	delete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			studentList.remove(student);
			currentViewList.remove(student);
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
		}
	});
	this.add(delete);
	
	JMenuItem edit = new JMenuItem("Edit Student");
	edit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			EditStudentFrame frame = new EditStudentFrame(studentList, data, student, currentViewList, eventList);
		}
	});
	this.add(edit);
	}

}
