package util;

import java.util.EmptyStackException;
/**
 * creates a new stack using a linked list
 * 
 * @author William
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {
	/** the underlying abstract list **/
	private LinkedAbstractList<E> stack;
	/**
	 * creates a new Linked Stack with a given capacity
	 * @param capacity
	 * 			the capacity of the new stack
	 */
	public LinkedStack(int capacity) {
			LinkedAbstractList<E> thing = new LinkedAbstractList<E>(capacity);
			this.stack = thing;
			
	}
	/**
	 * adds the element to the top of the stack
	 * Throws an IllegalArgumentException if there is no room
	 * @param element
	 * 			the element to be added
	 */
	@Override
	public void push(E element) {

		this.stack.add(0, element);
	}
	/**
	 * removes and returns the element at the top of the stack
	 * if the stack is empty an EmptyStackException is thrown
	 * @return E
	 * 			the element to be deleted is returned
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
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < stack.size()) {
			throw new IllegalArgumentException ("Capacity cannot be set to less than size");
		}
		this.stack.setCapacity(capacity);
	}

}
