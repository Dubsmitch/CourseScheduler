package edu.ncsu.csc16.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

public class CourseNameValidatorTest {

	@Test
	public void test() {
		CourseNameValidator a = new CourseNameValidator();
		//99% coverage
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
		
		CourseNameValidator b = new CourseNameValidator();
		
		try{
			assertTrue(b.isValid("AA111"));
		} catch (InvalidTransitionException e) {
			fail("This is a valid construction");
		}
		
		CourseNameValidator c = new CourseNameValidator();
		
		try{
			assertTrue(c.isValid("AAA111"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		CourseNameValidator d = new CourseNameValidator();
		
		try{
			assertTrue(d.isValid("AAAA111"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		CourseNameValidator f = new CourseNameValidator();
		
		try{
			assertTrue(f.isValid("A111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		CourseNameValidator g = new CourseNameValidator();
		
		try{
			assertTrue(g.isValid("AA111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		CourseNameValidator h = new CourseNameValidator();
		try{
			assertTrue(h.isValid("AAA111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		CourseNameValidator i = new CourseNameValidator();
		try{
			assertTrue(i.isValid("AAAA111A"));
		} catch (InvalidTransitionException e) {
			fail("");
		}
		
		CourseNameValidator j = new CourseNameValidator();
		
		try{
			assertEquals(j.isValid("AAAAA111"), false);
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
}

}
