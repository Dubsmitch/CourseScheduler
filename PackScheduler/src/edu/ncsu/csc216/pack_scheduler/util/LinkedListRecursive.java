package edu.ncsu.csc216.pack_scheduler.util;

public class LinkedListRecursive<E> {
	/** the size of the list **/
	private int size;
	/** creates a reference to the front **/
	private ListNode front;
	
	/**
	 * creates a recursive linked list
	 */
	public LinkedListRecursive () {
		this.size = 0;
		this.front = null;
	}
	/**
	 * returns if a list is empty or not
	 * @return boolean
	 * 		if a list is empty or not
	 */
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		
		return false;
	}
	/**
	 * returns the size of the list
	 * @return int
	 * 		the size of the list
	 */
	public int size() {
		return this.size;
	}
	/**
	 * returns whether or not an element is added
	 * @param e
	 * 		the element to be added
	 * @return boolean
	 * 		whether or not the element was added
	 */
	public boolean add(E e) {
		if (front == null) {
			front = new ListNode (e, front);
			this.size = size + 1;

			return true;
		} 
		
		if (front.contains(e)) {
			throw new IllegalArgumentException();
		} else {
			front.add(e);
			this.size = size + 1;
			return true;
		}
	}
	/**
	 * adds an element e to a given index idx
	 * @param idx
	 * 		the index at which to add the element
	 * @param e
	 * 		the element to be added
	 */
	public void add(int idx, E e) {
		if (idx < 0 || idx >= size()) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new ListNode (e, front);
			size = size + 1;
		}
		if (front.contains(e)) {
			throw new IllegalArgumentException();
		} 
		
		if (idx == 0) {
			front = new ListNode(e, front);
			size = size + 1;
		} else {
			front.add(e);
			size = size + 1;
		}
	}
	/**
	 * returns an element at a give index
	 * @param idx
	 * 		the index of the element to be retrieved
	 * @return E
	 * 		the element at a given index
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IllegalArgumentException();
		}
		if (idx == 0) {
			return front.data;
		} else {
			return front.get(idx);
		}
	}
	/**
	 * returns whether an element can be removed; and if it can be 
	 * @param e
	 * 		element to be removed
	 * @return boolean
	 * 		if an element was added or not
	 */
	public boolean remove(E e) {
		if (front == null) {
			return false;
		}
		if (e == null) {
			return false;
		}
		if (front.data.equals(e)) {
			front = front.next;
			size = size - 1;
			return true;
		}
		if (!front.contains(e)) {
			return false;
		}
		
		front.remove(e);
		size = size - 1;
		return true;
	}
	
	/**
	 * removes an element from a given index and returns the value
	 * @param idx
	 * 		the index from which to remove the element
	 * @return boolean
	 * 		whether or not the element was removed
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IllegalArgumentException();
		} 
		if (idx == 0) {
			ListNode thing = front;
			front = front.next;
			size = size - 1;
			return thing.data;
		} else {
			size = size - 1;
			return front.remove(idx);
		}
	}
	
	/**
	 * sets an element at a given index and returns it
	 * @param idx
	 * 		the index at which to set the element
	 * @param e
	 * 		the element that the index will be set to
	 * @return E
	 * 		the element that was at the index
	 */
	public E set(int idx, E e) {
		if(idx < 0 || idx >= size()) {
			throw new IllegalArgumentException();
		} 
		if (idx == 0) {
			ListNode thing = front;
			ListNode newFront = new ListNode (e, front.next);
			front = newFront;
			return thing.data;	
		} else {
			return front.set(idx, e);
		}
	}
	/**
	 * returns whether or not an element is contained within the list
	 * @param e
	 * 		the element to check the list against
	 * @return boolean
	 * 		whether or not the element is in the list
	 */
	public boolean contains(E e) {
		if (front == null) {
			return false;
		} else if (front.data.equals(e)) {
			return true;
		} else {
			return front.contains(e);
		}
	}
	public class ListNode {
		/** the data included in the node **/
		public E data;
		/** the reference to the next element **/
		public ListNode next;
		
		/**
		 * creates a new listnode
		 * @param e
		 * 		the data for the listnode
		 * @param next
		 * 		reference to the next list not
		 */
		public ListNode (E e, ListNode next) {
			this.next = next;
			this.data = e;
		}
		/**
		 * adds an element to the end of a non-empty list
		 * @param e
		 * 		the element to be added
		 */
		public void add (E e) {
			if (next == null) {
				next = new ListNode (e, null);

			} else {
				next.add(e);
			}
		}
		/**
		 * adds an alement at a given index
		 * @param index
		 * 		the index at which to add the element
		 * @param e
		 * 		the element to be added
		 */
		public void add (int index, E e) {
			if (index == 0) {
				next = new ListNode(e, next);
			} else {
				next.add(index - 1, e);
			}
		}
		/**
		 * returns an element at a given index
		 * @param idx
		 * 		the index from which to return the element
		 * @return E
		 * 		the element at the given index
		 */
		public E get(int idx) {
			if (idx == 0) {
				return data;
			} else {
				return next.get(idx - 1);
			}
		}
		/**
		 * removes an element at a particular index, returns that element
		 * @param idx
		 * 		the index from which to remove the element
		 * @return e
		 * 		the element at the given index
		 */
		public E remove(int idx) {
			if (idx == 0) {
				E stuff = next.data;
				next = next.next;
				return stuff;
			} else {
				return next.remove(idx - 1);
			}
		}
		/**
		 * removes a particular element that is given
		 * @param e
		 * 		the element to be removed
		 * @return boolean
		 * 		whether or not the element is removed
		 */
		public boolean remove(E e) {
			if (next != null) {
				if (next.data.equals(e)) {
					next = next.next;
				} else {
					next.remove(e);
				}
			} 
			
			return true;
		}
		/**
		 * sets an element at a particular index and returns the
		 * original element
		 * @param idx
		 * 		the index of the element to be replace
		 * @param e
		 * 		the element to replace the element at the specified index with
		 * @return E
		 * 		the original element
		 */
		public E set(int idx, E e) {
			if (idx == 0) {
				E stuff = next.data;
				ListNode replace = new ListNode(e, next.next);
				next = replace;
				return stuff;
			} else {
				return next.set(idx - 1, e);
			}		
		}
		/**
		 * returns whether or not the list contains a data element
		 * @param e
		 * 		the element for the list to be checked against
		 * @return boolean
		 * 		whether or not an element is contained within a list
		 */
		public boolean contains(E e) {
			if (next == null) {
				return false;
			} else if (next.data == e) {
				return true;
			} else {
				return next.contains(e);
			}
		}
	}
}
