
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class ThisAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        decls_visited = -1;
        decl_visited = -1;
        decl_computed = false;
        decl_value = null;
        isThisAccess_visited = -1;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        inExplicitConstructorInvocation_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ThisAccess clone() throws CloneNotSupportedException {
        ThisAccess node = (ThisAccess)super.clone();
        node.decls_visited = -1;
        node.decl_visited = -1;
        node.decl_computed = false;
        node.decl_value = null;
        node.isThisAccess_visited = -1;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.inExplicitConstructorInvocation_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ThisAccess copy() {
      try {
          ThisAccess node = (ThisAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ThisAccess fullCopy() {
        ThisAccess res = (ThisAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 501


  public void toString(StringBuffer s) {
    s.append("this");
  }

    // Declared in TypeHierarchyCheck.jrag at line 103


  public void nameCheck() {
    // 8.8.5.1
    if(inExplicitConstructorInvocation() && hostType() == type())
      error("this may not be accessed in an explicit constructor invocation");
    else if(isQualified()) {
      // 15.8.4
      if(inStaticContext())
        error("qualified this may not occur in static context");
      else if(!hostType().isInnerTypeOf(decl()) && hostType() != decl())
        error("qualified this must name an enclosing type: " + getParent());
    }
    // 8.4.3.2
    else if(!isQualified() && inStaticContext())
      error("this may not be accessed in static context: " + enclosingStmt());
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 24

    public ThisAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 24
    public ThisAccess(String p0) {
        setID(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 24
    public ThisAccess(beaver.Symbol p0) {
        setID(p0);
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
    // Declared in java.ast line 24
    protected String tokenString_ID;

    // Declared in java.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 6

    public int IDstart;

    // Declared in java.ast at line 7

    public int IDend;

    // Declared in java.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    protected int decls_visited = -1;
    // Declared in LookupType.jrag at line 160
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet decls() {
        ASTNode$State state = state();
        if(decls_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: decls in class: ");
        decls_visited = state().boundariesCrossed;
        SimpleSet decls_value = decls_compute();
        decls_visited = -1;
        return decls_value;
    }

    private SimpleSet decls_compute() {  return SimpleSet.emptySet;  }

    protected int decl_visited = -1;
    protected boolean decl_computed = false;
    protected TypeDecl decl_value;
    // Declared in LookupType.jrag at line 162
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl decl() {
        if(decl_computed) {
            return decl_value;
        }
        ASTNode$State state = state();
        if(decl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: decl in class: ");
        decl_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decl_value = decl_compute();
        if(isFinal && num == state().boundariesCrossed)
            decl_computed = true;
        decl_visited = -1;
        return decl_value;
    }

    private TypeDecl decl_compute() {  return isQualified() ? qualifier().type() : hostType();  }

    protected int isThisAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 33
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        if(isThisAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isThisAccess in class: ");
        isThisAccess_visited = state().boundariesCrossed;
        boolean isThisAccess_value = isThisAccess_compute();
        isThisAccess_visited = -1;
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return true;  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 92
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.TYPE_NAME;  }

    // Declared in TypeAnalysis.jrag at line 287
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

    private TypeDecl type_compute() {  return decl();  }

    protected int inExplicitConstructorInvocation_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 125
 @SuppressWarnings({"unchecked", "cast"})     public boolean inExplicitConstructorInvocation() {
        ASTNode$State state = state();
        if(inExplicitConstructorInvocation_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inExplicitConstructorInvocation in class: ");
        inExplicitConstructorInvocation_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
        inExplicitConstructorInvocation_visited = -1;
        return inExplicitConstructorInvocation_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
