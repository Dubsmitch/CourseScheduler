package edu.ncsu.csc216.pack_scheduler.user;


import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {
	
	///** first name **/
	//private static final String FIRST_NAME = "First";
	///** last name **/

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testStudentStringStringStringStringStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testStudentStringStringStringStringString() {
		
		Student s = null; //Initialize a student reference to null
		try {
			s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
			assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
			assertEquals(18, s.getMaxCredits());
			
		} catch (IllegalArgumentException e){
			fail();
		}

		// Start testing invalid input for each parameter - one at a time.
		// The standard structure of an invalid test is the following:
		s = null;
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		s = null; 
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword");
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		s = null; 
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null);
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}

	}

	@Test
	public void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxCredits() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFirstName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLastName() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
