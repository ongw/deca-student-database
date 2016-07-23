import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The main base button bar for the stuudent module.
 */
public class StudentMenuBar extends JMenuBar{

	/** The event list. */
	private List<Event> eventList;
	
	/** The student list. */
	private List<Student> studentList;
	
	/** The data. */
	private JPanel data;
	
	/** The view item all. */
	private static JCheckBoxMenuItem viewItemAll;
	
	/** The view item team. */
	private static JCheckBoxMenuItem viewItemTeam;
	
	/** The view item individual. */
	private static JCheckBoxMenuItem viewItemIndividual;
	
	/** The view item grade9. */
	private static JCheckBoxMenuItem viewItemGrade9;
	
	/** The view item grade10. */
	private static JCheckBoxMenuItem viewItemGrade10;
	
	/** The view item grade11. */
	private static JCheckBoxMenuItem viewItemGrade11;
	
	/** The view item grade12. */
	private static JCheckBoxMenuItem viewItemGrade12;
	
	/** The view item payment true. */
	private static JCheckBoxMenuItem viewItemPaymentTrue;
	
	/** The view item payment false. */
	private static JCheckBoxMenuItem viewItemPaymentFalse;
	
	/** The view item priority true. */
	private static JCheckBoxMenuItem viewItemPriorityTrue;
	
	/** The view item priority false. */
	private static JCheckBoxMenuItem viewItemPriorityFalse;
	
