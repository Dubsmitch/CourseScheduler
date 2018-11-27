package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

public class LinkedAbstractListTest {


	/**
	 * tests the constructor of the new array
	 */
	@Test
	public void Constructortest() {
//		String b = "b";
//		String c = "c";
		LinkedAbstractList<String> a = null;
		a = new LinkedAbstractList<String>(1);
		
		assertEquals(a.size(), 0);
//		a.add(0, b);
//		fail("Not yet implemented");
	}

	/**
	 * adding in testing for add() method
	 */
	@Test
	public void Addtest() {
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String f = "f";
		

		LinkedAbstractList<String> a = null;
		a = new LinkedAbstractList<String>(10);
		
		assertEquals(a.size(), 0);
		
		//add one object
		a.add(0, b);
		
		assertEquals(a.size(), 1);
		
		assertTrue(a.get(0).equals(b));

		//add second object to the front
		a.add(0, c);
		//should be object c in front
		assertTrue(a.get(0).equals(c));
		assertEquals(a.size(), 2);

		assertTrue(a.get(1).equals(b));
//		
//		//add third object
		a.add(0, d);
		assertEquals(a.size(), 3);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(b));
//		
//		//add object to the end
		a.add(3, e);
		assertEquals(a.size(), 4);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(b));
		assertTrue(a.get(3).equals(e));
//		
//		//add object to the middle
		a.add(2, f);
		assertEquals(a.size(), 5);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(f));
		assertTrue(a.get(3).equals(b));
		assertTrue(a.get(4).equals(e));		
	}
	
	/**
	 * test removal
	 */

	@Test
	public void Removetest() {
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String f = "f";
	
		LinkedAbstractList<String> a = null;
		a = new LinkedAbstractList<String>(10);
		
		assertEquals(a.size(), 0);
		
		//add one object
		a.add(0, b);
		
		assertEquals(a.size(), 1);
		
		assertTrue(a.get(0).equals(b));

		//add second object to the front
		a.add(0, c);
		//should be object c in front
		assertTrue(a.get(0).equals(c));
		assertEquals(a.size(), 2);

		assertTrue(a.get(1).equals(b));
//		
//		//add third object
		a.add(0, d);
		assertEquals(a.size(), 3);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(b));
//		
//		//add object to the end
		a.add(3, e);
		assertEquals(a.size(), 4);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(b));
		assertTrue(a.get(3).equals(e));
//		
//		//add object to the middle
		a.add(2, f);
		assertEquals(a.size(), 5);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(f));
		assertTrue(a.get(3).equals(b));
		assertTrue(a.get(4).equals(e));		
		//test removal from pos. 4 (the end)
		assertTrue(a.remove(4).equals(e));
		assertEquals(a.size(), 4);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(f));
		assertTrue(a.get(3).equals(b));
		
		//test removal from the front (pos 0)
		assertTrue(a.remove(0).equals(d));
		assertEquals(a.size(), 3);
		assertTrue(a.get(0).equals(c));
		assertTrue(a.get(1).equals(f));
		assertTrue(a.get(2).equals(b));
		
		//test removal from the middle (pos 1)
		assertTrue(a.remove(1).equals(f));
		assertEquals(a.size(), 2);
		assertTrue(a.get(0).equals(c));
		assertTrue(a.get(1).equals(b));
		
	}
	
	/**
	 * tests setting at an index
	 */ 

	@Test
	public void Settest() {
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String f = "f";

		String l = "l";
		String m = "m";

		LinkedAbstractList<String> a = null;
		a = new LinkedAbstractList<String>(10);
		
		assertEquals(a.size(), 0);
		
		//add one object
		a.add(0, b);
		
		assertEquals(a.size(), 1);
		
		assertTrue(a.get(0).equals(b));

		//add second object to the front
		a.add(0, c);
		//should be object c in front
		assertTrue(a.get(0).equals(c));
		assertEquals(a.size(), 2);

		assertTrue(a.get(1).equals(b));
//		
//		//add third object
		a.add(0, d);
		assertEquals(a.size(), 3);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(b));
//		
//		//add object to the end
		a.add(3, e);
		assertEquals(a.size(), 4);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(b));
		assertTrue(a.get(3).equals(e));
//		
//		//add object to the middle
		a.add(2, f);
		assertEquals(a.size(), 5);
		assertTrue(a.get(0).equals(d));
		assertTrue(a.get(1).equals(c));
		assertTrue(a.get(2).equals(f));
		assertTrue(a.get(3).equals(b));
		assertTrue(a.get(4).equals(e));	
		
		a.set(0, m);
		assertEquals(a.size(), 5);
		assertTrue(a.get(0).equals(m));
		
		a.set(3, l);
		assertEquals(a.size(), 5);
		assertTrue(a.get(3).equals(l));

	}
	
}
