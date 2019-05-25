package myarray;

public class MyArray {
	private long[] array;
	private int element;
	public MyArray() {
		array = new long[50];
	}
	public MyArray(int maxSize) {
		array = new long[maxSize];
	}
	/**
	 * 增加数据
	 */
	public void insert(long value) {
		array[element] = value;
		element++;
	}
	
	public void display() {
		System.out.print("[");
		for(int i=0;i<element;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println("]");
	}
	
	public int search(long value) {
		int i;
		for(i=0;i<element;i++) {
			if(array[i] == value)
				break;
		}
		if(i==element)
			return -1;
		else
			return i;
	}
	
	public long getValue(int index) {
		if(index < 0 || index >= element)
			throw new ArrayIndexOutOfBoundsException();
		else
			return array[index];
	}
	
	public void delete(int index) {
		if(index < 0 || index >= element) {
			throw new ArrayIndexOutOfBoundsException();
		}else {
			for(int i=index;i<element;i++) {
				array[i] = array[i+1];
			}
			
		}
		element --;
	}
	public void change(int index,int value) {
		if(index < 0 || index >= element) {
			throw new ArrayIndexOutOfBoundsException();
		}else {
			array[index] = value;
		}
	}
}
