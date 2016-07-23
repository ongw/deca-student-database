import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The base bar for the flight Module.
 */
public class FlightBaseBar extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 334454L; 

	/** The event list. */
	private List<Event> eventList = new ArrayList<Event>();
	
	/** The student list. */
	private List<Student> studentList = new ArrayList<Student>();
	
	/** The current view list. */
	private List<Flight> currentViewList = new ArrayList<Flight>();
	
	/** The flight list. */
	private List<Flight> flightList = new ArrayList<Flight>();
	
	/** The flight map. */
	private Map<Event,Flight> flightMap = new HashMap<Event, Flight>();


	/**
	 * Instantiates a new flight base bar.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list
	 * @param studentList the student list
	 * @param flightList the flight list
	 * @param flightMap the flight map
	 */
	public FlightBaseBar(List<Event> eventList, JPanel data, List<Flight> currentViewList, List<Student> studentList,List<Flight> flightList, Map<Event,Flight> flightMap) {
		this.eventList = eventList;
		this.studentList = studentList;
		this.currentViewList = currentViewList;
		this.flightList = flightList;

		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setPreferredSize(new Dimension(960,30));
		this.setBorder(new EmptyBorder(0,10,0,0));

		JButton imageButton = new JButton("Export to Image");
		imageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ExportPngFrame frame = new ExportPngFrame(data);
			}
		});
		this.add(imageButton);
		this.add(Box.createHorizontalStrut(450));

		JPanel create = new JPanel();
		create.setPreferredSize(new Dimension (50,30));
		create.setBorder(new EmptyBorder(-5,0,0,0));;

		JButton createFlight = new JButton("Create Flights");
		create.add(createFlight);
		JLabel priorityLabel = new JLabel("\t\tPriority: ");
		create.add(priorityLabel);
		JComboBox<String> priorityBox = new JComboBox<String>();
		priorityBox.addItem("Time");
		priorityBox.addItem("Optimal");
		create.add(priorityBox);

		createFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				flightList.clear();
				flightMap.clear();
				List<Student> priority = new ArrayList<Student>();
				List<Student> tempStudent = new ArrayList<Student>();
				List<Student> noEventStudent = new ArrayList<Student>();

				for (int i = 0; i < studentList.size(); i++) {
					tempStudent.add(studentList.get(i));
					if (studentList.get(i).getPriorityStatus()) {
						priority.add(studentList.get(i));
						tempStudent.remove(studentList.get(i));
					}
				}

				for (int i = 0; i < eventList.size(); i++) {
					Flight temp = new Flight(eventList.get(i));
					flightList.add(temp);
					flightMap.put(eventList.get(i), temp);
				}

				Collections.sort(priority, Student.DateComparatorEarly);
				int count = 0;
				while (count < priority.size()) {
					if (!priority.get(count).getChoice1().equals(Event.NULL_EVENT)) {
						if (flightMap.get(priority.get(count).getChoice1()).isNotFull()) {
							if (priority.get(count).getPartnerName().equals("")) {
								flightMap.get(priority.get(count).getChoice1()).addStudent(priority.get(count));
								priority.remove(priority.get(count));
							}
							else {
								Student temp = priority.get(count);
								for (int i = 0; i < studentList.size(); i++) {
									if (studentList.get(i).getName().equals(temp.getPartnerName()) &&  
											studentList.get(i).getPartnerName().equals(temp.getName())) {
										priority.remove(studentList.get(i));
										tempStudent.remove(studentList.get(i));
										break;
									}
								}
								priority.remove(temp);
							}
						}
						else count++;
					}
					else if (!priority.get(count).getChoice2().equals(Event.NULL_EVENT)) {
						if (flightMap.get(priority.get(count).getChoice2()).isNotFull()) {
							if (priority.get(count).getPartnerName().equals("")) {
								flightMap.get(priority.get(count).getChoice2()).addStudent(priority.get(count));
								priority.remove(priority.get(count));
							}
							else {
								Student temp = priority.get(count);
								for (int i = 0; i < studentList.size(); i++) {
									if (studentList.get(i).getName().equals(temp.getPartnerName()) &&  
											studentList.get(i).getPartnerName().equals(temp.getName())) {
										priority.remove(studentList.get(i));
										tempStudent.remove(studentList.get(i));
										break;
									}
								}
								priority.remove(temp);
							}
						}
						count++;
					}
					else if (!priority.get(count).getChoice3().equals(Event.NULL_EVENT)) {
						if (flightMap.get(priority.get(count).getChoice3()).isNotFull()) {
							if (priority.get(count).getPartnerName().equals("")) {
								flightMap.get(priority.get(count).getChoice3()).addStudent(priority.get(count));
								priority.remove(priority.get(count));
							}
							else {
								Student temp = priority.get(count);
								for (int i = 0; i < studentList.size(); i++) {
									if (studentList.get(i).getName().equals(temp.getPartnerName()) &&  
											studentList.get(i).getPartnerName().equals(temp.getName())) {
										priority.remove(studentList.get(i));
										tempStudent.remove(studentList.get(i));
										break;
									}
								}
								priority.remove(temp);
							}
						}
						count++;
					}
					else count++;
				}
				for (int i = 0; i < priority.size(); i++)
					noEventStudent.add(priority.get(i));	

				if (priorityBox.getSelectedItem().equals("Time")) {
					Collections.sort(tempStudent, Student.DateComparatorEarly);
					count = 0;
					while (count < tempStudent.size()) {
						if (!tempStudent.get(count).getChoice1().equals(Event.NULL_EVENT)) {
							if (flightMap.get(tempStudent.get(count).getChoice1()).isNotFull()) {
								if (tempStudent.get(count).getPartnerName().equals("")) {
									flightMap.get(tempStudent.get(count).getChoice1()).addStudent(tempStudent.get(count));
									tempStudent.remove(tempStudent.get(count));
								}
								else {
									Student temp = tempStudent.get(count);
									for (int i = 0; i < studentList.size(); i++) {
										if (studentList.get(i).getName().equals(temp.getPartnerName()) &&  
												studentList.get(i).getPartnerName().equals(temp.getName())) {
											tempStudent.remove(studentList.get(i));
											break;
										}
									}
									tempStudent.remove(temp);
								}
							}
							else count++;
						}
						else if (!tempStudent.get(count).getChoice2().equals(Event.NULL_EVENT)) {
							if (flightMap.get(tempStudent.get(count).getChoice2()).isNotFull()) {
								if (tempStudent.get(count).getPartnerName().equals("")) {
									flightMap.get(tempStudent.get(count).getChoice2()).addStudent(tempStudent.get(count));
									tempStudent.remove(tempStudent.get(count));
								}
								else {
									Student temp = tempStudent.get(count);
									for (int i = 0; i < studentList.size(); i++) {
										if (studentList.get(i).getName().equals(temp.getPartnerName()) &&  
												studentList.get(i).getPartnerName().equals(temp.getName())) {
											tempStudent.remove(studentList.get(i));
											break;
										}
									}
									tempStudent.remove(temp);
								}
							}
							else count++;
						}
						else if (!tempStudent.get(count).getChoice3().equals(Event.NULL_EVENT)) {
							if (flightMap.get(tempStudent.get(count).getChoice3()).isNotFull()) {
								if (tempStudent.get(count).getPartnerName().equals("")) {
									flightMap.get(tempStudent.get(count).getChoice3()).addStudent(tempStudent.get(count));
									tempStudent.remove(tempStudent.get(count));
								}
								else {
									Student temp = tempStudent.get(count);
									for (int i = 0; i < studentList.size(); i++) {
										if (studentList.get(i).getName().equals(temp.getPartnerName()) &&  
												studentList.get(i).getPartnerName().equals(temp.getName())) {
											tempStudent.remove(studentList.get(i));
											break;
										}
									}
									tempStudent.remove(temp);
								}
							}
							else count++;
						}
						else count++;
					}
					for (int i = 0; i < tempStudent.size(); i++)
						noEventStudent.add(tempStudent.get(i));	
				}
				if (priorityBox.getSelectedItem().equals("Optimal")) {
					List<Event> tempEvent = new ArrayList<Event>();
					for (int i = 0; i < eventList.size(); i++) {
						eventList.get(i).resetStudentChoices();	
						tempEvent.add(eventList.get(i));
					}
					for (int i = 0; i < tempStudent.size(); i++) {
						if (!tempStudent.get(i).getChoice1().equals(Event.NULL_EVENT))
							tempStudent.get(i).getChoice1().addStudentChoice1();
						if (!tempStudent.get(i).getChoice2().equals(Event.NULL_EVENT))
							tempStudent.get(i).getChoice2().addStudentChoice2();
						if (!tempStudent.get(i).getChoice3().equals(Event.NULL_EVENT))
							tempStudent.get(i).getChoice3().addStudentChoice3();
					}

					Collections.sort(tempEvent, Event.Choice1Comparator);
					for (int i = 0; i < tempEvent.size(); i++) {
						count = 0;
						while (count < tempStudent.size()) {
							if (tempStudent.get(count).getChoice1().equals(tempEvent.get(i))) {
								if(flightMap.get(tempStudent.get(count).getChoice1()).isNotFull()) {
									if (tempStudent.get(count).getPartnerName().equals("")) {
										flightMap.get(tempStudent.get(count).getChoice1()).addStudent(tempStudent.get(count));
										tempStudent.remove(tempStudent.get(count));
									}
									else {
										Student temp = tempStudent.get(count);
										for (int j = 0; j < studentList.size(); j++) {
											if (studentList.get(j).getName().equals(temp.getPartnerName()) &&  
													studentList.get(j).getPartnerName().equals(temp.getName())) {
												tempStudent.remove(studentList.get(j));
												break;
											}
										}
										tempStudent.remove(temp);
									}
								}
								else count++;
							}
							else count++;
						}
					}
					Collections.sort(tempEvent, Event.Choice2Comparator);
					for (int i = 0; i < tempEvent.size(); i++) {
						count = 0;
						while (count < tempStudent.size()) {
							if (tempStudent.get(count).getChoice2().equals(tempEvent.get(i))) {
								if(flightMap.get(tempStudent.get(count).getChoice2()).isNotFull()) {
									if (tempStudent.get(count).getPartnerName().equals("")) {
										flightMap.get(tempStudent.get(count).getChoice2()).addStudent(tempStudent.get(count));
										tempStudent.remove(tempStudent.get(count));
									}
									else {
										Student temp = tempStudent.get(count);
										for (int j = 0; j < studentList.size(); j++) {
											if (studentList.get(j).getName().equals(temp.getPartnerName()) &&  
													studentList.get(j).getPartnerName().equals(temp.getName())) {
												tempStudent.remove(studentList.get(j));
												break;
											}
										}
										tempStudent.remove(temp);
									}
								}
								else count++;
							}
							else count++;
						}
					}

					Collections.sort(tempEvent, Event.Choice3Comparator);
					for (int i = 0; i < tempEvent.size(); i++) {
						count = 0;
						while (count < tempStudent.size()) {
							if (tempStudent.get(count).getChoice3().equals(tempEvent.get(i))) {
								if(flightMap.get(tempStudent.get(count).getChoice3()).isNotFull()) {
									if (tempStudent.get(count).getPartnerName().equals("")) {
										flightMap.get(tempStudent.get(count).getChoice3()).addStudent(tempStudent.get(count));
										tempStudent.remove(tempStudent.get(count));
									}
									else {
										Student temp = tempStudent.get(count);
										for (int j = 0; j < studentList.size(); j++) {
											if (studentList.get(j).getName().equals(temp.getPartnerName()) &&  
													studentList.get(j).getPartnerName().equals(temp.getName())) {
												tempStudent.remove(studentList.get(j));
												break;
											}
										}
										tempStudent.remove(temp);
									}
								}
								else count++;
							}
							else count++;
						}
					}

					for (int i = 0; i < tempStudent.size(); i++)
						noEventStudent.add(tempStudent.get(i));
				}

				Flight nullFlight = new Flight(Event.NULL_EVENT);
				flightList.add(nullFlight);
				flightMap.put(Event.NULL_EVENT, nullFlight);

				for (int i = 0; i < noEventStudent.size(); i++){
					noEventStudent.get(i).setReceivedEvent(false);
					for (int j = 0; j < flightList.size(); j++) {
						if(flightList.get(j).isNotFull()) {
							flightList.get(j).addStudent(noEventStudent.get(i));
							break;
						}
					}
				}

				data.removeAll();
				data.repaint();
				data.revalidate();
				for (int i = 0; i < flightList.size(); i++) {
					currentViewList.add(flightList.get(i));
					data.add(new FlightDetailDataBar(flightList.get(i), flightList, data, currentViewList, studentList));
				}
				data.repaint();
				data.revalidate();
			}
		});
		this.add(create);
	}

}
