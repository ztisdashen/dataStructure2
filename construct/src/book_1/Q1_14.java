package book_1;

public class Q1_14{
	private Comparable[] arr; 
	private int maxSize;
	public Q1_14() {
		arr = new Comparable[50];
	}
	public Q1_14(int size) {
		arr = new Comparable[size];
	}
	public void insert(Comparable value) {
		arr[maxSize] = value;
		maxSize++;
	}
	public void remove(int index) {
		
		if(index>=0 && index< maxSize) {
			for(int a=index;a<maxSize;a++) {
				arr[a] = arr[a+1];
			}
		maxSize--;
		}
		else
			throw new ArrayIndexOutOfBoundsException();
	}
	public void makeEmpty() {
		for(int t=0;t<maxSize;t++)
			arr[t]=null;
		maxSize = 0;
	}
	public boolean isEmpty() {
		if(maxSize==0)
			return true;
		else
			return false;
	}
	@SuppressWarnings("unchecked")
	public Comparable<?> findMax() {
		int max = 0;
		for(int i=0;i<maxSize;i++) {
			if(arr[i].compareTo(arr[max])> 0) {
				max = i;
			}
		}
		return arr[max];
	}
	@SuppressWarnings("unchecked")
	public Comparable<?> findMin() {
		int min = 0;
		for(int i=0;i<maxSize;i++) {
			if(arr[i].compareTo(arr[min])> 0) {
				min = i;
			}
		}
		return arr[min];
	}
	
}
