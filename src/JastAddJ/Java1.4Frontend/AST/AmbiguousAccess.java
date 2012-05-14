
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class AmbiguousAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        qualifiedLookupType_String_visited = null;
        qualifiedLookupVariable_String_visited = null;
        dumpString_visited = -1;
        name_visited = -1;
        predNameType_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public AmbiguousAccess clone() throws CloneNotSupportedException {
        AmbiguousAccess node = (AmbiguousAccess)super.clone();
        node.qualifiedLookupType_String_visited = null;
        node.qualifiedLookupVariable_String_visited = null;
        node.dumpString_visited = -1;
        node.name_visited = -1;
        node.predNameType_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AmbiguousAccess copy() {
      try {
          AmbiguousAccess node = (AmbiguousAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public AmbiguousAccess fullCopy() {
        AmbiguousAccess res = (AmbiguousAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in NameCheck.jrag at line 51


  public void nameCheck() {
    error("ambiguous name " + name());
  }

    // Declared in NodeConstructors.jrag at line 33

  public AmbiguousAccess(String name, int start, int end) {
    this(name);
    this.start = start;
    this.end = end;
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 32

    public AmbiguousAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 32
    public AmbiguousAccess(String p0) {
        setID(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 32
    public AmbiguousAccess(beaver.Symbol p0) {
        setID(p0);
    }

    // Declared in java.ast at line 19


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 22

    public boolean mayHaveRewrite() {
        return true;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 32
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

    protected java.util.Map qualifiedLookupType_String_visited;
    // Declared in LookupType.jrag at line 339
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupType(String name) {
        Object _parameters = name;
if(qualifiedLookupType_String_visited == null) qualifiedLookupType_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(qualifiedLookupType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: qualifiedLookupType in class: ");
        qualifiedLookupType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet qualifiedLookupType_String_value = qualifiedLookupType_compute(name);
        qualifiedLookupType_String_visited.remove(_parameters);
        return qualifiedLookupType_String_value;
    }

    private SimpleSet qualifiedLookupType_compute(String name) {  return SimpleSet.emptySet;  }

    protected java.util.Map qualifiedLookupVariable_String_visited;
    // Declared in LookupVariable.jrag at line 141
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupVariable(String name) {
        Object _parameters = name;
if(qualifiedLookupVariable_String_visited == null) qualifiedLookupVariable_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(qualifiedLookupVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: qualifiedLookupVariable in class: ");
        qualifiedLookupVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet qualifiedLookupVariable_String_value = qualifiedLookupVariable_compute(name);
        qualifiedLookupVariable_String_visited.remove(_parameters);
        return qualifiedLookupVariable_String_value;
    }

    private SimpleSet qualifiedLookupVariable_compute(String name) {  return SimpleSet.emptySet;  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 808
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        if(dumpString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: dumpString in class: ");
        dumpString_visited = state().boundariesCrossed;
        String dumpString_value = dumpString_compute();
        dumpString_visited = -1;
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    protected int name_visited = -1;
    // Declared in QualifiedNames.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        if(name_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = state().boundariesCrossed;
        String name_value = name_compute();
        name_visited = -1;
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 112
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

public ASTNode rewriteTo() {
    // Declared in ResolveAmbiguousNames.jrag at line 189
    if(!duringSyntacticClassification()) {
        state().duringResolveAmbiguousNames++;
        ASTNode result = rewriteRule0();
        state().duringResolveAmbiguousNames--;
        return result;
    }

    return super.rewriteTo();
}

    // Declared in ResolveAmbiguousNames.jrag at line 189
    private Access rewriteRule0() {
         debugRewrite("Rewriting " + getClass().getName() + " using rule in ResolveAmbiguousNames.jrag at line 189");
{
      if(!lookupVariable(name()).isEmpty()) {
        return new VarAccess(name(), start(), end());
      }
      else if(!lookupType(name()).isEmpty()) {
        return new TypeAccess(name(), start(), end());
      }
      else {
        return new PackageAccess(name(), start(), end());
      }
    }    }
}
