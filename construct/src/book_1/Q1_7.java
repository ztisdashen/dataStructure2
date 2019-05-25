package book_1;

public class Q1_7 {
	public static int get2(int index) {
		if(index == 0 ) 
			return 1;
		if(index == 1)
			return 2;
		else
			return get2(index / 2) * get2(index - index / 2) % 5;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(get2(3));
	}

}
