/**
 * @author NCSU (the .zip folder containing this class 
 * @author William
 * @version 8/27/2018
 */
package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The class that creates a student object with a
 * first name, last name, Id, email, password and number of credits
 *
 * @author William Mitchell
 * @author NCSU
 */
public class Student extends User implements Comparable<Student> {
	
	/** maximum number of credits a student can have */
	public static final int MAX_CREDITS = 18;
	/** minimum number of credits a student can have */
	public static final int MIN_CREDITS = 3;
	
	
	/** Student's maxCredits. */	
	private int maxCredits;
	
	/**
	 * constructor that sets firstname, lastname, id, email, password (hashed)
	 * and maximum credits if they are other than 18
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param id the id of the student
	 * @param email the email of the student
	 * @param password the password of the student
	 * @param maxCredits the maximum number of credits a student can get
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * default constructor if the maximum credits are 18
	 * 
	 * @param firstName the first name of the student
	 * @param lastName the last name of the student
	 * @param id the id of the student
	 * @param email the email of the student
	 * @param password the password of the student
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}



	/**
	 * Gets the number of maximum credits
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the number of maximum credits
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if provided credits are greater than 18 or less than 3
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits > MAX_CREDITS || maxCredits < MIN_CREDITS) {
			throw new IllegalArgumentException ("Invalid max credits");	
		}
		else {
			this.maxCredits = maxCredits;
		}
	}

	/**
	 * Creates the hashcode for the student object
	 *
	 *	@return int
	 *			the integer value of the hashed fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * creates the equals object method for the student object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * returns a string of the student's record
	 * separated by commas
	 * 
	 * @return String of student's record separated by commas
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail()
				+ "," + super.getPassword() + "," + maxCredits;
	}
	/**
	 * Compares two student's, starting with the last names
	 * then moving to the first names then finally, comparing
	 * ID's if need be
	 * 
	 * @param s
	 * 			student to be compared to calling student
	 * 
	 * @return int
	 * 			returns 1, if the object is greater
	 * 			returns 0, if the object is equal
	 * 			returns -1, if the object is less
	 */
	@Override
	public int compareTo(Student s) {
		if (this.getLastName().compareTo(s.getLastName()) == 0) {
			if(this.getFirstName().compareTo(s.getFirstName()) == 0) {
				if(this.getId().compareTo(s.getId()) == 0) {
					return 0;
				} else {
					return this.getId().compareTo(s.getId());
				}
			} else {
				return this.getFirstName().compareTo(s.getFirstName());
			}
		} else {
			return this.getLastName().compareTo(s.getLastName());
		}
		//compare last names
//		if (this.getLastName().compareTo(s.getLastName()) == 1) {
//			return 1;	
//		} else if (this.getLastName().compareTo(s.getLastName()) == -1) {
//			return -1;
//		} else {
//			//if they are equal compare first names
//			if (this.getFirstName().compareTo(s.getFirstName()) == 1) {
//				return 1;
//			} else if (this.getFirstName().compareTo(s.getFirstName()) == -1) {
//				return -1;
//			} else {
//				//if the are equal compare ids
//				if (idOfThis.compareTo(idOfObj) == 1) {
//					return 1;
//				} else if (idOfThis.compareTo(idOfObj) == -1) {
//					return -1;
//				} else {
//					//this should be returned if they are equal
					//on all fields (shouldn't ever happen)
//					return 0;
//				}
//			}
//		}
	}
}

