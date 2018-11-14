package edu.ncsu.csc216.pack_scheduler.util;

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
public class LinkedAbstractList<E> extends AbstractList<Object> {
	/** reference to the first element **/
	private ListNode front;
	
	private int size;
	
	private int capacity;
	
	private ListNode back;
	
	public LinkedAbstractList (int cap) {
		if (cap < size) {
			throw new IllegalArgumentException ("Capacity cannot be less "
					+ "than size.");
		}
		front = null;
		size = 0;
		capacity = cap;
		back = null;
	}
	
	public E get(int index) {
		int idx = 0;
		E datum = null; 
		for (ListNode p = front; p != null; p = p.next) {
			
			if (idx == index) {
				datum = p.data;
			}
			idx++;
		}
		return datum;
	}
	
	@Override
	public void add (int index, Object e) {
		if (e == null) {
			throw new NullPointerException ("cannot add a null object to the list");
		}
		
		if (capacity == size) {
			throw new IllegalArgumentException ("The list is full");
		}
		//System.out.println(theSize);
		//first addition taken care of//
		if (size == 0 && index == 0) {
			ListNode newNode = new ListNode(e);
			front = newNode;
			back = newNode;
			size = size + 1;

		} else if (index == 0 && (size >= 1 && size != index)) {
			front = new ListNode (e, front);
			//adding to the end//
			size = size + 1;

		} else if (front != null && index != size && index > 0) {
			ListNode leading = front;
			while (leading != null && index > 1) {
				leading = leading.next;
				index--;
			}
			if (leading != null) {
				leading.next = new ListNode (e, leading.next);
			}
			size = size + 1;

		} else if (size != 0 && index == size) {
			size = size + 1;

			//make new node for old back//
			ListNode newBack = new ListNode (e);
			if (back == null) {
				System.out.println("back is null");
			}
			back.next = newBack;
			
			back = newBack;
		} else if (front != null && index != size && index > 0) {
			ListNode leading = front;
			while (leading != null && index > 1) {
				leading = leading.next;
				index--;
			}
		leading.next = new ListNode (e, leading.next);
		size = size + 1;

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
				back = leading.next;
			} 
			size = size - 1;
			return leading.data;
		}
		return null;
	}
	
	@Override
	public E set (int index, Object e) {
		if (e == null) {
			throw new NullPointerException ("element to be added cant be null");
		}
		
		ListNode leading = front;
		@SuppressWarnings("unused")
		ListNode trailing = null;
		
		while (leading != null) {

			if (leading.data.equals(e)) {
				throw new IllegalArgumentException ("cannot have duplicate elements");
			}
			trailing = leading;
			leading = leading.next;
			
			//index--;
		}
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException ("index was out of range");
		}
		
		E q = (E) this.get(index);
		
		if (index == 0) {
			front = new ListNode (e, front);
		} else if (front != null && index > 0) {
			leading = front;
			while (leading != null && index > 1) {
				leading = leading.next;
				index--;
			}
			if (leading != null) {
				leading.next = new ListNode (e, leading.next);
			}
		}
		//E datum = leading.data;
		
		// the leading equal to e, leading.next
		//leading = new ListNode(e, leading);
		//set the trailing equal to itself plus the new item
		//trailing.next = leading;
		
		return q;
		
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	public void setCapacity(int enrollmentCap) {
		if (enrollmentCap < 0 || enrollmentCap < size) {
			throw new IllegalArgumentException ("cannot set the capacity to a size smaller"
					+ " than zero or the size of the current list");
		}
		this.capacity = enrollmentCap;
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
		
		@SuppressWarnings({ "unchecked", "unused" })
		public ListNode (Object data) {
			this.data = (E) data;
			next = null;
		}
		@SuppressWarnings("unchecked")
		public ListNode (Object data, ListNode next) {
			this.data = (E) data;
			this.next = next;
		}
	}

}
