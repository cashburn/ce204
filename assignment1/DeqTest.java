public class DeqTest {
    public static void main(String[] args) {
        Dequeue<Integer> q = new Dequeue<Integer>();
        
        System.out.println(q);
        try {
            q.left();
        }
        catch (QueueException ex) {
            System.out.println("EXCEPTION: " + ex);
        }

        try {
            q.right();
        }
        catch (QueueException ex) {
            System.out.println("EXCEPTION: " + ex);
        }

        try {
            q.removeLeft();
        }
        catch (QueueException ex) {
            System.out.println("EXCEPTION: " + ex);
        }

        try {
            q.removeRight();
        }
        catch (QueueException ex) {
            System.out.println("EXCEPTION: " + ex);
        }
        System.out.println("Testing adding and removing from both sides");
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("addLeft(1)");
        q.addLeft(1);
        System.out.println("isEmpty: " + q.isEmpty());
        System.out.println("removeRight()");
        q.removeRight();
        System.out.println("addRight(2)");
        q.addRight(2);
        System.out.println("Right: " + q.right());
        System.out.println("removeRight()");
        q.removeRight();
        
        System.out.println("addLeft(1)");
        q.addLeft(1);
        System.out.println("removeLeft()");
        q.removeLeft();
        System.out.println("addRight(2)");
        q.addRight(2);
        System.out.println("Left: " + q.left());
        System.out.println("removeLeft()");
        q.removeLeft();
        System.out.println(q);
        
        System.out.println("");
        System.out.println("Adding ints 1-20");
        for (int i = 1; i < 21; i++)
            q.addLeft(i);
        System.out.println(q);
        System.out.println("Right: " + q.right());
        System.out.println("Removing 5 ints from right");
        for (int i = 0; i < 5; i++)
            q.removeRight();
        System.out.println("Right: " + q.right());
        System.out.println("Removing 5 ints from left");
        for (int i = 0; i < 5; i++)
            q.removeLeft();
        System.out.println("Left: " + q.left());
        System.out.println(q);

        System.out.println("");
        System.out.println("Testing other type (String)");
        
        Dequeue<String> w = new Dequeue<String>();
        System.out.println("addRight(\"removeThis\")");
        w.addRight("removeThis");
        System.out.println(w);
        System.out.println("Left: " + w.left());
        System.out.println("removeRight()");
        w.removeRight();
        System.out.println(w);
        
    }
}