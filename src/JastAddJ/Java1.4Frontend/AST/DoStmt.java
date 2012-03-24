
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class DoStmt extends BranchTargetStmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        targetOf_ContinueStmt_visited = null;
        targetOf_ContinueStmt_values = null;
        targetOf_BreakStmt_visited = null;
        targetOf_BreakStmt_values = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        isDUbeforeCondition_Variable_values = null;
        continueLabel_visited = -1;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public DoStmt clone() throws CloneNotSupportedException {
        DoStmt node = (DoStmt)super.clone();
        node.targetOf_ContinueStmt_visited = null;
        node.targetOf_ContinueStmt_values = null;
        node.targetOf_BreakStmt_visited = null;
        node.targetOf_BreakStmt_values = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.isDUbeforeCondition_Variable_values = null;
        node.continueLabel_visited = -1;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DoStmt copy() {
      try {
          DoStmt node = (DoStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public DoStmt fullCopy() {
        DoStmt res = (DoStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 594


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("do ");
    getStmt().toString(s);
    s.append("while(");
    getCondition().toString(s);
    s.append(");");
  }

    // Declared in TypeCheck.jrag at line 328

  public void typeCheck() {
    TypeDecl cond = getCondition().type();
    if(!cond.isBoolean()) {
      error("the type of \"" + getCondition() + "\" is " + cond.name() + " which is not boolean");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 212

    public DoStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 212
    public DoStmt(Stmt p0, Expr p1) {
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
    // Declared in java.ast line 212
    public void setStmt(Stmt node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Stmt getStmt() {
        return (Stmt)getChild(0);
    }

    // Declared in java.ast at line 9


    public Stmt getStmtNoTransform() {
        return (Stmt)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 212
    public void setCondition(Expr node) {
        setChild(node, 1);
    }

    // Declared in java.ast at line 5

    public Expr getCondition() {
        return (Expr)getChild(1);
    }

    // Declared in java.ast at line 9


    public Expr getConditionNoTransform() {
        return (Expr)getChildNoTransform(1);
    }

    protected java.util.Map targetOf_ContinueStmt_visited;
    protected java.util.Map targetOf_ContinueStmt_values;
    // Declared in BranchTarget.jrag at line 71
 @SuppressWarnings({"unchecked", "cast"})     public boolean targetOf(ContinueStmt stmt) {
        Object _parameters = stmt;
if(targetOf_ContinueStmt_visited == null) targetOf_ContinueStmt_visited = new java.util.HashMap(4);
if(targetOf_ContinueStmt_values == null) targetOf_ContinueStmt_values = new java.util.HashMap(4);
        if(targetOf_ContinueStmt_values.containsKey(_parameters)) {
            return ((Boolean)targetOf_ContinueStmt_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(targetOf_ContinueStmt_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: targetOf in class: ");
        targetOf_ContinueStmt_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean targetOf_ContinueStmt_value = targetOf_compute(stmt);
        if(isFinal && num == state().boundariesCrossed)
            targetOf_ContinueStmt_values.put(_parameters, Boolean.valueOf(targetOf_ContinueStmt_value));
        targetOf_ContinueStmt_visited.remove(_parameters);
        return targetOf_ContinueStmt_value;
    }

    private boolean targetOf_compute(ContinueStmt stmt) {  return !stmt.hasLabel();  }

    protected java.util.Map targetOf_BreakStmt_visited;
    protected java.util.Map targetOf_BreakStmt_values;
    // Declared in BranchTarget.jrag at line 79
 @SuppressWarnings({"unchecked", "cast"})     public boolean targetOf(BreakStmt stmt) {
        Object _parameters = stmt;
if(targetOf_BreakStmt_visited == null) targetOf_BreakStmt_visited = new java.util.HashMap(4);
if(targetOf_BreakStmt_values == null) targetOf_BreakStmt_values = new java.util.HashMap(4);
        if(targetOf_BreakStmt_values.containsKey(_parameters)) {
            return ((Boolean)targetOf_BreakStmt_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(targetOf_BreakStmt_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: targetOf in class: ");
        targetOf_BreakStmt_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean targetOf_BreakStmt_value = targetOf_compute(stmt);
        if(isFinal && num == state().boundariesCrossed)
            targetOf_BreakStmt_values.put(_parameters, Boolean.valueOf(targetOf_BreakStmt_value));
        targetOf_BreakStmt_visited.remove(_parameters);
        return targetOf_BreakStmt_value;
    }

    private boolean targetOf_compute(BreakStmt stmt) {  return !stmt.hasLabel();  }

    // Declared in DefiniteAssignment.jrag at line 592
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

    private boolean isDAafter_compute(Variable v) {
    if(!getCondition().isDAafterFalse(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 1074
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
    if(!isDUbeforeCondition(v)) // start a circular evaluation here
      return false;
    if(!getCondition().isDUafterFalse(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    protected java.util.Map isDUbeforeCondition_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 1088
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbeforeCondition(Variable v) {
        Object _parameters = v;
if(isDUbeforeCondition_Variable_values == null) isDUbeforeCondition_Variable_values = new java.util.HashMap(4);
        ASTNode$State.CircularValue _value;
        if(isDUbeforeCondition_Variable_values.containsKey(_parameters)) {
            Object _o = isDUbeforeCondition_Variable_values.get(_parameters);
            if(!(_o instanceof ASTNode$State.CircularValue)) {
                return ((Boolean)_o).booleanValue();
            }
            else
                _value = (ASTNode$State.CircularValue)_o;
        }
        else {
            _value = new ASTNode$State.CircularValue();
            isDUbeforeCondition_Variable_values.put(_parameters, _value);
            _value.value = Boolean.valueOf(true);
        }
        ASTNode$State state = state();
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            boolean new_isDUbeforeCondition_Variable_value;
            do {
                _value.visited = new Integer(state.CIRCLE_INDEX);
                state.CHANGE = false;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                new_isDUbeforeCondition_Variable_value = isDUbeforeCondition_compute(v);
                if (new_isDUbeforeCondition_Variable_value!=((Boolean)_value.value).booleanValue()) {
                    state.CHANGE = true;
                    _value.value = Boolean.valueOf(new_isDUbeforeCondition_Variable_value);
                }
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
                isDUbeforeCondition_Variable_values.put(_parameters, new_isDUbeforeCondition_Variable_value);
            state.LAST_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isDUbeforeCondition_compute(v);
            state.LAST_CYCLE = false;
            }
            else {
                isDUbeforeCondition_Variable_values.remove(_parameters);
            state.RESET_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isDUbeforeCondition_compute(v);
            state.RESET_CYCLE = false;
            }
            state.IN_CIRCLE = false; 
            return new_isDUbeforeCondition_Variable_value;
        }
        if(!new Integer(state.CIRCLE_INDEX).equals(_value.visited)) {
            _value.visited = new Integer(state.CIRCLE_INDEX);
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            boolean new_isDUbeforeCondition_Variable_value = isDUbeforeCondition_compute(v);
            if (state.LAST_CYCLE) {
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                isDUbeforeCondition_Variable_values.put(_parameters, new_isDUbeforeCondition_Variable_value);
            }
            if (state.RESET_CYCLE) {
                isDUbeforeCondition_Variable_values.remove(_parameters);
            }
            else if (new_isDUbeforeCondition_Variable_value!=((Boolean)_value.value).booleanValue()) {
                state.CHANGE = true;
                _value.value = new_isDUbeforeCondition_Variable_value;
            }
            return new_isDUbeforeCondition_Variable_value;
        }
        return ((Boolean)_value.value).booleanValue();
    }

    private boolean isDUbeforeCondition_compute(Variable v) {
    if(!getStmt().isDUafter(v))
      return false;
    else {
      for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
        ContinueStmt stmt = (ContinueStmt)iter.next();
        if(!stmt.isDUafterReachedFinallyBlocks(v))
          return false;
      }
    }
    return true;
  }

    protected int continueLabel_visited = -1;
    // Declared in NameCheck.jrag at line 399
 @SuppressWarnings({"unchecked", "cast"})     public boolean continueLabel() {
        ASTNode$State state = state();
        if(continueLabel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: continueLabel in class: ");
        continueLabel_visited = state().boundariesCrossed;
        boolean continueLabel_value = continueLabel_compute();
        continueLabel_visited = -1;
        return continueLabel_value;
    }

    private boolean continueLabel_compute() {  return true;  }

    // Declared in UnreachableStatements.jrag at line 88
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

    private boolean canCompleteNormally_compute() {  return getStmt().canCompleteNormally() && (!getCondition().isConstant() || !getCondition().isTrue())
    || reachableContinue() && (!getCondition().isConstant() || !getCondition().isTrue()) || reachableBreak();  }

    // Declared in DefiniteAssignment.jrag at line 603
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getConditionNoTransform()){
    if(!getStmt().isDAafter(v))
      return false;
    for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
      ContinueStmt stmt = (ContinueStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }
        if(caller == getStmtNoTransform()) {
            return isDAbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1087
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getConditionNoTransform()) {
            return isDUbeforeCondition(v);
        }
        if(caller == getStmtNoTransform()) {
            return isDUbefore(v) && getCondition().isDUafterTrue(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in NameCheck.jrag at line 367
    public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_insideLoop(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 100
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 150
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
