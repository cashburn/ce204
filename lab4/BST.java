public class BST<T extends Comparable<T>>
{ private BTNode<T> root;

  public BST()
  { root = null;
  }

  public boolean find(T i)
  { BTNode<T> n = root;
    boolean found = false;

    while (n!=null && !found)
    { int comp = i.compareTo(n.data);
      if (comp==0)
        found = true;
      else if (comp<0)
        n = n.left;
      else
        n = n.right;
 }

    return found;
  }

  public boolean insert(T i)
  { BTNode<T> parent = root, child = root;
    boolean goneLeft = false;

    while (child!=null && i.compareTo(child.data)!=0)
    { parent = child;
      if (i.compareTo(child.data)<0)
   { child = child.left;
     goneLeft = true;
   }
   else
   { child = child.right;
     goneLeft = false;
      }
 }

    if (child!=null)
      return false;  // number already present
    else
    { BTNode<T> leaf = new BTNode<T>(i);
      if (parent==null) // tree was empty
        root = leaf;
      else if (goneLeft)
        parent.left = leaf;
      else
        parent.right = leaf;
      return true;
    }
  }
  
  public boolean delete(T i) {
    boolean found = false;
    BTNode<T> n = root;
    BTNode<T> prev = null;
    while (n != null && !found) {
      int comp = i.compareTo(n.data);
      if (comp==0)
        found = true;
      else if (comp<0) {
        prev = n;
        n = n.left;
      }
      else {
        prev = n;
        n = n.right;
      }
    }
    
    if (n == null)
      return false;
    if (!found)
      return false;
    
    if (n.left == null && n.right == null) {
      if (prev.left == n)
        prev.left = null;
      if (prev.right == n)
        prev.right = null;
      return true;
    }
    
    if (n.left == null) {
      if (prev.left == n)
        prev.left = n.right;
      if (prev.right == n)
        prev.right = n.right;
      return true;
    }
    
    if (n.right == null) {
      if (prev.left == n)
        prev.left = n.left;
      if (prev.right == n)
        prev.right = n.left;
      return true;
    }
    
    else {
      BTNode<T> child = n.left;
      BTNode<T> childPrev = null;
      while (child.right != null) {
        childPrev = child;
        child = child.right;
      }
      
      childPrev.right = null;
      if (prev.left == n) {
        prev.left = child;
      }
      if (prev.right == n)
        prev.right = child;
      return true;
    }
  }

  public String toString() //same as toString method in slides for BTree class
  { return getString(root);
  }

  private static <T> String getString(BTNode<T> n)
  { if (n==null)
      return("");
    else
    { String s1 = getString(n.left);
      String s2 = getString(n.right);
      return s1+" "+n.data+" "+s2;
   }
  }
}

class BTNode<T>
{ T data;
  BTNode<T> left, right;

  BTNode(T o)
  { data = o; left = right = null;
  }
}
