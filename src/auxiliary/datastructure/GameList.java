/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliary.datastructure;

/**
 *
 * @author christian
 */
public class GameList<T> {
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;
    private int length;

    public GameList() {
    }
    
    
    public boolean add(T data)
    {
        try
        {
            Node<T> newLast = new Node<>(data, last);
            last.setNext(newLast);
            last = newLast;
            length++;
        }
        catch(OutOfMemoryError e)
        {
            return false;
        }
        return true;
    }
    
    public void posFirst()
    {
        current = first;
    }
    
    public void posEnd()
    {
        current = last;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public T getAndAdvance()
    {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
    
    public T getAndRegress()
    {
        T data = current.getData();
        current = current.getPrevious();
        return data;
    }
}
