package com.unite_find;

/**
 *
 * @author zt648
 * 这个并查集是优化unite的操作，利用parant数组实现树的操作
 * 找的index的parent，如果一个index的parant就是自己，那么他就是根
 * unite是将一个index的跟加到另一个index的一个跟上
 */
public class UniteFind2 {
//    private int[] items;
    private int parents[];
    private int count;

    public UniteFind2(int count) {
        this.count = count;
        init(count);
    }

    private void init(int count) {
        for(int i=0;i<count;i++){

            parents[i] = i;
        }
    }
    public int find(int p){
        if(p<0 || p>=count)
            throw new NullPointerException();
        while(parents[p] != p){
            p = parents[p];
        }
        return p;
    }

    public boolean isConnected(int p,int q){
        return parents[p]==parents[q];
    }
    public void unite(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot){
            return;
        }
        parents[p] = qRoot;
    }
}
