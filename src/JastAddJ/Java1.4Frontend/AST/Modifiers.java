
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;



public class Modifiers extends ASTNode<ASTNode> implements Cloneable {
    public void flushCache() {
        super.flushCache();
        isPublic_visited = -1;
        isPublic_computed = false;
        isPrivate_visited = -1;
        isPrivate_computed = false;
        isProtected_visited = -1;
        isProtected_computed = false;
        isStatic_visited = -1;
        isStatic_computed = false;
        isFinal_visited = -1;
        isFinal_computed = false;
        isAbstract_visited = -1;
        isAbstract_computed = false;
        isVolatile_visited = -1;
        isVolatile_computed = false;
        isTransient_visited = -1;
        isTransient_computed = false;
        isStrictfp_visited = -1;
        isStrictfp_computed = false;
        isSynchronized_visited = -1;
        isSynchronized_computed = false;
        isNative_visited = -1;
        isNative_computed = false;
        isSynthetic_visited = -1;
        isSynthetic_computed = false;
        numProtectionModifiers_visited = -1;
        numCompletenessModifiers_visited = -1;
        numModifier_String_visited = null;
        numModifier_String_values = null;
        hostType_visited = -1;
        mayBePublic_visited = -1;
        mayBePrivate_visited = -1;
        mayBeProtected_visited = -1;
        mayBeStatic_visited = -1;
        mayBeFinal_visited = -1;
        mayBeAbstract_visited = -1;
        mayBeVolatile_visited = -1;
        mayBeTransient_visited = -1;
        mayBeStrictfp_visited = -1;
        mayBeSynchronized_visited = -1;
        mayBeNative_visited = -1;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public Modifiers clone() throws CloneNotSupportedException {
        Modifiers node = (Modifiers)super.clone();
        node.isPublic_visited = -1;
        node.isPublic_computed = false;
        node.isPrivate_visited = -1;
        node.isPrivate_computed = false;
        node.isProtected_visited = -1;
        node.isProtected_computed = false;
        node.isStatic_visited = -1;
        node.isStatic_computed = false;
        node.isFinal_visited = -1;
        node.isFinal_computed = false;
        node.isAbstract_visited = -1;
        node.isAbstract_computed = false;
        node.isVolatile_visited = -1;
        node.isVolatile_computed = false;
        node.isTransient_visited = -1;
        node.isTransient_computed = false;
        node.isStrictfp_visited = -1;
        node.isStrictfp_computed = false;
        node.isSynchronized_visited = -1;
        node.isSynchronized_computed = false;
        node.isNative_visited = -1;
        node.isNative_computed = false;
        node.isSynthetic_visited = -1;
        node.isSynthetic_computed = false;
        node.numProtectionModifiers_visited = -1;
        node.numCompletenessModifiers_visited = -1;
        node.numModifier_String_visited = null;
        node.numModifier_String_values = null;
        node.hostType_visited = -1;
        node.mayBePublic_visited = -1;
        node.mayBePrivate_visited = -1;
        node.mayBeProtected_visited = -1;
        node.mayBeStatic_visited = -1;
        node.mayBeFinal_visited = -1;
        node.mayBeAbstract_visited = -1;
        node.mayBeVolatile_visited = -1;
        node.mayBeTransient_visited = -1;
        node.mayBeStrictfp_visited = -1;
        node.mayBeSynchronized_visited = -1;
        node.mayBeNative_visited = -1;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Modifiers copy() {
      try {
          Modifiers node = (Modifiers)clone();
          if(children != null) node.children = (ASTNode[])children.clone();
          return node;
      } catch (CloneNotSupportedException e) {
      }
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
      return null;
    }
     @SuppressWarnings({"unchecked", "cast"})  public Modifiers fullCopy() {
        Modifiers res = (Modifiers)copy();
        for(int i = 0; i < getNumChildNoTransform(); i++) {
          ASTNode node = getChildNoTransform(i);
          if(node != null) node = node.fullCopy();
          res.setChild(node, i);
        }
        return res;
    }
    // Declared in Modifiers.jrag at line 312


  // 8.4.3
  public void checkModifiers() {
    super.checkModifiers();
    if(numProtectionModifiers() > 1)
      error("only one public, protected, private allowed");
    if(numModifier("static") > 1)
      error("only one static allowed");
    // 8.4.3.1
    // 8.4.3.2
    // 8.1.1.2
    if(numCompletenessModifiers() > 1)
      error("only one of final, abstract, volatile allowed");
    if(numModifier("synchronized") > 1)
      error("only one synchronized allowed");
    if(numModifier("transient") > 1)
      error("only one transient allowed");
    if(numModifier("native") > 1)
      error("only one native allowed");
    if(numModifier("strictfp") > 1)
      error("only one strictfp allowed");

    if(isPublic() && !mayBePublic())
      error("modifier public not allowed in this context");
    if(isPrivate() && !mayBePrivate())
      error("modifier private not allowed in this context");
    if(isProtected() && !mayBeProtected())
      error("modifier protected not allowed in this context");
    if(isStatic() && !mayBeStatic())
      error("modifier static not allowed in this context");
    if(isFinal() && !mayBeFinal())
      error("modifier final not allowed in this context");
    if(isAbstract() && !mayBeAbstract())
      error("modifier abstract not allowed in this context");
    if(isVolatile() && !mayBeVolatile())
      error("modifier volatile not allowed in this context");
    if(isTransient() && !mayBeTransient())
      error("modifier transient not allowed in this context");
    if(isStrictfp() && !mayBeStrictfp())
      error("modifier strictfp not allowed in this context");
    if(isSynchronized() && !mayBeSynchronized())
      error("modifier synchronized not allowed in this context");
    if(isNative() && !mayBeNative())
      error("modifier native not allowed in this context");
  }

    // Declared in PrettyPrint.jadd at line 434


  public void toString(StringBuffer s) {
    for(int i = 0; i < getNumModifier(); i++) {
      getModifier(i).toString(s);
      s.append(" ");
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 193

    public Modifiers() {
        super();

        setChild(new List(), 0);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 193
    public Modifiers(List<Modifier> p0) {
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
    // Declared in java.ast line 193
    public void setModifierList(List<Modifier> list) {
        setChild(list, 0);
    }

    // Declared in java.ast at line 6


    public int getNumModifier() {
        return getModifierList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public Modifier getModifier(int i) {
        return (Modifier)getModifierList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addModifier(Modifier node) {
        List<Modifier> list = (parent == null || state == null) ? getModifierListNoTransform() : getModifierList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addModifierNoTransform(Modifier node) {
        List<Modifier> list = getModifierListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setModifier(Modifier node, int i) {
        List<Modifier> list = getModifierList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<Modifier> getModifiers() {
        return getModifierList();
    }

    // Declared in java.ast at line 31

    public List<Modifier> getModifiersNoTransform() {
        return getModifierListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<Modifier> getModifierList() {
        List<Modifier> list = (List<Modifier>)getChild(0);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<Modifier> getModifierListNoTransform() {
        return (List<Modifier>)getChildNoTransform(0);
    }

    protected int isPublic_visited = -1;
    protected boolean isPublic_computed = false;
    protected boolean isPublic_value;
    // Declared in Modifiers.jrag at line 370
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPublic() {
        if(isPublic_computed) {
            return isPublic_value;
        }
        ASTNode$State state = state();
        if(isPublic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPublic in class: ");
        isPublic_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isPublic_value = isPublic_compute();
        if(isFinal && num == state().boundariesCrossed)
            isPublic_computed = true;
        isPublic_visited = -1;
        return isPublic_value;
    }

    private boolean isPublic_compute() {  return numModifier("public") != 0;  }

    protected int isPrivate_visited = -1;
    protected boolean isPrivate_computed = false;
    protected boolean isPrivate_value;
    // Declared in Modifiers.jrag at line 371
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrivate() {
        if(isPrivate_computed) {
            return isPrivate_value;
        }
        ASTNode$State state = state();
        if(isPrivate_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrivate in class: ");
        isPrivate_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isPrivate_value = isPrivate_compute();
        if(isFinal && num == state().boundariesCrossed)
            isPrivate_computed = true;
        isPrivate_visited = -1;
        return isPrivate_value;
    }

    private boolean isPrivate_compute() {  return numModifier("private") != 0;  }

    protected int isProtected_visited = -1;
    protected boolean isProtected_computed = false;
    protected boolean isProtected_value;
    // Declared in Modifiers.jrag at line 372
 @SuppressWarnings({"unchecked", "cast"})     public boolean isProtected() {
        if(isProtected_computed) {
            return isProtected_value;
        }
        ASTNode$State state = state();
        if(isProtected_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isProtected in class: ");
        isProtected_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isProtected_value = isProtected_compute();
        if(isFinal && num == state().boundariesCrossed)
            isProtected_computed = true;
        isProtected_visited = -1;
        return isProtected_value;
    }

    private boolean isProtected_compute() {  return numModifier("protected") != 0;  }

    protected int isStatic_visited = -1;
    protected boolean isStatic_computed = false;
    protected boolean isStatic_value;
    // Declared in Modifiers.jrag at line 373
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStatic() {
        if(isStatic_computed) {
            return isStatic_value;
        }
        ASTNode$State state = state();
        if(isStatic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isStatic in class: ");
        isStatic_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isStatic_value = isStatic_compute();
        if(isFinal && num == state().boundariesCrossed)
            isStatic_computed = true;
        isStatic_visited = -1;
        return isStatic_value;
    }

    private boolean isStatic_compute() {  return numModifier("static") != 0;  }

    protected int isFinal_visited = -1;
    protected boolean isFinal_computed = false;
    protected boolean isFinal_value;
    // Declared in Modifiers.jrag at line 374
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        if(isFinal_computed) {
            return isFinal_value;
        }
        ASTNode$State state = state();
        if(isFinal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFinal in class: ");
        isFinal_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isFinal_value = isFinal_compute();
        if(isFinal && num == state().boundariesCrossed)
            isFinal_computed = true;
        isFinal_visited = -1;
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return numModifier("final") != 0;  }

    protected int isAbstract_visited = -1;
    protected boolean isAbstract_computed = false;
    protected boolean isAbstract_value;
    // Declared in Modifiers.jrag at line 375
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAbstract() {
        if(isAbstract_computed) {
            return isAbstract_value;
        }
        ASTNode$State state = state();
        if(isAbstract_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isAbstract in class: ");
        isAbstract_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isAbstract_value = isAbstract_compute();
        if(isFinal && num == state().boundariesCrossed)
            isAbstract_computed = true;
        isAbstract_visited = -1;
        return isAbstract_value;
    }

    private boolean isAbstract_compute() {  return numModifier("abstract") != 0;  }

    protected int isVolatile_visited = -1;
    protected boolean isVolatile_computed = false;
    protected boolean isVolatile_value;
    // Declared in Modifiers.jrag at line 376
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVolatile() {
        if(isVolatile_computed) {
            return isVolatile_value;
        }
        ASTNode$State state = state();
        if(isVolatile_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVolatile in class: ");
        isVolatile_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isVolatile_value = isVolatile_compute();
        if(isFinal && num == state().boundariesCrossed)
            isVolatile_computed = true;
        isVolatile_visited = -1;
        return isVolatile_value;
    }

    private boolean isVolatile_compute() {  return numModifier("volatile") != 0;  }

    protected int isTransient_visited = -1;
    protected boolean isTransient_computed = false;
    protected boolean isTransient_value;
    // Declared in Modifiers.jrag at line 377
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTransient() {
        if(isTransient_computed) {
            return isTransient_value;
        }
        ASTNode$State state = state();
        if(isTransient_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTransient in class: ");
        isTransient_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isTransient_value = isTransient_compute();
        if(isFinal && num == state().boundariesCrossed)
            isTransient_computed = true;
        isTransient_visited = -1;
        return isTransient_value;
    }

    private boolean isTransient_compute() {  return numModifier("transient") != 0;  }

    protected int isStrictfp_visited = -1;
    protected boolean isStrictfp_computed = false;
    protected boolean isStrictfp_value;
    // Declared in Modifiers.jrag at line 378
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStrictfp() {
        if(isStrictfp_computed) {
            return isStrictfp_value;
        }
        ASTNode$State state = state();
        if(isStrictfp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isStrictfp in class: ");
        isStrictfp_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isStrictfp_value = isStrictfp_compute();
        if(isFinal && num == state().boundariesCrossed)
            isStrictfp_computed = true;
        isStrictfp_visited = -1;
        return isStrictfp_value;
    }

    private boolean isStrictfp_compute() {  return numModifier("strictfp") != 0;  }

    protected int isSynchronized_visited = -1;
    protected boolean isSynchronized_computed = false;
    protected boolean isSynchronized_value;
    // Declared in Modifiers.jrag at line 379
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynchronized() {
        if(isSynchronized_computed) {
            return isSynchronized_value;
        }
        ASTNode$State state = state();
        if(isSynchronized_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSynchronized in class: ");
        isSynchronized_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isSynchronized_value = isSynchronized_compute();
        if(isFinal && num == state().boundariesCrossed)
            isSynchronized_computed = true;
        isSynchronized_visited = -1;
        return isSynchronized_value;
    }

    private boolean isSynchronized_compute() {  return numModifier("synchronized") != 0;  }

    protected int isNative_visited = -1;
    protected boolean isNative_computed = false;
    protected boolean isNative_value;
    // Declared in Modifiers.jrag at line 380
 @SuppressWarnings({"unchecked", "cast"})     public boolean isNative() {
        if(isNative_computed) {
            return isNative_value;
        }
        ASTNode$State state = state();
        if(isNative_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isNative in class: ");
        isNative_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isNative_value = isNative_compute();
        if(isFinal && num == state().boundariesCrossed)
            isNative_computed = true;
        isNative_visited = -1;
        return isNative_value;
    }

    private boolean isNative_compute() {  return numModifier("native") != 0;  }

    protected int isSynthetic_visited = -1;
    protected boolean isSynthetic_computed = false;
    protected boolean isSynthetic_value;
    // Declared in Modifiers.jrag at line 382
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        if(isSynthetic_computed) {
            return isSynthetic_value;
        }
        ASTNode$State state = state();
        if(isSynthetic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSynthetic in class: ");
        isSynthetic_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isSynthetic_value = isSynthetic_compute();
        if(isFinal && num == state().boundariesCrossed)
            isSynthetic_computed = true;
        isSynthetic_visited = -1;
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return numModifier("synthetic") != 0;  }

    protected int numProtectionModifiers_visited = -1;
    // Declared in Modifiers.jrag at line 384
 @SuppressWarnings({"unchecked", "cast"})     public int numProtectionModifiers() {
        ASTNode$State state = state();
        if(numProtectionModifiers_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: numProtectionModifiers in class: ");
        numProtectionModifiers_visited = state().boundariesCrossed;
        int numProtectionModifiers_value = numProtectionModifiers_compute();
        numProtectionModifiers_visited = -1;
        return numProtectionModifiers_value;
    }

    private int numProtectionModifiers_compute() {  return numModifier("public") + numModifier("protected") + numModifier("private");  }

    protected int numCompletenessModifiers_visited = -1;
    // Declared in Modifiers.jrag at line 387
 @SuppressWarnings({"unchecked", "cast"})     public int numCompletenessModifiers() {
        ASTNode$State state = state();
        if(numCompletenessModifiers_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: numCompletenessModifiers in class: ");
        numCompletenessModifiers_visited = state().boundariesCrossed;
        int numCompletenessModifiers_value = numCompletenessModifiers_compute();
        numCompletenessModifiers_visited = -1;
        return numCompletenessModifiers_value;
    }

    private int numCompletenessModifiers_compute() {  return numModifier("abstract") + numModifier("final") + numModifier("volatile");  }

    protected java.util.Map numModifier_String_visited;
    protected java.util.Map numModifier_String_values;
    // Declared in Modifiers.jrag at line 390
 @SuppressWarnings({"unchecked", "cast"})     public int numModifier(String name) {
        Object _parameters = name;
if(numModifier_String_visited == null) numModifier_String_visited = new java.util.HashMap(4);
if(numModifier_String_values == null) numModifier_String_values = new java.util.HashMap(4);
        if(numModifier_String_values.containsKey(_parameters)) {
            return ((Integer)numModifier_String_values.get(_parameters)).intValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(numModifier_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: numModifier in class: ");
        numModifier_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        int numModifier_String_value = numModifier_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            numModifier_String_values.put(_parameters, Integer.valueOf(numModifier_String_value));
        numModifier_String_visited.remove(_parameters);
        return numModifier_String_value;
    }

    private int numModifier_compute(String name) {
    int n = 0;
    for(int i = 0; i < getNumModifier(); i++) {
      String s = getModifier(i).getID();
      if(s.equals(name))
        n++;
    }
    return n;
  }

    protected int hostType_visited = -1;
    // Declared in Modifiers.jrag at line 356
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

    protected int mayBePublic_visited = -1;
    // Declared in Modifiers.jrag at line 358
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBePublic() {
        ASTNode$State state = state();
        if(mayBePublic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBePublic in class: ");
        mayBePublic_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBePublic_value = getParent().Define_boolean_mayBePublic(this, null);
        mayBePublic_visited = -1;
        return mayBePublic_value;
    }

    protected int mayBePrivate_visited = -1;
    // Declared in Modifiers.jrag at line 359
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBePrivate() {
        ASTNode$State state = state();
        if(mayBePrivate_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBePrivate in class: ");
        mayBePrivate_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBePrivate_value = getParent().Define_boolean_mayBePrivate(this, null);
        mayBePrivate_visited = -1;
        return mayBePrivate_value;
    }

    protected int mayBeProtected_visited = -1;
    // Declared in Modifiers.jrag at line 360
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeProtected() {
        ASTNode$State state = state();
        if(mayBeProtected_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeProtected in class: ");
        mayBeProtected_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeProtected_value = getParent().Define_boolean_mayBeProtected(this, null);
        mayBeProtected_visited = -1;
        return mayBeProtected_value;
    }

    protected int mayBeStatic_visited = -1;
    // Declared in Modifiers.jrag at line 361
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeStatic() {
        ASTNode$State state = state();
        if(mayBeStatic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeStatic in class: ");
        mayBeStatic_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeStatic_value = getParent().Define_boolean_mayBeStatic(this, null);
        mayBeStatic_visited = -1;
        return mayBeStatic_value;
    }

    protected int mayBeFinal_visited = -1;
    // Declared in Modifiers.jrag at line 362
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeFinal() {
        ASTNode$State state = state();
        if(mayBeFinal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeFinal in class: ");
        mayBeFinal_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeFinal_value = getParent().Define_boolean_mayBeFinal(this, null);
        mayBeFinal_visited = -1;
        return mayBeFinal_value;
    }

    protected int mayBeAbstract_visited = -1;
    // Declared in Modifiers.jrag at line 363
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeAbstract() {
        ASTNode$State state = state();
        if(mayBeAbstract_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeAbstract in class: ");
        mayBeAbstract_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeAbstract_value = getParent().Define_boolean_mayBeAbstract(this, null);
        mayBeAbstract_visited = -1;
        return mayBeAbstract_value;
    }

    protected int mayBeVolatile_visited = -1;
    // Declared in Modifiers.jrag at line 364
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeVolatile() {
        ASTNode$State state = state();
        if(mayBeVolatile_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeVolatile in class: ");
        mayBeVolatile_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeVolatile_value = getParent().Define_boolean_mayBeVolatile(this, null);
        mayBeVolatile_visited = -1;
        return mayBeVolatile_value;
    }

    protected int mayBeTransient_visited = -1;
    // Declared in Modifiers.jrag at line 365
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeTransient() {
        ASTNode$State state = state();
        if(mayBeTransient_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeTransient in class: ");
        mayBeTransient_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeTransient_value = getParent().Define_boolean_mayBeTransient(this, null);
        mayBeTransient_visited = -1;
        return mayBeTransient_value;
    }

    protected int mayBeStrictfp_visited = -1;
    // Declared in Modifiers.jrag at line 366
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeStrictfp() {
        ASTNode$State state = state();
        if(mayBeStrictfp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeStrictfp in class: ");
        mayBeStrictfp_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeStrictfp_value = getParent().Define_boolean_mayBeStrictfp(this, null);
        mayBeStrictfp_visited = -1;
        return mayBeStrictfp_value;
    }

    protected int mayBeSynchronized_visited = -1;
    // Declared in Modifiers.jrag at line 367
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeSynchronized() {
        ASTNode$State state = state();
        if(mayBeSynchronized_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeSynchronized in class: ");
        mayBeSynchronized_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeSynchronized_value = getParent().Define_boolean_mayBeSynchronized(this, null);
        mayBeSynchronized_visited = -1;
        return mayBeSynchronized_value;
    }

    protected int mayBeNative_visited = -1;
    // Declared in Modifiers.jrag at line 368
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayBeNative() {
        ASTNode$State state = state();
        if(mayBeNative_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: mayBeNative in class: ");
        mayBeNative_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean mayBeNative_value = getParent().Define_boolean_mayBeNative(this, null);
        mayBeNative_visited = -1;
        return mayBeNative_value;
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
