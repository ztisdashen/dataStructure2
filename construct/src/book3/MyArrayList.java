package book3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T>{
	private static final int DEFAULT_CAPCITY = 10;
	private int size;
	private T[] theItems;
	
	public MyArrayList() {
		doClear();
	}
	public void clear() {
		doClear();
	}
	public int size() {
		return size;
	}
	
	public  boolean isEmpty() {
		return size() == 0;
	}
	
	public void trimCapcity() {
		ensureCapcity(size());
	}
	
	public  T  get(int index) {
		if(index<0 || index >= size())
			throw new ArrayIndexOutOfBoundsException();
		return theItems[index];
	}
	
	public T set(int index,T newVal) {
		if(index<0 || index >= size())
			throw new ArrayIndexOutOfBoundsException();
		T oldVal = theItems[index];
		theItems[index] = newVal;
		return oldVal;
		
	}
	public boolean add(T x) {
		add(size(),x);
		return true;
	}
	public void add(int index,T x) {
		if(theItems.length == size())
			ensureCapcity(2 * size() + 1);
		for(int i=size();i>index;i--) {
			theItems[i] = theItems[i-1];
			theItems[index] = x;
		}
		size++;
	}
	
	public T remove(int index) {
		T old = theItems[index];
		for(int i=index;i<size()-1;i++) {
			theItems[i] = theItems[i+1];
		}
		return old;
	}
	
	
	private void doClear() {
		size = 0;
		ensureCapcity(DEFAULT_CAPCITY);
	}
	public void ensureCapcity(int newCapcity) {
		if(newCapcity< size) 
			return;
		T[] old = this.theItems;
		this.theItems = (T[]) new Object[newCapcity];
		for(int i=0;i<old.length;i++) {
			theItems[i] = old[i];
		}
	}
	
	
	
	
	
	
	
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {
		private int currency = 0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return currency < size();
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(! hasNext()) {
				throw new NoSuchElementException();
			}
			return theItems[currency++];
		}
		public void remove() {
			MyArrayList.this.remove(--currency);
		}
		
	}
}
