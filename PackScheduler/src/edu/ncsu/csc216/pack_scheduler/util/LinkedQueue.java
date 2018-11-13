package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
	/** underlying Linked Abstract List **/
	private LinkedAbstractList<E> queue;
	
	public LinkedQueue(int cap) {
		LinkedAbstractList<E> tempQueue = new LinkedAbstractList<E>(cap);
		this.queue = tempQueue;
	}
	/**
	 * adds element to the back of the queue
	 * throws and IllegalArgumentException if the capacity
	 * has been reached
	 * 
	 * @param element
	 * 		the element to be added to the back of the queue
	 * @throws IllegalArgumentException
	 * 		if the capacity has already been reached
	 */
	public void enqueue(E element) {
		//if the queue is size 5 the last element will be at 4, so the element should be added at 5
		queue.add(queue.size(), element);
	}
	/**
	 * removes an element from the queue and returns it
	 * throws a NoSuchElementException if the queue is empty
	 * @return element
	 * 		the element that is removed
	 */
	public E dequeue() {
		if (queue.size() == 0) {
			throw new NoSuchElementException ("The queue is empty");
		}
		return queue.remove(0);
	}
	/**
	 * returns whether or not a queue is empty (true if so
	 * else false)
	 * 
	 * @return boolean
	 * 			if a queue is empty or not
	 */
	public boolean isEmpty() {
		if (queue.size() == 0) {
			return true;
		}
		return false;
	}
	/**
	 * returns the size of the queue
	 * @return int
	 * 			the size of the queue
	 */
	public int size() {
		return queue.size();
	}
	/**
	 * sets the capacity of the queue
	 * throws an IllegalArgumentException if the capacity
	 * to be set to is less than the size of the current queue
	 * 
	 * @param capacity
	 * 			the capacity of the queue
	 */
	public void setCapacity(int capacity) {
		if (capacity < queue.size()) {
			throw new IllegalArgumentException ("The capacity cannot be less "
					+ "than the size.");
		}
		queue.setCapacity(capacity);
	}

}
