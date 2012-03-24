
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class ThrowStmt extends Stmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
        typeNullPointerException_visited = -1;
        typeNullPointerException_computed = false;
        typeNullPointerException_value = null;
        handlesException_TypeDecl_visited = null;
        handlesException_TypeDecl_values = null;
        typeThrowable_visited = -1;
        typeThrowable_computed = false;
        typeThrowable_value = null;
        typeNull_visited = -1;
        typeNull_computed = false;
        typeNull_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ThrowStmt clone() throws CloneNotSupportedException {
        ThrowStmt node = (ThrowStmt)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.typeNullPointerException_visited = -1;
        node.typeNullPointerException_computed = false;
        node.typeNullPointerException_value = null;
        node.handlesException_TypeDecl_visited = null;
        node.handlesException_TypeDecl_values = null;
        node.typeThrowable_visited = -1;
        node.typeThrowable_computed = false;
        node.typeThrowable_value = null;
        node.typeNull_visited = -1;
        node.typeNull_computed = false;
        node.typeNull_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ThrowStmt copy() {
      try {
          ThrowStmt node = (ThrowStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ThrowStmt fullCopy() {
        ThrowStmt res = (ThrowStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AnonymousClasses.jrag at line 195


  protected void collectExceptions(Collection c, ASTNode target) {
    super.collectExceptions(c, target);
    TypeDecl exceptionType = getExpr().type();
    if(exceptionType == typeNull())
      exceptionType = typeNullPointerException();
    c.add(exceptionType);
  }

    // Declared in ExceptionHandling.jrag at line 105


  public void exceptionHandling() {
    TypeDecl exceptionType = getExpr().type();
    if(exceptionType == typeNull())
      exceptionType = typeNullPointerException();
    // 8.4.4
    if(!handlesException(exceptionType))
      error("" + this + " throws uncaught exception " + exceptionType.fullName());
  }

    // Declared in ExceptionHandling.jrag at line 234

  
  protected boolean reachedException(TypeDecl catchType) {
    TypeDecl exceptionType = getExpr().type();
    if(exceptionType == typeNull())
      exceptionType = typeNullPointerException();
    if(catchType.mayCatch(exceptionType))
      return true;
    return super.reachedException(catchType);
  }

    // Declared in PrettyPrint.jadd at line 691


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("throw ");
    getExpr().toString(s);
    s.append(";");
  }

    // Declared in TypeCheck.jrag at line 373


  public void typeCheck() {
    if(!getExpr().type().instanceOf(typeThrowable()))
      error("*** The thrown expression must extend Throwable");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 218

    public ThrowStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 218
    public ThrowStmt(Expr p0) {
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
    // Declared in java.ast line 218
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

    // Declared in DefiniteAssignment.jrag at line 653
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

    private boolean isDAafter_compute(Variable v) {  return true;  }

    // Declared in DefiniteAssignment.jrag at line 1179
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

    private boolean isDUafter_compute(Variable v) {  return true;  }

    // Declared in UnreachableStatements.jrag at line 108
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

    private boolean canCompleteNormally_compute() {  return false;  }

    protected int typeNullPointerException_visited = -1;
    protected boolean typeNullPointerException_computed = false;
    protected TypeDecl typeNullPointerException_value;
    // Declared in ExceptionHandling.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeNullPointerException() {
        if(typeNullPointerException_computed) {
            return typeNullPointerException_value;
        }
        ASTNode$State state = state();
        if(typeNullPointerException_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeNullPointerException in class: ");
        typeNullPointerException_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeNullPointerException_value = getParent().Define_TypeDecl_typeNullPointerException(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeNullPointerException_computed = true;
        typeNullPointerException_visited = -1;
        return typeNullPointerException_value;
    }

    protected java.util.Map handlesException_TypeDecl_visited;
    protected java.util.Map handlesException_TypeDecl_values;
    // Declared in ExceptionHandling.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        Object _parameters = exceptionType;
if(handlesException_TypeDecl_visited == null) handlesException_TypeDecl_visited = new java.util.HashMap(4);
if(handlesException_TypeDecl_values == null) handlesException_TypeDecl_values = new java.util.HashMap(4);
        if(handlesException_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)handlesException_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(handlesException_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: handlesException in class: ");
        handlesException_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        if(isFinal && num == state().boundariesCrossed)
            handlesException_TypeDecl_values.put(_parameters, Boolean.valueOf(handlesException_TypeDecl_value));
        handlesException_TypeDecl_visited.remove(_parameters);
        return handlesException_TypeDecl_value;
    }

    protected int typeThrowable_visited = -1;
    protected boolean typeThrowable_computed = false;
    protected TypeDecl typeThrowable_value;
    // Declared in LookupType.jrag at line 67
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeThrowable() {
        if(typeThrowable_computed) {
            return typeThrowable_value;
        }
        ASTNode$State state = state();
        if(typeThrowable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeThrowable in class: ");
        typeThrowable_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeThrowable_value = getParent().Define_TypeDecl_typeThrowable(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeThrowable_computed = true;
        typeThrowable_visited = -1;
        return typeThrowable_value;
    }

    protected int typeNull_visited = -1;
    protected boolean typeNull_computed = false;
    protected TypeDecl typeNull_value;
    // Declared in LookupType.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeNull() {
        if(typeNull_computed) {
            return typeNull_value;
        }
        ASTNode$State state = state();
        if(typeNull_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeNull in class: ");
        typeNull_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeNull_value = getParent().Define_TypeDecl_typeNull(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeNull_computed = true;
        typeNull_visited = -1;
        return typeNull_value;
    }

    // Declared in DefiniteAssignment.jrag at line 656
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getExprNoTransform()) {
            return isDAbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1182
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getExprNoTransform()) {
            return isDUbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
