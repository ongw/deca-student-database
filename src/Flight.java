import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Object used to hold the details of the flight for particular event (ie. the students participating).
 */
public class Flight{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 752147L; 

	/** The event. */
	private Event event = new Event();
	
	/** The student list. */
	private List<Student> studentList = new ArrayList<Student>();

	/**
	 * Instantiates a new flight.
	 */
	public Flight() {

	}

	/**
	 * Instantiates a new flight (used for the NullEvent).
	 *
	 * @param event the event
	 */
	public Flight(Event event) {
		this.event = event;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public Event getEvent(){
		return this.event;
	}

	/**
	 * Sets the event of the flight.
	 *
	 * @param event the new event
	 */
	public void setEvent(Event event){
		this.event = event;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override 
	public String toString() {
		String output = "";
		for (int i = 0; i < studentList.size(); i++) {
			output+= studentList.get(i).getName() + studentList.get(i).getPartnerName();
		}
		return output;
	}

	/**
	 * Checks if the current flight is not full.
	 *
	 * @return true, if is not full
	 */
	public boolean isNotFull() {
		if	(studentList.size() >= event.getFlightSize())
			return false;
		else return true;
	}

	/**
	 * Gets the max size of the flight.
	 *
	 * @return the size
	 */
	public int getSize() {
		return studentList.size();
	}

	/**
	 * Gets the list of students for the flight.
	 *
	 * @return the student list
	 */
	public List<Student> getStudentList() {
		return this.studentList;
	}

	/**
	 * Adds a new student.
	 *
	 * @param student the student
	 */
	public void addStudent(Student student) {
		studentList.add(student);
	}

	/** The Name comparator. */
	public static Comparator<Flight> NameComparator = new Comparator<Flight>() {
		public int compare(Flight a, Flight b) {
			return (a.getEvent().getName().compareToIgnoreCase(b.getEvent().getName()));
		}
	};
	
	/** The Cluster comparator. */
	public static Comparator<Flight> ClusterComparator = new Comparator<Flight>() {
		public int compare(Flight a, Flight b) {
			return (a.getEvent().getCluster().compareToIgnoreCase(b.getEvent().getCluster()));
		}
	};
	
	/** The Type comparator. */
	public static Comparator<Flight> TypeComparator = new Comparator<Flight>() {
		public int compare(Flight a, Flight b) {
			return (a.getEvent().getType().compareToIgnoreCase(b.getEvent().getType()));
		}
	};

}
