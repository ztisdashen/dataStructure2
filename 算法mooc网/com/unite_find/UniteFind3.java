package com.unite_find;

/**
 * @time 五月:15:11:14
 * @project 数据结构与算法分析
 * @author zt648
 * 第三种并查集，第二种并查集的一个缺点是在unite的操作中可能将
 * 一个树变成链表，使find操作的代价极大
 * 所以我们需要一个操作，判断两个数的size，将小的size的数加到大的size的树上
 * 我们需要一个size的数组，来储存每一个index个数的size
 */
public class UniteFind3 {
    private int[] parents;
    private int[] size;
    private int count;

    public UniteFind3(int count) {
        this.count = count;
        init(count);
    }

    private void init(int count) {
        size = new int[count];
        parents = new int[count];
        for(int i=0;i<count;i++){
            size[i] = 1;
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
        else if(size[pRoot] > size[qRoot]){
            parents[qRoot]  =pRoot;
            size[pRoot] += size[qRoot];
        }else {
            parents[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
    }
}
