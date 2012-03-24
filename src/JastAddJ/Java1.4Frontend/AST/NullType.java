
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;
 // placeholder for ; in compilation unit

public class NullType extends TypeDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isNull_visited = -1;
        instanceOf_TypeDecl_visited = null;
        instanceOf_TypeDecl_values = null;
        isSupertypeOfNullType_NullType_visited = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public NullType clone() throws CloneNotSupportedException {
        NullType node = (NullType)super.clone();
        node.isNull_visited = -1;
        node.instanceOf_TypeDecl_visited = null;
        node.instanceOf_TypeDecl_values = null;
        node.isSupertypeOfNullType_NullType_visited = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NullType copy() {
      try {
          NullType node = (NullType)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public NullType fullCopy() {
        NullType res = (NullType)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 854

	public void toString(StringBuffer s) {
		s.append("null");
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 44

    public NullType() {
        super();

        setChild(new List(), 1);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 44
    public NullType(Modifiers p0, String p1, List<BodyDecl> p2) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
    }

    // Declared in java.ast at line 18


    // Declared in java.ast line 44
    public NullType(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
    }

    // Declared in java.ast at line 24


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 27

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 38
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
    // Declared in java.ast line 38
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
    // Declared in java.ast line 38
    public void setBodyDeclList(List<BodyDecl> list) {
        setChild(list, 1);
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
        List<BodyDecl> list = (List<BodyDecl>)getChild(1);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclListNoTransform() {
        return (List<BodyDecl>)getChildNoTransform(1);
    }

    protected int isNull_visited = -1;
    // Declared in TypeAnalysis.jrag at line 206
 @SuppressWarnings({"unchecked", "cast"})     public boolean isNull() {
        ASTNode$State state = state();
        if(isNull_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isNull in class: ");
        isNull_visited = state().boundariesCrossed;
        boolean isNull_value = isNull_compute();
        isNull_visited = -1;
        return isNull_value;
    }

    private boolean isNull_compute() {  return true;  }

    // Declared in TypeAnalysis.jrag at line 413
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

    private boolean instanceOf_compute(TypeDecl type) {  return type.isSupertypeOfNullType(this);  }

    protected java.util.Map isSupertypeOfNullType_NullType_visited;
    // Declared in TypeAnalysis.jrag at line 484
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfNullType(NullType type) {
        Object _parameters = type;
if(isSupertypeOfNullType_NullType_visited == null) isSupertypeOfNullType_NullType_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfNullType_NullType_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfNullType in class: ");
        isSupertypeOfNullType_NullType_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfNullType_NullType_value = isSupertypeOfNullType_compute(type);
        isSupertypeOfNullType_NullType_visited.remove(_parameters);
        return isSupertypeOfNullType_NullType_value;
    }

    private boolean isSupertypeOfNullType_compute(NullType type) {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
