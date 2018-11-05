package util;

import java.util.AbstractList;
/**
 * class to create the functionality of a linked
 * abstract list
 * 
 * @author William
 *
 * @param <E>
 * 		generic type parameter
 */
public class LinkedAbstractList<E> extends AbstractList {
	private ListNode front;
	
	private int size;
	
	private int capacity;
	
	public LinkedAbstractList (int cap) {
		if (cap < size) {
			throw new IllegalArgumentException ("Capacity cannot be less "
					+ "than size.");
		}
		
		front = null;
		size = 0;
		capacity = cap;
		
		
	}
	
	@Override
	public void add (int index, E e) {
		if (e.equals(null)) {
			throw new NullPointerException ("cannot add a null object to the list");
		}
		
		if (capacity == size) {
			throw new IllegalArgumentException ("The list is full");
		}
		
		
	}
	
	/**
	 * inner class of LinkedAbstractList 
	 * 
	 * @author William
	 *
	 */
	private class ListNode {
		
		private E data;
		
		private ListNode next;
		
		public ListNode (E data) {
			//to-do
		}
		public ListNode (E data, ListNode next) {
			//to-do
		}
	}
}
