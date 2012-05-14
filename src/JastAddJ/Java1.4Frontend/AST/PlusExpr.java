
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class PlusExpr extends Unary implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isConstant_visited = -1;
        printPreOp_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public PlusExpr clone() throws CloneNotSupportedException {
        PlusExpr node = (PlusExpr)super.clone();
        node.constant_visited = -1;
        node.isConstant_visited = -1;
        node.printPreOp_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PlusExpr copy() {
      try {
          PlusExpr node = (PlusExpr)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public PlusExpr fullCopy() {
        PlusExpr res = (PlusExpr)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in TypeCheck.jrag at line 269

  
  // 15.15.3
  public void typeCheck() {
    if(!getOperand().type().isNumericType())
      error("unary plus only operates on numeric types");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 143

    public PlusExpr() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 143
    public PlusExpr(Expr p0) {
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
    // Declared in java.ast line 139
    public void setOperand(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getOperand() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getOperandNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 113
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return type().plus(getOperand().constant());  }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 487
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return getOperand().isConstant();  }

    protected int printPreOp_visited = -1;
    // Declared in PrettyPrint.jadd at line 379
 @SuppressWarnings({"unchecked", "cast"})     public String printPreOp() {
        ASTNode$State state = state();
        if(printPreOp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: printPreOp in class: ");
        printPreOp_visited = state().boundariesCrossed;
        String printPreOp_value = printPreOp_compute();
        printPreOp_visited = -1;
        return printPreOp_value;
    }

    private String printPreOp_compute() {  return "+";  }

    // Declared in TypeAnalysis.jrag at line 315
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

    private TypeDecl type_compute() {  return getOperand().type().unaryNumericPromotion();  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
