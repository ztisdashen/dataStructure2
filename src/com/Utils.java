package com;

/**
 * @ClassName Utils
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/5 11:31
 * @Version 1.0
 */

public class Utils{
    /**
     * @Author zt648
     * @description 这个方法适用于扩展数组的
     * @Date 11:35 2019/6/5
     * @Param [list, newLength]
     * @return void
     * @throw
     **/
    public static <T> void copyArray(T[] list, int newLength) {

        T[] old = list;
        list = (T[]) new Object[newLength];
        for (int i = 0; i < old.length; i++) {
            list[i] = old[i];
        }

    }
}
