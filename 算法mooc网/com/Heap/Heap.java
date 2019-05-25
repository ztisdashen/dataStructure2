package com.Heap;

/**
 * 为了便于parent和sun，lst的索引是从1开始的
 * /**
 *  * 这是一个堆满足父节点的元素一定大于子元素
 *  * sun / 2 = parent(如果sun时奇数则会取整)
 *  * @param
 *
 *  */



public class Heap {
    private int count = 0;
    private int DEFAULT_CAPACITY = 50;
    private int [] lst;
    public Heap(){
        lst = new int[DEFAULT_CAPACITY];
    }
    public Heap(int capacity){
        lst = new int[capacity];
    }
    public boolean isEmpty(){
        return count == 0;
    }
    public int Size(){
        return count;
    }
    public void ensureCapacity(int newCapacity){
        if(Size() > newCapacity)
            return;
        int[] old = lst;
        lst = new int[newCapacity];
        for(int i=1;i < count+1;i++)
            lst[i] = old[i];

    }
    public void insert(int value){
        if(Size() +2 > lst.length)
            ensureCapacity(2*Size()+1);
        lst[Size()+1] = value;
        shiftUp(Size()+1);
        count ++;
    }

    public int extractMax(){
        if(count <=0) {
            throw new NullPointerException();
        }
        int max = lst[1];
        lst[1] = lst[Size()];
        shiftDown(1);
        lst[Size()] = 0;
        count--;
        return max;
    }
    public int getMax(){
        if(count <=0) {
            throw new NullPointerException();
        }
        return lst[1];
    }
    /**、
     * 将顶部元素与最后的元素交换。
     * @return
     */
    private void shiftUp(int k){
        while( k > 1 && lst[k/2] < lst[k] ){
            int item = lst[k];
            lst[k] = lst[k/2];
            lst[k/2] = item;
            k = k/2;
        }
    }
    /**
     * 将当前索引的值向下移，与子元素2k或2k+1交换
     * @param k
     */
    private void shiftDown(int k){

        while(2 * k<=count) {
            int j = 2 * k;
            if (j + 1 <= count && lst[j + 1] > lst[j])
                j++;
            int item = lst[j];
            lst[j] = lst[k];
            lst[k] = item;
            k = j;
        }
    }
}
