
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public abstract class MemberDecl extends BodyDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isConstant_visited = -1;
        isSynthetic_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public MemberDecl clone() throws CloneNotSupportedException {
        MemberDecl node = (MemberDecl)super.clone();
        node.isConstant_visited = -1;
        node.isSynthetic_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in Modifiers.jrag at line 190

   
  public void checkModifiers() {
    if(!isSynthetic()) {
      super.checkModifiers();
      if(isStatic() && hostType().isInnerClass() && !isConstant())
        error("*** Inner classes may not declare static members, unless they are compile-time constant fields");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 74

    public MemberDecl() {
        super();


    }

    // Declared in java.ast at line 9


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 12

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in Modifiers.jrag at line 220
 @SuppressWarnings({"unchecked", "cast"})     public abstract boolean isStatic();
    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 478
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return false;  }

    protected int isSynthetic_visited = -1;
    // Declared in Modifiers.jrag at line 212
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        ASTNode$State state = state();
        if(isSynthetic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSynthetic in class: ");
        isSynthetic_visited = state().boundariesCrossed;
        boolean isSynthetic_value = isSynthetic_compute();
        isSynthetic_visited = -1;
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
