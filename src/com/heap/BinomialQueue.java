package com.heap;

/**
 * @ClassName BinomialQueue
 * @Description 这是一个二项队列，它不同于一个其他优先队列，而是一个优先队列的集合，是一个森林。而这的森林中每个树都是
 * 有约束的，每个树的size都是2^k次方，所以这个二项队列的totalSize是一个等比数列的求和
 * 高度为0的二项树是一个单节点
 * 高度为1的二项树有2个节点
 * 高度为2的二项树有4个节点
 * ……………………~~~~~~
 * 高度为k的二项树是将一个高度为k-1的树加到另一个高度为k-1的树的根上
 * 鉴于这是个堆，应该加到根节点较小的树上
 * 若这个二项队列，假设有B(3),B(2),B(0)的数
 * totalSize就是以二进制表示1101---->(十进制)=13
 * <p>
 * 每个数据储存在节点中，最终变成一个列表，要求各个儿子按照他们的子树的大小排列，二项队列将是数组
 * 每个节点将包含数据，第一个儿子，以及右兄弟，各个儿子降序排列
 * @Author zt648
 * @Date 2019/5/29 11:49
 * @Version 1.0
 */

public class BinomialQueue<T extends Comparable<? super T>> {

    private static final int DEFAULT_TREES = 1;
    private Node<T>[] theTrees;
    private int currentSize;


    public BinomialQueue() {
    }

    /**
     * @return
     * @Author zt648
     * @Description 处理单节点插入的情况
     * @Date 12:21 2019/5/29
     * @Param [item]
     * @throw
     **/
    public BinomialQueue(T item) {
        theTrees = new Node[DEFAULT_TREES];
        theTrees[0] = new Node<>(item);
        this.currentSize = 1;
    }

    /**
     * @Author zt648
     * @Description 节点类
     * @Date 12:12 2019/5/29
     * @Param
     * @return
     * @throw
     **/
    private static class Node<T> {
        T element; //数据
        Node<T> leftFirstChild; //第一个左儿子
        Node<T> nextSibing; //右兄弟

        Node(T theElement) {
            this(theElement, null, null);
        }

        Node(T element, Node<T> leftFirstChild, Node<T> nextSibing) {
            this.element = element;
            this.leftFirstChild = leftFirstChild;
            this.nextSibing = nextSibing;

        }

    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * @return int
     * @Author zt648
     * @Description 3 << 2，则是将数字3左移2位
     * <p>
     * 1、首先把3转换为二进制数字0000 0000 0000 0000 0000 0000 0000 0011
     * <p>
     * 2、然后把该数字高位（左侧）的两个零移出，其他的数字都朝左平移2位，最后在低位（右侧）的两个空位补零。
     * <p>
     * 3、则得到的最终结果是0000 0000 0000 0000 0000 0000 0000 1100，则转换为十进制是12。
     * <p>
     * 通过这种方式可以得到currentSize
     * @Date 12:16 2019/5/29
     * @Param []
     * @throw
     **/
    private int capacity() {
        return (1 << theTrees.length) - 1;
    }

    /**
     * @author zt648
     * @description 合并两个具有相同大小的数,让t2成为t1的左儿子
     * @date 12:24 2019/5/29
     * @param t1,t2
     * @return com.heap.BinomialQueue.Node<T>
     * @throw
     **/
    private Node<T> combineTrees(Node<T> t1,Node<T> t2){
        if(t2.element.compareTo(t1.element) > 0){
            return combineTrees(t2,t1);
        }
        t2.nextSibing = t1.leftFirstChild;
        t1.leftFirstChild = t2;
        return t1;
    }

}
