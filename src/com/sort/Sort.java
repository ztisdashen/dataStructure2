package com.sort;

import com.sun.source.tree.IfTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Sort
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/5 14:32
 * @Version 1.0
 */

public class Sort {


    /**
     * @return void
     * @Author zt648
     * @Description 交换数组的位置
     * @Date 14:37 2019/6/5
     * @Param [list, a, b]
     * @throw
     **/

    private static <T> void swapReference(T[] list, int a, int b) {
        T v = list[a];
        list[a] = list[b];
        list[b] = v;
    }

    /**
     * @return
     * @Author zt648
     * @Description 插入排序
     * 第i次循环使[0--i]变得有序
     * 第i次循环会将[i]的元素左移，只到倒找正确的位置
     * @Date 14:32 2019/6/5
     * @Param
     * @throw
     **/
    public static <T extends Comparable<? super T>> void
    insertSort(T[] list) {

        int j;

        for (int p = 1; p < list.length; p++) {
            //直接用tmp储存要比较的值避免重复的交换位置
            T tmp = list[p];

            for (j = p; j > 0 && tmp.compareTo(list[j - 1]) < 0; j--) {
                //直接将小于tmp的值覆盖右移
                list[j] = list[j - 1];
            }
            list[j] = tmp;
        }
    }

    /**
     * @return void
     * @Author zt648
     * @Description 希尔排序是第一批冲破二次时间屏障的第一批算法
     * 同时通过比较相聚一定间隙的元素进行工作
     * 每趟的间隙逐渐减小
     * 最后的间隙为1
     * 使用h1,h2,h3,h4,h5,h6....这样的增量间隙，每趟排序后都会使a[i] < a[i + h(k)]
     * <p>
     * <p>
     * 弊端当a[0]<a[2]<a[4]<a[6]...都是排序过的a[1]<a[3]<a[5]...且增量使1，2，4，则就会退化成为一个O^2算法
     * @Date 14:51 2019/6/5
     * @Param [a]
     * @throw
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a) {

        int j;

        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            //从这里向下就是一个插入排序，保证以gap为间隙的子数组是有序的，当gap=1时即使插入排序
            for (int i = gap; i < a.length; i++) {

                T tmp = a[i];

                for (j = i; j > gap && tmp.compareTo(a[j - gap]) > 0; j -= gap) {
                    a[j] = a[j - gap];
                }
            }
        }
    }


//-----------------------------堆排序----------------------------------------------------------------
    //----------------------------------------------------------------------------------------------


    /**
     * @return
     * @Author zt648
     * @Description 堆排序
     * 直接使用堆的数据结构一个弊端就是需要一个额外的数组储存数列
     * 回避他的方法就是利用这样的事实
     * 每次deleteMin之后堆减小1，位于堆最后的index储存刚刚被删除的元素
     * 使用最小堆得到的是一个逆序的数列
     * 所以我们要使用最大堆
     * @Date 15:00 2019/6/5
     * @Param
     * @throw
     **/

    private static int leftChild(int i) {
        return 2 * i + 1;
    }
    /**
     * @Author zt648
     * @Description
     * i 执行下率的位置
     * 下率，对i->n的元素下率(这个n将会是数组的逻辑大小不是实际大小)
     * @Date 15:14 2019/6/5
     * @Param [a, i, n]
     * @return void
     * @throw
     **/
    private static <T extends Comparable<? super T>> void perDown(T[] a, int i, int n) {
        int child;
        T tmp;

        for (tmp = a[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);

            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
                child++;
            if (tmp.compareTo(a[child]) < 0)
                a[i] = a[child];
            else
                break;

        }

        a[i] = tmp;
    }

    public static <T extends Comparable<? super T>> void heapSort(T[] a){
        //第一个非叶子
        for(int i=a.length/2 -1;i>=0;i--){
            //形成一个堆
            perDown(a,i,a.length);
        }
        //i就是数组的逻辑大小
        for (int i=a.length-1;i>0;i--){
            //将最小元和最后一个元素互换位置
            swapReference(a,0,i);
            perDown(a,0,i);
        }
    }


    //----------------------------------归并排序----------------------------------------
    //---------------------------------------------------------------------------------

    /**
     * @Author zt648
     * @Description 归并排序
     * 基本想法
     * 合并两个已经排序的表
     * 两个表为A，B，以及一个临时表C，三个计数器Acount,bCount,Ccount;
     * A[Acount] and B[bCount]的较小的将被拷贝到c中
     * 当a，或b右一个用完时，另一个表全部拷贝到c中
     * @Date 15:45 2019/6/5
     * @Param [a, tmpArray, left, right]
     * @return void
     * @throw
     **/
    private static <T extends Comparable<? super T>> void mergeSort(T[] a,T[] tmpArray,int left,int right){

        if(left < right ){


            int center = (left + right) / 2;
            mergeSort(a,tmpArray,left,center);
            mergeSort(a,tmpArray,center+1,right);
            merge(a,tmpArray,left,center+1,right);
        }
    }

    private static <T extends Comparable<? super T>>void merge(T[] a, T[] tmpArray
            , int leftPos, int rightPos, int rightEnd) {

        int leftEnd = rightPos -1 ;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while(leftPos <= leftEnd && rightPos<= rightEnd){
            if(a[leftPos].compareTo(a[rightPos]) < 0)
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[tmpPos++] = a[rightPos++];

        }

        while (leftPos<=leftEnd)
            tmpArray[tmpPos++] = a[leftPos++];
        while (rightPos<=rightEnd)
            tmpArray[tmpPos++] = a[rightPos++];
        //复制回去
        for(int i=0;i<numElements;i++,rightEnd--){
            a[rightEnd] = tmpArray[rightEnd];
        }
    }


    //---------------------------------------快速排序-------------------------------------------
    //-----------------------------------------------------------------------------------------

    public static <T extends Comparable<? super T>> void simpleQuickSort(List<T> list) {
        //List<T> list = Arrays.asList(a);
        if (list.size() > 1) {
            List<T> smaller = new ArrayList<>();
            List<T> same = new ArrayList<>();
            List<T> larger = new ArrayList<>();

            T chosenItem = list.get(list.size() / 2);

            for (T item :
                    list) {
                if (item.compareTo(chosenItem) < 0)
                    smaller.add(item);
                else if(item.compareTo(chosenItem) > 0)
                    larger.add(item);
                else
                    same.add(item);

                simpleQuickSort(smaller);
                simpleQuickSort(larger);
            }
            list.clear();
            list.addAll(smaller);
            list.addAll(same);
            list.addAll(larger);
        }
    }
}
