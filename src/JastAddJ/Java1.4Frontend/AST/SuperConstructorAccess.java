
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class SuperConstructorAccess extends ConstructorAccess implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDUafter_Variable_visited = null;
        decls_visited = -1;
        decls_computed = false;
        decls_value = null;
        name_visited = -1;
        isSuperConstructorAccess_visited = -1;
        predNameType_visited = -1;
        lookupSuperConstructor_visited = -1;
        enclosingInstance_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public SuperConstructorAccess clone() throws CloneNotSupportedException {
        SuperConstructorAccess node = (SuperConstructorAccess)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.decls_visited = -1;
        node.decls_computed = false;
        node.decls_value = null;
        node.name_visited = -1;
        node.isSuperConstructorAccess_visited = -1;
        node.predNameType_visited = -1;
        node.lookupSuperConstructor_visited = -1;
        node.enclosingInstance_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SuperConstructorAccess copy() {
      try {
          SuperConstructorAccess node = (SuperConstructorAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SuperConstructorAccess fullCopy() {
        SuperConstructorAccess res = (SuperConstructorAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TypeHierarchyCheck.jrag at line 63


  public void nameCheck() {
    super.nameCheck();
    // 8.8.5.1
    TypeDecl c = hostType();
    TypeDecl s = c.isClassDecl() && ((ClassDecl)c).hasSuperclass() ? ((ClassDecl)c).superclass() : unknownType();
    if(isQualified()) {
      if(!s.isInnerType() || s.inStaticContext())
        error("the super type " + s.typeName() + " of " + c.typeName() +
           " is not an inner class");
    
      else if(!qualifier().type().instanceOf(s.enclosingType()))
        error("The type of this primary expression, " +
                qualifier().type().typeName() + " is not enclosing the super type, " + 
                s.typeName() + ", of " + c.typeName());
    }
    if(!isQualified() && s.isInnerType()) {
      if(!c.isInnerType()) {
        error("no enclosing instance for " + s.typeName() + " when accessed in " + this);
      }
    }
    if(s.isInnerType() && hostType().instanceOf(s.enclosingType()))
      error("cannot reference this before supertype constructor has been called");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 19

    public SuperConstructorAccess() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 19
    public SuperConstructorAccess(String p0, List<Expr> p1) {
        setID(p0);
        setChild(p1, 0);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 19
    public SuperConstructorAccess(beaver.Symbol p0, List<Expr> p1) {
        setID(p0);
        setChild(p1, 0);
    }

    // Declared in java.ast at line 22


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 25

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 18
    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 5

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 12

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 18
    public void setArgList(List<Expr> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumArg() {
        return getArgList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getArg(int i) {
        return (Expr)getArgList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addArg(Expr node) {
        List<Expr> list = (parent == null || state == null) ? getArgListNoTransform() : getArgList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addArgNoTransform(Expr node) {
        List<Expr> list = getArgListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setArg(Expr node, int i) {
        List<Expr> list = getArgList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Expr> getArgs() {
        return getArgList();
    }

    // Declared in java.ast at line 31

    public List<Expr> getArgsNoTransform() {
        return getArgListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgList() {
        List<Expr> list = (List<Expr>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Expr> getArgListNoTransform() {
        return (List<Expr>)getChildNoTransform(0);
    }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 299
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

    private boolean isDAafter_compute(Variable v) {  return isDAbefore(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 757
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

    private boolean isDUafter_compute(Variable v) {  return isDUbefore(v);  }

    // Declared in LookupConstructor.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet decls() {
        if(decls_computed) {
            return decls_value;
        }
        ASTNode$State state = state();
        if(decls_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: decls in class: ");
        decls_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decls_value = decls_compute();
        if(isFinal && num == state().boundariesCrossed)
            decls_computed = true;
        decls_visited = -1;
        return decls_value;
    }

    private SimpleSet decls_compute() {
    Collection c = hasPrevExpr() && !prevExpr().isTypeAccess() ?
      hostType().lookupSuperConstructor() : lookupSuperConstructor();
    return mostSpecificConstructor(c);
  }

    protected int name_visited = -1;
    // Declared in QualifiedNames.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        if(name_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = state().boundariesCrossed;
        String name_value = name_compute();
        name_visited = -1;
        return name_value;
    }

    private String name_compute() {  return "super";  }

    protected int isSuperConstructorAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperConstructorAccess() {
        ASTNode$State state = state();
        if(isSuperConstructorAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSuperConstructorAccess in class: ");
        isSuperConstructorAccess_visited = state().boundariesCrossed;
        boolean isSuperConstructorAccess_value = isSuperConstructorAccess_compute();
        isSuperConstructorAccess_visited = -1;
        return isSuperConstructorAccess_value;
    }

    private boolean isSuperConstructorAccess_compute() {  return true;  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 96
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

    protected int lookupSuperConstructor_visited = -1;
    // Declared in LookupConstructor.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupSuperConstructor() {
        ASTNode$State state = state();
        if(lookupSuperConstructor_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: lookupSuperConstructor in class: ");
        lookupSuperConstructor_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Collection lookupSuperConstructor_value = getParent().Define_Collection_lookupSuperConstructor(this, null);
        lookupSuperConstructor_visited = -1;
        return lookupSuperConstructor_value;
    }

    protected int enclosingInstance_visited = -1;
    // Declared in TypeCheck.jrag at line 503
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl enclosingInstance() {
        ASTNode$State state = state();
        if(enclosingInstance_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingInstance in class: ");
        enclosingInstance_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl enclosingInstance_value = getParent().Define_TypeDecl_enclosingInstance(this, null);
        enclosingInstance_visited = -1;
        return enclosingInstance_value;
    }

    // Declared in LookupType.jrag at line 89
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().hasPackage(packageName);
        }
        return super.Define_boolean_hasPackage(caller, child, packageName);
    }

    // Declared in LookupVariable.jrag at line 132
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupVariable(name);
        }
        return super.Define_SimpleSet_lookupVariable(caller, child, name);
    }

    // Declared in TypeHierarchyCheck.jrag at line 131
    public boolean Define_boolean_inExplicitConstructorInvocation(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        return super.Define_boolean_inExplicitConstructorInvocation(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
