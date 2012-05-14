
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class CatchClause extends ASTNode<ASTNode> implements Cloneable, VariableScope {
    public void flushCache() {
        super.flushCache();
        handles_TypeDecl_visited = null;
        parameterDeclaration_String_visited = null;
        parameterDeclaration_String_values = null;
        typeThrowable_visited = -1;
        typeThrowable_computed = false;
        typeThrowable_value = null;
        lookupVariable_String_visited = null;
        lookupVariable_String_values = null;
        reachableCatchClause_visited = -1;
        reachableCatchClause_computed = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public CatchClause clone() throws CloneNotSupportedException {
        CatchClause node = (CatchClause)super.clone();
        node.handles_TypeDecl_visited = null;
        node.parameterDeclaration_String_visited = null;
        node.parameterDeclaration_String_values = null;
        node.typeThrowable_visited = -1;
        node.typeThrowable_computed = false;
        node.typeThrowable_value = null;
        node.lookupVariable_String_visited = null;
        node.lookupVariable_String_values = null;
        node.reachableCatchClause_visited = -1;
        node.reachableCatchClause_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CatchClause copy() {
      try {
          CatchClause node = (CatchClause)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CatchClause fullCopy() {
        CatchClause res = (CatchClause)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 721


  public void toString(StringBuffer s) {
    s.append("catch (");
    getParameter().toString(s);
    s.append(") ");
    getBlock().toString(s);
  }

    // Declared in TypeCheck.jrag at line 368


  public void typeCheck() {
    if(!getParameter().type().instanceOf(typeThrowable()))
      error("*** The catch variable must extend Throwable");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 223

    public CatchClause() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 223
    public CatchClause(ParameterDeclaration p0, Block p1) {
        setChild(p0, 0);
        setChild(p1, 1);
    }

    // Declared in java.ast at line 15


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 18

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 223
    public void setParameter(ParameterDeclaration node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public ParameterDeclaration getParameter() {
        return (ParameterDeclaration)getChild(0);
    }

    // Declared in java.ast at line 9


    public ParameterDeclaration getParameterNoTransform() {
        return (ParameterDeclaration)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 223
    public void setBlock(Block node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Block getBlock() {
        return (Block)getChild(1);
    }

    // Declared in java.ast at line 9


    public Block getBlockNoTransform() {
        return (Block)getChildNoTransform(1);
    }

    protected java.util.Map handles_TypeDecl_visited;
    // Declared in ExceptionHandling.jrag at line 189
 @SuppressWarnings({"unchecked", "cast"})     public boolean handles(TypeDecl exceptionType) {
        Object _parameters = exceptionType;
if(handles_TypeDecl_visited == null) handles_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(handles_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: handles in class: ");
        handles_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean handles_TypeDecl_value = handles_compute(exceptionType);
        handles_TypeDecl_visited.remove(_parameters);
        return handles_TypeDecl_value;
    }

    private boolean handles_compute(TypeDecl exceptionType) {  return !getParameter().type().isUnknown()
    && exceptionType.instanceOf(getParameter().type());  }

    protected java.util.Map parameterDeclaration_String_visited;
    protected java.util.Map parameterDeclaration_String_values;
    // Declared in LookupVariable.jrag at line 111
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet parameterDeclaration(String name) {
        Object _parameters = name;
if(parameterDeclaration_String_visited == null) parameterDeclaration_String_visited = new java.util.HashMap(4);
if(parameterDeclaration_String_values == null) parameterDeclaration_String_values = new java.util.HashMap(4);
        if(parameterDeclaration_String_values.containsKey(_parameters)) {
            return (SimpleSet)parameterDeclaration_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(parameterDeclaration_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: parameterDeclaration in class: ");
        parameterDeclaration_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet parameterDeclaration_String_value = parameterDeclaration_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            parameterDeclaration_String_values.put(_parameters, parameterDeclaration_String_value);
        parameterDeclaration_String_visited.remove(_parameters);
        return parameterDeclaration_String_value;
    }

    private SimpleSet parameterDeclaration_compute(String name) {  return getParameter().name().equals(name) ? (ParameterDeclaration)getParameter() : SimpleSet.emptySet;  }

    protected int typeThrowable_visited = -1;
    protected boolean typeThrowable_computed = false;
    protected TypeDecl typeThrowable_value;
    // Declared in LookupType.jrag at line 68
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

    protected java.util.Map lookupVariable_String_visited;
    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 20
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

    protected int reachableCatchClause_visited = -1;
    protected boolean reachableCatchClause_computed = false;
    protected boolean reachableCatchClause_value;
    // Declared in UnreachableStatements.jrag at line 124
 @SuppressWarnings({"unchecked", "cast"})     public boolean reachableCatchClause() {
        if(reachableCatchClause_computed) {
            return reachableCatchClause_value;
        }
        ASTNode$State state = state();
        if(reachableCatchClause_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: reachableCatchClause in class: ");
        reachableCatchClause_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        reachableCatchClause_value = getParent().Define_boolean_reachableCatchClause(this, null);
        if(isFinal && num == state().boundariesCrossed)
            reachableCatchClause_computed = true;
        reachableCatchClause_visited = -1;
        return reachableCatchClause_value;
    }

    // Declared in LookupVariable.jrag at line 83
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getParameterNoTransform()) {
            return parameterDeclaration(name);
        }
        if(caller == getBlockNoTransform()){
    SimpleSet set = parameterDeclaration(name);
    if(!set.isEmpty()) return set;
    return lookupVariable(name);
  }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in NameCheck.jrag at line 290
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_VariableScope_outerScope(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 86
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return NameType.TYPE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 122
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return reachableCatchClause();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 83
    public boolean Define_boolean_isMethodParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isMethodParameter(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 84
    public boolean Define_boolean_isConstructorParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isConstructorParameter(this, caller);
    }

    // Declared in VariableDeclaration.jrag at line 85
    public boolean Define_boolean_isExceptionHandlerParameter(ASTNode caller, ASTNode child) {
        if(caller == getParameterNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isExceptionHandlerParameter(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
