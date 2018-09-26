package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class SortedListTest {

	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		//confirm the list is empty:
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		assertTrue(list.isEmpty());
		
		//TODO Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		list.add("ball");		//1
		list.add("apple");		//2
		list.add("cat");		//3
		list.add("dog");		//4
		list.add("elephant");	//5
		list.add("frog");		//6
		list.add("gorilla");	//7
		list.add("heart");		//8
		list.add("inter");		//9
		list.add("jam");		//10
		list.add("kangaroo");	//11
		
		assertEquals(11, list.size());
		
	}

	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//add to the end
		list.add("cat");
		assertEquals(2, list.size());
		assertEquals("cat", list.get(1));
		
		//add to the middle
		list.add("bbanana");
		assertEquals(3, list.size());
		assertEquals("bbanana", list.get(1));
				
		//TODO Test adding a null element
		try{
			list.add(null);
			fail("added a null element");
		} catch (NullPointerException e){
			assertEquals(3, list.size());
		}
		
		
		//TODO Test adding a duplicate element
		try {
			list.add("cat");
			fail("added a duplicate");
		} catch (IllegalArgumentException e) {
			assertEquals(3, list.size());
		}
	}
	
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//TODO Add some elements to the list
		list.add("a");
		
		//TODO Test getting an element at an index < 0
		try {
			list.get(-1);
			fail("trying to get item from index less than zero");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		
		//TODO Test getting an element at size
		
		try {
			list.get(list.size());
			fail("Trying to get an element at the size");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		
	}
	
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		try{
			list.remove(0);
			fail("can't remove item from and empty list");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//TODO Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		//TODO Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail("Can't remove an element from index of less than zero");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//TODO Test removing an element at size
		
		try {
			list.remove(list.size());
			fail("Can't remove an element from index of list size");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//TODO Test removing a middle element
		

		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		assertEquals("d", list.get(2));
		
		
		
		//TODO Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals("a", list.get(0));
		assertEquals("b", list.get(1));
		
		
		//TODO Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("b", list.get(0));

		//TODO Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		list.indexOf("a");
		assertEquals(0, list.size());
		assertEquals(-1, list.indexOf("a"));
		
		//TODO Add some elements
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(4, list.size());
		assertEquals(0, list.indexOf("a"));
		assertEquals(1, list.indexOf("b"));
		assertEquals(2, list.indexOf("c"));
		assertEquals(3, list.indexOf("d"));
		assertEquals(-1, list.indexOf("g"));
		
		//TODO Test checking the index of null
		try {
			list.indexOf(null);
			fail("null cannot have an index");
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
	}
	
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertEquals(0, list.size());
	}

	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		//TODO Add at least one element
		list.add("a");
		
		//TODO Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertTrue(list.isEmpty());
		assertFalse(list.contains("a"));
		
		//TODO Add some elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		//TODO Test some true and false cases
		assertTrue(list.contains("a"));
		assertTrue(list.contains("b"));
		assertFalse(list.contains("e"));
		assertFalse(list.contains("g"));
	}
	
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("a");
		list2.add("a");
		list3.add("b");
		//TODO Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertTrue(list1.equals(list1));
		assertFalse(list1.equals(list3));
		
	}
	
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("a");
		list2.add("a");
		list3.add("b");
		
		//TODO Test for the same and different hashCode		assertTrue(list1.equals(list2));
		assertEquals(list2.hashCode(), list1.hashCode());
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list1.hashCode(), list3.hashCode());
	}

}
