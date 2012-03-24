
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class LongLiteral extends Literal implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isHex_visited = -1;
        isOctal_visited = -1;
        isDecimal_visited = -1;
        isPositive_visited = -1;
        constant_visited = -1;
        constant_computed = false;
        constant_value = null;
        type_visited = -1;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongLiteral clone() throws CloneNotSupportedException {
        LongLiteral node = (LongLiteral)super.clone();
        node.isHex_visited = -1;
        node.isOctal_visited = -1;
        node.isDecimal_visited = -1;
        node.isPositive_visited = -1;
        node.constant_visited = -1;
        node.constant_computed = false;
        node.constant_value = null;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongLiteral copy() {
      try {
          LongLiteral node = (LongLiteral)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public LongLiteral fullCopy() {
        LongLiteral res = (LongLiteral)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 274


  public void toString(StringBuffer s) {
    s.append(getLITERAL());
    s.append("L");
  }

    // Declared in TypeCheck.jrag at line 575

 public void typeCheck() {
   if(constant().error)
     error("The value of the long literal " + getLITERAL() + " is not legal");

 }

    // Declared in java.ast at line 3
    // Declared in java.ast line 126

    public LongLiteral() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 126
    public LongLiteral(String p0) {
        setLITERAL(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 126
    public LongLiteral(beaver.Symbol p0) {
        setLITERAL(p0);
    }

    // Declared in java.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 22

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 124
    public void setLITERAL(String value) {
        tokenString_LITERAL = value;
    }

    // Declared in java.ast at line 5

    public void setLITERAL(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setLITERAL is only valid for String lexemes");
        tokenString_LITERAL = (String)symbol.value;
        LITERALstart = symbol.getStart();
        LITERALend = symbol.getEnd();
    }

    // Declared in java.ast at line 12

    public String getLITERAL() {
        return tokenString_LITERAL != null ? tokenString_LITERAL : "";
    }

    protected int isHex_visited = -1;
    // Declared in ConstantExpression.jrag at line 237
 @SuppressWarnings({"unchecked", "cast"})     public boolean isHex() {
        ASTNode$State state = state();
        if(isHex_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isHex in class: ");
        isHex_visited = state().boundariesCrossed;
        boolean isHex_value = isHex_compute();
        isHex_visited = -1;
        return isHex_value;
    }

    private boolean isHex_compute() {  return getLITERAL().toLowerCase().startsWith("0x");  }

    protected int isOctal_visited = -1;
    // Declared in ConstantExpression.jrag at line 238
 @SuppressWarnings({"unchecked", "cast"})     public boolean isOctal() {
        ASTNode$State state = state();
        if(isOctal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isOctal in class: ");
        isOctal_visited = state().boundariesCrossed;
        boolean isOctal_value = isOctal_compute();
        isOctal_visited = -1;
        return isOctal_value;
    }

    private boolean isOctal_compute() {  return getLITERAL().startsWith("0");  }

    protected int isDecimal_visited = -1;
    // Declared in ConstantExpression.jrag at line 239
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDecimal() {
        ASTNode$State state = state();
        if(isDecimal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isDecimal in class: ");
        isDecimal_visited = state().boundariesCrossed;
        boolean isDecimal_value = isDecimal_compute();
        isDecimal_visited = -1;
        return isDecimal_value;
    }

    private boolean isDecimal_compute() {  return !isHex() && !isOctal();  }

    protected int isPositive_visited = -1;
    // Declared in ConstantExpression.jrag at line 243
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPositive() {
        ASTNode$State state = state();
        if(isPositive_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPositive in class: ");
        isPositive_visited = state().boundariesCrossed;
        boolean isPositive_value = isPositive_compute();
        isPositive_visited = -1;
        return isPositive_value;
    }

    private boolean isPositive_compute() {  return !getLITERAL().startsWith("-");  }

    // Declared in ConstantExpression.jrag at line 273
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

    private Constant constant_compute() {
    try {
      return Constant.create(Literal.parseLong(getLITERAL()));
    } catch (NumberFormatException e) {
      Constant c = Constant.create(0L);
      c.error = true;
      return c;
    }
  }

    protected int type_visited = -1;
    protected boolean type_computed = false;
    protected TypeDecl type_value;
    // Declared in TypeAnalysis.jrag at line 301
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

    private TypeDecl type_compute() {  return typeLong();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
