import java.time.LocalDate;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Object Student which holds the information about a particular student.
 */
public class Student{

	/** The name. */
	private String name ="";
	
	/** The grade. */
	private int grade = 9;
	
	/** The priority status. */
	private boolean priorityStatus = false;
	
	/** The partner name. */
	private String partnerName = "";
	
	/** The email. */
	private String email = "";
	
	/** The payment status. */
	private boolean paymentStatus = true;
	
	/** The year. */
	private int year = LocalDate.now().getYear();
	
	/** The month. */
	private int month = LocalDate.now().getMonthValue();
	
	/** The day. */
	private int day = LocalDate.now().getDayOfMonth();
	
	/** The choice1. */
	private Event choice1 = Event.NULL_EVENT;
	
	/** The choice2. */
	private Event choice2 = Event.NULL_EVENT;
	
	/** The choice3. */
	private Event choice3 = Event.NULL_EVENT;
	
	/** The selected. */
	private boolean selected = false;
	
	/** The received event. */
	private boolean receivedEvent = true;

	/**
	 * Instantiates a new student.
	 */
	public Student() {

	}

	/**
	 * Instantiates a new student, used for file input.
	 *
	 * @param name the name
	 * @param grade the grade
	 * @param priorityStatus the priority status
	 * @param partnerName the partner name
	 * @param email the email
	 * @param paymentStatus the payment status
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @param choice1 the choice1
	 * @param choice2 the choice2
	 * @param choice3 the choice3
	 */
	public Student(String name, int grade, boolean priorityStatus, String partnerName, String email, boolean paymentStatus
			, int year, int month, int day, Event choice1, Event choice2, Event choice3) {
		this.name = name;
		this.grade = grade;
		this.priorityStatus = priorityStatus;
		this.partnerName = partnerName;
		this.email = email;
		this.paymentStatus = paymentStatus;
		this.year = year;
		this.month = month;
		 this.day = day;
		 this.choice1 = choice1;
		 this.choice2 = choice2;
		 this.choice3 = choice3;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName () {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName (String name) {
		this.name = name;
	}

	/**
	 * Gets the grade.
	 *
	 * @return the grade
	 */
	public int getGrade () {
		return this.grade;
	}

	/**
	 * Sets the grade.
	 *
	 * @param grade the new grade
	 */
	public void setGrade (int grade) {
		this.grade = grade;
	}

	/**
	 * Gets the priority status.
	 *
	 * @return the priority status
	 */
	public boolean getPriorityStatus () {
		return this.priorityStatus;
	}

	/**
	 * Sets the priority status.
	 *
	 * @param priorityStatus the new priority status
	 */
	public void setPriorityStatus (boolean priorityStatus) {
		this.priorityStatus = priorityStatus; 
	}

	/**
	 * Gets the partner name.
	 *
	 * @return the partner name
	 */
	public String getPartnerName() {
		return this.partnerName;
	}

	/**
	 * Sets the partner name.
	 *
	 * @param partnerName the new partner name
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the payment status.
	 *
	 * @return the payment status
	 */
	public boolean getPaymentStatus() {
		return this.paymentStatus;
	}

	/**
	 * Sets the payment status.
	 *
	 * @param paymentStatus the new payment status
	 */
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month the new month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the new day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Gets the choice1.
	 *
	 * @return the choice1
	 */
	public Event getChoice1() {
		return this.choice1;
	}

	/**
	 * Sets the choice1.
	 *
	 * @param choice1 the new choice1
	 */
	public void setChoice1(Event choice1) {
		this.choice1 = choice1;
	}

	/**
	 * Gets the choice2.
	 *
	 * @return the choice2
	 */
	public Event getChoice2() {
		return this.choice2;
	}

	/**
	 * Sets the choice2.
	 *
	 * @param choice2 the new choice2
	 */
	public void setChoice2(Event choice2) {
		this.choice2 = choice2;
	}

	/**
	 * Gets the choice3.
	 *
	 * @return the choice3
	 */
	public Event getChoice3() {
		return this.choice3;
	}

	/**
	 * Sets the choice3.
	 *
	 * @param choice3 the new choice3
	 */
	public void setChoice3(Event choice3) {
		this.choice3 = choice3;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public boolean getSelected() {
		return this.selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Gets the received event.
	 *
	 * @return the received event
	 */
	public boolean getReceivedEvent() {
		return this.receivedEvent;
	}

	/**
	 * Sets the received event.
	 *
	 * @param receivedEvent the new received event
	 */
	public void setReceivedEvent(boolean receivedEvent) {
		this.receivedEvent = receivedEvent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name + " (Grade " + this.grade + ")";
	}

	/** The Name comparator. */
	public static Comparator<Student> NameComparator = new Comparator<Student>() {
		public int compare(Student a, Student b) {
			return (a.getName())
					.compareToIgnoreCase(b.getName());
		}
	};

	/** The Grade comparator ascending. */
	public static Comparator<Student> GradeComparatorAscending = new Comparator<Student>() {
		public int compare(Student a, Student b) {
			return (a.getGrade() - b.getGrade());
		}
	};

	/** The Grade comparator descending. */
	public static Comparator<Student> GradeComparatorDescending = new Comparator<Student>() {
		public int compare(Student a, Student b) {
			return (b.getGrade() - a.getGrade());
		}
	};

	/** The Date comparator early. */
	public static Comparator<Student> DateComparatorEarly = new Comparator<Student>() {
		@Override
		public int compare(Student a, Student b) {
			if (a.getPaymentStatus() == false && b.getPaymentStatus() == false)
				return 0;
			if (a.getPaymentStatus() == false)
				return 1;
			if (b.getPaymentStatus() == false)
				return -1;
			return (LocalDate.of(a.getYear(),a.getMonth()+1,a.getDay())
					.compareTo(LocalDate.of(b.getYear(),b.getMonth()+1,b.getDay())));
		}
	};

	/** The Date comparator late. */
	public static Comparator<Student> DateComparatorLate = new Comparator<Student>() {
		@Override
		public int compare(Student a, Student b) {
			if (a.getPaymentStatus() == false && b.getPaymentStatus() == false)
				return 0;
			if (a.getPaymentStatus() == false)
				return 1;
			if (b.getPaymentStatus() == false)
				return -1;
			return (LocalDate.of(b.getYear(),b.getMonth()+1,b.getDay())
					.compareTo(LocalDate.of(a.getYear(),a.getMonth()+1,a.getDay())));
		}
	};

	/** The Team comparator. */
	public static Comparator<Student> TeamComparator = new Comparator<Student>() {
		public int compare(Student a, Student b) {
			if ((a.getPartnerName() == null && b.getPartnerName() == null) ||
					(a.getPartnerName() != null && b.getPartnerName() != null))
				return 0;
			if (a.getPartnerName() != null)
				return 1;
			if (b.getPartnerName() != null)
				return -1;
			return -1;
		}
	};
}