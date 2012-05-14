
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class ArrayCreationExpr extends PrimaryExpr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafterCreation_Variable_visited = null;
        isDAafter_Variable_visited = null;
        isDUafterCreation_Variable_visited = null;
        isDUafter_Variable_visited = null;
        type_visited = -1;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayCreationExpr clone() throws CloneNotSupportedException {
        ArrayCreationExpr node = (ArrayCreationExpr)super.clone();
        node.isDAafterCreation_Variable_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDUafterCreation_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayCreationExpr copy() {
      try {
          ArrayCreationExpr node = (ArrayCreationExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ArrayCreationExpr fullCopy() {
        ArrayCreationExpr res = (ArrayCreationExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 355


  public void toString(StringBuffer s) {
    s.append("new ");
    getTypeAccess().toString(s);
    if(hasArrayInit()) {
      getArrayInit().toString(s);
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 136

    public ArrayCreationExpr() {
        super();

        setChild(new Opt(), 1);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 136
    public ArrayCreationExpr(Access p0, Opt<ArrayInit> p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in java.ast at line 16


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 19

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 136
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
    // Declared in java.ast line 136
    public void setArrayInitOpt(Opt<ArrayInit> opt) {
        setChild(opt, 1);
    }

    // Declared in java.ast at line 6


    public boolean hasArrayInit() {
        return getArrayInitOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public ArrayInit getArrayInit() {
        return (ArrayInit)getArrayInitOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setArrayInit(ArrayInit node) {
        getArrayInitOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<ArrayInit> getArrayInitOpt() {
        return (Opt<ArrayInit>)getChild(1);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<ArrayInit> getArrayInitOptNoTransform() {
        return (Opt<ArrayInit>)getChildNoTransform(1);
    }

    protected java.util.Map isDAafterCreation_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 435
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterCreation(Variable v) {
        Object _parameters = v;
if(isDAafterCreation_Variable_visited == null) isDAafterCreation_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterCreation_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterCreation in class: ");
        isDAafterCreation_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterCreation_Variable_value = isDAafterCreation_compute(v);
        isDAafterCreation_Variable_visited.remove(_parameters);
        return isDAafterCreation_Variable_value;
    }

    private boolean isDAafterCreation_compute(Variable v) {  return getTypeAccess().isDAafter(v);  }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 436
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

    private boolean isDAafter_compute(Variable v) {  return hasArrayInit() ? getArrayInit().isDAafter(v) : isDAafterCreation(v);  }

    protected java.util.Map isDUafterCreation_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 866
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterCreation(Variable v) {
        Object _parameters = v;
if(isDUafterCreation_Variable_visited == null) isDUafterCreation_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterCreation_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterCreation in class: ");
        isDUafterCreation_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterCreation_Variable_value = isDUafterCreation_compute(v);
        isDUafterCreation_Variable_visited.remove(_parameters);
        return isDUafterCreation_Variable_value;
    }

    private boolean isDUafterCreation_compute(Variable v) {  return getTypeAccess().isDUafter(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 867
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

    private boolean isDUafter_compute(Variable v) {  return hasArrayInit() ? getArrayInit().isDUafter(v) : isDUafterCreation(v);  }

    protected int type_visited = -1;
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 312
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

    // Declared in DefiniteAssignment.jrag at line 437
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getArrayInitOptNoTransform()) {
            return isDAafterCreation(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 869
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getArrayInitOptNoTransform()) {
            return isDUafterCreation(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in SyntacticClassification.jrag at line 87
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 262
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        if(caller == getArrayInitOptNoTransform()) {
            return type();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_declType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
