public class BST {
    private BTNode<Integer> root;
    private int min, max;

    public BST() {
        root = null;
    }
    
    public boolean find(Integer i) {
        BTNode<Integer> n = root;
        boolean found = false;
        
        while (n!=null && !found) {
            int comp = i.compareTo(n.data);
            if (comp==0)
                found = true;
            else if (comp<0)
                n = n.left;
            else
                n = n.right;
        }
        
        return found;
    }
    
    public boolean insert(Integer i) {
        BTNode<Integer> parent = root, child = root;
        boolean goneLeft = false;
        
        while (child!=null && i.compareTo(child.data)!=0) {
            parent = child;
            if (i.compareTo(child.data)<0) {
                child = child.left;
                goneLeft = true;
            }
            else {
                child = child.right;
                goneLeft = false;
            }
        }
        
        if (child!=null)
            return false;  // number already present
        else {
            BTNode<Integer> leaf = new BTNode<Integer>(i);
            if (parent==null) // tree was empty
                root = leaf;
            else if (goneLeft)
                parent.left = leaf;
            else
                parent.right = leaf;
            return true;
        }
    }
    
    public int nonleaves() {                           //public method to start recursion
        return nonleaves(root);
    }
    
    private int nonleaves(BTNode<Integer> root) {       //recursive nonleaves()
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)    //leaf--don't count
            return 0;
        return 1 + nonleaves(root.right) + nonleaves(root.left);    //count if has children (implicit else clause)
    }
    
    public int depth() {                                //public method to start recursion
        return depth(root);
    }
    
    private int depth(BTNode<Integer> root) {           //recursive depth()
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)    //leaf--count, but no recursion
            return 1;
        return 1 + Math.max(depth(root.left),depth(root.right));    //count child with most other children
    }
    
    public int range(int min, int max) {                //public range() to start recursion and set min/max private instance vars
        if (max < min)
            throw new IllegalArgumentException();
        this.min = min;                                 //set instance vars so that they don't have to be passed recursively, wasting resources
        this.max = max;
        return range(root);
    }
    
    private int range(BTNode<Integer> root) {           //recursive range()
        if (root == null)
            return 0;
        if (root.data >= min && root.data <= max)       //if in range, recurse and count
            return 1 + range(root.left) + range(root.right);
        return range(root.left) + range(root.right);    //if not in range, recurse without count
    }
}

class BTNode<T> {
    T data;
    BTNode<T> left, right;
    
    BTNode(T o) {
        data = o; left = right = null;
    }
}
