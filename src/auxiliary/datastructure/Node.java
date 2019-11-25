/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary.datastructure;

/**
 *
 * @author christian
 * @param <T> : data containing in the gamelist
 */
public class Node<T> {
    private T data;
    private Node<T> previous;
    private Node<T> next;
    int value;

    Node(T data) {
        this.data = data;
    }

    Node(T data,Node<T> previous) {
        this.data = data;
        this.previous = previous;
    }
   
    void setNext(Node<T> next)
    {
        this.next = next;
    }
    
    T getData() {
        return data;
    }
    
    Node<T> getNext()
    {
        return next;
    }
    
    Node<T> getPrevious()
    {
        return previous;
    }
    
}
