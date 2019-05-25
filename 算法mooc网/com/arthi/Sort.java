package com.arthi;

/**
 *
 */

public class Sort {

    /**
     * insertSort对i前的元素进行排序，即i前面的元素已经有序，
     * 通过循环判断arr[i]应在i前的那个位置，若arr[j-1]>v,则元素后移一个单位
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void insertSort(int[] arr, int l, int r) {
        for (int i = 1; i <= r; i++) {
            int value = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > value; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = value;
        }
    }

    public static void quickSort1(int[] arr, int left, int right) {
        if (left == right)
            return;
        if ((right - left) <= 15) {
            insertSort(arr, left, right);
            return;
        }
        int j = quickSort(arr, left, right);
        quickSort1(arr, left, j - 1);
        quickSort1(arr, j + 1, right);
    }

    /**
     * quickQort O(nlog(n))级别的算法
     * quickSort1首先找到一个基准value，将数组分为小于value和大于value的两部分
     * 即arr[left,j]<value arr[j+1,r]>v
     * 缺点：有可能产生极不均衡的情况使排序变为O(n^2)级别的算法
     *
     * @param arr   传入一个数组
     * @param left  数组的左边界
     * @param right 数组的有边界 length - 1
     * @return
     */
    private static int quickSort(int[] arr, int left, int right) {


        int v = arr[left];
        int index = (int) (Math.random() * (right - left + 1) + left);
        arr[left] = arr[index];
        arr[index] = v;
        v = arr[left];
        int j = left;
        //是的arr[left+1,j]<v arr[j,i)>v
        for (int lt = left + 1; lt <= right; lt++) {
            if (arr[lt] < v) {
                int value = arr[lt];
                arr[lt] = arr[j + 1];
                arr[j + 1] = value;
                j++;
            }
        }
        int value = arr[left];
        arr[left] = arr[j];
        arr[j] = value;
        return j;
    }


    public static void quickSort2(int[] arr, int left, int right) {
        if (left >= right)
            return;
        if (right - left <= 15)
            insertSort(arr, left, right);
        int j = quick2(arr, left, right);
        quickSort2(arr, left, j - 1);
        quickSort2(arr, j + 1, right);
    }

    /**
     * quickSort2对上面可能时quickSort退化进行优化，使等于v的元素均匀分布
     *
     * @param arr   数组
     * @param left  左边界
     * @param right 右边届
     * @return j v最后所在的位置
     */
    private static int quick2(int[] arr, int left, int right) {

        int v = arr[left];
        int index = (int) (Math.random() * (right - left + 1) + left);
        arr[left] = arr[index];
        arr[index] = v;
        v = arr[left];
        int i = left + 1, j = right;
        //arr[left+1,i]<=v  arr[j,r]>=v
        while (true) {
            //判断是否越界，切记越界条件要卸载前面，因为要先判断是否越界
            while (i < right && arr[i] <= v)
                i++;
            while (j > left && arr[j] >= v)
                j--;
            if (i > j)
                break;
            int x = arr[i];
            arr[i] = arr[j];
            arr[j] = x;
            i++;
            j--;
        }
        int x = arr[j];
        arr[j] = v;
        arr[left] = x;
        return j;
    }

    /**
     * quickSort3是将元素分为了arr[left+1,lt]<v arr[lt+1,i)=v arr[gt,r]>v
     * 避免像像quickSort2那样可能对等于v的元素重复操作
     * 最后arr[left,lt-1]<v,arr[lt,gt-1]=v arr[gt,r] >v
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort3(int[] arr, int left, int right) {

        System.out.println();
        if (left >= right)
            return;
        if (right - left <= 15) {
            insertSort(arr, left, right);
            return;
        }
        int lt = left; //保证初始的小于V的和大于V的数组长度为0;
        int gt = right + 1;
        int i = left + 1;
        int v = arr[left];
        int index = (int) (Math.random() * (right - left + 1) + left);
        arr[left] = arr[index];
        arr[index] = v;
        v = arr[left];

        while (i < gt) {
            if (arr[i] < v) {
                int x = arr[lt + 1];
                arr[lt + 1] = arr[i];
                arr[i] = x;
                lt++;
                i++;
            } else if (arr[i] > v) {
                int x = arr[gt - 1];
                arr[gt - 1] = arr[i];
                arr[i] = x;
                gt--;
            } else {
                i++;
            }
        }
        int c = arr[lt];
        arr[lt] = arr[left];
        arr[left] = c;

        quickSort3(arr, left, lt - 1);
        quickSort3(arr, gt, right);

    }



}
