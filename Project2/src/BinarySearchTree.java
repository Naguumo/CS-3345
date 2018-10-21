import java.util.LinkedList;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    private BinaryNode<AnyType> root;
    
    public static void main(String[] args)
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        final int NUMS = (int)(Math.random()*5)+3;
        for(int i = 0; i < NUMS; i++)
            t.insert((int)(Math.random()*100));
        System.out.println("t = ");
        t.printLevels();
        
        System.out.println("t.nodeCount() = " + t.nodeCount());
        
        System.out.println("t.isFull() = " + t.isFull());
        
        BinarySearchTree<Integer> u = new BinarySearchTree<>();
        for(int i = 0; i < NUMS; i++)
            u.insert((int)(Math.random()*100));
        System.out.println("u = ");
        u.printLevels();
        System.out.println("t.compareStructure(u) = " + t.compareStructure(u));
        System.out.println("t.equals(u) = " + t.equals(u));
        
        BinarySearchTree<Integer> v = t.copy();
        System.out.println("v = t.copy() = ");
        v.printLevels();
        
        BinarySearchTree<Integer> w = t.mirror();
        System.out.println("w = t.mirror() = ");
        w.printLevels();
        
        System.out.println("t.isMirror(u) = " + t.isMirror(u));
        System.out.println("t.isMirror(v) = " + t.isMirror(v));
        System.out.println("t.isMirror(w) = " + t.isMirror(w));
        
        Integer i = t.root.element;
        t.rotateRight(i);
        System.out.println("t.rotateRight(" + i + ") = ");
        t.printLevels();
        
        i = t.root.element;
        t.rotateLeft(i);
        System.out.println("t.rotateLeft(" + i + ") = ");
        t.printLevels();
    }
    
    public BinarySearchTree()
    {
        root = null;
    }
    private BinarySearchTree(BinaryNode<AnyType> t)
    {
        root = t;
    }
    
    public void insert(AnyType x)
    {
        root = insert(x, root);
    }
    
    public void remove(AnyType x)
    {
        root = remove(x, root);
    }
    
    public AnyType findMin() throws Exception
    {
        if(isEmpty())
        {
            throw new Exception("Underflow");
        }
        return findMin(root).element;
    }
    
    public AnyType findMax() throws Exception
    {
        if(isEmpty())
        {
            throw new Exception("Underflow");
        }
        return findMax(root).element;
    }
    
    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }
    
    public void makeEmpty()
    {
        root = null;
    }
    
    public boolean isEmpty()
    {
        return root == null;
    }
    
    public void printTree()
    {
        if(isEmpty())
            System.out.println("Empty Tree");
        else
        {
            printTree(root);
            System.out.println("");
        }
    }
    
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
            return new BinaryNode<>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            t.left = insert(x, t.left);
        else if(compareResult > 0)
            t.right = insert(x, t.right);
        return t;
    }
    
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
            return t;
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            t.left = remove(x, t.left);
        else if(compareResult > 0)
            t.right = remove(x, t.right);
        else if(t.left != null && t.right != null)
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }
    
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        else if(t.left == null)
            return t;
        return findMin(t.left);
    }
    
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
    {
        if(t != null)
            while(t.right != null)
                t = t.right;
        return t;
    }
    
    private boolean contains(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
            return false;
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            return contains(x, t.left);
        else if(compareResult > 0)
            return contains(x, t.right);
        return true;
    }
    
    private void printTree(BinaryNode<AnyType> t)
    {
        if(t != null)
        {
            System.out.print("[");
            printTree(t.left);
            System.out.print(t.element);
            printTree(t.right);
            System.out.print("]");
        }
    }
    
    private int height(BinaryNode<AnyType> t)
    {
        if(t == null)
            return -1;
        return 1 + Math.max(height(t.left), height(t.right));
    }
    
    private static class BinaryNode<AnyType>
    {
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    
        BinaryNode(AnyType theElement)
        {
            this(theElement, null, null);
        }
        
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
        {
            element = theElement;
            left = lt;
            right = rt;
        }
    }
    
    public int nodeCount()
    {
        return nodeCount(root);
    }
    private int nodeCount(BinaryNode<AnyType> t)
    {
        if(t == null)
            return 0;
        return 1 + nodeCount(t.left) + nodeCount(t.right);
    }
    
    public boolean isFull()
    {
        return isFull(root);
    }
    private boolean isFull(BinaryNode<AnyType> t)
    {
        if(t == null)
            return true;
        else if(t.left != null && t.right != null)
            return isFull(t.left) && isFull(t.right);
        else if(t.left == null && t.right == null)
            return true;
        return false;
    }
    
    public boolean compareStructure(BinarySearchTree b)
    {
        return compareStructure(this.root, b.root);
    }
    private boolean compareStructure(BinaryNode<AnyType> t, BinaryNode<AnyType> u)
    {
        if(t == null && u == null)
            return true;
        else if(t == null || u == null)
            return false;
        return compareStructure(t.left, u.left) && compareStructure(t.right, u.right);
    }
    
    public boolean equals(BinarySearchTree b)
    {
        if(compareStructure(b))
            return equals(this.root, b.root);
        return false;
    }    
    private boolean equals(BinaryNode<AnyType> t, BinaryNode<AnyType> u)
    {
        if(t == null || u == null)
            return true;
        if(t.element.equals(u.element))
            return equals(t.left, u.left) && equals(t.right, u.right);
        return false;
    }
    
    public BinarySearchTree copy()
    {
        return new BinarySearchTree<>(copy(root));
    }
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        return new BinaryNode(t.element, copy(t.left), copy(t.right));
    }
    
    public BinarySearchTree mirror()
    {
        return new BinarySearchTree<>(mirror(copy().root));
    }
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        
        BinaryNode<AnyType> temp = t.left;
        t.left = mirror(t.right);
        t.right = mirror(temp);
        return t;
    }
    
    public boolean isMirror(BinarySearchTree<AnyType> b)
    {
        return this.equals(b.mirror());
    }
    
    private BinaryNode<AnyType> getNode(AnyType x, BinaryNode<AnyType> t)
    {
        if(t == null)
            return null;
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            return getNode(x, t.left);
        else if(compareResult > 0)
            return getNode(x, t.right);
        return t;
    }
    
    private BinaryNode<AnyType> getParent(BinaryNode<AnyType> t, BinaryNode<AnyType> u)
    {
        if(u.left == null && u.right == null)
            return null;
        if(u.left == t || u.right == t)
            return u;
        int compareResult = t.element.compareTo(u.element);
        if(compareResult < 0)
            return getParent(t, u.left);
        else if(compareResult > 0)
            return getParent(t, u.right);
        return null;
    }
    
    public void rotateRight(AnyType x)
    {
        if(nodeCount() <= 2)
            return;
        
        BinaryNode<AnyType> point = getNode(x, root);
        BinaryNode<AnyType> parent = getParent(point, root);
        
        BinaryNode<AnyType> left;
        if(point.left != null)
            left = point.left;
        else
            return;
        
        BinaryNode<AnyType> lr;
        try{
            lr = left.right;
        }catch(NullPointerException e)
        {
            lr = null;
        }
        
        left.right = point;
        point.left = lr;
        
        if(parent == null)
            root = left;
        else if(point.element.compareTo(parent.element) < 0)
            parent.left = left;
        else if(point.element.compareTo(parent.element) > 0)
            parent.right = left;
    }
    
    public void rotateLeft(AnyType x)
    {
        if(nodeCount() <= 2)
            return;
        
        BinaryNode<AnyType> point = getNode(x, root);
        BinaryNode<AnyType> parent = getParent(point, root);
        
        BinaryNode<AnyType> right;
        if(point.right != null)
            right = point.right;
        else
            return;
        
        BinaryNode<AnyType> rl;
        try{
            rl = right.left;
        }catch(NullPointerException e)
        {
            rl = null;
        }
        
        right.left = point;
        point.right = rl;
        
        if(parent == null)
            root = right;
        else if(point.element.compareTo(parent.element) < 0)
            parent.left = right;
        else if(point.element.compareTo(parent.element) > 0)
            parent.right = right;
    }
    
    public void printLevels()
    {
        if(root == null)
            return;
        
        LinkedList<BinaryNode<AnyType>> list = new LinkedList<>();
        list.add(root);
        
        String space = "";
        for(int i = 0; i < 10; i++)
            space+=" ";
        
        while(true)
        {
            int count = list.size();
            if(count == 0)
                break;
            
            System.out.print(space);
            
            while(count > 0)
            {
                BinaryNode<AnyType> temp = list.getFirst();
                System.out.print(temp.element + "  ");
                list.removeFirst();
                if(temp.left != null)
                    list.add(temp.left);
                if(temp.right != null)
                    list.add(temp.right);
                count--;
            }
            
            System.out.println();
            space = space.substring(2);
        }
    }
}
