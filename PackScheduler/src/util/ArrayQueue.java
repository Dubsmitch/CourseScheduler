package util;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
	/** underlying Linked Abstract List **/
	private ArrayList<E> queue;
	/** capacity of the queue **/
	private int capacity;
	
	/**
	 * constructor for the queue with a give capacity
	 * @param cap
	 * 			capacity of the new queue
	 */
	public ArrayQueue(int cap) {
		ArrayList<E> tempQueue = new ArrayList<E>();
		this.queue = tempQueue;
		this.capacity = cap;
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
		if ( capacity == queue.size()) {
			throw new IllegalArgumentException ("the capacity has already been reached");
		}
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
		this.capacity = capacity;
	}
}