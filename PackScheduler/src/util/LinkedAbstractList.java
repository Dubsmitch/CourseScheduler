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
public class LinkedAbstractList<E> extends AbstractList<Object> {
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
			idx++;
		}
		System.out.println(datum);
		return datum;
	}
	
	@Override
	public void add (int index, Object e) {
		if (e.equals(null)) {
			throw new NullPointerException ("cannot add a null object to the list");
		}
		
		if (capacity == size) {
			throw new IllegalArgumentException ("The list is full");
		}
		size = size + 1;
		if (index == 0) {
			front = new ListNode (e, front);
		} else if (front != null && index > 0) {
			ListNode leading = front;
			while (leading != null && index > 1) {
				leading = leading.next;
				index--;
			}
			if (leading != null) {
				leading.next = new ListNode (e, leading.next);
			}
		//if empty then add a new listnode with a null tail link
//		if (size == 0) {
//			front = new ListNode(e);
//			size = size + 1;
//		//if the size isn't 0 and the index is 1
		//not equal to the size (adding to second index)
		//} else if (size > 0 && index == 1) {
			//make first node reference the second node
		//	ListNode newFront = front;
		//	front = new ListNode(e, front);
		//	size = size + 1;
		//add to middle
//		} else if (size >= 1) {
//			ListNode leading = front;
//			ListNode trailing = null;
//			int idx = 0;
//			//theoretical: size 8, index to insert: 5
//			//need to change the reference of index 4 to the new obj
//			//need to insert at # 5 and make a reference to old 5
//			while (leading != null && idx < index ) {
//				trailing = leading;
//				leading = leading.next;
//			}
//			//search for the insertion position. Stop when it
//			//finds the index, then sets the first node equal to old
			//sets the trailing equal to #4
			
//			trailing.next = new ListNode(e, leading);
//			size = size + 1;
			
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
		System.out.println("This executed (outside of comp statement");
		
		ListNode leading = front;
		ListNode trailing = null;
		
		while (leading != null) {
			System.out.println("This executed (inside of comp statement");

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
	/**
	 * inner class of LinkedAbstractList 
	 * 
	 * @author William
	 *
	 */
	private class ListNode {
		
		private E data;
		
		private ListNode next;
		
		@SuppressWarnings("unchecked")
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
