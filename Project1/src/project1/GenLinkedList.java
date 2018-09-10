package project1;

import java.util.List;

public class GenLinkedList<E>
{
    private GenNode<E> root;
    
    public static void main(String[] args)
    {
        GenLinkedList<Integer> arr = new GenLinkedList<>((int)(Math.random()*100));    //Instantiates List
        System.out.println("initial: \t" + arr.toString());
        
        arr.addFront((int)(Math.random()*100));  //Demonstrate addFront
        System.out.println("addFront(): \t" + arr.toString());
        
        arr.addEnd((int)(Math.random()*100));  //Demonstrate addEnd
        System.out.println("addEnd(): \t" + arr.toString());
        
        for(int i = 0; i < (int)(Math.random()*20); i++)
        {
            arr.addEnd((int)(Math.random()*100));
        }
        System.out.println("fill array: \t" + arr.toString());
        
        arr.removeFront();  //Demonstrate removeFront
        System.out.println("removeFront(): \t" + arr.toString());
        
        arr.removeEnd();    //Demonstrate removeEnd
        System.out.println("removeEnd(): \t" + arr.toString());
        
        int t1 = (int)(Math.random()*10);
        int t2 = (int)(Math.random()*100);
        arr.set(t1, t2);    //Demonstrate set
        System.out.println("set(" + t1 + ", " + t2 + "): \t" + arr.toString());
        
        int t3 = (int)(Math.random()*10);
        System.out.println("get(" + t3 + "): \t" + arr.get(t1));    //Demonstrate get
        
        int t4 = (int)(Math.random()*10);
        int t5 = (int)(Math.random()*10);
        arr.swap(t4, t5);   //Demonstrate swap
        System.out.println("swap(" + t4 + ", " + t5 + "): \t" + arr.toString());
        
        int t6 = (int)(Math.random()*20)-10;
        arr.shift(t6);  //Demonstrate shift
        System.out.println("shift(" + t6 + "): \t" + arr.toString());
        
        int t7 = (int)(Math.random()*100);
        arr.removeMatching(t7); //Demonstrate removeMatching
        System.out.println("remMatch(" + t7 + "): \t" + arr.toString());
        
        int t8 = (int)(Math.random()*10);
        int t9 = (int)(Math.random()*5);
        arr.erase(t8, t9);  //Demonstrate erase
        System.out.println("erase(" + t8 + ", " + t9 + "): \t" + arr.toString());
        
        List<Integer> jlist = new List<>();
    }
    
    public GenLinkedList(E item)
    {
        root = new GenNode<>(item);
    }
    
    public void addFront(E item)
    {
        //Adds to the front of the list
        GenNode<E> nitem = new GenNode<>(item);
        nitem.next = root;
        root = nitem;
    }
    
    public void addEnd(E item)
    {
        //Adds to the end of the list
        GenNode<E> nitem = new GenNode<>(item);
        GenNode<E> iterator = root;
        while(iterator.next != null)
            iterator = iterator.next;
        iterator.next = nitem;
    }
    
    public void removeFront()
    {
        //Removes a node from the front of the list
        root = root.next;
    }
    
    public void removeEnd()
    {
        //Removes a node from the end of the list
        GenNode<E> iterator = root;
        while(iterator.next.next != null)
            iterator = iterator.next;
        iterator.next = null;
    }
    
    public void set(int pos, E item)
    {
        //Sets the element at given position, provided it is within the current size
        GenNode<E> nitem = new GenNode<>(item);
        if(pos == 0)
        {
            nitem.next = root;
            root = nitem;
            return;
        }
        
        //Acquire Position Node
        GenNode<E> iterator = root;
        for(int i = 1; i < pos; i++)
        {
            if(iterator.next == null)
                return;
            iterator = iterator.next;
        }
        
        //Shift Positions of previously placed Nodes
        GenNode<E> pitem = iterator.next;
        iterator.next = nitem;
        nitem.next = pitem;
    }
    
    public E get(int pos)
    {
        //Returns the element at given position, provided it is within the current size
        GenNode<E> iterator = root;
        for(int i = 0; i < pos; i++)
        {
            if(iterator.next == null)
                return null;
            iterator = iterator.next;
        }
        return iterator.item;
    }
    
    public void swap(int pos1, int pos2)
    {
        //Swaps the Nodes at given positions, provided both are within the current size
    }
    
    public void shift(int num)
    {
        //Shifts the list forward or backward the given number of nodes, provided it is within the current size
    }
    
    public void removeMatching(E item)
    {
        //A
    }
    
    public void erase(int pos, int elements)
    {
        //A
    }
    
    public void insertList(int pos, List<E> list)
    {
        //A
    }
    
    @Override
    public String toString()
    {
        String out = root.item + " ";
        GenNode<E> iterator = root;
        while(iterator.next != null)
        {
            iterator = iterator.next;
            out += iterator.item + " ";
        }
        return out;
    }
}

class GenNode<E>
{
    E item;
    GenNode next;

    public GenNode(E item)
    {
        this.item = item;
    }
}
