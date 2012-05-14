
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class ParameterDeclaration extends ASTNode<ASTNode> implements Cloneable, SimpleSet, Iterator, Variable {
    public void flushCache() {
        super.flushCache();
        size_visited = -1;
        isEmpty_visited = -1;
        contains_Object_visited = null;
        isSynthetic_visited = -1;
        dumpString_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        isClassVariable_visited = -1;
        isInstanceVariable_visited = -1;
        isLocalVariable_visited = -1;
        isFinal_visited = -1;
        isBlank_visited = -1;
        isStatic_visited = -1;
        name_visited = -1;
        hasInit_visited = -1;
        getInit_visited = -1;
        constant_visited = -1;
        lookupVariable_String_visited = null;
        outerScope_visited = -1;
        enclosingBodyDecl_visited = -1;
        hostType_visited = -1;
        isMethodParameter_visited = -1;
        isConstructorParameter_visited = -1;
        isExceptionHandlerParameter_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration clone() throws CloneNotSupportedException {
        ParameterDeclaration node = (ParameterDeclaration)super.clone();
        node.size_visited = -1;
        node.isEmpty_visited = -1;
        node.contains_Object_visited = null;
        node.isSynthetic_visited = -1;
        node.dumpString_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.isClassVariable_visited = -1;
        node.isInstanceVariable_visited = -1;
        node.isLocalVariable_visited = -1;
        node.isFinal_visited = -1;
        node.isBlank_visited = -1;
        node.isStatic_visited = -1;
        node.name_visited = -1;
        node.hasInit_visited = -1;
        node.getInit_visited = -1;
        node.constant_visited = -1;
        node.lookupVariable_String_visited = null;
        node.outerScope_visited = -1;
        node.enclosingBodyDecl_visited = -1;
        node.hostType_visited = -1;
        node.isMethodParameter_visited = -1;
        node.isConstructorParameter_visited = -1;
        node.isExceptionHandlerParameter_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration copy() {
      try {
          ParameterDeclaration node = (ParameterDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ParameterDeclaration fullCopy() {
        ParameterDeclaration res = (ParameterDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DataStructures.jrag at line 101

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 107

  private ParameterDeclaration iterElem;

    // Declared in DataStructures.jrag at line 108

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 109

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 110

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 111

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in NameCheck.jrag at line 328

  
  public void nameCheck() {
    SimpleSet decls = outerScope().lookupVariable(name());
    for(Iterator iter = decls.iterator(); iter.hasNext(); ) {
      Variable var = (Variable)iter.next();
      if(var instanceof VariableDeclaration) {
        VariableDeclaration decl = (VariableDeclaration)var;
	      if(decl.enclosingBodyDecl() == enclosingBodyDecl())
  	      error("duplicate declaration of local variable " + name());
      }
      else if(var instanceof ParameterDeclaration) {
        ParameterDeclaration decl = (ParameterDeclaration)var;
	      if(decl.enclosingBodyDecl() == enclosingBodyDecl())
          error("duplicate declaration of local variable " + name());
      }
    }

    // 8.4.1  
    if(!lookupVariable(name()).contains(this)) {
      error("duplicate declaration of parameter " + name());
    }
  }

    // Declared in NodeConstructors.jrag at line 11

  public ParameterDeclaration(Access type, String name) {
    this(new Modifiers(new List()), type, name);
  }

    // Declared in NodeConstructors.jrag at line 14

  public ParameterDeclaration(TypeDecl type, String name) {
    this(new Modifiers(new List()), type.createQualifiedAccess(), name);
  }

    // Declared in PrettyPrint.jadd at line 232


  public void toString(StringBuffer s) {
    getModifiers().toString(s);
    getTypeAccess().toString(s);
    s.append(" " + name());
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 84

    public ParameterDeclaration() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 84
    public ParameterDeclaration(Modifiers p0, Access p1, String p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in java.ast at line 17


    // Declared in java.ast line 84
    public ParameterDeclaration(Modifiers p0, Access p1, beaver.Symbol p2) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
    }

    // Declared in java.ast at line 23


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 26

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 84
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
    // Declared in java.ast line 84
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
    // Declared in java.ast line 84
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

    protected int size_visited = -1;
    // Declared in DataStructures.jrag at line 99
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
    // Declared in DataStructures.jrag at line 100
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
    // Declared in DataStructures.jrag at line 104
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

    protected int isSynthetic_visited = -1;
    // Declared in Modifiers.jrag at line 218
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
    // Declared in PrettyPrint.jadd at line 812
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
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 253
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

    protected int isClassVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 69
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
    // Declared in VariableDeclaration.jrag at line 70
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

    protected int isLocalVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 74
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLocalVariable() {
        ASTNode$State state = state();
        if(isLocalVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isLocalVariable in class: ");
        isLocalVariable_visited = state().boundariesCrossed;
        boolean isLocalVariable_value = isLocalVariable_compute();
        isLocalVariable_visited = -1;
        return isLocalVariable_value;
    }

    private boolean isLocalVariable_compute() {  return false;  }

    protected int isFinal_visited = -1;
    // Declared in VariableDeclaration.jrag at line 92
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
    // Declared in VariableDeclaration.jrag at line 93
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBlank() {
        ASTNode$State state = state();
        if(isBlank_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isBlank in class: ");
        isBlank_visited = state().boundariesCrossed;
        boolean isBlank_value = isBlank_compute();
        isBlank_visited = -1;
        return isBlank_value;
    }

    private boolean isBlank_compute() {  return true;  }

    protected int isStatic_visited = -1;
    // Declared in VariableDeclaration.jrag at line 94
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
    // Declared in VariableDeclaration.jrag at line 96
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

    protected int hasInit_visited = -1;
    // Declared in VariableDeclaration.jrag at line 98
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasInit() {
        ASTNode$State state = state();
        if(hasInit_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasInit in class: ");
        hasInit_visited = state().boundariesCrossed;
        boolean hasInit_value = hasInit_compute();
        hasInit_visited = -1;
        return hasInit_value;
    }

    private boolean hasInit_compute() {  return false;  }

    protected int getInit_visited = -1;
    // Declared in VariableDeclaration.jrag at line 99
 @SuppressWarnings({"unchecked", "cast"})     public Expr getInit() {
        ASTNode$State state = state();
        if(getInit_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: getInit in class: ");
        getInit_visited = state().boundariesCrossed;
        Expr getInit_value = getInit_compute();
        getInit_visited = -1;
        return getInit_value;
    }

    private Expr getInit_compute() { throw new UnsupportedOperationException(); }

    protected int constant_visited = -1;
    // Declared in VariableDeclaration.jrag at line 100
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() { throw new UnsupportedOperationException(); }

    protected java.util.Map lookupVariable_String_visited;
    // Declared in LookupVariable.jrag at line 22
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
    // Declared in NameCheck.jrag at line 288
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

    protected int enclosingBodyDecl_visited = -1;
    // Declared in NameCheck.jrag at line 349
 @SuppressWarnings({"unchecked", "cast"})     public BodyDecl enclosingBodyDecl() {
        ASTNode$State state = state();
        if(enclosingBodyDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingBodyDecl in class: ");
        enclosingBodyDecl_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        BodyDecl enclosingBodyDecl_value = getParent().Define_BodyDecl_enclosingBodyDecl(this, null);
        enclosingBodyDecl_visited = -1;
        return enclosingBodyDecl_value;
    }

    protected int hostType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 586
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

    protected int isMethodParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodParameter() {
        ASTNode$State state = state();
        if(isMethodParameter_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isMethodParameter in class: ");
        isMethodParameter_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isMethodParameter_value = getParent().Define_boolean_isMethodParameter(this, null);
        isMethodParameter_visited = -1;
        return isMethodParameter_value;
    }

    protected int isConstructorParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstructorParameter() {
        ASTNode$State state = state();
        if(isConstructorParameter_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstructorParameter in class: ");
        isConstructorParameter_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isConstructorParameter_value = getParent().Define_boolean_isConstructorParameter(this, null);
        isConstructorParameter_visited = -1;
        return isConstructorParameter_value;
    }

    protected int isExceptionHandlerParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public boolean isExceptionHandlerParameter() {
        ASTNode$State state = state();
        if(isExceptionHandlerParameter_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isExceptionHandlerParameter in class: ");
        isExceptionHandlerParameter_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isExceptionHandlerParameter_value = getParent().Define_boolean_isExceptionHandlerParameter(this, null);
        isExceptionHandlerParameter_visited = -1;
        return isExceptionHandlerParameter_value;
    }

    // Declared in Modifiers.jrag at line 286
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
