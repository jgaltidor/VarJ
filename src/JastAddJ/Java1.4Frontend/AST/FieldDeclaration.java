
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class FieldDeclaration extends MemberDecl implements Cloneable, SimpleSet, Iterator, Variable {
    public void flushCache() {
        super.flushCache();
        accessibleFrom_TypeDecl_visited = null;
        accessibleFrom_TypeDecl_values = null;
        exceptions_visited = -1;
        exceptions_computed = false;
        exceptions_value = null;
        isConstant_visited = -1;
        size_visited = -1;
        isEmpty_visited = -1;
        contains_Object_visited = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        isSynthetic_visited = -1;
        isPublic_visited = -1;
        isPrivate_visited = -1;
        isProtected_visited = -1;
        isStatic_visited = -1;
        isFinal_visited = -1;
        isTransient_visited = -1;
        isVolatile_visited = -1;
        dumpString_visited = -1;
        type_visited = -1;
        isVoid_visited = -1;
        isClassVariable_visited = -1;
        isInstanceVariable_visited = -1;
        isMethodParameter_visited = -1;
        isConstructorParameter_visited = -1;
        isExceptionHandlerParameter_visited = -1;
        isLocalVariable_visited = -1;
        isBlank_visited = -1;
        name_visited = -1;
        constant_visited = -1;
        constant_computed = false;
        constant_value = null;
        handlesException_TypeDecl_visited = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldDeclaration clone() throws CloneNotSupportedException {
        FieldDeclaration node = (FieldDeclaration)super.clone();
        node.accessibleFrom_TypeDecl_visited = null;
        node.accessibleFrom_TypeDecl_values = null;
        node.exceptions_visited = -1;
        node.exceptions_computed = false;
        node.exceptions_value = null;
        node.isConstant_visited = -1;
        node.size_visited = -1;
        node.isEmpty_visited = -1;
        node.contains_Object_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.isSynthetic_visited = -1;
        node.isPublic_visited = -1;
        node.isPrivate_visited = -1;
        node.isProtected_visited = -1;
        node.isStatic_visited = -1;
        node.isFinal_visited = -1;
        node.isTransient_visited = -1;
        node.isVolatile_visited = -1;
        node.dumpString_visited = -1;
        node.type_visited = -1;
        node.isVoid_visited = -1;
        node.isClassVariable_visited = -1;
        node.isInstanceVariable_visited = -1;
        node.isMethodParameter_visited = -1;
        node.isConstructorParameter_visited = -1;
        node.isExceptionHandlerParameter_visited = -1;
        node.isLocalVariable_visited = -1;
        node.isBlank_visited = -1;
        node.name_visited = -1;
        node.constant_visited = -1;
        node.constant_computed = false;
        node.constant_value = null;
        node.handlesException_TypeDecl_visited = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldDeclaration copy() {
      try {
          FieldDeclaration node = (FieldDeclaration)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public FieldDeclaration fullCopy() {
        FieldDeclaration res = (FieldDeclaration)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BoundNames.jrag at line 11

  public Access createQualifiedBoundAccess() {
    if(isStatic())
      return hostType().createQualifiedAccess().qualifiesAccess(new BoundFieldAccess(this));
    else
      return new ThisAccess("this").qualifiesAccess(
        new BoundFieldAccess(this));
  }

    // Declared in BoundNames.jrag at line 86


  public Access createBoundFieldAccess() {
    return createQualifiedBoundAccess();
  }

    // Declared in DataStructures.jrag at line 69

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 75

  private FieldDeclaration iterElem;

    // Declared in DataStructures.jrag at line 76

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 77

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 78

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 79

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in DefiniteAssignment.jrag at line 179

  
  public void definiteAssignment() {
    super.definiteAssignment();
    if(isBlank() && isFinal() && isClassVariable()) {
      boolean found = false;
      TypeDecl typeDecl = hostType();
      for(int i = 0; i < typeDecl.getNumBodyDecl(); i++) {
        if(typeDecl.getBodyDecl(i) instanceof StaticInitializer) {
          StaticInitializer s = (StaticInitializer)typeDecl.getBodyDecl(i);
          if(s.isDAafter(this))
            found = true;
        }
        
        else if(typeDecl.getBodyDecl(i) instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)typeDecl.getBodyDecl(i);
          if(f.isStatic() && f.isDAafter(this))
            found = true;
        }
        
      }
      if(!found)
        error("blank final class variable " + name() + " in " + hostType().typeName() + " is not definitely assigned in static initializer");

    }
    if(isBlank() && isFinal() && isInstanceVariable()) {
      TypeDecl typeDecl = hostType();
      boolean found = false;
      for(int i = 0; !found && i < typeDecl.getNumBodyDecl(); i++) {
        if(typeDecl.getBodyDecl(i) instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)typeDecl.getBodyDecl(i);
          if(!f.isStatic() && f.isDAafter(this))
            found = true;
        }
        else if(typeDecl.getBodyDecl(i) instanceof InstanceInitializer) {
          InstanceInitializer ii = (InstanceInitializer)typeDecl.getBodyDecl(i);
          if(ii.getBlock().isDAafter(this))
            found = true;
        }
      }
      for(Iterator iter = typeDecl.constructors().iterator(); !found && iter.hasNext(); ) {
        ConstructorDecl c = (ConstructorDecl)iter.next();
        if(!c.isDAafter(this)) {
          error("blank final instance variable " + name() + " in " + hostType().typeName() + " is not definitely assigned after " + c.signature());
          }
      }
    }
    if(isBlank() && hostType().isInterfaceDecl()) {
            error("variable  " + name() + " in " + hostType().typeName() + " which is an interface must have an initializer");
    }

  }

    // Declared in Modifiers.jrag at line 112

 
  public void checkModifiers() {
    super.checkModifiers();
    if(hostType().isInterfaceDecl()) {
      if(isProtected())
        error("an interface field may not be protected");
      if(isPrivate())
        error("an interface field may not be private");
      if(isTransient())
        error("an interface field may not be transient");
      if(isVolatile())
        error("an interface field may not be volatile");
    }
  }

    // Declared in NameCheck.jrag at line 277


  public void nameCheck() {
    super.nameCheck();
    // 8.3
    for(Iterator iter = hostType().memberFields(name()).iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(v != this && v.hostType() == hostType())
        error("field named " + name() + " is multiply declared in type " + hostType().typeName());
    }

  }

    // Declared in NodeConstructors.jrag at line 86


  public FieldDeclaration(Modifiers m, Access type, String name) {
    this(m, type, name, new Opt());
  }

    // Declared in NodeConstructors.jrag at line 90

  
  public FieldDeclaration(Modifiers m, Access type, String name, Expr init) {
    this(m, type, name, new Opt(init));
  }

    // Declared in PrettyPrint.jadd at line 151


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

    // Declared in TypeCheck.jrag at line 33


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
    // Declared in java.ast line 77

    public FieldDeclaration() {
        super();

        setChild(new Opt(), 2);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 77
    public FieldDeclaration(Modifiers p0, Access p1, String p2, Opt<Expr> p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setID(p2);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 19


    // Declared in java.ast line 77
    public FieldDeclaration(Modifiers p0, Access p1, beaver.Symbol p2, Opt<Expr> p3) {
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
    // Declared in java.ast line 77
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
    // Declared in java.ast line 77
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
    // Declared in java.ast line 77
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
    // Declared in java.ast line 77
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

    protected java.util.Map accessibleFrom_TypeDecl_visited;
    protected java.util.Map accessibleFrom_TypeDecl_values;
    // Declared in AccessControl.jrag at line 109
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessibleFrom(TypeDecl type) {
        Object _parameters = type;
if(accessibleFrom_TypeDecl_visited == null) accessibleFrom_TypeDecl_visited = new java.util.HashMap(4);
if(accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
        if(accessibleFrom_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)accessibleFrom_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(accessibleFrom_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: accessibleFrom in class: ");
        accessibleFrom_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            accessibleFrom_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFrom_TypeDecl_value));
        accessibleFrom_TypeDecl_visited.remove(_parameters);
        return accessibleFrom_TypeDecl_value;
    }

    private boolean accessibleFrom_compute(TypeDecl type) {
    if(isPublic())
      return true;
    else if(isProtected()) {
      if(hostPackage().equals(type.hostPackage()))
        return true;
      if(type.withinBodyThatSubclasses(hostType()) != null)
        return true;
      return false;
    }
    else if(isPrivate())
      return hostType().topLevelType() == type.topLevelType();
    else
      return hostPackage().equals(type.hostPackage());
  }

    protected int exceptions_visited = -1;
    protected boolean exceptions_computed = false;
    protected Collection exceptions_value;
    // Declared in AnonymousClasses.jrag at line 166
 @SuppressWarnings({"unchecked", "cast"})     public Collection exceptions() {
        if(exceptions_computed) {
            return exceptions_value;
        }
        ASTNode$State state = state();
        if(exceptions_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: exceptions in class: ");
        exceptions_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        exceptions_value = exceptions_compute();
        if(isFinal && num == state().boundariesCrossed)
            exceptions_computed = true;
        exceptions_visited = -1;
        return exceptions_value;
    }

    private Collection exceptions_compute() {
    HashSet set = new HashSet();
    if(isInstanceVariable() && hasInit()) {
      collectExceptions(set, this);
      for(Iterator iter = set.iterator(); iter.hasNext(); ) {
        TypeDecl typeDecl = (TypeDecl)iter.next();
        if(!getInit().reachedException(typeDecl))
          iter.remove();
      }
    }
    return set;
  }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 479
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return isFinal() && hasInit() && getInit().isConstant() && (type() instanceof PrimitiveType || type().isString());  }

    protected int size_visited = -1;
    // Declared in DataStructures.jrag at line 67
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
    // Declared in DataStructures.jrag at line 68
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
    // Declared in DataStructures.jrag at line 72
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

    // Declared in DefiniteAssignment.jrag at line 316
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

    // Declared in DefiniteAssignment.jrag at line 774
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

    protected int isSynthetic_visited = -1;
    // Declared in Modifiers.jrag at line 214
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

    protected int isPublic_visited = -1;
    // Declared in Modifiers.jrag at line 237
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        ASTNode$State state = state();
        if(isPublic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPublic in class: ");
        isPublic_visited = state().boundariesCrossed;
        boolean isPublic_value = isPublic_compute();
        isPublic_visited = -1;
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return getModifiers().isPublic() || hostType().isInterfaceDecl();  }

    protected int isPrivate_visited = -1;
    // Declared in Modifiers.jrag at line 238
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrivate() {
        ASTNode$State state = state();
        if(isPrivate_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrivate in class: ");
        isPrivate_visited = state().boundariesCrossed;
        boolean isPrivate_value = isPrivate_compute();
        isPrivate_visited = -1;
        return isPrivate_value;
    }

    private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }

    protected int isProtected_visited = -1;
    // Declared in Modifiers.jrag at line 239
 @SuppressWarnings({"unchecked", "cast"})     public boolean isProtected() {
        ASTNode$State state = state();
        if(isProtected_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isProtected in class: ");
        isProtected_visited = state().boundariesCrossed;
        boolean isProtected_value = isProtected_compute();
        isProtected_visited = -1;
        return isProtected_value;
    }

    private boolean isProtected_compute() {  return getModifiers().isProtected();  }

    protected int isStatic_visited = -1;
    // Declared in Modifiers.jrag at line 240
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        if(isStatic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isStatic in class: ");
        isStatic_visited = state().boundariesCrossed;
        boolean isStatic_value = isStatic_compute();
        isStatic_visited = -1;
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return getModifiers().isStatic() || hostType().isInterfaceDecl();  }

    protected int isFinal_visited = -1;
    // Declared in Modifiers.jrag at line 242
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        ASTNode$State state = state();
        if(isFinal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFinal in class: ");
        isFinal_visited = state().boundariesCrossed;
        boolean isFinal_value = isFinal_compute();
        isFinal_visited = -1;
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal() || hostType().isInterfaceDecl();  }

    protected int isTransient_visited = -1;
    // Declared in Modifiers.jrag at line 243
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTransient() {
        ASTNode$State state = state();
        if(isTransient_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTransient in class: ");
        isTransient_visited = state().boundariesCrossed;
        boolean isTransient_value = isTransient_compute();
        isTransient_visited = -1;
        return isTransient_value;
    }

    private boolean isTransient_compute() {  return getModifiers().isTransient();  }

    protected int isVolatile_visited = -1;
    // Declared in Modifiers.jrag at line 244
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVolatile() {
        ASTNode$State state = state();
        if(isVolatile_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVolatile in class: ");
        isVolatile_visited = state().boundariesCrossed;
        boolean isVolatile_value = isVolatile_compute();
        isVolatile_visited = -1;
        return isVolatile_value;
    }

    private boolean isVolatile_compute() {  return getModifiers().isVolatile();  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 810
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
    // Declared in TypeAnalysis.jrag at line 251
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

    protected int isVoid_visited = -1;
    // Declared in TypeAnalysis.jrag at line 273
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVoid() {
        ASTNode$State state = state();
        if(isVoid_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVoid in class: ");
        isVoid_visited = state().boundariesCrossed;
        boolean isVoid_value = isVoid_compute();
        isVoid_visited = -1;
        return isVoid_value;
    }

    private boolean isVoid_compute() {  return type().isVoid();  }

    protected int isClassVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassVariable() {
        ASTNode$State state = state();
        if(isClassVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isClassVariable in class: ");
        isClassVariable_visited = state().boundariesCrossed;
        boolean isClassVariable_value = isClassVariable_compute();
        isClassVariable_visited = -1;
        return isClassVariable_value;
    }

    private boolean isClassVariable_compute() {  return isStatic() || hostType().isInterfaceDecl();  }

    protected int isInstanceVariable_visited = -1;
    // Declared in VariableDeclaration.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInstanceVariable() {
        ASTNode$State state = state();
        if(isInstanceVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isInstanceVariable in class: ");
        isInstanceVariable_visited = state().boundariesCrossed;
        boolean isInstanceVariable_value = isInstanceVariable_compute();
        isInstanceVariable_visited = -1;
        return isInstanceVariable_value;
    }

    private boolean isInstanceVariable_compute() {  return (hostType().isClassDecl() || hostType().isAnonymous() )&& !isStatic();  }

    protected int isMethodParameter_visited = -1;
    // Declared in VariableDeclaration.jrag at line 57
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
    // Declared in VariableDeclaration.jrag at line 58
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
    // Declared in VariableDeclaration.jrag at line 59
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
    // Declared in VariableDeclaration.jrag at line 60
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

    protected int isBlank_visited = -1;
    // Declared in VariableDeclaration.jrag at line 62
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

    protected int name_visited = -1;
    // Declared in VariableDeclaration.jrag at line 64
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
    // Declared in VariableDeclaration.jrag at line 65
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

    protected java.util.Map handlesException_TypeDecl_visited;
    // Declared in ExceptionHandling.jrag at line 34
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

    // Declared in DefiniteAssignment.jrag at line 39
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 322
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getInitOptNoTransform()){
    return isDAbefore(v);
  }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in ExceptionHandling.jrag at line 143
    public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
        if(caller == getInitOptNoTransform()){
    if(hostType().isAnonymous())
      return true;
    if(!exceptionType.isUncheckedException())
      return true;
    for(Iterator iter = hostType().constructors().iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(!decl.throwsException(exceptionType))
        return false;
    }
    return true;
  }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_handlesException(this, caller, exceptionType);
    }

    // Declared in Modifiers.jrag at line 260
    public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBePublic(this, caller);
    }

    // Declared in Modifiers.jrag at line 261
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeProtected(this, caller);
    }

    // Declared in Modifiers.jrag at line 262
    public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBePrivate(this, caller);
    }

    // Declared in Modifiers.jrag at line 263
    public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeStatic(this, caller);
    }

    // Declared in Modifiers.jrag at line 264
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in Modifiers.jrag at line 265
    public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeTransient(this, caller);
    }

    // Declared in Modifiers.jrag at line 266
    public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeVolatile(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 78
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getTypeAccessNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 260
    public TypeDecl Define_TypeDecl_declType(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return type();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_declType(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 141
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getInitOptNoTransform()) {
            return isStatic() || hostType().isInterfaceDecl();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_inStaticContext(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
