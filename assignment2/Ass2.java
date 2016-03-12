import java.util.Scanner;
public class Ass2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Colin Ashburn 1507771");
        System.out.println("Here, you can evaluate cool things!");
        boolean cont = true;
        while (cont) {
            System.out.println("Please type an expression.");
            try {
                ExpTree tree = new Parser().parseLine();
                System.out.println("Post-order: " + tree.postString());
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
}