package com.Minimum_Span_Trees;

import java.util.Iterator;

/**
 * @ClassName Graph
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/5 17:23
 * @Version 1.0
 */

public abstract class Graph<T extends Comparable<? super T> > {
    public abstract int getNode();
    public abstract int getEdge();
    public abstract void addEdge(int v, int w,T weight);
    public abstract boolean hasEdge(int v,int w);
    abstract void show();
    abstract Iterator iterator(int v);

    //abstract void addEdge(int )

}
