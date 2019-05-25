package com.arthi;

import com.Heap.Heap;
import com.Heap.HeapIml;


public class Test {
    public static void main(String[] args) {
        int[] lst = GenerateArr.orderIntList(20, 20);
        lst = GenerateArr.randomIntList(0, 100000, 1000000);
        long start = System.currentTimeMillis();
        int[] arrsy = HeapIml.heapSort(lst);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
