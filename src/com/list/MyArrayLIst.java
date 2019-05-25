package com.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayLIst<T> implements Iterable<T> {
    private static final int DEAFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayLIst(){
        doClear();
    }
    public void doClear(){
        theSize = 0;
        ensureCapacity(DEAFAULT_CAPACITY);

    }
    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }
    /*
        出去多余的空间
     */
    public void trimSize(){
        ensureCapacity(size());
    }
    public T get(int index){
        if(index<0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[index];
    }

    public T set(int index,T newVal){
        if(index<0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[index];
        theItems[index] = newVal;
        return old;
    }
    private void ensureCapacity(int newCapacity) {
        if(newCapacity < theSize)
            return;
        T[] old = theItems;
        theItems = (T[])new Object[newCapacity];
        for (int i=0;i<size();i++)
            theItems[i] = old[i];
    }

    public boolean add(T val){
        add(size(),val);
        return true;
    }

    public void add(int index,T val){
        if(theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int x = size();x>index;x--){
            theItems[x] = theItems[x-1];
        }
        theItems[index] = val;
        theSize ++;
    }

    public T remove(int index){
        T removeItem = theItems[index];
        for(int i=index;i<size()-1;i++){
            theItems[i] = theItems[i+1];
        }
         theSize--;
        return removeItem;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int current = 0;
        public boolean hasNext(){
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayLIst.this.remove(--current);
        }
    }
}
