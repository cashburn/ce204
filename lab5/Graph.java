public class Graph
{ private int numNodes;

  // vertices will be called A,B,...

  // adjacency list representation used
  // adjLists[0] will hold a reference to the first cell in a linked list containing
  //       details of the edges from A to all of its neighbours,
  // adjLists[1] will hold details of edges from B to its neighbours etc.

  private ListCell[] adjLists;

  // constructor to create a graph with n vertices and no edges
  public Graph(int n)
  { int i;
    numNodes = n;
    adjLists = new ListCell[n];
    for (i = 0; i<n; i++)
      adjLists[i] = null;
  }

  // try to add an edge (v1,v2) with weight w) - return true if succesful, false otherwise
  public boolean addEdge(char v1, char v2, int w)
  { // convert the vertex names into array positions
    int pos1 = v1-'A';
    int pos2 = v2-'A';

    // check if either of pos1 and pos2 is outside the array index range
 if (pos1 < 0 || pos2 < 0 || pos1 > numNodes || pos2 > numNodes)
    { // if so, print an error message and return false
      System.err.println("Error: vertices not in range");
      return false;
    }

    else if (v1==v2)
    { // vertices must be distinct - print a warning message and return false
      System.err.println("Warning: Vertices cannot be the same");
      return false;
    }

    else if (neighbours(v1,v2))
    { // edge is already present - print a warning error message and return false
      System.err.println("Warning: Edge already exists");
      return false;
    }

    else
    { // add a new cell with values (v2,w) to the front of the list adjLists[pos1]
      adjLists[pos1] = new ListCell(v2, w, adjLists[pos1]);
      // also add a new cell with values (v1,w) to the front of the list adjLists[pos2] - need similar line to above
      adjLists[pos2] = new ListCell(v1, w, adjLists[pos2]);
      // added succesfully
      return true;
    }
  }

  public String toString()
  {
 // should return a string containing all of the edges in the graph with their weights
 // e.g. "(A,B,2), (A,C,3), (B,D,4), (B,A,2), (C,A,3), (D,B,4)"
 // code below is supplied just to allow file to compile
    StringBuffer sb = new StringBuffer();
    ListCell c;
    for (int i = 0; i < numNodes; i++) {
      c = adjLists[i];
      while (c != null) {
        sb.append(String.format("(%c,%c,%d), ", i+'A', c.vertex, c.weight));
        c = c.next;
      }
    }
    if (sb.length() == 0)
     return "";
    return sb.substring(0, sb.length() - 2);

 // to convert the array position into a vertex name simply use something like
 // char vertex = 'A'+pos;
  }

  public boolean neighbours(char v1, char v2)
  {
 // should return true if there's an edge from v1 to v2
 // should return false if there's no such edge
 // need to search the linked list ajdLists[v1-'A'] to see if contains a node whose vertex variable is equal to v2
 // code below is supplied just to allow file to compile
 return false;
  }

  public int edgeWeight(char v1, char v2)
  {
 // should return the weight of the edge from v1 to v2, or a default value (e.g. -1) if there's no such edge
 // again need to search the linked list ajdLists[v1-'A'] but this time if a node whose vertex is v2 is found have to return its weight
 // code below is supplied just to allow file to compile
 return 0;
  }
}

// linked list - each cell contains a vertex name, a weight and a reference to the next item in the list
class ListCell
{ char vertex;
  int weight;
  ListCell next;

  ListCell(char v, int w, ListCell nxt)
  { vertex = v;
    weight = w;
    next = nxt;
  }
}



