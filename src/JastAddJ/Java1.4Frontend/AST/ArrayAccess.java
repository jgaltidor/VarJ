
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class ArrayAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isArrayAccess_visited = -1;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        isVariable_visited = -1;
        unknownType_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayAccess clone() throws CloneNotSupportedException {
        ArrayAccess node = (ArrayAccess)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isArrayAccess_visited = -1;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.isVariable_visited = -1;
        node.unknownType_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayAccess copy() {
      try {
          ArrayAccess node = (ArrayAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayAccess fullCopy() {
        ArrayAccess res = (ArrayAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 513


  public void toString(StringBuffer s) {
    s.append("[");
    getExpr().toString(s);
    s.append("]");
  }

    // Declared in TypeCheck.jrag at line 137

          
  // 15.13
  public void typeCheck() {
    if(isQualified() && !qualifier().type().isArrayDecl() && !qualifier().type().isUnknown())
      error("the type " + qualifier().type().name() + " of the indexed element is not an array");
    if(!getExpr().type().unaryNumericPromotion().isInt() || !getExpr().type().isIntegralType())
      error("array index must be int after unary numeric promotion which " + getExpr().type().typeName() + " is not");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 28

    public ArrayAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 28
    public ArrayAccess(Expr p0) {
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
    // Declared in java.ast line 28
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

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 359
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
    // Declared in DefiniteAssignment.jrag at line 842
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

    protected int isArrayAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayAccess() {
        ASTNode$State state = state();
        if(isArrayAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isArrayAccess in class: ");
        isArrayAccess_visited = state().boundariesCrossed;
        boolean isArrayAccess_value = isArrayAccess_compute();
        isArrayAccess_visited = -1;
        return isArrayAccess_value;
    }

    private boolean isArrayAccess_compute() {  return true;  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 100
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.EXPRESSION_NAME;  }

    // Declared in TypeAnalysis.jrag at line 280
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

    private TypeDecl type_compute() {  return isQualified() ? qualifier().type().componentType() : unknownType();  }

    protected int isVariable_visited = -1;
    // Declared in TypeCheck.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        if(isVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVariable in class: ");
        isVariable_visited = state().boundariesCrossed;
        boolean isVariable_value = isVariable_compute();
        isVariable_visited = -1;
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return true;  }

    protected int unknownType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 281
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        ASTNode$State state = state();
        if(unknownType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unknownType in class: ");
        unknownType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        unknownType_visited = -1;
        return unknownType_value;
    }

    // Declared in DefiniteAssignment.jrag at line 34
    public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDest(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 35
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in LookupMethod.jrag at line 30
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupMethod(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 90
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().hasPackage(packageName);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 167
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupType(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 133
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getExprNoTransform()) {
            return unqualifiedScope().lookupVariable(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 122
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getExprNoTransform()) {
            return NameType.EXPRESSION_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
