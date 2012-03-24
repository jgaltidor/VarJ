
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class LogNotExpr extends Unary implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isConstant_visited = -1;
        constant_visited = -1;
        isDAafterTrue_Variable_visited = null;
        isDAafterFalse_Variable_visited = null;
        isDAafter_Variable_visited = null;
        isDUafterTrue_Variable_visited = null;
        isDUafterFalse_Variable_visited = null;
        isDUafter_Variable_visited = null;
        printPreOp_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public LogNotExpr clone() throws CloneNotSupportedException {
        LogNotExpr node = (LogNotExpr)super.clone();
        node.isConstant_visited = -1;
        node.constant_visited = -1;
        node.isDAafterTrue_Variable_visited = null;
        node.isDAafterFalse_Variable_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDUafterTrue_Variable_visited = null;
        node.isDUafterFalse_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.printPreOp_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LogNotExpr copy() {
      try {
          LogNotExpr node = (LogNotExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LogNotExpr fullCopy() {
        LogNotExpr res = (LogNotExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TypeCheck.jrag at line 287


  // 15.15.6
  public void typeCheck() {
    if(!getOperand().type().isBoolean())
      error("unary ! only operates on boolean types");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 145

    public LogNotExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 145
    public LogNotExpr(Expr p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 17

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 139
    public void setOperand(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getOperand() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getOperandNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 490
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getOperand().isConstant();  }

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 530
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return Constant.create(!getOperand().constant().booleanValue());  }

    protected java.util.Map isDAafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 381
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterTrue(Variable v) {
        Object _parameters = v;
if(isDAafterTrue_Variable_visited == null) isDAafterTrue_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterTrue_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterTrue in class: ");
        isDAafterTrue_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
        isDAafterTrue_Variable_visited.remove(_parameters);
        return isDAafterTrue_Variable_value;
    }

    private boolean isDAafterTrue_compute(Variable v) {  return getOperand().isDAafterFalse(v) || isFalse();  }

    protected java.util.Map isDAafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 382
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFalse(Variable v) {
        Object _parameters = v;
if(isDAafterFalse_Variable_visited == null) isDAafterFalse_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterFalse_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterFalse in class: ");
        isDAafterFalse_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
        isDAafterFalse_Variable_visited.remove(_parameters);
        return isDAafterFalse_Variable_value;
    }

    private boolean isDAafterFalse_compute(Variable v) {  return getOperand().isDAafterTrue(v) || isTrue();  }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 384
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        Object _parameters = v;
if(isDAafter_Variable_visited == null) isDAafter_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafter in class: ");
        isDAafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        isDAafter_Variable_visited.remove(_parameters);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return isDAafterTrue(v) && isDAafterFalse(v);  }

    protected java.util.Map isDUafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 817
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

    private boolean isDUafterTrue_compute(Variable v) {  return getOperand().isDUafterFalse(v);  }

    protected java.util.Map isDUafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 818
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

    private boolean isDUafterFalse_compute(Variable v) {  return getOperand().isDUafterTrue(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 820
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        Object _parameters = v;
if(isDUafter_Variable_visited == null) isDUafter_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafter in class: ");
        isDUafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        isDUafter_Variable_visited.remove(_parameters);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return isDUafterTrue(v) && isDUafterFalse(v);  }

    protected int printPreOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 381
 @SuppressWarnings({"unchecked", "cast"})     public String printPreOp() {
        ASTNode$State state = state();
        if(printPreOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printPreOp in class: ");
        printPreOp_visited = state().boundariesCrossed;
        String printPreOp_value = printPreOp_compute();
        printPreOp_visited = -1;
        return printPreOp_value;
    }

    private String printPreOp_compute() {  return "!";  }

    // Declared in TypeAnalysis.jrag at line 318
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        if(type_computed) {
            return type_value;
        }
        ASTNode$State state = state();
        if(type_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: type in class: ");
        type_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        type_value = type_compute();
        if(isFinal && num == state().boundariesCrossed)
            type_computed = true;
        type_visited = -1;
        return type_value;
    }

    private TypeDecl type_compute() {  return typeBoolean();  }

    // Declared in DefiniteAssignment.jrag at line 383
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getOperandNoTransform()) {
            return isDAbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 819
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getOperandNoTransform()) {
            return isDUbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
