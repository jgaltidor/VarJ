
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;


public class TypeImportOnDemandDecl extends ImportDecl implements Cloneable {
    public void flushCache() {
        super.flushCache();
        importedTypes_String_visited = null;
        importedTypes_String_values = null;
        isOnDemand_visited = -1;
        lookupType_String_String_visited = null;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeImportOnDemandDecl clone() throws CloneNotSupportedException {
        TypeImportOnDemandDecl node = (TypeImportOnDemandDecl)super.clone();
        node.importedTypes_String_visited = null;
        node.importedTypes_String_values = null;
        node.isOnDemand_visited = -1;
        node.lookupType_String_String_visited = null;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeImportOnDemandDecl copy() {
      try {
          TypeImportOnDemandDecl node = (TypeImportOnDemandDecl)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeImportOnDemandDecl fullCopy() {
        TypeImportOnDemandDecl res = (TypeImportOnDemandDecl)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in NameCheck.jrag at line 30


  public void nameCheck() {
    if(getAccess().lastAccess().isTypeAccess() && !getAccess().type().typeName().equals(typeName()))
      error("On demand type import " + typeName() + ".* is not the canonical name of type " + getAccess().type().typeName());
  }

    // Declared in PrettyPrint.jadd at line 56


  public void toString(StringBuffer s) {
    s.append("import ");
    getAccess().toString(s);
    s.append(".*;\n");
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 9

    public TypeImportOnDemandDecl() {
        super();


    }

    // Declared in java.ast at line 10


    // Declared in java.ast line 9
    public TypeImportOnDemandDecl(Access p0) {
        setChild(p0, 0);
    }

    // Declared in java.ast at line 14


  protected int numChildren() {
    return 1;
  }

    // Declared in java.ast at line 17

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 7
    public void setAccess(Access node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Access getAccess() {
        return (Access)getChild(0);
    }

    // Declared in java.ast at line 9


    public Access getAccessNoTransform() {
        return (Access)getChildNoTransform(0);
    }

    // Declared in LookupType.jrag at line 241
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet importedTypes(String name) {
        Object _parameters = name;
if(importedTypes_String_visited == null) importedTypes_String_visited = new java.util.HashMap(4);
if(importedTypes_String_values == null) importedTypes_String_values = new java.util.HashMap(4);
        if(importedTypes_String_values.containsKey(_parameters)) {
            return (SimpleSet)importedTypes_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(importedTypes_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: importedTypes in class: ");
        importedTypes_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet importedTypes_String_value = importedTypes_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            importedTypes_String_values.put(_parameters, importedTypes_String_value);
        importedTypes_String_visited.remove(_parameters);
        return importedTypes_String_value;
    }

    private SimpleSet importedTypes_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    if(getAccess() instanceof PackageAccess) {
      String packageName = ((PackageAccess)getAccess()).getPackage();
      TypeDecl typeDecl = lookupType(packageName, name);
      if(typeDecl != null && typeDecl.accessibleFromPackage(packageName()) &&
         typeDecl.typeName().equals(packageName + "." + name)) // canonical names match
        set = set.add(typeDecl);
    }
    else {
      for(Iterator iter = getAccess().type().memberTypes(name).iterator(); iter.hasNext(); ) {
        TypeDecl decl = (TypeDecl)iter.next();
        if(decl.accessibleFromPackage(packageName()) &&
           decl.typeName().equals(getAccess().typeName() + "." + name)) // canonical names match
          set = set.add(decl);
      }
    }
    return set;
  }

    protected int isOnDemand_visited = -1;
    // Declared in LookupType.jrag at line 264
 @SuppressWarnings({"unchecked", "cast"})     public boolean isOnDemand() {
        ASTNode$State state = state();
        if(isOnDemand_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isOnDemand in class: ");
        isOnDemand_visited = state().boundariesCrossed;
        boolean isOnDemand_value = isOnDemand_compute();
        isOnDemand_visited = -1;
        return isOnDemand_value;
    }

    private boolean isOnDemand_compute() {  return true;  }

    protected java.util.Map lookupType_String_String_visited;
    // Declared in LookupType.jrag at line 260
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

    // Declared in SyntacticClassification.jrag at line 107
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getAccessNoTransform()) {
            return NameType.PACKAGE_OR_TYPE_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
