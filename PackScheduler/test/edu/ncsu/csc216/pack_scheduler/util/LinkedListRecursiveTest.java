package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListRecursiveTest {


	/**
	 * tests the constructor of the new LinikedList
	 */
	@Test
	public void Constructortest() {
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";

		
		LinkedListRecursive<String> rL = new LinkedListRecursive<String> ();
		assertEquals(rL.size(), 0);
		assertTrue(rL.isEmpty());
		//assertTrue(!rL.contains(a));
		
//		rL.add(a);
//		assertTrue(rL.contains(a));
//		
//		rL.add(b);
//		assertTrue(rL.contains(b));
//		
//		rL.add(c);
//		assertTrue(rL.contains(c));
//		
//		rL.add(d);
//		assertTrue(rL.contains(d));
//		
//		
//		assertTrue(!rL.contains(e));
	}
	
	/**
	 * tests add at index
	 */
	@Test
	public void testAddIdx() {
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		//String e = "e";

		
		LinkedListRecursive<String> rL = new LinkedListRecursive<String> ();
		assertEquals(rL.size(), 0);
		assertTrue(rL.isEmpty());
		rL.add(a);
		
		rL.add(b);
		
		rL.add(1, c);
		
		assertEquals(rL.get(1), b);
		assertEquals(rL.size(), 3);
		
		rL.add(0, d);
		assertEquals(rL.get(0), d);
		assertEquals(rL.size(), 4);
		assertTrue(rL.remove(c));
		assertEquals(rL.size(), 3);
		assertTrue(rL.contains(d));
		assertTrue(rL.remove(d));
		assertEquals(rL.size(), 2);
		assertTrue(rL.remove(b));
		assertEquals(rL.size(), 1);
	}
	/**
	 * removes an element at a given index
	 */
	@Test
	public void testRemoveindex() {
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";

		
		LinkedListRecursive<String> rL = new LinkedListRecursive<String> ();
		assertEquals(rL.size(), 0);
		assertTrue(rL.isEmpty());
		rL.add(a);
		
		rL.add(b);
		
		rL.add(1, c);
		
		assertEquals(rL.get(1), b);
		assertEquals(rL.size(), 3);
		
		rL.add(0, d);
		assertEquals(rL.get(0), d);
		assertEquals(rL.size(), 4);
		assertEquals(rL.remove(0), d);
		assertEquals(rL.size(), 3);
		assertEquals(rL.remove(1), c);
		assertEquals(rL.size(), 2);
		assertEquals(rL.remove(0), a);
		assertEquals(rL.size(), 1);
		
		assertEquals(rL.set(0, e), b);
		assertEquals(rL.get(0), e);
		
		rL.add(a);
		rL.add(c);
		rL.add(d);
		assertEquals(rL.set(1, e), c);
		assertEquals(rL.set(2, d), d);
	}
}