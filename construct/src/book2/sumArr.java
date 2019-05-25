package book2;

public class sumArr {
	public static int maxSubSum(int[] a) {
		int maxSum = 0, thisSum = 0;
		for(int j=0;j<a.length;j++) {
			thisSum += a[j];
			if(thisSum > maxSum)
				maxSum = thisSum;
			else
				thisSum = 0;
		}
		return maxSum;
	}
}
