package edu.ncsu.csc216.pack_scheduler.course;

/**
 * creates an abstract method that will be used to test if
 * two activities will conflict with each other 
 * 
 * @author William
 *
 */

public interface Conflict {
	
	/** Used to test if two activities will conflict with each other 
	 * 
	 * @param possibleConflictingActivity
	 * 			the new activity to be checked against other activities
	 * @throws ConflictException
	 * 			throws a conflict exception if two activities will
	 * 			conflict with each other
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
