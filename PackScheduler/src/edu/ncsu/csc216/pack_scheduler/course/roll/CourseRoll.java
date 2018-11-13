package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import util.LinkedAbstractList;
import util.LinkedQueue;

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
	
	public int getOpenSeats() {
		return (this.getEnrollmentCap() - this.roll.size()); 
	}
	
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < this.roll.size()) {
			throw new IllegalArgumentException ("Enrollment cap "
					+ "cannot be less than the size of the cucrrent"
					+ " roll");
		}
		
		this.enrollmentCap = enrollmentCap;
		roll.setCapacity(enrollmentCap);
	}
	
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
	
	public void drop (Student s) {
		if (s == null) {
			throw new IllegalArgumentException ("Student cannot be null");
		}
		boolean inWaitingList = false;
		boolean inRoll = false;
		
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
			LinkedQueue<Student> waitList2 = waitList;
			LinkedQueue<Student> waitList3 = waitList;
			LinkedQueue<Student> waitList4 = waitList;
			//make variable to find where the index of the student was
			int indexOfStudentInList = -1;
			
			for (int i = 0; i < waitList.size(); i++) {
				//remove students from the front of the list
				//when they match set the index.
				if (waitList2.dequeue().equals(s)) {
					indexOfStudentInList = i;
				}
			}
			//get an empty list
			for (int i = 0; i < waitList.size(); i++) {
				waitList3.dequeue();
			}
			//add up to the student that was removed
			for (int i = 0; i < indexOfStudentInList; i++) {
				waitList3.enqueue(waitList4.dequeue());
			}
			//remove the student to be dropped
			waitList4.dequeue();
			//now add the next students
			for (int i = indexOfStudentInList + 1; i < waitList4.size(); i++) {
				waitList3.enqueue(waitList4.dequeue());
			}
			//now add all the students back into original order to the 4th, and now empty, list
			for (int i = 0; i < waitList.size() - 1; i++) {
				waitList4.enqueue(waitList3.dequeue());
			}
			
			waitList = waitList4;
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
	
	public boolean canEnroll (Student s) {
		LinkedQueue<Student> waitList2 = waitList; 
		
		boolean canAddToWaitList = false;
		//try to add the student
		try {
			waitList2.enqueue(s);
			//if he can be added then mark it true
			canAddToWaitList = true;
		} catch (IllegalArgumentException e) {
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
		LinkedQueue<Student> waitList3 = waitList; 

		for (int i = 0; i < this.waitList.size(); i++) {
			if (s.equals(waitList3.dequeue())) {
				return false;
			}
		}
		//check for null
		if (s == null) {
			return false;
		}
		//if it passes then true
		return true;
	}
	
}
