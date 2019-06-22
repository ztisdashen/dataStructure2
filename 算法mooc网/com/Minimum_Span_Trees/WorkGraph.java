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
    //�ܵĿγ���Ŀ
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
//��ʼ��
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

//��
    class Edge {
        //���ڵ�
        private Vertex vertex1;
        //���ڵ�
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
        //���ſ�
        private int clazz;
        //���ſε�ʱ��
        private int time;
        //���ſε�������
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
            return "�γ�������"+clazz+" ʱ�䰲�ţ�"+time+" �γ�������"+numberOfPeople;
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


    //չʾ���ͼ
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

        //���ȵĴ�С����
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
        //time��ʱ��
        int time = 1;
        //visit�������ж�����γ��Ƿ��Ѿ���������ʱ��
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
                    //�ҵ��ǿյı�
                    if (edge != null) {
                        visit[index] = true;
                        //�������ڵ��Ӧ�Ŀγ�
                        clazz = edge.getVertex1().getClazz();
                        //ȷ�����ſγ�ʱ��
                        vertexs[clazz].setTime(time);
                        break;
                    }
                }
                while (index2 < list.size()) {
                    //�ҵ�û���ڽӱߵĽڵ�
                    while (index2 < list.size() && (hasEdge(index, index2, list)||visit[index2]) ) {
                        index2++;
                    }
                    //�ų��������
                    while (index2 < list.size() && (hasEdge(index2 - 1, index2, list) ||visit[index2])) {
                        index2++;
                        while (index2 < list.size() && (hasEdge(index, index2, list)||visit[index2])) {
                            index2++;
                        }
                    }
                    //��������̽���ҷ��������Ľڵ����ʱ�䣬����ΪvisitΪtrue;
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
        WorkGraph workGraph = new WorkGraph(17);
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 6, 7);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 8);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 10);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 9);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 6, 11);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 6, 7);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 11);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 6, 12);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 13, 14);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 13, 12, 14);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 13, 15, 11);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 16);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 6, 10);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 13, 9);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 10, 9);
        }

        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 6, 15);
        }

        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 7, 15);
        }
        for (int i = 0; i < 5; i++) {
            workGraph.addEdge(0, 1, 2, 3, 4, 5, 11, 7);
        }
        workGraph.show();
        workGraph.showTime();
    }

}
