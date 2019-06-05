package com.graph;

import java.io.PushbackInputStream;
import java.util.*;

/**
 * @ClassName Path
 * @Description 寻找两个节点的路
 * @Author zt648
 * @Date 2019/5/29 17:24
 * @Version 1.0
 */

public class Path {
    //图
    private Graph graph;
    //
    private int s;
    private boolean[] visited;
    //from储存该index的边的另一个节点，来自哪里,而s对应的from一定是-1
    private int[] from;
    
    private int length;
    public Path(Graph graph,int s){
        this.length = graph.getNode();
        if(s < 0 || s > length ) {
            throw  new IndexOutOfBoundsException();
        }
        this.graph = graph;
        this.s = s ;
        visited = new boolean[length];
        from = new int[length];
        for(int i=0;i<length;i++){
            visited[i] = false;
            from[i] = -1;
        }
        deepFind(s);
    }
    /**
     * @Author zt648
     * @Description 深度优先遍历
     * @Date 18:20 2019/5/29
     * @Param [node]
     * @return void
     * @throw
     **/
    private void deepFind(int node){
        visited[node] = true;
        Iterator iterator = graph.iterator(node);
        while (iterator.hasNext()){
            int num = (int) iterator.next();
            if (!visited[num]){
                from[num] = node;
                deepFind(num);
            }
        }
    }
    /**
     * @Author zt648
     * @Description 判断s与node之间是否有路
     * @Date 18:25 2019/5/29
     * @Param [node]
     * @return boolean
     * @throw
     **/
    boolean hasPath(int node){
        if(node < 0 || node > length)
            throw  new IndexOutOfBoundsException();
        return visited[node];
    }

//3->0->1，栈中储存的是1-0-3，所以要在pop出去
    public void path(int w, List<Integer> list ){
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while ( p != -1){
            stack.push(p);
            p = from[p];
        }

        list.clear();

        while (!stack.empty()){
            list.add(stack.peek());
            stack.pop();
        }

    }
    public void showPath(int w){
        List<Integer> list = new ArrayList<>();
        path(w,list);
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i));
            if(i!=list.size()-1){
                System.out.print("->");
            }

        }
        System.out.println();
    }


}
