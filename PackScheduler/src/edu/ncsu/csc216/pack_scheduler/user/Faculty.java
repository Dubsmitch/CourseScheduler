package edu.ncsu.csc216.pack_scheduler.user;

public class Faculty extends User {
	/** maximum number of courses **/
	private int maxCourses;
	
	/** the minimum number of courses **/
	private static final int MIN_COURSES = 1;
	
	/** the maximum number of courses **/
	private static final int MAX_COURSES = 3;
	
	/**
	 * constructs a faculty object
	 * @param firstName
	 * 		first name of the faculty member
	 * @param lastName
	 * 		last name of the faculty member
	 * @param id
	 * 		id of the faculty member
	 * @param email
	 * 		email of the faculty memember
	 * @param password
	 * 		the password of the faculty member
	 * @param maxCourses
	 * 		the number of courses the faculty member is teaching
	 */
	public Faculty (String firstName, String lastName, String id, String email,
			String password, int maxCourses) {
		
		super(firstName, lastName, id, email, password);
		
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException();
		}
		
		this.maxCourses = maxCourses;
	}
	/**
	 * sets the number of courses a faculty memeber is teaching
	 * 
	 * @param maxCourses
	 * 		the number of courses a faculty member is teaching
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < 1 || maxCourses > 3) {
			throw new IllegalArgumentException();
		}
		this.maxCourses = maxCourses;
	}
	/**
	 * returns the number of course
	 * @return int
	 * 		the number of courses being taught
	 */
	public int getMaxCourses() {
		return this.maxCourses;
	}
	
	/**
	 * returns a string of the Faculty's record
	 * separated by commas
	 * 
	 * @return String
	 * 		faculty's record separated by commas
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail()
				+ "," + super.getPassword() + "," + maxCourses;
	}
	/**
	 * Creates the hash code for the faculty object
	 *
	 *@return int
	 *		the integer value of the hashed fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}
	/**
	 * creates the equals object method for the faculty object
	 * @return boolean
	 * 		if two objects are equals true
	 * 		else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!super.equals(obj)) {

			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	
}
