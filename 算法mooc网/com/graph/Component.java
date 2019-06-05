package com.graph;


import java.util.Iterator;

/**
 * @ClassName Component
 * @Description 这个算法能够得到联通分支，两个是否链接
 * @Author zt648
 * @Date 2019/5/22 18:25
 * @Version 1.0
 */

public class Component {
    private Graph graph;
    //判断index是否被检测过
    private boolean[] visited;
    //有几个联通分支
    private int branch;
    //这个index属于那个联通分支（集合）
    private int[] id;

    public Component(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getNode()];
        this.id = new int[graph.getNode()];

        for (int i = 0; i < graph.getNode(); i++) {
            visited[i] = false;
            id[i] = -1;
        }
        branch = 0;

        for (int i = 0; i < this.graph.getNode(); i++) {
            if (!visited[i]) {
                deepFind(i);
                branch++;
            }
        }
    }

    //深度优先遍历
    private void deepFind(int v) {
        visited[v] = true;
        id[v] = branch;
        Iterator iterator = graph.iterator(v);
        while (iterator.hasNext()) {
            int index = (int) iterator.next();
            if (index >= 0) {
                if (!visited[index])
                    deepFind(index);
            }
        }

    }

    public boolean isConnected(int v, int w) {
//        if(v<0 || v >=graph.V())
//            throw new IndexOutOfBoundsException();
//        if(w<0 || w >= graph.V())
//            throw new IndexOutOfBoundsException();
        return id[v] == id[w];
    }

    public int getBranch() {
        return branch;
    }
}
