
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class AndLogicalExpr extends LogicalExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isDAafterTrue_Variable_visited = null;
        isDAafterTrue_Variable_values = null;
        isDAafterFalse_Variable_visited = null;
        isDAafterFalse_Variable_values = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafterTrue_Variable_visited = null;
        isDUafterFalse_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        printOp_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AndLogicalExpr clone() throws CloneNotSupportedException {
        AndLogicalExpr node = (AndLogicalExpr)super.clone();
        node.constant_visited = -1;
        node.isDAafterTrue_Variable_visited = null;
        node.isDAafterTrue_Variable_values = null;
        node.isDAafterFalse_Variable_visited = null;
        node.isDAafterFalse_Variable_values = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafterTrue_Variable_visited = null;
        node.isDUafterFalse_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.printOp_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AndLogicalExpr copy() {
      try {
          AndLogicalExpr node = (AndLogicalExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AndLogicalExpr fullCopy() {
        AndLogicalExpr res = (AndLogicalExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in java.ast at line 3
    // Declared in java.ast line 175

    public AndLogicalExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 175
    public AndLogicalExpr(Expr p0, Expr p1) {
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
    // Declared in ConstantExpression.jrag at line 537
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return Constant.create(left().constant().booleanValue() && right().constant().booleanValue());  }

    // Declared in DefiniteAssignment.jrag at line 365
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterTrue(Variable v) {
        Object _parameters = v;
if(isDAafterTrue_Variable_visited == null) isDAafterTrue_Variable_visited = new java.util.HashMap(4);
if(isDAafterTrue_Variable_values == null) isDAafterTrue_Variable_values = new java.util.HashMap(4);
        if(isDAafterTrue_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAafterTrue_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterTrue_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterTrue in class: ");
        isDAafterTrue_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAafterTrue_Variable_values.put(_parameters, Boolean.valueOf(isDAafterTrue_Variable_value));
        isDAafterTrue_Variable_visited.remove(_parameters);
        return isDAafterTrue_Variable_value;
    }

    private boolean isDAafterTrue_compute(Variable v) {  return getRightOperand().isDAafterTrue(v) || isFalse();  }

    // Declared in DefiniteAssignment.jrag at line 367
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFalse(Variable v) {
        Object _parameters = v;
if(isDAafterFalse_Variable_visited == null) isDAafterFalse_Variable_visited = new java.util.HashMap(4);
if(isDAafterFalse_Variable_values == null) isDAafterFalse_Variable_values = new java.util.HashMap(4);
        if(isDAafterFalse_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAafterFalse_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterFalse_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterFalse in class: ");
        isDAafterFalse_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAafterFalse_Variable_values.put(_parameters, Boolean.valueOf(isDAafterFalse_Variable_value));
        isDAafterFalse_Variable_visited.remove(_parameters);
        return isDAafterFalse_Variable_value;
    }

    private boolean isDAafterFalse_compute(Variable v) {  return (getLeftOperand().isDAafterFalse(v) && getRightOperand().isDAafterFalse(v)) || isTrue();  }

    // Declared in DefiniteAssignment.jrag at line 373
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        Object _parameters = v;
if(isDAafter_Variable_visited == null) isDAafter_Variable_visited = new java.util.HashMap(4);
if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
        if(isDAafter_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafter in class: ");
        isDAafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
        isDAafter_Variable_visited.remove(_parameters);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return isDAafterTrue(v) && isDAafterFalse(v);  }

    protected java.util.Map isDUafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 805
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterTrue(Variable v) {
        Object _parameters = v;
if(isDUafterTrue_Variable_visited == null) isDUafterTrue_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterTrue_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterTrue in class: ");
        isDUafterTrue_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterTrue_Variable_value = isDUafterTrue_compute(v);
        isDUafterTrue_Variable_visited.remove(_parameters);
        return isDUafterTrue_Variable_value;
    }

    private boolean isDUafterTrue_compute(Variable v) {  return getRightOperand().isDUafterTrue(v);  }

    protected java.util.Map isDUafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 806
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterFalse(Variable v) {
        Object _parameters = v;
if(isDUafterFalse_Variable_visited == null) isDUafterFalse_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterFalse_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterFalse in class: ");
        isDUafterFalse_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterFalse_Variable_value = isDUafterFalse_compute(v);
        isDUafterFalse_Variable_visited.remove(_parameters);
        return isDUafterFalse_Variable_value;
    }

    private boolean isDUafterFalse_compute(Variable v) {  return getLeftOperand().isDUafterFalse(v) && getRightOperand().isDUafterFalse(v);  }

    // Declared in DefiniteAssignment.jrag at line 809
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        Object _parameters = v;
if(isDUafter_Variable_visited == null) isDUafter_Variable_visited = new java.util.HashMap(4);
if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
        if(isDUafter_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafter in class: ");
        isDUafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
        isDUafter_Variable_visited.remove(_parameters);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return isDUafterTrue(v) && isDUafterFalse(v);  }

    protected int printOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 411
 @SuppressWarnings({"unchecked", "cast"})     public String printOp() {
        ASTNode$State state = state();
        if(printOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printOp in class: ");
        printOp_visited = state().boundariesCrossed;
        String printOp_value = printOp_compute();
        printOp_visited = -1;
        return printOp_value;
    }

    private String printOp_compute() {  return " && ";  }

    // Declared in DefiniteAssignment.jrag at line 371
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getRightOperandNoTransform()) {
            return getLeftOperand().isDAafterTrue(v);
        }
        if(caller == getLeftOperandNoTransform()) {
            return isDAbefore(v);
        }
        return super.Define_boolean_isDAbefore(caller, child, v);
    }

    // Declared in DefiniteAssignment.jrag at line 808
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getRightOperandNoTransform()) {
            return getLeftOperand().isDUafterTrue(v);
        }
        if(caller == getLeftOperandNoTransform()) {
            return isDUbefore(v);
        }
        return super.Define_boolean_isDUbefore(caller, child, v);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
