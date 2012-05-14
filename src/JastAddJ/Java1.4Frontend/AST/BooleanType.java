
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class BooleanType extends PrimitiveType implements Cloneable {
    public void flushCache() {
        super.flushCache();
        cast_Constant_visited = null;
        andBitwise_Constant_Constant_visited = null;
        xorBitwise_Constant_Constant_visited = null;
        orBitwise_Constant_Constant_visited = null;
        questionColon_Constant_Constant_Constant_visited = null;
        eqIsTrue_Expr_Expr_visited = null;
        isBoolean_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public BooleanType clone() throws CloneNotSupportedException {
        BooleanType node = (BooleanType)super.clone();
        node.cast_Constant_visited = null;
        node.andBitwise_Constant_Constant_visited = null;
        node.xorBitwise_Constant_Constant_visited = null;
        node.orBitwise_Constant_Constant_visited = null;
        node.questionColon_Constant_Constant_Constant_visited = null;
        node.eqIsTrue_Expr_Expr_visited = null;
        node.isBoolean_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BooleanType copy() {
      try {
          BooleanType node = (BooleanType)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BooleanType fullCopy() {
        BooleanType res = (BooleanType)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 830


	public void toString(StringBuffer s) {
		s.append("boolean");
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 51

    public BooleanType() {
        super();

        setChild(new Opt(), 1);
        setChild(new List(), 2);

    }

    // Declared in java.ast at line 12


    // Declared in java.ast line 51
    public BooleanType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 20


    // Declared in java.ast line 51
    public BooleanType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
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

    protected java.util.Map cast_Constant_visited;
    // Declared in ConstantExpression.jrag at line 317
 @SuppressWarnings({"unchecked", "cast"})     public Constant cast(Constant c) {
        Object _parameters = c;
if(cast_Constant_visited == null) cast_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(cast_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: cast in class: ");
        cast_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant cast_Constant_value = cast_compute(c);
        cast_Constant_visited.remove(_parameters);
        return cast_Constant_value;
    }

    private Constant cast_compute(Constant c) {  return Constant.create(c.booleanValue());  }

    protected java.util.Map andBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 418
 @SuppressWarnings({"unchecked", "cast"})     public Constant andBitwise(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(andBitwise_Constant_Constant_visited == null) andBitwise_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(andBitwise_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: andBitwise in class: ");
        andBitwise_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant andBitwise_Constant_Constant_value = andBitwise_compute(c1, c2);
        andBitwise_Constant_Constant_visited.remove(_parameters);
        return andBitwise_Constant_Constant_value;
    }

    private Constant andBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.booleanValue() & c2.booleanValue());  }

    protected java.util.Map xorBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 426
 @SuppressWarnings({"unchecked", "cast"})     public Constant xorBitwise(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(xorBitwise_Constant_Constant_visited == null) xorBitwise_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(xorBitwise_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: xorBitwise in class: ");
        xorBitwise_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant xorBitwise_Constant_Constant_value = xorBitwise_compute(c1, c2);
        xorBitwise_Constant_Constant_visited.remove(_parameters);
        return xorBitwise_Constant_Constant_value;
    }

    private Constant xorBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.booleanValue() ^ c2.booleanValue());  }

    protected java.util.Map orBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 434
 @SuppressWarnings({"unchecked", "cast"})     public Constant orBitwise(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(orBitwise_Constant_Constant_visited == null) orBitwise_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(orBitwise_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: orBitwise in class: ");
        orBitwise_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant orBitwise_Constant_Constant_value = orBitwise_compute(c1, c2);
        orBitwise_Constant_Constant_visited.remove(_parameters);
        return orBitwise_Constant_Constant_value;
    }

    private Constant orBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.booleanValue() | c2.booleanValue());  }

    protected java.util.Map questionColon_Constant_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 444
 @SuppressWarnings({"unchecked", "cast"})     public Constant questionColon(Constant cond, Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(3);
        _parameters.add(cond);
        _parameters.add(c1);
        _parameters.add(c2);
if(questionColon_Constant_Constant_Constant_visited == null) questionColon_Constant_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(questionColon_Constant_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: questionColon in class: ");
        questionColon_Constant_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant questionColon_Constant_Constant_Constant_value = questionColon_compute(cond, c1, c2);
        questionColon_Constant_Constant_Constant_visited.remove(_parameters);
        return questionColon_Constant_Constant_Constant_value;
    }

    private Constant questionColon_compute(Constant cond, Constant c1, Constant c2) {  return Constant.create(cond.booleanValue() ? c1.booleanValue() : c2.booleanValue());  }

    protected java.util.Map eqIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 548
 @SuppressWarnings({"unchecked", "cast"})     public boolean eqIsTrue(Expr left, Expr right) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(left);
        _parameters.add(right);
if(eqIsTrue_Expr_Expr_visited == null) eqIsTrue_Expr_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(eqIsTrue_Expr_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: eqIsTrue in class: ");
        eqIsTrue_Expr_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean eqIsTrue_Expr_Expr_value = eqIsTrue_compute(left, right);
        eqIsTrue_Expr_Expr_visited.remove(_parameters);
        return eqIsTrue_Expr_Expr_value;
    }

    private boolean eqIsTrue_compute(Expr left, Expr right) {  return left.isTrue() && right.isTrue() || left.isFalse() && right.isFalse();  }

    protected int isBoolean_visited = -1;
    // Declared in TypeAnalysis.jrag at line 182
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBoolean() {
        ASTNode$State state = state();
        if(isBoolean_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isBoolean in class: ");
        isBoolean_visited = state().boundariesCrossed;
        boolean isBoolean_value = isBoolean_compute();
        isBoolean_visited = -1;
        return isBoolean_value;
    }

    private boolean isBoolean_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
