package edu.ncsu.csc216.pack_scheduler.user;

abstract public class User {

	/** Student's first name. */
	protected String firstName;
	/** Student's last name. */
	protected String lastName;
	/** Student's ID. */
	protected String id;
	/** Student's email. */
	protected String email;
	/** Student's password. */
	protected String password;

	public User() {
		super();
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
	protected void setId(String id) {
		
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException ("Invalid id");	
		}
		else {
			this.id = id;
		}
	}

}