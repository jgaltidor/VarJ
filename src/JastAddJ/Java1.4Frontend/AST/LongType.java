
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class LongType extends IntegralType implements Cloneable {
    public void flushCache() {
        super.flushCache();
        cast_Constant_visited = null;
        plus_Constant_visited = null;
        minus_Constant_visited = null;
        bitNot_Constant_visited = null;
        mul_Constant_Constant_visited = null;
        div_Constant_Constant_visited = null;
        mod_Constant_Constant_visited = null;
        add_Constant_Constant_visited = null;
        sub_Constant_Constant_visited = null;
        lshift_Constant_Constant_visited = null;
        rshift_Constant_Constant_visited = null;
        urshift_Constant_Constant_visited = null;
        andBitwise_Constant_Constant_visited = null;
        xorBitwise_Constant_Constant_visited = null;
        orBitwise_Constant_Constant_visited = null;
        questionColon_Constant_Constant_Constant_visited = null;
        eqIsTrue_Expr_Expr_visited = null;
        ltIsTrue_Expr_Expr_visited = null;
        leIsTrue_Expr_Expr_visited = null;
        assignableToInt_visited = -1;
        isLong_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongType clone() throws CloneNotSupportedException {
        LongType node = (LongType)super.clone();
        node.cast_Constant_visited = null;
        node.plus_Constant_visited = null;
        node.minus_Constant_visited = null;
        node.bitNot_Constant_visited = null;
        node.mul_Constant_Constant_visited = null;
        node.div_Constant_Constant_visited = null;
        node.mod_Constant_Constant_visited = null;
        node.add_Constant_Constant_visited = null;
        node.sub_Constant_Constant_visited = null;
        node.lshift_Constant_Constant_visited = null;
        node.rshift_Constant_Constant_visited = null;
        node.urshift_Constant_Constant_visited = null;
        node.andBitwise_Constant_Constant_visited = null;
        node.xorBitwise_Constant_Constant_visited = null;
        node.orBitwise_Constant_Constant_visited = null;
        node.questionColon_Constant_Constant_Constant_visited = null;
        node.eqIsTrue_Expr_Expr_visited = null;
        node.ltIsTrue_Expr_Expr_visited = null;
        node.leIsTrue_Expr_Expr_visited = null;
        node.assignableToInt_visited = -1;
        node.isLong_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongType copy() {
      try {
          LongType node = (LongType)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongType fullCopy() {
        LongType res = (LongType)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 842

	public void toString(StringBuffer s) {
		s.append("long");
	}

    // Declared in java.ast at line 3
    // Declared in java.ast line 56

    public LongType() {
        super();

        setChild(new Opt(), 1);
        setChild(new List(), 2);

    }

    // Declared in java.ast at line 12


    // Declared in java.ast line 56
    public LongType(Modifiers p0, String p1, Opt<Access> p2, List<BodyDecl> p3) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
        setChild(p3, 2);
    }

    // Declared in java.ast at line 20


    // Declared in java.ast line 56
    public LongType(Modifiers p0, beaver.Symbol p1, Opt<Access> p2, List<BodyDecl> p3) {
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
    // Declared in ConstantExpression.jrag at line 314
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

    private Constant cast_compute(Constant c) {  return Constant.create(c.longValue());  }

    protected java.util.Map plus_Constant_visited;
    // Declared in ConstantExpression.jrag at line 325
 @SuppressWarnings({"unchecked", "cast"})     public Constant plus(Constant c) {
        Object _parameters = c;
if(plus_Constant_visited == null) plus_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(plus_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: plus in class: ");
        plus_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant plus_Constant_value = plus_compute(c);
        plus_Constant_visited.remove(_parameters);
        return plus_Constant_value;
    }

    private Constant plus_compute(Constant c) {  return c;  }

    protected java.util.Map minus_Constant_visited;
    // Declared in ConstantExpression.jrag at line 334
 @SuppressWarnings({"unchecked", "cast"})     public Constant minus(Constant c) {
        Object _parameters = c;
if(minus_Constant_visited == null) minus_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(minus_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: minus in class: ");
        minus_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant minus_Constant_value = minus_compute(c);
        minus_Constant_visited.remove(_parameters);
        return minus_Constant_value;
    }

    private Constant minus_compute(Constant c) {  return Constant.create(-c.longValue());  }

    protected java.util.Map bitNot_Constant_visited;
    // Declared in ConstantExpression.jrag at line 343
 @SuppressWarnings({"unchecked", "cast"})     public Constant bitNot(Constant c) {
        Object _parameters = c;
if(bitNot_Constant_visited == null) bitNot_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(bitNot_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: bitNot in class: ");
        bitNot_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant bitNot_Constant_value = bitNot_compute(c);
        bitNot_Constant_visited.remove(_parameters);
        return bitNot_Constant_value;
    }

    private Constant bitNot_compute(Constant c) {  return Constant.create(~c.longValue());  }

    protected java.util.Map mul_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 350
 @SuppressWarnings({"unchecked", "cast"})     public Constant mul(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(mul_Constant_Constant_visited == null) mul_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(mul_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: mul in class: ");
        mul_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant mul_Constant_Constant_value = mul_compute(c1, c2);
        mul_Constant_Constant_visited.remove(_parameters);
        return mul_Constant_Constant_value;
    }

    private Constant mul_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() * c2.longValue());  }

    protected java.util.Map div_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 359
 @SuppressWarnings({"unchecked", "cast"})     public Constant div(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(div_Constant_Constant_visited == null) div_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(div_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: div in class: ");
        div_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant div_Constant_Constant_value = div_compute(c1, c2);
        div_Constant_Constant_visited.remove(_parameters);
        return div_Constant_Constant_value;
    }

    private Constant div_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() / c2.longValue());  }

    protected java.util.Map mod_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 368
 @SuppressWarnings({"unchecked", "cast"})     public Constant mod(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(mod_Constant_Constant_visited == null) mod_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(mod_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: mod in class: ");
        mod_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant mod_Constant_Constant_value = mod_compute(c1, c2);
        mod_Constant_Constant_visited.remove(_parameters);
        return mod_Constant_Constant_value;
    }

    private Constant mod_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() % c2.longValue());  }

    protected java.util.Map add_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 377
 @SuppressWarnings({"unchecked", "cast"})     public Constant add(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(add_Constant_Constant_visited == null) add_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(add_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: add in class: ");
        add_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant add_Constant_Constant_value = add_compute(c1, c2);
        add_Constant_Constant_visited.remove(_parameters);
        return add_Constant_Constant_value;
    }

    private Constant add_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() + c2.longValue());  }

    protected java.util.Map sub_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 387
 @SuppressWarnings({"unchecked", "cast"})     public Constant sub(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(sub_Constant_Constant_visited == null) sub_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(sub_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: sub in class: ");
        sub_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant sub_Constant_Constant_value = sub_compute(c1, c2);
        sub_Constant_Constant_visited.remove(_parameters);
        return sub_Constant_Constant_value;
    }

    private Constant sub_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() - c2.longValue());  }

    protected java.util.Map lshift_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 396
 @SuppressWarnings({"unchecked", "cast"})     public Constant lshift(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(lshift_Constant_Constant_visited == null) lshift_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lshift_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lshift in class: ");
        lshift_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant lshift_Constant_Constant_value = lshift_compute(c1, c2);
        lshift_Constant_Constant_visited.remove(_parameters);
        return lshift_Constant_Constant_value;
    }

    private Constant lshift_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() << c2.longValue());  }

    protected java.util.Map rshift_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 403
 @SuppressWarnings({"unchecked", "cast"})     public Constant rshift(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(rshift_Constant_Constant_visited == null) rshift_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(rshift_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: rshift in class: ");
        rshift_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant rshift_Constant_Constant_value = rshift_compute(c1, c2);
        rshift_Constant_Constant_visited.remove(_parameters);
        return rshift_Constant_Constant_value;
    }

    private Constant rshift_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() >> c2.longValue());  }

    protected java.util.Map urshift_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 410
 @SuppressWarnings({"unchecked", "cast"})     public Constant urshift(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(urshift_Constant_Constant_visited == null) urshift_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(urshift_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: urshift in class: ");
        urshift_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant urshift_Constant_Constant_value = urshift_compute(c1, c2);
        urshift_Constant_Constant_visited.remove(_parameters);
        return urshift_Constant_Constant_value;
    }

    private Constant urshift_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() >>> c2.longValue());  }

    protected java.util.Map andBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 417
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

    private Constant andBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() & c2.longValue());  }

    protected java.util.Map xorBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 425
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

    private Constant xorBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() ^ c2.longValue());  }

    protected java.util.Map orBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 433
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

    private Constant orBitwise_compute(Constant c1, Constant c2) {  return Constant.create(c1.longValue() | c2.longValue());  }

    protected java.util.Map questionColon_Constant_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 441
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

    private Constant questionColon_compute(Constant cond, Constant c1, Constant c2) {  return Constant.create(cond.booleanValue() ? c1.longValue() : c2.longValue());  }

    protected java.util.Map eqIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 545
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

    private boolean eqIsTrue_compute(Expr left, Expr right) {  return left.constant().longValue() == right.constant().longValue();  }

    protected java.util.Map ltIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 553
 @SuppressWarnings({"unchecked", "cast"})     public boolean ltIsTrue(Expr left, Expr right) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(left);
        _parameters.add(right);
