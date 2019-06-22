package com.collection;

/**
 * @ClassName DisSet2
 * @Description
 * 这是针对1的优化，针对union
 * 一种是记住每个集合的大小，让每个集合的根元素的数组元素记住这棵树的大小的负值
 * 初始时每个元素都是-1;
 * 检查树的大小，新的大小事旧的大小之和
 *
 * 按高度求并
 * 在union时，使得浅的树成为深的树的子树。
 * 只有在两颗相同的深度求并时才会使树的高度增加
 * 由于零的高度不是负的，所以需要储存高度的负值-1，初始时每个元素的复制是-1;
 * @author zt648
 * @Date 2019/6/18 15:31
 * @version 1.0
 */

public class DisSet2 {
    private int[] s;
    /**
     * @Author zt648
     * @Description 不相交集类的初始化历程
     * @Date 15:24 2019/6/18
     * @Param [numElement]
     * @return
     * @throw
     **/
    protected DisSet2(int numElement){
        s = new int[numElement];
        for(int i=0;i<s.length;i++){
            s[i] = -1;
        }
    }
    /**
     *为了简化，我们假设两个根是不一样的
     * @param root1 一个根
     * @param root2 另一个根

     */
    public void union(int root1,int root2){
        //若root1的大小小于root2.则让root1的根变成root2
      if(s[root2] < s[root1])
          s[root1] =  root2;
      else {
          //让root1成为root2的根
          //并让root1的大小的负值减1
          if(s[root1] == s[root2]){
              s[root1]--;
          }
          s[root2] = root1;
      }
    }
    /**
     *为了简化，我们省略了一些检查error
     * 路径的压缩，即凡是x的直接祖宗，都会变成根的子节点
     * 从x到根的路径上每个节点都使其父节点成为该树的根
     * @param x 要搜寻的元素
     * @return 返回x的根
     */
    public int find(int x){
        if(s[x] < 0 )
            return x;
        else
            return s[x] = find(s[x]);
    }
}
