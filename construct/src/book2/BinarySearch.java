package book2;

public class BinarySearch {
	public static <T extends Comparable<? super T>> 
	int binarySearch(T[] a,T value) {
		final int NOT_FOUND = -1;
		int min = 0; int hight = a.length - 1;
		int mid = (min + hight)/2;
		while( min <= hight) {
			
			if(value.compareTo(a[mid])> 0) {
				min = mid + 1;
				mid = (min + hight) / 2;
			}
			else if(value.compareTo(a[mid])< 0) {
				hight = mid - 1;
				mid = (min + hight) / 2;
			}
			else if(value.compareTo(a[mid]) == 0)
				return mid;
				break;
			
		}
		return NOT_FOUND;
	}
}
