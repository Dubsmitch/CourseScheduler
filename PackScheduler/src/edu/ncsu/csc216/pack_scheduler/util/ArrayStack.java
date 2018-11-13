package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;
/**
 * Creates and gives functionality to a stack using
 * an underlying array list
 * 
 * @author William
 *
 * @param <E>
 * 		generic type
 */
public class ArrayStack<E> implements Stack<E> {
	/** the underlying array list **/
	private ArrayList<E> stack;
	/** the capacity of the stack **/
	private int capacity;
	/**
	 * creates a new array Stack with a given capacity
	 * @param capacity
	 * 			the capacity of the stack
	 */
	public ArrayStack(int capacity) {
			ArrayList<E> thing = new ArrayList<E>();
			this.capacity = capacity;
			this.stack = thing;
			
	}
	/**
	 * adds the element to the top of the stack
	 * Throws an IllegalArgumentException if there is no room
	 * @param element
	 * 			the element to be added
	 * @throws IllegalArgumentException
	 * 			if the stack is full
	 */
	@Override
	public void push(E element) {
		if (stack.size() == capacity) {
			throw new IllegalArgumentException ("Stack is full");
		}
		this.stack.add(0, element);
	}
	/**
	 * removes and returns the element at the top of the stack
	 * if the stack is empty an EmptyStackException is thrown
	 * @return E
	 * 			the element to be deleted is returned
	 * @throws EmptyStackException
	 * 			if the stack is empty
	 */
	@Override
	public E pop() {
		if (stack.size() == 0) {
			throw new EmptyStackException ();
		}
		//add in the thrown exception
		E returnedObj = (E) (this.stack.get(0));
		stack.remove(0);
		
		return returnedObj;
	}
	/**
	 * returns true if the stack is empty, else false
	 * @return boolean
	 * 			if the stack is empty
	 * 
	 */
	@Override
	public boolean isEmpty() {
		if (stack.size() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * returns the size of the stack
	 * @return int
	 * 			size of the stack
	 */
	@Override
	public int size() {
		return this.stack.size();
	}
	/**
	 * sets the capacity of the stack
	 * throws an IllegalArgumentException if the parameter
	 * is negative or less than the size of the stack
	 * @param Capacity
	 * 			the size of the stack
	 * @throws IllegalArgumentException
	 * 			if the capacity is less than the size of
	 * 			the current stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < stack.size()) {
			throw new IllegalArgumentException ("Capacity cannot be set to less than size");
		}
		this.capacity = capacity;
	}

}
