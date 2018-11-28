package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

public class FacultyTest {
	/**
	 * tests the constructor
	 */
	@Test
	public void testConstructor() {
		User f1 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		
		assertEquals(f1.getEmail(), "email@ncsu.edu");
		try {
			@SuppressWarnings("unused")
			User f2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 0);
			fail("Should not be able to construct without a course");
		} catch (IllegalArgumentException e){
		}
		
		try {
			@SuppressWarnings("unused")
			User f2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 4);
			fail("Should not be able to construct with more than 3 courses");
		} catch (IllegalArgumentException e){
		}
		
	}
	
	/**
	 * tests the getter and setter for max courses
	 */
	@Test
	public void testGetMaxCourses() {
		Faculty f1 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		
		assertEquals(f1.getEmail(), "email@ncsu.edu");
		
		f1.setMaxCourses(2);
		assertEquals(f1.getMaxCourses(), 2);
		
		try {
			Faculty f2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);

			f2.setMaxCourses(5);
			fail("Should not be able to construct with more than 3 courses");
		} catch (IllegalArgumentException e){
		}
		
		try {
			Faculty f2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);

			f2.setMaxCourses(0);
			fail("Should not be able to construct with less than 1 course");
		} catch (IllegalArgumentException e){
		}
	}
	
	/**
	 * tests the to string method
	 */
	@Test
	public void testToString() {
		Faculty s1 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,2";
		assertEquals(s1.toString(), string1);
		
		Faculty s2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		String string2 = "first,last,id,email@ncsu.edu,hashedpassword,1";
		assertEquals(s2.toString(), string2);
	}
	
	/**
	 * tests that hashCode() works correctly
	 */
	@Test
	public void testHashCode() {
		Faculty s1 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		
		//different on all states
		Faculty s3 = new Faculty ("firsty", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s4 = new Faculty ("first", "lasty", "id", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s5 = new Faculty ("first", "last", "idy", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s6 = new Faculty ("first", "last", "id", "email@ncsu.eduy", "hashedpassword", 1);
		Faculty s7 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpasswordy", 1);
		Faculty s8 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
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
	 * tests that equalsObject() works correctly
	 */
	@Test
	public void testEqualsObject() {
		Faculty s1 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s2 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		
		
		//for testing object of a different class, a string is a class
		String word = "not a Faculty";
		
		//different on all states
		Faculty s3 = new Faculty ("firsty", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s4 = new Faculty ("first", "lasty", "id", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s5 = new Faculty ("first", "last", "idy", "email@ncsu.edu", "hashedpassword", 1);
		Faculty s6 = new Faculty ("first", "last", "id", "email@ncsu.eduy", "hashedpassword", 1);
		Faculty s7 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpasswordy", 1);
		Faculty s8 = new Faculty ("first", "last", "id", "email@ncsu.edu", "hashedpassword", 2);
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
}


