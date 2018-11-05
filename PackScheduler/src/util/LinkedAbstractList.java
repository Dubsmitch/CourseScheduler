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
	/** reference to the first element **/
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
	
	public E get(int index) {
		int idx = 0;
		E datum = null; 
		for (ListNode p = front; p != null; p = p.next) {
			if (idx == index) {
				datum = p.data;
			}
		}
		return datum;
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
			size = size + 1;
		//if the size isn't 0 and the index is 1
		//not equal to the size (adding to second index)
		} else if (size > 0 && index == 1) {
			//make first node reference the second node
			ListNode newFront = front;
			front = new ListNode(e, front);
			size = size + 1;
		//add to middle
		} else if (size > 1 && index > 1) {
			ListNode leading = front;
			ListNode trailing = null;
			int idx = 0;
			//theoretical: size 8, index to insert: 5
			//need to change the reference of index 4 to the new obj
			//need to insert at # 5 and make a reference to old 5
			while (leading != null && idx < index ) {
				trailing = leading;
				leading = leading.next;
			}
			//search for the insertion position. Stop when it
			//finds the index, then sets the first node equal to old
			//sets the trailing equal to #4
			trailing.next = new ListNode(e, leading);
			
			//not sure if this is correct 
		}
	}
	
	public E remove (int idx) {
		ListNode leading = front;
		ListNode trailing = null;
		
		while (leading != null && idx > 0) {
			trailing = leading;
			leading = leading.next;
			
			idx--;
		}
		if (leading != null ) {
			if (leading == front ) {
				front = front.next;
			} else {
				trailing.next = leading.next;
			} return leading.data;
		} return null;
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
