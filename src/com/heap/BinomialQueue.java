package com.heap;

import com.Utils;

/**
 * @ClassName BinomialQueue
 * @Description 这是一个二项队列，它不同于一个其他优先队列，而是一个优先队列的集合，
 * 是一个森林。而这的森林中每个树都是
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
 *
 *
 *
 * 在详细介绍二项的队列的基本操作之前，先了解下二项队列这种数据结构：
 *
 * 1）一个二项队列是若干棵树的集合。也就是说，二项队列不仅仅是一棵树，而是多棵树，并且每一棵树都遵守堆序的性质，所谓堆序的性质，就是指每个结点都比它的左右子树中结点要小（小顶堆）。这棵树称为“二项树”
 *
 * 2）二项队列中的树高度不同，一个高度至多存在一棵二项树。将高度为0的二项树记为 B(0)，高度为 k 的二项树记为 B(k)
 *
 * 也就是说，对于k>=0，二项队列中至多存在一棵 B(k)的二项树。
 *
 * 3）B(k)是由 B(0)、B(1)、B(2)....B(k-1)组成的。B(0)是一棵单节点(2^0 = 1)的树，B(k)中含有 2^k 个结点。
 *
 * 高度为 k 的二项树B(k)通过将一棵二项树B(k-1)附到另一棵二项树B(k-1)的根上构成。而B(k-1)又可以由B(k-2)附到另一棵B(k-2)的二项树上，故正如上面提到，B(k)是由 B(0)、B(1)、B(2)....B(k-1)组成的。
 *
 * 4）具有N个结点的二项队列最多有 logN 棵二项树。因为，每棵二项树B(k)中含有2^k个结点。
 *
 * 故：2^0 + 2^1 + 2^2 + .... + 2^k = N，得到 k=logN。k为树的棵数。
 *
 * 注意到上面提到的是“最多” 有logN 棵二项树，这说明一个二项队列可以缺少某棵 B(i) , 0<=i<=k
 *
 * 5）由二项树中结点个数的性质(B(k)有2^k个结点)，而二项队列又是若干二项树的集合，故二项队列可以采用二进制数来标记：
 *
 * 如，大小为13(共有13个结点)的二项队列可以用森林 B(3)、B(2)、B(0)表示，并可以把这种表示写成 1101，1101以二进制形式表示13，而且还表示了该二项队列中，不存在B(1)这样的树。
 *
 * 介绍了二项队列的性质或者说逻辑结构，现在介绍下二项队列的存储结构。
 *
 * 二项队列是在内在中如何存储的呢？（从网上找到一张图如下：）
 *
 *
 *
 * 首先是需要一个一维数组。该数组中的每个元素存储一棵二项树的根结点指针。比如，最右边的那个数组元素存储了一颗高度为0的二项树B(0)。B(0)只有一个结点，该结点的权值为13。如果数组的长度表示二进制的位数，那么这个二项队列表示为 00001101
 *
 * 这说明该二项队列不存在高度为7、6、5、4、1 这样的二项树：B(7)、B(６)、B(５)、B(４)、B(1)
 *
 * 此外，还可以看出：
 *
 * ①数组大小为二项树的数目乘2加1，或者说二项树的数目是数组的长度除以2再减去1。二项树在数组中存储是按高度排序的。
 *
 * ②数组第 i 号索引处，存储的是高度为 i 的二项树。如，第0号索引，存储高度为0的二项树，该二项树只有一个结点，结点权值为13
 *
 * 除了需要一维数组存储各棵树的根结点外，当然还需要保存各棵二项树了，二项树的采用的是链表 表示，这里采用的是“左孩子右兄弟”表示法。
 *
 * 因此，二项队列的实现类的结构如下：
 * @Author zt648
 * @Date 2019/5/29 11:49
 * @Version 1.0
 */

public class BinomialQueue<T extends Comparable<? super T>> {

    private static final int DEFAULT_TREES = 1;
    private Node<T>[] theTrees;

    private int currentSize;


    public BinomialQueue() {
        theTrees = new Node[DEFAULT_TREES];
        makeEmpty();
    }

