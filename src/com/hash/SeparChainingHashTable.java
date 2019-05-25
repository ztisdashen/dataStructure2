package com.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zt648
 * @time 五月:15:15:12
 * @project 数据结构与算法分析
 *
 * 这是分离链接法，利用链表来储存分配到了相同值的key
 * 缺点：使用链表，由于给新的单元分配地址，所以比较慢
 */
public class SeparChainingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE=101;
    private List<T>[] theLists;
    private int currentSize;

    /**
     *构造函数
     */
    public SeparChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparChainingHashTable(int tableSize) {
        theLists = new LinkedList[nextPrime(tableSize)];
        for(int i=0;i<theLists.length;i++){
            theLists[i] = new LinkedList<>();
        }
    }
    public void makeEmpty(){
        for(int i=0;i<theLists.length;i++){
            theLists[i].clear();
        }
        currentSize = 0;
    }

    /**
     * 在哈希表中找这个item
     * @param x
     * @return
     */
    public boolean contains(T x){
        List<T> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }


    public void insert(T x){
        List<T> whichList = theLists[myhash(x)];
        if(!whichList.contains(x)){
            whichList.add(x);
            if(++currentSize > theLists.length)
                rehash();
        }

    }

    public void remove(T x){
        List<T> whichList = theLists[myhash(x)];
        if(whichList.contains(x)){
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * 再散列，即当表快慢时，再此扩充散列，并重新插入
     */
    private void rehash() {
        List<T> []olsLists = theLists;
        theLists = new List[nextPrime(theLists.length * 2)];
        for(int j=0;j<theLists.length;j++){
            theLists[j] = new LinkedList<>();
        }
        currentSize = 0;
        for(int i=0;i<olsLists.length;i++)
            for(T item:olsLists[i])
                insert(item);
    }

    private int myhash(T x){
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if(hashVal < 0)
            hashVal += theLists.length;
        return hashVal;
    }

    public static boolean isPrime(int n){
        int j;
        for(j=2;j<n;j++){
            if(n % j ==0)
                break;
        }
        return j==n;
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
    public static void  main(String[] args){
        System.out.println(isPrime(6));
    }
}
