package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

public class LinkedQueueTest {
	/**
	 * tests the constructor
	 */
	@Test
	public void testConstructor() {
		@SuppressWarnings("rawtypes")
		LinkedQueue newQueue = new LinkedQueue(5);
		assertEquals(newQueue.size(), 0);
	}
	
	/**
	 * tests enqueue
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> newStack = new LinkedQueue<String>(5);
		String a = "a";
		String b = "b";
		String c = "c";
		
		//add one
		newStack.enqueue(a);
		
		//add two
		newStack.enqueue(b);
		
		//add three
		newStack.enqueue(c);
		
		assertEquals(newStack.size(), 3);
	}

	/**
	 * tests remove
	 */
	@Test
	public void testPop() {
		LinkedQueue<String> newStack = new LinkedQueue<String>(5);
		String a = "a";
		String b = "b";
		String c = "c";
		
		//add one
		newStack.enqueue(a);
		
		//add two
		newStack.enqueue(b);
		
		//add three
		newStack.enqueue(c);
		
		//test removing first element
		assertEquals(newStack.dequeue(), a);
		//test removing second element
		assertEquals(newStack.dequeue(), b);
		//test removing third element
		assertEquals(newStack.dequeue(), c);
		
		
		//add one
		newStack.enqueue(a);
		
		//add two
		newStack.enqueue(b);
		
		//test removing top  element
		assertEquals(newStack.dequeue(), a);
		
		//add three
		newStack.enqueue(c);

		assertEquals(newStack.dequeue(), b);
		//test removing element
		assertEquals(newStack.dequeue(), c);
	
		try {
			newStack.dequeue();
			fail("Can't remove element from an empty stack");
		} catch (NoSuchElementException e) {
			//successful
		}	
	}
	
	/**
	 * tests setting capacity
	 */
	@Test
	public void testSetCapacity() {
		LinkedQueue<String> newStack = new LinkedQueue<String>(5);
		String a = "a";
		String b = "b";
		String c = "c";
		
		//add one
		newStack.enqueue(a);
		
		//add two
		newStack.enqueue(b);
		
		//add three
		newStack.enqueue(c);
	
		newStack.setCapacity(3);
		
		try {
			newStack.setCapacity(1);
			fail("Shouldn't be able to set to capacity less than size");
		} catch (IllegalArgumentException e) {
			//successful test
		}

	}
}