	/**
	 * Instantiates a new student menu bar which allows the user to save, export, and 
	 * load the student List and change the current view mode.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public StudentMenuBar(List<Student> studentList, JPanel data, List<Student> currentViewList, List<Event> eventList){
		this.data = data;
		this.studentList = studentList;
		this.eventList = eventList;
		JMenu actionMenu = new JMenu("<html><body><table width='200'><td align='center'>"
				+ "Actions</td></table></body></html>");

		JMenuItem actionItemDelete = new JMenuItem("Delete Selected");
		actionItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int count = 0;
				while (count < studentList.size()) {
					if (studentList.get(count).getSelected()) {
						Student a = studentList.get(count);
						studentList.remove(a);
						currentViewList.remove(a);
					}
					else count++;
				}
				resetView(studentList, data, currentViewList);
			}
		});
		actionMenu.add(actionItemDelete);

		JMenuItem actionItemTogglePriority = new JMenuItem("Toggle Priority Status");
		actionItemTogglePriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Component[] component = data.getComponents();
				for (int i = 0; i < component.length; i++) {
					if (component[i] instanceof StudentEventDataBar) {
						if (((StudentEventDataBar) component[i]).getStudent().getSelected())
							((StudentEventDataBar) component[i]).flipPriority();
					}
					if (component[i] instanceof StudentDetailDataBar) {
						if (((StudentDetailDataBar) component[i]).getStudent().getSelected())
							((StudentDetailDataBar) component[i]).flipPriority();
					}
				}
			}
		});
		actionMenu.add(actionItemTogglePriority);
		this.add(actionMenu);

		JMenu sortMenu = new JMenu("<html><body><table width='200'><td align='center'>"
				+ "Sort by</td></table></body></html>");
		JMenu gradeMenu = new JMenu("Grade");
		JMenu dateMenu = new JMenu("Date Registered");
		ButtonGroup sort = new ButtonGroup();

		JRadioButtonMenuItem sortItemNone = new JRadioButtonMenuItem("None");
		sortItemNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		});
		sortItemNone.setSelected(true);
		sort.add(sortItemNone);
		sortMenu.add(sortItemNone);

		JRadioButtonMenuItem sortItemName = new JRadioButtonMenuItem("Name");
		sortItemName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Student.NameComparator);
				resetView(studentList, data, currentViewList);
			}
		});
		sortMenu.add(sortItemName);
		sort.add(sortItemName);

		JRadioButtonMenuItem sortItemGradeAscending = new JRadioButtonMenuItem("Ascending");
		sortItemGradeAscending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Student.GradeComparatorAscending);
				resetView(studentList, data, currentViewList);
			}
		});
		sort.add(sortItemGradeAscending);
		gradeMenu.add(sortItemGradeAscending);

		JRadioButtonMenuItem sortItemGradeDescending = new JRadioButtonMenuItem("Descending");
		sortItemGradeDescending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Student.GradeComparatorDescending);
				resetView(studentList, data, currentViewList);
			}
		});
		sort.add(sortItemGradeDescending);
		gradeMenu.add(sortItemGradeDescending);
		sortMenu.add(gradeMenu);

		JRadioButtonMenuItem sortItemDateLatest = new JRadioButtonMenuItem("Latest First");
		sortItemDateLatest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList,Student.DateComparatorLate);

				resetView(studentList, data, currentViewList);
			}
		});
		sort.add(sortItemDateLatest);
		dateMenu.add(sortItemDateLatest);

		JRadioButtonMenuItem sortItemDateEarliest = new JRadioButtonMenuItem("Earliest First");
		sortItemDateEarliest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Student.DateComparatorEarly);
				resetView(studentList, data, currentViewList);
			}
		});
		sort.add(sortItemDateEarliest);
		dateMenu.add(sortItemDateEarliest);
		sortMenu.add(dateMenu);

		JRadioButtonMenuItem sortItemPartners = new JRadioButtonMenuItem("Partners/Individuals");
		sortItemPartners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Student.TeamComparator);
				resetView(studentList, data, currentViewList);
			}
		});
		sort.add(sortItemPartners);
		sortMenu.add(sortItemPartners);
		this.add(sortMenu);

		JMenu viewMenu = new JMenu("<html><body><table width='200'><td align='center'>"
				+ "View</td></table></body></html>");
		JMenu teamMenu = new JMenu("Teams/Individuals");
		JMenu paymentMenu = new JMenu("Payment Status");
		JMenu gradeViewMenu = new JMenu("Grade");
		JMenu priorityMenu = new JMenu("Priority Status");

		viewItemAll = new JCheckBoxMenuItem("All");
		viewItemTeam = new JCheckBoxMenuItem("Teams");
		viewItemIndividual = new JCheckBoxMenuItem("Individuals");
		viewItemGrade9 = new JCheckBoxMenuItem("9");
		viewItemGrade10 = new JCheckBoxMenuItem("10");
		viewItemGrade11 = new JCheckBoxMenuItem("11");
		viewItemGrade12 = new JCheckBoxMenuItem("12");
		viewItemPaymentTrue = new JCheckBoxMenuItem("Paid");
		viewItemPaymentFalse = new JCheckBoxMenuItem("Haven't Paid");
		viewItemPriorityTrue = new JCheckBoxMenuItem("Priority");
		viewItemPriorityFalse = new JCheckBoxMenuItem("Not Priority");


		viewItemAll.setSelected(true);
		viewItemAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemTeam.setSelected(false);
				viewItemIndividual.setSelected(false);
				viewItemGrade9.setSelected(false);
				viewItemGrade10.setSelected(false);
				viewItemGrade11.setSelected(false);
				viewItemGrade12.setSelected(false);
				viewItemPaymentTrue.setSelected(false);
				viewItemPaymentFalse.setSelected(false);
				viewItemPriorityTrue.setSelected(false);
				viewItemPriorityFalse.setSelected(false);

				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		viewMenu.add(viewItemAll);

		//View: Team
		viewItemTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		teamMenu.add(viewItemTeam);

		//View: Individuals
		viewItemIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		teamMenu.add(viewItemIndividual);
		viewMenu.add(teamMenu);


		viewItemGrade9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		gradeViewMenu.add(viewItemGrade9);


		viewItemGrade10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		gradeViewMenu.add(viewItemGrade10);

		viewItemGrade11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		gradeViewMenu.add(viewItemGrade11);

		viewItemGrade12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		gradeViewMenu.add(viewItemGrade12);
		viewMenu.add(gradeViewMenu);

		viewItemPaymentTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		paymentMenu.add(viewItemPaymentTrue);

		viewItemPaymentFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		paymentMenu.add(viewItemPaymentFalse);
		viewMenu.add(paymentMenu);
		this.add(viewMenu);

		viewItemPriorityTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		priorityMenu.add(viewItemPriorityTrue);

		viewItemPriorityFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(studentList, currentViewList);
				resetView(studentList, data, currentViewList);
			}
		});
		priorityMenu.add(viewItemPriorityFalse);
		viewMenu.add(priorityMenu);

		JLabel search = new JLabel("<html><body><table width='55'><td align='right'>"
				+ "Search: </td></table></body></html>");
		this.add(search);

		JTextField searchBar = new JTextField(20);
		searchBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				int count = 0;
				while (count < currentViewList.size()) {
					boolean remove = false;
					if (currentViewList.get(count).getPartnerName() != null) {
						if(!currentViewList.get(count).getName().toLowerCase().contains(searchBar.getText().toLowerCase()) &&
								!currentViewList.get(count).getPartnerName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
							currentViewList.remove(count);
							remove = true;
						}
					}
					else if(!currentViewList.get(count).getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
						currentViewList.remove(count);
					remove = true;	
					}
					if (!remove)
						count++;
					if (count >= currentViewList.size())
						break;
				}
				resetView(studentList, data, currentViewList);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateView(studentList, currentViewList);
				int count = 0;
				while (count < currentViewList.size()) {
					boolean remove = false;
					if (currentViewList.get(count).getPartnerName() != null) {
						if(!currentViewList.get(count).getName().toLowerCase().contains(searchBar.getText().toLowerCase()) &&
								!currentViewList.get(count).getPartnerName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
							currentViewList.remove(count);
							remove = true;
						}
					}
					if(!currentViewList.get(count).getName().toLowerCase().contains(searchBar.getText().toLowerCase())) {
						currentViewList.remove(count);
						remove = true;
					}
					if (!remove)
						count++;
					if (count >= currentViewList.size())
						break;
				}
				resetView(studentList, data, currentViewList);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		this.add(searchBar);
	}

	/**
	 * Updates the current view as per the user's selections.
	 *
	 * @param studentList the student list
	 * @param currentViewList the current view list
	 */
	private static void updateView(List<Student> studentList, List<Student> currentViewList) {
		currentViewList.clear();
		for (int i = 0; i < studentList.size(); i++)
			currentViewList.add(studentList.get(i));
		if (!viewItemIndividual.isSelected() && !viewItemTeam.isSelected() && !viewItemGrade9.isSelected()
				&& !viewItemGrade10.isSelected() && !viewItemGrade11.isSelected() && !viewItemGrade12.isSelected()
				&& !viewItemPaymentTrue.isSelected() && !viewItemPaymentFalse.isSelected() && !viewItemPriorityTrue.isSelected()
				&& !viewItemPriorityFalse.isSelected() && !viewItemAll.isSelected())
			currentViewList.clear();
		if (!viewItemAll.isSelected()) {
			int count = 0;
			while (count < currentViewList.size()) {
				boolean removed = false;
				if (viewItemIndividual.isSelected() && !viewItemTeam.isSelected()) {
					if (currentViewList.get(count).getPartnerName() != null) {
						currentViewList.remove(currentViewList.get(count));
						removed = true;
						if (count >= currentViewList.size())
							break;
					}
				}
				if (!viewItemIndividual.isSelected() && viewItemTeam.isSelected()) {
					if (currentViewList.get(count).getPartnerName() == null) {
						currentViewList.remove(currentViewList.get(count));
						removed = true;
						if (count >= currentViewList.size())
							break;
					}
				}
				if (!(viewItemGrade9.isSelected() && viewItemGrade10.isSelected()
						&& viewItemGrade11.isSelected() && viewItemGrade12.isSelected()) && 
						!(!viewItemGrade9.isSelected() && !viewItemGrade10.isSelected()
								&& !viewItemGrade11.isSelected() && !viewItemGrade12.isSelected())) {
					if (!viewItemGrade9.isSelected())
						if (currentViewList.get(count).getGrade() == 9) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemGrade10.isSelected())
						if (currentViewList.get(count).getGrade() == 10) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemGrade11.isSelected())
						if (currentViewList.get(count).getGrade() == 11) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemGrade12.isSelected())
						if (currentViewList.get(count).getGrade() == 12) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
				}
				if (viewItemPaymentTrue.isSelected() && !viewItemPaymentFalse.isSelected()) {
					if (!currentViewList.get(count).getPaymentStatus()) {
						currentViewList.remove(currentViewList.get(count));
						removed = true;
						if (count >= currentViewList.size())
							break;
					}
				}
				if (!viewItemPaymentTrue.isSelected() && viewItemPaymentFalse.isSelected()) {
					if (currentViewList.get(count).getPaymentStatus()) {
						currentViewList.remove(currentViewList.get(count));
						removed = true;
						if (count >= currentViewList.size())
							break;
					}
				}
				if (viewItemPriorityTrue.isSelected() && !viewItemPriorityFalse.isSelected()) {
					if (!currentViewList.get(count).getPriorityStatus()) {
						currentViewList.remove(currentViewList.get(count));
						removed = true;
						if (count >= currentViewList.size())
							break;
					}
				}
				if (!viewItemPriorityTrue.isSelected() && viewItemPriorityFalse.isSelected()) {
					if (currentViewList.get(count).getPriorityStatus()) {
						currentViewList.remove(currentViewList.get(count));
						removed = true;
						if (count >= currentViewList.size())
							break;
					}
				}
				if (!removed)
					count++;
			}
		}
	}

	/**
	 * Resets the current view.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param currentViewList the current view list
	 */
	public void resetView(List<Student> studentList, JPanel data, List<Student> currentViewList){
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
}
