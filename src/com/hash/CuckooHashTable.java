package com.hash;

import java.lang.invoke.SwitchPoint;
import java.util.Random;

/**
 * @ClassName CuckooHashTable
 * @Description 布谷鸟散列，假设有n个项，我们要维护两个分别超过半数的表，且有两个独立的散列函数，可以把每个项
 * 分配到每个表的每个位置。布谷鸟散列保持不变的是每个项总会被储存在两个位置之一。类似与线性探测，但这个可能会产生
 * 循环，但概率很低，若检测到循环发生，就再散列
 * @Author zt648
 * @Date 2019/5/22 13:59
 * @Version 1.0
 */

public class CuckooHashTable<T> {
    //最大负载，超过就再散列；
    private static final double MAX_LOAD = 0.4;
    //最大再散列的次数
    private static final int ALLOWED_REHASHES = 1;
    //默认的大小
    private static final int DEFAULT_TABLESIZE = 101;
    private HashFamily<? super T> hashFunctions;
    private int numFunctions;
    private T[] array;
    private int currentSize;


    public CuckooHashTable(HashFamily<? super T> hf) {
        this(hf, DEFAULT_TABLESIZE);
    }

    /**
     * @return
     * @Author zt648
     * @Description 构造器，散列函数集合
     * @Date 14:19 2019/5/22
     * @Param [hf, size]
     * @throw
     **/
    public CuckooHashTable(HashFamily<? super T> hf, int size) {
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numFunctions = hf.getNumberOfFunctions();
    }

    /**
     * @return void
     * @Author zt648
     * @Description 清空
     * @Date 14:26 2019/5/22
     * @Param []
     * @throw
     **/
    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private void allocateArray(int newSize) {
        array = (T[]) new Object[newSize];
    }

    /**
     * @return void
     * @Author zt648
     * @Description 散列函数
     * @Date 14:28 2019/5/22
     * @Param T x x对应的code
     * @Param int which 选择哪一个散列函数
     * @throw
     **/
    private int myHash(T x, int which) {
        int hashVal = hashFunctions.hash(x, which);

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;
        return hashVal;
    }

    /**
     * @return int position 返回位置
     * @Author zt648
     * @Description 找到x的位置
     * @Date 14:31 2019/5/22
     * @Param T x
     * @throw
     **/
    private int findPos(T x) {
        for (int i = 0; i < numFunctions; i++) {
            int pos = myHash(x, i);
            if (array[pos] != null && array[pos].equals(x))
                return pos;
        }
        return -1;
    }


    /**
     * @return boolean
     * @Author zt648
     * @Description 判断是否有x
     * @Date 14:33 2019/5/22
     * @Param [x]
     * @throw
     **/
    public boolean contains(T x) {
        return findPos(x) != -1;
    }

/**
 * @Author zt648
 * @Description 布谷鸟的删除例程
 * @Date 14:34 2019/5/22
 * @Param [x]
 * @return boolean
 * @throw
 **/
    public boolean remove(T x) {
        int pos = findPos(x);

        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }

        return pos != -1;
    }


    public boolean insert(T x){
        if(contains(x))
            return false;

        if(currentSize >= array.length * MAX_LOAD)
            expand();
        return insertHelper(x);
    }

    private int rehashes = 0;
    private Random r = new Random();


    private boolean insertHelper(T x) {
        final int COUNT_LIMIT = 100;

        while(true){
            //存储上次插入的位置
            int lastPos = -1;
            int pos;

            for(int count = 0; count<COUNT_LIMIT;count++){
                for(int i = 0;i<numFunctions;i++){
                    pos = myHash(x,i);
                    if(array[pos] == null){
                        array[pos] = x;
                        currentSize ++ ;
                        return true;
                    }
                }
                //没有位置是可以利用的，产生一个随机数
                int i = 0;
                do {
                    pos = myHash(x,r.nextInt(numFunctions));
                    //保证探测次数不超过5
                    //判断是不是上次刚刚插入的位置，如果是，如果是跳出循环
                }while (pos == lastPos && i++ < 5);

                //交换位置，让被交换的t成为新的x进行新的探测
                T tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
                //最多执行100次，如发现还不能满足，rehash扩充table
            }

            if(++rehashes > ALLOWED_REHASHES){
                expand();
                rehashes = 0;
            }else
                rehash();

        }




    }
    private void expand(){
        rehash((int)(array.length / MAX_LOAD));
    }

    private void rehash(){
        hashFunctions.generateFunction();;
        rehash(array.length);
    }


    private void rehash(int newLength){
        T[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        for(T str:oldArray){
            if(str != null)
                insert(str);
        }
    }

    public int nextPrime(int n) {
        int i;
        int j;
        for (i = n + 1; ; i++) {

            for (j = 2; j < i; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j == i) {
                break;
            }
        }
        return i;
    }

}