    private void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theTrees.length; i++) {
            theTrees[i] = null;
        }
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
        //merge(new BinomialQueue<>());
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
     * @description 合并两个具有相同大小的树, 让t2成为t1的左儿子，前提是t2的根
     * 大于t1
     * @date 12:24 2019/5/29
     * @param t1,t2
     * @return com.heap.BinomialQueue.Node<T>
     * @throw
     **/
    private Node<T> combineTrees(Node<T> t1, Node<T> t2) {
        if(t2.element.compareTo(t1.element) > 0){
            return combineTrees(t2,t1);
        }
        t2.nextSibing = t1.leftFirstChild;
        t1.leftFirstChild = t2;
        return t1;
    }

    /**
     * @return void
     * @Author zt648
     * @Description <p>
     * h1是当前树，h2是rhs，将h1和h2合并，并清空h2
     * </p>
     * <p>
     * t1和t2是h1和h2的树，再任意时刻，我们处理的是高度为i的树，carry是在上次循环中得到的树，高度一定是[i+1]
     * 这一共有8中情况
     * </p>
     * @Date 11:51 2019/6/5
     * @Param [rhs]
     * @throw
     **/
    public void merge(BinomialQueue<T> rhs) {
        if (this == rhs) {
            return;
        }

        currentSize += rhs.currentSize;
        //合并后最多使树的长度+1，原因便是下一步的carry树导致的
        if (currentSize > capacity()) {
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }

        Node<T> carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            Node<T> t1 = theTrees[i];
            Node<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;
            //0，1,2,4排列，覆盖了8种情况
            //theTree相同索引储存相同大小的树
            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;
            switch (whichCase) {
                case 0:

                case 1:
                    break;
                case 2:
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4:
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3:
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5:
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6:
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7:
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
            for (int i1 = 0; i1 < rhs.theTrees.length; i1++) {
                rhs.theTrees[i1] = null;
            }
            rhs.currentSize = 0;

        }

    }

    public int getCurrentSize() {
        return currentSize;
    }

    /**
     * @return T
     * @Author zt648
     * @Description 删除操作
     * @Date 14:05 2019/6/5
     * @Param []
     * @throw
     **/
    public T deleteMin() {
        if (isEmpty()) {
            throw new NullPointerException();
        }

        int min = findMinIndex();
        T deleteItem = theTrees[min].element;

        Node<T> deleteTree = theTrees[min].leftFirstChild;

        BinomialQueue<T> deleteQuene = new BinomialQueue<>();

        deleteQuene.expandTheTrees(min + 1);

        deleteQuene.currentSize = (1 << min) - 1;

        //删除B(i)的根，得到若干棵二项树:B(0)、B(1)...B(i-1)。这些二项树组成一个新的二项队列 H''
        //他的第一个左儿子一定比他的大小小1，他的第一个左儿子的右兄弟节点，又会比他小一，所以index--
        for (int j = min - 1; j > 0; j--) {
            deleteQuene.theTrees[j] = deleteTree;
            deleteTree = deleteTree.nextSibing;
            deleteQuene.theTrees[j].nextSibing = null;


        }


        theTrees[min] = null;

        //为什么＋1，因为不只是原本deleteQuene的树，还有min那个元素
        currentSize -= deleteQuene.currentSize + 1;

        merge(deleteQuene);
        return deleteItem;
    }

    private int findMinIndex() {
        T value = theTrees[0].element;
        int index = 0;
        for(int i=1;i<theTrees.length;i++){
            if(theTrees[i].element.compareTo(value) < 0){
                index = i;
                value = theTrees[i].element;
            }

        }
        return index;
    }

//public

    /**
     * @return void
     * @Author zt648
     * @Description 扩展森林
     * @Date 11:36 2019/6/5
     * @Param [newNumTrees]
     * @throw
     **/
    private void expandTheTrees(int newNumTrees) {
        Utils.copyArray(theTrees, newNumTrees);
    }

    public void insert(T value) {
        merge(new BinomialQueue<>(value));
    }

    //public void setCurrentSize(int currentSize) {
    //    this.currentSize = currentSize;
    //}
}
