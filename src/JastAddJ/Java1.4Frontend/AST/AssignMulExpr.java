
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class AssignMulExpr extends AssignMultiplicativeExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        printOp_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AssignMulExpr clone() throws CloneNotSupportedException {
        AssignMulExpr node = (AssignMulExpr)super.clone();
        node.printOp_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AssignMulExpr copy() {
      try {
          AssignMulExpr node = (AssignMulExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AssignMulExpr fullCopy() {
        AssignMulExpr res = (AssignMulExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in java.ast at line 3
    // Declared in java.ast line 104

    public AssignMulExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 104
    public AssignMulExpr(Expr p0, Expr p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in java.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 18

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 99
    public void setDest(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getDest() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getDestNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 99
    public void setSource(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getSource() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getSourceNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected int printOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 248
 @SuppressWarnings({"unchecked", "cast"})     public String printOp() {
        ASTNode$State state = state();
        if(printOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printOp in class: ");
        printOp_visited = state().boundariesCrossed;
        String printOp_value = printOp_compute();
        printOp_visited = -1;
        return printOp_value;
    }

    private String printOp_compute() {  return " *= ";  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
