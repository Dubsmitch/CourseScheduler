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
}