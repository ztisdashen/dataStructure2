package book_1;

public class Q1_5 {
	public static int getBinaryOne(int number) {
		if(number == 1) {
			return 1;
		}
		else if(number % 2 == 0)
			return getBinaryOne(number / 2);
		else 
			return getBinaryOne(number / 2) + 1;
	}
	public static void main(String[] args) {
		int n = getBinaryOne(7);
		System.out.println(n);
		System.out.println(Integer.toBinaryString(7));
		
	}
}
