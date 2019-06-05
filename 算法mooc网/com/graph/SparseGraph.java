package com.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @ClassName SparseGraph
 * @Description 稀疏图，使用邻接表
 * @Author zt648
 * @Date 2019/5/22 16:23
 * @Version 1.0
 */

public class SparseGraph implements Graph {

    private int node,edge;
    private boolean directed;
    List<List<Integer>> list = new ArrayList<>();

    public SparseGraph(int n,boolean isDirected){
        this.node = n;
        this.edge = 0;
        //是不是无向图
        directed = isDirected;
        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }
    }
    /*
     * @Author zt648
     * @Description 返回边数
     * @Date 16:31 2019/5/22
     * @Param []
     * @return int
     * @throw
     **/
    @Override
    public int getEdge() {
        return edge;
    }
    /*
     * @Author zt648
     * @Description 返回节点数
     * @Date 16:30 2019/5/22
     * @Param []
     * @return int
     * @throw
     **/
    @Override
    public int getNode() {
        return node;
    }
    /**
     * @Author zt648
     * @Description 添加边
     * @Date 16:37 2019/5/22
     * @Param [v, w]
     * @return void
     * @throw
     **/
    @Override
    public void addEdge(int v, int w) {
        if(v<0 || v > node)
            throw new ArrayIndexOutOfBoundsException();
        if(w<0 || w > node)
            throw new ArrayIndexOutOfBoundsException();
        List<Integer> lst = list.get(v);
        if(hasEdge(v,w))
            return;
        lst.add(w);
        list.set(v,lst);
        if(w != v && !directed){
            lst = list.get(w);
            lst.add(v);
            list.set(w,lst);
        }
        edge++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if(v<0 || v > node)
            throw new ArrayIndexOutOfBoundsException();
        if(w<0 || w > node)
            throw new ArrayIndexOutOfBoundsException();
        List<Integer> lst =  list.get(v);
        for( int i = 0 ; i < lst.size() ; i ++ ){
            if(w == lst.get(i))
                return true;
        }
        return false;
    }

    @Override
    public void show() {
        for (List<Integer> lst:
             list) {
            for(Integer i:lst)
                System.out.print(i+" ");
        }
        System.out.println();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator(int v) {
        return new SparseGraphIterator(v);
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */

    private class SparseGraphIterator implements Iterator{
        private int v;
        private int current = 0;
        private List<Integer> lst;

        public SparseGraphIterator(int v) {
            this.v = v;
            lst = list.get(this.v);
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

            return current < lst.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Object next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return lst.get(current++);
        }

    }
}
