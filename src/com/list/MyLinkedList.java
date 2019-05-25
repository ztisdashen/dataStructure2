package com.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable {

    private int theSize;
    private int modCount = 0;
    private Node<T> beginMaker;
    private Node<T> endMaker;

    private static class Node<T> {
        public T val;
        public Node<T> prev, next;

        /**
         * @param val  节点要储存的元素
         * @param prev 上一个节点
         * @param next 下一个节点
         */
        public Node(T val, Node<T> prev, Node<T> next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T x) {
        add(size(), x);
        return true;
    }

    public void add(int index, T x) {
        addBefore(getNode(index, 0, size()), x);
    }

    public T get(int index) {
        return getNode(index).val;
    }

    public T set(int index, T newVal) {
        Node<T> p = getNode(index);
        T olaVal = p.val;
        p.val = newVal;
        return olaVal;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 在特定的节点p在这个集合中添加一个新的元素
     * 原来这个位置的节点以及这个节点之后的节点将会后移
     *
     * @param p 要添加的节点 Node to add before
     * @param x 任何一个Object
     * @throws IndexOutOfBoundsException 吐过index不在0和size()之间
     */
    private void addBefore(Node<T> p, T x) {
        //断掉p.prev和p之间的链，将新的Node添加上去
        Node<T> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    private T remove(Node<T> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.val;
    }

    /**
     * 得到在这个位置index的节点，index必须在0到size()-1之间
     *
     * @param index 要查找的索引
     * @return 返回符合这个索引的节点
     * @throws IndexOutOfBoundsException
     */
    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    /**
     * 返回在index位置的节点，index应该在low和high之间
     *
     * @param index 要查找的索引
     * @param low   最低的位置
     * @param high  最高的位置
     * @return 返回符合这个索引的节点
     * @throws IndexOutOfBoundsException
     */
    private Node<T> getNode(int index, int low, int high) {
        Node<T> p;
        if (index < low || index > high)
            throw new IndexOutOfBoundsException();
        //使用二分查找BinarySearch
        if (index < size() / 2) {
            p = beginMaker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            //因为是是用的endMaker，所以从size()而不是size()-1开始
            p = endMaker;
            for (int i = size(); i > index; i--)
                p = p.prev;
        }
        return p;
    }

    private void doClear() {
        beginMaker = new Node<>(null, null, null);
        endMaker = new Node<>(null, null, null);
        beginMaker.next = endMaker;
        theSize = 0;
        modCount++;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {
        private Node<T> current = beginMaker.next;
        //expectedModcount确保在使用迭代器期间，没有对MyLinkedList进行操作
        private int expectedModCount = modCount;
        //确保是在next()执行remove
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMaker;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            T nextItem = current.val;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }

    }
}
