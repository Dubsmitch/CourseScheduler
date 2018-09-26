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
public class Student implements Comparable<Student> {
	
	/** maximum number of credits a student can have */
	public static final int MAX_CREDITS = 18;
	/** minimum number of credits a student can have */
	public static final int MIN_CREDITS = 3;
	
	
	/** Student's first name. */
	private String firstName;
	/** Student's last name. */	
	private String lastName;
	/** Student's ID. */	
	private String id;
	/** Student's email. */	
	private String email;
	/** Student's password. */	
	private String password;
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
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
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
	 * Gets the student's email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException email must have '@', '.' and the '@' must be
	 * before the final '.'
	 */
	public void setEmail(String email) {
		//check if null or empty
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException ("Invalid email");
		}
		
		int lengthOfEmail = email.length();
		char letter = 'a';
		char symbol = '@';
		char period = '.';
		int locSymbol = -1;
		int locPeriod = -1;
		
		
		//test for presence of @ and . and set their locations
		for (int i = 0; i < lengthOfEmail - 1; i++) {
			 letter = email.charAt(i);
			 if (letter == symbol) {
				 locSymbol = i;
			 }
			 if (letter == period) {
				 locPeriod = i;
			 }
		}
		//if period or symbol are -1 they are not in email, fails
		if (locPeriod < 0 || locSymbol < 0) {
			throw new IllegalArgumentException ("Invalid email");

		}
		
		//Symbol must come before the period
		if (locSymbol > locPeriod) {
			throw new IllegalArgumentException ("Invalid email");

		}
		
		// if all conditions are met then email is set
		this.email = email;
	}

	/**
	 * Sets the student's password
	 * 
	 * @param password the password to set
	 * @throws IllegalArgumentException if provided password is empty or null
	 */
	public void setPassword(String password) {
		
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException ("Invalid password");	
		} else {
			this.password = password;
		}
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
	 * Gets the student's first name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the student's last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the students ID
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the student's password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets First name
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if provided firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException ("Invalid first name");	
		}
		else {
			this.firstName = firstName;
		}
	}

	/**
	 * Sets the last name of a student
	 * 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if provided lastName is null or empty
	 */
	public void setLastName(String lastName) {
		
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException ("Invalid last name");	
		}
		else {
			this.lastName = lastName;
		}
	}

	/**
	 * Sets the Student id
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if provided id is null or empty
	 */
	private void setId(String id) {
		
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException ("Invalid id");	
		}
		else {
			this.id = id;
		}
	}


	/**
	 * creates the hashcode for the student object
	 *
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( email.hashCode());
		result = prime * result + (firstName.hashCode());
		result = prime * result + (id.hashCode());
		result = prime * result + (lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + (password.hashCode());
		return result;
	}

	/**
	 * creates the equals object method for the student object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (!password.equals(other.password))
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
		return firstName + "," + lastName + "," + id + "," + email
				+ "," + password + "," + maxCredits;
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
		// TODO Auto-generated method stub
//		String lastNameOfThis = this.lastName;
//		String lastNameOfObj = s.lastName;
		
//		String firstNameOfThis = this.firstName;
//		String firstNameOfObj = s.firstName;
		
//		String idOfThis = this.getId();
//		String idOfObj = s.getId();
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

