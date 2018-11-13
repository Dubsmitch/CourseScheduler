package edu.ncsu.csc216.pack_scheduler.util;
/**
 * this interface provides the methods for a Stack
 * @author William
 *
 * @param <E>
 * 		generic
 */
public interface Stack <E>{
	/**
	 * adds the element to the top of the stack
	 * Throws an IllegalArgumentException if there is no room
	 * @param element
	 * 			the element to be added
	 */
	void push (E element);
	/**
	 * removes and returns the element at the top of the stack
	 * if the stack is empty an EmptyStackException is thrown
	 * @return E
	 * 			the element to be deleted is returned
	 */
	E pop();
	/**
	 * returns true if the stack is empty, else false
	 * @return boolean
	 * 			if the stack is empty
	 */
	boolean isEmpty();
	/**
	 * returns the size of the stack
	 * @return int
	 * 			size of the stack
	 */
	int size();
	/**
	 * sets the capacity of the stack
	 * throws an IllegalArgumentException if the parameter
	 * is negative or less than the size of the stack
	 * @param Capacity
	 * 			the size of the stack
	 */
	void setCapacity(int Capacity);
}
