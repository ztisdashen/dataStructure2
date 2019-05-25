package book_1;

import java.util.Comparator;
class CaseInsensitiveCompare implements Comparator<String>{
	
	@Override
	public int compare(String s1,String s2) {
		return s1.compareToIgnoreCase(s2);
	}
}
public class TestProgram {
	public static <T> T findMax(T[] arr,Comparator<? super T> cmp) {
		int maxIndex = 0;
		for(int i=0;i<arr.length;i++) {
			if(cmp.compare(arr[i], arr[maxIndex]) > 0) {
				maxIndex = i ;
			}
		}
		return arr[maxIndex];
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] ss = {"ZEBRA","alligator","crocodie"};
		System.out.println(findMax(ss,new CaseInsensitiveCompare()));
	}

}
