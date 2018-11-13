package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
import edu.ncsu.csc216.pack_scheduler.user.Student;

public class CourseRollTest {
	/**
	 * test the constructor
	 * @throws InvalidTransitionException 
	 * 		if there is an invalid transition. should really fix this
	 */
	@Test
	public void testConstructor() throws InvalidTransitionException {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "A");
		
		CourseRoll rollA = c.getCourseRoll();
		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
	}
	
	/**
	 * test setting the enrollment cap
	 * @throws InvalidTransitionException 
	 * 		should really fix this
	 */
	@Test
	public void testSetEnrollmentCap() throws InvalidTransitionException {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "A");
		
		CourseRoll rollA = c.getCourseRoll();		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		rollA.setEnrollmentCap(24);
		
		assertEquals(rollA.getOpenSeats(), 24);
		assertEquals(rollA.getEnrollmentCap(), 24);
	}
	
	/**
	 * test adding a student
	 * @throws InvalidTransitionException 
	 * 		should really fix this
	 */
	@Test
	public void testAddStudent() throws InvalidTransitionException {
		//(String firstName, String lastName, String id, String email,
		//		String password, int maxCredits)
		
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "A");
		
		CourseRoll rollA = c.getCourseRoll();		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		Student a = new Student("first", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student b = new Student("firs", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student q = new Student("fir", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student d = new Student("f", "last", "fila", "fila@ncsu.edu", "pw", 15);
		//Student e = new Student("firsty", "last", "fila", "fila@ncsu.edu", "pw", 15);
		
		
		rollA.enroll(a);
		assertEquals(rollA.getOpenSeats(), 24);
		
		rollA.enroll(b);
		assertEquals(rollA.getOpenSeats(), 23);
		
		rollA.enroll(q);
		assertEquals(rollA.getOpenSeats(), 22);
		
		rollA.enroll(d);
		assertEquals(rollA.getOpenSeats(), 21);
	}
	
	/**
	 * test removing a student
	 * @throws InvalidTransitionException 
	 * 		should really fix this
	 */
	@Test
	public void testRemoveStudent() throws InvalidTransitionException {
		//(String firstName, String lastName, String id, String email,
		//		String password, int maxCredits)
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "A");
		
		CourseRoll rollA = c.getCourseRoll();
		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		Student a = new Student("first", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student b = new Student("firs", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student q = new Student("fir", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student d = new Student("f", "last", "fila", "fila@ncsu.edu", "pw", 15);
		//Student e = new Student("firsty", "last", "fila", "fila@ncsu.edu", "pw", 15);
		
		
		rollA.enroll(a);
		assertEquals(rollA.getOpenSeats(), 24);
		
		rollA.enroll(b);
		assertEquals(rollA.getOpenSeats(), 23);
		
		rollA.enroll(q);
		assertEquals(rollA.getOpenSeats(), 22);
		
		rollA.enroll(d);
		assertEquals(rollA.getOpenSeats(), 21);
	
		rollA.drop(a);
		assertEquals(rollA.getOpenSeats(), 22);
		
		//try to drop student not in roll
		//should not throw exception but instead do nothing
		rollA.drop(a);
		assertEquals(rollA.getOpenSeats(), 22);
	}
	
	/**
	 * test canenroll method
	 * @throws InvalidTransitionException 
	 * 		should really fix this
	 */
	@Test
	public void testCanEnroll() throws InvalidTransitionException {
		//(String firstName, String lastName, String id, String email,
		//		String password, int maxCredits)
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "A");
		
		CourseRoll rollA = c.getCourseRoll();
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		Student a = new Student("first", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student b = new Student("firs", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student q = new Student("fir", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student d = new Student("f", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student e = new Student("firsty", "last", "fila", "fila@ncsu.edu", "pw", 15);
		
		assertTrue(rollA.canEnroll(a));
		rollA.enroll(a);
		assertEquals(rollA.getOpenSeats(), 24);
		assertTrue(!rollA.canEnroll(a));
		assertEquals(rollA.getOpenSeats(), 24);

		rollA.enroll(b);
		assertEquals(rollA.getOpenSeats(), 23);
		
		rollA.enroll(q);
		assertEquals(rollA.getOpenSeats(), 22);
		
		rollA.enroll(d);
		assertEquals(rollA.getOpenSeats(), 21);
		
		//assertTrue()
		
		rollA.drop(a);
		assertEquals(rollA.getOpenSeats(), 22);
		assertTrue(rollA.canEnroll(a));

		//try to drop student not in roll
		//should not throw exception but instead do nothing
		rollA.drop(a);
		//assertEquals(rollA.getOpenSeats(), 22);
		
		//assertTrue(!rollA.canEnroll(a));
		//assertTrue(!rollA.canEnroll(q));
		//assertTrue(rollA.canEnroll(d));
		
		//assertTrue(rollA.canEnroll(a));
		//assertTrue(rollA.canEnroll(e));
		//assertTrue(rollA.canEnroll(e));
	}
	
}
