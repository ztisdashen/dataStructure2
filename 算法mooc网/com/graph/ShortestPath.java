package com.graph;

import com.list.Quene;

import java.util.*;

/**
 * @ClassName ShortestPath
 * @Description TODO
 * @Author zt648
 * @Date 2019/5/29 19:14
 * @Version 1.0
 */

public class ShortestPath {
    private Graph graph;
    private boolean[] visited;
    private int s;
    private int[] from;
    private int[] ord;
    private int length;

    public ShortestPath(Graph graph, int s) {
        length = graph.getNode();
        this.graph = graph;
        if (s < 0 || s >= length) {
            throw new IndexOutOfBoundsException();
        }
        this.s = s;
        from = new int[length];
        visited = new boolean[length];
        ord = new int[length];
        for (int i = 0; i < length; i++) {
            this.from[i] = -1;
            visited[i] = false;
            ord[i] = -1;
        }
/**
 * @Author zt648
 * @Description 进行广度优先遍历，即近似于树的操作，使用优先队列的方式，一定是高度低的
 * 位置先被处理，对应到图中而言就是路径短的先被处理。对于被遍历的元素，一定要先加入到队列再进行处理
 * @Date 20:10 2019/5/29
 * @Param [graph, s]
 * @return
 * @throw
 **/

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!queue.isEmpty()) {
            int num = queue.remove();
            Iterator iterator = graph.iterator(num);
            while (iterator.hasNext()) {
                int next = (int) iterator.next();
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    from[next] = num;
                    ord[next] = ord[num] + 1;
                }
            }
        }
    }


    public boolean hasPath(int w) {
        if (w < 0 || w >= length) {
            throw new IndexOutOfBoundsException();
        }
        return visited[w];
    }

    public void path(int w, List<Integer> list) {
        Stack<Integer> stack = new Stack<>();

        while (w != -1) {
            stack.push(w);
            w = from[w];
        }

        list.clear();
        while (!stack.empty()) {
            list.add(stack.pop());
        }
    }

    public void showPath(int w) {
        List<Integer> list = new ArrayList<>();
        path(w, list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print("->");
            }

        }
        System.out.println();
    }

    public int length(int w){
        return ord[w];
    }

}
