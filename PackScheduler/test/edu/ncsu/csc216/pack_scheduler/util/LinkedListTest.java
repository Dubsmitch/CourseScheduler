package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	/**
	 * tests the constructor of the new LinikedList
	 */
	@Test
	public void Constructortest() {
//		String b = "b";
//		String c = "c";
		LinkedList <String> a = null;
		a = new LinkedList<String>();
		
		assertEquals(a.size(), 0);
//		a.add(0, b);
//		fail("Not yet implemented");
	}

	/**
	 * tests the add method
	 */
	@Test
	public void Addtest() {
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String f = "f";
		String g = "g";
		String h = "h";
		String i = "i";
		String j = "j";
		String k = "k";

		LinkedList <String> a = null;
		a = new LinkedList<String>();
		
		//add one object
		a.add(0, b);
		assertEquals(a.get(0), b);
		assertEquals(a.size(), 1);

		//add second object to the front
		a.add(0, c);
		assertEquals(a.size(), 2);
		assertEquals(a.get(0), c);

		//add third object
		a.add(0, d);
		assertEquals(a.size(), 3);
		
		//add object to the end
		a.add(3, e);
		assertEquals(a.size(), 4);
		assertEquals(a.get(3), e);

		//add object to the middle
		a.add(2, f);
		assertEquals(a.size(), 5);
		assertEquals(a.get(0), d);
		assertEquals(a.get(1), c);
		assertEquals(a.get(2), f);
		assertEquals(a.get(3), b);
		
		a.add(0, g);
		assertEquals(a.size(), 6);
		a.add(3, h);
		assertEquals(a.size(), 7);
		a.add(3, i);
		assertEquals(a.size(), 8);
		
		a.add(3, j);
		assertEquals(a.size(), 9);
		//try to double size
		a.add(3, k);
		assertEquals(a.size(), 10);
		
		
		//a.get(0)
		assertEquals(a.get(0), g);
		assertEquals(a.get(9), e);
	}
	
	/**
	 * tests the remove method
	 * This test also tests the get method
	 */
	@Test
	public void Removetest() {
		LinkedList <String> a = null;
		a = new LinkedList<String>();
		
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		
		//add one object
		a.add(0, b);
		
		assertEquals(a.size(), 1);
		assertEquals(a.get(0), b);

		//add second object to the front
		a.add(0, c);
		assertEquals(a.size(), 2);
		assertEquals(a.get(0), c);
				
		//add third object
		a.add(0, d);
		assertEquals(a.size(), 3);
		assertEquals(a.get(0), d);
		assertEquals(a.get(1), c);
				
		//add object to the end
		a.add(3, e);
		assertEquals(a.size(), 4);
		assertEquals(a.get(2), b);
		
		//remove from the middle
		assertEquals(a.remove(2), b);
		//remove from the front
		assertEquals(a.remove(0), d);
		assertEquals(a.size(), 2);
		
		//remove from the end
		assertEquals(a.remove(1), e);
	}
	
	/**
	 * tests the set method
	 */
	@Test
	public void Settest() {
		LinkedList <String> a = null;
		a = new LinkedList<String>();
		
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String aa = "aa";
		String bb = "bb";
		String cc = "cc";
		//add one object
		a.add(0, b);
		
		assertEquals(a.size(), 1);
		assertEquals(a.get(0), b);

		//add second object to the front
		a.add(0, c);
		assertEquals(a.size(), 2);
		assertEquals(a.get(0), c);
				
		//add third object
		a.add(0, d);
		assertEquals(a.size(), 3);
		assertEquals(a.get(0), d);
		assertEquals(a.get(1), c);
				
		//add object to the end
		a.add(3, e);
		assertEquals(a.size(), 4);
		assertEquals(a.get(2), b);
		
		a.set(3, aa);
		assertEquals(a.size(), 4);
		assertEquals(a.get(3), aa);
		assertEquals(a.get(2), b);
		
		
		a.set(2, bb);
		assertEquals(a.size(), 4);
		assertEquals(a.get(2), bb);
		
		a.set(0, cc);
		assertEquals(a.size(), 4);
		assertEquals(a.get(0), cc);
		
		//a.add(3, bb);
		
	}
}