
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class ConstructorAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDUafter_Variable_visited = null;
        applicableAndAccessible_ConstructorDecl_visited = null;
        decls_visited = -1;
        decls_computed = false;
        decls_value = null;
        decl_visited = -1;
        decl_computed = false;
        decl_value = null;
        validArgs_visited = -1;
        name_visited = -1;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        handlesException_TypeDecl_visited = null;
        lookupConstructor_visited = -1;
        unknownConstructor_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorAccess clone() throws CloneNotSupportedException {
        ConstructorAccess node = (ConstructorAccess)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.applicableAndAccessible_ConstructorDecl_visited = null;
        node.decls_visited = -1;
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_visited = -1;
        node.decl_computed = false;
        node.decl_value = null;
        node.validArgs_visited = -1;
        node.name_visited = -1;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.handlesException_TypeDecl_visited = null;
        node.lookupConstructor_visited = -1;
        node.unknownConstructor_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorAccess copy() {
      try {
          ConstructorAccess node = (ConstructorAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstructorAccess fullCopy() {
        ConstructorAccess res = (ConstructorAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ExceptionHandling.jrag at line 97


  public void exceptionHandling() {
    for(int i = 0; i < decl().getNumException(); i++) {
      TypeDecl exceptionType = decl().getException(i).type();
      if(!handlesException(exceptionType))
        error("" + this + " may throw uncaught exception " + exceptionType.fullName());
    }
  }

    // Declared in ExceptionHandling.jrag at line 244

  
  // 8.8.4 (8.4.4)
  protected boolean reachedException(TypeDecl catchType) {
    for(int i = 0; i < decl().getNumException(); i++) {
      TypeDecl exceptionType = decl().getException(i).type();
      if(catchType.mayCatch(exceptionType))
        return true;
    }
    return super.reachedException(catchType);
  }

    // Declared in NameCheck.jrag at line 112


  public void nameCheck() {
    super.nameCheck();
    if(decls().isEmpty())
      error("no constructor named " + this);
    if(decls().size() > 1 && validArgs()) {
      error("several most specific constructors for " + this);
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        error("         " + ((ConstructorDecl)iter.next()).signature());
      }
    }
  }

    // Declared in PrettyPrint.jadd at line 469


  public void toString(StringBuffer s) {
    s.append(name());
    s.append("(");
    if(getNumArg() > 0) {
      getArg(0).toString(s);
      for(int i = 1; i < getNumArg(); i++) {
        s.append(", ");
        getArg(i).toString(s);
      }
    }
    s.append(")");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 18

    public ConstructorAccess() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 18
    public ConstructorAccess(String p0, List<Expr> p1) {
        setID(p0);
        setChild(p1, 0);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 18
    public ConstructorAccess(beaver.Symbol p0, List<Expr> p1) {
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
    protected String tokenString_ID;

    // Declared in java.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 6

    public int IDstart;

    // Declared in java.ast at line 7

    public int IDend;

    // Declared in java.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

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
    // Declared in DefiniteAssignment.jrag at line 298
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

    private boolean isDAafter_compute(Variable v) {  return decl().isDAafter(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 756
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

    private boolean isDUafter_compute(Variable v) {  return decl().isDUafter(v);  }

    protected java.util.Map applicableAndAccessible_ConstructorDecl_visited;
    // Declared in LookupConstructor.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableAndAccessible(ConstructorDecl decl) {
        Object _parameters = decl;
if(applicableAndAccessible_ConstructorDecl_visited == null) applicableAndAccessible_ConstructorDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(applicableAndAccessible_ConstructorDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: applicableAndAccessible in class: ");
        applicableAndAccessible_ConstructorDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean applicableAndAccessible_ConstructorDecl_value = applicableAndAccessible_compute(decl);
        applicableAndAccessible_ConstructorDecl_visited.remove(_parameters);
        return applicableAndAccessible_ConstructorDecl_value;
    }

    private boolean applicableAndAccessible_compute(ConstructorDecl decl) {  return decl.applicable(getArgList()) && decl.accessibleFrom(hostType());  }

    protected int decls_visited = -1;
    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in LookupConstructor.jrag at line 57
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

    private SimpleSet decls_compute() {  return mostSpecificConstructor(lookupConstructor());  }

    protected int decl_visited = -1;
    protected boolean decl_computed = false;
    protected ConstructorDecl decl_value;
    // Declared in LookupConstructor.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public ConstructorDecl decl() {
        if(decl_computed) {
            return decl_value;
        }
        ASTNode$State state = state();
        if(decl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: decl in class: ");
        decl_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decl_value = decl_compute();
        if(isFinal && num == state().boundariesCrossed)
            decl_computed = true;
        decl_visited = -1;
        return decl_value;
    }

    private ConstructorDecl decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1)
      return (ConstructorDecl)decls.iterator().next();
    return unknownConstructor();
  }

    protected int validArgs_visited = -1;
    // Declared in NameCheck.jrag at line 124
 @SuppressWarnings({"unchecked", "cast"})     public boolean validArgs() {
        ASTNode$State state = state();
        if(validArgs_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: validArgs in class: ");
        validArgs_visited = state().boundariesCrossed;
        boolean validArgs_value = validArgs_compute();
        validArgs_visited = -1;
        return validArgs_value;
    }

    private boolean validArgs_compute() {
    for(int i = 0; i < getNumArg(); i++)
      if(getArg(i).type().isUnknown())
        return false;
    return true;
  }

    protected int name_visited = -1;
    // Declared in QualifiedNames.jrag at line 19
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        if(name_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = state().boundariesCrossed;
        String name_value = name_compute();
        name_visited = -1;
        return name_value;
    }

    private String name_compute() {  return "this";  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 129
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    // Declared in TypeAnalysis.jrag at line 285
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

    private TypeDecl type_compute() {  return decl().type();  }

    protected java.util.Map handlesException_TypeDecl_visited;
    // Declared in ExceptionHandling.jrag at line 30
 @SuppressWarnings({"unchecked", "cast"})     public boolean handlesException(TypeDecl exceptionType) {
        Object _parameters = exceptionType;
if(handlesException_TypeDecl_visited == null) handlesException_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(handlesException_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: handlesException in class: ");
        handlesException_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean handlesException_TypeDecl_value = getParent().Define_boolean_handlesException(this, null, exceptionType);
        handlesException_TypeDecl_visited.remove(_parameters);
        return handlesException_TypeDecl_value;
    }

    protected int lookupConstructor_visited = -1;
    // Declared in LookupConstructor.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupConstructor() {
        ASTNode$State state = state();
        if(lookupConstructor_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: lookupConstructor in class: ");
        lookupConstructor_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Collection lookupConstructor_value = getParent().Define_Collection_lookupConstructor(this, null);
        lookupConstructor_visited = -1;
        return lookupConstructor_value;
    }

    protected int unknownConstructor_visited = -1;
    // Declared in LookupConstructor.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public ConstructorDecl unknownConstructor() {
        ASTNode$State state = state();
        if(unknownConstructor_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unknownConstructor in class: ");
        unknownConstructor_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        ConstructorDecl unknownConstructor_value = getParent().Define_ConstructorDecl_unknownConstructor(this, null);
        unknownConstructor_visited = -1;
        return unknownConstructor_value;
    }

    // Declared in LookupMethod.jrag at line 29
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupMethod(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 88
    public boolean Define_boolean_hasPackage(ASTNode caller, ASTNode child, String packageName) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().hasPackage(packageName);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_hasPackage(this, caller, packageName);
    }

    // Declared in LookupType.jrag at line 166
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupType(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 131
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return unqualifiedScope().lookupVariable(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in SyntacticClassification.jrag at line 121
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.EXPRESSION_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 18
    public String Define_String_methodHost(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return unqualifiedScope().methodHost();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_methodHost(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 130
    public boolean Define_boolean_inExplicitConstructorInvocation(ASTNode caller, ASTNode child) {
        if(caller == getArgListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_inExplicitConstructorInvocation(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
