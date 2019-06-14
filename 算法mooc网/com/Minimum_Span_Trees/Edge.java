package com.Minimum_Span_Trees;

import java.util.NoSuchElementException;

/**
 * @ClassName Edge
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/5 17:14
 * @Version 1.0
 */

public class Edge<T extends Comparable<? super T>> {
    private int node1, node2;
    private T weight;

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public T getWeight() {
        return weight;
    }

    /**
 * @Author zt648
 * @Description 返回另外一条边
 * @Date 17:22 2019/6/5
 * @Param [node]
 * @return int
 * @throw
 **/
    public int otherNode(int node){
        if(node!=node1 && node!=node2)
            throw new NoSuchElementException();
        return node == node1?node2:node1;
    }
}
