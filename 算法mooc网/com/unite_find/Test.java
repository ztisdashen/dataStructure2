package com.unite_find;

/**
 * @author zt648
 * @time 五月:15:11:44
 * @project 数据结构与算法分析
 */
public class Test {
    public static void main(String[] args){
        int count = 100000;
        UniteFind5 uniteFind5 = new UniteFind5(count);
//        int a = (int)(Math.random() * count);
//        int b = (int)(Math.random() * count);
        long s = System.currentTimeMillis();
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind5.unite(a,b);
        }
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind5.isConnected(a,b);
        }
        long e = System.currentTimeMillis();
        System.out.println(e-s);
        UniteFind4 uniteFind4 = new UniteFind4(count);
//        int a = (int)(Math.random() * count);
//        int b = (int)(Math.random() * count);
        long s2 = System.currentTimeMillis();
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind4.unite(a,b);
        }
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind4.isConnected(a,b);
        }
        long e2 = System.currentTimeMillis();
        System.out.println(e2-s2);
        UniteFind3 uniteFind3 = new UniteFind3(count);
//        int a = (int)(Math.random() * count);
//        int b = (int)(Math.random() * count);
        long s3 = System.currentTimeMillis();
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind3.unite(a,b);
        }
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind3.isConnected(a,b);
        }
        long e3 = System.currentTimeMillis();
        System.out.println(e3-s3);
        UniteFind1 uniteFind1 = new UniteFind1(count);
//        int a = (int)(Math.random() * count);
//        int b = (int)(Math.random() * count);
        long s4 = System.currentTimeMillis();
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind1.unite(a,b);
        }
        for(int i = 0 ; i <count; i ++ ){
            int a = (int)(Math.random() * count);
            int b = (int)(Math.random() * count);
            uniteFind1.isConnected(a,b);
        }
        long e4 = System.currentTimeMillis();
        System.out.println(e4-s4);

    }
}

