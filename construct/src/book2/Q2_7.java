package book2;

public class Q2_7 {
	public static void sum1(long n) {
		long sum = 0;
		for(long i=0;i<n;i++)
			sum ++;
		
	}
	public static void sum2(long n) {
		long sum = 0;
		for(long i=0;i<n;i++) {
			for(long j=0;j<n;j++)
				sum ++;
			}
		
	}
	public static void sum3(long n) {
		long sum = 0;
		for(long i=0;i<n;i++) {
			for(long j=0;j<n*n;j++)
				sum ++;
			}
		
	}
	public static void sum4(long n) {
		long sum = 0;
		for(long i=0;i<n;i++) {
			for(long j=0;j<i;j++)
				sum ++;
		}
		
	}	public static void sum5(long n) {
		long sum = 0;
		for(long i=0;i<n;i++) {
			for(int j=0;j<i*i;j++) {
				for(int k=0;k<j;k++) {
					sum++;
				}
			}
		}
			
		
	}	public static void sum6(long n) {
		long sum = 0;
		for(long i=1;i<n;i++) {
			for(int j=1;j<i;j++) {
				if(j % i ==0) {
					for( int k=0;k<j;k++) {
						sum++;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		long start = System.nanoTime();
		sum1(2000);
		long end = System.nanoTime();
		System.out.println((end-start)*1000000.0+"ºÁÃë");
		start = System.nanoTime();
		sum2(2000);
		end = System.nanoTime();
		System.out.println((end-start)*1000000.0+"ºÁÃë");
		start = System.nanoTime();
		sum3(2000);
		end = System.nanoTime();
		System.out.println((end-start)*1000000.0+"ºÁÃë");
		start = System.nanoTime();
		sum4(2000);
		end = System.nanoTime();
		System.out.println((end-start)*1000000.0+"ºÁÃë");
		sum5(2000);
		end = System.nanoTime();
		System.out.println((end-start)*1000000.0+"ºÁÃë");
		sum6(2000);
		end = System.nanoTime();
		System.out.println((end-start)*1000000.0+"ºÁÃë");
		
	}
}
