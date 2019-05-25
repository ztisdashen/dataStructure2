package book_1;

public class Q1_13 {
	private Object[] os;
	private int length;
	public Q1_13() {
		os = new Object[50];
	}
	public Q1_13(int maxSize) {
		
		os = new Object[maxSize];
	}
	public boolean isEmpty() {
		if(length == 0)
			return true;
		else
			return false;
	}
	public void insert(Object o) {
		os[length] = o;
		length ++ ;
	}
	public void makeEmpty() {
		
		for(int i=0;i<length;i++)
			os[i] = null;
		length = 0;
	}
	public void remove(int index) {
		os[index] = null;
	}
	public boolean isPresent(Object o) {
		boolean bool = false;
		for(Object oo: os) {
			if(oo.equals(o)) {;
			bool = true;
			break;
			}
			
		}
		return bool;
	}
}
