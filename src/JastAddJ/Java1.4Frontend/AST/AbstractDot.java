
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class AbstractDot extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isConstant_visited = -1;
        varDecl_visited = -1;
        isDAafterTrue_Variable_visited = null;
        isDAafterFalse_Variable_visited = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafterTrue_Variable_visited = null;
        isDUafterFalse_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        typeName_visited = -1;
        isTypeAccess_visited = -1;
        isMethodAccess_visited = -1;
        isFieldAccess_visited = -1;
        isSuperAccess_visited = -1;
        isThisAccess_visited = -1;
        isPackageAccess_visited = -1;
        isArrayAccess_visited = -1;
        isClassAccess_visited = -1;
        isSuperConstructorAccess_visited = -1;
        isQualified_visited = -1;
        leftSide_visited = -1;
        rightSide_visited = -1;
        lastAccess_visited = -1;
        nextAccess_visited = -1;
        prevExpr_visited = -1;
        hasPrevExpr_visited = -1;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        isVariable_visited = -1;
        staticContextQualifier_visited = -1;
        isDUbefore_Variable_visited = null;
        isDUbefore_Variable_values = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AbstractDot clone() throws CloneNotSupportedException {
        AbstractDot node = (AbstractDot)super.clone();
        node.constant_visited = -1;
        node.isConstant_visited = -1;
        node.varDecl_visited = -1;
        node.isDAafterTrue_Variable_visited = null;
        node.isDAafterFalse_Variable_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafterTrue_Variable_visited = null;
        node.isDUafterFalse_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.typeName_visited = -1;
        node.isTypeAccess_visited = -1;
        node.isMethodAccess_visited = -1;
        node.isFieldAccess_visited = -1;
        node.isSuperAccess_visited = -1;
        node.isThisAccess_visited = -1;
        node.isPackageAccess_visited = -1;
        node.isArrayAccess_visited = -1;
        node.isClassAccess_visited = -1;
        node.isSuperConstructorAccess_visited = -1;
        node.isQualified_visited = -1;
        node.leftSide_visited = -1;
        node.rightSide_visited = -1;
        node.lastAccess_visited = -1;
        node.nextAccess_visited = -1;
        node.prevExpr_visited = -1;
        node.hasPrevExpr_visited = -1;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.isVariable_visited = -1;
        node.staticContextQualifier_visited = -1;
        node.isDUbefore_Variable_visited = null;
        node.isDUbefore_Variable_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AbstractDot copy() {
      try {
          AbstractDot node = (AbstractDot)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AbstractDot fullCopy() {
        AbstractDot res = (AbstractDot)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 445


  public void toString(StringBuffer s) {
    getLeft().toString(s);
    if(!nextAccess().isArrayAccess())
      s.append(".");
    getRight().toString(s);
  }

    // Declared in ResolveAmbiguousNames.jrag at line 130



  // These are used by the parser to extract the last name which
  // will be replaced by a method name
  public Access extractLast() {
    return getRightNoTransform();
 }

    // Declared in ResolveAmbiguousNames.jrag at line 133

  public void replaceLast(Access access) {
    setRight(access);
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 13

    public AbstractDot() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 13
    public AbstractDot(Expr p0, Access p1) {
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
    // Declared in java.ast line 13
    public void setLeft(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getLeft() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getLeftNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 13
    public void setRight(Access node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Access getRight() {
        return (Access)getChild(1);
    }

    // Declared in java.ast at line 9


    public Access getRightNoTransform() {
        return (Access)getChildNoTransform(1);
    }

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 109
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return lastAccess().constant();  }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 495
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return lastAccess().isConstant();  }

    protected int varDecl_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        if(varDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: varDecl in class: ");
        varDecl_visited = state().boundariesCrossed;
        Variable varDecl_value = varDecl_compute();
        varDecl_visited = -1;
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return lastAccess().varDecl();  }

    protected java.util.Map isDAafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 337
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

    protected java.util.Map isDAafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 338
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

    protected java.util.Map isDAafter_Variable_visited;
    protected java.util.Map isDAafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 357
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

    private boolean isDAafter_compute(Variable v) {  return lastAccess().isDAafter(v);  }

    protected java.util.Map isDUafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 796
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

    private boolean isDUafterTrue_compute(Variable v) {  return isDUafter(v);  }

    protected java.util.Map isDUafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 797
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

    private boolean isDUafterFalse_compute(Variable v) {  return isDUafter(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    protected java.util.Map isDUafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 841
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

    private boolean isDUafter_compute(Variable v) {  return lastAccess().isDUafter(v);  }

    protected int typeName_visited = -1;
    // Declared in QualifiedNames.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public String typeName() {
        ASTNode$State state = state();
        if(typeName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeName in class: ");
        typeName_visited = state().boundariesCrossed;
        String typeName_value = typeName_compute();
        typeName_visited = -1;
        return typeName_value;
    }

    private String typeName_compute() {  return lastAccess().typeName();  }

    protected int isTypeAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        ASTNode$State state = state();
        if(isTypeAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTypeAccess in class: ");
        isTypeAccess_visited = state().boundariesCrossed;
        boolean isTypeAccess_value = isTypeAccess_compute();
        isTypeAccess_visited = -1;
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return getRight().isTypeAccess();  }

    protected int isMethodAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodAccess() {
        ASTNode$State state = state();
        if(isMethodAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isMethodAccess in class: ");
        isMethodAccess_visited = state().boundariesCrossed;
        boolean isMethodAccess_value = isMethodAccess_compute();
        isMethodAccess_visited = -1;
        return isMethodAccess_value;
    }

    private boolean isMethodAccess_compute() {  return getRight().isMethodAccess();  }

    protected int isFieldAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 22
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccess() {
        ASTNode$State state = state();
        if(isFieldAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFieldAccess in class: ");
        isFieldAccess_visited = state().boundariesCrossed;
        boolean isFieldAccess_value = isFieldAccess_compute();
        isFieldAccess_visited = -1;
        return isFieldAccess_value;
    }

    private boolean isFieldAccess_compute() {  return getRight().isFieldAccess();  }

    protected int isSuperAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperAccess() {
        ASTNode$State state = state();
        if(isSuperAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSuperAccess in class: ");
        isSuperAccess_visited = state().boundariesCrossed;
        boolean isSuperAccess_value = isSuperAccess_compute();
        isSuperAccess_visited = -1;
        return isSuperAccess_value;
    }

    private boolean isSuperAccess_compute() {  return getRight().isSuperAccess();  }

    protected int isThisAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        if(isThisAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isThisAccess in class: ");
        isThisAccess_visited = state().boundariesCrossed;
        boolean isThisAccess_value = isThisAccess_compute();
        isThisAccess_visited = -1;
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return getRight().isThisAccess();  }

    protected int isPackageAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPackageAccess() {
        ASTNode$State state = state();
        if(isPackageAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPackageAccess in class: ");
        isPackageAccess_visited = state().boundariesCrossed;
        boolean isPackageAccess_value = isPackageAccess_compute();
        isPackageAccess_visited = -1;
        return isPackageAccess_value;
    }

    private boolean isPackageAccess_compute() {  return getRight().isPackageAccess();  }

    protected int isArrayAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 42
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayAccess() {
        ASTNode$State state = state();
        if(isArrayAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isArrayAccess in class: ");
        isArrayAccess_visited = state().boundariesCrossed;
        boolean isArrayAccess_value = isArrayAccess_compute();
        isArrayAccess_visited = -1;
        return isArrayAccess_value;
    }

    private boolean isArrayAccess_compute() {  return getRight().isArrayAccess();  }

    protected int isClassAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 46
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassAccess() {
        ASTNode$State state = state();
        if(isClassAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isClassAccess in class: ");
        isClassAccess_visited = state().boundariesCrossed;
        boolean isClassAccess_value = isClassAccess_compute();
        isClassAccess_visited = -1;
        return isClassAccess_value;
    }

    private boolean isClassAccess_compute() {  return getRight().isClassAccess();  }

    protected int isSuperConstructorAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperConstructorAccess() {
        ASTNode$State state = state();
        if(isSuperConstructorAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSuperConstructorAccess in class: ");
        isSuperConstructorAccess_visited = state().boundariesCrossed;
        boolean isSuperConstructorAccess_value = isSuperConstructorAccess_compute();
        isSuperConstructorAccess_visited = -1;
        return isSuperConstructorAccess_value;
    }

    private boolean isSuperConstructorAccess_compute() {  return getRight().isSuperConstructorAccess();  }

    protected int isQualified_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public boolean isQualified() {
        ASTNode$State state = state();
        if(isQualified_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isQualified in class: ");
        isQualified_visited = state().boundariesCrossed;
        boolean isQualified_value = isQualified_compute();
        isQualified_visited = -1;
        return isQualified_value;
    }

    private boolean isQualified_compute() {  return hasParentDot();  }

    protected int leftSide_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public Expr leftSide() {
        ASTNode$State state = state();
        if(leftSide_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: leftSide in class: ");
        leftSide_visited = state().boundariesCrossed;
        Expr leftSide_value = leftSide_compute();
        leftSide_visited = -1;
        return leftSide_value;
    }

    private Expr leftSide_compute() {  return getLeft();  }

    protected int rightSide_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 64
 @SuppressWarnings({"unchecked", "cast"})     public Access rightSide() {
        ASTNode$State state = state();
        if(rightSide_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: rightSide in class: ");
        rightSide_visited = state().boundariesCrossed;
        Access rightSide_value = rightSide_compute();
        rightSide_visited = -1;
        return rightSide_value;
    }

    private Access rightSide_compute() {  return getRight/*NoTransform*/() instanceof AbstractDot ? (Access)((AbstractDot)getRight/*NoTransform*/()).getLeft() : (Access)getRight();  }

    protected int lastAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 67
 @SuppressWarnings({"unchecked", "cast"})     public Access lastAccess() {
        ASTNode$State state = state();
        if(lastAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: lastAccess in class: ");
        lastAccess_visited = state().boundariesCrossed;
        Access lastAccess_value = lastAccess_compute();
        lastAccess_visited = -1;
        return lastAccess_value;
    }

    private Access lastAccess_compute() {  return getRight().lastAccess();  }

    protected int nextAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 75
 @SuppressWarnings({"unchecked", "cast"})     public Access nextAccess() {
        ASTNode$State state = state();
        if(nextAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: nextAccess in class: ");
        nextAccess_visited = state().boundariesCrossed;
        Access nextAccess_value = nextAccess_compute();
        nextAccess_visited = -1;
        return nextAccess_value;
    }

    private Access nextAccess_compute() {  return rightSide();  }

    // Declared in ResolveAmbiguousNames.jrag at line 77
 @SuppressWarnings({"unchecked", "cast"})     public Expr prevExpr() {
        ASTNode$State state = state();
        if(prevExpr_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: prevExpr in class: ");
        prevExpr_visited = state().boundariesCrossed;
        Expr prevExpr_value = prevExpr_compute();
        prevExpr_visited = -1;
        return prevExpr_value;
    }

    private Expr prevExpr_compute() {  return leftSide();  }

    // Declared in ResolveAmbiguousNames.jrag at line 88
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasPrevExpr() {
        ASTNode$State state = state();
        if(hasPrevExpr_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasPrevExpr in class: ");
        hasPrevExpr_visited = state().boundariesCrossed;
        boolean hasPrevExpr_value = hasPrevExpr_compute();
        hasPrevExpr_visited = -1;
        return hasPrevExpr_value;
    }

    private boolean hasPrevExpr_compute() {  return true;  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 60
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return getLeft() instanceof Access ? ((Access)getLeft()).predNameType() : NameType.NO_NAME;  }

    // Declared in TypeAnalysis.jrag at line 249
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

    private TypeDecl type_compute() {  return lastAccess().type();  }

    protected int isVariable_visited = -1;
    // Declared in TypeCheck.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        if(isVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVariable in class: ");
        isVariable_visited = state().boundariesCrossed;
        boolean isVariable_value = isVariable_compute();
        isVariable_visited = -1;
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return lastAccess().isVariable();  }

    protected int staticContextQualifier_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 153
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        if(staticContextQualifier_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: staticContextQualifier in class: ");
        staticContextQualifier_visited = state().boundariesCrossed;
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        staticContextQualifier_visited = -1;
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return lastAccess().staticContextQualifier();  }

    protected java.util.Map isDUbefore_Variable_visited;
    protected java.util.Map isDUbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 700
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_visited == null) isDUbefore_Variable_visited = new java.util.HashMap(4);
if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
        if(isDUbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUbefore in class: ");
        isDUbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDUbefore_Variable_values.put(_parameters, Boolean.valueOf(isDUbefore_Variable_value));
        isDUbefore_Variable_visited.remove(_parameters);
        return isDUbefore_Variable_value;
    }

    // Declared in DefiniteAssignment.jrag at line 21
    public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
        if(caller == getLeftNoTransform()) {
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDest(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 31
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getLeftNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 356
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getRightNoTransform()) {
            return getLeft().isDAafter(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 840
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getRightNoTransform()) {
            return getLeft().isDUafter(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in LookupConstructor.jrag at line 17
    public Collection Define_Collection_lookupConstructor(ASTNode caller, ASTNode child) {
        if(caller == getRightNoTransform()) {
            return getLeft().type().constructors();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupConstructor(this, caller);
    }

    // Declared in LookupConstructor.jrag at line 25
    public Collection Define_Collection_lookupSuperConstructor(ASTNode caller, ASTNode child) {
        if(caller == getRightNoTransform()) {
            return getLeft().type().lookupSuperConstructor();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupSuperConstructor(this, caller);
    }

    // Declared in LookupMethod.jrag at line 20
    public Expr Define_Expr_nestedScope(ASTNode caller, ASTNode child) {
        if(caller == getLeftNoTransform()) {
            return isQualified() ? nestedScope() : this;
        }
        if(caller == getRightNoTransform()) {
            return isQualified() ? nestedScope() : this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Expr_nestedScope(this, caller);
    }

    // Declared in LookupMethod.jrag at line 64
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getRightNoTransform()) {
            return getLeft().type().memberMethods(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 82
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getRightNoTransform()) {
            return getLeft().hasQualifiedPackage(packageName);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 341
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getRightNoTransform()) {
            return getLeft().qualifiedLookupType(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 137
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getRightNoTransform()) {
            return getLeft().qualifiedLookupVariable(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 59
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getLeftNoTransform()) {
            return getRight().predNameType();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeCheck.jrag at line 516
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        if(caller == getRightNoTransform()) {
            return getLeft().type();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_enclosingInstance(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 13
    public String Define_String_methodHost(ASTNode caller, ASTNode child) {
        if(caller == getRightNoTransform()) {
            return getLeft().type().typeName();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_methodHost(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
