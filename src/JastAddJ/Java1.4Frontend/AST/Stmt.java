
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


// Statements


public abstract class Stmt extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        declaresVariable_String_visited = null;
        continueLabel_visited = -1;
        addsIndentationLevel_visited = -1;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
        isDAbefore_Variable_visited = null;
        isDUbefore_Variable_visited = null;
        lookupMethod_String_visited = null;
        lookupType_String_String_visited = null;
        lookupType_String_visited = null;
        lookupVariable_String_visited = null;
        enclosingBodyDecl_visited = -1;
        hostType_visited = -1;
        reachable_visited = -1;
        reportUnreachable_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Stmt clone() throws CloneNotSupportedException {
        Stmt node = (Stmt)super.clone();
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.declaresVariable_String_visited = null;
        node.continueLabel_visited = -1;
        node.addsIndentationLevel_visited = -1;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.isDAbefore_Variable_visited = null;
        node.isDUbefore_Variable_visited = null;
        node.lookupMethod_String_visited = null;
        node.lookupType_String_String_visited = null;
        node.lookupType_String_visited = null;
        node.lookupVariable_String_visited = null;
        node.enclosingBodyDecl_visited = -1;
        node.hostType_visited = -1;
        node.reachable_visited = -1;
        node.reportUnreachable_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in UnreachableStatements.jrag at line 14

  void checkUnreachableStmt() {
    if(!reachable() && reportUnreachable())
      error("statement is unreachable");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 198

    public Stmt() {
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
    // Declared in DefiniteAssignment.jrag at line 327
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

    private boolean isDAafter_compute(Variable v) {  return isDAbefore(v);  }

    protected java.util.Map isDUafter_Variable_visited;
    protected java.util.Map isDUafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 780
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

    private boolean isDUafter_compute(Variable v) {
    throw new Error("isDUafter in " + getClass().getName());
  }

    protected java.util.Map declaresVariable_String_visited;
    // Declared in LookupVariable.jrag at line 127
 @SuppressWarnings({"unchecked", "cast"})     public boolean declaresVariable(String name) {
        Object _parameters = name;
if(declaresVariable_String_visited == null) declaresVariable_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(declaresVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: declaresVariable in class: ");
        declaresVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean declaresVariable_String_value = declaresVariable_compute(name);
        declaresVariable_String_visited.remove(_parameters);
        return declaresVariable_String_value;
    }

    private boolean declaresVariable_compute(String name) {  return false;  }

    protected int continueLabel_visited = -1;
    // Declared in NameCheck.jrag at line 396
 @SuppressWarnings({"unchecked", "cast"})     public boolean continueLabel() {
        ASTNode$State state = state();
        if(continueLabel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: continueLabel in class: ");
        continueLabel_visited = state().boundariesCrossed;
        boolean continueLabel_value = continueLabel_compute();
        continueLabel_visited = -1;
        return continueLabel_value;
    }

    private boolean continueLabel_compute() {  return false;  }

    protected int addsIndentationLevel_visited = -1;
    // Declared in PrettyPrint.jadd at line 761
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

    protected int canCompleteNormally_visited = -1;
    protected boolean canCompleteNormally_computed = false;
    protected boolean canCompleteNormally_value;
    // Declared in UnreachableStatements.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean canCompleteNormally() {
        if(canCompleteNormally_computed) {
            return canCompleteNormally_value;
        }
        ASTNode$State state = state();
        if(canCompleteNormally_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: canCompleteNormally in class: ");
        canCompleteNormally_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        canCompleteNormally_value = canCompleteNormally_compute();
        if(isFinal && num == state().boundariesCrossed)
            canCompleteNormally_computed = true;
        canCompleteNormally_visited = -1;
        return canCompleteNormally_value;
    }

    private boolean canCompleteNormally_compute() {  return true;  }

    protected java.util.Map isDAbefore_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 234
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAbefore(Variable v) {
        Object _parameters = v;
if(isDAbefore_Variable_visited == null) isDAbefore_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAbefore in class: ");
        isDAbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDAbefore_Variable_value = getParent().Define_boolean_isDAbefore(this, null, v);
        isDAbefore_Variable_visited.remove(_parameters);
        return isDAbefore_Variable_value;
    }

    protected java.util.Map isDUbefore_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 694
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_visited == null) isDUbefore_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUbefore in class: ");
        isDUbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        isDUbefore_Variable_visited.remove(_parameters);
        return isDUbefore_Variable_value;
    }

    protected java.util.Map lookupMethod_String_visited;
    // Declared in LookupMethod.jrag at line 24
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
    // Declared in LookupType.jrag at line 96
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
    // Declared in LookupType.jrag at line 174
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
    // Declared in LookupVariable.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupVariable(String name) {
        Object _parameters = name;
if(lookupVariable_String_visited == null) lookupVariable_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupVariable in class: ");
        lookupVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        SimpleSet lookupVariable_String_value = getParent().Define_SimpleSet_lookupVariable(this, null, name);
        lookupVariable_String_visited.remove(_parameters);
        return lookupVariable_String_value;
    }

    protected int enclosingBodyDecl_visited = -1;
    // Declared in TypeAnalysis.jrag at line 512
 @SuppressWarnings({"unchecked", "cast"})     public BodyDecl enclosingBodyDecl() {
        ASTNode$State state = state();
        if(enclosingBodyDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingBodyDecl in class: ");
        enclosingBodyDecl_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        BodyDecl enclosingBodyDecl_value = getParent().Define_BodyDecl_enclosingBodyDecl(this, null);
        enclosingBodyDecl_visited = -1;
        return enclosingBodyDecl_value;
    }

    protected int hostType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 584
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

    protected int reachable_visited = -1;
    // Declared in UnreachableStatements.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean reachable() {
        ASTNode$State state = state();
        if(reachable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: reachable in class: ");
        reachable_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean reachable_value = getParent().Define_boolean_reachable(this, null);
        reachable_visited = -1;
        return reachable_value;
    }

    protected int reportUnreachable_visited = -1;
    // Declared in UnreachableStatements.jrag at line 145
 @SuppressWarnings({"unchecked", "cast"})     public boolean reportUnreachable() {
        ASTNode$State state = state();
        if(reportUnreachable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: reportUnreachable in class: ");
        reportUnreachable_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean reportUnreachable_value = getParent().Define_boolean_reportUnreachable(this, null);
        reportUnreachable_visited = -1;
        return reportUnreachable_value;
    }

    // Declared in PrettyPrint.jadd at line 351
    public String Define_String_typeDeclIndent(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return indent();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_typeDeclIndent(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
