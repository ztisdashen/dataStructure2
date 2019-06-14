package com.Minimum_Span_Trees;

import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @ClassName DenseGraph
 * @Description 稠密图
 * @Author zt648
 * @Date 2019/6/5 17:26
 * @Version 1.0
 */

public class DenseGraph<T extends Comparable<? super T>> extends Graph {
    private int totalNode;
    private int totalEdge;
    private List<List<Edge>> graph;
    private boolean directed;

    public DenseGraph(int totalNode, boolean directed) {
        this.totalNode = totalNode;
        this.directed = directed;
        graph = new ArrayList<>();
        this.totalEdge = 0;

        for (int i = 0; i < totalNode; i++) {
            List<Edge> list = new ArrayList<>();
            for (int j = 0; j < totalNode; j++) {
                list.add(null);
            }
            graph.add(list);
        }

    }

    @Override
    public int getNode() {
        return totalNode;
    }

    @Override
    public int getEdge()
    {
        return totalEdge;
    }

    @Override
    public void addEdge(int v, int w, Comparable weight) {
            if(v<0 || v > totalNode)
                throw new IndexOutOfBoundsException();
            if(w<0 || w > totalNode)
                throw new IndexOutOfBoundsException();
            if(hasEdge(v,w)){
                Edge<T> edge = graph.get(v).get(w);
                edge.weight = (T)weight;
                if(!directed){
                    Edge<T> edge2 = graph.get(v).get(w);
                    edge2.weight = (T)weight;
                }
            }
            Edge<T> edge = new Edge<>(v,w, (T) weight);
            graph.get(v).set(w,edge);
            if(!directed){
                graph.get(w).set(v,edge);
            }



    }

    @Override
    public boolean hasEdge(int v, int w) {
        if(v<0 || v > totalNode)
            throw new IndexOutOfBoundsException();
        if(w<0 || w > totalNode)
            throw new IndexOutOfBoundsException();
        return graph.get(v).get(w)!= null;
    }

    @Override
    void show() {
        for(int i=0;i<totalNode;i++){
            for(int j=0;j<totalNode;j++){
                Edge<T> edge = graph.get(i).get(j);
                if(edge != null){
                    System.out.print(edge.otherNode(i)+" ");
                }
            }
            System.out.println();
        }
    }

    @Override
    Iterator iterator(int v) {
        return null;
    }
    /**

     * @author zt648
     * @Description //TODO zt648
     * @Date 18:42 2019/6/5
     * @Param
     * @return
     * @throw
     * 迭代器
     */

    private class DenseGraphIterator implements Iterator{

        private int v;
        private int current;
        private List<Edge> lst;
        private List<Integer> array;

        public DenseGraphIterator(int v) {
            this.v = v;
            this.current = 0;
            lst = graph.get(v);
            for(int i=0;i<lst.size();i++){
                if(lst.get(i) != null)
                    array.add(i);
            }
        }




        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current==array.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Object next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return array.get(current++);
        }
    }


    class Edge<T> {
        private int node1, node2;
        private T weight;

        public Edge() {
        }

        public Edge(int node1, int node2, T weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

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
         * @return int
         * @Author zt648
         * @Description 返回另外一条边
         * @Date 17:22 2019/6/5
         * @Param [node]
         * @throw
         **/
        public int otherNode(int node) {
            if (node != node1 && node != node2)
                throw new NoSuchElementException();
            return node == node1 ? node2 : node1;
        }
    }
}
