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
    }

    @Override
    public void add(int i, E e) {
    	if (e == null) {
    		throw new NullPointerException ("can't do this");
    	}
    	
    	for (int j = i + 1; j < list.length; j++) {
    		if (list[j].equals(e)) {
    			throw new IllegalArgumentException ("Duplicate object cannot be added");
    		}
    	}
    	
    	
    	if (list.length < INIT_SIZE && i != 0) {
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[INIT_SIZE];
    		
    		for (int j = 0; j < i - 1; j++) {
    			list2[j] = list[j];
    		}
    		
    		list2[i] = e;
    		
    		for (int k = i + 1; k < list.length; k++) {
    			list2[k] = list[k];
    		}
    		
    		for (int j = 0; j < list2.length; j++) {
    			list[j] = list2[j];
    		}
    		

    	} else {
    		int sizeCurrentArray = size;
    		@SuppressWarnings("unchecked")
    		E[] list2 = (E[])new Object[sizeCurrentArray * 2];
    		
    	}
    }


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
    

}