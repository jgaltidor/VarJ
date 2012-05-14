
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class SwitchStmt extends BranchTargetStmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        targetOf_ContinueStmt_visited = null;
        targetOf_ContinueStmt_values = null;
        targetOf_BreakStmt_visited = null;
        targetOf_BreakStmt_values = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        assignedAfterLastStmt_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        unassignedAfterLastStmt_Variable_visited = null;
        switchLabelEndsBlock_visited = -1;
        lastStmtCanCompleteNormally_visited = -1;
        noStmts_visited = -1;
        noStmtsAfterLastLabel_visited = -1;
        noDefaultLabel_visited = -1;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
        typeInt_visited = -1;
        typeInt_computed = false;
        typeInt_value = null;
        typeLong_visited = -1;
        typeLong_computed = false;
        typeLong_value = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public SwitchStmt clone() throws CloneNotSupportedException {
        SwitchStmt node = (SwitchStmt)super.clone();
        node.targetOf_ContinueStmt_visited = null;
        node.targetOf_ContinueStmt_values = null;
        node.targetOf_BreakStmt_visited = null;
        node.targetOf_BreakStmt_values = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.assignedAfterLastStmt_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.unassignedAfterLastStmt_Variable_visited = null;
        node.switchLabelEndsBlock_visited = -1;
        node.lastStmtCanCompleteNormally_visited = -1;
        node.noStmts_visited = -1;
        node.noStmtsAfterLastLabel_visited = -1;
        node.noDefaultLabel_visited = -1;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.typeInt_visited = -1;
        node.typeInt_computed = false;
        node.typeInt_value = null;
        node.typeLong_visited = -1;
        node.typeLong_computed = false;
        node.typeLong_value = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SwitchStmt copy() {
      try {
          SwitchStmt node = (SwitchStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public SwitchStmt fullCopy() {
        SwitchStmt res = (SwitchStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 553


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("switch (");
    getExpr().toString(s);
    s.append(")");
    getBlock().toString(s);
  }

    // Declared in TypeCheck.jrag at line 343


  public void typeCheck() {
    TypeDecl type = getExpr().type();
    if(!type.isIntegralType() || type.isLong())
      error("Switch expression must be of char, byte, short, or int");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 205

    public SwitchStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 205
    public SwitchStmt(Expr p0, Block p1) {
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
    // Declared in java.ast line 205
    public void setExpr(Expr node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Expr getExpr() {
        return (Expr)getChild(0);
    }

    // Declared in java.ast at line 9


    public Expr getExprNoTransform() {
        return (Expr)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 205
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

    protected java.util.Map targetOf_ContinueStmt_visited;
    protected java.util.Map targetOf_ContinueStmt_values;
    // Declared in BranchTarget.jrag at line 73
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

    private boolean targetOf_compute(ContinueStmt stmt) {  return false;  }

    protected java.util.Map targetOf_BreakStmt_visited;
    protected java.util.Map targetOf_BreakStmt_values;
    // Declared in BranchTarget.jrag at line 77
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

    // Declared in DefiniteAssignment.jrag at line 534
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
    if(!(!noDefaultLabel() || getExpr().isDAafter(v))) {
      return false;
    }
    if(!(!switchLabelEndsBlock() || getExpr().isDAafter(v))) {
      return false;
    }
    if(!assignedAfterLastStmt(v)) {
      return false;
    }
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    protected java.util.Map assignedAfterLastStmt_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 552
 @SuppressWarnings({"unchecked", "cast"})     public boolean assignedAfterLastStmt(Variable v) {
        Object _parameters = v;
if(assignedAfterLastStmt_Variable_visited == null) assignedAfterLastStmt_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(assignedAfterLastStmt_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: assignedAfterLastStmt in class: ");
        assignedAfterLastStmt_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean assignedAfterLastStmt_Variable_value = assignedAfterLastStmt_compute(v);
        assignedAfterLastStmt_Variable_visited.remove(_parameters);
        return assignedAfterLastStmt_Variable_value;
    }

    private boolean assignedAfterLastStmt_compute(Variable v) {  return getBlock().isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 1006
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
    if(!(!noDefaultLabel() || getExpr().isDUafter(v)))
      return false;
    if(!(!switchLabelEndsBlock() || getExpr().isDUafter(v)))
      return false;
    if(!unassignedAfterLastStmt(v))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    protected java.util.Map unassignedAfterLastStmt_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 1021
 @SuppressWarnings({"unchecked", "cast"})     public boolean unassignedAfterLastStmt(Variable v) {
        Object _parameters = v;
if(unassignedAfterLastStmt_Variable_visited == null) unassignedAfterLastStmt_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(unassignedAfterLastStmt_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: unassignedAfterLastStmt in class: ");
        unassignedAfterLastStmt_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean unassignedAfterLastStmt_Variable_value = unassignedAfterLastStmt_compute(v);
        unassignedAfterLastStmt_Variable_visited.remove(_parameters);
        return unassignedAfterLastStmt_Variable_value;
    }

    private boolean unassignedAfterLastStmt_compute(Variable v) {  return getBlock().isDUafter(v);  }

    protected int switchLabelEndsBlock_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 1024
 @SuppressWarnings({"unchecked", "cast"})     public boolean switchLabelEndsBlock() {
        ASTNode$State state = state();
        if(switchLabelEndsBlock_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: switchLabelEndsBlock in class: ");
        switchLabelEndsBlock_visited = state().boundariesCrossed;
        boolean switchLabelEndsBlock_value = switchLabelEndsBlock_compute();
        switchLabelEndsBlock_visited = -1;
        return switchLabelEndsBlock_value;
    }

    private boolean switchLabelEndsBlock_compute() {  return getBlock().getNumStmt() > 0 && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof ConstCase;  }

    protected int lastStmtCanCompleteNormally_visited = -1;
    // Declared in UnreachableStatements.jrag at line 60
 @SuppressWarnings({"unchecked", "cast"})     public boolean lastStmtCanCompleteNormally() {
        ASTNode$State state = state();
        if(lastStmtCanCompleteNormally_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: lastStmtCanCompleteNormally in class: ");
        lastStmtCanCompleteNormally_visited = state().boundariesCrossed;
        boolean lastStmtCanCompleteNormally_value = lastStmtCanCompleteNormally_compute();
        lastStmtCanCompleteNormally_visited = -1;
        return lastStmtCanCompleteNormally_value;
    }

    private boolean lastStmtCanCompleteNormally_compute() {  return getBlock().canCompleteNormally();  }

    protected int noStmts_visited = -1;
    // Declared in UnreachableStatements.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public boolean noStmts() {
        ASTNode$State state = state();
        if(noStmts_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: noStmts in class: ");
        noStmts_visited = state().boundariesCrossed;
        boolean noStmts_value = noStmts_compute();
        noStmts_visited = -1;
        return noStmts_value;
    }

    private boolean noStmts_compute() {
    for(int i = 0; i < getBlock().getNumStmt(); i++)
      if(!(getBlock().getStmt(i) instanceof Case))
        return false;
    return true;
  }

    protected int noStmtsAfterLastLabel_visited = -1;
    // Declared in UnreachableStatements.jrag at line 69
 @SuppressWarnings({"unchecked", "cast"})     public boolean noStmtsAfterLastLabel() {
        ASTNode$State state = state();
        if(noStmtsAfterLastLabel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: noStmtsAfterLastLabel in class: ");
        noStmtsAfterLastLabel_visited = state().boundariesCrossed;
        boolean noStmtsAfterLastLabel_value = noStmtsAfterLastLabel_compute();
        noStmtsAfterLastLabel_visited = -1;
        return noStmtsAfterLastLabel_value;
    }

    private boolean noStmtsAfterLastLabel_compute() {  return getBlock().getNumStmt() > 0 && getBlock().getStmt(getBlock().getNumStmt()-1) instanceof Case;  }

    protected int noDefaultLabel_visited = -1;
    // Declared in UnreachableStatements.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public boolean noDefaultLabel() {
        ASTNode$State state = state();
        if(noDefaultLabel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: noDefaultLabel in class: ");
        noDefaultLabel_visited = state().boundariesCrossed;
        boolean noDefaultLabel_value = noDefaultLabel_compute();
        noDefaultLabel_visited = -1;
        return noDefaultLabel_value;
    }

    private boolean noDefaultLabel_compute() {
    for(int i = 0; i < getBlock().getNumStmt(); i++)
      if(getBlock().getStmt(i) instanceof DefaultCase)
        return false;
    return true;
  }

    // Declared in UnreachableStatements.jrag at line 79
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

    private boolean canCompleteNormally_compute() {  return lastStmtCanCompleteNormally() || noStmts() || noStmtsAfterLastLabel() || noDefaultLabel() || reachableBreak();  }

    protected int typeInt_visited = -1;
    protected boolean typeInt_computed = false;
    protected TypeDecl typeInt_value;
    // Declared in LookupType.jrag at line 61
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeInt() {
        if(typeInt_computed) {
            return typeInt_value;
        }
        ASTNode$State state = state();
        if(typeInt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeInt in class: ");
        typeInt_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeInt_value = getParent().Define_TypeDecl_typeInt(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeInt_computed = true;
        typeInt_visited = -1;
        return typeInt_value;
    }

    protected int typeLong_visited = -1;
    protected boolean typeLong_computed = false;
    protected TypeDecl typeLong_value;
    // Declared in LookupType.jrag at line 63
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeLong() {
        if(typeLong_computed) {
            return typeLong_value;
        }
        ASTNode$State state = state();
        if(typeLong_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeLong in class: ");
        typeLong_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeLong_value = getParent().Define_TypeDecl_typeLong(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeLong_computed = true;
        typeLong_visited = -1;
        return typeLong_value;
    }

    // Declared in DefiniteAssignment.jrag at line 571
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBlockNoTransform()) {
            return getExpr().isDAafter(v);
        }
        if(caller == getExprNoTransform()){
    if(((ASTNode)v).isDescendantTo(this))
      return false;
    boolean result = isDAbefore(v);
    return result;
  }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1029
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBlockNoTransform()) {
            return getExpr().isDUafter(v);
        }
        if(caller == getExprNoTransform()) {
            return isDUbefore(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in NameCheck.jrag at line 372
    public boolean Define_boolean_insideSwitch(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_insideSwitch(this, caller);
    }

    // Declared in NameCheck.jrag at line 413
    public Case Define_Case_bind(ASTNode caller, ASTNode child, Case c) {
        if(caller == getBlockNoTransform()){
    Block b = getBlock();
    for(int i = 0; i < b.getNumStmt(); i++)
      if(b.getStmt(i) instanceof Case && ((Case)b.getStmt(i)).constValue(c))
        return (Case)b.getStmt(i);
    return null;
  }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Case_bind(this, caller, c);
    }

    // Declared in TypeCheck.jrag at line 359
    public TypeDecl Define_TypeDecl_switchType(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return getExpr().type();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_switchType(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 82
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 156
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getBlockNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
