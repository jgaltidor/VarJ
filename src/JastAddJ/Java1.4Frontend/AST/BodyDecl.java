
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public abstract class BodyDecl extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        declaresType_String_visited = null;
        type_String_visited = null;
        addsIndentationLevel_visited = -1;
        isVoid_visited = -1;
        isDAbefore_Variable_visited = null;
        isDAbefore_Variable_values = null;
        isDUbefore_Variable_visited = null;
        isDUbefore_Variable_values = null;
        typeThrowable_visited = -1;
        typeThrowable_computed = false;
        typeThrowable_value = null;
        lookupMethod_String_visited = null;
        lookupType_String_String_visited = null;
        lookupType_String_visited = null;
        lookupVariable_String_visited = null;
        lookupVariable_String_values = null;
        nameType_visited = -1;
        hostPackage_visited = -1;
        hostType_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public BodyDecl clone() throws CloneNotSupportedException {
        BodyDecl node = (BodyDecl)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.declaresType_String_visited = null;
        node.type_String_visited = null;
        node.addsIndentationLevel_visited = -1;
        node.isVoid_visited = -1;
        node.isDAbefore_Variable_visited = null;
        node.isDAbefore_Variable_values = null;
        node.isDUbefore_Variable_visited = null;
        node.isDUbefore_Variable_values = null;
        node.typeThrowable_visited = -1;
        node.typeThrowable_computed = false;
        node.typeThrowable_value = null;
        node.lookupMethod_String_visited = null;
        node.lookupType_String_String_visited = null;
        node.lookupType_String_visited = null;
        node.lookupVariable_String_visited = null;
        node.lookupVariable_String_values = null;
        node.nameType_visited = -1;
        node.hostPackage_visited = -1;
        node.hostType_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in BranchTarget.jrag at line 211

  public void collectFinally(Stmt branchStmt, ArrayList list) {
    // terminate search if body declaration is reached
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 69

    public BodyDecl() {
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

    protected java.util.Map isDAafter_Variable_visited;
    protected java.util.Map isDAafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 245
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        Object _parameters = v;
if(isDAafter_Variable_visited == null) isDAafter_Variable_visited = new java.util.HashMap(4);
if(isDAafter_Variable_values == null) isDAafter_Variable_values = new java.util.HashMap(4);
        if(isDAafter_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAafter_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafter in class: ");
        isDAafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAafter_Variable_values.put(_parameters, Boolean.valueOf(isDAafter_Variable_value));
        isDAafter_Variable_visited.remove(_parameters);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return true;  }

    protected java.util.Map isDUafter_Variable_visited;
    protected java.util.Map isDUafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 711
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        Object _parameters = v;
if(isDUafter_Variable_visited == null) isDUafter_Variable_visited = new java.util.HashMap(4);
if(isDUafter_Variable_values == null) isDUafter_Variable_values = new java.util.HashMap(4);
        if(isDUafter_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUafter_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafter in class: ");
        isDUafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDUafter_Variable_values.put(_parameters, Boolean.valueOf(isDUafter_Variable_value));
        isDUafter_Variable_visited.remove(_parameters);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {  return true;  }

    protected java.util.Map declaresType_String_visited;
    // Declared in LookupType.jrag at line 391
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

    private boolean declaresType_compute(String name) {  return false;  }

    protected java.util.Map type_String_visited;
    // Declared in LookupType.jrag at line 393
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

    private TypeDecl type_compute(String name) {  return null;  }

    protected int addsIndentationLevel_visited = -1;
    // Declared in PrettyPrint.jadd at line 759
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        if(addsIndentationLevel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: addsIndentationLevel in class: ");
        addsIndentationLevel_visited = state().boundariesCrossed;
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        addsIndentationLevel_visited = -1;
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return true;  }

    protected int isVoid_visited = -1;
    // Declared in TypeAnalysis.jrag at line 271
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVoid() {
        ASTNode$State state = state();
        if(isVoid_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVoid in class: ");
        isVoid_visited = state().boundariesCrossed;
        boolean isVoid_value = isVoid_compute();
        isVoid_visited = -1;
        return isVoid_value;
    }

    private boolean isVoid_compute() {  return false;  }

    protected java.util.Map isDAbefore_Variable_visited;
    protected java.util.Map isDAbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 244
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAbefore(Variable v) {
        Object _parameters = v;
if(isDAbefore_Variable_visited == null) isDAbefore_Variable_visited = new java.util.HashMap(4);
if(isDAbefore_Variable_values == null) isDAbefore_Variable_values = new java.util.HashMap(4);
        if(isDAbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAbefore in class: ");
        isDAbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDAbefore_Variable_value = getParent().Define_boolean_isDAbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDAbefore_Variable_values.put(_parameters, Boolean.valueOf(isDAbefore_Variable_value));
        isDAbefore_Variable_visited.remove(_parameters);
        return isDAbefore_Variable_value;
    }

    protected java.util.Map isDUbefore_Variable_visited;
    protected java.util.Map isDUbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 710
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_visited == null) isDUbefore_Variable_visited = new java.util.HashMap(4);
if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
        if(isDUbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUbefore in class: ");
        isDUbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDUbefore_Variable_values.put(_parameters, Boolean.valueOf(isDUbefore_Variable_value));
        isDUbefore_Variable_visited.remove(_parameters);
        return isDUbefore_Variable_value;
    }

    protected int typeThrowable_visited = -1;
    protected boolean typeThrowable_computed = false;
    protected TypeDecl typeThrowable_value;
    // Declared in ExceptionHandling.jrag at line 22
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeThrowable() {
        if(typeThrowable_computed) {
            return typeThrowable_value;
        }
        ASTNode$State state = state();
        if(typeThrowable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeThrowable in class: ");
        typeThrowable_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeThrowable_value = getParent().Define_TypeDecl_typeThrowable(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeThrowable_computed = true;
        typeThrowable_visited = -1;
        return typeThrowable_value;
    }

    protected java.util.Map lookupMethod_String_visited;
    // Declared in LookupMethod.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupMethod(String name) {
        Object _parameters = name;
if(lookupMethod_String_visited == null) lookupMethod_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupMethod_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupMethod in class: ");
        lookupMethod_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Collection lookupMethod_String_value = getParent().Define_Collection_lookupMethod(this, null, name);
        lookupMethod_String_visited.remove(_parameters);
        return lookupMethod_String_value;
    }

    protected java.util.Map lookupType_String_String_visited;
    // Declared in LookupType.jrag at line 97
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl lookupType(String packageName, String typeName) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(packageName);
        _parameters.add(typeName);
if(lookupType_String_String_visited == null) lookupType_String_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupType_String_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupType in class: ");
        lookupType_String_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl lookupType_String_String_value = getParent().Define_TypeDecl_lookupType(this, null, packageName, typeName);
        lookupType_String_String_visited.remove(_parameters);
        return lookupType_String_String_value;
    }

    protected java.util.Map lookupType_String_visited;
    // Declared in LookupType.jrag at line 173
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupType(String name) {
        Object _parameters = name;
if(lookupType_String_visited == null) lookupType_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupType in class: ");
        lookupType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
        lookupType_String_visited.remove(_parameters);
        return lookupType_String_value;
    }

    protected java.util.Map lookupVariable_String_visited;
    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        Object _parameters = name;
if(lookupVariable_String_visited == null) lookupVariable_String_visited = new java.util.HashMap(4);
if(lookupVariable_String_values == null) lookupVariable_String_values = new java.util.HashMap(4);
        if(lookupVariable_String_values.containsKey(_parameters)) {
            return (SimpleSet)lookupVariable_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupVariable in class: ");
        lookupVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        if(isFinal && num == state().boundariesCrossed)
            lookupVariable_String_values.put(_parameters, lookupVariable_String_value);
        lookupVariable_String_visited.remove(_parameters);
        return lookupVariable_String_value;
    }

    protected int nameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public NameType nameType() {
        ASTNode$State state = state();
        if(nameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: nameType in class: ");
        nameType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        NameType nameType_value = getParent().Define_NameType_nameType(this, null);
        nameType_visited = -1;
        return nameType_value;
    }

    protected int hostPackage_visited = -1;
    // Declared in TypeAnalysis.jrag at line 567
 @SuppressWarnings({"unchecked", "cast"})     public String hostPackage() {
        ASTNode$State state = state();
        if(hostPackage_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hostPackage in class: ");
        hostPackage_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        String hostPackage_value = getParent().Define_String_hostPackage(this, null);
        hostPackage_visited = -1;
        return hostPackage_value;
    }

    protected int hostType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 582
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        if(hostType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hostType in class: ");
        hostType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl hostType_value = getParent().Define_TypeDecl_hostType(this, null);
        hostType_visited = -1;
        return hostType_value;
    }

    // Declared in PrettyPrint.jadd at line 352
    public String Define_String_typeDeclIndent(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return indent();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_typeDeclIndent(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 515
    public BodyDecl Define_BodyDecl_enclosingBodyDecl(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_BodyDecl_enclosingBodyDecl(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
