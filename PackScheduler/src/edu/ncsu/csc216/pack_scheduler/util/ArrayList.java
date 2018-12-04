package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
/**
 * Creates a custom ArrayList and provides methods to
 * manipulate and maintain them.
 *  
 * @author William
 * 
 * @param <E>
 * 			generic parameter
 */
public class ArrayList<E> extends AbstractList<E> {
    /** initial size of the list **/
    private static final int INIT_SIZE = 10;
    /**    list **/
    private E[] list;
    /** size of list **/
    private int size;
    
    /**
     * Constructor for Array List
     */
    public ArrayList () {
        @SuppressWarnings("unchecked")
        E[] list = (E[])new Object[INIT_SIZE];
        this.list = list;
        size = 0;
    }

    /**
     * adds an element to the arraylist
     * 
     * @param i
     * 		i is the index where the element will be added
     * @param e
     * 		e is the element to be added
     */
    @Override
    public void add(int i, E e) {
    	//check if e is a null object
    	if (e == null) {
    		throw new NullPointerException ("can't do this");
    	}
    	//check if e is a duplicate
    	if (size > 0) {
    		for (int j = 0 ; j < size; j++) {
    			if (list[j].equals(e)) {
    				throw new IllegalArgumentException ("Duplicate object cannot be added");
    			}
    		}
    	}
    	
    	//check to see if it is in range
    	if (i < 0 || i > size()) {
    		throw new IndexOutOfBoundsException ("the index is out of range"); 
    	}
    	
    	//check to see if the array size is equal to length
    	//if so double the size
    	if (size + 1 == list.length) {
    		int sizeCurrentArray = list.length;
		
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[sizeCurrentArray * 2];
    		
    		for (int j = 0 ; j < size; j++) {
    			list2[j] = list[j];
    		}
    		
    		this.list = list2;
    	}
    	//size is the number of items the list currently holds
    	//length is the capacity (initialized to 10)
    	//if the element is not added at the beginning or the end (end would be size-1 = index)
    	if (i != 0 && i != (size)) {
    		//create a second list to hold the original list (with the same length)
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[list.length];
    		
    		//go through all the elements and add them to the second list
    		//until the index to add is reached.
    		for (int j = 0; j < i; j++) {
    			list2[j] = list[j];
    		}
    		//then add object to the index to add
    		list2[i] = e;
    		
    		//add one to size because the array will have to be
    		//one larger to accomedate the new addition
        	size = size + 1;
        	
    		//then add the rest of the items, adding one to the index for the
        	//location in the new list
    		for (int k = i; k < size; k++) {
    			list2[k + 1] = list[k];
    		}
    		//then add all elements back to original list
    		for (int j = 0; j < size; j++) {
    			list[j] = list2[j];
    		}
    		
    	// if tried to add to the beginning
    	} else if (i == 0 && i != (size)) {
    		//create a second list to hold the original list (with the same length)
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[list.length];
    		
    		//add element to the beginning of the empty list
    		list2[i] = e;
    		
    		//add one to size because the array will have to be
    		//one larger to accomedate the new addition
    		size = size + 1;
    		
    		//add all of the orginal list's elements to the new list
    		//in order +1 of original index
    		for (int j = 1; j < size; j++) {
    			list2[j] = list[j-1];
    		}
    		
    		//then add all elements back to original list
    		for (int j = 0; j < size; j++) {
    			list[j] = list2[j];
    		}
    		
    		// if size is equal to length
    	} else {
    		
    		//create a second list to hold the original list (with the same length)
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[list.length];

    		if (i != 0) {
    			//go through all the elements and add them to the second list
        		//until the index to add is reached.
        		for (int j = 0; j < size; j++) {
        			list2[j] = list[j];
        		}
        		//then add object to the index to add
        		list2[i] = e;
        		
        		//add one to size because the array will have to be
        		//one larger to acommadate the new addition
            	size = size + 1;
        		
        		//then add all elements back to original list
        		for (int j = 0; j < size; j++) {
        			list[j] = list2[j];
        		}
        		
    		} else {
    			//add element to the beginning of the empty list
        		list2[i] = e;
        		
        		//add one to size because the array will have to be
        		//one larger to accomedate the new addition
        		size = size + 1;
        		
        		//add all of the orginal list's elements to the new list
        		//in order +1 of original index
        		for (int j = 1; j < size; j++) {
        			list2[j] = list[j-1];
        		}
        		
        		//then add all elements back to original list
        		for (int j = 0; j < size; j++) {
        			list[j] = list2[j];
        		}
    		}
    	}
    }

    /**
     * removes an element and returns the removed element
     * 
     * @param index
     * 		index where the element will be removed
     * @return E
     * 		returns the element that was removed
     * @throws IndexOutOfBoundsException
     * 		if the index is not within range (index less 0 || index greater or equal size())
     */
    @Override
    public E remove (int index) {
    	if (index < 0 || index >= size()) {
    		throw new IndexOutOfBoundsException ("index is out of range");
    	}
    	
    	//if the index is not zero and less than the size
    	if (index != 0 && index != size) {
    		
    		@SuppressWarnings("unchecked")
			E ee = (E)new Object();
    		ee = list[index];
    		
    		//create new list to hold
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[list.length];
		
    		//before the index of removal the list is the same
    		for (int i = 0; i < index; i++) {
    			list2[i] = list[i];
    		}
		
    		//not sure if size should be one smaller or not here..
    		for (int i = index; i < size; i++) {
    			list2[i] = list[i + 1];
    		}
		
    		//	now switch again
    		for (int i = 0; i < size; i++) {
    			list[i]=list2[i];
    		}
    		list[size - 1] = null;
    		
    		size = size - 1;
    		
    		return ee;
    		
    	//	if the index is zero
    	} else {
        	//create new list to hold
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[list.length];
    			

    		@SuppressWarnings("unchecked")
			E ee = (E)new Object();
    		ee = list[index];
    		
    	//	before the index of removal the list is the same
   			for (int i = 0; i < size; i++) {
   				list2[i] = list[i + 1];
   			}
   			
   		//	now switch again
    		for (int i = 0; i < size; i++) {
    			list[i]=list2[i];
    		}
   			list[size - 1] = null;
   			
   			size = size - 1;
    			
    		return ee;
    	}
    }
    
    
    /**
     * returns the size of the arrayList
     * 
     * @return int
     * 			the size of the arrayList
     */
	@Override
	public int size() {
		int sizeThis = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				sizeThis++;
			}
		}
		
		return sizeThis;
	}
	
	/**
	 * changes an element at a specified index
	 * 
	 * @param index
	 * 			the index that will be changed
	 * @param e
	 * 			the object that will be changed
	 * 
	 * @return e
	 * 			the old object that was changed	
	 */
	@Override
	public E set (int index, E e) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException ("out of range");
		}
		@SuppressWarnings("unchecked")
		E[] list3 = (E[])new Object[list.length];
		list3 = list;
		
		list[index] = e;
		
		return list3[index];
	}
	
	/**
	 * returns the element at the given index
	 * 
	 * @param index
	 * 			the index to get the element from
	 * 
	 * @return E
	 * 			the object at the given index
	 */
	@Override
	public E get(int index) {
		if  (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException ("out of range");
		}
		
		return list[index];
	}
    

}