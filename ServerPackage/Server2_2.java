package ClientPackage;
import java.io.Serializable;

public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    private int op1;
    private char operateur;
    private int op2;


    public Operation(int op1, char operateur, int op2) {
        this.op1 = op1;
        this.operateur = operateur;
        this.op2 = op2;
    }


    public int getOp1() { return op1; }
    public char getOperateur() { return operateur; }
    public int getOp2() { return op2; }

    @Override
    public String toString() {
        return op1 + " " + operateur + " " + op2;
    }
}