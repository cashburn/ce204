import java.util.HashMap;
public class ExpTree {
    public static final int numNode = 0, idNode = 1, opNode = 2, letNode = 3, andNode = 4, defNode = 5;
    public static final int exp = 0, times = 1, div = 2, mod = 3, plus = 4, minus = 5;
    public static final int[] order = {0,1,1,1,2,2}; //order[exp] = o, order[mod] = 1, etc.

    public int type;
    public int value;
    public ExpTree left, right;
    
    //constructor ex: new ExpTree(opNode, 0, new ExpTree(numNode, 42, null, null), new ExpTree(numNode, 64, null, null))
    public ExpTree(int typ, int val, ExpTree l, ExpTree r) {
        if (typ == numNode) {
            if (val < 0) {
                throw new TreeException("ERROR: negative integer");
            }
            if (l != null || r != null) {
                throw new TreeException("ERROR: number cannot have children");
            }
        }
        if (typ == idNode) {
            if (val < 'A' || val > 'Z') {
                throw new TreeException("ERROR: incorrect identifier");
            }
            if (l != null || r != null) {
                throw new TreeException("ERROR: identifier cannot have children");
            }
        }
        if (typ == opNode) {
            if (val != '+' && val != '-' && val != '*' && val != '/' &&
                    val != '%' && val != '^') {
                throw new TreeException("ERROR: incorrect operator");
            }
            if (l == null || r == null)
                throw new TreeException("ERROR: operator must have children");
        }
        type = typ;
        value = val;
        left = l;
        right = r;
    }
    
    //makes post-order String
    public String postString() {
        String s = "";
        if (type == letNode) {
            return right.postString();
        }
        else if (left != null && right != null) {
            s += left.postString() + " ";
            s += right.postString() + " ";
        }
        if (type == numNode) {
            s += value;
        }
        else {
            s += (char) value;
        }
        return s;
    }
    
    //makes in-order String
    public String toString() {
        String s = "";
        if (type == letNode) {
            s += "let ";
            s += left.toString();
            s += "in ";
            s += right.toString();
        }
        else if (type == andNode) {
            s += left.toString();
            s += "and ";
            s += right.toString();
        }
        else if (type == defNode) {
            s += (char) value + " = ";
            s += left.toString() + " ";
        }
        else {
            if (left != null) {
                int precedence = 0;
                if (left.type == opNode) {
                    if ((precedence = precedence(left.value, value)) > 0 && (precedence != 4)) {
                        s += "(";
                    }
                }
                s += left.toString();
                if ((precedence > 0) && (precedence != 4)) {
                    s += ")";
                }
            }
            if (type == numNode) {
                s += value;
            }
            else {
                s += (char) value;
            }
            if (right != null) {
                int precedence = 0;
                if (right.type == opNode) {
                    if ((precedence = precedence(right.value, value)) > 0) {
                        s += "(";
                    }
                }
                s += right.toString();
                if (precedence > 0) {
                    s += ")";
                }
            }
        }
        return s;
    }

    //helper method to find when parentheses are necessary
    //arguments: child and parent operation values (ex. precedence('+', '*');)
    //returns greater than 0 if operand 1 has higher precedence than operand 2
    private static int precedence(int a, int b) {
        int aOrder = 0;
        int bOrder = 0;
        switch ((char) a) {
            case '^':
                aOrder = exp;
                break;
            case '*':
                aOrder = times;
                break;
            case '/':
                aOrder = div;
                break;
            case '%':
                aOrder = mod;
                break;
            case '+':
                aOrder = plus;
                break;
            case '-':
                aOrder = minus;
                break;
        }
        switch ((char) b) {
            case '^':
                bOrder = exp;
                break;
            case '*':
                bOrder = times;
                break;
            case '/':
                bOrder = div;
                break;
            case '%':
                bOrder = mod;
                break;
            case '+':
                bOrder = plus;
                break;
            case '-':
                bOrder = minus;
                break;
        }
        
        if (aOrder == bOrder) {
            if (aOrder == plus || aOrder == times) {
                return order[aOrder] - order[bOrder];
            }
            return 4;
        }
        
        return order[aOrder] - order[bOrder];
    }

    //evaluates the tree recursively
    //uses a HashMap passed as an argument to find the set values of identifiers
    //returns an int evaluation
    public int evaluate(HashMap<Character, Integer> m) {
        if (m != null) {
            if (type == idNode) {
                Integer i;
                if ((i = m.get((char)value)) == null) {
                    return value - 'A';
                }
                return i;
            }
        }
        if (type == numNode) {
            return value;
        }
        else if (type == idNode) {
            return value - 'A';
        }
        if (type == defNode) {
            return left.evaluate(m);
        }
        else if (type == opNode) {
            switch (value) {
                case '+':
                    return left.evaluate(m) + right.evaluate(m);

                case '-':
                    return left.evaluate(m) - right.evaluate(m);

                case '*':
                    return left.evaluate(m) * right.evaluate(m);

                case '/':
                    return left.evaluate(m) / right.evaluate(m);

                case '%':
                    return left.evaluate(m) % right.evaluate(m);

                case '^':
                    return (int) Math.pow((double) left.evaluate(m), (double) right.evaluate(m));
            }
        }
        return value;
    }
}

class TreeException extends RuntimeException {
    public TreeException(String s) {
        super(s);
    }
}
