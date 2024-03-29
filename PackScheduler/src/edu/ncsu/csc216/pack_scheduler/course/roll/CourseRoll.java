package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * holds the number of students that are enrolled in a course
 * @author William
 *
 */
public class CourseRoll {
	//State
	/** the waitlist **/
	private LinkedQueue<Student> waitList;
	/** a custom LinkedAbstractList of Student **/
	private LinkedAbstractList<Student> roll;
	/** the roll's enrollment capacity **/
	private int enrollmentCap;
	/** the smallest a class can be **/
	public static final int MIN_ENROLLMENT = 10;
	//by the way the following number is crazy.
	/** the largest a class can be **/
	public static final int MAX_ENROLLMENT = 250;
	/**
	 * constructs a course roll
	 * @param enrollmentCap
	 * 		how many students can be enrolled in a given course
	 * @param course
	 * 		the course to be enrolled in
	 */
	public CourseRoll(int enrollmentCap, Course course) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException ("Enrollment Cap must be between 10 and 250");
		}
		
		if (course == null) {
			throw new IllegalArgumentException ("Course must not be null");
		}
		waitList = new LinkedQueue<Student>(10);
		
		LinkedAbstractList<Student> myRoll = new 
				LinkedAbstractList<Student>(enrollmentCap);
		roll = myRoll;
		
		this.setEnrollmentCap(enrollmentCap);
		
	}
	/**
	 * returns the number of open seats
	 * @return	int
	 * 		the number of open seats
	 */
	public int getOpenSeats() {
		return (this.getEnrollmentCap() - this.roll.size()); 
	}
	/**
	 * returns the enrollmentcap
	 * @return int
	 * 		the number of students that can take a course
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	/**
	 * sets the enrollmentcap
	 * @param enrollmentCap
	 * 			the number of students that can take a course
	 * @throws IllegalArgumentException
	 * 			if the size of the cap is less than the
	 * 			size of the current roll
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < this.roll.size()) {
			throw new IllegalArgumentException ("Enrollment cap "
					+ "cannot be less than the size of the cucrrent"
					+ " roll");
		}
		
		this.enrollmentCap = enrollmentCap;
		roll.setCapacity(enrollmentCap);
	}
	/**
	 * enrolls a student
	 * @param s
	 * 		the student to be enrolled
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException ("Student cannot be null");
		}
		
		if (this.getOpenSeats() == 0) {
			this.waitList.enqueue(s);
		} else {
			for (int i = 0; i < roll.size(); i++) {
				if (s.equals(roll.get(i))) {
					throw new IllegalArgumentException ("student is already enrolled");
				}
			}
			this.roll.add(0, s);
		}
	}
	/**
	 * drops a student from the roll or waitlist
	 * @param s
	 * 			the student to be dropped
	 */
	public void drop (Student s) {
		if (s == null) {
			throw new IllegalArgumentException ("Student cannot be null");
		}
		boolean inWaitingList = false;
		boolean inRoll = false;
		System.out.println(waitList.size());
		if (this.waitList.size() > 0) {
			LinkedQueue<Student> waitList2 = waitList;
			for (int i = 0; i < this.waitList.size(); i++) {
				if(s.equals(waitList2.dequeue())) {
					inWaitingList = true;
				}
			}
		}
		
		if (inWaitingList) {
			//make a new list
			LinkedQueue<Student> tempList = new LinkedQueue<Student>(waitList.size());
			//LinkedQueue<Student> tempList2 = new LinkedQueue<Student>(waitList.size());
			//make variable to find where the index of the student was
			Student element = null;
			for (int i = 0; i < waitList.size(); i++) {
				element = waitList.dequeue();
				//remove students from the front of the list
				//when they match set the index.
				if (!element.equals(s)) {
					tempList.enqueue(element);//this will add elements back in the reverse order
					//without the offending element
				}
			}
			for (int i = 0; i < tempList.size(); i++) {
				waitList.enqueue(tempList.dequeue());
			}
			
		}			
		
		//
		for (int i = 0; i < this.roll.size(); i++) {
			if (s.equals(this.roll.get(i))) {
				inRoll = true;
			}
		}
		if (inRoll) {
			int index = -1;
			if (inRoll) {
				for (int i = 0; i < this.roll.size(); i++) {
					if (s.equals(this.roll.get(i))) {
						index = i;
					}
				}
		
				if (index != -1) {
					this.roll.remove(index);
				} 
		
				if (this.waitList.size() > 0) {
					this.roll.add(this.roll.size(), s);
				}
			}
		}
	}
	/**
	 * checks to see if a student can be enrolled
	 * @param s 
	 * 		the student to be enrolled
	 * @return boolean
	 * 		whether or not a student can be enrolled
	 */
	public boolean canEnroll (Student s) {
		LinkedQueue<Student> waitList2 = new LinkedQueue<Student>(waitList.size()); 
		@SuppressWarnings("unused")
		LinkedQueue<Student> waitList3 = new LinkedQueue<Student>(waitList.size()); 

		boolean canAddToWaitList = false;
		//try to add the student
		try {

			waitList.enqueue(s);
			//if he can be added then mark it true
			canAddToWaitList = true;
		} catch (IllegalArgumentException e) {
		}
		for (int i = 0; i < waitList.size() - 1; i++) {
			waitList2.enqueue(waitList.dequeue());
		}
		waitList.dequeue();
		
		for (int i = 0; i < waitList2.size(); i++) {
			waitList.enqueue(waitList2.dequeue());
		}
		// get open seats, if there are none then see if
		//the can be added to the wait list
		
		if (this.getOpenSeats() == 0) {
			if (!canAddToWaitList) {

				return false;
			}
		}
				
		//check if they are already in the roll
		for (int i = 0; i < this.roll.size(); i++) {
			if (s.equals(this.roll.get(i))) {
				return false;
			}
		}
		
		//check if it is the same as someone already on the waitlist
		LinkedQueue<Student> waitList4 = new LinkedQueue<Student>(waitList.size());
		
		if (waitList.size() > 0) {
			for (int i = 0; i < this.waitList.size(); i++) {
				Student element = waitList.dequeue();
				if (s.equals(element)) {
					return false;
				}
				waitList4.enqueue(element);
			}
			waitList.enqueue(waitList4.dequeue());
		}
		//check for null
		if (s == null) {
			return false;
		}
		//if it passes then true
		return true;
	}
	/**
	 * returns the number of students on the waitlist
	 * @return int
	 * 			number of students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitList.size();
	}
	
}
