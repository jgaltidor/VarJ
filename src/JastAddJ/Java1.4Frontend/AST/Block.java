
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;
  // a statement that can be reached by break or continue

public class Block extends Stmt implements Cloneable, VariableScope {
    public void flushCache() {
        super.flushCache();
        checkReturnDA_Variable_visited = null;
        checkReturnDA_Variable_values = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUeverywhere_Variable_visited = null;
        checkReturnDU_Variable_visited = null;
        checkReturnDU_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        localVariableDeclaration_String_visited = null;
        localVariableDeclaration_String_values = null;
        addsIndentationLevel_visited = -1;
        shouldHaveIndent_visited = -1;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
        lookupType_String_visited = null;
        lookupType_String_values = null;
        lookupVariable_String_visited = null;
        lookupVariable_String_values = null;
        reachable_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Block clone() throws CloneNotSupportedException {
        Block node = (Block)super.clone();
        node.checkReturnDA_Variable_visited = null;
        node.checkReturnDA_Variable_values = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUeverywhere_Variable_visited = null;
        node.checkReturnDU_Variable_visited = null;
        node.checkReturnDU_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.localVariableDeclaration_String_visited = null;
        node.localVariableDeclaration_String_values = null;
        node.addsIndentationLevel_visited = -1;
        node.shouldHaveIndent_visited = -1;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.lookupType_String_visited = null;
        node.lookupType_String_values = null;
        node.lookupVariable_String_visited = null;
        node.lookupVariable_String_values = null;
        node.reachable_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Block copy() {
      try {
          Block node = (Block)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Block fullCopy() {
        Block res = (Block)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DeclareBeforeUse.jrag at line 21


  public boolean declaredBeforeUse(Variable decl, ASTNode use) {
    int indexDecl = ((ASTNode)decl).varChildIndex(this);
    int indexUse = use.varChildIndex(this);
    return indexDecl <= indexUse;
  }

    // Declared in DeclareBeforeUse.jrag at line 26

  public boolean declaredBeforeUse(Variable decl, int indexUse) {
    int indexDecl = ((ASTNode)decl).varChildIndex(this);
    return indexDecl <= indexUse;
  }

    // Declared in PrettyPrint.jadd at line 525


  // Stmts

  public void toString(StringBuffer s) {
    String indent = indent();
    s.append(shouldHaveIndent() ? indent : "");
    s.append("{");
    for(int i = 0; i < getNumStmt(); i++) {
      getStmt(i).toString(s);
    }
    s.append(shouldHaveIndent() ? indent : indent.substring(0, indent.length()-2));
    s.append("}");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 200

    public Block() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 200
    public Block(List<Stmt> p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 15


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 18

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 200
    public void setStmtList(List<Stmt> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumStmt() {
        return getStmtList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Stmt getStmt(int i) {
        return (Stmt)getStmtList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addStmt(Stmt node) {
        List<Stmt> list = (parent == null || state == null) ? getStmtListNoTransform() : getStmtList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addStmtNoTransform(Stmt node) {
        List<Stmt> list = getStmtListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setStmt(Stmt node, int i) {
        List<Stmt> list = getStmtList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Stmt> getStmts() {
        return getStmtList();
    }

    // Declared in java.ast at line 31

    public List<Stmt> getStmtsNoTransform() {
        return getStmtListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Stmt> getStmtList() {
        List<Stmt> list = (List<Stmt>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Stmt> getStmtListNoTransform() {
        return (List<Stmt>)getChildNoTransform(0);
    }

    protected java.util.Map checkReturnDA_Variable_visited;
    protected java.util.Map checkReturnDA_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 302
 @SuppressWarnings({"unchecked", "cast"})     public boolean checkReturnDA(Variable v) {
        Object _parameters = v;
if(checkReturnDA_Variable_visited == null) checkReturnDA_Variable_visited = new java.util.HashMap(4);
if(checkReturnDA_Variable_values == null) checkReturnDA_Variable_values = new java.util.HashMap(4);
        if(checkReturnDA_Variable_values.containsKey(_parameters)) {
            return ((Boolean)checkReturnDA_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(checkReturnDA_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: checkReturnDA in class: ");
        checkReturnDA_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean checkReturnDA_Variable_value = checkReturnDA_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            checkReturnDA_Variable_values.put(_parameters, Boolean.valueOf(checkReturnDA_Variable_value));
        checkReturnDA_Variable_visited.remove(_parameters);
        return checkReturnDA_Variable_value;
    }

    private boolean checkReturnDA_compute(Variable v) {
    HashSet set = new HashSet();
    collectBranches(set);
    for(Iterator iter = set.iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ReturnStmt) {
        ReturnStmt stmt = (ReturnStmt)o;
        if(!stmt.isDAafterReachedFinallyBlocks(v))
          return false;
      }
    }
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 444
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

    private boolean isDAafter_compute(Variable v) {  return getNumStmt() == 0 ? isDAbefore(v) : getStmt(getNumStmt()-1).isDAafter(v);  }

    protected java.util.Map isDUeverywhere_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 450
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUeverywhere(Variable v) {
        Object _parameters = v;
if(isDUeverywhere_Variable_visited == null) isDUeverywhere_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUeverywhere_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUeverywhere in class: ");
        isDUeverywhere_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUeverywhere_Variable_value = isDUeverywhere_compute(v);
        isDUeverywhere_Variable_visited.remove(_parameters);
        return isDUeverywhere_Variable_value;
    }

    private boolean isDUeverywhere_compute(Variable v) {  return isDUbefore(v) && checkDUeverywhere(v);  }

    protected java.util.Map checkReturnDU_Variable_visited;
    protected java.util.Map checkReturnDU_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 760
 @SuppressWarnings({"unchecked", "cast"})     public boolean checkReturnDU(Variable v) {
        Object _parameters = v;
if(checkReturnDU_Variable_visited == null) checkReturnDU_Variable_visited = new java.util.HashMap(4);
if(checkReturnDU_Variable_values == null) checkReturnDU_Variable_values = new java.util.HashMap(4);
        if(checkReturnDU_Variable_values.containsKey(_parameters)) {
            return ((Boolean)checkReturnDU_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(checkReturnDU_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: checkReturnDU in class: ");
        checkReturnDU_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean checkReturnDU_Variable_value = checkReturnDU_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            checkReturnDU_Variable_values.put(_parameters, Boolean.valueOf(checkReturnDU_Variable_value));
        checkReturnDU_Variable_visited.remove(_parameters);
        return checkReturnDU_Variable_value;
    }

    private boolean checkReturnDU_compute(Variable v) {
    HashSet set = new HashSet();
    collectBranches(set);
    for(Iterator iter = set.iterator(); iter.hasNext(); ) {
      Object o = iter.next();
      if(o instanceof ReturnStmt) {
        ReturnStmt stmt = (ReturnStmt)o;
        if(!stmt.isDUafterReachedFinallyBlocks(v))
          return false;
      }
    }
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 876
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

    private boolean isDUafter_compute(Variable v) {  return getNumStmt() == 0 ? isDUbefore(v) : getStmt(getNumStmt()-1).isDUafter(v);  }

    protected java.util.Map localVariableDeclaration_String_visited;
    protected java.util.Map localVariableDeclaration_String_values;
    // Declared in LookupVariable.jrag at line 114
 @SuppressWarnings({"unchecked", "cast"})     public VariableDeclaration localVariableDeclaration(String name) {
        Object _parameters = name;
if(localVariableDeclaration_String_visited == null) localVariableDeclaration_String_visited = new java.util.HashMap(4);
if(localVariableDeclaration_String_values == null) localVariableDeclaration_String_values = new java.util.HashMap(4);
        if(localVariableDeclaration_String_values.containsKey(_parameters)) {
            return (VariableDeclaration)localVariableDeclaration_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(localVariableDeclaration_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: localVariableDeclaration in class: ");
        localVariableDeclaration_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        VariableDeclaration localVariableDeclaration_String_value = localVariableDeclaration_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            localVariableDeclaration_String_values.put(_parameters, localVariableDeclaration_String_value);
        localVariableDeclaration_String_visited.remove(_parameters);
        return localVariableDeclaration_String_value;
    }

    private VariableDeclaration localVariableDeclaration_compute(String name) {
    for(int i = 0; i < getNumStmt(); i++)
      if(getStmt(i).declaresVariable(name))
        return (VariableDeclaration)getStmt(i);
    return null;
  }

    protected int addsIndentationLevel_visited = -1;
    // Declared in PrettyPrint.jadd at line 762
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        if(addsIndentationLevel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: addsIndentationLevel in class: ");
        addsIndentationLevel_visited = state().boundariesCrossed;
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        addsIndentationLevel_visited = -1;
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return shouldHaveIndent();  }

    protected int shouldHaveIndent_visited = -1;
    // Declared in PrettyPrint.jadd at line 764
 @SuppressWarnings({"unchecked", "cast"})     public boolean shouldHaveIndent() {
        ASTNode$State state = state();
        if(shouldHaveIndent_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: shouldHaveIndent in class: ");
        shouldHaveIndent_visited = state().boundariesCrossed;
        boolean shouldHaveIndent_value = shouldHaveIndent_compute();
        shouldHaveIndent_visited = -1;
        return shouldHaveIndent_value;
    }

    private boolean shouldHaveIndent_compute() {  return getParent() instanceof List && getParent().getParent() instanceof Block;  }

    // Declared in UnreachableStatements.jrag at line 37
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

    private boolean canCompleteNormally_compute() {  return getNumStmt() == 0 ? reachable() : getStmt(getNumStmt() - 1).canCompleteNormally();  }

    protected java.util.Map lookupType_String_visited;
    protected java.util.Map lookupType_String_values;
    // Declared in LookupType.jrag at line 175
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet lookupType(String name) {
        Object _parameters = name;
if(lookupType_String_visited == null) lookupType_String_visited = new java.util.HashMap(4);
if(lookupType_String_values == null) lookupType_String_values = new java.util.HashMap(4);
        if(lookupType_String_values.containsKey(_parameters)) {
            return (SimpleSet)lookupType_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupType in class: ");
        lookupType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        SimpleSet lookupType_String_value = getParent().Define_SimpleSet_lookupType(this, null, name);
        if(isFinal && num == state().boundariesCrossed)
            lookupType_String_values.put(_parameters, lookupType_String_value);
        lookupType_String_visited.remove(_parameters);
        return lookupType_String_value;
    }

    protected java.util.Map lookupVariable_String_visited;
    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 17
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

    protected int reachable_visited = -1;
    // Declared in UnreachableStatements.jrag at line 28
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

    // Declared in DefiniteAssignment.jrag at line 52
    public boolean Define_boolean_isIncOrDec(ASTNode caller, ASTNode child) {
        if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isIncOrDec(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 447
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getStmtListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return index == 0 ? isDAbefore(v) : getStmt(index - 1).isDAafter(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 877
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getStmtListNoTransform()) {
      int index = caller.getIndexOfChild(child);
            return index == 0 ? isDUbefore(v) : getStmt(index - 1).isDUafter(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in LookupType.jrag at line 292
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    SimpleSet c = SimpleSet.emptySet;
    for(int i = index; i >= 0 && !(getStmt(i) instanceof Case); i--) {
      if(getStmt(i) instanceof LocalClassDeclStmt) {
        TypeDecl t = ((LocalClassDeclStmt)getStmt(i)).getClassDecl();
        if(t.name().equals(name)) {
          c = c.add(t);
        }
      }
    }
    if(!c.isEmpty())
      return c;
    return lookupType(name);
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 68
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtListNoTransform()) { 
   int index = caller.getIndexOfChild(child);
{
    VariableDeclaration v = localVariableDeclaration(name);
    // declare before use and shadowing
    if(v != null && declaredBeforeUse(v, index))
      return v;
    return lookupVariable(name);
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in NameCheck.jrag at line 291
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_VariableScope_outerScope(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 116
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.EXPRESSION_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 38
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return childIndex == 0 ? reachable() : getStmt(childIndex-1).canCompleteNormally();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 146
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return i == 0 ? reachable() : getStmt(i-1).reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
