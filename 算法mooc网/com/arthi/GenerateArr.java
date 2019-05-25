package com.arthi;

import java.util.stream.Stream;


public class GenerateArr {
    /**
     *
     * @param low     ???????????С?
     * @param height  ?????????????
     * @param length  ????????????
     * @return Integer[] ????????
     */
    public static int[] randomIntList(int low,int height,int length) {
        Stream<Integer> stream = Stream.generate(()->{
            return (int)(low + Math.random() * (height - low + 1));
        }).limit(length);
        Integer[] arr = stream.toArray(Integer[]::new);
        int[] list = new int[arr.length];
        for(int i=0;i<length;i++)
            list[i] = arr[i];
        return list;
    }


    /**
     *
     * ????????????????????
     *
     * @param length   ????????????
     * @param swapTimes  ????????
     * @return int[] ????????;
     */
    public static int[] orderIntList(int length,int swapTimes) {
        int []arr = new int[length];
        for(int i=0;i<length;i++) {
            arr[i] = i;
        }
        for(int i=0;i<swapTimes;i++) {
            int j = (int)(Math.random() * length);
            int k = (int)(Math.random() * length);
            int l = arr[j];
            arr[j] = arr[k];
            arr[k] = l;
        }
        return arr;
    }
}
