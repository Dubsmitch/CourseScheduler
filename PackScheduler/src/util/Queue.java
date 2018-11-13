package util;
/**
 * contains all methods for a queue
 * @author William
 *
 */
public interface Queue <E> {
	/**
	 * adds element to the back of the queue
	 * 
	 * @param element
	 * 		the element to be added to the back of the queue
	 */
	void enqueue(E element);
	/**
	 * removes an element from the queue and returns it
	 * @return element
	 * 		the element that is removed
	 */
	E dequeue();
	/**
	 * returns whether or not a queue is empty (true if so
	 * else false)
	 * 
	 * @return boolean
	 * 			if a queue is empty or not
	 */
	boolean isEmpty();
	/**
	 * returns the size of the queue
	 * @return int
	 * 			the size of the queue
	 */
	int size();
	/**
	 * sets the capacity of the queue
	 * @param capacity
	 * 			the capacity of the queue
	 */
	void setCapacity(int capacity);
	
}
