
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class VariableDeclaration extends Stmt implements Cloneable, SimpleSet, Iterator, Variable {
    public void flushCache() {
        super.flushCache();
        size_visited = -1;
        isEmpty_visited = -1;
        contains_Object_visited = null;
        isBlankFinal_visited = -1;
        isValue_visited = -1;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        declaresVariable_String_visited = null;
        isSynthetic_visited = -1;
        dumpString_visited = -1;
        type_visited = -1;
        isClassVariable_visited = -1;
        isInstanceVariable_visited = -1;
        isMethodParameter_visited = -1;
        isConstructorParameter_visited = -1;
        isExceptionHandlerParameter_visited = -1;
        isLocalVariable_visited = -1;
        isFinal_visited = -1;
        isBlank_visited = -1;
        isStatic_visited = -1;
        name_visited = -1;
        constant_visited = -1;
        constant_computed = false;
        constant_value = null;
        lookupVariable_String_visited = null;
        outerScope_visited = -1;
        hostType_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public VariableDeclaration clone() throws CloneNotSupportedException {
        VariableDeclaration node = (VariableDeclaration)super.clone();
        node.size_visited = -1;
        node.isEmpty_visited = -1;
        node.contains_Object_visited = null;
        node.isBlankFinal_visited = -1;
        node.isValue_visited = -1;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.declaresVariable_String_visited = null;
        node.isSynthetic_visited = -1;
        node.dumpString_visited = -1;
        node.type_visited = -1;
        node.isClassVariable_visited = -1;
        node.isInstanceVariable_visited = -1;
        node.isMethodParameter_visited = -1;
        node.isConstructorParameter_visited = -1;
        node.isExceptionHandlerParameter_visited = -1;
        node.isLocalVariable_visited = -1;
        node.isFinal_visited = -1;
        node.isBlank_visited = -1;
        node.isStatic_visited = -1;
        node.name_visited = -1;
        node.constant_visited = -1;
        node.constant_computed = false;
        node.constant_value = null;
        node.lookupVariable_String_visited = null;
        node.outerScope_visited = -1;
        node.hostType_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public VariableDeclaration copy() {
      try {
          VariableDeclaration node = (VariableDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public VariableDeclaration fullCopy() {
        VariableDeclaration res = (VariableDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DataStructures.jrag at line 85

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 91

  private VariableDeclaration iterElem;

    // Declared in DataStructures.jrag at line 92

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 93

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 94

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 95

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in NameCheck.jrag at line 299


  public void nameCheck() {
    SimpleSet decls = outerScope().lookupVariable(name());
    for(Iterator iter = decls.iterator(); iter.hasNext(); ) {
      Variable var = (Variable)iter.next();
      if(var instanceof VariableDeclaration) {
        VariableDeclaration decl = (VariableDeclaration)var;
        if(decl != this && decl.enclosingBodyDecl() == enclosingBodyDecl())
  	      error("duplicate declaration of local variable " + name() + " in enclosing scope");
      }
      // 8.4.1
      else if(var instanceof ParameterDeclaration) {
        ParameterDeclaration decl = (ParameterDeclaration)var;
	      if(decl.enclosingBodyDecl() == enclosingBodyDecl())
  	      error("duplicate declaration of local variable and parameter " + name());
      }
    }
    if(getParent().getParent() instanceof Block) {
      Block block = (Block)getParent().getParent();
      for(int i = 0; i < block.getNumStmt(); i++) {
        if(block.getStmt(i) instanceof Variable) {
          Variable v = (Variable)block.getStmt(i);
          if(v.name().equals(name()) && v != this) {
     	    error("duplicate declaration of local variable " + name());
          }
	}
      }
    }
  }

    // Declared in NodeConstructors.jrag at line 74


  public VariableDeclaration(Access type, String name, Expr init) {
    this(new Modifiers(new List()), type, name, new Opt(init));
  }

    // Declared in NodeConstructors.jrag at line 78


  public VariableDeclaration(Access type, String name) {
    this(new Modifiers(new List()), type, name, new Opt());
  }

    // Declared in PrettyPrint.jadd at line 163


  public void toString(StringBuffer s) {
    s.append(indent());
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
    if(hasInit()) {
      s.append(" = ");
      getInit().toString(s);
    }
    s.append(";");
  }

    // Declared in TypeCheck.jrag at line 22

 
  // 5.2
  public void typeCheck() {
    if(hasInit()) {
      TypeDecl source = getInit().type();
      TypeDecl dest = type();
      if(!source.assignConversionTo(dest, getInit()))
        error("can not assign " + name() + " of type " + dest.typeName() +
              " a value of type " + source.typeName());
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 80

    public VariableDeclaration() {
        super();

        setChild(new Opt(), 2);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 80
    public VariableDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 19


    // Declared in java.ast line 80
    public VariableDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 26


  protected int numChildren() {
    return 3;
  }

    // Declared in java.ast at line 29

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 80
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in java.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 80
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

    // Declared in java.ast at line 2
    // Declared in java.ast line 80
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
    // Declared in java.ast line 80
    public void setInitOpt(Opt<Expr> opt) {
        setChild(opt, 2);
    }

    // Declared in java.ast at line 6


    public boolean hasInit() {
        return getInitOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getInit() {
        return (Expr)getInitOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setInit(Expr node) {
        getInitOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getInitOpt() {
        return (Opt<Expr>)getChild(2);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getInitOptNoTransform() {
        return (Opt<Expr>)getChildNoTransform(2);
    }

    protected int size_visited = -1;
    // Declared in DataStructures.jrag at line 83
 @SuppressWarnings({"unchecked", "cast"})     public int size() {
        ASTNode$State state = state();
        if(size_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: size in class: ");
        size_visited = state().boundariesCrossed;
        int size_value = size_compute();
        size_visited = -1;
        return size_value;
    }

    private int size_compute() {  return 1;  }

    protected int isEmpty_visited = -1;
    // Declared in DataStructures.jrag at line 84
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        ASTNode$State state = state();
        if(isEmpty_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isEmpty in class: ");
        isEmpty_visited = state().boundariesCrossed;
        boolean isEmpty_value = isEmpty_compute();
        isEmpty_visited = -1;
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    protected java.util.Map contains_Object_visited;
    // Declared in DataStructures.jrag at line 88
 @SuppressWarnings({"unchecked", "cast"})     public boolean contains(Object o) {
        Object _parameters = o;
if(contains_Object_visited == null) contains_Object_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(contains_Object_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: contains in class: ");
        contains_Object_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean contains_Object_value = contains_compute(o);
        contains_Object_visited.remove(_parameters);
        return contains_Object_value;
    }

    private boolean contains_compute(Object o) {  return this == o;  }

    protected int isBlankFinal_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 91
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlankFinal() {
        ASTNode$State state = state();
        if(isBlankFinal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isBlankFinal in class: ");
        isBlankFinal_visited = state().boundariesCrossed;
        boolean isBlankFinal_value = isBlankFinal_compute();
        isBlankFinal_visited = -1;
        return isBlankFinal_value;
    }

    private boolean isBlankFinal_compute() {  return isFinal() && (!hasInit() || !getInit().isConstant());  }

    protected int isValue_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 92
 @SuppressWarnings({"unchecked", "cast"})     public boolean isValue() {
        ASTNode$State state = state();
        if(isValue_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isValue in class: ");
        isValue_visited = state().boundariesCrossed;
        boolean isValue_value = isValue_compute();
        isValue_visited = -1;
        return isValue_value;
    }

    private boolean isValue_compute() {  return isFinal() && hasInit() && getInit().isConstant();  }

    // Declared in DefiniteAssignment.jrag at line 495
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

    private boolean isDAafter_compute(Variable v) {
    if(v == this)
      return hasInit();
    return hasInit() ? getInit().isDAafter(v) : isDAbefore(v);
  }

    // Declared in DefiniteAssignment.jrag at line 881
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

    private boolean isDUafter_compute(Variable v) {
    if(v == this)
      return !hasInit();
    return hasInit() ? getInit().isDUafter(v) : isDUbefore(v);
  }

    protected java.util.Map declaresVariable_String_visited;
    // Declared in LookupVariable.jrag at line 128
 @SuppressWarnings({"unchecked", "cast"})     public boolean declaresVariable(String name) {
        Object _parameters = name;
if(declaresVariable_String_visited == null) declaresVariable_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(declaresVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: declaresVariable in class: ");
        declaresVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean declaresVariable_String_value = declaresVariable_compute(name);
        declaresVariable_String_visited.remove(_parameters);
        return declaresVariable_String_value;
    }

    private boolean declaresVariable_compute(String name) {  return name().equals(name);  }

    protected int isSynthetic_visited = -1;
    // Declared in Modifiers.jrag at line 217
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        ASTNode$State state = state();
        if(isSynthetic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSynthetic in class: ");
        isSynthetic_visited = state().boundariesCrossed;
        boolean isSynthetic_value = isSynthetic_compute();
        isSynthetic_visited = -1;
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 811
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        if(dumpString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: dumpString in class: ");
        dumpString_visited = state().boundariesCrossed;
        String dumpString_value = dumpString_compute();
        dumpString_visited = -1;
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    protected int type_visited = -1;
    // Declared in TypeAnalysis.jrag at line 252
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        ASTNode$State state = state();
        if(type_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: type in class: ");
        type_visited = state().boundariesCrossed;
        TypeDecl type_value = type_compute();
        type_visited = -1;
        return type_value;
    }

    private TypeDecl type_compute() {  return getTypeAccess().type();  }

    protected int isClassVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 38
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassVariable() {
        ASTNode$State state = state();
        if(isClassVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isClassVariable in class: ");
        isClassVariable_visited = state().boundariesCrossed;
        boolean isClassVariable_value = isClassVariable_compute();
        isClassVariable_visited = -1;
        return isClassVariable_value;
    }

    private boolean isClassVariable_compute() {  return false;  }

    protected int isInstanceVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 39
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInstanceVariable() {
        ASTNode$State state = state();
        if(isInstanceVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isInstanceVariable in class: ");
        isInstanceVariable_visited = state().boundariesCrossed;
        boolean isInstanceVariable_value = isInstanceVariable_compute();
        isInstanceVariable_visited = -1;
        return isInstanceVariable_value;
    }

    private boolean isInstanceVariable_compute() {  return false;  }

    protected int isMethodParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 40
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodParameter() {
        ASTNode$State state = state();
        if(isMethodParameter_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isMethodParameter in class: ");
        isMethodParameter_visited = state().boundariesCrossed;
        boolean isMethodParameter_value = isMethodParameter_compute();
        isMethodParameter_visited = -1;
        return isMethodParameter_value;
    }

    private boolean isMethodParameter_compute() {  return false;  }

    protected int isConstructorParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 41
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstructorParameter() {
        ASTNode$State state = state();
        if(isConstructorParameter_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstructorParameter in class: ");
        isConstructorParameter_visited = state().boundariesCrossed;
        boolean isConstructorParameter_value = isConstructorParameter_compute();
        isConstructorParameter_visited = -1;
        return isConstructorParameter_value;
    }

    private boolean isConstructorParameter_compute() {  return false;  }

    protected int isExceptionHandlerParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 42
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExceptionHandlerParameter() {
        ASTNode$State state = state();
        if(isExceptionHandlerParameter_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isExceptionHandlerParameter in class: ");
        isExceptionHandlerParameter_visited = state().boundariesCrossed;
        boolean isExceptionHandlerParameter_value = isExceptionHandlerParameter_compute();
        isExceptionHandlerParameter_visited = -1;
        return isExceptionHandlerParameter_value;
    }

    private boolean isExceptionHandlerParameter_compute() {  return false;  }

    protected int isLocalVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 43
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLocalVariable() {
        ASTNode$State state = state();
        if(isLocalVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isLocalVariable in class: ");
        isLocalVariable_visited = state().boundariesCrossed;
        boolean isLocalVariable_value = isLocalVariable_compute();
        isLocalVariable_visited = -1;
        return isLocalVariable_value;
    }

    private boolean isLocalVariable_compute() {  return true;  }

    protected int isFinal_visited = -1;
    // Declared in VariableDeclaration.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        ASTNode$State state = state();
        if(isFinal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFinal in class: ");
        isFinal_visited = state().boundariesCrossed;
        boolean isFinal_value = isFinal_compute();
        isFinal_visited = -1;
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal();  }

    protected int isBlank_visited = -1;
    // Declared in VariableDeclaration.jrag at line 46
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlank() {
        ASTNode$State state = state();
        if(isBlank_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isBlank in class: ");
        isBlank_visited = state().boundariesCrossed;
        boolean isBlank_value = isBlank_compute();
        isBlank_visited = -1;
        return isBlank_value;
    }

    private boolean isBlank_compute() {  return !hasInit();  }

    protected int isStatic_visited = -1;
    // Declared in VariableDeclaration.jrag at line 47
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        if(isStatic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isStatic in class: ");
        isStatic_visited = state().boundariesCrossed;
        boolean isStatic_value = isStatic_compute();
        isStatic_visited = -1;
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return false;  }

    protected int name_visited = -1;
    // Declared in VariableDeclaration.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        if(name_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = state().boundariesCrossed;
        String name_value = name_compute();
        name_visited = -1;
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected int constant_visited = -1;
    protected boolean constant_computed = false;
    protected Constant constant_value;
    // Declared in VariableDeclaration.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        if(constant_computed) {
            return constant_value;
        }
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        constant_value = constant_compute();
        if(isFinal && num == state().boundariesCrossed)
            constant_computed = true;
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return type().cast(getInit().constant());  }

    protected java.util.Map lookupVariable_String_visited;
    // Declared in LookupVariable.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        Object _parameters = name;
if(lookupVariable_String_visited == null) lookupVariable_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupVariable in class: ");
        lookupVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        lookupVariable_String_visited.remove(_parameters);
        return lookupVariable_String_value;
    }

    protected int outerScope_visited = -1;
    // Declared in NameCheck.jrag at line 289
 @SuppressWarnings({"unchecked", "cast"})     public VariableScope outerScope() {
        ASTNode$State state = state();
        if(outerScope_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: outerScope in class: ");
        outerScope_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        VariableScope outerScope_value = getParent().Define_VariableScope_outerScope(this, null);
        outerScope_visited = -1;
        return outerScope_value;
    }

    protected int hostType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 585
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        if(hostType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hostType in class: ");
        hostType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        hostType_visited = -1;
        return hostType_value;
    }

    // Declared in DefiniteAssignment.jrag at line 40
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 500
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getInitOptNoTransform()) {
            return isDAbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 886
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getInitOptNoTransform()) {
            return isDUbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in Modifiers.jrag at line 284
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 85
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 261
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return type();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_declType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
