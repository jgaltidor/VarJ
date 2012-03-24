
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class ForStmt extends BranchTargetStmt implements Cloneable, VariableScope {
    public void flushCache() {
        super.flushCache();
        targetOf_ContinueStmt_visited = null;
        targetOf_ContinueStmt_values = null;
        targetOf_BreakStmt_visited = null;
        targetOf_BreakStmt_values = null;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDAafterInitialization_Variable_visited = null;
        isDUafter_Variable_visited = null;
        isDUafter_Variable_values = null;
        isDUafterInit_Variable_visited = null;
        isDUbeforeCondition_Variable_values = null;
        isDUafterUpdate_Variable_visited = null;
        localLookup_String_visited = null;
        localLookup_String_values = null;
        localVariableDeclaration_String_visited = null;
        localVariableDeclaration_String_values = null;
        continueLabel_visited = -1;
        canCompleteNormally_visited = -1;
        canCompleteNormally_computed = false;
        lookupVariable_String_visited = null;
        lookupVariable_String_values = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public ForStmt clone() throws CloneNotSupportedException {
        ForStmt node = (ForStmt)super.clone();
        node.targetOf_ContinueStmt_visited = null;
        node.targetOf_ContinueStmt_values = null;
        node.targetOf_BreakStmt_visited = null;
        node.targetOf_BreakStmt_values = null;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDAafterInitialization_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.isDUafter_Variable_values = null;
        node.isDUafterInit_Variable_visited = null;
        node.isDUbeforeCondition_Variable_values = null;
        node.isDUafterUpdate_Variable_visited = null;
        node.localLookup_String_visited = null;
        node.localLookup_String_values = null;
        node.localVariableDeclaration_String_visited = null;
        node.localVariableDeclaration_String_values = null;
        node.continueLabel_visited = -1;
        node.canCompleteNormally_visited = -1;
        node.canCompleteNormally_computed = false;
        node.lookupVariable_String_visited = null;
        node.lookupVariable_String_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ForStmt copy() {
      try {
          ForStmt node = (ForStmt)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public ForStmt fullCopy() {
        ForStmt res = (ForStmt)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in PrettyPrint.jadd at line 603


  public void toString(StringBuffer s) {
    s.append(indent());
    s.append("for(");
    if(getNumInitStmt() > 0) {
      if(getInitStmt(0) instanceof VariableDeclaration) {
        int minDimension = Integer.MAX_VALUE;
        for(int i = 0; i < getNumInitStmt(); i++) {
          VariableDeclaration v = (VariableDeclaration)getInitStmt(i);
          minDimension = Math.min(minDimension, v.type().dimension());
        }
        VariableDeclaration v = (VariableDeclaration)getInitStmt(0);
        v.getModifiers().toString(s);
        s.append(v.type().elementType().typeName());
        for(int i = minDimension; i > 0; i--)
          s.append("[]");

        for(int i = 0; i < getNumInitStmt(); i++) {
          if(i != 0)
            s.append(",");
          v = (VariableDeclaration)getInitStmt(i);
          s.append(" " + v.name());
          for(int j = v.type().dimension() - minDimension; j > 0; j--)
            s.append("[]");
          if(v.hasInit()) {
            s.append(" = ");
            v.getInit().toString(s);
          }
        }
      }
      else if(getInitStmt(0) instanceof ExprStmt) {
        ExprStmt stmt = (ExprStmt)getInitStmt(0);
        stmt.getExpr().toString(s);
        for(int i = 1; i < getNumInitStmt(); i++) {
          s.append(", ");
          stmt = (ExprStmt)getInitStmt(i);
          stmt.getExpr().toString(s);
        }
      }
      else {
        throw new Error("Unexpected initializer in for loop: " + getInitStmt(0));
      }
    }
    
    s.append("; ");
    if(hasCondition()) {
      getCondition().toString(s);
    }
    s.append("; ");

    if(getNumUpdateStmt() > 0) {
      ExprStmt stmt = (ExprStmt)getUpdateStmt(0);
      stmt.getExpr().toString(s);
      for(int i = 1; i < getNumUpdateStmt(); i++) {
        s.append(", ");
        stmt = (ExprStmt)getUpdateStmt(i);
        stmt.getExpr().toString(s);
      }
    }
    
    s.append(") ");
    getStmt().toString(s);
  }

    // Declared in TypeCheck.jrag at line 334

  public void typeCheck() {
    if(hasCondition()) {
      TypeDecl cond = getCondition().type();
      if(!cond.isBoolean()) {
        error("the type of \"" + getCondition() + "\" is " + cond.name() + " which is not boolean");
      }
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 213

    public ForStmt() {
        super();

        setChild(new List(), 0);
        setChild(new Opt(), 1);
        setChild(new List(), 2);

    }

    // Declared in java.ast at line 13


    // Declared in java.ast line 213
    public ForStmt(List<Stmt> p0, Opt<Expr> p1, List<Stmt> p2, Stmt p3) {
        setChild(p0, 0);
        setChild(p1, 1);
        setChild(p2, 2);
        setChild(p3, 3);
    }

    // Declared in java.ast at line 20


  protected int numChildren() {
    return 4;
  }

    // Declared in java.ast at line 23

    public boolean mayHaveRewrite() {
        return true;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 213
    public void setInitStmtList(List<Stmt> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumInitStmt() {
        return getInitStmtList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Stmt getInitStmt(int i) {
        return (Stmt)getInitStmtList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addInitStmt(Stmt node) {
        List<Stmt> list = (parent == null || state == null) ? getInitStmtListNoTransform() : getInitStmtList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addInitStmtNoTransform(Stmt node) {
        List<Stmt> list = getInitStmtListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setInitStmt(Stmt node, int i) {
        List<Stmt> list = getInitStmtList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Stmt> getInitStmts() {
        return getInitStmtList();
    }

    // Declared in java.ast at line 31

    public List<Stmt> getInitStmtsNoTransform() {
        return getInitStmtListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Stmt> getInitStmtList() {
        List<Stmt> list = (List<Stmt>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Stmt> getInitStmtListNoTransform() {
        return (List<Stmt>)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 213
    public void setConditionOpt(Opt<Expr> opt) {
        setChild(opt, 1);
    }

    // Declared in java.ast at line 6


    public boolean hasCondition() {
        return getConditionOpt().getNumChild() != 0;
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Expr getCondition() {
        return (Expr)getConditionOpt().getChild(0);
    }

    // Declared in java.ast at line 14


    public void setCondition(Expr node) {
        getConditionOpt().setChild(node, 0);
    }

    // Declared in java.ast at line 17

     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getConditionOpt() {
        return (Opt<Expr>)getChild(1);
    }

    // Declared in java.ast at line 21


     @SuppressWarnings({"unchecked", "cast"})  public Opt<Expr> getConditionOptNoTransform() {
        return (Opt<Expr>)getChildNoTransform(1);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 213
    public void setUpdateStmtList(List<Stmt> list) {
        setChild(list, 2);
    }

    // Declared in java.ast at line 6


    public int getNumUpdateStmt() {
        return getUpdateStmtList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Stmt getUpdateStmt(int i) {
        return (Stmt)getUpdateStmtList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addUpdateStmt(Stmt node) {
        List<Stmt> list = (parent == null || state == null) ? getUpdateStmtListNoTransform() : getUpdateStmtList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addUpdateStmtNoTransform(Stmt node) {
        List<Stmt> list = getUpdateStmtListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setUpdateStmt(Stmt node, int i) {
        List<Stmt> list = getUpdateStmtList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Stmt> getUpdateStmts() {
        return getUpdateStmtList();
    }

    // Declared in java.ast at line 31

    public List<Stmt> getUpdateStmtsNoTransform() {
        return getUpdateStmtListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Stmt> getUpdateStmtList() {
        List<Stmt> list = (List<Stmt>)getChild(2);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Stmt> getUpdateStmtListNoTransform() {
        return (List<Stmt>)getChildNoTransform(2);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 213
    public void setStmt(Stmt node) {
        setChild(node, 3);
    }

    // Declared in java.ast at line 5

    public Stmt getStmt() {
        return (Stmt)getChild(3);
    }

    // Declared in java.ast at line 9


    public Stmt getStmtNoTransform() {
        return (Stmt)getChildNoTransform(3);
    }

    protected java.util.Map targetOf_ContinueStmt_visited;
    protected java.util.Map targetOf_ContinueStmt_values;
    // Declared in BranchTarget.jrag at line 72
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
    // Declared in BranchTarget.jrag at line 80
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

    // Declared in DefiniteAssignment.jrag at line 615
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
    if(!(!hasCondition() || getCondition().isDAafterFalse(v)))
      return false;
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    protected java.util.Map isDAafterInitialization_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 628
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterInitialization(Variable v) {
        Object _parameters = v;
if(isDAafterInitialization_Variable_visited == null) isDAafterInitialization_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterInitialization_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterInitialization in class: ");
        isDAafterInitialization_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterInitialization_Variable_value = isDAafterInitialization_compute(v);
        isDAafterInitialization_Variable_visited.remove(_parameters);
        return isDAafterInitialization_Variable_value;
    }

    private boolean isDAafterInitialization_compute(Variable v) {  return getNumInitStmt() == 0 ? isDAbefore(v) : getInitStmt(getNumInitStmt()-1).isDAafter(v);  }

    // Declared in DefiniteAssignment.jrag at line 1102
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
    if(!(!hasCondition() || getCondition().isDUafterFalse(v))) {
      return false;
    }
    for(Iterator iter = targetBreaks().iterator(); iter.hasNext(); ) {
      BreakStmt stmt = (BreakStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    //if(!isDUafterUpdate(v))
    //  return false;
    return true;
  }

    protected java.util.Map isDUafterInit_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 1122
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterInit(Variable v) {
        Object _parameters = v;
if(isDUafterInit_Variable_visited == null) isDUafterInit_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterInit_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterInit in class: ");
        isDUafterInit_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterInit_Variable_value = isDUafterInit_compute(v);
        isDUafterInit_Variable_visited.remove(_parameters);
        return isDUafterInit_Variable_value;
    }

    private boolean isDUafterInit_compute(Variable v) {  return getNumInitStmt() == 0 ? isDUbefore(v) : getInitStmt(getNumInitStmt()-1).isDUafter(v);  }

    protected java.util.Map isDUbeforeCondition_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 1124
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
    if(!isDUafterInit(v))
      return false;
    else if(!isDUafterUpdate(v))
      return false;
    return true;
  }

    protected java.util.Map isDUafterUpdate_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 1135
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterUpdate(Variable v) {
        Object _parameters = v;
if(isDUafterUpdate_Variable_visited == null) isDUafterUpdate_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterUpdate_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterUpdate in class: ");
        isDUafterUpdate_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterUpdate_Variable_value = isDUafterUpdate_compute(v);
        isDUafterUpdate_Variable_visited.remove(_parameters);
        return isDUafterUpdate_Variable_value;
    }

    private boolean isDUafterUpdate_compute(Variable v) {
    if(!isDUbeforeCondition(v)) // start a circular evaluation here
      return false;
    if(getNumUpdateStmt() > 0)
      return getUpdateStmt(getNumUpdateStmt()-1).isDUafter(v);
    if(!getStmt().isDUafter(v))
      return false;
    for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
      ContinueStmt stmt = (ContinueStmt)iter.next();
      if(!stmt.isDUafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }

    protected java.util.Map localLookup_String_visited;
    protected java.util.Map localLookup_String_values;
    // Declared in LookupVariable.jrag at line 91
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookup(String name) {
        Object _parameters = name;
if(localLookup_String_visited == null) localLookup_String_visited = new java.util.HashMap(4);
if(localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
        if(localLookup_String_values.containsKey(_parameters)) {
            return (SimpleSet)localLookup_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(localLookup_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: localLookup in class: ");
        localLookup_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet localLookup_String_value = localLookup_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            localLookup_String_values.put(_parameters, localLookup_String_value);
        localLookup_String_visited.remove(_parameters);
        return localLookup_String_value;
    }

    private SimpleSet localLookup_compute(String name) {
    VariableDeclaration v = localVariableDeclaration(name);
    if(v != null) return v;
    return lookupVariable(name);
  }

    protected java.util.Map localVariableDeclaration_String_visited;
    protected java.util.Map localVariableDeclaration_String_values;
    // Declared in LookupVariable.jrag at line 121
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
    for(int i = 0; i < getNumInitStmt(); i++)
      if(getInitStmt(i).declaresVariable(name))
        return (VariableDeclaration)getInitStmt(i);
    return null;
  }

    protected int continueLabel_visited = -1;
    // Declared in NameCheck.jrag at line 397
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

    // Declared in UnreachableStatements.jrag at line 102
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

    private boolean canCompleteNormally_compute() {  return reachable() && hasCondition() && (!getCondition().isConstant() || !getCondition().isTrue()) || reachableBreak();  }

    protected java.util.Map lookupVariable_String_visited;
    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 18
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

    // Declared in DefiniteAssignment.jrag at line 639
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getUpdateStmtListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    if(!getStmt().isDAafter(v))
      return false;
    for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
      ContinueStmt stmt = (ContinueStmt)iter.next();
      if(!stmt.isDAafterReachedFinallyBlocks(v))
        return false;
    }
    return true;
  }
}
        if(caller == getStmtNoTransform()){
    if(hasCondition() && getCondition().isDAafterTrue(v))
      return true;
    if(!hasCondition() && isDAafterInitialization(v))
      return true;
    return false;
  }
        if(caller == getConditionOptNoTransform()) {
            return isDAafterInitialization(v);
        }
        if(caller == getInitStmtListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return i == 0 ? isDAbefore(v) : getInitStmt(i-1).isDAafter(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 1151
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getUpdateStmtListNoTransform()) { 
   int i = caller.getIndexOfChild(child);
{
    if(!isDUbeforeCondition(v)) // start a circular evaluation here
      return false;
    if(i == 0) {
      if(!getStmt().isDUafter(v))
        return false;
      for(Iterator iter = targetContinues().iterator(); iter.hasNext(); ) {
        ContinueStmt stmt = (ContinueStmt)iter.next();
        if(!stmt.isDUafterReachedFinallyBlocks(v))
          return false;
      }
      return true;
    }
    else
      return getUpdateStmt(i-1).isDUafter(v);
  }
}
        if(caller == getStmtNoTransform()) {
            return isDUbeforeCondition(v) && (hasCondition() ?
    getCondition().isDUafterTrue(v) : isDUafterInit(v));
        }
        if(caller == getConditionOptNoTransform()) {
            return isDUbeforeCondition(v);
        }
        if(caller == getInitStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return childIndex == 0 ? isDUbefore(v) : getInitStmt(childIndex-1).isDUafter(v);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in LookupVariable.jrag at line 90
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getStmtNoTransform()) {
            return localLookup(name);
        }
        if(caller == getUpdateStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return localLookup(name);
        }
        if(caller == getConditionOptNoTransform()) {
            return localLookup(name);
        }
        if(caller == getInitStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return localLookup(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in NameCheck.jrag at line 294
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return this;
        }
        if(caller == getInitStmtListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_VariableScope_outerScope(this, caller);
    }

    // Declared in NameCheck.jrag at line 365
    public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_insideLoop(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 103
    public boolean Define_boolean_reachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable() && (!hasCondition() || (!getCondition().isConstant() || !getCondition().isFalse()));
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reachable(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 149
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(caller == getStmtNoTransform()) {
            return reachable();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

public ASTNode rewriteTo() {
    // Declared in DefiniteAssignment.jrag at line 1169
    if(!hasCondition()) {
        state().duringDefiniteAssignment++;
        ASTNode result = rewriteRule0();
        state().duringDefiniteAssignment--;
        return result;
    }

    return super.rewriteTo();
}

    // Declared in DefiniteAssignment.jrag at line 1169
    private ForStmt rewriteRule0() {
         debugRewrite("Rewriting " + getClass().getName() + " using rule in DefiniteAssignment.jrag at line 1169");
{
      setCondition(new BooleanLiteral("true"));
      return this;
    }    }
}
