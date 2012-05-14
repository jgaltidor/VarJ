
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


// 7.3 Compilation Units

public class CompilationUnit extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        relativeName_visited = -1;
        pathName_visited = -1;
        fromSource_visited = -1;
        localLookupType_String_visited = null;
        importedTypes_String_visited = null;
        importedTypesOnDemand_String_visited = null;
        dumpString_visited = -1;
        packageName_visited = -1;
        packageName_computed = false;
        packageName_value = null;
        lookupType_String_String_visited = null;
        lookupType_String_visited = null;
        lookupType_String_values = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public CompilationUnit clone() throws CloneNotSupportedException {
        CompilationUnit node = (CompilationUnit)super.clone();
        node.relativeName_visited = -1;
        node.pathName_visited = -1;
        node.fromSource_visited = -1;
        node.localLookupType_String_visited = null;
        node.importedTypes_String_visited = null;
        node.importedTypesOnDemand_String_visited = null;
        node.dumpString_visited = -1;
        node.packageName_visited = -1;
        node.packageName_computed = false;
        node.packageName_value = null;
        node.lookupType_String_String_visited = null;
        node.lookupType_String_visited = null;
        node.lookupType_String_values = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CompilationUnit copy() {
      try {
          CompilationUnit node = (CompilationUnit)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public CompilationUnit fullCopy() {
        CompilationUnit res = (CompilationUnit)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in ClassPath.jrag at line 153


  private String relativeName;

    // Declared in ClassPath.jrag at line 154

  private String pathName;

    // Declared in ClassPath.jrag at line 155

  private boolean fromSource;

    // Declared in ClassPath.jrag at line 157


  public void setRelativeName(String name) {
    relativeName = name;
  }

    // Declared in ClassPath.jrag at line 160

  public void setPathName(String name) {
    pathName = name;
  }

    // Declared in ClassPath.jrag at line 163

  public void setFromSource(boolean value) {
    fromSource = value;
  }

    // Declared in ErrorCheck.jrag at line 65


  protected java.util.ArrayList errors = new java.util.ArrayList();

    // Declared in ErrorCheck.jrag at line 66

  protected java.util.ArrayList warnings = new java.util.ArrayList();

    // Declared in ErrorCheck.jrag at line 68


  public Collection parseErrors() { return parseErrors; }

    // Declared in ErrorCheck.jrag at line 69

  public void addParseError(Problem msg) { parseErrors.add(msg); }

    // Declared in ErrorCheck.jrag at line 70

  protected Collection parseErrors = new ArrayList();

    // Declared in ErrorCheck.jrag at line 228


  public void errorCheck(Collection collection) {
    collectErrors();
    collection.addAll(errors);
  }

    // Declared in ErrorCheck.jrag at line 232

  public void errorCheck(Collection err, Collection warn) {
    collectErrors();
    err.addAll(errors);
    warn.addAll(warnings);
  }

    // Declared in NameCheck.jrag at line 35


  public void nameCheck() {
    for(int i = 0; i < getNumImportDecl(); i++) {
      ImportDecl decl = getImportDecl(i);
      if(decl instanceof SingleTypeImportDecl) {
        if(localLookupType(decl.getAccess().type().name()).contains(decl.getAccess().type()))
          error("" + decl + " is conflicting with visible type");
      }
    }
  }

    // Declared in PrettyPrint.jadd at line 32

        
  public void toString(StringBuffer s) {
    try {
      if(!getPackageDecl().equals("")) {
        s.append("package " + getPackageDecl() + ";\n");
      }
      for(int i = 0; i < getNumImportDecl(); i++) {
        getImportDecl(i).toString(s);
      }
      for(int i = 0; i < getNumTypeDecl(); i++) {
        getTypeDecl(i).toString(s);
        s.append("\n");
      }
    } catch (NullPointerException e) {
      System.out.print("Error in compilation unit hosting " + getTypeDecl(0).typeName());
      throw e;
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 4

    public CompilationUnit() {
        super();

        setChild(new List(), 0);
        setChild(new List(), 1);

    }

    // Declared in java.ast at line 12


    // Declared in java.ast line 4
    public CompilationUnit(java.lang.String p0, List<ImportDecl> p1, List<TypeDecl> p2) {
        setPackageDecl(p0);
        setChild(p1, 0);
        setChild(p2, 1);
    }

    // Declared in java.ast at line 19


    // Declared in java.ast line 4
    public CompilationUnit(beaver.Symbol p0, List<ImportDecl> p1, List<TypeDecl> p2) {
        setPackageDecl(p0);
        setChild(p1, 0);
        setChild(p2, 1);
    }

    // Declared in java.ast at line 25


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 28

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 4
    protected java.lang.String tokenjava_lang_String_PackageDecl;

    // Declared in java.ast at line 3

    public void setPackageDecl(java.lang.String value) {
        tokenjava_lang_String_PackageDecl = value;
    }

    // Declared in java.ast at line 6

    public int PackageDeclstart;

    // Declared in java.ast at line 7

    public int PackageDeclend;

    // Declared in java.ast at line 8

    public void setPackageDecl(beaver.Symbol symbol) {
        if(symbol.value != null && !(symbol.value instanceof String))
          throw new UnsupportedOperationException("setPackageDecl is only valid for String lexemes");
        tokenjava_lang_String_PackageDecl = (String)symbol.value;
        PackageDeclstart = symbol.getStart();
        PackageDeclend = symbol.getEnd();
    }

    // Declared in java.ast at line 15

    public java.lang.String getPackageDecl() {
        return tokenjava_lang_String_PackageDecl != null ? tokenjava_lang_String_PackageDecl : "";
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 4
    public void setImportDeclList(List<ImportDecl> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumImportDecl() {
        return getImportDeclList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public ImportDecl getImportDecl(int i) {
        return (ImportDecl)getImportDeclList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addImportDecl(ImportDecl node) {
        List<ImportDecl> list = (parent == null || state == null) ? getImportDeclListNoTransform() : getImportDeclList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addImportDeclNoTransform(ImportDecl node) {
        List<ImportDecl> list = getImportDeclListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setImportDecl(ImportDecl node, int i) {
        List<ImportDecl> list = getImportDeclList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<ImportDecl> getImportDecls() {
        return getImportDeclList();
    }

    // Declared in java.ast at line 31

    public List<ImportDecl> getImportDeclsNoTransform() {
        return getImportDeclListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<ImportDecl> getImportDeclList() {
        List<ImportDecl> list = (List<ImportDecl>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<ImportDecl> getImportDeclListNoTransform() {
        return (List<ImportDecl>)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 4
    public void setTypeDeclList(List<TypeDecl> list) {
        setChild(list, 1);
    }

    // Declared in java.ast at line 6


    public int getNumTypeDecl() {
        return getTypeDeclList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public TypeDecl getTypeDecl(int i) {
        return (TypeDecl)getTypeDeclList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addTypeDecl(TypeDecl node) {
        List<TypeDecl> list = (parent == null || state == null) ? getTypeDeclListNoTransform() : getTypeDeclList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addTypeDeclNoTransform(TypeDecl node) {
        List<TypeDecl> list = getTypeDeclListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setTypeDecl(TypeDecl node, int i) {
        List<TypeDecl> list = getTypeDeclList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<TypeDecl> getTypeDecls() {
        return getTypeDeclList();
    }

    // Declared in java.ast at line 31

    public List<TypeDecl> getTypeDeclsNoTransform() {
        return getTypeDeclListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<TypeDecl> getTypeDeclList() {
        List<TypeDecl> list = (List<TypeDecl>)getChild(1);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<TypeDecl> getTypeDeclListNoTransform() {
        return (List<TypeDecl>)getChildNoTransform(1);
    }

    protected int relativeName_visited = -1;
    // Declared in ClassPath.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public String relativeName() {
        ASTNode$State state = state();
        if(relativeName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: relativeName in class: ");
        relativeName_visited = state().boundariesCrossed;
        String relativeName_value = relativeName_compute();
        relativeName_visited = -1;
        return relativeName_value;
    }

    private String relativeName_compute() {  return relativeName;  }

    protected int pathName_visited = -1;
    // Declared in ClassPath.jrag at line 28
 @SuppressWarnings({"unchecked", "cast"})     public String pathName() {
        ASTNode$State state = state();
        if(pathName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: pathName in class: ");
        pathName_visited = state().boundariesCrossed;
        String pathName_value = pathName_compute();
        pathName_visited = -1;
        return pathName_value;
    }

    private String pathName_compute() {  return pathName;  }

    protected int fromSource_visited = -1;
    // Declared in ClassPath.jrag at line 29
 @SuppressWarnings({"unchecked", "cast"})     public boolean fromSource() {
        ASTNode$State state = state();
        if(fromSource_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: fromSource in class: ");
        fromSource_visited = state().boundariesCrossed;
        boolean fromSource_value = fromSource_compute();
        fromSource_visited = -1;
        return fromSource_value;
    }

    private boolean fromSource_compute() {  return fromSource;  }

    protected java.util.Map localLookupType_String_visited;
    // Declared in LookupType.jrag at line 211
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localLookupType(String name) {
        Object _parameters = name;
if(localLookupType_String_visited == null) localLookupType_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(localLookupType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: localLookupType in class: ");
        localLookupType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet localLookupType_String_value = localLookupType_compute(name);
        localLookupType_String_visited.remove(_parameters);
        return localLookupType_String_value;
    }

    private SimpleSet localLookupType_compute(String name) {
    for(int i = 0; i < getNumTypeDecl(); i++)
      if(getTypeDecl(i).name().equals(name))
        return SimpleSet.emptySet.add(getTypeDecl(i));
    return SimpleSet.emptySet;
  }

    protected java.util.Map importedTypes_String_visited;
    // Declared in LookupType.jrag at line 218
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet importedTypes(String name) {
        Object _parameters = name;
if(importedTypes_String_visited == null) importedTypes_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(importedTypes_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: importedTypes in class: ");
        importedTypes_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet importedTypes_String_value = importedTypes_compute(name);
        importedTypes_String_visited.remove(_parameters);
        return importedTypes_String_value;
    }

    private SimpleSet importedTypes_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumImportDecl(); i++)
      if(!getImportDecl(i).isOnDemand())
        for(Iterator iter = getImportDecl(i).importedTypes(name).iterator(); iter.hasNext(); )
          set = set.add(iter.next());
    return set;
  }

    protected java.util.Map importedTypesOnDemand_String_visited;
    // Declared in LookupType.jrag at line 226
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet importedTypesOnDemand(String name) {
        Object _parameters = name;
if(importedTypesOnDemand_String_visited == null) importedTypesOnDemand_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(importedTypesOnDemand_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: importedTypesOnDemand in class: ");
        importedTypesOnDemand_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet importedTypesOnDemand_String_value = importedTypesOnDemand_compute(name);
        importedTypesOnDemand_String_visited.remove(_parameters);
        return importedTypesOnDemand_String_value;
    }

    private SimpleSet importedTypesOnDemand_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumImportDecl(); i++)
      if(getImportDecl(i).isOnDemand())
        for(Iterator iter = getImportDecl(i).importedTypes(name).iterator(); iter.hasNext(); )
          set = set.add(iter.next());
    return set;
  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 800
 @SuppressWarnings({"unchecked", "cast"})     public String dumpString() {
        ASTNode$State state = state();
        if(dumpString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: dumpString in class: ");
        dumpString_visited = state().boundariesCrossed;
        String dumpString_value = dumpString_compute();
        dumpString_visited = -1;
        return dumpString_value;
    }

    private String dumpString_compute() {  return getClass().getName() + " [" + getPackageDecl() + "]";  }

    protected int packageName_visited = -1;
    protected boolean packageName_computed = false;
    protected String packageName_value;
    // Declared in QualifiedNames.jrag at line 92
 @SuppressWarnings({"unchecked", "cast"})     public String packageName() {
        if(packageName_computed) {
            return packageName_value;
        }
        ASTNode$State state = state();
        if(packageName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: packageName in class: ");
        packageName_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        packageName_value = packageName_compute();
        if(isFinal && num == state().boundariesCrossed)
            packageName_computed = true;
        packageName_visited = -1;
        return packageName_value;
    }

    private String packageName_compute() {return getPackageDecl();}

    protected java.util.Map lookupType_String_String_visited;
    // Declared in LookupType.jrag at line 99
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
    protected java.util.Map lookupType_String_values;
    // Declared in LookupType.jrag at line 171
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

    // Declared in ClassPath.jrag at line 32
    public CompilationUnit Define_CompilationUnit_compilationUnit(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_CompilationUnit_compilationUnit(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 51
    public boolean Define_boolean_isIncOrDec(ASTNode caller, ASTNode child) {
        if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isIncOrDec(this, caller);
    }

    // Declared in ExceptionHandling.jrag at line 117
    public boolean Define_boolean_handlesException(ASTNode caller, ASTNode child, TypeDecl exceptionType) {
        if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return !exceptionType.isUncheckedException();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_handlesException(this, caller, exceptionType);
    }

    // Declared in LookupType.jrag at line 267
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return lookupType(name);
        }
        if(true) { 
   int childIndex = this.getIndexOfChild(caller);
{
    // locally declared types in compilation unit
    SimpleSet set = localLookupType(name);
    if(!set.isEmpty()) return set;

    // imported types
    set = importedTypes(name);
    if(!set.isEmpty()) return set;

    // types in the same package
    TypeDecl result = lookupType(packageName(), name);
    if(result != null && result.accessibleFromPackage(packageName())) 
      return SimpleSet.emptySet.add(result);
    
    // types imported on demand
    set = importedTypesOnDemand(name);
    if(!set.isEmpty()) return set;
    
    // include primitive types
    result = lookupType(PRIMITIVE_PACKAGE_NAME, name);
    if(result != null) return SimpleSet.emptySet.add(result);
    
    // 7.5.5 Automatic Imports
    result = lookupType("java.lang", name);
    if(result != null && result.accessibleFromPackage(packageName()))
      return SimpleSet.emptySet.add(result);
    return lookupType(name);
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in NameCheck.jrag at line 27
    public SimpleSet Define_SimpleSet_allImportedTypes(ASTNode caller, ASTNode child, String name) {
        if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return importedTypes(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_allImportedTypes(this, caller, name);
    }

    // Declared in QualifiedNames.jrag at line 90
    public String Define_String_packageName(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return packageName();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_packageName(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 69
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.PACKAGE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 493
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return null;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 520
    public boolean Define_boolean_isNestedType(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isNestedType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 530
    public boolean Define_boolean_isMemberType(ASTNode caller, ASTNode child) {
        if(caller == getTypeDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isMemberType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 542
    public boolean Define_boolean_isLocalClass(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isLocalClass(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 564
    public String Define_String_hostPackage(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return packageName();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_hostPackage(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 580
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getImportDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return null;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
