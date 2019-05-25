package com.tree;

import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryNode<T> {
        private BinaryNode<T> left;
        private BinaryNode<T> right;
        private T element;

        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contain(T x) {
        return contain(x, root);
    }

    public T findMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        return findMin(root).element;
    }

    public T finfMax() {
        if (!isEmpty())
            throw new NoSuchElementException();
        return findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {

    }
    public void priTraverse(){
        preRraverse(root);
    }
    public void midTraverse(){
        midRraverse(root);
    }
    public void nextTraverse(){
        nextRraverse(root);
    }

    /**
     * 删除元素由三种可能
     * 这个节点是个叶子，直接删除
     * 一种是该节点只有一个儿子，则直接让该节点的父亲的儿子变成该节点的左儿子
     * 另一种是该节点由两个儿子，则做法是在给该节点的右子树寻找最小的节点，并让最小节点的元素代替该节点的元素，
     * 这个最小节点一定没有左子树，再将这个最小的节点删除
     * @param x
     * @param root
     * @return 返回子树的新跟节点
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> root) {
        if(root == null)
            return new BinaryNode<>(x,null,null);
        int compareResult = x.compareTo(root.element);
        if(compareResult < 0)
            root.left = remove(x,root.left);
        else if(compareResult > 0)
            root.right = remove(x,root.right);
        else if(root.right != null && root.left != null){
            root.element = findMin(root.right).element;
            root.right = remove(root.element,root.right);
        }else
            root = (root.left != null)?root.left:root.right;
        return root;
    }

    /**
     * 最大元素一定在右子树上，所以对右子树进行递归
     *
     * @param root 子树的根节点
     * @return 最大的节点
     */
    private BinaryNode<T> findMax(BinaryNode<T> root) {
        //使用尾递归
        if (root == null)
            return null;
        else if (root.right == null)
            return root;
        return findMax(root.right);
    }

    /**
     * 是用while循环找到最小节点
     *
     * @param root
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> root) {
        if (root != null)
            while (root.left != null) {
                root = root.left;
            }
        return root;
    }

    /**
     * 寻找子树中的一个元素
     *
     * @param x    要寻找的元素
     * @param root 子树的根节点，一般是从整个树的根节点开始
     * @return true if the item is found;false otherwise
     */
    private boolean contain(T x, BinaryNode<T> root) {
        if (root == null)
            return false;
        int compareResult = x.compareTo(root.element);
        //结果大于零，在右子树，反之在左子树
        if (compareResult > 0)
            return contain(x, root.right);
        else if (compareResult < 0)
            return contain(x, root.left);
        else
            return true;
    }

    /**
     * 要插入的元素一定位于树的叶处，创建BinaryNode<>(x,null,null);
     * root.left = insert(x, root.left);root.right = insert(x,root.right);
     * 对左右子树的根节点进行递归更新。
     * @param x
     * @param root
     * @return 返回子树的新的根节点
     */

    private BinaryNode<T> insert(T x, BinaryNode<T> root) {
        if (root == null)
            return new BinaryNode<>(x,null,null);
        int compareResult = x.compareTo(root.element);
        if (compareResult < 0)
            root.left = insert(x, root.left);
        else if (compareResult > 0)
            root.right = insert(x,root.right);
        return root;
    }

    /**
     * 前序遍历
     * @param root
     */
    private void preRraverse(BinaryNode root){
        if(root != null){
            System.out.println(root.element);
            preRraverse(root.left);
            preRraverse(root.right);
        }
    }
    private void midRraverse(BinaryNode root){
        if(root != null){
            midRraverse(root.left);
            System.out.println(root.element);
            midRraverse(root.right);
        }
    }
    private void nextRraverse(BinaryNode root){
        if(root != null){
            nextRraverse(root.left);
            nextRraverse(root.right);
            System.out.println(root.element);
        }
    }
}