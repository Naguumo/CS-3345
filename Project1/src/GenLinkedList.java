import java.util.ArrayList;
import java.util.List;

public class GenLinkedList<E>
{
    //Pointers for List
    private GenNode<E> head;
    private GenNode<E> tail;
    private int len;
    
    public static void main(String[] args)
    {
        GenLinkedList<Integer> arr = new GenLinkedList<>((int)(Math.random()*100));    //Instantiates List
        System.out.println("initial: \t\t" + arr.toString());
        
        int t1 = (int)(Math.random()*100);
        arr.addFront(t1);  //Demonstrate addFront
        System.out.println("addFront(" + t1 + "): \t\t" + arr.toString());
        
        int t2 = (int)(Math.random()*100);
        arr.addEnd(t2);  //Demonstrate addEnd
        System.out.println("addEnd(" + t2 + "): \t\t" + arr.toString());
        
        for(int i = 0; i < (int)(Math.random()*20); i++)
        {
            arr.addEnd((int)(Math.random()*100));
        }
        System.out.println("fill array: \t\t" + arr.toString());
        
        arr.removeFront();  //Demonstrate removeFront
        System.out.println("removeFront(): \t\t" + arr.toString());
        
        arr.removeEnd();    //Demonstrate removeEnd
        System.out.println("removeEnd(): \t\t" + arr.toString());
        
        int t3 = (int)(Math.random()*10);
        int t4 = (int)(Math.random()*100);
        arr.set(t3, t4);    //Demonstrate set
        System.out.println("set(" + t3 + ", " + t4 + "): \t\t" + arr.toString());
        
        int t5 = (int)(Math.random()*5);
        System.out.println("get(" + t5 + "): \t\t" + arr.get(t5));    //Demonstrate get
        
        int t6 = (int)(Math.random()*5);
        int t7 = (int)(Math.random()*5);
        arr.swap(t6, t7);   //Demonstrate swap
        System.out.println("swap(" + t6 + ", " + t7 + "): \t\t" + arr.toString());
        
        int t8 = (int)(Math.random()*20)-10;
        arr.shift(t8);  //Demonstrate shift
        System.out.println("shift(" + t8 + "): \t\t" + arr.toString());
        
        arr.removeMatching(t2); //Demonstrate removeMatching
        System.out.println("removeMatching(" + t2 + "): \t" + arr.toString());
        
        int t9 = (int)(Math.random()*5);
        int t10 = (int)(Math.random()*5)+1;
        arr.erase(t9, t10);  //Demonstrate erase
        System.out.println("erase(" + t9 + ", " + t10 + "): \t\t" + arr.toString());
        
        ArrayList<Integer> randarr = new ArrayList<>();
        for(int i = 0; i < (int)(Math.random()*10)+1; i++)
            randarr.add((int)(Math.random()*100));
        System.out.println("random array: \t\t" + randarr.toString());
        
        int t11 = (int)(Math.random()*5);
        arr.insertList(t11, randarr);   //Demonstrate insertList
        System.out.println("insertList(" + t11 + ", randarr):\t" + arr.toString());
    }
    
    //Empty Constructor
    public GenLinkedList()
    {
        head = tail = null;
        len = 0;
    }
    //1 item Constructor
    public GenLinkedList(E item)
    {
        head = tail = new GenNode<>(item);
        len = 1;
    }
    
    //Adds to the front of the list
    public void addFront(E item)
    {
        head = new GenNode<>(item, head);
        if(tail == null)
            tail = head;
        len++;
    }
    
    //Adds to the end of the list
    public void addEnd(E item)
    {
        if(head == null)
            head = tail = new GenNode<>(item);
        else
        {
            tail.next = new GenNode<>(item);
            tail = tail.next;
        }
        len++;
    }
    
    //Removes a node from the front of the list
    public E removeFront()
    {
        E node = null;
        if(head != null)
        {
            node = head.item;
            if(head == tail)
                head = tail = null;
            else
                head = head.next;
            len--;
        }
        return node;
    }
    
    //Removes a node from the end of the list
    public E removeEnd()
    {
        E node = null;
        if(head != null)
        {
            if(head == tail)
            {
                node = head.item;
                head = tail = null;
            }
            else
            {
                node = tail.item;
                        
                //Get to 2nd Last of List
                GenNode<E> iterator = head;
                while(iterator.next != tail)
                    iterator = iterator.next;

                //Remove link to last node
                iterator.next = null;
                tail = iterator;
            }
            len--;    
        }
        return node;
    }
    
