package edu.ncsu.csc216.pack_scheduler.user;


import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {

	@Test
	public void testHashCode() {
		Student s1 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s2 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//different on all states
		Student s3 = new Student ("firsty", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s4 = new Student ("first", "lasty", "id", "email@ncsu.edu", "hashedpassword");
		Student s5 = new Student ("first", "last", "idy", "email@ncsu.edu", "hashedpassword");
		Student s6 = new Student ("first", "last", "id", "email@ncsu.eduy", "hashedpassword");
		Student s7 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpasswordy");
		Student s8 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 5);
		//test for equality in all fields
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//test each field for differences
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	@Test
	public void testStudentStringStringStringStringStringInt() {
		//needs to create a valid student
		//and test for credits
		
		//test creating a valid Student
		Student s = null; //Initialize a student reference to null
		try {
			s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 18);
			assertEquals("first", s.getFirstName());
			assertEquals("last", s.getLastName());
			assertEquals("id", s.getId());
			assertEquals("email@ncsu.edu", s.getEmail());
			assertEquals("hashedpassword", s.getPassword());
			assertEquals(18, s.getMaxCredits());
			
		} catch (IllegalArgumentException e){
			fail();
		}
		
		//boundary test for <3 credits
		s = null;
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//boundary test for >18 credits
		s = null;
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 19);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
				
		
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
		// test null firstname
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
		//test empty firstname
		s = null;
		try {
		    s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test null lastname
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

		//test empty lastname
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "", "id", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}

		// test null id
		s = null;
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test empty id
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "lastname", "", "email@ncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}

		//test null email
		s = null; 
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword");
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test empty email
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test last period before '@' sign
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "email.ncsu@edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}

		//test no period in email
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "email@ncsuedu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test no @ lastname
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "emailncsu.edu", "hashedpassword");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test null password
		s = null; 
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null);
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//test empty password
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "");
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}


	}

	@Test
	public void testSetEmail() {
		Student s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//testing trying to set email to null
		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set email to empty, ""
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set email to string with '.' before '@'
		try {
			s.setEmail("yo.ncsu@edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set email without '@'
		try {
			s.setEmail("ncsu.edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set email without '.'
		try {
			s.setEmail("ncsu@edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}	
	}

	@Test
	public void testSetPassword() {
		Student s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//testing trying to set password to null
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set password to empty: ""
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
	}

	@Test
	public void testSetMaxCredits() {
		Student s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//testing trying to set credits to less than 3
		try {
			s.setMaxCredits(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set credits to more than 18
		try {
			s.setMaxCredits(19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing trying to set credits to 15
		try {
			s.setMaxCredits(15);
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (15, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testSetFirstName() {
		Student s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//testing trying to set first name null
		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing for an empty first name
		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
			
		}
	}

	@Test
	public void testSetLastName() {
		Student s = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//testing trying to set last name null, not thing should change
		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
		}
		
		//testing for an empty last name
		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals ("first", s.getFirstName());
			assertEquals ("last", s.getLastName());
			assertEquals ("id", s.getId());
			assertEquals ("email@ncsu.edu", s.getEmail());
			assertEquals ("hashedpassword", s.getPassword());
			assertEquals (18, s.getMaxCredits());
			
		}
		
	}

	@Test
	public void testEqualsObject() {
		Student s1 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s2 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		String word = "not a student";
		
		//different on all states
		Student s3 = new Student ("firsty", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s4 = new Student ("first", "lasty", "id", "email@ncsu.edu", "hashedpassword");
		Student s5 = new Student ("first", "last", "idy", "email@ncsu.edu", "hashedpassword");
		Student s6 = new Student ("first", "last", "id", "email@ncsu.eduy", "hashedpassword");
		Student s7 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpasswordy");
		Student s8 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 5);
		//test for equality in all fields
		assertTrue(s1.equals(s2));
		assertTrue(s1.equals(s1));
		assertFalse(s1.equals(word));
		assertTrue(s2.equals(s1));
		
		//test for each fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}

	/**
	 * Tests that toString returns the correct comma-separated value.
	 */
	@Test
	public void testToString() {
		Student s1 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(s1.toString(), string1);
		
		Student s2 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		String string2 = "first,last,id,email@ncsu.edu,hashedpassword,15";
		assertEquals(s2.toString(), string2);
		
	}

}
