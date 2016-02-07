class ListCell
{ char data;
  ListCell next;

  public ListCell(char x, ListCell c)
  { data = x;
    next = c;
  }
}

class ListException extends RuntimeException
{ public ListException(String s)
  { super(s);
  }
}

// interface for exercise 6
interface LListIterator
{ public boolean hasNext();
  public char next();
  public void remove();
}

public class LList
{ private ListCell front;
  int count;

  public LList()
  { front = null;
    count = 0;
  }

  public void addToFront(char x)
  { front = new ListCell(x, front);
    count++;
  }

  public void addToBack(char x)
  { if (front==null)
      front = new ListCell(x, front);
    else
    { ListCell c = front;
      while (c.next != null)
        c = c.next;
      c.next = new ListCell(x, null);
    }
    count++;
  }

  public char elementAt(int n)
  { ListCell c = front;
    if (n<0)
      throw new ListException("elementAt called with negative argument");
    for (int i = 0; i<n; i++)
    { if (c == null)
        throw new ListException("no element at position "+n);
      c = c.next;
    }
    if (c == null)
      throw new ListException("no element at position "+n);
    return c.data;
  }

  public String toString()
  { StringBuffer sb = new StringBuffer("<");
    ListCell c = front;
    while (c != null)
    { sb.append(c.data);
      c = c.next;
    }
    return(sb+">");
  }

  public int length() {
      return count;
  }

  public int occs(char c) {
      int occs = 0;
      ListCell cell = front;
      while (cell != null) {
          if (cell.data == c)
              occs++;
          cell = cell.next;
      }
      return occs;
  }
  
  public void removeFront() {
      ListCell c = front;
      if (c == null)
          throw new ListException("no elements in the list");
      front = front.next;
      c.next = null;
      count--;
  }
  
  public void removeBack() {
      ListCell c = front;
      if (c == null)
          throw new ListException("no elements in the list");
      if (c.next == null) {
          front = null;
          count--;
          return;
      }
      while (c.next.next != null)
          c = c.next;
      c.next = null;
      count--;
  }

  public boolean remove(char c) {
      ListCell cell = front;
      //ListCell prev = null;
      if (cell == null)
          throw new ListException("no elements in the list");
      if (cell.data == c) {
          front = front.next;
          cell.next = null;
          count--;
          return true;
      }
      while (cell.next != null) {
          if (cell.next.data == c) {
              cell.next = cell.next.next;
              count--;
              return true;
          }
          cell = cell.next;
      }
      return false;
  }
  // main method to test the class - expected list contents shown as comments

  public static void main(String args[])
  { LList myList = new LList(); // <>
    System.out.println(myList);
    myList.addToFront('c');     // <c>
    myList.addToFront('b');     // <bc>
    myList.addToFront('a');     // <abc>
    System.out.println(myList);
    myList.addToBack('d');      // <abcd>
    myList.addToBack('e');      // <abcde>
    System.out.println(myList);
    for (int i = -1; i<7; i++)
      try
      { System.out.println(myList.elementAt(i));
      }
      catch (ListException e)
      { System.out.println("ERROR: "+e);
      }
  }
  
  
class MyIterator implements LListIterator {
    ListCell prev;
    ListCell next; 
    public MyIterator() {
        prev = null;
        next = front;
    }
    public boolean hasNext() {
        return true;
    }
    public char next() {
        return 0;
    }
    public void remove() {
        
    }
}
}

