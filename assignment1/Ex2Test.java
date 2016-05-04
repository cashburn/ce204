// if you've used IntelliJ you'll probably need a line like the following one

// package ex2;

public class Ex2Test
{ private static void testnl(BST t)
  { try
    { System.out.println(t.nonleaves());
    }
    catch (Exception e)
    { System.out.println("*** CAUGHT EXCEPTION: " + e);
    }
  }

  private static void testdep(BST t)
    { try
      { System.out.println(t.depth());
      }
      catch (Exception e)
      { System.out.println("*** CAUGHT EXCEPTION: " + e);
      }
  }

  private static void testran(BST t, int min, int max)
  { try
    { System.out.println(t.range(min, max));
    }
    catch (IllegalArgumentException e)
    { System.out.println("*** CAUGHT ILLEGAL ARGUMENT EXCEPTION");
    }
    catch (Exception e)
    { System.out.println("*** CAUGHT OTHER EXCEPTION: " + e);
    }
  }

  public static void main(String args[])
  { BST leaf = new BST();
    leaf.insert(5);
    BST t1 = new BST();
    t1.insert(2);
    t1.insert(1);
    t1.insert(3);
    BST t2 = new BST();
    t2.insert(1);
    t2.insert(10);
    t2.insert(2);
    t2.insert(9);
    t2.insert(3);
    t2.insert(8);
    t2.insert(5);
    t2.insert(4);
    t2.insert(6);
    t2.insert(7);
    BST t3 = new BST();
    t3.insert(4);
    t3.insert(2);
    t3.insert(6);
    t3.insert(1);
    t3.insert(3);
    t3.insert(5);
    t3.insert(7);
    t3.insert(9);
    t3.insert(8);

    System.out.println();
    System.out.println("Testing nonleaves");
    System.out.print("leaf (expect 0): ");
    testnl(leaf);
    System.out.print("t1 (expect 1): ");
    testnl(t1);
    System.out.print("t2 (expect 8): ");
    testnl(t2);
    System.out.print("t3 (expect 5): ");
    testnl(t3);

    System.out.println();
    System.out.println("Testing depth");
    System.out.print("leaf (expect 1): ");
    testdep(leaf);
    System.out.print("t1 (expect 2): ");
    testdep(t1);
    System.out.print("t2 (expect 9): ");
    testdep(t2);
    System.out.print("t3 (expect 5): ");
    testdep(t3);

    System.out.println();
    System.out.println("Testing range");
    System.out.print("leaf.range(5,7) (expect 1): ");
    testran(leaf,5,7);
    System.out.print("leaf.range(1,3) (expect 0): ");
    testran(leaf,1,3);
    System.out.print("t2.range(2,3) (expect 2): ");
    testran(t2,2,3);
    System.out.print("t2.range(1,9) (expect 9): ");
    testran(t2,1,9);
    System.out.print("t3.range(3,7) (expect 5): ");
    testran(t3,3,7);
    System.out.print("t3.range(5,8) (expect 4): ");
    testran(t3,5,8);

    System.out.println();
    System.out.println("Testing with empty tree");
    System.out.print("nonleaves (expect 0): ");
    testnl(new BST());
    System.out.print("depth (expect 0): ");
    testdep(new BST());
    System.out.print("range(1,100) (expect 0): ");
    testran(new BST(),1,100);

    System.out.println("Testing invalid range arguments (expect IllegalArgumentException");
    testran(t3,5,2);
  }
}
