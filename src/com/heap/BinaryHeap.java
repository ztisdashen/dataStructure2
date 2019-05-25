package com.heap;

/**
 * @ClassName BinaryHeap
 * @Description 二叉堆，这里是最小堆，与mooc网往相反,基本是从1开始父亲再[i/2]的位置
 * @Author zt648
 * @Date 2019/5/22 15:37
 * @Version 1.0
 */

public class BinaryHeap<T extends Comparable<? super T>> {
    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private T[] array;

    /**
     * @Author zt648
     * @Description 向上交换，直到找到父节点比自己小的为之，并不直接交换，因为每次交换伴随3此赋值，选择先储存，覆盖的方式
     * @Date 15:47 2019/5/22
     * @Param [x]
     * @return void
     * @throw
     **/
    public void insert(T x){
        //因为是从1开始，所以currentSize是size-1
        if(currentSize == array.length -1)
            enlargeArray(array.length * 2 - 1);


        int hole = ++currentSize;
        for(array[0] = x;x.compareTo(array[hole / 2]) < 0;hole /= 2){
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void enlargeArray(int i) {
        T[] oldList = array;
        array =  (T[]) new Object[i];
        for(int n=1;n<oldList.length;n++){
            array[i] = oldList[n];
        }
    }

    public T deleteMin(){
        if(isEmpty()){
            throw new NullPointerException();
        }
        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);

        return minItem;
    }
    /**
     * @Author zt648
     * @Description 向下交换，总与孩子中较小的交换，避免破坏最小堆的性质，如果换了较大的值，导致父亲比孩子大
     * @Date 15:58 2019/5/22
     * @Param [hole]
     * @return void
     * @throw
     **/
    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];

        for(;hole * 2<=currentSize;hole = child){
            child = hole * 2;
            if(child != currentSize && array[child].compareTo(array[child+1]) > 0){
                child++;
            }

            if(array[child].compareTo(tmp) < 0){
                array[hole] = array[child];
            }else break;
        }
        array[hole] = tmp;
    }
    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }
    public BinaryHeap(int capacity){
        array = (T[]) new Object[capacity];
        makeEmpty();
    }
    public BinaryHeap(T[] items){
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize+2) * 11 / 10];

        int i = 1;
        for(T item:items){
            array[i++] = item;
        }
        buildHeap();
    }
    private void makeEmpty(){
        for(int i=1;i<array.length;i++){
            array[i] = null;
        }
    }

    private void buildHeap() {
        //第一个非叶子节点进行下降，否则没意义。
        for(int i=currentSize / 2;i > 0;i--){
            percolateDown(i);
        }
    }

    public T findMin() {
        return array[1];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }
}
