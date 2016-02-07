public class ListMain
{ public static void main(String args[])
  { LList<Integer> myList = new LList<Integer>(); // <>
  LList<Integer> myList2= new LList<Integer>();
    System.out.println(myList);
    System.out.println("length = " + myList.length());
    myList.addToFront(101);
    myList.addToFront(101);
    myList.addToFront(5);     // <5>
    myList.addToFront(4);     // <45>
    myList.addToFront(101);     // <345>
    System.out.println(myList);
    myList2.addToBack(101);
    myList2.addToBack(5);
    myList2.addToBack(128);      // <3458>
    myList2.addToBack(9);      // <34589>
    System.out.println(myList.find(128));
    myList2.addToBack(101);
    System.out.println(myList.find(128));
    System.out.println(myList.find(101));
    System.out.println(myList2);
    System.out.println("length = " + myList.length());
    myList.inBoth(myList, myList2);
    myList2.removeBoth(myList, myList2);
    myList.removeAll(101);
    System.out.println(myList);
    System.out.println(myList2);
    /*for (int i = 0; i<7; i++)
      try
      { System.out.println(myList.elementAt(i));
      }
      catch (ListException e)
      { System.out.println("ERROR: "+e);
      }
*/
    LList<String> mySList = new LList<String>();
    mySList.addToFront("hello");
    mySList.addToBack("goodbye");
    mySList.find("hello");
    System.out.println(mySList);
  }
}
