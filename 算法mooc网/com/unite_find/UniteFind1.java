package com.unite_find;

/**
 * 通过数组实现并查集，将索引index与其他的index合并
 * 这种并查集的find操作最为简单，直接利用数组的索引来返回的属于那个集合
 * @author zt648742668
 */
public class UniteFind1 {
    private int[] items;
    private int count;

    public UniteFind1(int count) {
        this.count = count;
        items = new int[count];
        for(int i=0;i<count;i++) {
            items[i] = i;
        }
    }
    public int find(int value){
        if(value<0 || value>=count)
            throw  new NullPointerException();
        return items[value];
    }
    public boolean isConnected(int p,int q){
        return find(p) == find(q);
    }
    public void unite(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;
        else {
            for(int i=0;i<count;i++){
                if(find(i)==pRoot){
                    items[i] = qRoot;
                }
            }
            return;
        }
    }
}
