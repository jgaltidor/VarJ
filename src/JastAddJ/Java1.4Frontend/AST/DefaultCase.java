
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class DefaultCase extends Case implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constValue_Case_visited = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DefaultCase clone() throws CloneNotSupportedException {
        DefaultCase node = (DefaultCase)super.clone();
        node.constValue_Case_visited = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DefaultCase copy() {
      try {
          DefaultCase node = (DefaultCase)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DefaultCase fullCopy() {
        DefaultCase res = (DefaultCase)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in NameCheck.jrag at line 406

  public void nameCheck() {
    if(bind(this) != this) {
      error("only one default case statement allowed");
    }
  }

    // Declared in PrettyPrint.jadd at line 568


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("default:");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 208

    public DefaultCase() {
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

    protected java.util.Map constValue_Case_visited;
    // Declared in NameCheck.jrag at line 434
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

    private boolean constValue_compute(Case c) {  return c instanceof DefaultCase;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
