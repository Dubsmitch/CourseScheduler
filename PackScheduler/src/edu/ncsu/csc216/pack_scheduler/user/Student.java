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
 */
public class Student {
	
	/** maximum number of credits a student can have */
	public static final int MAX_CREDITS = 18;
	
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
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		this.maxCredits=18;
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
	 */
	public void setEmail(String email) {
		boolean isEmailValid = true;
		int lengthOfEmail=email.length();
		char letter = 'a';
		char symbol = '@';
		int locSymbol = -1;
		char period = '.';
		int locPeriod = -1;
		
		// test for null and empty 
		if (email == null || email.equals("")) {
			isEmailValid = false;
		}
		
		//test for presence of @ and . and set their locations
		for (int i=0; i<lengthOfEmail-1; i++) {
			 letter = email.charAt(i);
			 if (letter == symbol) {
				 locSymbol = i;
			 }
			 if (letter == period) {
				 locPeriod = i;
			 }
			 //if period is set above -1 and symbol is at -1 then the
			 //period is before the symbol
			 if (locPeriod > 0 && locSymbol < 0) {
				 isEmailValid = false;
			 }
		}
		
		//is there a symbol and a period
		if (locSymbol < 0 || locPeriod < 0 ) {
			isEmailValid = false;
		}
		
		// if all conditions are met then email is set
		if (isEmailValid) {
			this.email = email;
		} else {
			throw new IllegalArgumentException ("email must not be empty or null, must contain an '@' and a '.' and the @ must be before the .");
		}
	}

	/**
	 * Gets the student's password
	 * 
	 * @return the password
	 */
	public String password() {
		return password;
	}

	/**
	 * Sets the student's password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException ("password cannot be empty or null");	
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
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits >= 19 || maxCredits < 3) {
			throw new IllegalArgumentException ("maximum credits cannot be either greater than 18 or less than 3");	
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
	 */
	public void setFirstName(String firstName) {
		
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException ("first name cannot be empty or null");	
		}
		else {
			this.firstName = firstName;
		}
	}

	/**
	 * Sets the last name of a student
	 * 
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException ("last name cannot be empty or null");	
		}
		else {
			this.lastName = lastName;
		}
	}

	/**
	 * Sets the Student id
	 * 
	 * @param id the id to set
	 */
	private void setId(String id) {
		
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException ("id cannot be empty or null");	
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * creates the equals object method for the student object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * returns a string of the student's record
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email
				+ "," + password + "," + maxCredits;
	}

	
}
