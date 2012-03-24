
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


// 8.5 Member Type Declarations

public abstract class MemberTypeDecl extends MemberDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        declaresType_String_visited = null;
        type_String_visited = null;
        isStatic_visited = -1;
        addsIndentationLevel_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public MemberTypeDecl clone() throws CloneNotSupportedException {
        MemberTypeDecl node = (MemberTypeDecl)super.clone();
        node.declaresType_String_visited = null;
        node.type_String_visited = null;
        node.isStatic_visited = -1;
        node.addsIndentationLevel_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in java.ast at line 3
    // Declared in java.ast line 91

    public MemberTypeDecl() {
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

    // Declared in LookupType.jrag at line 396
 @SuppressWarnings({"unchecked", "cast"})     public abstract TypeDecl typeDecl();
    protected java.util.Map declaresType_String_visited;
    // Declared in LookupType.jrag at line 392
 @SuppressWarnings({"unchecked", "cast"})     public boolean declaresType(String name) {
        Object _parameters = name;
if(declaresType_String_visited == null) declaresType_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(declaresType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: declaresType in class: ");
        declaresType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean declaresType_String_value = declaresType_compute(name);
        declaresType_String_visited.remove(_parameters);
        return declaresType_String_value;
    }

    private boolean declaresType_compute(String name) {  return typeDecl().name().equals(name);  }

    protected java.util.Map type_String_visited;
    // Declared in LookupType.jrag at line 394
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type(String name) {
        Object _parameters = name;
if(type_String_visited == null) type_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(type_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: type in class: ");
        type_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        TypeDecl type_String_value = type_compute(name);
        type_String_visited.remove(_parameters);
        return type_String_value;
    }

    private TypeDecl type_compute(String name) {  return declaresType(name) ? typeDecl() : null;  }

    protected int isStatic_visited = -1;
    // Declared in Modifiers.jrag at line 246
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        ASTNode$State state = state();
        if(isStatic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isStatic in class: ");
        isStatic_visited = state().boundariesCrossed;
        boolean isStatic_value = isStatic_compute();
        isStatic_visited = -1;
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return typeDecl().isStatic();  }

    protected int addsIndentationLevel_visited = -1;
    // Declared in PrettyPrint.jadd at line 760
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        if(addsIndentationLevel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: addsIndentationLevel in class: ");
        addsIndentationLevel_visited = state().boundariesCrossed;
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        addsIndentationLevel_visited = -1;
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return false;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
