package com.Heap;

public class HeapIml {
    private static Heap heap = new Heap();
    public static int[] heapSort(int[] array){
        int[] list = new int[array.length];
        for(int i=0;i<array.length;i++)
            heap.insert(array[i]);
        int i=0;
        while(!heap.isEmpty())
            list[i++] = heap.extractMax();
        return list;
    }
}
