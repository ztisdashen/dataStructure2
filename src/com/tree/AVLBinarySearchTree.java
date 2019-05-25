package com.tree;

import java.util.NoSuchElementException;

public class AVLBinarySearchTree<T extends Comparable<? super T>> {

    private static final int ALLOW_IMBANLANCE = 1;
    private AvlNode<T> root;

    private static class AvlNode<T> {
        T element;
        AvlNode<T> left, right;
        int height;

        AvlNode(T theElement) {
            this(theElement, null, null);

        }

        AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
        }
    }
    AVLBinarySearchTree(){
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

    public void priTraverse(){
        preRraverse(root);
    }
    public void midTraverse(){
        midRraverse(root);
    }
    public void nextTraverse(){
        nextRraverse(root);
    }
    public T findMax(){
        if(isEmpty())
            throw new NoSuchElementException();
        return findMax(root).element;
    }

    public T findMin(){
        if(isEmpty())
            throw new NoSuchElementException();
        return findMin(root).element;
    }
    /**
     * 要插入的元素一定位于树的叶处，创建BinaryNode<>(x,null,null);
     * root.left = insert(x, root.left);root.right = insert(x,root.right);
     * 对左右子树的根节点进行递归更新。
     * 并对新的根节点进行平恒操作
     * @param x
     * @param root
     * @return 返回子树的新的根节点
     */


    public AvlNode<T> insert(T x,AvlNode<T> root){
        if(root == null)
            return new AvlNode<>(x,null,null);
        int compareResult = x.compareTo(root.element);
        if(compareResult < 0)
            root.left = insert(x,root.left);
        else if(compareResult > 0)
            root.right = insert(x,root.right);
        return balance(root);

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

    public AvlNode<T> remove(T x,AvlNode<T> root){
        if(root == null)
            return new AvlNode<>(x,null,null);
        int compareResult = x.compareTo(root.element);
        if(compareResult < 0)
            root.left = remove(x,root.left);
        else if(compareResult > 0)
            root.right = remove(x,root.right);
        else if(root.right!=null && root.left != null){
            root.element = findMax(root.right).element;
            root.right = remove(root.element,root.right);
        }else
            root = (root.left != null) ? root.left:root.right;
        return balance(root);

    }
    private AvlNode<T> findMin(AvlNode<T> root) {
        if(root == null)
            return null;
        else if(root.left == null)
            return root;
        return findMin(root.left);
    }

    private AvlNode<T> findMax(AvlNode<T> root) {
        if(root == null)
            return null;
        else if(root.right == null)
            return root;
        return findMax(root.right);
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null)
            return t;
        if (height(t.left) - height(t.right) > ALLOW_IMBANLANCE) {
            //左侧的左子树大于左侧的右子树，说明是再左侧添加了新的元素
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        } else {
            if(height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        }
        t.height = Math.max(height(t.right),height(t.left)) + 1;
        return t;
    }

    /**
     * 单旋转之右旋转，将k2的左儿子k1变成这个子树根节点返回，
     * 并将左儿子k1的右子树变成k2的左子树
     * 更新k1,k2的高度，返回新的根节点
     *
     * @param k2 根节点
     * @return 新的根节点
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), height(k1.left)) + 1;
        return k1;
    }

    /**
     * 与上面相反
     *
     * @param k2
     * @return
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), height(k1.left)) + 1;
        return k1;
    }

    /**
     * 双旋转之左侧，进行两次相反的单旋转
     * 将k3的左儿子k1的右儿子k2通过右侧单旋转变成k3的左儿子
     * 再通过左侧单旋转让k3变成k2的右儿子。
     *
     * @param k3
     * @return 返回新的根节点
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }
    /**
     * 寻找子树中的一个元素
     *
     * @param x    要寻找的元素
     * @param root 子树的根节点，一般是从整个树的根节点开始
     * @return true if the item is found;false otherwise
     */
    private boolean contain(T x, AvlNode<T> root) {
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
     * 前序遍历
     * @param root
     */
    private void preRraverse(AvlNode root){
        if(root != null){
            System.out.println(root.element);
            preRraverse(root.left);
            preRraverse(root.right);
        }
    }
    private void midRraverse(AvlNode root){
        if(root != null){
            midRraverse(root.left);
            System.out.println(root.element);
            midRraverse(root.right);
        }
    }
    private void nextRraverse(AvlNode root){
        if(root != null){
            nextRraverse(root.left);
            nextRraverse(root.right);
            System.out.println(root.element);
        }
    }
}

