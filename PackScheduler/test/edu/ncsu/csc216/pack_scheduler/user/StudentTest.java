package edu.ncsu.csc216.pack_scheduler.user;


import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
/**
 * This tests the Student class
 * 
 * Getter tests have been omitted and will be tested
 * through the testing of the setters and constructors
 * 
 * @author William
 *
 */
public class StudentTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** cap **/
	private static final int CAP = 25;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * tests that hashCode() works correctly
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s2 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		//different on all states
		User s3 = new Student ("firsty", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s4 = new Student ("first", "lasty", "id", "email@ncsu.edu", "hashedpassword");
		User s5 = new Student ("first", "last", "idy", "email@ncsu.edu", "hashedpassword");
		User s6 = new Student ("first", "last", "id", "email@ncsu.eduy", "hashedpassword");
		User s7 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpasswordy");
		User s8 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 5);
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

	/**
	 * tests all fields of the constructor to create a student
	 * Also tests the Credits state
	 */
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
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//boundary test for >18 credits
		s = null;
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 19);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
				
		
	}
	/**
	 * tests all fields of the constructor for Students except for Credits
	 * 
	 */
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
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		//test empty firstname
		s = null;
		try {
		    s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword");
		   
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//test null lastname
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", null, "id", "email@ncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//test empty lastname
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "", "id", "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		// test null id
		s = null;
		try {
		    s = new Student("first", "last", null, "email@ncsu.edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//test empty id
		s = null;
		try {
		    s = new Student("first", "lastname", "", "email@ncsu.edu", "hashedpassword");
		    
		    fail();
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}

		//test null email
		s = null; 
		try {
		    s = new Student("first", "last", "id", null, "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {
		    
		    assertNull(s);
		}
		
		//test empty email
		s = null;
		try {
		    s = new Student("first", "last", "id", "", "hashedpassword");
		    
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//test last period before '@' sign
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "email.ncsu@edu", "hashedpassword");
		    fail();
		} catch (IllegalArgumentException e) {

		    assertNull(s);
		}

		//test no period in email
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "email@ncsuedu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//test no @ lastname
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "emailncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//test null password
		s = null; 
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		//test empty password
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}


	}
	/**
	 * tests that setEmail() works correctly
	 */
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
	/**
	 * tests that setPassword() works correctly
	 */
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
	/**
	 * tests that setMaxCredits() works correctly
	 */
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
	/**
	 *test that setFirstName() works correctly 
	 */
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
	/**
	 * tests that setLastName() works correctly
	 */
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
	/**
	 * tests that equalsObject() works correctly
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s2 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		
		
		//for testing object of a different class, a string is a class
		String word = "not a student";
		
		//different on all states
		User s3 = new Student ("firsty", "last", "id", "email@ncsu.edu", "hashedpassword");
		User s4 = new Student ("first", "lasty", "id", "email@ncsu.edu", "hashedpassword");
		User s5 = new Student ("first", "last", "idy", "email@ncsu.edu", "hashedpassword");
		User s6 = new Student ("first", "last", "id", "email@ncsu.eduy", "hashedpassword");
		User s7 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpasswordy");
		User s8 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 5);
		//test for equality in all fields
		assertTrue(s1.equals(s2));
		assertTrue(s1.equals(s1));
		assertTrue(s2.equals(s1));
		
		//test for comparing types
		assertFalse(s1.equals(word));
		
		//test for each field
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
		User s1 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(s1.toString(), string1);
		
		User s2 = new Student ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		String string2 = "first,last,id,email@ncsu.edu,hashedpassword,15";
		assertEquals(s2.toString(), string2);
		
	}
	
	/**
	 * tests that the compareTo() functions correctly
	 * testing ordering by last name, then first name, then id
	 */
	@Test
	public void testCompareTo() {
		//("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		Student s1 = new Student ("a", "a", "a", "email@ncsu.edu", "hashedpassword", 15);
		Student s2 = new Student ("a", "a", "b", "email@ncsu.edu", "hashedpassword", 15);
		Student s3 = new Student ("b", "a", "a", "email@ncsu.edu", "hashedpassword", 15);
		Student s4 = new Student ("b", "b", "a", "email@ncsu.edu", "hashedpassword", 15);
		//compare two equal students
		assertEquals(0, s1.compareTo(s1));
		
		//compare two students with different last names
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(1, s4.compareTo(s1));
		
		//compare two students with same last names, different first names
		assertEquals(-1, s1.compareTo(s3));
		assertEquals(1, s3.compareTo(s1));
		
		//compare two students with same first and last names, different ids
		assertEquals(-1, s1.compareTo(s2));
		assertEquals(1, s2.compareTo(s1));
		
		

	}
	
	/**
	 * tests can add method
	 * @throws InvalidTransitionException 
	 * @throws ConflictException 
	 */
	@Test
	public void testCanAdd() throws InvalidTransitionException, ConflictException {
		Student s1 = new Student ("a", "a", "a", "email@ncsu.edu", "hashedpassword", 15);
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0011, 0012);

		
		//student credits 15
		//schedule credits 0
		//course credits 4
		
		assertTrue(s1.canAdd(c));
		s1.schedule.addCourseToSchedule(c);
		
		assertEquals(s1.getSchedule().getSchedule().size(), 1);
		
		Course c2 = new Course("AAAA", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0013, 0014);
		
		//student credits 15
		//schedule credits 4
		//course credits 4
		
		s1.schedule.addCourseToSchedule(c2);
		
		//student credits 15
		//schedule credits 4
		//course credits 8
		
		Course c3 = new Course("AABA", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0015, 0016);
		s1.schedule.addCourseToSchedule(c3);

		//student credits 15
		//schedule credits 4
		//course credits 12
		Course c4 = new Course("AACA", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0017, 18);
		assertTrue(!s1.canAdd(c4));
		
		//boundary case
		Course c5 = new Course("AACA", TITLE, SECTION, 3, INSTRUCTOR_ID, CAP, MEETING_DAYS, 0017, 18);
		assertTrue(s1.canAdd(c5));

		

	}
}
