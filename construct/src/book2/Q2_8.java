package book2;

public class Q2_8 {
	public static int randomInt(int low,int hieght) {
		return (int)(low + Math.random() * (hieght-low + 1));
	}
	
	public static void randomArr(int n,int low,int hight) {
		int[] arr = new int[n];
		
		for(int i=0;i<n;i++) {
			int count = randomInt(low,hight);
			boolean b = false;
			if(i != 0) {
				while(!b) {
				b = true;
				for(int nu=0;nu<i;nu++) {
					if(arr[nu] == count ) {
						b = false;
						break;
					}
				}
				if(b == false) count = randomInt(low,hight);
				}
			}
			arr[i] = count;
		}
		//for(int i:arr) {
			//System.out.print(i+" ");
		//}
	}
	public static void randomArr2(int n,int low,int hight) {
		int[] arr = new int[n];
		boolean[] bools = new boolean[hight+1];
		for(int i=0;i<hight+1;i++)
			bools[i] = false;
		for(int i=0;i<n;i++) {
			int count = randomInt(low,hight);
			while(bools[count]) {
				count = randomInt(low,hight);
			}
			arr[i] = count; 
			
		}
		//for(int i:arr) {
			//System.out.print(i+" ");}
	}
	public static void main(String[] args) {
		long Start = System.nanoTime();
		randomArr(80000,10,100000);
		long end = System.nanoTime();
		System.out.println((end-Start)/10000);
		Start = System.nanoTime();
		randomArr2(80000,10,100000);
		end = System.nanoTime();
		System.out.println((end-Start)/10000);
		

		
	}
	
}
