package edu.ncsu.csc216.pack_scheduler.course.validator;

public class InvalidTransitionException extends Exception {
	/** ID used for serialization **/
	//what? design says "Long"//
	private static final long serialVersionUID = 1L;

	/**
	 * generates a new exception type that throws a custom
	 * message when triggered (when an invalid character is used
	 * to create a course title)
	 * 
	 * @param message
	 * 			the message that is seen when this exception is
	 * 			thrown.
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Generates a new exception type for when
	 * an invalid character is used to create a course title. 
	 * Has a default message "Invalid FSM Transition.".
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
}

