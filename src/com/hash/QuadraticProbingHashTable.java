package com.hash;

/**
 * 如是线性探测，则会导致一次聚集，形成区块
 * 平方探测
 * 这个表保证有一半是空的并且表的大小是素数，我们能狗保证每次都能插入一个新的元素，但他可能导致二次聚集
 * @author zt648
 * @time 五月:15:16:10
 * @project 数据结构与算法分析
 */
public class QuadraticProbingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<T>[] array;
    private int currentSize;

    private class HashEntry<T> {
        public T element;
        public boolean isActive;
        //当元素被删除时或没有时，位false

        public HashEntry(T element) {
            this(element,true);
        }

        public HashEntry(T element, boolean isActive) {
            this.element = element;
            this.isActive = isActive;
        }
    }

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * 构造函数
     */

    public QuadraticProbingHashTable(int n) {
        allocateArray(n);
        makeEmpty();
    }

    public void makeEmpty() {
        currentSize = 0;
        for(int i=0;i<array.length;i++){
            array[i] = null;
        }
    }

    public void insert(T x){
        int currentPos = findPos(x);
        if(isActive(currentPos)){
            return;
        }
        array[currentPos] = new HashEntry<>(x,true);
        if(currentSize > array.length / 2)
            rehash();
    }

    public void remove(T x){
        int currentPos = findPos(x);
        if(isActive(currentPos))
            array[currentPos].isActive = false;
    }

    private void rehash() {
        HashEntry<T>[] oldArray = array;
        allocateArray(nextPrime(2 * oldArray.length));

        currentSize = 0;
        for(int i=0;i<oldArray.length;i++){
            if(oldArray[i] != null && oldArray[i].isActive)
                insert(oldArray[i].element);
        }
    }

    public boolean contains(T x){
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    /**
     * 在半满的表中去探测正确对位置
     * @param x
     * @return
     */
    private int findPos(T x){
        int offset = 1;
        int currentPos = myhash(x);
        while (array[currentPos] !=null && !array[currentPos].element.equals(x)){
            currentPos += offset;
            offset += 2;
            if(currentPos >= array.length){
                currentPos -= array.length;
            }
        }
        return currentPos;
    }
    /**
     * 判断这个位置的值存在并且活动的
     * @param currentPos
     * @return
     */
    private boolean isActive(int currentPos){
        return array[currentPos] != null && array[currentPos].isActive;
    }
    private void allocateArray(int n) {
        array = new HashEntry[nextPrime(n)];
    }
    public  int nextPrime(int n){
        int i;
        int j;
        for(i=n+1;;i++){

            for(j=2;j<i;j++){
                if(i % j ==0){
                    break;
                }
            }
            if(j == i){
                System.out.println(i);
                break;
            }
        }
        return i;
    }
    private int myhash(T x){
        int hashVal = x.hashCode();
        hashVal %= array.length;
        if(hashVal < 0)
            hashVal += array.length;
        return hashVal;
    }
}
