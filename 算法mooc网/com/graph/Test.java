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
        int m = 200;
        int n = 50;
        DenseGraph graph = new DenseGraph(n, false);

        for (int i = 0; i < m; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            graph.addEdge(a, b);
        }
        graph.show();
        Path path = new Path(graph,3);
        path.showPath(3);
        ShortestPath path1 = new ShortestPath(graph,3);
        path1.showPath(3);
        System.out.println(path1.length(3));
    }


}
