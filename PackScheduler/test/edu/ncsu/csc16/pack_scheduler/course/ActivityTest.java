package edu.ncsu.csc16.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
/**
 * Tests the Activity class
 * 
 * @author William Mitchell
 */
public class ActivityTest {
	/**
	 * tests the functioning of checkConflict() method
	 * 
	 * an activity with overlapping days and times should
	 * throw a conflict exception
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testCheckConflict() throws InvalidTransitionException {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "TH", 1330, 1445);
		Activity a3 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "A");
		Activity a4 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "A");
		Activity a5 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		Activity a6 = new Course ("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1000, 1130);
		
		//test end time between start and end time of activity to be added
		a6.setActivityTime(1400, 1500);
		try {
		    a1.checkConflict(a6);
			fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
		    assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		    assertEquals("MW 2:00PM-3:00PM", a6.getMeetingString());
		}
		
		//test arranged and not arranged
		try {
			a3.checkConflict(a1);
			 assertEquals("Incorrect meeting string for this Activity.",
					 	"Arranged", a3.getMeetingString());
		     assertEquals("Incorrect meeting string for possibleConflictingActivity.",
		        		"MW 1:30PM-2:45PM", a1.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on"
					+ " completely distinct days were compared.");
		}
		
		try {
		    a1.checkConflict(a2);
		    assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM",
		    		a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.",
	        		"TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
		    fail("A ConflictException was thrown when two Activities at the same time"
		    		+ " on completely distinct days were compared.");
		}
	
		//Update a1 with the same meeting days and a start time that overlaps the end time of a2
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		try {
		    a1.checkConflict(a2);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
		    //Check that the internal state didn't change during method call.
		    assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		try {
		    a2.checkConflict(a1);
		    fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
		    //Check that the internal state didn't change during method call.
		    assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Update a2 with the same meeting days and a start time that overlaps the end time of a2
		a2.setMeetingDays("TH");
		a2.setActivityTime(1530, 1540);
		try {
		    a2.checkConflict(a1);
			fail(); //ConflictException should have been thrown, but was not.
		} catch (ConflictException e) {
		//Check that the internal state didn't change during method call.
			assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
			assertEquals("TH 3:30PM-3:40PM", a2.getMeetingString());
		}
		
		
		//test arranged courses
		try {
			a3.checkConflict(a4);
		    assertEquals("Incorrect meeting string for this Activity.", "Arranged", a3.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "Arranged", a4.getMeetingString());
		} catch (ConflictException e) {
		    fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		
		//test more overlapping courses
			a2.setMeetingDays("MW");
			a2.setActivityTime(1330, 1445);
		try {
			a5.checkConflict(a2);
		    fail();
		} catch (ConflictException e) {
			//Check that the internal state didn't change during method call.
		    assertEquals("MW 1:30PM-2:45PM", a5.getMeetingString());
		    assertEquals("MW 1:30PM-2:45PM", a2.getMeetingString());		}
	}
	
	/**
	 * tests the functioning of setTitle() method
	 * 
	 * should allow a title to be set unless name is null or empty
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetTitle() throws InvalidTransitionException {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		//set title to empty
		try {
			a1.setTitle("");
			fail("Title cannot be empty");
		} catch (IllegalArgumentException e) {
			assertEquals("Programming Concepts - Java", a1.getTitle());
		}
		
		//set title to null
		try {
			a1.setTitle(null);
			fail("Title cannot be null");
		} catch (IllegalArgumentException e) {
			assertEquals("Programming Concepts - Java", a1.getTitle());
		}
	}

	/**
	 * tests the functioning of setActivityTime() method
	 * 
	 * should allow a title to be set unless name is null or empty
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testSetActivityTime() throws InvalidTransitionException {
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		
		//set activity time to unacceptable start time
		try {
			a1.setActivityTime(-1, 1445);
			fail("time cannot be negative");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
		
		//set activity time to unacceptable start time
		try {
			a1.setActivityTime(2500, 1445);
			fail("time cannot be over 2359");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
		
		//set activity time to unacceptable end time
		try {
			a1.setActivityTime(1330, -1445);
			fail("time cannot be negative");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
		
		//set activity time to unacceptable end time
		try {
			a1.setActivityTime(1330, 2400);
			fail("time cannot be negative");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
		
		//set activity start time to after end time
		try {
			a1.setActivityTime(1550, 1445);
			fail("start time cannot be after end time");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
		
		//military time cannot be above 59 for minutes!
		try {
			a1.setActivityTime(1465, 1467);
			fail("time cannot be after 59 minutes");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
		
		//military time cannot be above 59 for minutes!
		try {
			a1.setActivityTime(1460, 1460);
			fail("time cannot be after 59 minutes");
		} catch (IllegalArgumentException e){
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
		}
	
	}
	
	/**
	 * tests the functioning of hashCode() method
	 * 
	 * should set hash code to reliable values
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testHashCode() throws InvalidTransitionException {
		
		//create to equal activities
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		
		//create activities that are different on all hash related fields 
		Activity a3 = new Course("CSC217", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "A");
		Activity a4 = new Course("CSC216", "Programming Concepts - J", "001", 4,
				"sesmith5", 25, "A");
		Activity a5 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1345, 1445);
		Activity a6 = new Course ("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1530);
		
		//test for equality in all fields
		assertEquals(a1.hashCode(), a2.hashCode());
		//test for inequality
		assertNotEquals(a1.hashCode(), a3.hashCode());
		assertNotEquals(a1.hashCode(), a4.hashCode());
		assertNotEquals(a1.hashCode(), a5.hashCode());
		assertNotEquals(a1.hashCode(), a6.hashCode());
	}	
	
	/**
	 * tests the functioning of toEquals() method
	 * 
	 * should set hash code to reliable values
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testToEquals() throws InvalidTransitionException {
		
		//create two equal activities
		Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1445);
		
		//create activities that are different on all hash related fields 
		Activity a3 = new Course("CSC217", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "A");
		Activity a4 = new Course("CSC216", "Programming Concepts - J", "001", 4,
				"sesmith5", 25, "A");
		Activity a5 = new Course("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1345, 1445);
		Activity a6 = new Course ("CSC216", "Programming Concepts - Java", "001", 4,
				"sesmith5", 25, "MW", 1330, 1530);

		//for testing object of a different class, a string is a class
		String word = "not a Course";
		
		//a1 and a2 should be equal
		assertTrue(a1.equals(a2));
		assertTrue(a2.equals(a1));
		
		//a1 should equal itself
		assertTrue(a1.equals(a1));
		
		//test for comparing types
		assertFalse(a1.equals(word));
		
		//test for inequality on all fields
		
		assertFalse(a1.equals(a3));
		assertFalse(a1.equals(a4));
		assertFalse(a1.equals(a5));
		assertFalse(a1.equals(a6));
	}
}
