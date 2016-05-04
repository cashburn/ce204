public class ExpTree {
    public static final int numNode = 0;
    public static final int idNode = 1;
    public static final int opNode = 2;
    private int type;
    private int value;
    private ExpTree left, right;

    public ExpTree (int typ, int val, ExpTree l, ExpTree r) {
        //TODO: check that type fits value etc.
        if (typ == 0) {
            if (val < 0) {
                throw new TreeException("ERROR: negative integer");
            }
            if (l != null || r != null) {
                throw new TreeException("ERROR: number cannot have children");
            }
        }
        if (typ == 1) {
            if (val < 'A' || val > 'Z') {
                throw new TreeException("ERROR: incorrect identifier");
            }
            if (l != null || r != null) {
                throw new TreeException("ERROR: identifier cannot have children");
            }
        }
        if (typ == 2) {
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

    public String postString() {
        String s = "";
        if (left != null && right != null) {
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

    public int evaluate() {
        if (type == numNode) {
            return value;
        }
        if (type == idNode) {
            return value - 'A';
        }

    }
}

class TreeException extends RuntimeException {
    public TreeException(String s) {
        super(s);
    }
}
