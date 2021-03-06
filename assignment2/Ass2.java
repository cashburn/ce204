import java.util.Scanner;
import java.util.HashMap;
public class Ass2 {
    //HashMap - storage for identifiers
    static HashMap<Character, Integer> map;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Colin Ashburn 1507771");
        System.out.println("Here, you can evaluate cool things!");
        boolean cont = true;
        while (cont) {
            map = null;
            System.out.println("Please type an expression.");
            try {
                ExpTree tree = new Parser().parseLine();
                System.out.println("Post-order: " + tree.postString());
                System.out.println(tree);

                System.out.println("Result: " + evaluate(tree));
            } catch (ParseException e) {
                System.err.println(e);
                continue;
            }
            boolean validAnswer = false;
            while (!validAnswer) {
                System.out.println("Another expression (y/n)?");
                String s = in.nextLine();

                if (s.charAt(0) == 'n') {
                    cont = false;
                    validAnswer = true;
                    System.out.println("Goodbye!");
                    in.close();
                }
                else if (s.charAt(0) == 'y') {
                    validAnswer = true;
                    continue;
                }
            }
        }
    }
    
    //recursively evaluate to store set identifiers into HashMap
    public static int evaluate(ExpTree t) {
        if (t.type == ExpTree.letNode) {
            map = new HashMap<Character, Integer>();
            evaluate(t.left);
            return evaluate(t.right);
        }
        else if (t.type == ExpTree.andNode) {
            evaluate(t.right);
            evaluate(t.left);
        }
        else if (t.type == ExpTree.defNode) {
            map.put((char)t.value, t.left.value);
            return 0;
        }
        else if (map != null && t.type == ExpTree.idNode) {
            return map.get((char) t.value);
        }
        return t.evaluate(map);
    }
}
