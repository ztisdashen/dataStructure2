package com.collection;

/**
 * @ClassName DisSets
 * @Description TODO
 * 并查集
 * @Author zt648
 * @Date 2019/6/18 15:19
 * @Version 1.0
 */

public class DisSets {

    private int[] s;
    /**
     * @Author zt648
     * @Description 不相交集类的初始化历程
     * @Date 15:24 2019/6/18
     * @Param [numElement]
     * @return
     * @throw
     **/
    protected DisSets(int numElement){
        s = new int[numElement];
        for(int i=0;i<s.length;i++){
            s[i] = -1;
        }
    }
    /**
     *将两种个元素联合
     * 为了简化，我们假设root1和root2是不相同的
     *
     * 在union的过程中很有可能产生一种链表，在find的过程中就会变成o^2级别的操作
     */
    public void union(int root1,int root2){
        s[root2] = root1;
        //这个union并不是最好的方法
    }
    /**
   *为了简化，我们省略了一些检查error
     */
    public int find(int x){
        if(s[x] <0 )
            return x;
        else
            return find(s[x]);
    }
}
