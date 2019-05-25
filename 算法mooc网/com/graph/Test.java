package com.graph;

import java.util.Iterator;
import java.util.List;

/**
 * @ClassName Test
 * @Description TODO
 * @Author zt648
 * @Date 2019/5/22 18:06
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) {
        int m = 5;
        int n = 4;
        DenseGraph graph = new DenseGraph(n, false);

        for (int i = 0; i < m; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            graph.addEdge(a, b);
        }
                for(int i = 0;i<n;i++){
            Iterator iterator = graph.iterator(i);
            while (iterator.hasNext())
                System.out.print(iterator.next()+"   ");
            System.out.println();


//        Graph graph1 = new SparseGraph(n,false);
//        for(int i=0;i<m;i++){
//            int a = (int)(Math.random() * n );
//            int b = (int)(Math.random() * n );
//            graph1.addEdge(a,b);
//        }
//        for(int i = 0;i<n;i++){
//            Iterator iterator = graph1.iterator(i);
//            while (iterator.hasNext())
//                System.out.print(iterator.next()+"   ");
//            System.out.println();
//        }
        }

    }
}
