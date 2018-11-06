package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class CourseRollTest {
	/**
	 * test the constructor
	 */
	@Test
	public void testConstructor() {
		CourseRoll rollA = new CourseRoll(25);
		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
	}
	
	/**
	 * test setting the enrollment cap
	 */
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll rollA = new CourseRoll(25);
		
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
	 */
	@Test
	public void testAddStudent() {
		//(String firstName, String lastName, String id, String email,
		//		String password, int maxCredits)
		
		CourseRoll rollA = new CourseRoll(25);
		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		Student a = new Student("first", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student b = new Student("firs", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student c = new Student("fir", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student d = new Student("f", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student e = new Student("firsty", "last", "fila", "fila@ncsu.edu", "pw", 15);
		
		
		rollA.enroll(a);
		assertEquals(rollA.getOpenSeats(), 24);
		
		rollA.enroll(b);
		assertEquals(rollA.getOpenSeats(), 23);
		
		rollA.enroll(c);
		assertEquals(rollA.getOpenSeats(), 22);
		
		rollA.enroll(d);
		assertEquals(rollA.getOpenSeats(), 21);
	}
	
	/**
	 * test removing a student
	 */
	@Test
	public void testRemoveStudent() {
		//(String firstName, String lastName, String id, String email,
		//		String password, int maxCredits)
		
		CourseRoll rollA = new CourseRoll(25);
		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		Student a = new Student("first", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student b = new Student("firs", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student c = new Student("fir", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student d = new Student("f", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student e = new Student("firsty", "last", "fila", "fila@ncsu.edu", "pw", 15);
		
		
		rollA.enroll(a);
		assertEquals(rollA.getOpenSeats(), 24);
		
		rollA.enroll(b);
		assertEquals(rollA.getOpenSeats(), 23);
		
		rollA.enroll(c);
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
	 */
	@Test
	public void testCanEnroll() {
		//(String firstName, String lastName, String id, String email,
		//		String password, int maxCredits)
		
		CourseRoll rollA = new CourseRoll(25);
		
		//test that there are 25 open seats
		//test that the enrollment cap is 25.
		assertEquals(rollA.getOpenSeats(), 25);
		assertEquals(rollA.getEnrollmentCap(), 25);
		
		Student a = new Student("first", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student b = new Student("firs", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student c = new Student("fir", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student d = new Student("f", "last", "fila", "fila@ncsu.edu", "pw", 15);
		Student e = new Student("firsty", "last", "fila", "fila@ncsu.edu", "pw", 15);
		
		
		rollA.enroll(a);
		assertEquals(rollA.getOpenSeats(), 24);
		
		rollA.enroll(b);
		assertEquals(rollA.getOpenSeats(), 23);
		
		rollA.enroll(c);
		assertEquals(rollA.getOpenSeats(), 22);
		
		rollA.enroll(d);
		assertEquals(rollA.getOpenSeats(), 21);
	
		rollA.drop(a);
		assertEquals(rollA.getOpenSeats(), 22);
		
		//try to drop student not in roll
		//should not throw exception but instead do nothing
		rollA.drop(a);
		assertEquals(rollA.getOpenSeats(), 22);
		
		assertTrue(rollA.canEnroll(a));
		
		assertTrue(!rollA.canEnroll(c));
	}
	
}
