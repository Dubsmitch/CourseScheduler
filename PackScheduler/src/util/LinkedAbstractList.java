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
	
	public ListNode get(int index) {
		
		return null;
	}
	
	@Override
	public void add (int index, E e) {
		if (e.equals(null)) {
			throw new NullPointerException ("cannot add a null object to the list");
		}
		
		if (capacity == size) {
			throw new IllegalArgumentException ("The list is full");
		}
		//if empty then add a new listnode with a null tail link
		if (size == 0) {
			front = new ListNode(e);
			
		//if the size isn't 0 and the index is
		//not equal to the size (adding to second index)
		} else if (size > 0 && index == 1) {
			//make first node reference the second node
			ListNode current = front;
			ListNode Temp = new ListNode(e);
			
//			for (int i = 0; i < index; i++) {
//				current = current.getNext();
//			}
			 	
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
			this.data = data;
			next = null;
		}
		public ListNode (E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
