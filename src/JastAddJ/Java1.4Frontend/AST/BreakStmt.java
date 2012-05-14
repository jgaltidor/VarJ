
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class BreakStmt extends Stmt implements Cloneable {
    public void flushCache() {
        super.flushCache();
        hasLabel_visited = -1;
        targetStmt_visited = -1;
        targetStmt_computed = false;
        targetStmt_value = null;
        finallyList_visited = -1;
        finallyList_computed = false;
        finallyList_value = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafterReachedFinallyBlocks_Variable_visited = null;
        isDUafterReachedFinallyBlocks_Variable_values = null;
        isDAafterReachedFinallyBlocks_Variable_visited = null;
        isDAafterReachedFinallyBlocks_Variable_values = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
        lookupLabel_String_visited = null;
        lookupLabel_String_values = null;
        insideLoop_visited = -1;
        insideSwitch_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public BreakStmt clone() throws CloneNotSupportedException {
        BreakStmt node = (BreakStmt)super.clone();
        node.hasLabel_visited = -1;
        node.targetStmt_visited = -1;
        node.targetStmt_computed = false;
        node.targetStmt_value = null;
        node.finallyList_visited = -1;
        node.finallyList_computed = false;
        node.finallyList_value = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafterReachedFinallyBlocks_Variable_visited = null;
        node.isDUafterReachedFinallyBlocks_Variable_values = null;
        node.isDAafterReachedFinallyBlocks_Variable_visited = null;
        node.isDAafterReachedFinallyBlocks_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.lookupLabel_String_visited = null;
        node.lookupLabel_String_values = null;
        node.insideLoop_visited = -1;
        node.insideSwitch_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BreakStmt copy() {
      try {
          BreakStmt node = (BreakStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public BreakStmt fullCopy() {
        BreakStmt res = (BreakStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in BranchTarget.jrag at line 52

  public void collectBranches(Collection c) {
    c.add(this);
  }

    // Declared in NameCheck.jrag at line 374


  public void nameCheck() {
    if(!hasLabel() && !insideLoop() && !insideSwitch())
      error("break outside switch or loop");
    else if(hasLabel()) {
      LabeledStmt label = lookupLabel(getLabel());
      if(label == null)
        error("labeled break must have visible matching label");
    }
  }

    // Declared in PrettyPrint.jadd at line 666


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("break ");
    if(hasLabel())
      s.append(getLabel());
    s.append(";");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 215

    public BreakStmt() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 215
    public BreakStmt(String p0) {
        setLabel(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 215
    public BreakStmt(beaver.Symbol p0) {
        setLabel(p0);
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
    // Declared in java.ast line 215
    protected String tokenString_Label;

    // Declared in java.ast at line 3

    public void setLabel(String value) {
        tokenString_Label = value;
    }

    // Declared in java.ast at line 6

    public int Labelstart;

    // Declared in java.ast at line 7

    public int Labelend;

    // Declared in java.ast at line 8

    public void setLabel(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setLabel is only valid for String lexemes");
        tokenString_Label = (String)symbol.value;
        Labelstart = symbol.getStart();
        Labelend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getLabel() {
        return tokenString_Label != null ? tokenString_Label : "";
    }

    protected int hasLabel_visited = -1;
    // Declared in BranchTarget.jrag at line 66
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasLabel() {
        ASTNode$State state = state();
        if(hasLabel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasLabel in class: ");
        hasLabel_visited = state().boundariesCrossed;
        boolean hasLabel_value = hasLabel_compute();
        hasLabel_visited = -1;
        return hasLabel_value;
    }

    private boolean hasLabel_compute() {  return !getLabel().equals("");  }

    protected int targetStmt_visited = -1;
    protected boolean targetStmt_computed = false;
    protected Stmt targetStmt_value;
    // Declared in BranchTarget.jrag at line 149
 @SuppressWarnings({"unchecked", "cast"})     public Stmt targetStmt() {
        if(targetStmt_computed) {
            return targetStmt_value;
        }
        ASTNode$State state = state();
        if(targetStmt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: targetStmt in class: ");
        targetStmt_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        targetStmt_value = targetStmt_compute();
        if(isFinal && num == state().boundariesCrossed)
            targetStmt_computed = true;
        targetStmt_visited = -1;
        return targetStmt_value;
    }

    private Stmt targetStmt_compute() {  return branchTarget(this);  }

    protected int finallyList_visited = -1;
    protected boolean finallyList_computed = false;
    protected ArrayList finallyList_value;
    // Declared in BranchTarget.jrag at line 176
 @SuppressWarnings({"unchecked", "cast"})     public ArrayList finallyList() {
        if(finallyList_computed) {
            return finallyList_value;
        }
        ASTNode$State state = state();
        if(finallyList_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: finallyList in class: ");
        finallyList_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        finallyList_value = finallyList_compute();
        if(isFinal && num == state().boundariesCrossed)
            finallyList_computed = true;
        finallyList_visited = -1;
        return finallyList_value;
    }

    private ArrayList finallyList_compute() {
    ArrayList list = new ArrayList();
    collectFinally(this, list);
    return list;
  }

    // Declared in DefiniteAssignment.jrag at line 650
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

    protected java.util.Map isDUafterReachedFinallyBlocks_Variable_visited;
    protected java.util.Map isDUafterReachedFinallyBlocks_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 927
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterReachedFinallyBlocks(Variable v) {
        Object _parameters = v;
if(isDUafterReachedFinallyBlocks_Variable_visited == null) isDUafterReachedFinallyBlocks_Variable_visited = new java.util.HashMap(4);
if(isDUafterReachedFinallyBlocks_Variable_values == null) isDUafterReachedFinallyBlocks_Variable_values = new java.util.HashMap(4);
        if(isDUafterReachedFinallyBlocks_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUafterReachedFinallyBlocks_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterReachedFinallyBlocks_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterReachedFinallyBlocks in class: ");
        isDUafterReachedFinallyBlocks_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDUafterReachedFinallyBlocks_Variable_value = isDUafterReachedFinallyBlocks_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDUafterReachedFinallyBlocks_Variable_values.put(_parameters, Boolean.valueOf(isDUafterReachedFinallyBlocks_Variable_value));
        isDUafterReachedFinallyBlocks_Variable_visited.remove(_parameters);
        return isDUafterReachedFinallyBlocks_Variable_value;
    }

    private boolean isDUafterReachedFinallyBlocks_compute(Variable v) {
    if(!isDUbefore(v) && finallyList().isEmpty())
      return false;
    for(Iterator iter = finallyList().iterator(); iter.hasNext(); ) {
      FinallyHost f = (FinallyHost)iter.next();
      if(!f.isDUafterFinally(v))
        return false;
    }
    return true;
  }

    protected java.util.Map isDAafterReachedFinallyBlocks_Variable_visited;
    protected java.util.Map isDAafterReachedFinallyBlocks_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 959
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterReachedFinallyBlocks(Variable v) {
        Object _parameters = v;
if(isDAafterReachedFinallyBlocks_Variable_visited == null) isDAafterReachedFinallyBlocks_Variable_visited = new java.util.HashMap(4);
if(isDAafterReachedFinallyBlocks_Variable_values == null) isDAafterReachedFinallyBlocks_Variable_values = new java.util.HashMap(4);
        if(isDAafterReachedFinallyBlocks_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAafterReachedFinallyBlocks_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterReachedFinallyBlocks_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterReachedFinallyBlocks in class: ");
        isDAafterReachedFinallyBlocks_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean isDAafterReachedFinallyBlocks_Variable_value = isDAafterReachedFinallyBlocks_compute(v);
        if(isFinal && num == state().boundariesCrossed)
            isDAafterReachedFinallyBlocks_Variable_values.put(_parameters, Boolean.valueOf(isDAafterReachedFinallyBlocks_Variable_value));
        isDAafterReachedFinallyBlocks_Variable_visited.remove(_parameters);
        return isDAafterReachedFinallyBlocks_Variable_value;
    }

    private boolean isDAafterReachedFinallyBlocks_compute(Variable v) {
    if(isDAbefore(v))
      return true;
    if(finallyList().isEmpty())
      return false;
    for(Iterator iter = finallyList().iterator(); iter.hasNext(); ) {
      FinallyHost f = (FinallyHost)iter.next();
      if(!f.isDAafterFinally(v))
        return false;
    }
    return true;
  }

    // Declared in DefiniteAssignment.jrag at line 1176
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

    // Declared in UnreachableStatements.jrag at line 105
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

    private boolean canCompleteNormally_compute() {  return false;  }

    protected java.util.Map lookupLabel_String_visited;
    protected java.util.Map lookupLabel_String_values;
    // Declared in BranchTarget.jrag at line 169
 @SuppressWarnings({"unchecked", "cast"})     public LabeledStmt lookupLabel(String name) {
        Object _parameters = name;
if(lookupLabel_String_visited == null) lookupLabel_String_visited = new java.util.HashMap(4);
if(lookupLabel_String_values == null) lookupLabel_String_values = new java.util.HashMap(4);
        if(lookupLabel_String_values.containsKey(_parameters)) {
            return (LabeledStmt)lookupLabel_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupLabel_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupLabel in class: ");
        lookupLabel_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        LabeledStmt lookupLabel_String_value = getParent().Define_LabeledStmt_lookupLabel(this, null, name);
        if(isFinal && num == state().boundariesCrossed)
            lookupLabel_String_values.put(_parameters, lookupLabel_String_value);
        lookupLabel_String_visited.remove(_parameters);
        return lookupLabel_String_value;
    }

    protected int insideLoop_visited = -1;
    // Declared in NameCheck.jrag at line 360
 @SuppressWarnings({"unchecked", "cast"})     public boolean insideLoop() {
        ASTNode$State state = state();
        if(insideLoop_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: insideLoop in class: ");
        insideLoop_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean insideLoop_value = getParent().Define_boolean_insideLoop(this, null);
        insideLoop_visited = -1;
        return insideLoop_value;
    }

    protected int insideSwitch_visited = -1;
    // Declared in NameCheck.jrag at line 369
 @SuppressWarnings({"unchecked", "cast"})     public boolean insideSwitch() {
        ASTNode$State state = state();
        if(insideSwitch_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: insideSwitch in class: ");
        insideSwitch_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean insideSwitch_value = getParent().Define_boolean_insideSwitch(this, null);
        insideSwitch_visited = -1;
        return insideSwitch_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
