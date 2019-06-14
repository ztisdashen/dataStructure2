package com.Minimum_Span_Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName WorkGraph
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/13 19:41
 * @Version 1.0
 */

public class WorkGraph {
    //总的课程数目
    private int totalVertex;
    private int totalEdge;
    private List<List<Edge>> graph;
    private Vertex[] vertexs;

    public WorkGraph(int totalVertex) {
        this.totalVertex = totalVertex;
        this.vertexs = new Vertex[totalVertex];
        this.totalEdge = 0;
        init(totalVertex);

    }
//初始化
    private void init(int totalVertex) {
        graph = new ArrayList<>(totalVertex);
        for (int i = 0; i < totalVertex; i++) {
            List<Edge> list = new ArrayList<>(totalVertex);
            for (int n = 0; n < totalVertex; n++) {
                list.add(null);
            }
            vertexs[i] = new Vertex(i);
            graph.add(list);
        }
    }

//边
    class Edge {
        //主节点
        private Vertex vertex1;
        //副节点
        private Vertex vertex2;

        public Edge(Vertex vertex1, Vertex vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }

        public Vertex getVertex1() {
            return vertex1;
        }

        public Vertex getVertex2() {
            return vertex2;
        }

    }

    class Vertex {
        //这门课
        private int clazz;
        //这门课的时间
        private int time;
        //这门课的总人数
        private int numberOfPeople;

        public Vertex(int clazz) {
            this.clazz = clazz;
            this.time = 0;
            this.numberOfPeople = 0;
        }

        public int getClazz() {
            return clazz;
        }

        public void setClazz(int clazz) {
            this.clazz = clazz;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getNumberOfPeople() {
            return numberOfPeople;
        }

        public void setNumberOfPeople(int numberOfPeople) {
            this.numberOfPeople = numberOfPeople;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "clazz=" + clazz +
                    ", time=" + time +
                    ", numberOfPeople=" + numberOfPeople +
                    '}';
        }
    }

    //@Override
    public int getNode() {
        return totalVertex;
    }

    //@Override
    public int getEdge() {
        return totalEdge;
    }


    public void addEdge(int... num) {

        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                Edge edge = new Edge(vertexs[num[i]], vertexs[num[j]]);
                graph.get(num[i]).set(num[j], edge);
                totalEdge++;
                Edge edge2 = new Edge(vertexs[num[j]], vertexs[num[i]]);
                graph.get(num[j]).set(num[i], edge2);
                totalEdge++;
            }

        }
        for (int i = 0; i < num.length; i++) {
            vertexs[num[i]].setNumberOfPeople(vertexs[num[i]].getNumberOfPeople() + 1);
        }
    }


    //展示这个图
    void show() {
        for (int i = 0; i < totalVertex; i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                Edge edge = graph.get(i).get(j);
                if (edge != null) {
                    System.out.print(edge.getVertex2().getClazz() + " ");
                }
            }
            System.out.println();
        }
    }

    public List<List<Edge>> sort() {
        List<List<Edge>> lists = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            lists.add(graph.get(i));
        }

        //按度的大小排序
        Collections.sort(lists, (o1, o2) -> {
            int i = 0;
            int i1 = 0;
            for (Edge e : o1
            ) {
                if (e != null)
                    i++;
            }
            for (Edge e : o2
            ) {
                if (e != null)
                    i1++;
            }
            return i1 - i;
        });
        return lists;

    }

    public void chooseClass() {
        //time是时间
        int time = 1;
        //visit数组是判断这个课程是否已经被分配了时间
        boolean[] visit = new boolean[totalVertex];
        for (int i = 0; i < visit.length; i++) {
            visit[i] = false;
        }

        List<List<Edge>> list = sort();
        int index = 0;
            while (index < list.size()) {
                int index2 = index + 1;
                if (visit[index]) {
                    index++;
                    continue;
                }
                int clazz = -1;
                for (int j = 0; j < list.get(index).size(); j++) {
                    Edge edge = list.get(index).get(j);
                    //找到非空的边
                    if (edge != null) {
                        visit[index] = true;
                        //查出这个节点对应的课程
                        clazz = edge.getVertex1().getClazz();
                        //确定这门课程时间
                        vertexs[clazz].setTime(time);
                        break;
                    }
                }
                while (index2 < list.size()) {
                    while (index2 < list.size() && hasEdge(index, index2, list)) {

                        //排除一种特殊情况，即a与c无边，a与d无边，但c与d右边
                        if(visit[index2-1] && hasEdge(index2-1,index2,list))
                            index2++;
                        index2++;
                    }
                    //将想向下探测且符合条件的节点分配时间，并设为visit为true;
                    for (int j = 0; index2 < list.size() && j < list.get(index2).size(); j++) {
                        Edge edge = list.get(index2).get(j);
                        if (edge != null) {
                            visit[index2] = true;
                            clazz = edge.getVertex1().getClazz();
                            vertexs[clazz].setTime(time);
                            index2++;
                            break;
                        }
                    }
                }
                index++;
                time++;


        }

    }

    public void showTime() {
        chooseClass();
        for (int i = 0; i < vertexs.length; i++) {
            System.out.println(vertexs[i]);
        }
    }

    private boolean hasEdge(int index, int index2, List<List<Edge>> sort) {
        return sort.get(index).get(index2) != null;
    }


    public static void main(String[] args) {
        WorkGraph workGraph = new WorkGraph(6);
        workGraph.addEdge(0, 1);
        workGraph.addEdge(3, 4, 0);
        workGraph.addEdge(5, 0, 1);
        workGraph.addEdge(3, 0, 4);
        workGraph.addEdge(4, 5);
        workGraph.addEdge(2, 3, 0);
        workGraph.addEdge(5, 1, 0);
        workGraph.addEdge(0, 2, 1);
        workGraph.addEdge(2, 1);
        workGraph.addEdge(4, 0, 5);
        workGraph.show();
        workGraph.showTime();


    }

}