    //Sets the element at given position, provided it is within the current size
    public void set(int pos, E item)
    {
        if(pos < len)
            getNode(pos).item = item;
    }
    
    //Returns the element at given position, provided it is within the current size
    public E get(int pos)
    {
        return (pos >= len) ? null : getNode(pos).item;
    }
    
    private GenNode<E> getNode(int pos)
    {
        if(pos >= len || head == null)
            return null;
        
        GenNode<E> iterator = head;
        for(int i = 0; i < pos; i++)
        {
            if(iterator == tail)
                return null;
            iterator = iterator.next;
        }
        return iterator;
    }
    
    //Swaps the Nodes at given positions, provided both are within the current size
    public void swap(int pos1, int pos2)
    {
        if(pos1 >= len || pos2 >= len)
            return;
        
        GenNode<E> iterator1 = getNode(pos1);
        GenNode<E> iterator2 = getNode(pos2);
        
        //Ensure both are valid locations
        if(iterator1 == null || iterator2 == null)
            return;
        
        //Swap contents around
        E tempitem = iterator2.item;
        iterator2.item = iterator1.item;
        iterator1.item = tempitem;
    }
    
    //Shifts the list forward or backward the given number of nodes, provided it is within the current size
    public void shift(int num)
    {
        if(head == null || head == tail)
            return;
        
        //Shifting Upwards
        if(num > 0)
            for(int i = 0; i < num; i++)
                this.addFront(this.removeEnd());
        //Shifting Downwards
        else if(num < 0)
            for(int i = 0; i > num; i--)
                this.addEnd(this.removeFront());
    }
    
    //removes all occurences of the given value from the list
    public void removeMatching(E item)
    {
        GenNode<E> prev = null;
        GenNode<E> iterator = head;
        
        //Iterate through list
        while(iterator != null)
        {
            //Remove matching item
            if(iterator.item == item)
            {
                if(prev == null)
                    removeFront();
                else if(iterator.next == tail)
                    removeEnd();
                else
                {
                    prev.next = iterator.next;
                    len--;
                }
            }
            prev = iterator;
            iterator = iterator.next;
        }
    }
    
    //removes elements starting at the given position and spanning for given amount of elements
    public void erase(int pos, int elements)
    {
        if(pos >= len)
            return;
        
        //case of head
        if(pos == 0)
        {
            for(int i = 0; i < elements; i++)
                this.removeFront();
        }
        //Case of tail
        else if(pos+elements >= len)
        {
            for(int i = 0; i < elements; i++)
                this.removeEnd();
        }
        else
        {
            GenNode<E> begin = getNode(pos-1);
            GenNode<E> end = getNode(pos+elements);
            begin.next = end;
            len -= elements;
        }
    }
    
    //copies each value of the given list into the current list at the given position
    public void insertList(int pos, List<E> list)
    {
        if(head == null || list.isEmpty() || pos > len)
            return;
        
        //Case of head
        if(pos == 0)
        {
            for(int i = list.size()-1; i >= 0; i--)
                addFront(list.get(i));
        }
        //Case of tail
        else if(pos == len)
        {
            for(int i = 0; i < list.size(); i++)
                addEnd(list.get(i));
        }
        else
        {
            GenNode<E> iterator = getNode(pos-1);
            GenNode<E> nend = iterator.next;

            //Iterate through new Nodes
            for(int i = 0; i < list.size(); i++)
            {
                iterator.next = new GenNode<>(list.get(i));
                len++;
                iterator = iterator.next;
            }

            //Connect to rest of list
            iterator.next = nend;
        }
    }
    
    public int size()
    {
        return len;
    }
    
    @Override
    public String toString()
    {
        if(head == null)
            return "[ ]";
        
        String out = "[" + head.item;
        GenNode<E> iterator = head;
        while(iterator.next != null)
        {
            out += ", ";
            iterator = iterator.next;
            out += iterator.item;
        }
        return out + "]";
    }
}

//Basic Node Subclass
class GenNode<E>
{
    E item;
    GenNode next;

    GenNode(E item)
    {
        this.item = item;
    }
    GenNode(E item, GenNode<E> next)
    {
        this.item = item;
        this.next = next;
    }
}