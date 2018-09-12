import java.util.ArrayList;
import java.util.List;

public class GenLinkedList<E>
{
    //Pointer for Head of List
    private GenNode<E> root;
    
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
    
    public GenLinkedList(E item)
    {
        root = new GenNode<>(item);
    }
    
    //Adds to the front of the list
    public void addFront(E item)
    {
        GenNode<E> nitem = new GenNode<>(item, root);
        root = nitem;
    }
    
    //Adds to the end of the list
    public void addEnd(E item)
    {
       getLast().next = new GenNode<>(item);
    }
    
    //Removes a node from the front of the list
    public void removeFront()
    {
        if(root != null)
            root = root.next;
    }
    
    //Removes a node from the end of the list
    public void removeEnd()
    {
        //Get to 2nd Last of List
        GenNode<E> iterator = root;
        while(iterator.next.next != null)
            iterator = iterator.next;
        
        //Remove link to last node
        iterator.next = null;
    }
    
    //Sets the element at given position, provided it is within the current size
    public void set(int pos, E item)
    {
        GenNode<E> iterator = getNode(pos);
        //Change contents of node
        if(iterator != null)
            iterator.item = item;
    }
    
    //Returns the element at given position, provided it is within the current size
    public E get(int pos)
    {
        GenNode<E> iterator = getNode(pos);  
        //Fetch contents of node
        if(iterator != null)
            return iterator.item;
        return null;
    }
    
    private GenNode<E> getNode(int pos)
    {
        GenNode<E> iterator = root;
        for(int i = 0; i < pos; i++)
        {
            if(iterator.next == null)
                return null;
            iterator = iterator.next;
        }
        return iterator;
    }
    
    private GenNode<E> getLast()
    {
        GenNode<E> iterator = root;
        while(iterator.next != null)
            iterator = iterator.next;
        return iterator;
    }
    
    //Swaps the Nodes at given positions, provided both are within the current size
    public void swap(int pos1, int pos2)
    {
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
        if(root == null || root.next == null)
            return;
        
        //Shifting Upwards
        if(num > 0)
            for(int i = 0; i < num; i++)
            {
                //Get to 2nd Last of List
                GenNode<E> iterator = root;
                while(iterator.next.next != null)
                    iterator = iterator.next;
                
                GenNode<E> temp = iterator.next;
                iterator.next = null;
                temp.next = root;
                root = temp;
            }
        //Shifting Downwards
        else if(num < 0)
            for(int i = 0; i > num; i--)
            {
                GenNode<E> temp = root;
                root = root.next;
                temp.next = null;
                getLast().next = temp;
            }
    }
    
    //removes all occurences of the given value from the list
    public void removeMatching(E item)
    {
        GenNode<E> prev = null;
        GenNode<E> iterator = root;
        
        //Iterate through list
        while(iterator != null)
        {
            //Remove matching item
            if(iterator.item == item)
            {
                if(prev == null)
                    root = iterator.next;
                else
                    prev.next = iterator.next;
            }
            prev = iterator;
            iterator = iterator.next;
        }
    }
    
    //removes elements starting at the given position and spanning for given amount of elements
    public void erase(int pos, int elements)
    {
        //Special case of root
        if(pos == 0)
        {
            GenNode<E> nroot = root;
            for(int i = 0; i < elements; i++)
            {
                if(nroot.next == null)
                    break;
                nroot = nroot.next;
            }
            root = nroot;
            return;
        }
        
        //Iterate to position
        GenNode<E> iterator = getNode(pos-1);
        if(iterator == null)
            return;
        GenNode<E> parent = iterator;
        
        //Iterate through erased nodes
        for(int i = 0; i <= elements; i++)
        {
            if(iterator.next == null)
            {
                iterator = null;
                break;
            }
            iterator = iterator.next;
        }
        
        //Set as next to skip deleted nodes
        parent.next = iterator;
    }
    
    //copies each value of the given list into the current list at the given position
    public void insertList(int pos, List<E> list)
    {
        if(root == null || list.isEmpty())
            return;
        
        GenNode<E> iterator;
        
        //Special case of root
        if(pos == 0)
        {
            GenNode<E> oroot = root;
            root = new GenNode<>(list.get(0));
            iterator = root;
            for(int i = 1; i < list.size(); i++)
            {
                iterator.next = new GenNode<>(list.get(i));
                iterator = iterator.next;
            }
            iterator.next = oroot;
            return;
        }
        
        iterator = getNode(pos-1);
        if(iterator == null)
            return;
        GenNode<E> nend = iterator.next;
        
        //Iterate through new Nodes
        for(int i = 0; i < list.size(); i++)
        {
            iterator.next = new GenNode<>(list.get(i));
            iterator = iterator.next;
        }
        
        //Connect to rest of list
        iterator.next = nend;
    }
    
    @Override
    public String toString()
    {
        if(root == null)
            return "[ ]";
        
        String out = "[" + root.item;
        GenNode<E> iterator = root;
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
