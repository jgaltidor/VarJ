
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public abstract class Case extends Stmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isDAbefore_Variable_visited = null;
        isDAbefore_Variable_values = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUbefore_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        reachable_visited = -1;
        bind_Case_visited = null;
        bind_Case_values = null;
        switchType_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Case clone() throws CloneNotSupportedException {
        Case node = (Case)super.clone();
        node.isDAbefore_Variable_visited = null;
        node.isDAbefore_Variable_values = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUbefore_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.reachable_visited = -1;
        node.bind_Case_visited = null;
        node.bind_Case_values = null;
        node.switchType_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in java.ast at line 3
    // Declared in java.ast line 206

    public Case() {
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

    // Declared in NameCheck.jrag at line 426
 @SuppressWarnings({"unchecked", "cast"})     public abstract boolean constValue(Case c);
    protected java.util.Map isDAbefore_Variable_visited;
    protected java.util.Map isDAbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 573
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
        boolean isDAbefore_Variable_value = isDAbefore_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAbefore_Variable_values.put(_parameters, Boolean.valueOf(isDAbefore_Variable_value));
        isDAbefore_Variable_visited.remove(_parameters);
        return isDAbefore_Variable_value;
    }

    private boolean isDAbefore_compute(Variable v) {  return getParent().getParent() instanceof Block && ((Block)getParent().getParent()).isDAbefore(v)
    && super.isDAbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 577
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

    protected java.util.Map isDUbefore_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 1031
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_visited == null) isDUbefore_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUbefore in class: ");
        isDUbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUbefore_Variable_value = isDUbefore_compute(v);
        isDUbefore_Variable_visited.remove(_parameters);
        return isDUbefore_Variable_value;
    }

    private boolean isDUbefore_compute(Variable v) {  return getParent().getParent() instanceof Block && ((Block)getParent().getParent()).isDUbefore(v)
    && super.isDUbefore(v);  }

    // Declared in DefiniteAssignment.jrag at line 1035
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

    private boolean isDUafter_compute(Variable v) {  return isDUbefore(v);  }

    protected int reachable_visited = -1;
    // Declared in UnreachableStatements.jrag at line 83
 @SuppressWarnings({"unchecked", "cast"})     public boolean reachable() {
        ASTNode$State state = state();
        if(reachable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: reachable in class: ");
        reachable_visited = state().boundariesCrossed;
        boolean reachable_value = reachable_compute();
        reachable_visited = -1;
        return reachable_value;
    }

    private boolean reachable_compute() {  return getParent().getParent() instanceof Block && ((Block)getParent().getParent()).reachable();  }

    protected java.util.Map bind_Case_visited;
    protected java.util.Map bind_Case_values;
    // Declared in NameCheck.jrag at line 412
 @SuppressWarnings({"unchecked", "cast"})     public Case bind(Case c) {
        Object _parameters = c;
if(bind_Case_visited == null) bind_Case_visited = new java.util.HashMap(4);
if(bind_Case_values == null) bind_Case_values = new java.util.HashMap(4);
        if(bind_Case_values.containsKey(_parameters)) {
            return (Case)bind_Case_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(bind_Case_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: bind in class: ");
        bind_Case_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Case bind_Case_value = getParent().Define_Case_bind(this, null, c);
        if(isFinal && num == state().boundariesCrossed)
            bind_Case_values.put(_parameters, bind_Case_value);
        bind_Case_visited.remove(_parameters);
        return bind_Case_value;
    }

    protected int switchType_visited = -1;
    // Declared in TypeCheck.jrag at line 358
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl switchType() {
        ASTNode$State state = state();
        if(switchType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: switchType in class: ");
        switchType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl switchType_value = getParent().Define_TypeDecl_switchType(this, null);
        switchType_visited = -1;
        return switchType_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
