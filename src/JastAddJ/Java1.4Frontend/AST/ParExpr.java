
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class ParExpr extends PrimaryExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isConstant_visited = -1;
        varDecl_visited = -1;
        isDAafterTrue_Variable_visited = null;
        isDAafterFalse_Variable_visited = null;
        isDAafter_Variable_visited = null;
        isDUafterTrue_Variable_visited = null;
        isDUafterFalse_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isSuperAccess_visited = -1;
        isThisAccess_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        isVariable_visited = -1;
        staticContextQualifier_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParExpr clone() throws CloneNotSupportedException {
        ParExpr node = (ParExpr)super.clone();
        node.constant_visited = -1;
        node.isConstant_visited = -1;
        node.varDecl_visited = -1;
        node.isDAafterTrue_Variable_visited = null;
        node.isDAafterFalse_Variable_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDUafterTrue_Variable_visited = null;
        node.isDUafterFalse_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isSuperAccess_visited = -1;
        node.isThisAccess_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.isVariable_visited = -1;
        node.staticContextQualifier_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParExpr copy() {
      try {
          ParExpr node = (ParExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParExpr fullCopy() {
        ParExpr res = (ParExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 320


  public void toString(StringBuffer s) {
    s.append("(");
    getExpr().toString(s);
    s.append(")");
  }

    // Declared in TypeCheck.jrag at line 263


  public void typeCheck() {
    if(getExpr().isTypeAccess())
      error("" + getExpr() + " is a type and may not be used in parenthesized expression");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 134

    public ParExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 134
    public ParExpr(Expr p0) {
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
    // Declared in java.ast line 134
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

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 111
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return getExpr().constant();  }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 494
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getExpr().isConstant();  }

    protected int varDecl_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        if(varDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: varDecl in class: ");
        varDecl_visited = state().boundariesCrossed;
        Variable varDecl_value = varDecl_compute();
        varDecl_visited = -1;
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return getExpr().varDecl();  }

    protected java.util.Map isDAafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 350
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

    private boolean isDAafterTrue_compute(Variable v) {  return getExpr().isDAafterTrue(v) || isFalse();  }

    protected java.util.Map isDAafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 351
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

    private boolean isDAafterFalse_compute(Variable v) {  return getExpr().isDAafterFalse(v) || isTrue();  }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 401
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

    private boolean isDAafter_compute(Variable v) {  return getExpr().isDAafter(v);  }

    protected java.util.Map isDUafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 802
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

    private boolean isDUafterTrue_compute(Variable v) {  return getExpr().isDUafterTrue(v);  }

    protected java.util.Map isDUafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 803
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

    private boolean isDUafterFalse_compute(Variable v) {  return getExpr().isDUafterFalse(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 847
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

    private boolean isDUafter_compute(Variable v) {  return getExpr().isDUafter(v);  }

    protected int isSuperAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperAccess() {
        ASTNode$State state = state();
        if(isSuperAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSuperAccess in class: ");
        isSuperAccess_visited = state().boundariesCrossed;
        boolean isSuperAccess_value = isSuperAccess_compute();
        isSuperAccess_visited = -1;
        return isSuperAccess_value;
    }

    private boolean isSuperAccess_compute() {  return getExpr().isSuperAccess();  }

    protected int isThisAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 34
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        if(isThisAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isThisAccess in class: ");
        isThisAccess_visited = state().boundariesCrossed;
        boolean isThisAccess_value = isThisAccess_compute();
        isThisAccess_visited = -1;
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return getExpr().isThisAccess();  }

    protected int type_visited = -1;
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 309
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

    private TypeDecl type_compute() {  return getExpr().isTypeAccess() ? unknownType() : getExpr().type();  }

    protected int isVariable_visited = -1;
    // Declared in TypeCheck.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        if(isVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVariable in class: ");
        isVariable_visited = state().boundariesCrossed;
        boolean isVariable_value = isVariable_compute();
        isVariable_visited = -1;
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return getExpr().isVariable();  }

    protected int staticContextQualifier_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 151
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        if(staticContextQualifier_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: staticContextQualifier in class: ");
        staticContextQualifier_visited = state().boundariesCrossed;
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        staticContextQualifier_visited = -1;
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return getExpr().staticContextQualifier();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
