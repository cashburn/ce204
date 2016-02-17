import java.util.Random;

public class Test {
  public static void main(String[] args) {
    Random rand = new Random();
    BST b = new BST();
    int count = 0;
    b.insert(1);
    for (int i = 0; i < 100000; i++) {
      if (b.insert(rand.nextInt(1000000)))
        count++;
    }
    System.out.printf("Number of elements inserted: %d\n", count);
    System.out.printf("Depth: %d\n", b.depth());
  }
}