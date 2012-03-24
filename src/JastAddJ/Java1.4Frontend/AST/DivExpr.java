
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class DivExpr extends MultiplicativeExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isConstant_visited = -1;
        isConstant_computed = false;
        isConstant_initialized = false;
        printOp_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DivExpr clone() throws CloneNotSupportedException {
        DivExpr node = (DivExpr)super.clone();
        node.constant_visited = -1;
        node.isConstant_visited = -1;
        node.isConstant_computed = false;
        node.isConstant_initialized = false;
        node.printOp_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DivExpr copy() {
      try {
          DivExpr node = (DivExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DivExpr fullCopy() {
        DivExpr res = (DivExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in java.ast at line 3
    // Declared in java.ast line 158

    public DivExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 158
    public DivExpr(Expr p0, Expr p1) {
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
    // Declared in java.ast line 153
    public void setLeftOperand(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getLeftOperand() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getLeftOperandNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 153
    public void setRightOperand(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getRightOperand() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getRightOperandNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 118
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return type().div(getLeftOperand().constant(), getRightOperand().constant());  }

    // Declared in ConstantExpression.jrag at line 497
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        if(isConstant_computed) {
            return isConstant_value;
        }
        ASTNode$State state = state();
        if (!isConstant_initialized) {
            isConstant_initialized = true;
            isConstant_value = false;
        }
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            do {
                isConstant_visited = state.CIRCLE_INDEX;
                state.CHANGE = false;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                boolean new_isConstant_value = isConstant_compute();
                if (new_isConstant_value!=isConstant_value)
                    state.CHANGE = true;
                isConstant_value = new_isConstant_value; 
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
            isConstant_computed = true;
            state.LAST_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isConstant_compute();
            state.LAST_CYCLE = false;
            }
            else {
            state.RESET_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isConstant_compute();
            state.RESET_CYCLE = false;
              isConstant_computed = false;
              isConstant_initialized = false;
            }
            state.IN_CIRCLE = false; 
            return isConstant_value;
        }
        if(isConstant_visited != state.CIRCLE_INDEX) {
            isConstant_visited = state.CIRCLE_INDEX;
            if (state.LAST_CYCLE) {
                isConstant_computed = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                return isConstant_compute();
            }
            if (state.RESET_CYCLE) {
                isConstant_computed = false;
                isConstant_initialized = false;
                isConstant_visited = -1;
                return isConstant_value;
            }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            boolean new_isConstant_value = isConstant_compute();
            if (new_isConstant_value!=isConstant_value)
                state.CHANGE = true;
            isConstant_value = new_isConstant_value; 
            return isConstant_value;
        }
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getLeftOperand().isConstant() && getRightOperand().isConstant() && !(getRightOperand().type().isInt() && getRightOperand().constant().intValue() == 0);  }

    protected int printOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 401
 @SuppressWarnings({"unchecked", "cast"})     public String printOp() {
        ASTNode$State state = state();
        if(printOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printOp in class: ");
        printOp_visited = state().boundariesCrossed;
        String printOp_value = printOp_compute();
        printOp_visited = -1;
        return printOp_value;
    }

    private String printOp_compute() {  return " / ";  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
