
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class VarAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isConstant_visited = -1;
        isConstant_computed = false;
        isConstant_initialized = false;
        varDecl_visited = -1;
        isDAafter_Variable_visited = null;
        isDAafter_Variable_values = null;
        isDUafter_Variable_visited = null;
        unassignedEverywhere_Variable_TryStmt_visited = null;
        decls_visited = -1;
        decls_computed = false;
        decls_value = null;
        decl_visited = -1;
        decl_computed = false;
        decl_value = null;
        inSameInitializer_visited = -1;
        simpleAssignment_visited = -1;
        inDeclaringClass_visited = -1;
        dumpString_visited = -1;
        name_visited = -1;
        isFieldAccess_visited = -1;
        isFieldAccess_computed = false;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        isVariable_visited = -1;
        inExplicitConstructorInvocation_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public VarAccess clone() throws CloneNotSupportedException {
        VarAccess node = (VarAccess)super.clone();
        node.constant_visited = -1;
        node.isConstant_visited = -1;
        node.isConstant_computed = false;
        node.isConstant_initialized = false;
        node.varDecl_visited = -1;
        node.isDAafter_Variable_visited = null;
        node.isDAafter_Variable_values = null;
        node.isDUafter_Variable_visited = null;
        node.unassignedEverywhere_Variable_TryStmt_visited = null;
        node.decls_visited = -1;
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_visited = -1;
        node.decl_computed = false;
        node.decl_value = null;
        node.inSameInitializer_visited = -1;
        node.simpleAssignment_visited = -1;
        node.inDeclaringClass_visited = -1;
        node.dumpString_visited = -1;
        node.name_visited = -1;
        node.isFieldAccess_visited = -1;
        node.isFieldAccess_computed = false;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.isVariable_visited = -1;
        node.inExplicitConstructorInvocation_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public VarAccess copy() {
      try {
          VarAccess node = (VarAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public VarAccess fullCopy() {
        VarAccess res = (VarAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in DefiniteAssignment.jrag at line 94

  
  public void definiteAssignment() {
    if(isSource()) {
      if(decl() instanceof VariableDeclaration) {
        VariableDeclaration v = (VariableDeclaration)decl();
        //System.err.println("Is " + v + " final? " + v.isFinal() + ", DAbefore: " + isDAbefore(v));
        if(v.isValue()) {
        }
        else if(v.isBlankFinal()) {
          //if(!isDAbefore(v) && !v.hasInit() && !v.getInit().isConstant())
          if(!isDAbefore(v))
            error("Final variable " + v.name() + " is not assigned before used");
        }
        else {
          //if(!v.hasInit() && !isDAbefore(v)) {
          if(!isDAbefore(v))
          error("Local variable " + v.name() + " in not assigned before used");
        }
      }
      
      else if(decl() instanceof FieldDeclaration && !isQualified()) {
        FieldDeclaration f = (FieldDeclaration)decl();
        //if(f.isFinal() && f.isInstanceVariable() && !isDAbefore(f)) {
        //if(f.isFinal() && !isDAbefore(f) && (!f.hasInit() || !f.getInit().isConstant())) {
        //if(f.isFinal() && (!f.hasInit() || !f.getInit().isConstant()) && !isDAbefore(f)) {
        if(f.isFinal() && !f.hasInit() && !isDAbefore(f)) {
          error("Final field " + f + " is not assigned before used");
        }
      }
      
    }
    if(isDest()) {
      Variable v = decl();
      // Blank final field
      if(v.isFinal() && v.isBlank() && !hostType().instanceOf(v.hostType()))
        error("The final variable is not a blank final in this context, so it may not be assigned.");
      else if(v.isFinal() && isQualified() && (!qualifier().isThisAccess() || ((Access)qualifier()).isQualified()))
        error("the blank final field " + v.name() + " may only be assigned by simple name");
      
      // local variable or parameter
      else if(v instanceof VariableDeclaration) {
        VariableDeclaration var = (VariableDeclaration)v;
        //System.out.println("### is variable");
        if(!var.isValue() && var.getParent().getParent().getParent() instanceof SwitchStmt && var.isFinal()) {
          if(!isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        else if(var.isValue()) {
          if(var.hasInit() || !isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        else if(var.isBlankFinal()) {
          if(var.hasInit() || !isDUbefore(var))
            error("Final variable " + var.name() + " may only be assigned once");
        }
        if(var.isFinal() && (var.hasInit() || !isDUbefore(var))) {
        //if(var.isFinal() && ((var.hasInit() && var.getInit().isConstant()) || !isDUbefore(var))) {
        }
      }
      // field
      else if(v instanceof FieldDeclaration) {
        FieldDeclaration f = (FieldDeclaration)v;
        if(f.isFinal()) {
          if(f.hasInit())
            error("initialized field " + f.name() + " can not be assigned");
          else {
            BodyDecl bodyDecl = enclosingBodyDecl();
            if(!(bodyDecl instanceof ConstructorDecl) && !(bodyDecl instanceof InstanceInitializer) && !(bodyDecl instanceof StaticInitializer) && !(bodyDecl instanceof FieldDeclaration))
              error("final field " + f.name() + " may only be assigned in constructors and initializers");
            else if(!isDUbefore(f))
              error("Final field " + f.name() + " may only be assigned once");
          }
        }
      }
      else if(v instanceof ParameterDeclaration) {
        ParameterDeclaration p = (ParameterDeclaration)v;

        // 8.4.1
        if(p.isFinal()) {
          error("Final parameter " + p.name() + " may not be assigned");
        }
      }
      
    }
  }

    // Declared in DefiniteAssignment.jrag at line 460


  protected boolean checkDUeverywhere(Variable v) {
    if(isDest() && decl() == v)
      return false;
    return super.checkDUeverywhere(v);
  }

    // Declared in NameCheck.jrag at line 177


  public void nameCheck() {
    if(decls().isEmpty() && (!isQualified() || !qualifier().type().isUnknown() || qualifier().isPackageAccess()))
      error("no field named " + name());
    if(decls().size() > 1) {
      StringBuffer s = new StringBuffer();
      s.append("several fields named " + name());
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        Variable v = (Variable)iter.next();
        s.append("\n    " + v.type().typeName() + "." + v.name() + " declared in " + v.hostType().typeName());
      }
      error(s.toString());
    }
      
    // 8.8.5.1
    if(inExplicitConstructorInvocation() && !isQualified() && decl().isInstanceVariable() && hostType() == decl().hostType())
      error("instance variable " + name() + " may not be accessed in an explicit constructor invocation");

    Variable v = decl();
    if(!v.isFinal() && !v.isClassVariable() && !v.isInstanceVariable() && v.hostType() != hostType())
      error("A parameter/variable used but not declared in an inner class must be declared final");

    // 8.3.2.3
    if((decl().isInstanceVariable() || decl().isClassVariable()) && !isQualified()) {
      if(hostType() != null && !hostType().declaredBeforeUse(decl(), this)) {
        if(inSameInitializer() && !simpleAssignment() && inDeclaringClass()) {
          BodyDecl b = closestBodyDecl(hostType());
          error("variable " + decl().name() + " is used in " + b + " before it is declared");
        }
      }
    }

  }

    // Declared in NameCheck.jrag at line 211


  // find the bodydecl declared in t in which this construct is nested
  public BodyDecl closestBodyDecl(TypeDecl t) {
    ASTNode node = this;
    while(!(node.getParent().getParent() instanceof Program) && node.getParent().getParent() != t) {
      node = node.getParent();
    }
    if(node instanceof BodyDecl)
      return (BodyDecl)node;
    return null;
  }

    // Declared in NodeConstructors.jrag at line 38

  public VarAccess(String name, int start, int end) {
    this(name);
    this.start = start;
    this.end = end;
  }

    // Declared in PrettyPrint.jadd at line 452


  public void toString(StringBuffer s) {
    s.append(name());
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 16

    public VarAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 16
    public VarAccess(String p0) {
        setID(p0);
    }

    // Declared in java.ast at line 15


    // Declared in java.ast line 16
    public VarAccess(beaver.Symbol p0) {
        setID(p0);
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
    // Declared in java.ast line 16
    protected String tokenString_ID;

    // Declared in java.ast at line 3

    public void setID(String value) {
        tokenString_ID = value;
    }

    // Declared in java.ast at line 6

    public int IDstart;

    // Declared in java.ast at line 7

    public int IDend;

    // Declared in java.ast at line 8

    public void setID(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setID is only valid for String lexemes");
        tokenString_ID = (String)symbol.value;
        IDstart = symbol.getStart();
        IDend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getID() {
        return tokenString_ID != null ? tokenString_ID : "";
    }

    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 108
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {  return type().cast(decl().getInit().constant());  }

    protected int isConstant_visited = -1;
    protected boolean isConstant_computed = false;
    protected boolean isConstant_initialized = false;
    protected boolean isConstant_value;
    // Declared in ConstantExpression.jrag at line 500
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        if(isConstant_computed) {
            return isConstant_value;
        }
        ASTNode$State state = state();
        if (!isConstant_initialized) {
            isConstant_initialized = true;
            isConstant_value = false;
        }
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            do {
                isConstant_visited = state.CIRCLE_INDEX;
                state.CHANGE = false;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                boolean new_isConstant_value = isConstant_compute();
                if (new_isConstant_value!=isConstant_value)
                    state.CHANGE = true;
                isConstant_value = new_isConstant_value; 
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
            isConstant_computed = true;
            state.LAST_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isConstant_compute();
            state.LAST_CYCLE = false;
            }
            else {
            state.RESET_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isConstant_compute();
            state.RESET_CYCLE = false;
              isConstant_computed = false;
              isConstant_initialized = false;
            }
            state.IN_CIRCLE = false; 
            return isConstant_value;
        }
        if(isConstant_visited != state.CIRCLE_INDEX) {
            isConstant_visited = state.CIRCLE_INDEX;
            if (state.LAST_CYCLE) {
                isConstant_computed = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                return isConstant_compute();
            }
            if (state.RESET_CYCLE) {
                isConstant_computed = false;
                isConstant_initialized = false;
                isConstant_visited = -1;
                return isConstant_value;
            }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            boolean new_isConstant_value = isConstant_compute();
            if (new_isConstant_value!=isConstant_value)
                state.CHANGE = true;
            isConstant_value = new_isConstant_value; 
            return isConstant_value;
        }
        return isConstant_value;
    }

    private boolean isConstant_compute() {
    Variable v = decl();
    if(v instanceof FieldDeclaration) {
      FieldDeclaration f = (FieldDeclaration)v;
      return f.isConstant() && (!isQualified() || (isQualified() && qualifier().isTypeAccess()));
    }
    boolean result = v.isFinal() && v.hasInit() && v.getInit().isConstant() && (v.type().isPrimitive() || v.type().isString());
    return result && (!isQualified() || (isQualified() && qualifier().isTypeAccess()));
  }

    protected int varDecl_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 60
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        if(varDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: varDecl in class: ");
        varDecl_visited = state().boundariesCrossed;
        Variable varDecl_value = varDecl_compute();
        varDecl_visited = -1;
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return decl();  }

    protected java.util.Map isDAafter_Variable_visited;
    protected java.util.Map isDAafter_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 353
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
    return (isDest() && decl() == v) || isDAbefore(v);
  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 835
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafter(Variable v) {
        Object _parameters = v;
if(isDUafter_Variable_visited == null) isDUafter_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafter in class: ");
        isDUafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafter_Variable_value = isDUafter_compute(v);
        isDUafter_Variable_visited.remove(_parameters);
        return isDUafter_Variable_value;
    }

    private boolean isDUafter_compute(Variable v) {
    if(isDest() && decl() == v)
      return false;
    return isDUbefore(v);
  }

    protected java.util.Map unassignedEverywhere_Variable_TryStmt_visited;
    // Declared in DefiniteAssignment.jrag at line 1210
 @SuppressWarnings({"unchecked", "cast"})     public boolean unassignedEverywhere(Variable v, TryStmt stmt) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(v);
        _parameters.add(stmt);
if(unassignedEverywhere_Variable_TryStmt_visited == null) unassignedEverywhere_Variable_TryStmt_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(unassignedEverywhere_Variable_TryStmt_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: unassignedEverywhere in class: ");
        unassignedEverywhere_Variable_TryStmt_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean unassignedEverywhere_Variable_TryStmt_value = unassignedEverywhere_compute(v, stmt);
        unassignedEverywhere_Variable_TryStmt_visited.remove(_parameters);
        return unassignedEverywhere_Variable_TryStmt_value;
    }

    private boolean unassignedEverywhere_compute(Variable v, TryStmt stmt) {
    if(isDest() && decl() == v && enclosingStmt().reachable()) {
      return false;
    }
    return super.unassignedEverywhere(v, stmt);
  }

    protected int decls_visited = -1;
    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in LookupVariable.jrag at line 230
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet decls() {
        if(decls_computed) {
            return decls_value;
        }
        ASTNode$State state = state();
        if(decls_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: decls in class: ");
        decls_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decls_value = decls_compute();
        if(isFinal && num == state().boundariesCrossed)
            decls_computed = true;
        decls_visited = -1;
        return decls_value;
    }

    private SimpleSet decls_compute() {
    SimpleSet set = lookupVariable(name());
    if(set.size() == 1) {
      Variable v = (Variable)set.iterator().next();
      if(!isQualified() && inStaticContext()) {
        if(v.isInstanceVariable() && !hostType().memberFields(v.name()).isEmpty())
          return SimpleSet.emptySet;
      }
      else if(isQualified() && qualifier().staticContextQualifier()) {
        if(v.isInstanceVariable())
          return SimpleSet.emptySet;
      }
    }
    return set;
  }

    protected int decl_visited = -1;
    protected boolean decl_computed = false;
    protected Variable decl_value;
    // Declared in LookupVariable.jrag at line 245
 @SuppressWarnings({"unchecked", "cast"})     public Variable decl() {
        if(decl_computed) {
            return decl_value;
        }
        ASTNode$State state = state();
        if(decl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: decl in class: ");
        decl_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        decl_value = decl_compute();
        if(isFinal && num == state().boundariesCrossed)
            decl_computed = true;
        decl_visited = -1;
        return decl_value;
    }

    private Variable decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1)
      return (Variable)decls.iterator().next();
    return unknownField();
  }

    protected int inSameInitializer_visited = -1;
    // Declared in NameCheck.jrag at line 221
 @SuppressWarnings({"unchecked", "cast"})     public boolean inSameInitializer() {
        ASTNode$State state = state();
        if(inSameInitializer_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inSameInitializer in class: ");
        inSameInitializer_visited = state().boundariesCrossed;
        boolean inSameInitializer_value = inSameInitializer_compute();
        inSameInitializer_visited = -1;
        return inSameInitializer_value;
    }

    private boolean inSameInitializer_compute() {
    BodyDecl b = closestBodyDecl(decl().hostType());
    if(b == null) return false;
    if(b instanceof FieldDeclaration && ((FieldDeclaration)b).isStatic() == decl().isStatic())
      return true;
    if(b instanceof InstanceInitializer && !decl().isStatic())
      return true;
    if(b instanceof StaticInitializer && decl().isStatic())
      return true;
    return false;
  }

    protected int simpleAssignment_visited = -1;
    // Declared in NameCheck.jrag at line 233
 @SuppressWarnings({"unchecked", "cast"})     public boolean simpleAssignment() {
        ASTNode$State state = state();
        if(simpleAssignment_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: simpleAssignment in class: ");
        simpleAssignment_visited = state().boundariesCrossed;
        boolean simpleAssignment_value = simpleAssignment_compute();
        simpleAssignment_visited = -1;
        return simpleAssignment_value;
    }

    private boolean simpleAssignment_compute() {  return isDest() && getParent() instanceof AssignSimpleExpr;  }

    protected int inDeclaringClass_visited = -1;
    // Declared in NameCheck.jrag at line 235
 @SuppressWarnings({"unchecked", "cast"})     public boolean inDeclaringClass() {
        ASTNode$State state = state();
        if(inDeclaringClass_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inDeclaringClass in class: ");
        inDeclaringClass_visited = state().boundariesCrossed;
        boolean inDeclaringClass_value = inDeclaringClass_compute();
        inDeclaringClass_visited = -1;
        return inDeclaringClass_value;
    }

    private boolean inDeclaringClass_compute() {  return hostType() == decl().hostType();  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 801
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        if(dumpString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: dumpString in class: ");
        dumpString_visited = state().boundariesCrossed;
        String dumpString_value = dumpString_compute();
        dumpString_visited = -1;
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getID() + "]";  }

    protected int name_visited = -1;
    // Declared in QualifiedNames.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public String name() {
        ASTNode$State state = state();
        if(name_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: name in class: ");
        name_visited = state().boundariesCrossed;
        String name_value = name_compute();
        name_visited = -1;
        return name_value;
    }

    private String name_compute() {  return getID();  }

    protected int isFieldAccess_visited = -1;
    protected boolean isFieldAccess_computed = false;
    protected boolean isFieldAccess_value;
    // Declared in ResolveAmbiguousNames.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccess() {
        if(isFieldAccess_computed) {
            return isFieldAccess_value;
        }
        ASTNode$State state = state();
        if(isFieldAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFieldAccess in class: ");
        isFieldAccess_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isFieldAccess_value = isFieldAccess_compute();
        if(isFinal && num == state().boundariesCrossed)
            isFieldAccess_computed = true;
        isFieldAccess_visited = -1;
        return isFieldAccess_value;
    }

    private boolean isFieldAccess_compute() {  return decl().isClassVariable() || decl().isInstanceVariable();  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 111
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.AMBIGUOUS_NAME;  }

    // Declared in TypeAnalysis.jrag at line 283
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl type() {
        if(type_computed) {
            return type_value;
        }
        ASTNode$State state = state();
        if(type_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: type in class: ");
        type_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        type_value = type_compute();
        if(isFinal && num == state().boundariesCrossed)
            type_computed = true;
        type_visited = -1;
        return type_value;
    }

    private TypeDecl type_compute() {  return decl().type();  }

    protected int isVariable_visited = -1;
    // Declared in TypeCheck.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        if(isVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVariable in class: ");
        isVariable_visited = state().boundariesCrossed;
        boolean isVariable_value = isVariable_compute();
        isVariable_visited = -1;
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return true;  }

    protected int inExplicitConstructorInvocation_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 122
 @SuppressWarnings({"unchecked", "cast"})     public boolean inExplicitConstructorInvocation() {
        ASTNode$State state = state();
        if(inExplicitConstructorInvocation_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inExplicitConstructorInvocation in class: ");
        inExplicitConstructorInvocation_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
        inExplicitConstructorInvocation_visited = -1;
        return inExplicitConstructorInvocation_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
