
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class PrimitiveType extends TypeDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        wideningConversionTo_TypeDecl_visited = null;
        narrowingConversionTo_TypeDecl_visited = null;
        narrowingConversionTo_TypeDecl_values = null;
        isPrimitiveType_visited = -1;
        isPrimitive_visited = -1;
        instanceOf_TypeDecl_visited = null;
        instanceOf_TypeDecl_values = null;
        isSupertypeOfPrimitiveType_PrimitiveType_visited = null;
        superclass_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PrimitiveType clone() throws CloneNotSupportedException {
        PrimitiveType node = (PrimitiveType)super.clone();
        node.wideningConversionTo_TypeDecl_visited = null;
        node.narrowingConversionTo_TypeDecl_visited = null;
        node.narrowingConversionTo_TypeDecl_values = null;
        node.isPrimitiveType_visited = -1;
        node.isPrimitive_visited = -1;
        node.instanceOf_TypeDecl_visited = null;
        node.instanceOf_TypeDecl_values = null;
        node.isSupertypeOfPrimitiveType_PrimitiveType_visited = null;
        node.superclass_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PrimitiveType copy() {
      try {
          PrimitiveType node = (PrimitiveType)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PrimitiveType fullCopy() {
        PrimitiveType res = (PrimitiveType)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in QualifiedNames.jrag at line 108


  public Access createQualifiedAccess() {
    return new PrimitiveTypeAccess(name());
  }

    // Declared in TypeAnalysis.jrag at line 605

  
  public boolean hasSuperclass() {
    return !isObject();
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 42

    public PrimitiveType() {
        super();

        setChild(new Opt(), 1);
        setChild(new List(), 2);

    }

    // Declared in java.ast at line 12


    // Declared in java.ast line 42
    public PrimitiveType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 20


    // Declared in java.ast line 42
    public PrimitiveType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 27


  protected int numChildren() {
    return 3;
  }

    // Declared in java.ast at line 30

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 42
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
    // Declared in java.ast line 42
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
    // Declared in java.ast line 42
    public void setSuperClassAccessOpt(Opt<Access> opt) {
        setChild(opt, 1);
    }

    // Declared in java.ast at line 6


    public boolean hasSuperClassAccess() {
        return getSuperClassAccessOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Access getSuperClassAccess() {
        return (Access)getSuperClassAccessOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setSuperClassAccess(Access node) {
        getSuperClassAccessOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Access> getSuperClassAccessOpt() {
        return (Opt<Access>)getChild(1);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Access> getSuperClassAccessOptNoTransform() {
        return (Opt<Access>)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 42
    public void setBodyDeclList(List<BodyDecl> list) {
        setChild(list, 2);
    }

    // Declared in java.ast at line 6


    public int getNumBodyDecl() {
        return getBodyDeclList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public BodyDecl getBodyDecl(int i) {
        return (BodyDecl)getBodyDeclList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addBodyDecl(BodyDecl node) {
        List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addBodyDeclNoTransform(BodyDecl node) {
        List<BodyDecl> list = getBodyDeclListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setBodyDecl(BodyDecl node, int i) {
        List<BodyDecl> list = getBodyDeclList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<BodyDecl> getBodyDecls() {
        return getBodyDeclList();
    }

    // Declared in java.ast at line 31

    public List<BodyDecl> getBodyDeclsNoTransform() {
        return getBodyDeclListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclList() {
        List<BodyDecl> list = (List<BodyDecl>)getChild(2);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclListNoTransform() {
        return (List<BodyDecl>)getChildNoTransform(2);
    }

    protected java.util.Map wideningConversionTo_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public boolean wideningConversionTo(TypeDecl type) {
        Object _parameters = type;
if(wideningConversionTo_TypeDecl_visited == null) wideningConversionTo_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(wideningConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: wideningConversionTo in class: ");
        wideningConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean wideningConversionTo_TypeDecl_value = wideningConversionTo_compute(type);
        wideningConversionTo_TypeDecl_visited.remove(_parameters);
        return wideningConversionTo_TypeDecl_value;
    }

    private boolean wideningConversionTo_compute(TypeDecl type) {  return instanceOf(type);  }

    // Declared in TypeAnalysis.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean narrowingConversionTo(TypeDecl type) {
        Object _parameters = type;
if(narrowingConversionTo_TypeDecl_visited == null) narrowingConversionTo_TypeDecl_visited = new java.util.HashMap(4);
if(narrowingConversionTo_TypeDecl_values == null) narrowingConversionTo_TypeDecl_values = new java.util.HashMap(4);
        if(narrowingConversionTo_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)narrowingConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(narrowingConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: narrowingConversionTo in class: ");
        narrowingConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean narrowingConversionTo_TypeDecl_value = narrowingConversionTo_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            narrowingConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(narrowingConversionTo_TypeDecl_value));
        narrowingConversionTo_TypeDecl_visited.remove(_parameters);
        return narrowingConversionTo_TypeDecl_value;
    }

    private boolean narrowingConversionTo_compute(TypeDecl type) {  return type.instanceOf(this);  }

    protected int isPrimitiveType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 169
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrimitiveType() {
        ASTNode$State state = state();
        if(isPrimitiveType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrimitiveType in class: ");
        isPrimitiveType_visited = state().boundariesCrossed;
        boolean isPrimitiveType_value = isPrimitiveType_compute();
        isPrimitiveType_visited = -1;
        return isPrimitiveType_value;
    }

    private boolean isPrimitiveType_compute() {  return true;  }

    protected int isPrimitive_visited = -1;
    // Declared in TypeAnalysis.jrag at line 222
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrimitive() {
        ASTNode$State state = state();
        if(isPrimitive_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrimitive in class: ");
        isPrimitive_visited = state().boundariesCrossed;
        boolean isPrimitive_value = isPrimitive_compute();
        isPrimitive_visited = -1;
        return isPrimitive_value;
    }

    private boolean isPrimitive_compute() {  return true;  }

    // Declared in TypeAnalysis.jrag at line 412
 @SuppressWarnings({"unchecked", "cast"})     public boolean instanceOf(TypeDecl type) {
        Object _parameters = type;
if(instanceOf_TypeDecl_visited == null) instanceOf_TypeDecl_visited = new java.util.HashMap(4);
if(instanceOf_TypeDecl_values == null) instanceOf_TypeDecl_values = new java.util.HashMap(4);
        if(instanceOf_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)instanceOf_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(instanceOf_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: instanceOf in class: ");
        instanceOf_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean instanceOf_TypeDecl_value = instanceOf_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            instanceOf_TypeDecl_values.put(_parameters, Boolean.valueOf(instanceOf_TypeDecl_value));
        instanceOf_TypeDecl_visited.remove(_parameters);
        return instanceOf_TypeDecl_value;
    }

    private boolean instanceOf_compute(TypeDecl type) {  return type.isSupertypeOfPrimitiveType(this);  }

    protected java.util.Map isSupertypeOfPrimitiveType_PrimitiveType_visited;
    // Declared in TypeAnalysis.jrag at line 476
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfPrimitiveType(PrimitiveType type) {
        Object _parameters = type;
if(isSupertypeOfPrimitiveType_PrimitiveType_visited == null) isSupertypeOfPrimitiveType_PrimitiveType_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfPrimitiveType_PrimitiveType_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfPrimitiveType in class: ");
        isSupertypeOfPrimitiveType_PrimitiveType_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfPrimitiveType_PrimitiveType_value = isSupertypeOfPrimitiveType_compute(type);
        isSupertypeOfPrimitiveType_PrimitiveType_visited.remove(_parameters);
        return isSupertypeOfPrimitiveType_PrimitiveType_value;
    }

    private boolean isSupertypeOfPrimitiveType_compute(PrimitiveType type) {
    if(super.isSupertypeOfPrimitiveType(type))
      return true;
    return type.hasSuperclass() && type.superclass().isPrimitive() && type.superclass().instanceOf(this);
  }

    protected int superclass_visited = -1;
    // Declared in TypeAnalysis.jrag at line 609
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl superclass() {
        ASTNode$State state = state();
        if(superclass_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: superclass in class: ");
        superclass_visited = state().boundariesCrossed;
        TypeDecl superclass_value = superclass_compute();
        superclass_visited = -1;
        return superclass_value;
    }

    private TypeDecl superclass_compute() {  return getSuperClassAccess().type();  }

    // Declared in TypeAnalysis.jrag at line 574
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getSuperClassAccessOptNoTransform()) {
            return hostType();
        }
        return super.Define_TypeDecl_hostType(caller, child);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
