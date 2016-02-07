import java.util.*;

public class LLQueue<T> implements Queue<T>
{ private LinkedList<T> ll;

  public LLQueue()
  { ll = new LinkedList<T>();
  }

  public boolean isEmpty()
  { return ll.isEmpty();
  }

  public void add(T x)
  { ll.addLast(x);
  }

  public T front()
  { if (ll.isEmpty())
      throw new QueueException("front");
    return ll.getFirst();
  }

  public void removeFront()
  { if (ll.isEmpty())
      throw new QueueException("removeFront");
      ll.removeFirst();
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer("<");
    ListIterator<T> iter = ll.listIterator(0);
    while (iter.hasNext()) {
      sb.append(iter.next());
      if (iter.hasNext())
        sb.append(",");
    }
    return sb + ">";
  }
}

