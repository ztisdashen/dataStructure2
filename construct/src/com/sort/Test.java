package com.sort;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list =  GenerateArray.randomIntList(0, 10000, 300);
		int[] list4 =  GenerateArray.randomIntList(0, 10000, 5);
		int[] list3 =  GenerateArray.randomIntList(0, 10000, 300);
		
		
		int[] list2 = GenerateArray.orderIntList(300, 20);
		
		/**
		long start = System.currentTimeMillis();
		Sort.insertSort(list, 0, list.length);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		start = System.currentTimeMillis();
		Sort.mergeSort(list, 0, list.length-1);
		end = System.currentTimeMillis();
		System.out.println(end - start);
		start = System.currentTimeMillis();
		Sort.mergeSortDU(list, 0, list.length-1);
		end = System.currentTimeMillis();
		System.out.println(end - start);
		start = System.currentTimeMillis();
		Sort.selectSort(list, 0, list.length);
		end = System.currentTimeMillis();
		System.out.println(end - start);
	
	*/
//		Sort.insertSort(list, 0, list.length);
//		for(int i:list)
//			System.out.print(i+" ");
		
		
		for(int i:list4)
			System.out.print(i+" ");
		System.out.println();
		Sort.quickSort(list4, 0, list4.length-1);
		for(int i:list4)
			System.out.print(i+" ");
		
	}

}
