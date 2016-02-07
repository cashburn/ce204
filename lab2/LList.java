class ListCell<T>
{ T data;
  ListCell<T> next;

  public ListCell(T x, ListCell<T> c)
  { data = x;
    next = c;
  }
}

class ListException extends RuntimeException
{ public ListException(String s)
  { super(s);
  }
}

public class LList<T>
{ private ListCell<T> front;

  public LList()
  { front = null;
  }

  public void addToFront(T x)
  { front = new ListCell<T>(x, front);
  }

  public void addToBack(T x)
  { if (front==null)
      front = new ListCell<T>(x, front);
    else
    { ListCell<T> c = front;
      while (c.next != null)
        c = c.next;
      c.next = new ListCell<T>(x, null);
    }
  }

  public T elementAt(int n)
  { ListCell<T> c = front;
    for (int i = 0; i<n; i++)
    { if (c == null)
        throw new ListException("no element at position "+n);
      c = c.next;
    }
    if (c == null)
      throw new ListException("no element at position "+n);
    return c.data;
  }

  public int length()
  { ListCell<T> c = front;
    int result = 0;
    while (c != null)
    { result++;
      c = c.next;
    }
    return result;
  }

  public String toString()
  { StringBuffer sb = new StringBuffer("<");
    ListCell<T> c = front;
    while (c != null)
    { sb.append(c.data);
      c = c.next;
      if (c != null)
        sb.append(',');
    }
    return(sb+">");
  }
  
  public int find(T x) {
    int count = 0;
    ListCell<T> c = front;
    while (c != null) {
      if (x==c.data)
        return count;
      count++;
      c = c.next;
    }
    return -1;
  }
  
  public static <T> void inBoth(LList<T> a, LList<T> b) {
    int count = 0;
    ListCell<T> c = a.front;
    while (c != null) {
      if (b.find(c.data) > -1)
        if (a.find(c.data) == count)
        System.out.println(c.data);
      c = c.next;
      count++;
    }
  }
  
  public boolean removeAll(T x) {
    boolean remove = false;
    ListCell<T> c = front;
    ListCell<T> prev = null;
    if (c == null)
      return false;
    if (front.data == x) {
      front = front.next;
    }
    prev = c;
    c = c.next;
    while (c != null) {
      if (c.data == x) {
        prev.next = c.next;
        c = prev.next;
      }
      else {
        c = c.next;
        prev = prev.next;
      }
    }
    return remove;
  }
  
  public static <T> void removeBoth(LList<T> a, LList<T> b) {
    ListCell c = a.front;
    while (c != null) {
      b.removeAll((T) c.data);
      c = c.next;
    }
  }
}
