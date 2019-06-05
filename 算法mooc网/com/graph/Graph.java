package com.graph;

import java.util.Iterator;

/**
 * @ClassName Graph
 * @Description 图的接口，能够增加边，判断是否有边，实现迭代器
 * @Author zt648
 * @Date 2019/5/22 16:14
 * @Version 1.0
 */

public interface Graph{
    int getNode();
    int getEdge();
    void addEdge(int v,int w);
    boolean hasEdge(int v,int w);
    void show();
    Iterator iterator(int v);
}
