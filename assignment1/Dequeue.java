class DeqCell<T> {              //doubly linked list to eliminate overheads from array copying or singly LL iterating
    T data;
    DeqCell<T> next;            //right of the current cell
    DeqCell<T> prev;            //left of the current cell
    
    DeqCell() {
        data = null;
        next = null;
        prev = null;
    }
}

class QueueException extends RuntimeException {
    QueueException(String s) {
        super("Tried to apply " + s + " to empty queue");
    }
}

public class Dequeue<T> {
    private DeqCell<T> left;    //left end of queue
    private DeqCell<T> right;   //right end of queue
    
    public Dequeue() {
        left = null;
        right = null;
    }
    
    public boolean isEmpty() {
        return (left == null && right == null);
    }
    
    public T left() {
        if (left == null)
            throw new QueueException("left");
        return left.data;
    }
    
    public T right() {
        if (right == null)
            throw new QueueException("right");
        return right.data;
    }
    
    public void addLeft(T o) {
        DeqCell<T> c = new DeqCell<T>();
        if (left == null) {     //if first entry, set both left and right to c
            c.data = o;
            left = c;
            right = c;
            return;
        }
        c.data = o;
        left.prev = c;          //connect the two links
        c.next = left;
        left = c;
    }
    
    public void addRight(T o) {
        DeqCell<T> c = new DeqCell<T>();
        if (right == null) {    //if first entry, set both left and right to c
            c.data = o;
            left = c;
            right = c;
            return;
        }
        c.data = o;
        right.next = c;         //connect the two links
        c.prev = right;
        right = c;
    }
    
    public void removeLeft() {
        if (left == null)
            throw new QueueException("removeLeft");
        left = left.next;       //disconnect from chain
        if (left == null) {
            right = null;
        }
        else
            left.prev = null;   //fully disconnect for garbage collector
    }

        
    public void removeRight() { //similar to above
        if (right == null)
            throw new QueueException("removeRight");
        right = right.prev;
        if (right == null)
            left = null;
        else
            right.next = null;
    }
    
    public String toString() {
        DeqCell<T> c = left;
        StringBuffer sb = new StringBuffer("<");
        
        while (c != null) {     //iterate through cells
            sb.append(c.data);
            c = c.next;
            if (c != null)
                sb.append(",");
        }
        return sb + ">";
    }
}