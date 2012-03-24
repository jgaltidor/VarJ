
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class ConstCase extends Case implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constValue_Case_visited = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstCase clone() throws CloneNotSupportedException {
        ConstCase node = (ConstCase)super.clone();
        node.constValue_Case_visited = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstCase copy() {
      try {
          ConstCase node = (ConstCase)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ConstCase fullCopy() {
        ConstCase res = (ConstCase)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in NameCheck.jrag at line 401

  
  public void nameCheck() {
    if(getValue().isConstant() && bind(this) != this) {
      error("constant expression " + getValue() + " is multiply declared in two case statements");
    }
  }

    // Declared in PrettyPrint.jadd at line 561


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("case ");
    getValue().toString(s);
    s.append(":");
  }

    // Declared in TypeCheck.jrag at line 349


  public void typeCheck() {
    TypeDecl switchType = switchType();
    TypeDecl type = getValue().type();
    if(!type.assignConversionTo(switchType, getValue()))
      error("Constant expression must be assignable to Expression");
    if(!getValue().isConstant() && !getValue().type().isUnknown()) 
      error("Switch expression must be constant");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 207

    public ConstCase() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 207
    public ConstCase(Expr p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 17

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 207
    public void setValue(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getValue() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getValueNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    protected java.util.Map constValue_Case_visited;
    // Declared in NameCheck.jrag at line 427
 @SuppressWarnings({"unchecked", "cast"})     public boolean constValue(Case c) {
        Object _parameters = c;
if(constValue_Case_visited == null) constValue_Case_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(constValue_Case_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: constValue in class: ");
        constValue_Case_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean constValue_Case_value = constValue_compute(c);
        constValue_Case_visited.remove(_parameters);
        return constValue_Case_value;
    }

    private boolean constValue_compute(Case c) {
    if(!(c instanceof ConstCase) || !getValue().isConstant())
      return false;
    if(!getValue().type().assignableToInt() || !((ConstCase)c).getValue().type().assignableToInt())
      return false;
    return getValue().constant().intValue() == ((ConstCase)c).getValue().constant().intValue();
  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
