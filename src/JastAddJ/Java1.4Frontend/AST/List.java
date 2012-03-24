
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;
public class List<T extends ASTNode> extends ASTNode<T> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        requiresDefaultConstructor_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public List<T> clone() throws CloneNotSupportedException {
        List node = (List)super.clone();
        node.requiresDefaultConstructor_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public List<T> copy() {
      try {
          List node = (List)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public List<T> fullCopy() {
        List res = (List)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in List.ast at line 3
    // Declared in List.ast line 0

    public List() {
        super();


    }

    // Declared in List.ast at line 9


     public List<T> add(T node) {
        if(node instanceof List)
            throw new RuntimeException("Lists can not have children of type List");
        if(node instanceof Opt)
            throw new RuntimeException("Lists can not have children of type Opt");
          addChild(node);
          return this;
     }

    // Declared in List.ast at line 18


     public void insertChild(T node, int i) {
          list$touched = true;
          super.insertChild(node, i);
     }

    // Declared in List.ast at line 22

     public void addChild(T node) {
          list$touched = true;
          super.addChild(node);
     }

    // Declared in List.ast at line 26

     public void removeChild(int i) {
          list$touched = true;
          super.removeChild(i);
     }

    // Declared in List.ast at line 30

     public int getNumChild() {
          if(list$touched) {
              for(int i = 0; i < getNumChildNoTransform(); i++)
                  getChild(i);
              list$touched = false;
          }
          return getNumChildNoTransform();
     }

    // Declared in List.ast at line 38

     private boolean list$touched = true;

    // Declared in List.ast at line 39

    public boolean mayHaveRewrite() {
        return true;
    }

    protected int requiresDefaultConstructor_visited = -1;
    // Declared in LookupConstructor.jrag at line 178
 @SuppressWarnings({"unchecked", "cast"})     public boolean requiresDefaultConstructor() {
        ASTNode$State state = state();
        if(requiresDefaultConstructor_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: requiresDefaultConstructor in class: ");
        requiresDefaultConstructor_visited = state().boundariesCrossed;
        boolean requiresDefaultConstructor_value = requiresDefaultConstructor_compute();
        requiresDefaultConstructor_visited = -1;
        return requiresDefaultConstructor_value;
    }

    private boolean requiresDefaultConstructor_compute() {
    if(getParent() instanceof ClassDecl) {
      ClassDecl c = (ClassDecl)getParent();
      return c.getBodyDeclList() == this && !(c instanceof AnonymousDecl) && c.noConstructor();
    }
    return false;
  }

public ASTNode rewriteTo() {
    if(list$touched) {
        for(int i = 0 ; i < getNumChildNoTransform(); i++)
            getChild(i);
        list$touched = false;
        return this;
    }
    // Declared in LookupConstructor.jrag at line 187
    if(requiresDefaultConstructor()) {
        state().duringLookupConstructor++;
        ASTNode result = rewriteRule0();
        state().duringLookupConstructor--;
        return result;
    }

    return super.rewriteTo();
}

    // Declared in LookupConstructor.jrag at line 187
    private List rewriteRule0() {
         debugRewrite("Rewriting " + getClass().getName() + " using rule in LookupConstructor.jrag at line 187");
{
      ClassDecl c = (ClassDecl)getParent();
      Modifiers m = new Modifiers();
      if(c.isPublic()) m.addModifier(new Modifier("public"));
      else if(c.isProtected()) m.addModifier(new Modifier("protected"));
      else if(c.isPrivate()) m.addModifier(new Modifier("private"));
      c.addBodyDecl(
          new ConstructorDecl(
            m,
            c.name(),
            new List(),
            new List(),
            new Opt(),
            new Block()
          )
      );
      return this;
    }    }
}
