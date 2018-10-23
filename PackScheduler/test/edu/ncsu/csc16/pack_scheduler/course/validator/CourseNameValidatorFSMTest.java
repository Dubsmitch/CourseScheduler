package edu.ncsu.csc16.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

public class CourseNameValidatorFSMTest {

	@Test
	public void test() {
		CourseNameValidatorFSM a = new CourseNameValidatorFSM();

		try {
			assertTrue(a.isValid("1"));
			fail("incorrect construction");
		} catch (InvalidTransitionException e) {
		}

		try {
			assertTrue(a.isValid("!"));
			fail("incorrect construction");
		} catch (InvalidTransitionException e) {
		}
		
		try{
			assertTrue(a.isValid("A111"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("AA111"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("AAA111"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("AAAA111"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("A111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("AA111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("AAA111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertTrue(a.isValid("AAAA111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		try{
			assertEquals(a.isValid("AAAAA111"), false);
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
		
		try{
			assertTrue(a.isValid("AAA11A"));
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
		
		try{
			assertTrue(a.isValid("AAA111AA"));
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
		
		try{
			assertTrue(a.isValid("AAA111A1"));
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
		
		try{
			assertTrue(a.isValid("AAA1111"));
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
		
		try{
			assertTrue(a.isValid("AAA1A"));
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
		
		try{
			assertTrue(a.isValid(""));
			fail("");
		} catch (InvalidTransitionException e) {
			
		}
}

}
