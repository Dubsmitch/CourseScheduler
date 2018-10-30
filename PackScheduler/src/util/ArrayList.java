package util;

import java.util.AbstractList;

public class ArrayList<E> extends AbstractList<E> {
    /** initial size of the list **/
    private static final int INIT_SIZE = 10;
    /**    list **/
    private E[] list;
    /** size of list **/
    private int size;
    
    public ArrayList (Class<E> c, int INIT_SIZE) {
        @SuppressWarnings("unchecked")
        E[] list = (E[])new Object[INIT_SIZE];
        this.list = list;
        size = 0;
    }

    @Override
    public void add(int i, E e) {
    	//check if e is a null object
    	if (e == null) {
    		throw new NullPointerException ("can't do this");
    	}
    	//check if e is a duplicate
    	for (int j = i + 1; j < list.length; j++) {
    		if (list[j].equals(e)) {
    			throw new IllegalArgumentException ("Duplicate object cannot be added");
    		}
    	}
    	
    	//check to see if it is in range
    	if (i < 0 || i > size()) {
    		throw new IndexOutOfBoundsException ("the index is out of range"); 
    	}
    	
    	//size is the number of items the list currently holds
    	//length is the capacity (initialized to 10)
    	//if the size is lest than the capacity then add the element
    	if (size < list.length && i != 0) {
    		//create a second list to hold the original list (with the same length)
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[list.length];
    		
    		//go through all the elements and add them to the second list
    		//until the index to add is reached.
    		for (int j = 0; j < i - 1; j++) {
    			list2[j] = list[j];
    		}
    		//then add object to the index to add
    		list2[i] = e;
    		
    		//add one to size because the array will have to be
    		//one larger to accomedate the new addition
        	size = size + 1;
        	
    		//then add the rest of the items
    		for (int k = i + 1; k < size; k++) {
    			list2[k] = list[k];
    		}
    		//then add all elements back to original list
    		for (int j = 0; j < size; j++) {
    			list[j] = list2[j];
    		}
    		
    	
    	} else if (size < list.length && i == 0) {
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
    		
    		
    		
    		
    		size = size + 1;
    	} else {
    		
    		int sizeCurrentArray = size;
    		
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[sizeCurrentArray * 2];
    		
        	//update size
        	size = size + 1;
    	}
    }


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

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
    

}