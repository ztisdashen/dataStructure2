package com.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @ClassName DenseGraph
 * @Description 稠密图
 * @Author zt648
 * @Date 2019/5/22 17:08
 * @Version 1.0
 */

public class DenseGraph implements Graph {
    private int m;
    private int v;
    private boolean directed;
    private List<List<Boolean>> list = new ArrayList<>();
    public DenseGraph(int m, boolean directed) {
        this.v = 0;
        this.m = m;
        this.directed = directed;
        for(int i=0;i<m;i++){
            List<Boolean> lst = new ArrayList<>();
            for(int n=0;n<m;n++){
                lst.add(false);
            }

          list.add(lst);
        }
    }

    public List<List<Boolean>> getList() {
        return list;
    }

    @Override
    public int V() {
        return m;
    }

    @Override
    public int E() {
        return v;
    }

    @Override
    public void addEdge(int v, int w) {
        if(list.get(v).get(w))
            return;
        else {
            list.get(v).set(w, true);
            if(!directed){
                list.get(w).set(v,true);
            }
        }
        v++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if(v<0 || v > m)
            throw new IndexOutOfBoundsException();
        if(w<0 || w > m)
            throw new IndexOutOfBoundsException();
        return list.get(v).get(w);
    }

    @Override
    public void show() {
        for (List<Boolean> lst:list){
            for (Boolean b:lst
                 ) {
                System.out.print(b+" ");
            }
            System.out.println();
        }
    }

    @Override
    public Iterator iterator(int v) {
        return new DenseGraphIterator(v);
    }



    private class DenseGraphIterator implements Iterator{
        private int current = 0;
        private int v;
        private List<Boolean> lst;
        private List<Integer> array;

        public DenseGraphIterator(int v) {
            this.v = v;
            lst = list.get(this.v);
            array = new ArrayList<>();
            for(int i=0;i<lst.size();i++){
                if(lst.get(i)){
                    array.add(i);
                }
            }
        }

        @Override
        public boolean hasNext() {

            return current < array.size();
        }

        @Override
        public Object next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return array.get(current++);
        }
    }
}