if(ltIsTrue_Expr_Expr_visited == null) ltIsTrue_Expr_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(ltIsTrue_Expr_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: ltIsTrue in class: ");
        ltIsTrue_Expr_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean ltIsTrue_Expr_Expr_value = ltIsTrue_compute(left, right);
        ltIsTrue_Expr_Expr_visited.remove(_parameters);
        return ltIsTrue_Expr_Expr_value;
    }

    private boolean ltIsTrue_compute(Expr left, Expr right) {  return left.constant().longValue() < right.constant().longValue();  }

    protected java.util.Map leIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 559
 @SuppressWarnings({"unchecked", "cast"})     public boolean leIsTrue(Expr left, Expr right) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(left);
        _parameters.add(right);
if(leIsTrue_Expr_Expr_visited == null) leIsTrue_Expr_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(leIsTrue_Expr_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: leIsTrue in class: ");
        leIsTrue_Expr_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean leIsTrue_Expr_Expr_value = leIsTrue_compute(left, right);
        leIsTrue_Expr_Expr_visited.remove(_parameters);
        return leIsTrue_Expr_Expr_value;
    }

    private boolean leIsTrue_compute(Expr left, Expr right) {  return left.constant().longValue() <= right.constant().longValue();  }

    protected int assignableToInt_visited = -1;
    // Declared in NameCheck.jrag at line 424
 @SuppressWarnings({"unchecked", "cast"})     public boolean assignableToInt() {
        ASTNode$State state = state();
        if(assignableToInt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: assignableToInt in class: ");
        assignableToInt_visited = state().boundariesCrossed;
        boolean assignableToInt_value = assignableToInt_compute();
        assignableToInt_visited = -1;
        return assignableToInt_value;
    }

    private boolean assignableToInt_compute() {  return false;  }

    protected int isLong_visited = -1;
    // Declared in TypeAnalysis.jrag at line 198
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLong() {
        ASTNode$State state = state();
        if(isLong_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isLong in class: ");
        isLong_visited = state().boundariesCrossed;
        boolean isLong_value = isLong_compute();
        isLong_visited = -1;
        return isLong_value;
    }

    private boolean isLong_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
