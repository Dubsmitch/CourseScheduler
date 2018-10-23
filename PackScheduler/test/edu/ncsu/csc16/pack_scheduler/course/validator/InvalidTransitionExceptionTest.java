package edu.ncsu.csc16.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * tests if the custom exception: ConflictException
 * works as expected 
 * 
 * @author William
 *
 */

public class InvalidTransitionExceptionTest {
	/**
	 * Test if InvalidTransitionException(String) constructor
	 * works as expected
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = 
				new InvalidTransitionException ("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}
	
	/**
	 * Test if ConflictException() constructor
	 * works as expected	 
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ce = 
				new InvalidTransitionException ();
		assertEquals("Invalid FSM Transition.", ce.getMessage());
	}

}
