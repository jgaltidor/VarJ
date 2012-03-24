
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class InstanceOfExpr extends Expr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isConstant_visited = -1;
        isDAafterFalse_Variable_visited = null;
        isDAafterTrue_Variable_visited = null;
        isDAafter_Variable_visited = null;
        isDUafter_Variable_visited = null;
        type_visited = -1;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public InstanceOfExpr clone() throws CloneNotSupportedException {
        InstanceOfExpr node = (InstanceOfExpr)super.clone();
        node.isConstant_visited = -1;
        node.isDAafterFalse_Variable_visited = null;
        node.isDAafterTrue_Variable_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public InstanceOfExpr copy() {
      try {
          InstanceOfExpr node = (InstanceOfExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public InstanceOfExpr fullCopy() {
        InstanceOfExpr res = (InstanceOfExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 420


  public void toString(StringBuffer s) {
    getExpr().toString(s);
    s.append(" instanceof ");
    getTypeAccess().toString(s);
  }

    // Declared in TypeCheck.jrag at line 235


  // 15.20.2
  public void typeCheck() {
    TypeDecl relationalExpr = getExpr().type();
    TypeDecl referenceType = getTypeAccess().type();
    if(!relationalExpr.isUnknown()) {
      if(!relationalExpr.isReferenceType() && !relationalExpr.isNull())
        error("The relational expression in instance of must be reference or null type");
      if(!referenceType.isReferenceType())
        error("The reference expression in instance of must be reference type");
      if(!relationalExpr.castingConversionTo(referenceType))
        error("The type " + relationalExpr.typeName() + " of the relational expression " + 
          getExpr() +  " can not be cast into the type " + referenceType.typeName());
      if(getExpr().isTypeAccess())
        error("The relational expression " + getExpr() + " must not be a type name");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 188

    public InstanceOfExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 188
    public InstanceOfExpr(Expr p0, Access p1) {
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
    // Declared in java.ast line 188
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
    // Declared in java.ast line 188
    public void setTypeAccess(Access node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Access getTypeAccess() {
        return (Access)getChild(1);
    }

    // Declared in java.ast at line 9


    public Access getTypeAccessNoTransform() {
        return (Access)getChildNoTransform(1);
    }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 492
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return false;  }

    protected java.util.Map isDAafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 347
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

    private boolean isDAafterFalse_compute(Variable v) {  return isDAafter(v);  }

    protected java.util.Map isDAafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 348
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

    private boolean isDAafterTrue_compute(Variable v) {  return isDAafter(v);  }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 411
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
    // Declared in DefiniteAssignment.jrag at line 854
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

    protected int type_visited = -1;
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 361
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

    // Declared in SyntacticClassification.jrag at line 89
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
