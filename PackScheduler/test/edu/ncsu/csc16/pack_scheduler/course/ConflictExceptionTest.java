/**
 * 
 */
package edu.ncsu.csc16.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * tests if the custom exception: ConflictException
 * works as expected 
 * 
 * @author William
 *
 */
public class ConflictExceptionTest {

	/**
	 * Test if ConflictException(String) constructor
	 * works as expected
	 */
	@Test
	public void testConflictExceptionString() {
		ConflictException ce = new ConflictException ("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test if ConflictException() constructor
	 * works as expected	 
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
