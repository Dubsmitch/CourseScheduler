package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * that class creates a linked list and contains within it
 * two other inner classes that provide the functionality for the linked list
 * 
 * this linked list is doubly linked and contains a reference to the front
 * and the back of the list
 * 
 * @author William
 *
 * @param <E>
 * 		the generic data type
 */
public class LinkedList <E> extends AbstractSequentialList <E>{
	/**
	 * overrides the method for adding an element at a given index
	 * also checks the element to ensure it is not a duplicate
	 * 
	 * @param idx
	 * 		the index to add the element to
	 * @param element
	 * 		the element to be added at the given index
	 */
	@Override
	public void add(int idx, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException ();
		}

		super.add(idx, element);
		//LinkedListIterator thing = (LinkedList<E>.LinkedListIterator) listIterator(idx);
		//thing.add(datum);
		
	
	}

	/**
	 * Overrides the method to set an element at a given index
	 * @param index
	 * 		the index at which to set the element
	 * @param element
	 * 		the element to change the element at the given index to
	 */
	@Override
	public E set(int index, E element) {

		if (this.contains(element)) {
			throw new IllegalArgumentException ();
		}
		
		return super.set(index, element);
	}

	/** contains the size of the linked list **/
	private int size;
	/** contains reference to the back element **/
	private ListNode back;
	/** contains reference to the front element **/
	private ListNode front;
	
	
	/**
	 * creates a new Linked List
	 */
	public LinkedList() {
		ListNode frontPointer = new ListNode(null); 
		ListNode backPointer = new ListNode(null);
		
		this.front = frontPointer;
		this.back = backPointer;
		front.next = back;
		back.prev = front;
		
		this.size = 0;
	}
	/**
	 * iterates through the list? (Maybe?)
	 * 
	 * @param int
	 * 		the pointer (where the iterator should be pointing to)
	 * 
	 * @return ListIterator
	 * 		an iterator that moves through a doubly linked list
	 */
	@Override
	public ListIterator<E> listIterator(int idx) {
		//The other abstract method in AbstractSequentialList is listIterator().
		//The method returns a ListIterator<E> 
		//that is positioned such that a call to ListIterator.next() will 
		//return the element at given index (as per the AbstractSequentialList API.
		//All LinkedList.listIterator() does is construct a LinkedListIterator object.
		//When constructing the LinkedListIterator object, you don’t need the generic type on the constructor
		//because the generic type is handled by extending ListIterator<E>!
		if (idx < 0 || idx > this.size()) {
			throw new IndexOutOfBoundsException("Cannot create iterator");
		}
		
		
		return new LinkedListIterator(idx);
	}
	
	/**
	 * returns the size of the linked list
	 * 
	 * @return int
	 * 		the size of the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * this inner class constructs the individual nodes
	 * 
	 * each node holds data and a reference to the node before and after it.
	 * 
	 * the beginning of the list will hold null, null, reference; the end will hold
	 * 
	 * reference, null, null
	 * 
	 * @author William
	 *
	 */
	private class ListNode {
		/** the data of the node **/
		public E data;
		/** the reference to the next **/
		public ListNode next;
		/** reference to the previous **/
		public ListNode prev;
		
		/**
		 * creates a node for the front of the list
		 * @param data
		 * 		the data to be stored
		 */
		public ListNode (E data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}
		/**
		 * creates a node for the list
		 * not at the front
		 * @param data
		 * 		the data to be stored
		 * @param next
		 * 		the reference to the next item in the list
		 * @param prev
		 * 		the reference to the previous item in the list
		 */
		public ListNode (E data, ListNode next, ListNode prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * creates an iterator for a linkedlist that can
	 * traverse a list  bringing the complexity from O(n^2) to
	 * O(n). provides basic functionality (adding, removing, setting)
	 * 
	 * 
	 * @author William
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** represents the ListNode that would be returned on call "previous()" **/
		private ListNode previous;
		/** represents the ListNode that would be returned on call "next()" **/
		private ListNode next;
		/** the index that would be returned on a call to "previousIndex()" **/
		private int previousIndex;
		/** the index that would be returned on a call to "nextIndex()" **/
		private int nextIndex;
		/** 
		 * represents the ListNode that was returned on the last call to either "previous()"
		 * or "next()" or null if a call to "previous()" or "next()" was not the last
		 * call on the ListIterator
		 */
		private ListNode lastRetrieved;
		
		/**
		 * creates a new iterator that points between a given index and 
		 * the index before it
		 * @param index
		 * 		the index beyond which to point
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()){
				throw new IndexOutOfBoundsException();
			}
			
			previous = front;
			next = previous.next;
			previousIndex = index - 1;
			nextIndex = index;
			
			//if the index is not zero
			if (index != 0) {
				//iterate until before the index is met
				for (int i = 0; i < index; i++) {
					
					previous = next;
					next = next.next;
				}
			}
			//when this ends the next element will be the one we should be
			//just before (circular logic, but I get it)
			
			lastRetrieved = null;
		}
		
		/**
		 * adds an element to the list
		 * 
		 * @param datum
		 * 		the element to be added
		 */
		@Override
		public void add(E datum) {
			if (datum == null) {
				throw new NullPointerException();
			}
			
			//the new node will go inbetween the previous and next nodes.
			//previous should be set to the new listnode and next should call the
			//original next
			if (size() == 0) {
				ListNode newNode = new ListNode(datum, next, previous);
				previous.next = newNode;
				next.prev = newNode;
				front = previous;
				back = next;
				
				previous = newNode;
				
				nextIndex = nextIndex + 1;
				previousIndex = previousIndex + 1;
				
			}
			//adding to the back
			else if (size() > 0 || next.data == null) {
				ListNode newNode = new ListNode (datum, next, previous);
				previous.next = newNode;
				next.prev = newNode;
				back = next;
				
				previous = newNode;
				
				nextIndex = nextIndex + 1;
				previousIndex = previousIndex + 1;
			}
			//adding to front of list
			else if (size() > 0 || previous.data == null) {
				ListNode newNode = new ListNode (datum, next, previous);
				previous.next = newNode;
				next.prev = newNode;
				front = previous;
				
				previous = newNode;
				
				nextIndex = nextIndex + 1;
				previousIndex = previousIndex + 1;
			}
			//[] 1 [] - [] 3 [] - [] 4 []
				//add ^
				// [] 2 []
			//new listnode [[] 1 []] 2 [[] 3 []]
			//previous = 	[] 1 [[] 3 []]
			//next = 		[[] 1 []] 3 []
			
			//set previous.next to [] 1 [[] 2 []]
					//to the new listnode
			
			//set next.prev to [[] 2 []] 3 []
					//to the new listnode
			
			//set previous = new listnode
			else {
			
			ListNode newNode = new ListNode(datum, next, previous);
			
			//ListNode oldPrevious = previous;
			
			previous.next = newNode;
			
			previous = newNode;
			
			next.prev = newNode;
			nextIndex = nextIndex + 1;
			previousIndex = previousIndex + 1;
			}
			//if this is the first element then previous is ____ and next is ____.
			lastRetrieved = null;
			size = size + 1;
		}
		/**
		 * returns true if there is a next element
		 * else false
		 * @return boolean
		 * 		returns true if there is another element 
		 * 		else false
		 */
		@Override
		public boolean hasNext() {
			if (next.data != null) {
				return true;
			}
			return false;
		}
		/**
		 * returns true if there is a node previous to the current one
		 * else false
		 * 
		 * @boolean 
		 * 		true if there is a previous element
		 * 		false otherwise
		 */
		@Override
		public boolean hasPrevious() {
				if (previous.data != null) {
					return true;
				}
			return false;
		}

		/**
		 * returns the next node
		 * 
		 * @return E
		 * 		next Node
		 */
		@Override
		public E next() {
			//set the returned data
			E thing = next.data;

			//throw exception if it's null (end of list)
			if (thing == null) {
				throw new NoSuchElementException();
			}
			//System.out.println("yo");

			//last retrieved set to the next null
			lastRetrieved = next;
			
			//set new stuff
			previous = next;
			next = next.next;
			previousIndex = previousIndex + 1;
			nextIndex = nextIndex + 1;
			
			//return the data
			return  thing;
		}
		/**
		 * provides the value of the next index
		 * 
		 * @return int
		 * 		the index
		 */
		@Override
		public int nextIndex() {
			return this.nextIndex;
		}
		/**
		 * returns the item in the previous location
		 * 
		 * @return E
		 * 		the item in the previous location
		 */
		@Override
		public E previous() {
			//set the data
			E thing = previous.data;
			
			
			//throw an error if it doesn't exist
			if (thing == null) {
				throw new NoSuchElementException();
			}
			
			//last retrieved set to previous
			lastRetrieved = previous;
			
			//reset the state (move backward)
			next = previous;
			previous = previous.prev;
			previousIndex = previousIndex - 1;
			nextIndex = nextIndex - 1;
			
			//return the data
			return thing;
		}
		/**
		 * returns the value of the previous index
		 * 
		 * @return int
		 * 		the previous index
		 */
		@Override
		public int previousIndex() {
			return this.previousIndex;
		}
		/**
		 * removes a node (list element)
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			if (this.lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			//cases:
			//removing from the head
			//removing from the back
			//removing from the middle
			
			//removing from the back
			if (next.data == null) {
				previous = previous.prev;
				previous.next = next;
				next.prev = previous;
				lastRetrieved = null;
				back = next;
			//removing from the front
			} else if (previous.data == null) {
				//System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data);

				next = next.next;
				
				previous.next = next;
				next.prev = previous;
				front = previous;
			}
			//if lastRetrieved == next then we need to set previous == previous.prev
			//I think this is broken still
			else if (lastRetrieved.data.equals(next.data)) {
				System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data);

				next = next.next;
				

				previous.next = next;
				next.prev=previous;
			//if last retrieved was 	
			} else if (lastRetrieved == previous) {
				//System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data + " " + datum);

				previous = previous.prev;
				//System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data + " " + datum);

				next.prev = previous;
				previous.next = next;
				
			}
			size = size - 1;
			
		}
		/**
		 * sets a node to a new value
		 * 
		 * @param datum
		 * 		the data to be set to
		 */
		@Override
		public void set(E datum) {
			if (this.lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (datum == null) {
				throw new NullPointerException();
			}
			
			//different possibilities:
			//setting end of list (set back)
			//setting beginning of list (setting front)
			//setting body
			//what we know is what the last retrieved node was
			//we don't know what the call was. it could be next or previous node too.
			
			// if we are at the end of the list
			if (next.data == null) {
				previous = previous.prev;
				lastRetrieved.data = datum;

				next.prev = lastRetrieved;

				previous.next = lastRetrieved;
				next = next.prev;
				back = next;
				
			} else if (previous.data == null) {
				//System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data);

				next = next.next;
				lastRetrieved.data = datum;
				previous.next = lastRetrieved;
				next.prev=lastRetrieved;
				next = next.prev;
				front = previous;
			}
			//if lastRetrieved == next then we need to set previous == previous.prev
			//I think this is broken still
			else if (lastRetrieved.data.equals(next.data)) {
				System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data);

				next = next.next;
				lastRetrieved.data = datum;

				previous.next = lastRetrieved;
				next.prev=lastRetrieved;
				next = next.prev;
			//if last retrieved was 	
			} else if (lastRetrieved == previous) {
				//System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data + " " + datum);

				previous = previous.prev;
				//System.out.println(previous.data + " " + next.data + " " + lastRetrieved.data + " " + datum);

				lastRetrieved.data = datum;
				next.prev = lastRetrieved;
				previous.next = lastRetrieved;
				previous = previous.next;
			}
		}
		
	}

}
