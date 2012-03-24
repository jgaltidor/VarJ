
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class SynchronizedStmt extends Stmt implements Cloneable, FinallyHost {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafterFinally_Variable_visited = null;
        isDAafterFinally_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public SynchronizedStmt clone() throws CloneNotSupportedException {
        SynchronizedStmt node = (SynchronizedStmt)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafterFinally_Variable_visited = null;
        node.isDAafterFinally_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SynchronizedStmt copy() {
      try {
          SynchronizedStmt node = (SynchronizedStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SynchronizedStmt fullCopy() {
        SynchronizedStmt res = (SynchronizedStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BranchTarget.jrag at line 207

  public void collectFinally(Stmt branchStmt, ArrayList list) {
    list.add(this);
    super.collectFinally(branchStmt, list);
  }

    // Declared in PrettyPrint.jadd at line 698


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("synchronized(");
    getExpr().toString(s);
    s.append(") ");
    getBlock().toString(s);
  }

    // Declared in TypeCheck.jrag at line 362


  public void typeCheck() {
    TypeDecl type = getExpr().type();
    if(!type.isReferenceType() || type.isNull())
      error("*** The type of the expression must be a reference");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 220

    public SynchronizedStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 220
    public SynchronizedStmt(Expr p0, Block p1) {
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
    // Declared in java.ast line 220
    public void setExpr(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 220
    public void setBlock(Block node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Block getBlock() {
        return (Block)getChild(1);
    }

    // Declared in java.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(1);
    }

    // Declared in DefiniteAssignment.jrag at line 658
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

    private boolean isDAafter_compute(Variable v) {  return getBlock().isDAafter(v);  }

    protected java.util.Map isDUafterFinally_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 921
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterFinally(Variable v) {
        Object _parameters = v;
if(isDUafterFinally_Variable_visited == null) isDUafterFinally_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterFinally_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterFinally in class: ");
        isDUafterFinally_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterFinally_Variable_value = isDUafterFinally_compute(v);
        isDUafterFinally_Variable_visited.remove(_parameters);
        return isDUafterFinally_Variable_value;
    }

    private boolean isDUafterFinally_compute(Variable v) {  return true;  }

    protected java.util.Map isDAafterFinally_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 924
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFinally(Variable v) {
        Object _parameters = v;
if(isDAafterFinally_Variable_visited == null) isDAafterFinally_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterFinally_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterFinally in class: ");
        isDAafterFinally_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterFinally_Variable_value = isDAafterFinally_compute(v);
        isDAafterFinally_Variable_visited.remove(_parameters);
        return isDAafterFinally_Variable_value;
    }

    private boolean isDAafterFinally_compute(Variable v) {  return false;  }

    // Declared in DefiniteAssignment.jrag at line 1184
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

    private boolean isDUafter_compute(Variable v) {  return getBlock().isDUafter(v);  }

    // Declared in UnreachableStatements.jrag at line 110
 @SuppressWarnings({"unchecked", "cast"})     public boolean canCompleteNormally() {
        if(canCompleteNormally_computed) {
            return canCompleteNormally_value;
        }
        ASTNode$State state = state();
        if(canCompleteNormally_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: canCompleteNormally in class: ");
        canCompleteNormally_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        canCompleteNormally_value = canCompleteNormally_compute();
        if(isFinal && num == state().boundariesCrossed)
            canCompleteNormally_computed = true;
        canCompleteNormally_visited = -1;
        return canCompleteNormally_value;
    }

    private boolean canCompleteNormally_compute() {  return getBlock().canCompleteNormally();  }

    // Declared in DefiniteAssignment.jrag at line 660
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBlockNoTransform()) {
            return getExpr().isDAafter(v);
        }
        if(caller == getExprNoTransform()) {
            return isDAbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1186
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBlockNoTransform()) {
            return getExpr().isDUafter(v);
        }
        if(caller == getExprNoTransform()) {
            return isDUbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in UnreachableStatements.jrag at line 111
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 155
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
