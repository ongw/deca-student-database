import java.util.Comparator;


// TODO: Auto-generated Javadoc
/**
 * The Class Event, an Object that holds information regarding an event.
 */
public class Event {

	/** The Constant NULL_EVENT. */
	public final static Event NULL_EVENT = new Event("None");
	
	/** The name of the Event. */
	private String name = "";
	
	/** The acronym for the Event name. */
	private String acronym = "";
	
	/** The event cluster for the event. */
	private String cluster = "None";
	
	/** The type of event. */
	private String type = "None";
	
	/** The maximum flight size. */
	private int flightSize = Integer.MAX_VALUE;
	
	/** Holds whether the Data Panel has been selected. */
	private boolean selected = false;
	
	/** The student's 1st choice of event. */
	private int studentChoice1 = 0;
	
	/** The student's 2nd choice of event. */
	private int studentChoice2 = 0;
	
	/** The student's 3rd choice of event. */
	private int studentChoice3 = 0;
	
	/**
	 * Instantiates a new event.
	 *
	 * @param acronym the acronym
	 */
	public Event(String acronym) {
		this.name = acronym;
		this.acronym = acronym;
	}
	
	/**
	 * Instantiates a new event, empty.
	 */
	public Event() {
	}
	
	/**
	 * Instantiates a new event from file.
	 *
	 * @param name the name
	 * @param acronym the acronym
	 * @param cluster the event cluster
	 * @param type the type of event
	 * @param flightSize the maximum flight size
	 */
	public Event(String name, String acronym, String cluster, String type, int flightSize) {
		this.name = name;
		this.acronym = acronym;
		this.cluster = cluster;
		this.type = type;
		this.flightSize = flightSize;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the acronym.
	 *
	 * @return the acronym
	 */
	public String getAcronym(){
		return this.acronym;
	}
	
	/**
	 * Sets the acronym.
	 *
	 * @param acronym the new acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	/**
	 * Gets the cluster.
	 *
	 * @return the cluster
	 */
	public String getCluster(){
		return this.cluster;
	}
	
	/**
	 * Sets the cluster.
	 *
	 * @param cluster the new cluster
	 */
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the maximum flight size.
	 *
	 * @return the flight size
	 */
	public int getFlightSize(){
		return this.flightSize;
	}
	
	/**
	 * Sets the maximum flight size.
	 *
	 * @param flightSize the new flight size
	 */
	public void setFlightSize(int flightSize) {
		this.flightSize = flightSize;
	}
	
	/**
	 * Gets whether the event is selected.
	 *
	 * @return the selected
	 */
	public boolean getSelected() {
		return this.selected;
	}
	
	/**
	 * Sets whether the event is selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Gets the number of students selected this event as 1st choice
	 *
	 * @return the student choice1
	 */
	public int getStudentChoice1(){
		return studentChoice1;
	}
	
	/**
	 * Adds to the 1st choice counter
	 */
	public void addStudentChoice1() {
		studentChoice1++;
	}
	
	/**
	 * Gets the number of students selected this event as 2nd choice
	 *
	 * @return the student choice2
	 */
	public int getStudentChoice2(){
		return studentChoice2;
	}
	
	/**
	 * Adds to the 2nd choice counter
	 */
	public void addStudentChoice2() {
		studentChoice2++;
	}
	
	/**
	 * Gets the number of students selected this event as 3rd choice
	 *
	 * @return the student choice3
	 */
	public int getStudentChoice3(){
		return studentChoice3;
	}
	
	/**
	 * Adds to the 3rd choice counter
	 */
	public void addStudentChoice3() {
		studentChoice3++;
	}
	
	/**
	 * Reset the student choice counters
	 */
	public void resetStudentChoices() {
		this.studentChoice1 = 0;
		this.studentChoice2 = 0;
		this.studentChoice3 = 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.acronym;
	}
	
	/** The Name comparator. */
	public static Comparator<Event> NameComparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			return (a.getName().compareToIgnoreCase(b.getName()));
		}
	};
	
	/** The Cluster comparator. */
	public static Comparator<Event> ClusterComparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			return (a.getCluster().compareToIgnoreCase(b.getCluster()));
		}
	};
	
	/** The Type comparator. */
	public static Comparator<Event> TypeComparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			return (a.getType().compareToIgnoreCase(b.getType()));
		}
	};
	
	/** The 1st Event choice counter comparator. */
	public static Comparator<Event> Choice1Comparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			return (a.getStudentChoice1() - (b.getStudentChoice1()));
		}
	};
	
	/** The 2nd Event choice counter comparator. */
	public static Comparator<Event> Choice2Comparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			return (a.getStudentChoice2() - (b.getStudentChoice2()));
		}
	};
	
	/** The 3rd Event choice counter comparator. */
	public static Comparator<Event> Choice3Comparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			return (a.getStudentChoice3() - (b.getStudentChoice3()));
		}
	};
}
