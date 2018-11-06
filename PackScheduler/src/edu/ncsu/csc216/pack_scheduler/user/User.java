package edu.ncsu.csc216.pack_scheduler.user;

/**
 * abstract class for user types
 * 
 * @author William
 *
 */
abstract public class User {

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
	/**
	 * creates a new user
	 * @param firstName
	 * 		the first name of the user
	 * @param lastName
	 * 		the last name of the user
	 * @param id
	 * 		the unity id of the user
	 * @param email
	 * 		the email of the user
	 * @param password
	 * 		the password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
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

	/**
	 * generates a hash code for user fields
	 * 
	 * @return int
	 * 			integer value of hashed fields for the user.		
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + email.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + id.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + password.hashCode();
		return result;
	}

	/**
	 * checks whether or not two user objects are equal
	 * 
	 * @param obj
	 * 			the object to be checked against the reference
	 * 
	 * @return boolean
	 * 			whether or not the two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		user fields cannot be null
//		if (obj == null)
//			return false;
		System.out.println("This executed 1");

		if (getClass() != obj.getClass()) {
			System.out.println("This executed");
			return false;
		}
		System.out.println("This executed 2");

		
//		cast 
		User other = (User) obj;
//		email cannot be null		
//		if (email == null) {
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		if (!password.equals(other.password))
			return false;
		
		return true;
	}
}