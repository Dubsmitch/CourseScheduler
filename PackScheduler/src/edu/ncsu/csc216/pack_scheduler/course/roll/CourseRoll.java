package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import util.LinkedAbstractList;

public class CourseRoll {
	//State
	/** a custom LinkedAbstractList of Student **/
	private LinkedAbstractList<Student> roll;
	/** the roll's enrollment capacity **/
	private int enrollmentCap;
	/** the smallest a class can be **/
	public static final int MIN_ENROLLMENT = 10;
	//by the way the following number is crazy.
	/** the largest a class can be **/
	public static final int MAX_ENROLLMENT = 250;
	
	public CourseRoll(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException ("Enrollment Cap must be between 10 and 250");
		}
		
		
		
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
	}
	
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException ("Student cannot be null");
		}
		
		if (this.getOpenSeats() == 0) {
			throw new IllegalArgumentException ("Roll is full");
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				throw new IllegalArgumentException ("student is already enrolled");
			}
		}
		this.roll.add(0, s);
	}
	
	public void drop (Student s) {
		if (s == null) {
			throw new IllegalArgumentException ("Student cannot be null");
		}
		
		int index = -1;
		
		for (int i = 0; i < this.roll.size(); i++) {
			if (s.equals(this.roll.get(i))) {
				index = i;
			}
		}
		
		if (index != -1) {
			this.roll.remove(index);
		} 
	}
	
	public boolean canEnroll (Student s) {
		if (this.getOpenSeats() == 0) {
			return false;
		}
		
		for (int i = 0; i < this.roll.size(); i++) {
			if (s.equals(this.roll.get(i))) {
				return false;
			}
		}
		
		if (s == null) {
			return false;
		}
		
		return true;
	}
	
}
