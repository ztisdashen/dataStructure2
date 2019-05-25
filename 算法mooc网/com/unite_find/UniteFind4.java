package com.unite_find;

/**
 * @author zt648
 * @time 五月:15:11:26
 * @project 数据结构与算法分析
 */
public class UniteFind4 {
    private int[] parents;
    private int[] rank;//rank数组来储存以i为根的树其中树的层数
    private int count;

    public UniteFind4(int count) {
        this.count = count;
        init(count);
    }

    private void init(int count) {
        rank = new int[count];
        parents = new int[count];
        for (int i = 0; i < count; i++) {
            rank[i] = 1;
            parents[i] = i;
        }
    }

    public int find(int p) {
        if (p < 0 || p >= count)
            throw new NullPointerException();
        while (parents[p] != p) {
            p = parents[p];
        }
        return p;
    }

    public boolean isConnected(int p, int q) {
        return parents[p] == parents[q];
    }

    public void unite(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        } else if (rank[pRoot] > rank[qRoot]) {
            parents[qRoot] = pRoot;
        } else if (rank[pRoot] < rank[qRoot]) {
            parents[pRoot] = qRoot;
        } else {
            parents[pRoot] = qRoot;
            rank[qRoot]++;
        }
    }
}
