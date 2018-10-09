package edu.ncsu.csc216.pack_scheduler.course;

/**
 * creates a new custom exception type:
 * conflict Exception, that is thrown when two activities
 * conflict with each other.
 * @author William
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization **/
	private static final long serialVersionUID = 1L;

	/**
	 * generates a new exception type that throws a custom
	 * message when triggered (when two activities conflict with
	 * each other)
	 * 
	 * @param message
	 * 			the message that is seen when this exception is
	 * 			thrown.
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Generates a new exception type for when
	 * two activities conflict with each other. has
	 * a default message "Schedule conflict.".
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}
}

