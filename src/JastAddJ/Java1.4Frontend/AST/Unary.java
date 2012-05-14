
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public abstract class Unary extends Expr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDUafter_Variable_visited = null;
        printPostOp_visited = -1;
        printPreOp_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Unary clone() throws CloneNotSupportedException {
        Unary node = (Unary)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.printPostOp_visited = -1;
        node.printPreOp_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in PrettyPrint.jadd at line 365


  // Pre and post operations for unary expression
  
  public void toString(StringBuffer s) {
    s.append(printPreOp());
    getOperand().toString(s);
    s.append(printPostOp());
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 139

    public Unary() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 139
    public Unary(Expr p0) {
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

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 402
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

    private boolean isDAafter_compute(Variable v) {  return getOperand().isDAafter(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 848
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

    private boolean isDUafter_compute(Variable v) {  return getOperand().isDUafter(v);  }

    protected int printPostOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 371
 @SuppressWarnings({"unchecked", "cast"})     public String printPostOp() {
        ASTNode$State state = state();
        if(printPostOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printPostOp in class: ");
        printPostOp_visited = state().boundariesCrossed;
        String printPostOp_value = printPostOp_compute();
        printPostOp_visited = -1;
        return printPostOp_value;
    }

    private String printPostOp_compute() {  return "";  }

    protected int printPreOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 375
 @SuppressWarnings({"unchecked", "cast"})     public String printPreOp() {
        ASTNode$State state = state();
        if(printPreOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printPreOp in class: ");
        printPreOp_visited = state().boundariesCrossed;
        String printPreOp_value = printPreOp_compute();
        printPreOp_visited = -1;
        return printPreOp_value;
    }

    private String printPreOp_compute() {  return "";  }

    protected int type_visited = -1;
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 314
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

    private TypeDecl type_compute() {  return getOperand().type();  }

    // Declared in DefiniteAssignment.jrag at line 44
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getOperandNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isSource(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
