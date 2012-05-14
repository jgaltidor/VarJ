
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public abstract class Expr extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        constant_visited = -1;
        isPositive_visited = -1;
        representableIn_TypeDecl_visited = null;
        isConstant_visited = -1;
        isTrue_visited = -1;
        isFalse_visited = -1;
        varDecl_visited = -1;
        isDAafterFalse_Variable_visited = null;
        isDAafterTrue_Variable_visited = null;
        isDAafter_Variable_visited = null;
        isDUafterFalse_Variable_visited = null;
        isDUafterTrue_Variable_visited = null;
        isDUafter_Variable_visited = null;
        mostSpecificConstructor_Collection_visited = null;
        applicableAndAccessible_ConstructorDecl_visited = null;
        hasQualifiedPackage_String_visited = null;
        qualifiedLookupType_String_visited = null;
        qualifiedLookupVariable_String_visited = null;
        packageName_visited = -1;
        typeName_visited = -1;
        isTypeAccess_visited = -1;
        isMethodAccess_visited = -1;
        isFieldAccess_visited = -1;
        isSuperAccess_visited = -1;
        isThisAccess_visited = -1;
        isPackageAccess_visited = -1;
        isArrayAccess_visited = -1;
        isClassAccess_visited = -1;
        isSuperConstructorAccess_visited = -1;
        isLeftChildOfDot_visited = -1;
        isRightChildOfDot_visited = -1;
        parentDot_visited = -1;
        hasParentDot_visited = -1;
        nextAccess_visited = -1;
        hasNextAccess_visited = -1;
        enclosingStmt_visited = -1;
        isVariable_visited = -1;
        isUnknown_visited = -1;
        staticContextQualifier_visited = -1;
        isDest_visited = -1;
        isSource_visited = -1;
        isIncOrDec_visited = -1;
        isDAbefore_Variable_visited = null;
        isDUbefore_Variable_visited = null;
        lookupMethod_String_visited = null;
        typeBoolean_visited = -1;
        typeByte_visited = -1;
        typeShort_visited = -1;
        typeChar_visited = -1;
        typeInt_visited = -1;
        typeLong_visited = -1;
        typeFloat_visited = -1;
        typeDouble_visited = -1;
        typeString_visited = -1;
        typeVoid_visited = -1;
        typeNull_visited = -1;
        unknownType_visited = -1;
        hasPackage_String_visited = null;
        lookupType_String_String_visited = null;
        lookupType_String_visited = null;
        lookupVariable_String_visited = null;
        nameType_visited = -1;
        enclosingBodyDecl_visited = -1;
        hostPackage_visited = -1;
        hostType_visited = -1;
        methodHost_visited = -1;
        inStaticContext_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Expr clone() throws CloneNotSupportedException {
        Expr node = (Expr)super.clone();
        node.constant_visited = -1;
        node.isPositive_visited = -1;
        node.representableIn_TypeDecl_visited = null;
        node.isConstant_visited = -1;
        node.isTrue_visited = -1;
        node.isFalse_visited = -1;
        node.varDecl_visited = -1;
        node.isDAafterFalse_Variable_visited = null;
        node.isDAafterTrue_Variable_visited = null;
        node.isDAafter_Variable_visited = null;
        node.isDUafterFalse_Variable_visited = null;
        node.isDUafterTrue_Variable_visited = null;
        node.isDUafter_Variable_visited = null;
        node.mostSpecificConstructor_Collection_visited = null;
        node.applicableAndAccessible_ConstructorDecl_visited = null;
        node.hasQualifiedPackage_String_visited = null;
        node.qualifiedLookupType_String_visited = null;
        node.qualifiedLookupVariable_String_visited = null;
        node.packageName_visited = -1;
        node.typeName_visited = -1;
        node.isTypeAccess_visited = -1;
        node.isMethodAccess_visited = -1;
        node.isFieldAccess_visited = -1;
        node.isSuperAccess_visited = -1;
        node.isThisAccess_visited = -1;
        node.isPackageAccess_visited = -1;
        node.isArrayAccess_visited = -1;
        node.isClassAccess_visited = -1;
        node.isSuperConstructorAccess_visited = -1;
        node.isLeftChildOfDot_visited = -1;
        node.isRightChildOfDot_visited = -1;
        node.parentDot_visited = -1;
        node.hasParentDot_visited = -1;
        node.nextAccess_visited = -1;
        node.hasNextAccess_visited = -1;
        node.enclosingStmt_visited = -1;
        node.isVariable_visited = -1;
        node.isUnknown_visited = -1;
        node.staticContextQualifier_visited = -1;
        node.isDest_visited = -1;
        node.isSource_visited = -1;
        node.isIncOrDec_visited = -1;
        node.isDAbefore_Variable_visited = null;
        node.isDUbefore_Variable_visited = null;
        node.lookupMethod_String_visited = null;
        node.typeBoolean_visited = -1;
        node.typeByte_visited = -1;
        node.typeShort_visited = -1;
        node.typeChar_visited = -1;
        node.typeInt_visited = -1;
        node.typeLong_visited = -1;
        node.typeFloat_visited = -1;
        node.typeDouble_visited = -1;
        node.typeString_visited = -1;
        node.typeVoid_visited = -1;
        node.typeNull_visited = -1;
        node.unknownType_visited = -1;
        node.hasPackage_String_visited = null;
        node.lookupType_String_String_visited = null;
        node.lookupType_String_visited = null;
        node.lookupVariable_String_visited = null;
        node.nameType_visited = -1;
        node.enclosingBodyDecl_visited = -1;
        node.hostPackage_visited = -1;
        node.hostType_visited = -1;
        node.methodHost_visited = -1;
        node.inStaticContext_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in LookupType.jrag at line 373

    
  public SimpleSet keepAccessibleTypes(SimpleSet oldSet) {
    SimpleSet newSet = SimpleSet.emptySet;
    TypeDecl hostType = hostType();
    for(Iterator iter = oldSet.iterator(); iter.hasNext(); ) {
      TypeDecl t = (TypeDecl)iter.next();
      if((hostType != null && t.accessibleFrom(hostType)) || (hostType == null && t.accessibleFromPackage(hostPackage())))
        newSet = newSet.add(t);
    }
    return newSet;
  }

    // Declared in LookupVariable.jrag at line 164


  // remove fields that are not accessible when using this Expr as qualifier
  public SimpleSet keepAccessibleFields(SimpleSet oldSet) {
    SimpleSet newSet = SimpleSet.emptySet;
    for(Iterator iter = oldSet.iterator(); iter.hasNext(); ) {
      Variable v = (Variable)iter.next();
      if(v instanceof FieldDeclaration) {
        FieldDeclaration f = (FieldDeclaration)v;
        if(mayAccess(f))
          newSet = newSet.add(f);
      }
    }
    return newSet;
  }

    // Declared in LookupVariable.jrag at line 187


  public boolean mayAccess(FieldDeclaration f) {
    if(f.isPublic()) 
      return true;
    else if(f.isProtected()) {
      if(f.hostPackage().equals(hostPackage()))
        return true;
      TypeDecl C = f.hostType();
      TypeDecl S = hostType().subclassWithinBody(C);
      TypeDecl Q = type();
      if(S == null)
        return false;
      if(f.isInstanceVariable() && !isSuperAccess())
        return Q.instanceOf(S);
      return true;
    }
    else if(f.isPrivate())
      return f.hostType().topLevelType() == hostType().topLevelType();
    else
      return f.hostPackage().equals(hostType().hostPackage());
  }

    // Declared in ResolveAmbiguousNames.jrag at line 106


  public Dot qualifiesAccess(Access access) {
    Dot dot = new Dot(this, access);
    return dot;
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 97

    public Expr() {
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

    // Declared in TypeAnalysis.jrag at line 276
 @SuppressWarnings({"unchecked", "cast"})     public abstract TypeDecl type();
    protected int constant_visited = -1;
    // Declared in ConstantExpression.jrag at line 98
 @SuppressWarnings({"unchecked", "cast"})     public Constant constant() {
        ASTNode$State state = state();
        if(constant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constant in class: ");
        constant_visited = state().boundariesCrossed;
        Constant constant_value = constant_compute();
        constant_visited = -1;
        return constant_value;
    }

    private Constant constant_compute() {
    throw new UnsupportedOperationException("ConstantExpression operation constant" +
      " not supported for type " + getClass().getName()); 
  }

    protected int isPositive_visited = -1;
    // Declared in ConstantExpression.jrag at line 241
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPositive() {
        ASTNode$State state = state();
        if(isPositive_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPositive in class: ");
        isPositive_visited = state().boundariesCrossed;
        boolean isPositive_value = isPositive_compute();
        isPositive_visited = -1;
        return isPositive_value;
    }

    private boolean isPositive_compute() {  return false;  }

    protected java.util.Map representableIn_TypeDecl_visited;
    // Declared in ConstantExpression.jrag at line 454
 @SuppressWarnings({"unchecked", "cast"})     public boolean representableIn(TypeDecl t) {
        Object _parameters = t;
if(representableIn_TypeDecl_visited == null) representableIn_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(representableIn_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: representableIn in class: ");
        representableIn_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean representableIn_TypeDecl_value = representableIn_compute(t);
        representableIn_TypeDecl_visited.remove(_parameters);
        return representableIn_TypeDecl_value;
    }

    private boolean representableIn_compute(TypeDecl t) {	
  	if (!type().isByte() && !type().isChar() && !type().isShort() && !type().isInt()) {
  		return false;
  	}
  	if (t.isByte())
  		return constant().intValue() >= Byte.MIN_VALUE && constant().intValue() <= Byte.MAX_VALUE;
  	if (t.isChar())
  		return constant().intValue() >= Character.MIN_VALUE && constant().intValue() <= Character.MAX_VALUE;
  	if (t.isShort())
  		return constant().intValue() >= Short.MIN_VALUE && constant().intValue() <= Short.MAX_VALUE;
    if(t.isInt()) 
      return constant().intValue() >= Integer.MIN_VALUE && constant().intValue() <= Integer.MAX_VALUE;
	  return false;
  }

    protected int isConstant_visited = -1;
    // Declared in ConstantExpression.jrag at line 482
 @SuppressWarnings({"unchecked", "cast"})     public boolean isConstant() {
        ASTNode$State state = state();
        if(isConstant_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isConstant in class: ");
        isConstant_visited = state().boundariesCrossed;
        boolean isConstant_value = isConstant_compute();
        isConstant_visited = -1;
        return isConstant_value;
    }

    private boolean isConstant_compute() {  return false;  }

    protected int isTrue_visited = -1;
    // Declared in ConstantExpression.jrag at line 511
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTrue() {
        ASTNode$State state = state();
        if(isTrue_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTrue in class: ");
        isTrue_visited = state().boundariesCrossed;
        boolean isTrue_value = isTrue_compute();
        isTrue_visited = -1;
        return isTrue_value;
    }

    private boolean isTrue_compute() {  return isConstant() && type() instanceof BooleanType && constant().booleanValue();  }

    protected int isFalse_visited = -1;
    // Declared in ConstantExpression.jrag at line 512
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFalse() {
        ASTNode$State state = state();
        if(isFalse_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFalse in class: ");
        isFalse_visited = state().boundariesCrossed;
        boolean isFalse_value = isFalse_compute();
        isFalse_visited = -1;
        return isFalse_value;
    }

    private boolean isFalse_compute() {  return isConstant() && type() instanceof BooleanType && !constant().booleanValue();  }

    protected int varDecl_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public Variable varDecl() {
        ASTNode$State state = state();
        if(varDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: varDecl in class: ");
        varDecl_visited = state().boundariesCrossed;
        Variable varDecl_value = varDecl_compute();
        varDecl_visited = -1;
        return varDecl_value;
    }

    private Variable varDecl_compute() {  return null;  }

    protected java.util.Map isDAafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 340
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterFalse(Variable v) {
        Object _parameters = v;
if(isDAafterFalse_Variable_visited == null) isDAafterFalse_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterFalse_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterFalse in class: ");
        isDAafterFalse_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterFalse_Variable_value = isDAafterFalse_compute(v);
        isDAafterFalse_Variable_visited.remove(_parameters);
        return isDAafterFalse_Variable_value;
    }

    private boolean isDAafterFalse_compute(Variable v) {  return isTrue() || isDAbefore(v);  }

    protected java.util.Map isDAafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 342
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafterTrue(Variable v) {
        Object _parameters = v;
if(isDAafterTrue_Variable_visited == null) isDAafterTrue_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafterTrue_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafterTrue in class: ");
        isDAafterTrue_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafterTrue_Variable_value = isDAafterTrue_compute(v);
        isDAafterTrue_Variable_visited.remove(_parameters);
        return isDAafterTrue_Variable_value;
    }

    private boolean isDAafterTrue_compute(Variable v) {  return isFalse() || isDAbefore(v);  }

    protected java.util.Map isDAafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 345
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAafter(Variable v) {
        Object _parameters = v;
if(isDAafter_Variable_visited == null) isDAafter_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAafter_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAafter in class: ");
        isDAafter_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDAafter_Variable_value = isDAafter_compute(v);
        isDAafter_Variable_visited.remove(_parameters);
        return isDAafter_Variable_value;
    }

    private boolean isDAafter_compute(Variable v) {  return (isDAafterFalse(v) && isDAafterTrue(v)) || isDAbefore(v);  }

    protected java.util.Map isDUafterFalse_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 784
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterFalse(Variable v) {
        Object _parameters = v;
if(isDUafterFalse_Variable_visited == null) isDUafterFalse_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterFalse_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterFalse in class: ");
        isDUafterFalse_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterFalse_Variable_value = isDUafterFalse_compute(v);
        isDUafterFalse_Variable_visited.remove(_parameters);
        return isDUafterFalse_Variable_value;
    }

    private boolean isDUafterFalse_compute(Variable v) {
    if(isTrue())
      return true;
    return isDUbefore(v);
  }

    protected java.util.Map isDUafterTrue_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 790
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUafterTrue(Variable v) {
        Object _parameters = v;
if(isDUafterTrue_Variable_visited == null) isDUafterTrue_Variable_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUafterTrue_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUafterTrue in class: ");
        isDUafterTrue_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isDUafterTrue_Variable_value = isDUafterTrue_compute(v);
        isDUafterTrue_Variable_visited.remove(_parameters);
        return isDUafterTrue_Variable_value;
    }

    private boolean isDUafterTrue_compute(Variable v) {
    if(isFalse())
      return true;
    return isDUbefore(v);
  }

    protected java.util.Map isDUafter_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 800
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

    private boolean isDUafter_compute(Variable v) {  return (isDUafterFalse(v) && isDUafterTrue(v)) || isDUbefore(v);  }

    protected java.util.Map mostSpecificConstructor_Collection_visited;
    // Declared in LookupConstructor.jrag at line 32
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet mostSpecificConstructor(Collection constructors) {
        Object _parameters = constructors;
if(mostSpecificConstructor_Collection_visited == null) mostSpecificConstructor_Collection_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(mostSpecificConstructor_Collection_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: mostSpecificConstructor in class: ");
        mostSpecificConstructor_Collection_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet mostSpecificConstructor_Collection_value = mostSpecificConstructor_compute(constructors);
        mostSpecificConstructor_Collection_visited.remove(_parameters);
        return mostSpecificConstructor_Collection_value;
    }

    private SimpleSet mostSpecificConstructor_compute(Collection constructors) {
    SimpleSet maxSpecific = SimpleSet.emptySet;
    for(Iterator iter = constructors.iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(applicableAndAccessible(decl)) {
        if(maxSpecific.isEmpty())
          maxSpecific = maxSpecific.add(decl);
        else {
          if(decl.moreSpecificThan((ConstructorDecl)maxSpecific.iterator().next()))
            maxSpecific = SimpleSet.emptySet.add(decl);
          else if(!((ConstructorDecl)maxSpecific.iterator().next()).moreSpecificThan(decl))
            maxSpecific = maxSpecific.add(decl);
        }
      }
    }
    return maxSpecific;
  }

    protected java.util.Map applicableAndAccessible_ConstructorDecl_visited;
    // Declared in LookupConstructor.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public boolean applicableAndAccessible(ConstructorDecl decl) {
        Object _parameters = decl;
if(applicableAndAccessible_ConstructorDecl_visited == null) applicableAndAccessible_ConstructorDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(applicableAndAccessible_ConstructorDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: applicableAndAccessible in class: ");
        applicableAndAccessible_ConstructorDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean applicableAndAccessible_ConstructorDecl_value = applicableAndAccessible_compute(decl);
        applicableAndAccessible_ConstructorDecl_visited.remove(_parameters);
        return applicableAndAccessible_ConstructorDecl_value;
    }

    private boolean applicableAndAccessible_compute(ConstructorDecl decl) {  return false;  }

    protected java.util.Map hasQualifiedPackage_String_visited;
    // Declared in LookupType.jrag at line 83
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasQualifiedPackage(String packageName) {
        Object _parameters = packageName;
if(hasQualifiedPackage_String_visited == null) hasQualifiedPackage_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(hasQualifiedPackage_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: hasQualifiedPackage in class: ");
        hasQualifiedPackage_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean hasQualifiedPackage_String_value = hasQualifiedPackage_compute(packageName);
        hasQualifiedPackage_String_visited.remove(_parameters);
        return hasQualifiedPackage_String_value;
    }

    private boolean hasQualifiedPackage_compute(String packageName) {  return false;  }

    protected java.util.Map qualifiedLookupType_String_visited;
    // Declared in LookupType.jrag at line 342
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupType(String name) {
        Object _parameters = name;
if(qualifiedLookupType_String_visited == null) qualifiedLookupType_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(qualifiedLookupType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: qualifiedLookupType in class: ");
        qualifiedLookupType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet qualifiedLookupType_String_value = qualifiedLookupType_compute(name);
        qualifiedLookupType_String_visited.remove(_parameters);
        return qualifiedLookupType_String_value;
    }

    private SimpleSet qualifiedLookupType_compute(String name) {  return keepAccessibleTypes(type().memberTypes(name));  }

    protected java.util.Map qualifiedLookupVariable_String_visited;
    // Declared in LookupVariable.jrag at line 146
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet qualifiedLookupVariable(String name) {
        Object _parameters = name;
if(qualifiedLookupVariable_String_visited == null) qualifiedLookupVariable_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(qualifiedLookupVariable_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: qualifiedLookupVariable in class: ");
        qualifiedLookupVariable_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet qualifiedLookupVariable_String_value = qualifiedLookupVariable_compute(name);
        qualifiedLookupVariable_String_visited.remove(_parameters);
        return qualifiedLookupVariable_String_value;
    }

    private SimpleSet qualifiedLookupVariable_compute(String name) {
    if(type().accessibleFrom(hostType()))
      return keepAccessibleFields(type().memberFields(name));
    return SimpleSet.emptySet;
  }

    protected int packageName_visited = -1;
    // Declared in QualifiedNames.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public String packageName() {
        ASTNode$State state = state();
        if(packageName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: packageName in class: ");
        packageName_visited = state().boundariesCrossed;
        String packageName_value = packageName_compute();
        packageName_visited = -1;
        return packageName_value;
    }

    private String packageName_compute() {  return "";  }

    protected int typeName_visited = -1;
    // Declared in QualifiedNames.jrag at line 62
 @SuppressWarnings({"unchecked", "cast"})     public String typeName() {
        ASTNode$State state = state();
        if(typeName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeName in class: ");
        typeName_visited = state().boundariesCrossed;
        String typeName_value = typeName_compute();
        typeName_visited = -1;
        return typeName_value;
    }

    private String typeName_compute() {  return "";  }

    protected int isTypeAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 13
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        ASTNode$State state = state();
        if(isTypeAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTypeAccess in class: ");
        isTypeAccess_visited = state().boundariesCrossed;
        boolean isTypeAccess_value = isTypeAccess_compute();
        isTypeAccess_visited = -1;
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return false;  }

    protected int isMethodAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMethodAccess() {
        ASTNode$State state = state();
        if(isMethodAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isMethodAccess in class: ");
        isMethodAccess_visited = state().boundariesCrossed;
        boolean isMethodAccess_value = isMethodAccess_compute();
        isMethodAccess_visited = -1;
        return isMethodAccess_value;
    }

    private boolean isMethodAccess_compute() {  return false;  }

    protected int isFieldAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFieldAccess() {
        ASTNode$State state = state();
        if(isFieldAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFieldAccess in class: ");
        isFieldAccess_visited = state().boundariesCrossed;
        boolean isFieldAccess_value = isFieldAccess_compute();
        isFieldAccess_visited = -1;
        return isFieldAccess_value;
    }

    private boolean isFieldAccess_compute() {  return false;  }

    protected int isSuperAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperAccess() {
        ASTNode$State state = state();
        if(isSuperAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSuperAccess in class: ");
        isSuperAccess_visited = state().boundariesCrossed;
        boolean isSuperAccess_value = isSuperAccess_compute();
        isSuperAccess_visited = -1;
        return isSuperAccess_value;
    }

    private boolean isSuperAccess_compute() {  return false;  }

    protected int isThisAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public boolean isThisAccess() {
        ASTNode$State state = state();
        if(isThisAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isThisAccess in class: ");
        isThisAccess_visited = state().boundariesCrossed;
        boolean isThisAccess_value = isThisAccess_compute();
        isThisAccess_visited = -1;
        return isThisAccess_value;
    }

    private boolean isThisAccess_compute() {  return false;  }

    protected int isPackageAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 37
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPackageAccess() {
        ASTNode$State state = state();
        if(isPackageAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPackageAccess in class: ");
        isPackageAccess_visited = state().boundariesCrossed;
        boolean isPackageAccess_value = isPackageAccess_compute();
        isPackageAccess_visited = -1;
        return isPackageAccess_value;
    }

    private boolean isPackageAccess_compute() {  return false;  }

    protected int isArrayAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 41
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayAccess() {
        ASTNode$State state = state();
        if(isArrayAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isArrayAccess in class: ");
        isArrayAccess_visited = state().boundariesCrossed;
        boolean isArrayAccess_value = isArrayAccess_compute();
        isArrayAccess_visited = -1;
        return isArrayAccess_value;
    }

    private boolean isArrayAccess_compute() {  return false;  }

    protected int isClassAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 45
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassAccess() {
        ASTNode$State state = state();
        if(isClassAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isClassAccess in class: ");
        isClassAccess_visited = state().boundariesCrossed;
        boolean isClassAccess_value = isClassAccess_compute();
        isClassAccess_visited = -1;
        return isClassAccess_value;
    }

    private boolean isClassAccess_compute() {  return false;  }

    protected int isSuperConstructorAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSuperConstructorAccess() {
        ASTNode$State state = state();
        if(isSuperConstructorAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSuperConstructorAccess in class: ");
        isSuperConstructorAccess_visited = state().boundariesCrossed;
        boolean isSuperConstructorAccess_value = isSuperConstructorAccess_compute();
        isSuperConstructorAccess_visited = -1;
        return isSuperConstructorAccess_value;
    }

    private boolean isSuperConstructorAccess_compute() {  return false;  }

    protected int isLeftChildOfDot_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLeftChildOfDot() {
        ASTNode$State state = state();
        if(isLeftChildOfDot_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isLeftChildOfDot in class: ");
        isLeftChildOfDot_visited = state().boundariesCrossed;
        boolean isLeftChildOfDot_value = isLeftChildOfDot_compute();
        isLeftChildOfDot_visited = -1;
        return isLeftChildOfDot_value;
    }

    private boolean isLeftChildOfDot_compute() {  return hasParentDot() && parentDot().getLeft() == this;  }

    protected int isRightChildOfDot_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public boolean isRightChildOfDot() {
        ASTNode$State state = state();
        if(isRightChildOfDot_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isRightChildOfDot in class: ");
        isRightChildOfDot_visited = state().boundariesCrossed;
        boolean isRightChildOfDot_value = isRightChildOfDot_compute();
        isRightChildOfDot_visited = -1;
        return isRightChildOfDot_value;
    }

    private boolean isRightChildOfDot_compute() {  return hasParentDot() && parentDot().getRight() == this;  }

    protected int parentDot_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 69
 @SuppressWarnings({"unchecked", "cast"})     public AbstractDot parentDot() {
        ASTNode$State state = state();
        if(parentDot_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: parentDot in class: ");
        parentDot_visited = state().boundariesCrossed;
        AbstractDot parentDot_value = parentDot_compute();
        parentDot_visited = -1;
        return parentDot_value;
    }

    private AbstractDot parentDot_compute() {  return getParent() instanceof AbstractDot ? (AbstractDot)getParent() : null;  }

    protected int hasParentDot_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasParentDot() {
        ASTNode$State state = state();
        if(hasParentDot_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasParentDot in class: ");
        hasParentDot_visited = state().boundariesCrossed;
        boolean hasParentDot_value = hasParentDot_compute();
        hasParentDot_visited = -1;
        return hasParentDot_value;
    }

    private boolean hasParentDot_compute() {  return parentDot() != null;  }

    protected int nextAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public Access nextAccess() {
        ASTNode$State state = state();
        if(nextAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: nextAccess in class: ");
        nextAccess_visited = state().boundariesCrossed;
        Access nextAccess_value = nextAccess_compute();
        nextAccess_visited = -1;
        return nextAccess_value;
    }

    private Access nextAccess_compute() {  return parentDot().nextAccess();  }

    protected int hasNextAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 73
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasNextAccess() {
        ASTNode$State state = state();
        if(hasNextAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasNextAccess in class: ");
        hasNextAccess_visited = state().boundariesCrossed;
        boolean hasNextAccess_value = hasNextAccess_compute();
        hasNextAccess_visited = -1;
        return hasNextAccess_value;
    }

    private boolean hasNextAccess_compute() {  return isLeftChildOfDot();  }

    protected int enclosingStmt_visited = -1;
    // Declared in TypeAnalysis.jrag at line 504
 @SuppressWarnings({"unchecked", "cast"})     public Stmt enclosingStmt() {
        ASTNode$State state = state();
        if(enclosingStmt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingStmt in class: ");
        enclosingStmt_visited = state().boundariesCrossed;
        Stmt enclosingStmt_value = enclosingStmt_compute();
        enclosingStmt_visited = -1;
        return enclosingStmt_value;
    }

    private Stmt enclosingStmt_compute() {
    ASTNode node = this;
    while(node != null && !(node instanceof Stmt))
      node = node.getParent();
    return (Stmt)node;
  }

    protected int isVariable_visited = -1;
    // Declared in TypeCheck.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVariable() {
        ASTNode$State state = state();
        if(isVariable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVariable in class: ");
        isVariable_visited = state().boundariesCrossed;
        boolean isVariable_value = isVariable_compute();
        isVariable_visited = -1;
        return isVariable_value;
    }

    private boolean isVariable_compute() {  return false;  }

    protected int isUnknown_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 20
 @SuppressWarnings({"unchecked", "cast"})     public boolean isUnknown() {
        ASTNode$State state = state();
        if(isUnknown_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isUnknown in class: ");
        isUnknown_visited = state().boundariesCrossed;
        boolean isUnknown_value = isUnknown_compute();
        isUnknown_visited = -1;
        return isUnknown_value;
    }

    private boolean isUnknown_compute() {  return type().isUnknown();  }

    protected int staticContextQualifier_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 150
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        if(staticContextQualifier_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: staticContextQualifier in class: ");
        staticContextQualifier_visited = state().boundariesCrossed;
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        staticContextQualifier_visited = -1;
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return false;  }

    protected int isDest_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDest() {
        ASTNode$State state = state();
        if(isDest_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isDest in class: ");
        isDest_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDest_value = getParent().Define_boolean_isDest(this, null);
        isDest_visited = -1;
        return isDest_value;
    }

    protected int isSource_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSource() {
        ASTNode$State state = state();
        if(isSource_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSource in class: ");
        isSource_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isSource_value = getParent().Define_boolean_isSource(this, null);
        isSource_visited = -1;
        return isSource_value;
    }

    protected int isIncOrDec_visited = -1;
    // Declared in DefiniteAssignment.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public boolean isIncOrDec() {
        ASTNode$State state = state();
        if(isIncOrDec_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isIncOrDec in class: ");
        isIncOrDec_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isIncOrDec_value = getParent().Define_boolean_isIncOrDec(this, null);
        isIncOrDec_visited = -1;
        return isIncOrDec_value;
    }

    protected java.util.Map isDAbefore_Variable_visited;
    // Declared in DefiniteAssignment.jrag at line 236
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
    // Declared in DefiniteAssignment.jrag at line 696
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
    // Declared in LookupMethod.jrag at line 23
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

    protected int typeBoolean_visited = -1;
    // Declared in LookupType.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeBoolean() {
        ASTNode$State state = state();
        if(typeBoolean_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeBoolean in class: ");
        typeBoolean_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeBoolean_value = getParent().Define_TypeDecl_typeBoolean(this, null);
        typeBoolean_visited = -1;
        return typeBoolean_value;
    }

    protected int typeByte_visited = -1;
    // Declared in LookupType.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeByte() {
        ASTNode$State state = state();
        if(typeByte_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeByte in class: ");
        typeByte_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeByte_value = getParent().Define_TypeDecl_typeByte(this, null);
        typeByte_visited = -1;
        return typeByte_value;
    }

    protected int typeShort_visited = -1;
    // Declared in LookupType.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeShort() {
        ASTNode$State state = state();
        if(typeShort_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeShort in class: ");
        typeShort_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeShort_value = getParent().Define_TypeDecl_typeShort(this, null);
        typeShort_visited = -1;
        return typeShort_value;
    }

    protected int typeChar_visited = -1;
    // Declared in LookupType.jrag at line 52
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeChar() {
        ASTNode$State state = state();
        if(typeChar_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeChar in class: ");
        typeChar_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeChar_value = getParent().Define_TypeDecl_typeChar(this, null);
        typeChar_visited = -1;
        return typeChar_value;
    }

    protected int typeInt_visited = -1;
    // Declared in LookupType.jrag at line 53
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeInt() {
        ASTNode$State state = state();
        if(typeInt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeInt in class: ");
        typeInt_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeInt_value = getParent().Define_TypeDecl_typeInt(this, null);
        typeInt_visited = -1;
        return typeInt_value;
    }

    protected int typeLong_visited = -1;
    // Declared in LookupType.jrag at line 54
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeLong() {
        ASTNode$State state = state();
        if(typeLong_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeLong in class: ");
        typeLong_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeLong_value = getParent().Define_TypeDecl_typeLong(this, null);
        typeLong_visited = -1;
        return typeLong_value;
    }

    protected int typeFloat_visited = -1;
    // Declared in LookupType.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeFloat() {
        ASTNode$State state = state();
        if(typeFloat_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeFloat in class: ");
        typeFloat_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeFloat_value = getParent().Define_TypeDecl_typeFloat(this, null);
        typeFloat_visited = -1;
        return typeFloat_value;
    }

    protected int typeDouble_visited = -1;
    // Declared in LookupType.jrag at line 56
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeDouble() {
        ASTNode$State state = state();
        if(typeDouble_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeDouble in class: ");
        typeDouble_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeDouble_value = getParent().Define_TypeDecl_typeDouble(this, null);
        typeDouble_visited = -1;
        return typeDouble_value;
    }

    protected int typeString_visited = -1;
    // Declared in LookupType.jrag at line 57
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeString() {
        ASTNode$State state = state();
        if(typeString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeString in class: ");
        typeString_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeString_value = getParent().Define_TypeDecl_typeString(this, null);
        typeString_visited = -1;
        return typeString_value;
    }

    protected int typeVoid_visited = -1;
    // Declared in LookupType.jrag at line 58
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeVoid() {
        ASTNode$State state = state();
        if(typeVoid_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeVoid in class: ");
        typeVoid_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeVoid_value = getParent().Define_TypeDecl_typeVoid(this, null);
        typeVoid_visited = -1;
        return typeVoid_value;
    }

    protected int typeNull_visited = -1;
    // Declared in LookupType.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeNull() {
        ASTNode$State state = state();
        if(typeNull_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeNull in class: ");
        typeNull_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeNull_value = getParent().Define_TypeDecl_typeNull(this, null);
        typeNull_visited = -1;
        return typeNull_value;
    }

    protected int unknownType_visited = -1;
    // Declared in LookupType.jrag at line 72
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        ASTNode$State state = state();
        if(unknownType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unknownType in class: ");
        unknownType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        unknownType_visited = -1;
        return unknownType_value;
    }

    protected java.util.Map hasPackage_String_visited;
    // Declared in LookupType.jrag at line 86
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasPackage(String packageName) {
        Object _parameters = packageName;
if(hasPackage_String_visited == null) hasPackage_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(hasPackage_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: hasPackage in class: ");
        hasPackage_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean hasPackage_String_value = getParent().Define_boolean_hasPackage(this, null, packageName);
        hasPackage_String_visited.remove(_parameters);
        return hasPackage_String_value;
    }

    protected java.util.Map lookupType_String_String_visited;
    // Declared in LookupType.jrag at line 95
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
    // Declared in LookupType.jrag at line 176
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
    // Declared in LookupVariable.jrag at line 19
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

    protected int nameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 20
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

    protected int enclosingBodyDecl_visited = -1;
    // Declared in TypeAnalysis.jrag at line 511
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

    protected int hostPackage_visited = -1;
    // Declared in TypeAnalysis.jrag at line 568
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
    // Declared in TypeAnalysis.jrag at line 583
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

    protected int methodHost_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 11
 @SuppressWarnings({"unchecked", "cast"})     public String methodHost() {
        ASTNode$State state = state();
        if(methodHost_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: methodHost in class: ");
        methodHost_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        String methodHost_value = getParent().Define_String_methodHost(this, null);
        methodHost_visited = -1;
        return methodHost_value;
    }

    protected int inStaticContext_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 134
 @SuppressWarnings({"unchecked", "cast"})     public boolean inStaticContext() {
        ASTNode$State state = state();
        if(inStaticContext_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inStaticContext in class: ");
        inStaticContext_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean inStaticContext_value = getParent().Define_boolean_inStaticContext(this, null);
        inStaticContext_visited = -1;
        return inStaticContext_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
