package com.list;

public class Quene<T> {
    private QueneNode<T> begin;
    private QueneNode<T> end;
    private int size;
    public Quene(){
        doClear();
    }
    public int Size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public T pop(){
        QueneNode node = getPop();
        T value = (T)node.value;
        return value;
    }
    public T Last(){
        QueneNode<T> node = getLast();
        return node.value;
    }
    public void getIn(T value){
        if(value!=null){
        QueneNode<T> node = new QueneNode<>(value,null);
        getIn(node);
        }
    }
    private QueneNode<T> getLast(){
        QueneNode<T> node1;
        if(Size()>0){
            node1 = begin.next;
            while(!node1.next.equals(end)){
                node1 = node1.next;
            }
            return node1;
        }else{
            return null;
        }

    }
    private void doClear(){
        size = 0;
        begin = new QueneNode<>(null,end);
        end = new QueneNode<>(null,null);
        begin.next = end;
    }

    private QueneNode<T> getPop(){
        if(Size()<=0){
            throw new ArrayIndexOutOfBoundsException();
        }
        QueneNode<T> node = begin.next;
        begin.next = begin.next.next;
        size--;

        return node;
    }
    private class QueneNode<T>{
        T value;
        QueneNode<T> next;
        QueneNode(T value,QueneNode<T> next){
            this.value = value;
            this.next = next;
        }
    }
    private QueneNode<T> getIn(QueneNode<T> node){
//        QueneNode<T> lastNode;
        QueneNode<T> last = getLast();
        if(last == null){
            begin.next = node;
            node.next = end;
        }else {
            last.next = node;
            node.next = end;
        }
        size++;
        return node;
    }

    public static void main(String[] args) {
        Quene<Integer> quene = new Quene<>();
        quene.getIn(1);
        quene.getIn(2);
        quene.getIn(3);
        System.out.println(quene.pop());
        System.out.println(quene.size);
        quene.getIn(12);
        System.out.println(quene.Last());
        System.out.println(quene.size);
    }
}
