package lepetit.com.calculate;

/**
 * Created by LePetit on 2018/3/7.
 */

public class Stack {
    private Node top = null;
    private int size = 0;

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(Object data){
        top = new Node(data, top);
        size++;
    }

    public void pop(){
        Node temp = top;
        top = top.next;
        temp.next = null;
        size--;
    }

    public Object getTop(){
        return top.data;
    }

    public int getSize(){
        return size;
    }
}

class Node{
    public Object data;
    public Node next;

    public Node(){}

    public Node(Object data, Node next){
        this.data = data;
        this.next = next;
    }
}
