
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public abstract class Access extends Expr implements Cloneable {
    public void flushCache() {
        super.flushCache();
        unqualifiedScope_visited = -1;
        isQualified_visited = -1;
        qualifier_visited = -1;
        lastAccess_visited = -1;
        prevExpr_visited = -1;
        prevExpr_computed = false;
        prevExpr_value = null;
        hasPrevExpr_visited = -1;
        hasPrevExpr_computed = false;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        nestedScope_visited = -1;
        unknownType_visited = -1;
        unknownField_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Access clone() throws CloneNotSupportedException {
        Access node = (Access)super.clone();
        node.unqualifiedScope_visited = -1;
        node.isQualified_visited = -1;
        node.qualifier_visited = -1;
        node.lastAccess_visited = -1;
        node.prevExpr_visited = -1;
        node.prevExpr_computed = false;
        node.prevExpr_value = null;
        node.hasPrevExpr_visited = -1;
        node.hasPrevExpr_computed = false;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.nestedScope_visited = -1;
        node.unknownType_visited = -1;
        node.unknownField_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in ResolveAmbiguousNames.jrag at line 143


  public Access addArrayDims(List list) {
    Access a = this;
    for(int i = 0; i < list.getNumChildNoTransform(); i++) {
      Dims dims = (Dims)list.getChildNoTransform(i);
      Opt opt = dims.getExprOpt();
      if(opt.getNumChildNoTransform() == 1)
        a = new ArrayTypeWithSizeAccess(a, (Expr)opt.getChildNoTransform(0));
      else
        a = new ArrayTypeAccess(a);
      a.setStart(dims.start());
      a.setEnd(dims.end());
    }
    return a;
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 11

    public Access() {
        super();


    }

    // Declared in java.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    protected int unqualifiedScope_visited = -1;
    // Declared in LookupMethod.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public Expr unqualifiedScope() {
        ASTNode$State state = state();
        if(unqualifiedScope_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unqualifiedScope in class: ");
        unqualifiedScope_visited = state().boundariesCrossed;
        Expr unqualifiedScope_value = unqualifiedScope_compute();
        unqualifiedScope_visited = -1;
        return unqualifiedScope_value;
    }

    private Expr unqualifiedScope_compute() {  return isQualified() ? nestedScope() : this;  }

    protected int isQualified_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public boolean isQualified() {
        ASTNode$State state = state();
        if(isQualified_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isQualified in class: ");
        isQualified_visited = state().boundariesCrossed;
        boolean isQualified_value = isQualified_compute();
        isQualified_visited = -1;
        return isQualified_value;
    }

    private boolean isQualified_compute() {  return hasPrevExpr();  }

    protected int qualifier_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public Expr qualifier() {
        ASTNode$State state = state();
        if(qualifier_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: qualifier in class: ");
        qualifier_visited = state().boundariesCrossed;
        Expr qualifier_value = qualifier_compute();
        qualifier_visited = -1;
        return qualifier_value;
    }

    private Expr qualifier_compute() {  return prevExpr();  }

    protected int lastAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public Access lastAccess() {
        ASTNode$State state = state();
        if(lastAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: lastAccess in class: ");
        lastAccess_visited = state().boundariesCrossed;
        Access lastAccess_value = lastAccess_compute();
        lastAccess_visited = -1;
        return lastAccess_value;
    }

    private Access lastAccess_compute() {  return this;  }

    protected int prevExpr_visited = -1;
    protected boolean prevExpr_computed = false;
    protected Expr prevExpr_value;
    // Declared in ResolveAmbiguousNames.jrag at line 78
 @SuppressWarnings({"unchecked", "cast"})     public Expr prevExpr() {
        if(prevExpr_computed) {
            return prevExpr_value;
        }
        ASTNode$State state = state();
        if(prevExpr_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: prevExpr in class: ");
        prevExpr_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        prevExpr_value = prevExpr_compute();
        if(isFinal && num == state().boundariesCrossed)
            prevExpr_computed = true;
        prevExpr_visited = -1;
        return prevExpr_value;
    }

    private Expr prevExpr_compute() {
    if(isLeftChildOfDot()) {
      if(parentDot().isRightChildOfDot())
        return parentDot().parentDot().leftSide();
    }
    else if(isRightChildOfDot())
      return parentDot().leftSide();
    throw new Error(this + " does not have a previous expression");
  }

    protected int hasPrevExpr_visited = -1;
    protected boolean hasPrevExpr_computed = false;
    protected boolean hasPrevExpr_value;
    // Declared in ResolveAmbiguousNames.jrag at line 89
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasPrevExpr() {
        if(hasPrevExpr_computed) {
            return hasPrevExpr_value;
        }
        ASTNode$State state = state();
        if(hasPrevExpr_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasPrevExpr in class: ");
        hasPrevExpr_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        hasPrevExpr_value = hasPrevExpr_compute();
        if(isFinal && num == state().boundariesCrossed)
            hasPrevExpr_computed = true;
        hasPrevExpr_visited = -1;
        return hasPrevExpr_value;
    }

    private boolean hasPrevExpr_compute() {
    if(isLeftChildOfDot()) {
      if(parentDot().isRightChildOfDot())
        return true;
    }
    else if(isRightChildOfDot())
      return true;
    return false;
  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.NO_NAME;  }

    protected int type_visited = -1;
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 278
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

    private TypeDecl type_compute() {  return unknownType();  }

    protected int nestedScope_visited = -1;
    // Declared in LookupMethod.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public Expr nestedScope() {
        ASTNode$State state = state();
        if(nestedScope_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: nestedScope in class: ");
        nestedScope_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Expr nestedScope_value = getParent().Define_Expr_nestedScope(this, null);
        nestedScope_visited = -1;
        return nestedScope_value;
    }

    protected int unknownType_visited = -1;
    // Declared in LookupType.jrag at line 133
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

    protected int unknownField_visited = -1;
    // Declared in LookupVariable.jrag at line 228
 @SuppressWarnings({"unchecked", "cast"})     public Variable unknownField() {
        ASTNode$State state = state();
        if(unknownField_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unknownField in class: ");
        unknownField_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Variable unknownField_value = getParent().Define_Variable_unknownField(this, null);
        unknownField_visited = -1;
        return unknownField_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
