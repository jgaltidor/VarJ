
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class TypeAccess extends Access implements Cloneable {
    public void flushCache() {
        super.flushCache();
        decls_visited = -1;
        decls_computed = false;
        decls_value = null;
        decl_visited = -1;
        decl_computed = false;
        decl_value = null;
        qualifiedLookupVariable_String_visited = null;
        dumpString_visited = -1;
        name_visited = -1;
        packageName_visited = -1;
        nameWithPackage_visited = -1;
        typeName_visited = -1;
        isTypeAccess_visited = -1;
        predNameType_visited = -1;
        type_visited = -1;
        type_computed = false;
        type_value = null;
        staticContextQualifier_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeAccess clone() throws CloneNotSupportedException {
        TypeAccess node = (TypeAccess)super.clone();
        node.decls_visited = -1;
        node.decls_computed = false;
        node.decls_value = null;
        node.decl_visited = -1;
        node.decl_computed = false;
        node.decl_value = null;
        node.qualifiedLookupVariable_String_visited = null;
        node.dumpString_visited = -1;
        node.name_visited = -1;
        node.packageName_visited = -1;
        node.nameWithPackage_visited = -1;
        node.typeName_visited = -1;
        node.isTypeAccess_visited = -1;
        node.predNameType_visited = -1;
        node.type_visited = -1;
        node.type_computed = false;
        node.type_value = null;
        node.staticContextQualifier_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeAccess copy() {
      try {
          TypeAccess node = (TypeAccess)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeAccess fullCopy() {
        TypeAccess res = (TypeAccess)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in AccessControl.jrag at line 128

  
  public void accessControl() {
    super.accessControl();
    TypeDecl hostType = hostType();
    if(hostType != null && !hostType.isUnknown() && !type().accessibleFrom(hostType)) {
      error("" + this + " in " + hostType().fullName() + " can not access type " + type().fullName());
    }
    else if((hostType == null || hostType.isUnknown())  && !type().accessibleFromPackage(hostPackage())) {
      error("" + this + " can not access type " + type().fullName());
    }
  }

    // Declared in NameCheck.jrag at line 155

  
  public void nameCheck() {
    if(isQualified() && !qualifier().isTypeAccess() && !qualifier().isPackageAccess())
      error("can not access the type named " + decl().typeName() + " in this context");
    if(decls().isEmpty())
      error("no visible type named " + typeName());
    if(decls().size() > 1) {
      StringBuffer s = new StringBuffer();
      s.append("several types named " + name() + ":");
      for(Iterator iter = decls().iterator(); iter.hasNext(); ) {
        TypeDecl t = (TypeDecl)iter.next();
        s.append(" " + t.typeName());
      }
      error(s.toString());
    }
  }

    // Declared in NodeConstructors.jrag at line 23

  public TypeAccess(String name, int start, int end) {
    this(name);
    this.start = start;
    this.end = end;
  }

    // Declared in NodeConstructors.jrag at line 44


  public TypeAccess(String typeName) {
    this("", typeName);
  }

    // Declared in PrettyPrint.jadd at line 482

  
  public void toString(StringBuffer s) {
    if(decl().isReferenceType())
      s.append(nameWithPackage());
    else
      s.append(decl().name());
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 20

    public TypeAccess() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 20
    public TypeAccess(String p0, String p1) {
        setPackage(p0);
        setID(p1);
    }

    // Declared in java.ast at line 16


    // Declared in java.ast line 20
    public TypeAccess(beaver.Symbol p0, beaver.Symbol p1) {
        setPackage(p0);
        setID(p1);
    }

    // Declared in java.ast at line 21


  protected int numChildren() {
    return 0;
  }

    // Declared in java.ast at line 24

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 20
    protected String tokenString_Package;

    // Declared in java.ast at line 3

    public void setPackage(String value) {
        tokenString_Package = value;
    }

    // Declared in java.ast at line 6

    public int Packagestart;

    // Declared in java.ast at line 7

    public int Packageend;

    // Declared in java.ast at line 8

    public void setPackage(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setPackage is only valid for String lexemes");
        tokenString_Package = (String)symbol.value;
        Packagestart = symbol.getStart();
        Packageend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public String getPackage() {
        return tokenString_Package != null ? tokenString_Package : "";
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 20
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

    protected int decls_visited = -1;
    protected boolean decls_computed = false;
    protected SimpleSet decls_value;
    // Declared in LookupType.jrag at line 135
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
    if(packageName().equals(""))
      return lookupType(name());
    else {
      TypeDecl typeDecl = lookupType(packageName(), name());
      if(typeDecl != null)
        return SimpleSet.emptySet.add(typeDecl);
      return SimpleSet.emptySet;
    }
  }

    protected int decl_visited = -1;
    protected boolean decl_computed = false;
    protected TypeDecl decl_value;
    // Declared in LookupType.jrag at line 150
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl decl() {
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

    private TypeDecl decl_compute() {
    SimpleSet decls = decls();
    if(decls.size() == 1) {
      return (TypeDecl)decls.iterator().next();
    }
    return unknownType();
  }

    protected java.util.Map qualifiedLookupVariable_String_visited;
    // Declared in LookupVariable.jrag at line 152
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
    if(type().accessibleFrom(hostType())) {
      SimpleSet c = type().memberFields(name);
      c = keepAccessibleFields(c);
      if(type().isClassDecl() && c.size() == 1)
        c = removeInstanceVariables(c);
      return c;
    }
    return SimpleSet.emptySet;
  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 803
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        if(dumpString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: dumpString in class: ");
        dumpString_visited = state().boundariesCrossed;
        String dumpString_value = dumpString_compute();
        dumpString_visited = -1;
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getPackage() + ", " + getID() + "]";  }

    protected int name_visited = -1;
    // Declared in QualifiedNames.jrag at line 21
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

    protected int packageName_visited = -1;
    // Declared in QualifiedNames.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public String packageName() {
        ASTNode$State state = state();
        if(packageName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: packageName in class: ");
        packageName_visited = state().boundariesCrossed;
        String packageName_value = packageName_compute();
        packageName_visited = -1;
        return packageName_value;
    }

    private String packageName_compute() {  return getPackage();  }

    protected int nameWithPackage_visited = -1;
    // Declared in QualifiedNames.jrag at line 49
 @SuppressWarnings({"unchecked", "cast"})     public String nameWithPackage() {
        ASTNode$State state = state();
        if(nameWithPackage_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: nameWithPackage in class: ");
        nameWithPackage_visited = state().boundariesCrossed;
        String nameWithPackage_value = nameWithPackage_compute();
        nameWithPackage_visited = -1;
        return nameWithPackage_value;
    }

    private String nameWithPackage_compute() {  return getPackage().equals("") ? name() : (getPackage() + "." + name());  }

    protected int typeName_visited = -1;
    // Declared in QualifiedNames.jrag at line 64
 @SuppressWarnings({"unchecked", "cast"})     public String typeName() {
        ASTNode$State state = state();
        if(typeName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeName in class: ");
        typeName_visited = state().boundariesCrossed;
        String typeName_value = typeName_compute();
        typeName_visited = -1;
        return typeName_value;
    }

    private String typeName_compute() {  return isQualified() ? (qualifier().typeName() + "." + name()) : nameWithPackage();  }

    protected int isTypeAccess_visited = -1;
    // Declared in ResolveAmbiguousNames.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTypeAccess() {
        ASTNode$State state = state();
        if(isTypeAccess_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTypeAccess in class: ");
        isTypeAccess_visited = state().boundariesCrossed;
        boolean isTypeAccess_value = isTypeAccess_compute();
        isTypeAccess_visited = -1;
        return isTypeAccess_value;
    }

    private boolean isTypeAccess_compute() {  return true;  }

    protected int predNameType_visited = -1;
    // Declared in SyntacticClassification.jrag at line 106
 @SuppressWarnings({"unchecked", "cast"})     public NameType predNameType() {
        ASTNode$State state = state();
        if(predNameType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: predNameType in class: ");
        predNameType_visited = state().boundariesCrossed;
        NameType predNameType_value = predNameType_compute();
        predNameType_visited = -1;
        return predNameType_value;
    }

    private NameType predNameType_compute() {  return NameType.PACKAGE_OR_TYPE_NAME;  }

    // Declared in TypeAnalysis.jrag at line 279
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

    private TypeDecl type_compute() {  return decl();  }

    protected int staticContextQualifier_visited = -1;
    // Declared in TypeHierarchyCheck.jrag at line 154
 @SuppressWarnings({"unchecked", "cast"})     public boolean staticContextQualifier() {
        ASTNode$State state = state();
        if(staticContextQualifier_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: staticContextQualifier in class: ");
        staticContextQualifier_visited = state().boundariesCrossed;
        boolean staticContextQualifier_value = staticContextQualifier_compute();
        staticContextQualifier_visited = -1;
        return staticContextQualifier_value;
    }

    private boolean staticContextQualifier_compute() {  return true;  }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
