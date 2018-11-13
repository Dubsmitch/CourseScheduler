package util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;
/**
 * tests the linkedStack class
 * 
 * @author William
 *
 */
public class LinkedStackTest {
	/**
	 * tests the constructor
	 */
	@Test
	public void testConstructor() {
		@SuppressWarnings("rawtypes")
		LinkedStack newStack = new LinkedStack(5);
		assertEquals(newStack.size(), 0);
	}
	
	/**
	 * tests pop
	 */
	@Test
	public void testPush() {
		LinkedStack<String> newStack = new LinkedStack<String>(5);
		String a = "a";
		String b = "b";
		String c = "c";
		
		//add one
		newStack.push(a);
		
		//add two
		newStack.push(b);
		
		//add three
		newStack.push(c);
		
		assertEquals(newStack.size(), 3);
	}

	/**
	 * tests remove
	 */
	@Test
	public void testPop() {
		LinkedStack<String> newStack = new LinkedStack<String>(5);
		String a = "a";
		String b = "b";
		String c = "c";
		
		//add one
		newStack.push(a);
		
		//add two
		newStack.push(b);
		
		//add three
		newStack.push(c);
		
		//test removing first element
		assertEquals(newStack.pop(), c);
		//test removing second element
		assertEquals(newStack.pop(), b);
		//test removing third element
		assertEquals(newStack.pop(), a);
		
		
		//add one
		newStack.push(a);
		
		//add two
		newStack.push(b);
		
		//test removing top (second) element
		assertEquals(newStack.pop(), b);
		
		//add three
		newStack.push(c);

		assertEquals(newStack.pop(), c);
		//test removing element
		assertEquals(newStack.pop(), a);
	
		try {
			newStack.pop();
			fail("Can't remove element from an empty stack");
		} catch (EmptyStackException e) {
			//successful
		}	
	}
	
	/**
	 * tests setting capacity
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack<String> newStack = new LinkedStack<String>(5);
		String a = "a";
		String b = "b";
		String c = "c";
		
		//add one
		newStack.push(a);
		
		//add two
		newStack.push(b);
		
		//add three
		newStack.push(c);
	
		newStack.setCapacity(3);
		
		try {
			newStack.setCapacity(1);
			fail("Shouldn't be able to set to capacity less than size");
		} catch (IllegalArgumentException e) {
			//successful test
		}

	}
}