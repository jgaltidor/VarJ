
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class CastExpr extends Expr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isConstant_visited = -1;
        isDAafter_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isSuperAccess_visited = -1;
        isThisAccess_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        staticContextQualifier_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public CastExpr clone() throws CloneNotSupportedException {
        CastExpr node = (CastExpr)super.clone();
        node.constant_visited = -1;
        node.isConstant_visited = -1;
        node.isDAafter_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isSuperAccess_visited = -1;
        node.isThisAccess_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.staticContextQualifier_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CastExpr copy() {
      try {
          CastExpr node = (CastExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CastExpr fullCopy() {
        CastExpr res = (CastExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 384

  

  public void toString(StringBuffer s) {
    s.append("(");
    getTypeAccess().toString(s);
    s.append(")");
    getExpr().toString(s);
  }

    // Declared in TypeCheck.jrag at line 252

  
  // 15.16
  public void typeCheck() {
    TypeDecl expr = getExpr().type();
    TypeDecl type = getTypeAccess().type();
    if(!expr.isUnknown()) {
      if(!expr.castingConversionTo(type))
        error(expr.typeName() + " can not be cast into " + type.typeName());
      if(!getTypeAccess().isTypeAccess())
        error("" + getTypeAccess() + " is not a type access in cast expression");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 147

    public CastExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 147
    public CastExpr(Access p0, Expr p1) {
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
    // Declared in java.ast line 147
    public void setTypeAccess(Access node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(0);
    }

    // Declared in java.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 147
    public void setExpr(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 110
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return type().cast(getExpr().constant());  }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 485
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getExpr().isConstant() &&
    (getTypeAccess().type().isPrimitive() || getTypeAccess().type().isString());  }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 403
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

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 849
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
    // Declared in ResolveAmbiguousNames.jrag at line 29
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
    // Declared in ResolveAmbiguousNames.jrag at line 35
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
    // Declared in TypeAnalysis.jrag at line 320
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

    private TypeDecl type_compute() {  return getTypeAccess().type();  }

    protected int staticContextQualifier_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 152
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

    // Declared in SyntacticClassification.jrag at line 88
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
