
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;

 

public abstract class TypeDecl extends ASTNode<ASTNode> implements Cloneable, SimpleSet, Iterator, VariableScope {
    public void flushCache() {
        super.flushCache();
        accessibleFromPackage_String_visited = null;
        accessibleFromPackage_String_values = null;
        accessibleFromExtend_TypeDecl_visited = null;
        accessibleFromExtend_TypeDecl_values = null;
        accessibleFrom_TypeDecl_visited = null;
        accessibleFrom_TypeDecl_values = null;
        dimension_visited = -1;
        dimension_computed = false;
        elementType_visited = -1;
        elementType_computed = false;
        elementType_value = null;
        arrayType_visited = -1;
        arrayType_computed = false;
        arrayType_value = null;
        cast_Constant_visited = null;
        plus_Constant_visited = null;
        minus_Constant_visited = null;
        bitNot_Constant_visited = null;
        mul_Constant_Constant_visited = null;
        div_Constant_Constant_visited = null;
        mod_Constant_Constant_visited = null;
        add_Constant_Constant_visited = null;
        sub_Constant_Constant_visited = null;
        lshift_Constant_Constant_visited = null;
        rshift_Constant_Constant_visited = null;
        urshift_Constant_Constant_visited = null;
        andBitwise_Constant_Constant_visited = null;
        xorBitwise_Constant_Constant_visited = null;
        orBitwise_Constant_Constant_visited = null;
        questionColon_Constant_Constant_Constant_visited = null;
        eqIsTrue_Expr_Expr_visited = null;
        ltIsTrue_Expr_Expr_visited = null;
        leIsTrue_Expr_Expr_visited = null;
        size_visited = -1;
        isEmpty_visited = -1;
        contains_Object_visited = null;
        isException_visited = -1;
        isException_computed = false;
        isCheckedException_visited = -1;
        isCheckedException_computed = false;
        isUncheckedException_visited = -1;
        isUncheckedException_computed = false;
        mayCatch_TypeDecl_visited = null;
        mayCatch_TypeDecl_values = null;
        lookupSuperConstructor_visited = -1;
        constructors_visited = -1;
        constructors_computed = false;
        constructors_value = null;
        unqualifiedLookupMethod_String_visited = null;
        unqualifiedLookupMethod_String_values = null;
        memberMethods_String_visited = null;
        methodsNameMap_visited = -1;
        methodsNameMap_computed = false;
        methodsNameMap_value = null;
        localMethodsSignature_String_visited = null;
        localMethodsSignatureMap_visited = -1;
        localMethodsSignatureMap_computed = false;
        localMethodsSignatureMap_value = null;
        methodsSignature_String_visited = null;
        methodsSignatureMap_visited = -1;
        methodsSignatureMap_computed = false;
        methodsSignatureMap_value = null;
        ancestorMethods_String_visited = null;
        ancestorMethods_String_values = null;
        hasType_String_visited = null;
        localTypeDecls_String_visited = null;
        localTypeDecls_String_values = null;
        memberTypes_String_visited = null;
        memberTypes_String_values = null;
        localFields_String_visited = null;
        localFields_String_values = null;
        localFieldsMap_visited = -1;
        localFieldsMap_computed = false;
        localFieldsMap_value = null;
        memberFieldsMap_visited = -1;
        memberFieldsMap_computed = false;
        memberFieldsMap_value = null;
        memberFields_String_visited = null;
        memberFields_String_values = null;
        hasAbstract_visited = -1;
        hasAbstract_computed = false;
        unimplementedMethods_visited = -1;
        unimplementedMethods_computed = false;
        unimplementedMethods_value = null;
        isPublic_visited = -1;
        isPublic_computed = false;
        isPrivate_visited = -1;
        isProtected_visited = -1;
        isAbstract_visited = -1;
        isStatic_visited = -1;
        isStatic_computed = false;
        isFinal_visited = -1;
        isStrictfp_visited = -1;
        isSynthetic_visited = -1;
        hasEnclosingTypeDecl_String_visited = null;
        assignableToInt_visited = -1;
        addsIndentationLevel_visited = -1;
        dumpString_visited = -1;
        name_visited = -1;
        fullName_visited = -1;
        fullName_computed = false;
        fullName_value = null;
        typeName_visited = -1;
        typeName_computed = false;
        typeName_value = null;
        identityConversionTo_TypeDecl_visited = null;
        wideningConversionTo_TypeDecl_visited = null;
        narrowingConversionTo_TypeDecl_visited = null;
        narrowingConversionTo_TypeDecl_values = null;
        stringConversion_visited = -1;
        assignConversionTo_TypeDecl_Expr_visited = null;
        methodInvocationConversionTo_TypeDecl_visited = null;
        methodInvocationConversionTo_TypeDecl_values = null;
        castingConversionTo_TypeDecl_visited = null;
        castingConversionTo_TypeDecl_values = null;
        unaryNumericPromotion_visited = -1;
        binaryNumericPromotion_TypeDecl_visited = null;
        isReferenceType_visited = -1;
        isPrimitiveType_visited = -1;
        isNumericType_visited = -1;
        isIntegralType_visited = -1;
        isBoolean_visited = -1;
        isByte_visited = -1;
        isChar_visited = -1;
        isShort_visited = -1;
        isInt_visited = -1;
        isFloat_visited = -1;
        isLong_visited = -1;
        isDouble_visited = -1;
        isVoid_visited = -1;
        isNull_visited = -1;
        isClassDecl_visited = -1;
        isInterfaceDecl_visited = -1;
        isArrayDecl_visited = -1;
        isPrimitive_visited = -1;
        isString_visited = -1;
        isString_computed = false;
        isObject_visited = -1;
        isObject_computed = false;
        isUnknown_visited = -1;
        instanceOf_TypeDecl_visited = null;
        instanceOf_TypeDecl_values = null;
        isSupertypeOfClassDecl_ClassDecl_visited = null;
        isSupertypeOfInterfaceDecl_InterfaceDecl_visited = null;
        isSupertypeOfArrayDecl_ArrayDecl_visited = null;
        isSupertypeOfPrimitiveType_PrimitiveType_visited = null;
        isSupertypeOfNullType_NullType_visited = null;
        isSupertypeOfVoidType_VoidType_visited = null;
        topLevelType_visited = -1;
        isTopLevelType_visited = -1;
        isInnerClass_visited = -1;
        isInnerType_visited = -1;
        isInnerTypeOf_TypeDecl_visited = null;
        withinBodyThatSubclasses_TypeDecl_visited = null;
        encloses_TypeDecl_visited = null;
        enclosedBy_TypeDecl_visited = null;
        hostType_visited = -1;
        isCircular_visited = -1;
        isCircular_computed = false;
        isCircular_initialized = false;
        componentType_visited = -1;
        componentType_computed = false;
        componentType_value = null;
        typeCloneable_visited = -1;
        typeSerializable_visited = -1;
        compilationUnit_visited = -1;
        isDAbefore_Variable_visited = null;
        isDAbefore_Variable_values = null;
        isDUbefore_Variable_visited = null;
        isDUbefore_Variable_values = null;
        typeException_visited = -1;
        typeException_computed = false;
        typeException_value = null;
        typeRuntimeException_visited = -1;
        typeRuntimeException_computed = false;
        typeRuntimeException_value = null;
        typeError_visited = -1;
        typeError_computed = false;
        typeError_value = null;
        lookupMethod_String_visited = null;
        lookupMethod_String_values = null;
        typeInt_visited = -1;
        typeObject_visited = -1;
        typeObject_computed = false;
        typeObject_value = null;
        lookupType_String_String_visited = null;
        lookupType_String_visited = null;
        lookupType_String_values = null;
        lookupVariable_String_visited = null;
        lookupVariable_String_values = null;
        hasPackage_String_visited = null;
        enclosingBlock_visited = -1;
        packageName_visited = -1;
        packageName_computed = false;
        packageName_value = null;
        isAnonymous_visited = -1;
        isAnonymous_computed = false;
        enclosingType_visited = -1;
        enclosingBodyDecl_visited = -1;
        isNestedType_visited = -1;
        isMemberType_visited = -1;
        isLocalClass_visited = -1;
        hostPackage_visited = -1;
        unknownType_visited = -1;
        unknownType_computed = false;
        unknownType_value = null;
        typeVoid_visited = -1;
        enclosingInstance_visited = -1;
        inExplicitConstructorInvocation_visited = -1;
        inExplicitConstructorInvocation_computed = false;
        inStaticContext_visited = -1;
        inStaticContext_computed = false;
    }
    public void flushCollectionCache() {
        super.flushCollectionCache();
    }
     @SuppressWarnings({"unchecked", "cast"})  public TypeDecl clone() throws CloneNotSupportedException {
        TypeDecl node = (TypeDecl)super.clone();
        node.accessibleFromPackage_String_visited = null;
        node.accessibleFromPackage_String_values = null;
        node.accessibleFromExtend_TypeDecl_visited = null;
        node.accessibleFromExtend_TypeDecl_values = null;
        node.accessibleFrom_TypeDecl_visited = null;
        node.accessibleFrom_TypeDecl_values = null;
        node.dimension_visited = -1;
        node.dimension_computed = false;
        node.elementType_visited = -1;
        node.elementType_computed = false;
        node.elementType_value = null;
        node.arrayType_visited = -1;
        node.arrayType_computed = false;
        node.arrayType_value = null;
        node.cast_Constant_visited = null;
        node.plus_Constant_visited = null;
        node.minus_Constant_visited = null;
        node.bitNot_Constant_visited = null;
        node.mul_Constant_Constant_visited = null;
        node.div_Constant_Constant_visited = null;
        node.mod_Constant_Constant_visited = null;
        node.add_Constant_Constant_visited = null;
        node.sub_Constant_Constant_visited = null;
        node.lshift_Constant_Constant_visited = null;
        node.rshift_Constant_Constant_visited = null;
        node.urshift_Constant_Constant_visited = null;
        node.andBitwise_Constant_Constant_visited = null;
        node.xorBitwise_Constant_Constant_visited = null;
        node.orBitwise_Constant_Constant_visited = null;
        node.questionColon_Constant_Constant_Constant_visited = null;
        node.eqIsTrue_Expr_Expr_visited = null;
        node.ltIsTrue_Expr_Expr_visited = null;
        node.leIsTrue_Expr_Expr_visited = null;
        node.size_visited = -1;
        node.isEmpty_visited = -1;
        node.contains_Object_visited = null;
        node.isException_visited = -1;
        node.isException_computed = false;
        node.isCheckedException_visited = -1;
        node.isCheckedException_computed = false;
        node.isUncheckedException_visited = -1;
        node.isUncheckedException_computed = false;
        node.mayCatch_TypeDecl_visited = null;
        node.mayCatch_TypeDecl_values = null;
        node.lookupSuperConstructor_visited = -1;
        node.constructors_visited = -1;
        node.constructors_computed = false;
        node.constructors_value = null;
        node.unqualifiedLookupMethod_String_visited = null;
        node.unqualifiedLookupMethod_String_values = null;
        node.memberMethods_String_visited = null;
        node.methodsNameMap_visited = -1;
        node.methodsNameMap_computed = false;
        node.methodsNameMap_value = null;
        node.localMethodsSignature_String_visited = null;
        node.localMethodsSignatureMap_visited = -1;
        node.localMethodsSignatureMap_computed = false;
        node.localMethodsSignatureMap_value = null;
        node.methodsSignature_String_visited = null;
        node.methodsSignatureMap_visited = -1;
        node.methodsSignatureMap_computed = false;
        node.methodsSignatureMap_value = null;
        node.ancestorMethods_String_visited = null;
        node.ancestorMethods_String_values = null;
        node.hasType_String_visited = null;
        node.localTypeDecls_String_visited = null;
        node.localTypeDecls_String_values = null;
        node.memberTypes_String_visited = null;
        node.memberTypes_String_values = null;
        node.localFields_String_visited = null;
        node.localFields_String_values = null;
        node.localFieldsMap_visited = -1;
        node.localFieldsMap_computed = false;
        node.localFieldsMap_value = null;
        node.memberFieldsMap_visited = -1;
        node.memberFieldsMap_computed = false;
        node.memberFieldsMap_value = null;
        node.memberFields_String_visited = null;
        node.memberFields_String_values = null;
        node.hasAbstract_visited = -1;
        node.hasAbstract_computed = false;
        node.unimplementedMethods_visited = -1;
        node.unimplementedMethods_computed = false;
        node.unimplementedMethods_value = null;
        node.isPublic_visited = -1;
        node.isPublic_computed = false;
        node.isPrivate_visited = -1;
        node.isProtected_visited = -1;
        node.isAbstract_visited = -1;
        node.isStatic_visited = -1;
        node.isStatic_computed = false;
        node.isFinal_visited = -1;
        node.isStrictfp_visited = -1;
        node.isSynthetic_visited = -1;
        node.hasEnclosingTypeDecl_String_visited = null;
        node.assignableToInt_visited = -1;
        node.addsIndentationLevel_visited = -1;
        node.dumpString_visited = -1;
        node.name_visited = -1;
        node.fullName_visited = -1;
        node.fullName_computed = false;
        node.fullName_value = null;
        node.typeName_visited = -1;
        node.typeName_computed = false;
        node.typeName_value = null;
        node.identityConversionTo_TypeDecl_visited = null;
        node.wideningConversionTo_TypeDecl_visited = null;
        node.narrowingConversionTo_TypeDecl_visited = null;
        node.narrowingConversionTo_TypeDecl_values = null;
        node.stringConversion_visited = -1;
        node.assignConversionTo_TypeDecl_Expr_visited = null;
        node.methodInvocationConversionTo_TypeDecl_visited = null;
        node.methodInvocationConversionTo_TypeDecl_values = null;
        node.castingConversionTo_TypeDecl_visited = null;
        node.castingConversionTo_TypeDecl_values = null;
        node.unaryNumericPromotion_visited = -1;
        node.binaryNumericPromotion_TypeDecl_visited = null;
        node.isReferenceType_visited = -1;
        node.isPrimitiveType_visited = -1;
        node.isNumericType_visited = -1;
        node.isIntegralType_visited = -1;
        node.isBoolean_visited = -1;
        node.isByte_visited = -1;
        node.isChar_visited = -1;
        node.isShort_visited = -1;
        node.isInt_visited = -1;
        node.isFloat_visited = -1;
        node.isLong_visited = -1;
        node.isDouble_visited = -1;
        node.isVoid_visited = -1;
        node.isNull_visited = -1;
        node.isClassDecl_visited = -1;
        node.isInterfaceDecl_visited = -1;
        node.isArrayDecl_visited = -1;
        node.isPrimitive_visited = -1;
        node.isString_visited = -1;
        node.isString_computed = false;
        node.isObject_visited = -1;
        node.isObject_computed = false;
        node.isUnknown_visited = -1;
        node.instanceOf_TypeDecl_visited = null;
        node.instanceOf_TypeDecl_values = null;
        node.isSupertypeOfClassDecl_ClassDecl_visited = null;
        node.isSupertypeOfInterfaceDecl_InterfaceDecl_visited = null;
        node.isSupertypeOfArrayDecl_ArrayDecl_visited = null;
        node.isSupertypeOfPrimitiveType_PrimitiveType_visited = null;
        node.isSupertypeOfNullType_NullType_visited = null;
        node.isSupertypeOfVoidType_VoidType_visited = null;
        node.topLevelType_visited = -1;
        node.isTopLevelType_visited = -1;
        node.isInnerClass_visited = -1;
        node.isInnerType_visited = -1;
        node.isInnerTypeOf_TypeDecl_visited = null;
        node.withinBodyThatSubclasses_TypeDecl_visited = null;
        node.encloses_TypeDecl_visited = null;
        node.enclosedBy_TypeDecl_visited = null;
        node.hostType_visited = -1;
        node.isCircular_visited = -1;
        node.isCircular_computed = false;
        node.isCircular_initialized = false;
        node.componentType_visited = -1;
        node.componentType_computed = false;
        node.componentType_value = null;
        node.typeCloneable_visited = -1;
        node.typeSerializable_visited = -1;
        node.compilationUnit_visited = -1;
        node.isDAbefore_Variable_visited = null;
        node.isDAbefore_Variable_values = null;
        node.isDUbefore_Variable_visited = null;
        node.isDUbefore_Variable_values = null;
        node.typeException_visited = -1;
        node.typeException_computed = false;
        node.typeException_value = null;
        node.typeRuntimeException_visited = -1;
        node.typeRuntimeException_computed = false;
        node.typeRuntimeException_value = null;
        node.typeError_visited = -1;
        node.typeError_computed = false;
        node.typeError_value = null;
        node.lookupMethod_String_visited = null;
        node.lookupMethod_String_values = null;
        node.typeInt_visited = -1;
        node.typeObject_visited = -1;
        node.typeObject_computed = false;
        node.typeObject_value = null;
        node.lookupType_String_String_visited = null;
        node.lookupType_String_visited = null;
        node.lookupType_String_values = null;
        node.lookupVariable_String_visited = null;
        node.lookupVariable_String_values = null;
        node.hasPackage_String_visited = null;
        node.enclosingBlock_visited = -1;
        node.packageName_visited = -1;
        node.packageName_computed = false;
        node.packageName_value = null;
        node.isAnonymous_visited = -1;
        node.isAnonymous_computed = false;
        node.enclosingType_visited = -1;
        node.enclosingBodyDecl_visited = -1;
        node.isNestedType_visited = -1;
        node.isMemberType_visited = -1;
        node.isLocalClass_visited = -1;
        node.hostPackage_visited = -1;
        node.unknownType_visited = -1;
        node.unknownType_computed = false;
        node.unknownType_value = null;
        node.typeVoid_visited = -1;
        node.enclosingInstance_visited = -1;
        node.inExplicitConstructorInvocation_visited = -1;
        node.inExplicitConstructorInvocation_computed = false;
        node.inStaticContext_visited = -1;
        node.inStaticContext_computed = false;
        node.in$Circle(false);
        node.is$Final(false);
        return node;
    }
    // Declared in AnonymousClasses.jrag at line 28

  
  public int anonymousIndex = 0;

    // Declared in AnonymousClasses.jrag at line 45


  public int nextAnonymousIndex() {
    if(isNestedType())
      return enclosingType().nextAnonymousIndex();
    return anonymousIndex++;
  }

    // Declared in BoundNames.jrag at line 24


  // The memberMethods(String name) attribute is used to lookup member methods.
  // It uses the methodsNameMap() map where a name is mapped to a list of member
  // methods. We extend the map with the declaration m by either appending
  // it to an existing list of declarations or adding a new list. That list
  // will be used to name bind a new qualified name access.
  public MethodDecl addMemberMethod(MethodDecl m) {
    addBodyDecl(m);
    return (MethodDecl)getBodyDecl(getNumBodyDecl()-1);
    /*
    HashMap map = methodsNameMap();
    ArrayList list = (ArrayList)map.get(m.name());
    if(list == null) {
      list = new ArrayList(4);
      map.put(m.name(), list);
    }
    list.add(m);
    if(!memberMethods(m.name()).contains(m))
      throw new Error("The method " + m.signature() + " added to " + typeName() + " can not be found using lookupMemberMethod");
    */
  }

    // Declared in BoundNames.jrag at line 40


  public ConstructorDecl addConstructor(ConstructorDecl c) {
    addBodyDecl(c);
    return (ConstructorDecl)getBodyDecl(getNumBodyDecl()-1);
  }

    // Declared in BoundNames.jrag at line 45


  public ClassDecl addMemberClass(ClassDecl c) {
    addBodyDecl(new MemberClassDecl(c));
    return ((MemberClassDecl)getBodyDecl(getNumBodyDecl()-1)).getClassDecl();
  }

    // Declared in BoundNames.jrag at line 52



  // the new field must be unique otherwise an error occurs
  public FieldDeclaration addMemberField(FieldDeclaration f) {
    addBodyDecl(f);
    return (FieldDeclaration)getBodyDecl(getNumBodyDecl()-1);
    //if(!memberFields(f.name()).contains(f))
    //  throw new Error("The field " + f.name() + " added to " + typeName() + " can not be found using lookupMemberField");
  }

    // Declared in BoundNames.jrag at line 90


  public TypeAccess createBoundAccess() {
    return new BoundTypeAccess("", name(), this);
  }

    // Declared in DataStructures.jrag at line 118

  public SimpleSet add(Object o) {
    return new SimpleSetImpl().add(this).add(o);
  }

    // Declared in DataStructures.jrag at line 124

  private TypeDecl iterElem;

    // Declared in DataStructures.jrag at line 125

  public Iterator iterator() { iterElem = this; return this; }

    // Declared in DataStructures.jrag at line 126

  public boolean hasNext() { return iterElem != null; }

    // Declared in DataStructures.jrag at line 127

  public Object next() { Object o = iterElem; iterElem = null; return o; }

    // Declared in DataStructures.jrag at line 128

  public void remove() { throw new UnsupportedOperationException(); }

    // Declared in DeclareBeforeUse.jrag at line 41


  public boolean declaredBeforeUse(Variable decl, ASTNode use) {
    int indexDecl = ((ASTNode)decl).varChildIndex(this);
    int indexUse = use.varChildIndex(this);
    return indexDecl < indexUse;
  }

    // Declared in DeclareBeforeUse.jrag at line 46

  public boolean declaredBeforeUse(Variable decl, int indexUse) {
    int indexDecl = ((ASTNode)decl).varChildIndex(this);
    return indexDecl < indexUse;
  }

    // Declared in LookupConstructor.jrag at line 88

  public ConstructorDecl lookupConstructor(ConstructorDecl signature) {
    for(Iterator iter = constructors().iterator(); iter.hasNext(); ) {
      ConstructorDecl decl = (ConstructorDecl)iter.next();
      if(decl.sameSignature(signature)) {
        return decl;
      }
    }
    return null;
  }

    // Declared in LookupMethod.jrag at line 214



  public Iterator localMethodsIterator() {
    return new Iterator() {
      private Iterator outer = localMethodsSignatureMap().values().iterator();
      private Iterator inner = null;
      public boolean hasNext() {
        if((inner == null || !inner.hasNext()) && outer.hasNext())
          inner = ((SimpleSet)outer.next()).iterator();
        return inner == null ? false : inner.hasNext();
      }
      public Object next() {
        return inner.next();
      }
      public void remove() { throw new UnsupportedOperationException(); }
    };
    //return localMethodsSignatureMap().values().iterator();
  }

    // Declared in LookupMethod.jrag at line 282


  // iterate over all member methods in this type
  public Iterator methodsIterator() {
    return new Iterator() {
      private Iterator outer = methodsSignatureMap().values().iterator();
      private Iterator inner = null;
      public boolean hasNext() {
        if((inner == null || !inner.hasNext()) && outer.hasNext())
          inner = ((SimpleSet)outer.next()).iterator();
        return inner != null ? inner.hasNext() : false;
      }
      public Object next() {
        return inner.next();
      }
      public void remove() { throw new UnsupportedOperationException(); }
    };
  }

    // Declared in LookupMethod.jrag at line 347

  protected boolean allMethodsAbstract(SimpleSet set) {
    if(set == null) return true;
    for(Iterator iter = set.iterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      if(!m.isAbstract())
        return false;
    }
    return true;
  }

    // Declared in LookupVariable.jrag at line 208

  
  public TypeDecl subclassWithinBody(TypeDecl typeDecl) {
    if(instanceOf(typeDecl))
      return this;
    if(isNestedType()) {
      return enclosingType().subclassWithinBody(typeDecl);
    }
    return null;
  }

    // Declared in LookupVariable.jrag at line 304

  public Iterator fieldsIterator() {
    return new Iterator() {
      private Iterator outer = memberFieldsMap().values().iterator();
      private Iterator inner = null;
      public boolean hasNext() {
        if((inner == null || !inner.hasNext()) && outer.hasNext())
          inner = ((SimpleSet)outer.next()).iterator();
        return inner != null ? inner.hasNext() : false;
      }
      public Object next() {
        return inner.next();
      }
      public void remove() { throw new UnsupportedOperationException(); }
    };
  }

    // Declared in Modifiers.jrag at line 66


  public void checkModifiers() {
    super.checkModifiers();
    // 8.1.1
    if(isPublic() && !isTopLevelType() && !isMemberType())
      error("public pertains only to top level types and member types");

    // 8.1.1
    if((isProtected() || isPrivate()) && !(isMemberType() && enclosingType().isClassDecl()))
      error("protected and private may only be used on member types within a directly enclosing class declaration");

    // 8.1.1
    if(isStatic() && !isMemberType())
      error("static pertains only to member types");
    
    
    // 8.4.3.1
    // 8.1.1.1
    if(!isAbstract() && hasAbstract()) {
      StringBuffer s = new StringBuffer();
      s.append("" + name() + " is not declared abstract but contains abstract members: \n");
      for(Iterator iter = unimplementedMethods().iterator(); iter.hasNext(); ) {
        MethodDecl m = (MethodDecl)iter.next();
        s.append("  " + m.signature() + " in " + m.hostType().typeName() + "\n");
      }
      error(s.toString());
    }
  }

    // Declared in NameCheck.jrag at line 246


  public void nameCheck() {
    if(isTopLevelType() && lookupType(packageName(), name()) != this)
      error("duplicate member " + name() + " in compilation unit");
  
    if(!isTopLevelType() && !isAnonymous() && !isLocalClass() && extractSingleType(enclosingType().memberTypes(name())) != this)
      error("duplicate member type " + name() + " in type " + enclosingType().typeName());

    // 14.3
    if(isLocalClass()) {
      TypeDecl typeDecl = extractSingleType(lookupType(name()));
      if(typeDecl != null && typeDecl != this && typeDecl.isLocalClass() && enclosingBlock() == typeDecl.enclosingBlock())
        error("local class named " + name() + " may not be redeclared as a local class in the same block");
    }

    if(!packageName().equals("") && hasPackage(fullName()))
      error("duplicate member class and package " + name());
    
    // 8.1 & 9.1
    if(hasEnclosingTypeDecl(name())) {
      error("type may not have the same simple name as an enclosing type declaration");
    }
  }

    // Declared in QualifiedNames.jrag at line 96

  public Access createQualifiedAccess() {
    if(isLocalClass() || isAnonymous()) {
      return new TypeAccess(name());
    }
    else if(!isTopLevelType()) {
      return enclosingType().createQualifiedAccess().qualifiesAccess(new TypeAccess(name()));
    }
    else {
      return new TypeAccess(packageName(), name());
    }
  }

    // Declared in TypeAnalysis.jrag at line 234

  public FieldDeclaration findSingleVariable(String name) {
    return (FieldDeclaration)memberFields(name).iterator().next();
  }

    // Declared in TypeHierarchyCheck.jrag at line 157


  public void typeCheck() {
    // 8.4.6.4 & 9.4.1
    for(Iterator iter1 = localMethodsIterator(); iter1.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter1.next();
      ASTNode target = m.hostType() == this ? (ASTNode)m : (ASTNode)this;
      
      //for(Iterator i2 = overrides(m).iterator(); i2.hasNext(); ) {
      for(Iterator i2 = ancestorMethods(m.signature()).iterator(); i2.hasNext(); ) {
        MethodDecl decl = (MethodDecl)i2.next();
        if(m.overrides(decl)) {
          // 8.4.6.1
          if(!m.isStatic() && decl.isStatic())
            target.error("an instance method may not override a static method");
 
          // regardless of overriding
          // 8.4.6.3
          if(!m.mayOverrideReturn(decl))
            target.error("the return type of method " + m.signature() + " in " + m.hostType().typeName() + " does not match the return type of method " + decl.signature() + " in " + decl.hostType().typeName() + " and may thus not be overriden");
 
          // regardless of overriding
          // 8.4.4
          for(int i = 0; i < m.getNumException(); i++) {
            Access e = m.getException(i);
            boolean found = false;
            for(int j = 0; !found && j < decl.getNumException(); j++) {
              if(e.type().instanceOf(decl.getException(j).type()))
                found = true;
            }
            if(!found && e.type().isUncheckedException())
              target.error(m.signature() + " in " + m.hostType().typeName() + " may not throw more checked exceptions than overridden method " +
               decl.signature() + " in " + decl.hostType().typeName());
          }
          // 8.4.6.3
          if(decl.isPublic() && !m.isPublic())
            target.error("overriding access modifier error");
          // 8.4.6.3
          if(decl.isProtected() && !(m.isPublic() || m.isProtected()))
            target.error("overriding access modifier error");
          // 8.4.6.3
          if((!decl.isPrivate() && !decl.isProtected() && !decl.isPublic()) && m.isPrivate())
            target.error("overriding access modifier error");
 
          // regardless of overriding
          if(decl.isFinal())
            target.error("method " + m.signature() + " in " + hostType().typeName() + " can not override final method " + decl.signature() + " in " + decl.hostType().typeName());
        }
        if(m.hides(decl)) {
          // 8.4.6.2
          if(m.isStatic() && !decl.isStatic())
            target.error("a static method may not hide an instance method");
          // 8.4.6.3
          if(!m.mayOverrideReturn(decl))
            target.error("can not hide a method with a different return type");
          // 8.4.4
          for(int i = 0; i < m.getNumException(); i++) {
            Access e = m.getException(i);
            boolean found = false;
            for(int j = 0; !found && j < decl.getNumException(); j++) {
              if(e.type().instanceOf(decl.getException(j).type()))
                found = true;
            }
            if(!found)
              target.error("may not throw more checked exceptions than hidden method");
          }
          // 8.4.6.3
          if(decl.isPublic() && !m.isPublic())
            target.error("hiding access modifier error: public method " + decl.signature() + " in " + decl.hostType().typeName() + " is hidden by non public method " + m.signature() + " in " + m.hostType().typeName());
          // 8.4.6.3
          if(decl.isProtected() && !(m.isPublic() || m.isProtected()))
            target.error("hiding access modifier error: protected method " + decl.signature() + " in " + decl.hostType().typeName() + " is hidden by non (public|protected) method " + m.signature() + " in " + m.hostType().typeName());
          // 8.4.6.3
          if((!decl.isPrivate() && !decl.isProtected() && !decl.isPublic()) && m.isPrivate())
            target.error("hiding access modifier error: default method " + decl.signature() + " in " + decl.hostType().typeName() + " is hidden by private method " + m.signature() + " in " + m.hostType().typeName());
          if(decl.isFinal())
            target.error("method " + m.signature() + " in " + hostType().typeName() + " can not hide final method " + decl.signature() + " in " + decl.hostType().typeName());
        }
      }
    }
  }

    // Declared in java.ast at line 3
    // Declared in java.ast line 38

    public TypeDecl() {
        super();

        setChild(new List(), 1);

    }

    // Declared in java.ast at line 11


    // Declared in java.ast line 38
    public TypeDecl(Modifiers p0, String p1, List<BodyDecl> p2) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
    }

    // Declared in java.ast at line 18


    // Declared in java.ast line 38
    public TypeDecl(Modifiers p0, beaver.Symbol p1, List<BodyDecl> p2) {
        setChild(p0, 0);
        setID(p1);
        setChild(p2, 1);
    }

    // Declared in java.ast at line 24


  protected int numChildren() {
    return 2;
  }

    // Declared in java.ast at line 27

    public boolean mayHaveRewrite() {
        return false;
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 38
    public void setModifiers(Modifiers node) {
        setChild(node, 0);
    }

    // Declared in java.ast at line 5

    public Modifiers getModifiers() {
        return (Modifiers)getChild(0);
    }

    // Declared in java.ast at line 9


    public Modifiers getModifiersNoTransform() {
        return (Modifiers)getChildNoTransform(0);
    }

    // Declared in java.ast at line 2
    // Declared in java.ast line 38
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

    // Declared in java.ast at line 2
    // Declared in java.ast line 38
    public void setBodyDeclList(List<BodyDecl> list) {
        setChild(list, 1);
    }

    // Declared in java.ast at line 6


    public int getNumBodyDecl() {
        return getBodyDeclList().getNumChild();
    }

    // Declared in java.ast at line 10


     @SuppressWarnings({"unchecked", "cast"})  public BodyDecl getBodyDecl(int i) {
        return (BodyDecl)getBodyDeclList().getChild(i);
    }

    // Declared in java.ast at line 14


    public void addBodyDecl(BodyDecl node) {
        List<BodyDecl> list = (parent == null || state == null) ? getBodyDeclListNoTransform() : getBodyDeclList();
        list.addChild(node);
    }

    // Declared in java.ast at line 19


    public void addBodyDeclNoTransform(BodyDecl node) {
        List<BodyDecl> list = getBodyDeclListNoTransform();
        list.addChild(node);
    }

    // Declared in java.ast at line 24


    public void setBodyDecl(BodyDecl node, int i) {
        List<BodyDecl> list = getBodyDeclList();
        list.setChild(node, i);
    }

    // Declared in java.ast at line 28

    public List<BodyDecl> getBodyDecls() {
        return getBodyDeclList();
    }

    // Declared in java.ast at line 31

    public List<BodyDecl> getBodyDeclsNoTransform() {
        return getBodyDeclListNoTransform();
    }

    // Declared in java.ast at line 35


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclList() {
        List<BodyDecl> list = (List<BodyDecl>)getChild(1);
        list.getNumChild();
        return list;
    }

    // Declared in java.ast at line 41


     @SuppressWarnings({"unchecked", "cast"})  public List<BodyDecl> getBodyDeclListNoTransform() {
        return (List<BodyDecl>)getChildNoTransform(1);
    }

    protected java.util.Map accessibleFromPackage_String_visited;
    protected java.util.Map accessibleFromPackage_String_values;
    // Declared in AccessControl.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessibleFromPackage(String packageName) {
        Object _parameters = packageName;
if(accessibleFromPackage_String_visited == null) accessibleFromPackage_String_visited = new java.util.HashMap(4);
if(accessibleFromPackage_String_values == null) accessibleFromPackage_String_values = new java.util.HashMap(4);
        if(accessibleFromPackage_String_values.containsKey(_parameters)) {
            return ((Boolean)accessibleFromPackage_String_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(accessibleFromPackage_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: accessibleFromPackage in class: ");
        accessibleFromPackage_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean accessibleFromPackage_String_value = accessibleFromPackage_compute(packageName);
        if(isFinal && num == state().boundariesCrossed)
            accessibleFromPackage_String_values.put(_parameters, Boolean.valueOf(accessibleFromPackage_String_value));
        accessibleFromPackage_String_visited.remove(_parameters);
        return accessibleFromPackage_String_value;
    }

    private boolean accessibleFromPackage_compute(String packageName) {  return !isPrivate() && (isPublic() || hostPackage().equals(packageName));  }

    protected java.util.Map accessibleFromExtend_TypeDecl_visited;
    protected java.util.Map accessibleFromExtend_TypeDecl_values;
    // Declared in AccessControl.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessibleFromExtend(TypeDecl type) {
        Object _parameters = type;
if(accessibleFromExtend_TypeDecl_visited == null) accessibleFromExtend_TypeDecl_visited = new java.util.HashMap(4);
if(accessibleFromExtend_TypeDecl_values == null) accessibleFromExtend_TypeDecl_values = new java.util.HashMap(4);
        if(accessibleFromExtend_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)accessibleFromExtend_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(accessibleFromExtend_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: accessibleFromExtend in class: ");
        accessibleFromExtend_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean accessibleFromExtend_TypeDecl_value = accessibleFromExtend_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            accessibleFromExtend_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFromExtend_TypeDecl_value));
        accessibleFromExtend_TypeDecl_visited.remove(_parameters);
        return accessibleFromExtend_TypeDecl_value;
    }

    private boolean accessibleFromExtend_compute(TypeDecl type) {
    if(type == this)
      return true;
    if(isInnerType()) { 
      if(!enclosingType().accessibleFrom(type)) {
        return false;
      }
    }
    if(isPublic()) 
      return true;
    else if(isProtected()) {
      // isProtected implies a nested type
      if(hostPackage().equals(type.hostPackage())) {
        return true;
      }
      if(type.isNestedType() && type.enclosingType().withinBodyThatSubclasses(enclosingType()) != null)
        return true;
      return false;
    }
    else if(isPrivate()) {
      return topLevelType() == type.topLevelType();
    }
    else
      return hostPackage().equals(type.hostPackage());
  }

    protected java.util.Map accessibleFrom_TypeDecl_visited;
    protected java.util.Map accessibleFrom_TypeDecl_values;
    // Declared in AccessControl.jrag at line 44
 @SuppressWarnings({"unchecked", "cast"})     public boolean accessibleFrom(TypeDecl type) {
        Object _parameters = type;
if(accessibleFrom_TypeDecl_visited == null) accessibleFrom_TypeDecl_visited = new java.util.HashMap(4);
if(accessibleFrom_TypeDecl_values == null) accessibleFrom_TypeDecl_values = new java.util.HashMap(4);
        if(accessibleFrom_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)accessibleFrom_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(accessibleFrom_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: accessibleFrom in class: ");
        accessibleFrom_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean accessibleFrom_TypeDecl_value = accessibleFrom_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            accessibleFrom_TypeDecl_values.put(_parameters, Boolean.valueOf(accessibleFrom_TypeDecl_value));
        accessibleFrom_TypeDecl_visited.remove(_parameters);
        return accessibleFrom_TypeDecl_value;
    }

    private boolean accessibleFrom_compute(TypeDecl type) {
    if(type == this)
      return true;
    if(isInnerType()) { 
      if(!enclosingType().accessibleFrom(type)) {
        return false;
      }
    }
    if(isPublic()) {  
      return true;
    }
    else if(isProtected()) {
      if(hostPackage().equals(type.hostPackage())) {
        return true;
      }
      if(isMemberType()) {
        TypeDecl typeDecl = type;
        while(typeDecl != null && !typeDecl.instanceOf(enclosingType()))
          typeDecl = typeDecl.enclosingType();
        if(typeDecl != null) {
          return true;
        }
      }
      return false;
    }
    else if(isPrivate()) {
      return topLevelType() == type.topLevelType();
    }
    else {
      return hostPackage().equals(type.hostPackage());
    }
  }

    protected int dimension_visited = -1;
    protected boolean dimension_computed = false;
    protected int dimension_value;
    // Declared in Arrays.jrag at line 11
 @SuppressWarnings({"unchecked", "cast"})     public int dimension() {
        if(dimension_computed) {
            return dimension_value;
        }
        ASTNode$State state = state();
        if(dimension_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: dimension in class: ");
        dimension_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        dimension_value = dimension_compute();
        if(isFinal && num == state().boundariesCrossed)
            dimension_computed = true;
        dimension_visited = -1;
        return dimension_value;
    }

    private int dimension_compute() {  return 0;  }

    protected int elementType_visited = -1;
    protected boolean elementType_computed = false;
    protected TypeDecl elementType_value;
    // Declared in Arrays.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl elementType() {
        if(elementType_computed) {
            return elementType_value;
        }
        ASTNode$State state = state();
        if(elementType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: elementType in class: ");
        elementType_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        elementType_value = elementType_compute();
        if(isFinal && num == state().boundariesCrossed)
            elementType_computed = true;
        elementType_visited = -1;
        return elementType_value;
    }

    private TypeDecl elementType_compute() {  return this;  }

    protected int arrayType_visited = -1;
    protected boolean arrayType_computed = false;
    protected TypeDecl arrayType_value;
    // Declared in Arrays.jrag at line 23
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl arrayType() {
        if(arrayType_computed) {
            return arrayType_value;
        }
        ASTNode$State state = state();
        if(arrayType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: arrayType in class: ");
        arrayType_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        arrayType_value = arrayType_compute();
        arrayType_value.setParent(this);
        arrayType_value.is$Final = true;
        if(true)
            arrayType_computed = true;
        arrayType_visited = -1;
        return arrayType_value;
    }

    private TypeDecl arrayType_compute() {
    String name = name() + "[]";
    TypeDecl typeDecl =
      new ArrayDecl(
        new Modifiers(new List().add(new Modifier("public"))),
        name,
        new Opt(typeObject().createQualifiedAccess()), // [SuperClassAccess]
        new List().add(typeCloneable().createQualifiedAccess()).add(typeSerializable().createQualifiedAccess()), // Implements*
        new List().add( // BodyDecl*
          new FieldDeclaration(
            new Modifiers(new List().add(new Modifier("public")).add(new Modifier("final"))),
            new PrimitiveTypeAccess("int"),
            "length",
            new Opt() // [Init:Expr]
          )).add(
          new MethodDecl(
            new Modifiers(new List().add(new Modifier("public"))),
            typeObject().createQualifiedAccess(),
            "clone",
            new List(),
            new List(),
            new Opt(new Block())
          )
        )
      );
    return typeDecl;
  }

    protected java.util.Map cast_Constant_visited;
    // Declared in ConstantExpression.jrag at line 306
 @SuppressWarnings({"unchecked", "cast"})     public Constant cast(Constant c) {
        Object _parameters = c;
if(cast_Constant_visited == null) cast_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(cast_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: cast in class: ");
        cast_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant cast_Constant_value = cast_compute(c);
        cast_Constant_visited.remove(_parameters);
        return cast_Constant_value;
    }

    private Constant cast_compute(Constant c) {
    throw new UnsupportedOperationException("ConstantExpression operation cast" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map plus_Constant_visited;
    // Declared in ConstantExpression.jrag at line 320
 @SuppressWarnings({"unchecked", "cast"})     public Constant plus(Constant c) {
        Object _parameters = c;
if(plus_Constant_visited == null) plus_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(plus_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: plus in class: ");
        plus_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant plus_Constant_value = plus_compute(c);
        plus_Constant_visited.remove(_parameters);
        return plus_Constant_value;
    }

    private Constant plus_compute(Constant c) {
    throw new UnsupportedOperationException("ConstantExpression operation plus" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map minus_Constant_visited;
    // Declared in ConstantExpression.jrag at line 329
 @SuppressWarnings({"unchecked", "cast"})     public Constant minus(Constant c) {
        Object _parameters = c;
if(minus_Constant_visited == null) minus_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(minus_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: minus in class: ");
        minus_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant minus_Constant_value = minus_compute(c);
        minus_Constant_visited.remove(_parameters);
        return minus_Constant_value;
    }

    private Constant minus_compute(Constant c) {
    throw new UnsupportedOperationException("ConstantExpression operation minus" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map bitNot_Constant_visited;
    // Declared in ConstantExpression.jrag at line 338
 @SuppressWarnings({"unchecked", "cast"})     public Constant bitNot(Constant c) {
        Object _parameters = c;
if(bitNot_Constant_visited == null) bitNot_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(bitNot_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: bitNot in class: ");
        bitNot_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant bitNot_Constant_value = bitNot_compute(c);
        bitNot_Constant_visited.remove(_parameters);
        return bitNot_Constant_value;
    }

    private Constant bitNot_compute(Constant c) {
    throw new UnsupportedOperationException("ConstantExpression operation bitNot" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map mul_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 345
 @SuppressWarnings({"unchecked", "cast"})     public Constant mul(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(mul_Constant_Constant_visited == null) mul_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(mul_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: mul in class: ");
        mul_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant mul_Constant_Constant_value = mul_compute(c1, c2);
        mul_Constant_Constant_visited.remove(_parameters);
        return mul_Constant_Constant_value;
    }

    private Constant mul_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation mul" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map div_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 354
 @SuppressWarnings({"unchecked", "cast"})     public Constant div(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(div_Constant_Constant_visited == null) div_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(div_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: div in class: ");
        div_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant div_Constant_Constant_value = div_compute(c1, c2);
        div_Constant_Constant_visited.remove(_parameters);
        return div_Constant_Constant_value;
    }

    private Constant div_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation div" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map mod_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 363
 @SuppressWarnings({"unchecked", "cast"})     public Constant mod(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(mod_Constant_Constant_visited == null) mod_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(mod_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: mod in class: ");
        mod_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant mod_Constant_Constant_value = mod_compute(c1, c2);
        mod_Constant_Constant_visited.remove(_parameters);
        return mod_Constant_Constant_value;
    }

    private Constant mod_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation mod" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map add_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 372
 @SuppressWarnings({"unchecked", "cast"})     public Constant add(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(add_Constant_Constant_visited == null) add_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(add_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: add in class: ");
        add_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant add_Constant_Constant_value = add_compute(c1, c2);
        add_Constant_Constant_visited.remove(_parameters);
        return add_Constant_Constant_value;
    }

    private Constant add_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation add" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map sub_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 382
 @SuppressWarnings({"unchecked", "cast"})     public Constant sub(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(sub_Constant_Constant_visited == null) sub_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(sub_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: sub in class: ");
        sub_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant sub_Constant_Constant_value = sub_compute(c1, c2);
        sub_Constant_Constant_visited.remove(_parameters);
        return sub_Constant_Constant_value;
    }

    private Constant sub_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation sub" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map lshift_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 391
 @SuppressWarnings({"unchecked", "cast"})     public Constant lshift(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(lshift_Constant_Constant_visited == null) lshift_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lshift_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lshift in class: ");
        lshift_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant lshift_Constant_Constant_value = lshift_compute(c1, c2);
        lshift_Constant_Constant_visited.remove(_parameters);
        return lshift_Constant_Constant_value;
    }

    private Constant lshift_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation lshift" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map rshift_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 398
 @SuppressWarnings({"unchecked", "cast"})     public Constant rshift(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(rshift_Constant_Constant_visited == null) rshift_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(rshift_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: rshift in class: ");
        rshift_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant rshift_Constant_Constant_value = rshift_compute(c1, c2);
        rshift_Constant_Constant_visited.remove(_parameters);
        return rshift_Constant_Constant_value;
    }

    private Constant rshift_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation rshift" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map urshift_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 405
 @SuppressWarnings({"unchecked", "cast"})     public Constant urshift(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(urshift_Constant_Constant_visited == null) urshift_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(urshift_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: urshift in class: ");
        urshift_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant urshift_Constant_Constant_value = urshift_compute(c1, c2);
        urshift_Constant_Constant_visited.remove(_parameters);
        return urshift_Constant_Constant_value;
    }

    private Constant urshift_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation urshift" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map andBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 412
 @SuppressWarnings({"unchecked", "cast"})     public Constant andBitwise(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(andBitwise_Constant_Constant_visited == null) andBitwise_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(andBitwise_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: andBitwise in class: ");
        andBitwise_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant andBitwise_Constant_Constant_value = andBitwise_compute(c1, c2);
        andBitwise_Constant_Constant_visited.remove(_parameters);
        return andBitwise_Constant_Constant_value;
    }

    private Constant andBitwise_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation andBitwise" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map xorBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 420
 @SuppressWarnings({"unchecked", "cast"})     public Constant xorBitwise(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(xorBitwise_Constant_Constant_visited == null) xorBitwise_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(xorBitwise_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: xorBitwise in class: ");
        xorBitwise_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant xorBitwise_Constant_Constant_value = xorBitwise_compute(c1, c2);
        xorBitwise_Constant_Constant_visited.remove(_parameters);
        return xorBitwise_Constant_Constant_value;
    }

    private Constant xorBitwise_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation xorBitwise" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map orBitwise_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 428
 @SuppressWarnings({"unchecked", "cast"})     public Constant orBitwise(Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(c1);
        _parameters.add(c2);
if(orBitwise_Constant_Constant_visited == null) orBitwise_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(orBitwise_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: orBitwise in class: ");
        orBitwise_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant orBitwise_Constant_Constant_value = orBitwise_compute(c1, c2);
        orBitwise_Constant_Constant_visited.remove(_parameters);
        return orBitwise_Constant_Constant_value;
    }

    private Constant orBitwise_compute(Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation orBitwise" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map questionColon_Constant_Constant_Constant_visited;
    // Declared in ConstantExpression.jrag at line 436
 @SuppressWarnings({"unchecked", "cast"})     public Constant questionColon(Constant cond, Constant c1, Constant c2) {
        java.util.List _parameters = new java.util.ArrayList(3);
        _parameters.add(cond);
        _parameters.add(c1);
        _parameters.add(c2);
if(questionColon_Constant_Constant_Constant_visited == null) questionColon_Constant_Constant_Constant_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(questionColon_Constant_Constant_Constant_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: questionColon in class: ");
        questionColon_Constant_Constant_Constant_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Constant questionColon_Constant_Constant_Constant_value = questionColon_compute(cond, c1, c2);
        questionColon_Constant_Constant_Constant_visited.remove(_parameters);
        return questionColon_Constant_Constant_Constant_value;
    }

    private Constant questionColon_compute(Constant cond, Constant c1, Constant c2) {
    throw new UnsupportedOperationException("ConstantExpression operation questionColon" +
      " not supported for type " + getClass().getName()); 
  }

    protected java.util.Map eqIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 540
 @SuppressWarnings({"unchecked", "cast"})     public boolean eqIsTrue(Expr left, Expr right) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(left);
        _parameters.add(right);
if(eqIsTrue_Expr_Expr_visited == null) eqIsTrue_Expr_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(eqIsTrue_Expr_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: eqIsTrue in class: ");
        eqIsTrue_Expr_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean eqIsTrue_Expr_Expr_value = eqIsTrue_compute(left, right);
        eqIsTrue_Expr_Expr_visited.remove(_parameters);
        return eqIsTrue_Expr_Expr_value;
    }

    private boolean eqIsTrue_compute(Expr left, Expr right) {
    System.err.println("Evaluation eqIsTrue for unknown type: " + getClass().getName());
    return false;
  }

    protected java.util.Map ltIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 551
 @SuppressWarnings({"unchecked", "cast"})     public boolean ltIsTrue(Expr left, Expr right) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(left);
        _parameters.add(right);
if(ltIsTrue_Expr_Expr_visited == null) ltIsTrue_Expr_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(ltIsTrue_Expr_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: ltIsTrue in class: ");
        ltIsTrue_Expr_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean ltIsTrue_Expr_Expr_value = ltIsTrue_compute(left, right);
        ltIsTrue_Expr_Expr_visited.remove(_parameters);
        return ltIsTrue_Expr_Expr_value;
    }

    private boolean ltIsTrue_compute(Expr left, Expr right) {  return false;  }

    protected java.util.Map leIsTrue_Expr_Expr_visited;
    // Declared in ConstantExpression.jrag at line 557
 @SuppressWarnings({"unchecked", "cast"})     public boolean leIsTrue(Expr left, Expr right) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(left);
        _parameters.add(right);
if(leIsTrue_Expr_Expr_visited == null) leIsTrue_Expr_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(leIsTrue_Expr_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: leIsTrue in class: ");
        leIsTrue_Expr_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean leIsTrue_Expr_Expr_value = leIsTrue_compute(left, right);
        leIsTrue_Expr_Expr_visited.remove(_parameters);
        return leIsTrue_Expr_Expr_value;
    }

    private boolean leIsTrue_compute(Expr left, Expr right) {  return false;  }

    protected int size_visited = -1;
    // Declared in DataStructures.jrag at line 116
 @SuppressWarnings({"unchecked", "cast"})     public int size() {
        ASTNode$State state = state();
        if(size_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: size in class: ");
        size_visited = state().boundariesCrossed;
        int size_value = size_compute();
        size_visited = -1;
        return size_value;
    }

    private int size_compute() {  return 1;  }

    protected int isEmpty_visited = -1;
    // Declared in DataStructures.jrag at line 117
 @SuppressWarnings({"unchecked", "cast"})     public boolean isEmpty() {
        ASTNode$State state = state();
        if(isEmpty_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isEmpty in class: ");
        isEmpty_visited = state().boundariesCrossed;
        boolean isEmpty_value = isEmpty_compute();
        isEmpty_visited = -1;
        return isEmpty_value;
    }

    private boolean isEmpty_compute() {  return false;  }

    protected java.util.Map contains_Object_visited;
    // Declared in DataStructures.jrag at line 121
 @SuppressWarnings({"unchecked", "cast"})     public boolean contains(Object o) {
        Object _parameters = o;
if(contains_Object_visited == null) contains_Object_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(contains_Object_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: contains in class: ");
        contains_Object_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean contains_Object_value = contains_compute(o);
        contains_Object_visited.remove(_parameters);
        return contains_Object_value;
    }

    private boolean contains_compute(Object o) {  return this == o;  }

    protected int isException_visited = -1;
    protected boolean isException_computed = false;
    protected boolean isException_value;
    // Declared in ExceptionHandling.jrag at line 24
 @SuppressWarnings({"unchecked", "cast"})     public boolean isException() {
        if(isException_computed) {
            return isException_value;
        }
        ASTNode$State state = state();
        if(isException_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isException in class: ");
        isException_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isException_value = isException_compute();
        if(isFinal && num == state().boundariesCrossed)
            isException_computed = true;
        isException_visited = -1;
        return isException_value;
    }

    private boolean isException_compute() {  return instanceOf(typeException());  }

    protected int isCheckedException_visited = -1;
    protected boolean isCheckedException_computed = false;
    protected boolean isCheckedException_value;
    // Declared in ExceptionHandling.jrag at line 25
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCheckedException() {
        if(isCheckedException_computed) {
            return isCheckedException_value;
        }
        ASTNode$State state = state();
        if(isCheckedException_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isCheckedException in class: ");
        isCheckedException_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isCheckedException_value = isCheckedException_compute();
        if(isFinal && num == state().boundariesCrossed)
            isCheckedException_computed = true;
        isCheckedException_visited = -1;
        return isCheckedException_value;
    }

    private boolean isCheckedException_compute() {  return isException() &&
    (instanceOf(typeRuntimeException()) || instanceOf(typeError()));  }

    protected int isUncheckedException_visited = -1;
    protected boolean isUncheckedException_computed = false;
    protected boolean isUncheckedException_value;
    // Declared in ExceptionHandling.jrag at line 27
 @SuppressWarnings({"unchecked", "cast"})     public boolean isUncheckedException() {
        if(isUncheckedException_computed) {
            return isUncheckedException_value;
        }
        ASTNode$State state = state();
        if(isUncheckedException_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isUncheckedException in class: ");
        isUncheckedException_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isUncheckedException_value = isUncheckedException_compute();
        if(isFinal && num == state().boundariesCrossed)
            isUncheckedException_computed = true;
        isUncheckedException_visited = -1;
        return isUncheckedException_value;
    }

    private boolean isUncheckedException_compute() {  return isException() && !isCheckedException();  }

    protected java.util.Map mayCatch_TypeDecl_visited;
    protected java.util.Map mayCatch_TypeDecl_values;
    // Declared in ExceptionHandling.jrag at line 222
 @SuppressWarnings({"unchecked", "cast"})     public boolean mayCatch(TypeDecl thrownType) {
        Object _parameters = thrownType;
if(mayCatch_TypeDecl_visited == null) mayCatch_TypeDecl_visited = new java.util.HashMap(4);
if(mayCatch_TypeDecl_values == null) mayCatch_TypeDecl_values = new java.util.HashMap(4);
        if(mayCatch_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)mayCatch_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(mayCatch_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: mayCatch in class: ");
        mayCatch_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean mayCatch_TypeDecl_value = mayCatch_compute(thrownType);
        if(isFinal && num == state().boundariesCrossed)
            mayCatch_TypeDecl_values.put(_parameters, Boolean.valueOf(mayCatch_TypeDecl_value));
        mayCatch_TypeDecl_visited.remove(_parameters);
        return mayCatch_TypeDecl_value;
    }

    private boolean mayCatch_compute(TypeDecl thrownType) {  return thrownType.instanceOf(this) || this.instanceOf(thrownType);  }

    protected int lookupSuperConstructor_visited = -1;
    // Declared in LookupConstructor.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupSuperConstructor() {
        ASTNode$State state = state();
        if(lookupSuperConstructor_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: lookupSuperConstructor in class: ");
        lookupSuperConstructor_visited = state().boundariesCrossed;
        Collection lookupSuperConstructor_value = lookupSuperConstructor_compute();
        lookupSuperConstructor_visited = -1;
        return lookupSuperConstructor_value;
    }

    private Collection lookupSuperConstructor_compute() {  return Collections.EMPTY_LIST;  }

    protected int constructors_visited = -1;
    protected boolean constructors_computed = false;
    protected Collection constructors_value;
    // Declared in LookupConstructor.jrag at line 99
 @SuppressWarnings({"unchecked", "cast"})     public Collection constructors() {
        if(constructors_computed) {
            return constructors_value;
        }
        ASTNode$State state = state();
        if(constructors_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: constructors in class: ");
        constructors_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        constructors_value = constructors_compute();
        if(isFinal && num == state().boundariesCrossed)
            constructors_computed = true;
        constructors_visited = -1;
        return constructors_value;
    }

    private Collection constructors_compute() {
    Collection c = new ArrayList();
    for(int i = 0; i < getNumBodyDecl(); i++) {
      if(getBodyDecl(i) instanceof ConstructorDecl) {
        c.add(getBodyDecl(i));
      }
    }
    /*
    if(c.isEmpty() && isClassDecl()) {
      Modifiers m = new Modifiers();
      if(isPublic()) m.addModifier(new Modifier("public"));
      else if(isProtected()) m.addModifier(new Modifier("protected"));
      else if(isPrivate()) m.addModifier(new Modifier("private"));
      addBodyDecl(
          new ConstructorDecl(
            m,
            name(),
            new List(),
            new List(),
            new Opt(),
            new Block()
          )
      );
      c.add(getBodyDecl(getNumBodyDecl()-1));
    }
    */
    return c;
  }

    protected java.util.Map unqualifiedLookupMethod_String_visited;
    protected java.util.Map unqualifiedLookupMethod_String_values;
    // Declared in LookupMethod.jrag at line 36
 @SuppressWarnings({"unchecked", "cast"})     public Collection unqualifiedLookupMethod(String name) {
        Object _parameters = name;
if(unqualifiedLookupMethod_String_visited == null) unqualifiedLookupMethod_String_visited = new java.util.HashMap(4);
if(unqualifiedLookupMethod_String_values == null) unqualifiedLookupMethod_String_values = new java.util.HashMap(4);
        if(unqualifiedLookupMethod_String_values.containsKey(_parameters)) {
            return (Collection)unqualifiedLookupMethod_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(unqualifiedLookupMethod_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: unqualifiedLookupMethod in class: ");
        unqualifiedLookupMethod_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        Collection unqualifiedLookupMethod_String_value = unqualifiedLookupMethod_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            unqualifiedLookupMethod_String_values.put(_parameters, unqualifiedLookupMethod_String_value);
        unqualifiedLookupMethod_String_visited.remove(_parameters);
        return unqualifiedLookupMethod_String_value;
    }

    private Collection unqualifiedLookupMethod_compute(String name) {
    Collection c = memberMethods(name);
    if(!c.isEmpty()) return c;
    if(isInnerType())
      return lookupMethod(name);
    return removeInstanceMethods(lookupMethod(name));
  }

    protected java.util.Map memberMethods_String_visited;
    // Declared in LookupMethod.jrag at line 193
 @SuppressWarnings({"unchecked", "cast"})     public Collection memberMethods(String name) {
        Object _parameters = name;
if(memberMethods_String_visited == null) memberMethods_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(memberMethods_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: memberMethods in class: ");
        memberMethods_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        Collection memberMethods_String_value = memberMethods_compute(name);
        memberMethods_String_visited.remove(_parameters);
        return memberMethods_String_value;
    }

    private Collection memberMethods_compute(String name) {
    Collection c = (Collection)methodsNameMap().get(name);
    if(c != null) return c;
    return Collections.EMPTY_LIST;
  }

    protected int methodsNameMap_visited = -1;
    protected boolean methodsNameMap_computed = false;
    protected HashMap methodsNameMap_value;
    // Declared in LookupMethod.jrag at line 199
 @SuppressWarnings({"unchecked", "cast"})     public HashMap methodsNameMap() {
        if(methodsNameMap_computed) {
            return methodsNameMap_value;
        }
        ASTNode$State state = state();
        if(methodsNameMap_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: methodsNameMap in class: ");
        methodsNameMap_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        methodsNameMap_value = methodsNameMap_compute();
        if(isFinal && num == state().boundariesCrossed)
            methodsNameMap_computed = true;
        methodsNameMap_visited = -1;
        return methodsNameMap_value;
    }

    private HashMap methodsNameMap_compute() {
    HashMap map = new HashMap();
    for(Iterator iter = methodsIterator(); iter.hasNext(); ) {
      MethodDecl m = (MethodDecl)iter.next();
      ArrayList list = (ArrayList)map.get(m.name());
      if(list == null) {
        list = new ArrayList(4);
        map.put(m.name(), list);
      }
      list.add(m);
    }
    return map;
  }

    protected java.util.Map localMethodsSignature_String_visited;
    // Declared in LookupMethod.jrag at line 230
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localMethodsSignature(String signature) {
        Object _parameters = signature;
if(localMethodsSignature_String_visited == null) localMethodsSignature_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(localMethodsSignature_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: localMethodsSignature in class: ");
        localMethodsSignature_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet localMethodsSignature_String_value = localMethodsSignature_compute(signature);
        localMethodsSignature_String_visited.remove(_parameters);
        return localMethodsSignature_String_value;
    }

    private SimpleSet localMethodsSignature_compute(String signature) {
    SimpleSet set = (SimpleSet)localMethodsSignatureMap().get(signature);
    if(set != null) return set;
    return SimpleSet.emptySet;
  }

    protected int localMethodsSignatureMap_visited = -1;
    protected boolean localMethodsSignatureMap_computed = false;
    protected HashMap localMethodsSignatureMap_value;
    // Declared in LookupMethod.jrag at line 236
 @SuppressWarnings({"unchecked", "cast"})     public HashMap localMethodsSignatureMap() {
        if(localMethodsSignatureMap_computed) {
            return localMethodsSignatureMap_value;
        }
        ASTNode$State state = state();
        if(localMethodsSignatureMap_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: localMethodsSignatureMap in class: ");
        localMethodsSignatureMap_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        localMethodsSignatureMap_value = localMethodsSignatureMap_compute();
        if(isFinal && num == state().boundariesCrossed)
            localMethodsSignatureMap_computed = true;
        localMethodsSignatureMap_visited = -1;
        return localMethodsSignatureMap_value;
    }

    private HashMap localMethodsSignatureMap_compute() {
    HashMap map = new HashMap(getNumBodyDecl());
    for(int i = 0; i < getNumBodyDecl(); i++) {
      if(getBodyDecl(i) instanceof MethodDecl) {
        MethodDecl decl = (MethodDecl)getBodyDecl(i);
        map.put(decl.signature(), decl);
      }
    }
    return map;
  }

    protected java.util.Map methodsSignature_String_visited;
    // Declared in LookupMethod.jrag at line 298
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet methodsSignature(String signature) {
        Object _parameters = signature;
if(methodsSignature_String_visited == null) methodsSignature_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(methodsSignature_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: methodsSignature in class: ");
        methodsSignature_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        SimpleSet methodsSignature_String_value = methodsSignature_compute(signature);
        methodsSignature_String_visited.remove(_parameters);
        return methodsSignature_String_value;
    }

    private SimpleSet methodsSignature_compute(String signature) {
    SimpleSet set = (SimpleSet)methodsSignatureMap().get(signature);
    if(set != null) return set;
    return SimpleSet.emptySet;
  }

    protected int methodsSignatureMap_visited = -1;
    protected boolean methodsSignatureMap_computed = false;
    protected HashMap methodsSignatureMap_value;
    // Declared in LookupMethod.jrag at line 304
 @SuppressWarnings({"unchecked", "cast"})     public HashMap methodsSignatureMap() {
        if(methodsSignatureMap_computed) {
            return methodsSignatureMap_value;
        }
        ASTNode$State state = state();
        if(methodsSignatureMap_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: methodsSignatureMap in class: ");
        methodsSignatureMap_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        methodsSignatureMap_value = methodsSignatureMap_compute();
        if(isFinal && num == state().boundariesCrossed)
            methodsSignatureMap_computed = true;
        methodsSignatureMap_visited = -1;
        return methodsSignatureMap_value;
    }

    private HashMap methodsSignatureMap_compute() {  return localMethodsSignatureMap();  }

    protected java.util.Map ancestorMethods_String_visited;
    protected java.util.Map ancestorMethods_String_values;
    // Declared in LookupMethod.jrag at line 361
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet ancestorMethods(String signature) {
        Object _parameters = signature;
if(ancestorMethods_String_visited == null) ancestorMethods_String_visited = new java.util.HashMap(4);
if(ancestorMethods_String_values == null) ancestorMethods_String_values = new java.util.HashMap(4);
        if(ancestorMethods_String_values.containsKey(_parameters)) {
            return (SimpleSet)ancestorMethods_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(ancestorMethods_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: ancestorMethods in class: ");
        ancestorMethods_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet ancestorMethods_String_value = ancestorMethods_compute(signature);
        if(isFinal && num == state().boundariesCrossed)
            ancestorMethods_String_values.put(_parameters, ancestorMethods_String_value);
        ancestorMethods_String_visited.remove(_parameters);
        return ancestorMethods_String_value;
    }

    private SimpleSet ancestorMethods_compute(String signature) {  return SimpleSet.emptySet;  }

    protected java.util.Map hasType_String_visited;
    // Declared in LookupType.jrag at line 390
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasType(String name) {
        Object _parameters = name;
if(hasType_String_visited == null) hasType_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(hasType_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: hasType in class: ");
        hasType_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean hasType_String_value = hasType_compute(name);
        hasType_String_visited.remove(_parameters);
        return hasType_String_value;
    }

    private boolean hasType_compute(String name) {  return !memberTypes(name).isEmpty();  }

    protected java.util.Map localTypeDecls_String_visited;
    protected java.util.Map localTypeDecls_String_values;
    // Declared in LookupType.jrag at line 401
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localTypeDecls(String name) {
        Object _parameters = name;
if(localTypeDecls_String_visited == null) localTypeDecls_String_visited = new java.util.HashMap(4);
if(localTypeDecls_String_values == null) localTypeDecls_String_values = new java.util.HashMap(4);
        if(localTypeDecls_String_values.containsKey(_parameters)) {
            return (SimpleSet)localTypeDecls_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(localTypeDecls_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: localTypeDecls in class: ");
        localTypeDecls_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet localTypeDecls_String_value = localTypeDecls_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            localTypeDecls_String_values.put(_parameters, localTypeDecls_String_value);
        localTypeDecls_String_visited.remove(_parameters);
        return localTypeDecls_String_value;
    }

    private SimpleSet localTypeDecls_compute(String name) {
    SimpleSet set = SimpleSet.emptySet;
    for(int i = 0; i < getNumBodyDecl(); i++)
      if(getBodyDecl(i).declaresType(name))
        set = set.add(getBodyDecl(i).type(name));
    return set;
  }

    protected java.util.Map memberTypes_String_visited;
    protected java.util.Map memberTypes_String_values;
    // Declared in LookupType.jrag at line 409
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet memberTypes(String name) {
        Object _parameters = name;
if(memberTypes_String_visited == null) memberTypes_String_visited = new java.util.HashMap(4);
if(memberTypes_String_values == null) memberTypes_String_values = new java.util.HashMap(4);
        if(memberTypes_String_values.containsKey(_parameters)) {
            return (SimpleSet)memberTypes_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(memberTypes_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: memberTypes in class: ");
        memberTypes_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet memberTypes_String_value = memberTypes_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            memberTypes_String_values.put(_parameters, memberTypes_String_value);
        memberTypes_String_visited.remove(_parameters);
        return memberTypes_String_value;
    }

    private SimpleSet memberTypes_compute(String name) {  return SimpleSet.emptySet;  }

    protected java.util.Map localFields_String_visited;
    protected java.util.Map localFields_String_values;
    // Declared in LookupVariable.jrag at line 255
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet localFields(String name) {
        Object _parameters = name;
if(localFields_String_visited == null) localFields_String_visited = new java.util.HashMap(4);
if(localFields_String_values == null) localFields_String_values = new java.util.HashMap(4);
        if(localFields_String_values.containsKey(_parameters)) {
            return (SimpleSet)localFields_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(localFields_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: localFields in class: ");
        localFields_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet localFields_String_value = localFields_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            localFields_String_values.put(_parameters, localFields_String_value);
        localFields_String_visited.remove(_parameters);
        return localFields_String_value;
    }

    private SimpleSet localFields_compute(String name) {  return localFieldsMap().containsKey(name) ? (SimpleSet)localFieldsMap().get(name) : SimpleSet.emptySet;  }

    protected int localFieldsMap_visited = -1;
    protected boolean localFieldsMap_computed = false;
    protected HashMap localFieldsMap_value;
    // Declared in LookupVariable.jrag at line 258
 @SuppressWarnings({"unchecked", "cast"})     public HashMap localFieldsMap() {
        if(localFieldsMap_computed) {
            return localFieldsMap_value;
        }
        ASTNode$State state = state();
        if(localFieldsMap_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: localFieldsMap in class: ");
        localFieldsMap_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        localFieldsMap_value = localFieldsMap_compute();
        if(isFinal && num == state().boundariesCrossed)
            localFieldsMap_computed = true;
        localFieldsMap_visited = -1;
        return localFieldsMap_value;
    }

    private HashMap localFieldsMap_compute() {
    HashMap map = new HashMap();
    for(int i = 0; i < getNumBodyDecl(); i++) {
      if(getBodyDecl(i) instanceof FieldDeclaration) {
        FieldDeclaration decl = (FieldDeclaration)getBodyDecl(i);
        SimpleSet fields = (SimpleSet)map.get(decl.name());
        if(fields == null) fields = SimpleSet.emptySet;
        fields = fields.add(decl);
        map.put(decl.name(), fields);
      }
    }
    return map;
  }

    protected int memberFieldsMap_visited = -1;
    protected boolean memberFieldsMap_computed = false;
    protected HashMap memberFieldsMap_value;
    // Declared in LookupVariable.jrag at line 271
 @SuppressWarnings({"unchecked", "cast"})     public HashMap memberFieldsMap() {
        if(memberFieldsMap_computed) {
            return memberFieldsMap_value;
        }
        ASTNode$State state = state();
        if(memberFieldsMap_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: memberFieldsMap in class: ");
        memberFieldsMap_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        memberFieldsMap_value = memberFieldsMap_compute();
        if(isFinal && num == state().boundariesCrossed)
            memberFieldsMap_computed = true;
        memberFieldsMap_visited = -1;
        return memberFieldsMap_value;
    }

    private HashMap memberFieldsMap_compute() {  return localFieldsMap();  }

    protected java.util.Map memberFields_String_visited;
    protected java.util.Map memberFields_String_values;
    // Declared in LookupVariable.jrag at line 320
 @SuppressWarnings({"unchecked", "cast"})     public SimpleSet memberFields(String name) {
        Object _parameters = name;
if(memberFields_String_visited == null) memberFields_String_visited = new java.util.HashMap(4);
if(memberFields_String_values == null) memberFields_String_values = new java.util.HashMap(4);
        if(memberFields_String_values.containsKey(_parameters)) {
            return (SimpleSet)memberFields_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(memberFields_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: memberFields in class: ");
        memberFields_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        SimpleSet memberFields_String_value = memberFields_compute(name);
        if(isFinal && num == state().boundariesCrossed)
            memberFields_String_values.put(_parameters, memberFields_String_value);
        memberFields_String_visited.remove(_parameters);
        return memberFields_String_value;
    }

    private SimpleSet memberFields_compute(String name) {  return localFields(name);  }

    protected int hasAbstract_visited = -1;
    protected boolean hasAbstract_computed = false;
    protected boolean hasAbstract_value;
    // Declared in Modifiers.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasAbstract() {
        if(hasAbstract_computed) {
            return hasAbstract_value;
        }
        ASTNode$State state = state();
        if(hasAbstract_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hasAbstract in class: ");
        hasAbstract_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        hasAbstract_value = hasAbstract_compute();
        if(isFinal && num == state().boundariesCrossed)
            hasAbstract_computed = true;
        hasAbstract_visited = -1;
        return hasAbstract_value;
    }

    private boolean hasAbstract_compute() {  return false;  }

    protected int unimplementedMethods_visited = -1;
    protected boolean unimplementedMethods_computed = false;
    protected Collection unimplementedMethods_value;
    // Declared in Modifiers.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public Collection unimplementedMethods() {
        if(unimplementedMethods_computed) {
            return unimplementedMethods_value;
        }
        ASTNode$State state = state();
        if(unimplementedMethods_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unimplementedMethods in class: ");
        unimplementedMethods_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        unimplementedMethods_value = unimplementedMethods_compute();
        if(isFinal && num == state().boundariesCrossed)
            unimplementedMethods_computed = true;
        unimplementedMethods_visited = -1;
        return unimplementedMethods_value;
    }

    private Collection unimplementedMethods_compute() {  return Collections.EMPTY_LIST;  }

    protected int isPublic_visited = -1;
    protected boolean isPublic_computed = false;
    protected boolean isPublic_value;
    // Declared in Modifiers.jrag at line 198
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

    private boolean isPublic_compute() {  return getModifiers().isPublic() || isMemberType() && enclosingType().isInterfaceDecl();  }

    protected int isPrivate_visited = -1;
    // Declared in Modifiers.jrag at line 200
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrivate() {
        ASTNode$State state = state();
        if(isPrivate_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrivate in class: ");
        isPrivate_visited = state().boundariesCrossed;
        boolean isPrivate_value = isPrivate_compute();
        isPrivate_visited = -1;
        return isPrivate_value;
    }

    private boolean isPrivate_compute() {  return getModifiers().isPrivate();  }

    protected int isProtected_visited = -1;
    // Declared in Modifiers.jrag at line 201
 @SuppressWarnings({"unchecked", "cast"})     public boolean isProtected() {
        ASTNode$State state = state();
        if(isProtected_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isProtected in class: ");
        isProtected_visited = state().boundariesCrossed;
        boolean isProtected_value = isProtected_compute();
        isProtected_visited = -1;
        return isProtected_value;
    }

    private boolean isProtected_compute() {  return getModifiers().isProtected();  }

    protected int isAbstract_visited = -1;
    // Declared in Modifiers.jrag at line 202
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAbstract() {
        ASTNode$State state = state();
        if(isAbstract_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isAbstract in class: ");
        isAbstract_visited = state().boundariesCrossed;
        boolean isAbstract_value = isAbstract_compute();
        isAbstract_visited = -1;
        return isAbstract_value;
    }

    private boolean isAbstract_compute() {  return getModifiers().isAbstract();  }

    protected int isStatic_visited = -1;
    protected boolean isStatic_computed = false;
    protected boolean isStatic_value;
    // Declared in Modifiers.jrag at line 204
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

    private boolean isStatic_compute() {  return getModifiers().isStatic() || isMemberType() && enclosingType().isInterfaceDecl();  }

    protected int isFinal_visited = -1;
    // Declared in Modifiers.jrag at line 207
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFinal() {
        ASTNode$State state = state();
        if(isFinal_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFinal in class: ");
        isFinal_visited = state().boundariesCrossed;
        boolean isFinal_value = isFinal_compute();
        isFinal_visited = -1;
        return isFinal_value;
    }

    private boolean isFinal_compute() {  return getModifiers().isFinal();  }

    protected int isStrictfp_visited = -1;
    // Declared in Modifiers.jrag at line 208
 @SuppressWarnings({"unchecked", "cast"})     public boolean isStrictfp() {
        ASTNode$State state = state();
        if(isStrictfp_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isStrictfp in class: ");
        isStrictfp_visited = state().boundariesCrossed;
        boolean isStrictfp_value = isStrictfp_compute();
        isStrictfp_visited = -1;
        return isStrictfp_value;
    }

    private boolean isStrictfp_compute() {  return getModifiers().isStrictfp();  }

    protected int isSynthetic_visited = -1;
    // Declared in Modifiers.jrag at line 210
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSynthetic() {
        ASTNode$State state = state();
        if(isSynthetic_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isSynthetic in class: ");
        isSynthetic_visited = state().boundariesCrossed;
        boolean isSynthetic_value = isSynthetic_compute();
        isSynthetic_visited = -1;
        return isSynthetic_value;
    }

    private boolean isSynthetic_compute() {  return getModifiers().isSynthetic();  }

    protected java.util.Map hasEnclosingTypeDecl_String_visited;
    // Declared in NameCheck.jrag at line 269
 @SuppressWarnings({"unchecked", "cast"})     public boolean hasEnclosingTypeDecl(String name) {
        Object _parameters = name;
if(hasEnclosingTypeDecl_String_visited == null) hasEnclosingTypeDecl_String_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(hasEnclosingTypeDecl_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: hasEnclosingTypeDecl in class: ");
        hasEnclosingTypeDecl_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean hasEnclosingTypeDecl_String_value = hasEnclosingTypeDecl_compute(name);
        hasEnclosingTypeDecl_String_visited.remove(_parameters);
        return hasEnclosingTypeDecl_String_value;
    }

    private boolean hasEnclosingTypeDecl_compute(String name) {
    TypeDecl enclosingType = enclosingType();
    if(enclosingType != null) {
      return enclosingType.name().equals(name) || enclosingType.hasEnclosingTypeDecl(name);
    }
    return false;
  }

    protected int assignableToInt_visited = -1;
    // Declared in NameCheck.jrag at line 422
 @SuppressWarnings({"unchecked", "cast"})     public boolean assignableToInt() {
        ASTNode$State state = state();
        if(assignableToInt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: assignableToInt in class: ");
        assignableToInt_visited = state().boundariesCrossed;
        boolean assignableToInt_value = assignableToInt_compute();
        assignableToInt_visited = -1;
        return assignableToInt_value;
    }

    private boolean assignableToInt_compute() {  return false;  }

    protected int addsIndentationLevel_visited = -1;
    // Declared in PrettyPrint.jadd at line 758
 @SuppressWarnings({"unchecked", "cast"})     public boolean addsIndentationLevel() {
        ASTNode$State state = state();
        if(addsIndentationLevel_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: addsIndentationLevel in class: ");
        addsIndentationLevel_visited = state().boundariesCrossed;
        boolean addsIndentationLevel_value = addsIndentationLevel_compute();
        addsIndentationLevel_visited = -1;
        return addsIndentationLevel_value;
    }

    private boolean addsIndentationLevel_compute() {  return true;  }

    protected int dumpString_visited = -1;
    // Declared in PrettyPrint.jadd at line 809
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
    // Declared in QualifiedNames.jrag at line 68
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

    protected int fullName_visited = -1;
    protected boolean fullName_computed = false;
    protected String fullName_value;
    // Declared in QualifiedNames.jrag at line 70
 @SuppressWarnings({"unchecked", "cast"})     public String fullName() {
        if(fullName_computed) {
            return fullName_value;
        }
        ASTNode$State state = state();
        if(fullName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: fullName in class: ");
        fullName_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        fullName_value = fullName_compute();
        if(isFinal && num == state().boundariesCrossed)
            fullName_computed = true;
        fullName_visited = -1;
        return fullName_value;
    }

    private String fullName_compute() {
    if(isNestedType())
      return enclosingType().fullName() + "." + name();
    String packageName = packageName();
    if(packageName.equals(""))
      return name();
    return packageName + "." + name();
  }

    protected int typeName_visited = -1;
    protected boolean typeName_computed = false;
    protected String typeName_value;
    // Declared in QualifiedNames.jrag at line 79
 @SuppressWarnings({"unchecked", "cast"})     public String typeName() {
        if(typeName_computed) {
            return typeName_value;
        }
        ASTNode$State state = state();
        if(typeName_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeName in class: ");
        typeName_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        typeName_value = typeName_compute();
        if(isFinal && num == state().boundariesCrossed)
            typeName_computed = true;
        typeName_visited = -1;
        return typeName_value;
    }

    private String typeName_compute() {
    if(isNestedType())
      return enclosingType().typeName() + "." + name();
    String packageName = packageName();
    if(packageName.equals("") || packageName.equals(PRIMITIVE_PACKAGE_NAME))
      return name();
    return packageName + "." + name();
  }

    protected java.util.Map identityConversionTo_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 15
 @SuppressWarnings({"unchecked", "cast"})     public boolean identityConversionTo(TypeDecl type) {
        Object _parameters = type;
if(identityConversionTo_TypeDecl_visited == null) identityConversionTo_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(identityConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: identityConversionTo in class: ");
        identityConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean identityConversionTo_TypeDecl_value = identityConversionTo_compute(type);
        identityConversionTo_TypeDecl_visited.remove(_parameters);
        return identityConversionTo_TypeDecl_value;
    }

    private boolean identityConversionTo_compute(TypeDecl type) {  return this == type;  }

    protected java.util.Map wideningConversionTo_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 17
 @SuppressWarnings({"unchecked", "cast"})     public boolean wideningConversionTo(TypeDecl type) {
        Object _parameters = type;
if(wideningConversionTo_TypeDecl_visited == null) wideningConversionTo_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(wideningConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: wideningConversionTo in class: ");
        wideningConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean wideningConversionTo_TypeDecl_value = wideningConversionTo_compute(type);
        wideningConversionTo_TypeDecl_visited.remove(_parameters);
        return wideningConversionTo_TypeDecl_value;
    }

    private boolean wideningConversionTo_compute(TypeDecl type) {  return instanceOf(type);  }

    protected java.util.Map narrowingConversionTo_TypeDecl_visited;
    protected java.util.Map narrowingConversionTo_TypeDecl_values;
    // Declared in TypeAnalysis.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public boolean narrowingConversionTo(TypeDecl type) {
        Object _parameters = type;
if(narrowingConversionTo_TypeDecl_visited == null) narrowingConversionTo_TypeDecl_visited = new java.util.HashMap(4);
if(narrowingConversionTo_TypeDecl_values == null) narrowingConversionTo_TypeDecl_values = new java.util.HashMap(4);
        if(narrowingConversionTo_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)narrowingConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(narrowingConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: narrowingConversionTo in class: ");
        narrowingConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean narrowingConversionTo_TypeDecl_value = narrowingConversionTo_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            narrowingConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(narrowingConversionTo_TypeDecl_value));
        narrowingConversionTo_TypeDecl_visited.remove(_parameters);
        return narrowingConversionTo_TypeDecl_value;
    }

    private boolean narrowingConversionTo_compute(TypeDecl type) {  return instanceOf(type);  }

    protected int stringConversion_visited = -1;
    // Declared in TypeAnalysis.jrag at line 55
 @SuppressWarnings({"unchecked", "cast"})     public boolean stringConversion() {
        ASTNode$State state = state();
        if(stringConversion_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: stringConversion in class: ");
        stringConversion_visited = state().boundariesCrossed;
        boolean stringConversion_value = stringConversion_compute();
        stringConversion_visited = -1;
        return stringConversion_value;
    }

    private boolean stringConversion_compute() {  return true;  }

    protected java.util.Map assignConversionTo_TypeDecl_Expr_visited;
    // Declared in TypeAnalysis.jrag at line 59
 @SuppressWarnings({"unchecked", "cast"})     public boolean assignConversionTo(TypeDecl type, Expr expr) {
        java.util.List _parameters = new java.util.ArrayList(2);
        _parameters.add(type);
        _parameters.add(expr);
if(assignConversionTo_TypeDecl_Expr_visited == null) assignConversionTo_TypeDecl_Expr_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(assignConversionTo_TypeDecl_Expr_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: assignConversionTo in class: ");
        assignConversionTo_TypeDecl_Expr_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean assignConversionTo_TypeDecl_Expr_value = assignConversionTo_compute(type, expr);
        assignConversionTo_TypeDecl_Expr_visited.remove(_parameters);
        return assignConversionTo_TypeDecl_Expr_value;
    }

    private boolean assignConversionTo_compute(TypeDecl type, Expr expr) {
    //System.out.println("@@@ " + fullName() + " assign conversion to " + type.fullName() + ", expr: " + expr);
    boolean sourceIsConstant = expr != null ? expr.isConstant() : false;
    //System.out.println("@@@ sourceIsConstant: " + sourceIsConstant);
    if(identityConversionTo(type) || wideningConversionTo(type))
      return true;
    //System.out.println("@@@ narrowing conversion needed");
    //System.out.println("@@@ value: " + expr.value());
    if(sourceIsConstant && (isInt() || isChar() || isShort() || isByte()) &&
        (type.isByte() || type.isShort() || type.isChar()) &&
        narrowingConversionTo(type) && expr.representableIn(type))
      return true;
    //System.out.println("@@@ false");
    return false;
  }

    protected java.util.Map methodInvocationConversionTo_TypeDecl_visited;
    protected java.util.Map methodInvocationConversionTo_TypeDecl_values;
    // Declared in TypeAnalysis.jrag at line 76
 @SuppressWarnings({"unchecked", "cast"})     public boolean methodInvocationConversionTo(TypeDecl type) {
        Object _parameters = type;
if(methodInvocationConversionTo_TypeDecl_visited == null) methodInvocationConversionTo_TypeDecl_visited = new java.util.HashMap(4);
if(methodInvocationConversionTo_TypeDecl_values == null) methodInvocationConversionTo_TypeDecl_values = new java.util.HashMap(4);
        if(methodInvocationConversionTo_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)methodInvocationConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(methodInvocationConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: methodInvocationConversionTo in class: ");
        methodInvocationConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean methodInvocationConversionTo_TypeDecl_value = methodInvocationConversionTo_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            methodInvocationConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(methodInvocationConversionTo_TypeDecl_value));
        methodInvocationConversionTo_TypeDecl_visited.remove(_parameters);
        return methodInvocationConversionTo_TypeDecl_value;
    }

    private boolean methodInvocationConversionTo_compute(TypeDecl type) {
    return identityConversionTo(type) || wideningConversionTo(type);
  }

    protected java.util.Map castingConversionTo_TypeDecl_visited;
    protected java.util.Map castingConversionTo_TypeDecl_values;
    // Declared in TypeAnalysis.jrag at line 81
 @SuppressWarnings({"unchecked", "cast"})     public boolean castingConversionTo(TypeDecl type) {
        Object _parameters = type;
if(castingConversionTo_TypeDecl_visited == null) castingConversionTo_TypeDecl_visited = new java.util.HashMap(4);
if(castingConversionTo_TypeDecl_values == null) castingConversionTo_TypeDecl_values = new java.util.HashMap(4);
        if(castingConversionTo_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)castingConversionTo_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(castingConversionTo_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: castingConversionTo in class: ");
        castingConversionTo_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean castingConversionTo_TypeDecl_value = castingConversionTo_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            castingConversionTo_TypeDecl_values.put(_parameters, Boolean.valueOf(castingConversionTo_TypeDecl_value));
        castingConversionTo_TypeDecl_visited.remove(_parameters);
        return castingConversionTo_TypeDecl_value;
    }

    private boolean castingConversionTo_compute(TypeDecl type) {  return identityConversionTo(type) ||
    wideningConversionTo(type) || narrowingConversionTo(type);  }

    protected int unaryNumericPromotion_visited = -1;
    // Declared in TypeAnalysis.jrag at line 146
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unaryNumericPromotion() {
        ASTNode$State state = state();
        if(unaryNumericPromotion_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unaryNumericPromotion in class: ");
        unaryNumericPromotion_visited = state().boundariesCrossed;
        TypeDecl unaryNumericPromotion_value = unaryNumericPromotion_compute();
        unaryNumericPromotion_visited = -1;
        return unaryNumericPromotion_value;
    }

    private TypeDecl unaryNumericPromotion_compute() {  return this;  }

    protected java.util.Map binaryNumericPromotion_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 154
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl binaryNumericPromotion(TypeDecl type) {
        Object _parameters = type;
if(binaryNumericPromotion_TypeDecl_visited == null) binaryNumericPromotion_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(binaryNumericPromotion_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: binaryNumericPromotion in class: ");
        binaryNumericPromotion_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        TypeDecl binaryNumericPromotion_TypeDecl_value = binaryNumericPromotion_compute(type);
        binaryNumericPromotion_TypeDecl_visited.remove(_parameters);
        return binaryNumericPromotion_TypeDecl_value;
    }

    private TypeDecl binaryNumericPromotion_compute(TypeDecl type) {  return unknownType();  }

    protected int isReferenceType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 165
 @SuppressWarnings({"unchecked", "cast"})     public boolean isReferenceType() {
        ASTNode$State state = state();
        if(isReferenceType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isReferenceType in class: ");
        isReferenceType_visited = state().boundariesCrossed;
        boolean isReferenceType_value = isReferenceType_compute();
        isReferenceType_visited = -1;
        return isReferenceType_value;
    }

    private boolean isReferenceType_compute() {  return false;  }

    protected int isPrimitiveType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 168
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrimitiveType() {
        ASTNode$State state = state();
        if(isPrimitiveType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrimitiveType in class: ");
        isPrimitiveType_visited = state().boundariesCrossed;
        boolean isPrimitiveType_value = isPrimitiveType_compute();
        isPrimitiveType_visited = -1;
        return isPrimitiveType_value;
    }

    private boolean isPrimitiveType_compute() {  return false;  }

    protected int isNumericType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 173
 @SuppressWarnings({"unchecked", "cast"})     public boolean isNumericType() {
        ASTNode$State state = state();
        if(isNumericType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isNumericType in class: ");
        isNumericType_visited = state().boundariesCrossed;
        boolean isNumericType_value = isNumericType_compute();
        isNumericType_visited = -1;
        return isNumericType_value;
    }

    private boolean isNumericType_compute() {  return false;  }

    protected int isIntegralType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 177
 @SuppressWarnings({"unchecked", "cast"})     public boolean isIntegralType() {
        ASTNode$State state = state();
        if(isIntegralType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isIntegralType in class: ");
        isIntegralType_visited = state().boundariesCrossed;
        boolean isIntegralType_value = isIntegralType_compute();
        isIntegralType_visited = -1;
        return isIntegralType_value;
    }

    private boolean isIntegralType_compute() {  return false;  }

    protected int isBoolean_visited = -1;
    // Declared in TypeAnalysis.jrag at line 181
 @SuppressWarnings({"unchecked", "cast"})     public boolean isBoolean() {
        ASTNode$State state = state();
        if(isBoolean_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isBoolean in class: ");
        isBoolean_visited = state().boundariesCrossed;
        boolean isBoolean_value = isBoolean_compute();
        isBoolean_visited = -1;
        return isBoolean_value;
    }

    private boolean isBoolean_compute() {  return false;  }

    protected int isByte_visited = -1;
    // Declared in TypeAnalysis.jrag at line 185
 @SuppressWarnings({"unchecked", "cast"})     public boolean isByte() {
        ASTNode$State state = state();
        if(isByte_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isByte in class: ");
        isByte_visited = state().boundariesCrossed;
        boolean isByte_value = isByte_compute();
        isByte_visited = -1;
        return isByte_value;
    }

    private boolean isByte_compute() {  return false;  }

    protected int isChar_visited = -1;
    // Declared in TypeAnalysis.jrag at line 187
 @SuppressWarnings({"unchecked", "cast"})     public boolean isChar() {
        ASTNode$State state = state();
        if(isChar_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isChar in class: ");
        isChar_visited = state().boundariesCrossed;
        boolean isChar_value = isChar_compute();
        isChar_visited = -1;
        return isChar_value;
    }

    private boolean isChar_compute() {  return false;  }

    protected int isShort_visited = -1;
    // Declared in TypeAnalysis.jrag at line 189
 @SuppressWarnings({"unchecked", "cast"})     public boolean isShort() {
        ASTNode$State state = state();
        if(isShort_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isShort in class: ");
        isShort_visited = state().boundariesCrossed;
        boolean isShort_value = isShort_compute();
        isShort_visited = -1;
        return isShort_value;
    }

    private boolean isShort_compute() {  return false;  }

    protected int isInt_visited = -1;
    // Declared in TypeAnalysis.jrag at line 191
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInt() {
        ASTNode$State state = state();
        if(isInt_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isInt in class: ");
        isInt_visited = state().boundariesCrossed;
        boolean isInt_value = isInt_compute();
        isInt_visited = -1;
        return isInt_value;
    }

    private boolean isInt_compute() {  return false;  }

    protected int isFloat_visited = -1;
    // Declared in TypeAnalysis.jrag at line 195
 @SuppressWarnings({"unchecked", "cast"})     public boolean isFloat() {
        ASTNode$State state = state();
        if(isFloat_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isFloat in class: ");
        isFloat_visited = state().boundariesCrossed;
        boolean isFloat_value = isFloat_compute();
        isFloat_visited = -1;
        return isFloat_value;
    }

    private boolean isFloat_compute() {  return false;  }

    protected int isLong_visited = -1;
    // Declared in TypeAnalysis.jrag at line 197
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLong() {
        ASTNode$State state = state();
        if(isLong_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isLong in class: ");
        isLong_visited = state().boundariesCrossed;
        boolean isLong_value = isLong_compute();
        isLong_visited = -1;
        return isLong_value;
    }

    private boolean isLong_compute() {  return false;  }

    protected int isDouble_visited = -1;
    // Declared in TypeAnalysis.jrag at line 199
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDouble() {
        ASTNode$State state = state();
        if(isDouble_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isDouble in class: ");
        isDouble_visited = state().boundariesCrossed;
        boolean isDouble_value = isDouble_compute();
        isDouble_visited = -1;
        return isDouble_value;
    }

    private boolean isDouble_compute() {  return false;  }

    protected int isVoid_visited = -1;
    // Declared in TypeAnalysis.jrag at line 202
 @SuppressWarnings({"unchecked", "cast"})     public boolean isVoid() {
        ASTNode$State state = state();
        if(isVoid_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isVoid in class: ");
        isVoid_visited = state().boundariesCrossed;
        boolean isVoid_value = isVoid_compute();
        isVoid_visited = -1;
        return isVoid_value;
    }

    private boolean isVoid_compute() {  return false;  }

    protected int isNull_visited = -1;
    // Declared in TypeAnalysis.jrag at line 205
 @SuppressWarnings({"unchecked", "cast"})     public boolean isNull() {
        ASTNode$State state = state();
        if(isNull_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isNull in class: ");
        isNull_visited = state().boundariesCrossed;
        boolean isNull_value = isNull_compute();
        isNull_visited = -1;
        return isNull_value;
    }

    private boolean isNull_compute() {  return false;  }

    protected int isClassDecl_visited = -1;
    // Declared in TypeAnalysis.jrag at line 209
 @SuppressWarnings({"unchecked", "cast"})     public boolean isClassDecl() {
        ASTNode$State state = state();
        if(isClassDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isClassDecl in class: ");
        isClassDecl_visited = state().boundariesCrossed;
        boolean isClassDecl_value = isClassDecl_compute();
        isClassDecl_visited = -1;
        return isClassDecl_value;
    }

    private boolean isClassDecl_compute() {  return false;  }

    protected int isInterfaceDecl_visited = -1;
    // Declared in TypeAnalysis.jrag at line 211
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInterfaceDecl() {
        ASTNode$State state = state();
        if(isInterfaceDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isInterfaceDecl in class: ");
        isInterfaceDecl_visited = state().boundariesCrossed;
        boolean isInterfaceDecl_value = isInterfaceDecl_compute();
        isInterfaceDecl_visited = -1;
        return isInterfaceDecl_value;
    }

    private boolean isInterfaceDecl_compute() {  return false;  }

    protected int isArrayDecl_visited = -1;
    // Declared in TypeAnalysis.jrag at line 213
 @SuppressWarnings({"unchecked", "cast"})     public boolean isArrayDecl() {
        ASTNode$State state = state();
        if(isArrayDecl_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isArrayDecl in class: ");
        isArrayDecl_visited = state().boundariesCrossed;
        boolean isArrayDecl_value = isArrayDecl_compute();
        isArrayDecl_visited = -1;
        return isArrayDecl_value;
    }

    private boolean isArrayDecl_compute() {  return false;  }

    protected int isPrimitive_visited = -1;
    // Declared in TypeAnalysis.jrag at line 221
 @SuppressWarnings({"unchecked", "cast"})     public boolean isPrimitive() {
        ASTNode$State state = state();
        if(isPrimitive_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isPrimitive in class: ");
        isPrimitive_visited = state().boundariesCrossed;
        boolean isPrimitive_value = isPrimitive_compute();
        isPrimitive_visited = -1;
        return isPrimitive_value;
    }

    private boolean isPrimitive_compute() {  return false;  }

    protected int isString_visited = -1;
    protected boolean isString_computed = false;
    protected boolean isString_value;
    // Declared in TypeAnalysis.jrag at line 224
 @SuppressWarnings({"unchecked", "cast"})     public boolean isString() {
        if(isString_computed) {
            return isString_value;
        }
        ASTNode$State state = state();
        if(isString_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isString in class: ");
        isString_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isString_value = isString_compute();
        if(isFinal && num == state().boundariesCrossed)
            isString_computed = true;
        isString_visited = -1;
        return isString_value;
    }

    private boolean isString_compute() {  return false;  }

    protected int isObject_visited = -1;
    protected boolean isObject_computed = false;
    protected boolean isObject_value;
    // Declared in TypeAnalysis.jrag at line 227
 @SuppressWarnings({"unchecked", "cast"})     public boolean isObject() {
        if(isObject_computed) {
            return isObject_value;
        }
        ASTNode$State state = state();
        if(isObject_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isObject in class: ");
        isObject_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        isObject_value = isObject_compute();
        if(isFinal && num == state().boundariesCrossed)
            isObject_computed = true;
        isObject_visited = -1;
        return isObject_value;
    }

    private boolean isObject_compute() {  return false;  }

    protected int isUnknown_visited = -1;
    // Declared in TypeAnalysis.jrag at line 230
 @SuppressWarnings({"unchecked", "cast"})     public boolean isUnknown() {
        ASTNode$State state = state();
        if(isUnknown_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isUnknown in class: ");
        isUnknown_visited = state().boundariesCrossed;
        boolean isUnknown_value = isUnknown_compute();
        isUnknown_visited = -1;
        return isUnknown_value;
    }

    private boolean isUnknown_compute() {  return false;  }

    protected java.util.Map instanceOf_TypeDecl_visited;
    protected java.util.Map instanceOf_TypeDecl_values;
    // Declared in TypeAnalysis.jrag at line 408
 @SuppressWarnings({"unchecked", "cast"})     public boolean instanceOf(TypeDecl type) {
        Object _parameters = type;
if(instanceOf_TypeDecl_visited == null) instanceOf_TypeDecl_visited = new java.util.HashMap(4);
if(instanceOf_TypeDecl_values == null) instanceOf_TypeDecl_values = new java.util.HashMap(4);
        if(instanceOf_TypeDecl_values.containsKey(_parameters)) {
            return ((Boolean)instanceOf_TypeDecl_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(instanceOf_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: instanceOf in class: ");
        instanceOf_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        boolean instanceOf_TypeDecl_value = instanceOf_compute(type);
        if(isFinal && num == state().boundariesCrossed)
            instanceOf_TypeDecl_values.put(_parameters, Boolean.valueOf(instanceOf_TypeDecl_value));
        instanceOf_TypeDecl_visited.remove(_parameters);
        return instanceOf_TypeDecl_value;
    }

    private boolean instanceOf_compute(TypeDecl type) {  return type == this;  }

    protected java.util.Map isSupertypeOfClassDecl_ClassDecl_visited;
    // Declared in TypeAnalysis.jrag at line 423
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfClassDecl(ClassDecl type) {
        Object _parameters = type;
if(isSupertypeOfClassDecl_ClassDecl_visited == null) isSupertypeOfClassDecl_ClassDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfClassDecl_ClassDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfClassDecl in class: ");
        isSupertypeOfClassDecl_ClassDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfClassDecl_ClassDecl_value = isSupertypeOfClassDecl_compute(type);
        isSupertypeOfClassDecl_ClassDecl_visited.remove(_parameters);
        return isSupertypeOfClassDecl_ClassDecl_value;
    }

    private boolean isSupertypeOfClassDecl_compute(ClassDecl type) {  return type == this;  }

    protected java.util.Map isSupertypeOfInterfaceDecl_InterfaceDecl_visited;
    // Declared in TypeAnalysis.jrag at line 440
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfInterfaceDecl(InterfaceDecl type) {
        Object _parameters = type;
if(isSupertypeOfInterfaceDecl_InterfaceDecl_visited == null) isSupertypeOfInterfaceDecl_InterfaceDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfInterfaceDecl_InterfaceDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfInterfaceDecl in class: ");
        isSupertypeOfInterfaceDecl_InterfaceDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfInterfaceDecl_InterfaceDecl_value = isSupertypeOfInterfaceDecl_compute(type);
        isSupertypeOfInterfaceDecl_InterfaceDecl_visited.remove(_parameters);
        return isSupertypeOfInterfaceDecl_InterfaceDecl_value;
    }

    private boolean isSupertypeOfInterfaceDecl_compute(InterfaceDecl type) {  return type == this;  }

    protected java.util.Map isSupertypeOfArrayDecl_ArrayDecl_visited;
    // Declared in TypeAnalysis.jrag at line 453
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfArrayDecl(ArrayDecl type) {
        Object _parameters = type;
if(isSupertypeOfArrayDecl_ArrayDecl_visited == null) isSupertypeOfArrayDecl_ArrayDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfArrayDecl_ArrayDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfArrayDecl in class: ");
        isSupertypeOfArrayDecl_ArrayDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfArrayDecl_ArrayDecl_value = isSupertypeOfArrayDecl_compute(type);
        isSupertypeOfArrayDecl_ArrayDecl_visited.remove(_parameters);
        return isSupertypeOfArrayDecl_ArrayDecl_value;
    }

    private boolean isSupertypeOfArrayDecl_compute(ArrayDecl type) {  return this == type;  }

    protected java.util.Map isSupertypeOfPrimitiveType_PrimitiveType_visited;
    // Declared in TypeAnalysis.jrag at line 475
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfPrimitiveType(PrimitiveType type) {
        Object _parameters = type;
if(isSupertypeOfPrimitiveType_PrimitiveType_visited == null) isSupertypeOfPrimitiveType_PrimitiveType_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfPrimitiveType_PrimitiveType_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfPrimitiveType in class: ");
        isSupertypeOfPrimitiveType_PrimitiveType_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfPrimitiveType_PrimitiveType_value = isSupertypeOfPrimitiveType_compute(type);
        isSupertypeOfPrimitiveType_PrimitiveType_visited.remove(_parameters);
        return isSupertypeOfPrimitiveType_PrimitiveType_value;
    }

    private boolean isSupertypeOfPrimitiveType_compute(PrimitiveType type) {  return type == this;  }

    protected java.util.Map isSupertypeOfNullType_NullType_visited;
    // Declared in TypeAnalysis.jrag at line 482
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfNullType(NullType type) {
        Object _parameters = type;
if(isSupertypeOfNullType_NullType_visited == null) isSupertypeOfNullType_NullType_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfNullType_NullType_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfNullType in class: ");
        isSupertypeOfNullType_NullType_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfNullType_NullType_value = isSupertypeOfNullType_compute(type);
        isSupertypeOfNullType_NullType_visited.remove(_parameters);
        return isSupertypeOfNullType_NullType_value;
    }

    private boolean isSupertypeOfNullType_compute(NullType type) {  return false;  }

    protected java.util.Map isSupertypeOfVoidType_VoidType_visited;
    // Declared in TypeAnalysis.jrag at line 486
 @SuppressWarnings({"unchecked", "cast"})     public boolean isSupertypeOfVoidType(VoidType type) {
        Object _parameters = type;
if(isSupertypeOfVoidType_VoidType_visited == null) isSupertypeOfVoidType_VoidType_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isSupertypeOfVoidType_VoidType_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isSupertypeOfVoidType in class: ");
        isSupertypeOfVoidType_VoidType_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isSupertypeOfVoidType_VoidType_value = isSupertypeOfVoidType_compute(type);
        isSupertypeOfVoidType_VoidType_visited.remove(_parameters);
        return isSupertypeOfVoidType_VoidType_value;
    }

    private boolean isSupertypeOfVoidType_compute(VoidType type) {  return false;  }

    protected int topLevelType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 498
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl topLevelType() {
        ASTNode$State state = state();
        if(topLevelType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: topLevelType in class: ");
        topLevelType_visited = state().boundariesCrossed;
        TypeDecl topLevelType_value = topLevelType_compute();
        topLevelType_visited = -1;
        return topLevelType_value;
    }

    private TypeDecl topLevelType_compute() {
    if(isTopLevelType())
      return this;
    return enclosingType().topLevelType();
  }

    protected int isTopLevelType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 524
 @SuppressWarnings({"unchecked", "cast"})     public boolean isTopLevelType() {
        ASTNode$State state = state();
        if(isTopLevelType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isTopLevelType in class: ");
        isTopLevelType_visited = state().boundariesCrossed;
        boolean isTopLevelType_value = isTopLevelType_compute();
        isTopLevelType_visited = -1;
        return isTopLevelType_value;
    }

    private boolean isTopLevelType_compute() {  return !isNestedType();  }

    protected int isInnerClass_visited = -1;
    // Declared in TypeAnalysis.jrag at line 535
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInnerClass() {
        ASTNode$State state = state();
        if(isInnerClass_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isInnerClass in class: ");
        isInnerClass_visited = state().boundariesCrossed;
        boolean isInnerClass_value = isInnerClass_compute();
        isInnerClass_visited = -1;
        return isInnerClass_value;
    }

    private boolean isInnerClass_compute() {  return false;  }

    protected int isInnerType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 537
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInnerType() {
        ASTNode$State state = state();
        if(isInnerType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isInnerType in class: ");
        isInnerType_visited = state().boundariesCrossed;
        boolean isInnerType_value = isInnerType_compute();
        isInnerType_visited = -1;
        return isInnerType_value;
    }

    private boolean isInnerType_compute() {  return (isLocalClass() || isAnonymous() || (isMemberType() && !isStatic())) && !inStaticContext();  }

    protected java.util.Map isInnerTypeOf_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 539
 @SuppressWarnings({"unchecked", "cast"})     public boolean isInnerTypeOf(TypeDecl typeDecl) {
        Object _parameters = typeDecl;
if(isInnerTypeOf_TypeDecl_visited == null) isInnerTypeOf_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isInnerTypeOf_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isInnerTypeOf in class: ");
        isInnerTypeOf_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean isInnerTypeOf_TypeDecl_value = isInnerTypeOf_compute(typeDecl);
        isInnerTypeOf_TypeDecl_visited.remove(_parameters);
        return isInnerTypeOf_TypeDecl_value;
    }

    private boolean isInnerTypeOf_compute(TypeDecl typeDecl) {  return typeDecl == this || (isInnerType() && enclosingType().isInnerTypeOf(typeDecl));  }

    protected java.util.Map withinBodyThatSubclasses_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 546
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl withinBodyThatSubclasses(TypeDecl type) {
        Object _parameters = type;
if(withinBodyThatSubclasses_TypeDecl_visited == null) withinBodyThatSubclasses_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(withinBodyThatSubclasses_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: withinBodyThatSubclasses in class: ");
        withinBodyThatSubclasses_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        TypeDecl withinBodyThatSubclasses_TypeDecl_value = withinBodyThatSubclasses_compute(type);
        withinBodyThatSubclasses_TypeDecl_visited.remove(_parameters);
        return withinBodyThatSubclasses_TypeDecl_value;
    }

    private TypeDecl withinBodyThatSubclasses_compute(TypeDecl type) {
    if(instanceOf(type))
      return this;
    if(!isTopLevelType())
      return enclosingType().withinBodyThatSubclasses(type);
    return null;
  }

    protected java.util.Map encloses_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 554
 @SuppressWarnings({"unchecked", "cast"})     public boolean encloses(TypeDecl type) {
        Object _parameters = type;
if(encloses_TypeDecl_visited == null) encloses_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(encloses_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: encloses in class: ");
        encloses_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean encloses_TypeDecl_value = encloses_compute(type);
        encloses_TypeDecl_visited.remove(_parameters);
        return encloses_TypeDecl_value;
    }

    private boolean encloses_compute(TypeDecl type) {  return type.enclosedBy(this);  }

    protected java.util.Map enclosedBy_TypeDecl_visited;
    // Declared in TypeAnalysis.jrag at line 556
 @SuppressWarnings({"unchecked", "cast"})     public boolean enclosedBy(TypeDecl type) {
        Object _parameters = type;
if(enclosedBy_TypeDecl_visited == null) enclosedBy_TypeDecl_visited = new java.util.HashMap(4);
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(enclosedBy_TypeDecl_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: enclosedBy in class: ");
        enclosedBy_TypeDecl_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        boolean enclosedBy_TypeDecl_value = enclosedBy_compute(type);
        enclosedBy_TypeDecl_visited.remove(_parameters);
        return enclosedBy_TypeDecl_value;
    }

    private boolean enclosedBy_compute(TypeDecl type) {
    if(this == type)
      return true;
    if(isTopLevelType())
      return false;
    return enclosingType().enclosedBy(type);
  }

    protected int hostType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 570
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl hostType() {
        ASTNode$State state = state();
        if(hostType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: hostType in class: ");
        hostType_visited = state().boundariesCrossed;
        TypeDecl hostType_value = hostType_compute();
        hostType_visited = -1;
        return hostType_value;
    }

    private TypeDecl hostType_compute() {  return this;  }

    protected int isCircular_visited = -1;
    protected boolean isCircular_computed = false;
    protected boolean isCircular_initialized = false;
    protected boolean isCircular_value;
    // Declared in TypeAnalysis.jrag at line 673
 @SuppressWarnings({"unchecked", "cast"})     public boolean isCircular() {
        if(isCircular_computed) {
            return isCircular_value;
        }
        ASTNode$State state = state();
        if (!isCircular_initialized) {
            isCircular_initialized = true;
            isCircular_value = true;
        }
        if (!state.IN_CIRCLE) {
            state.IN_CIRCLE = true;
            int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
            do {
                isCircular_visited = state.CIRCLE_INDEX;
                state.CHANGE = false;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                boolean new_isCircular_value = isCircular_compute();
                if (new_isCircular_value!=isCircular_value)
                    state.CHANGE = true;
                isCircular_value = new_isCircular_value; 
                state.CIRCLE_INDEX++;
            } while (state.CHANGE);
            if(isFinal && num == state().boundariesCrossed)
{
            isCircular_computed = true;
            state.LAST_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isCircular_compute();
            state.LAST_CYCLE = false;
            }
            else {
            state.RESET_CYCLE = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            isCircular_compute();
            state.RESET_CYCLE = false;
              isCircular_computed = false;
              isCircular_initialized = false;
            }
            state.IN_CIRCLE = false; 
            return isCircular_value;
        }
        if(isCircular_visited != state.CIRCLE_INDEX) {
            isCircular_visited = state.CIRCLE_INDEX;
            if (state.LAST_CYCLE) {
                isCircular_computed = true;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
                return isCircular_compute();
            }
            if (state.RESET_CYCLE) {
                isCircular_computed = false;
                isCircular_initialized = false;
                isCircular_visited = -1;
                return isCircular_value;
            }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
            boolean new_isCircular_value = isCircular_compute();
            if (new_isCircular_value!=isCircular_value)
                state.CHANGE = true;
            isCircular_value = new_isCircular_value; 
            return isCircular_value;
        }
        return isCircular_value;
    }

    private boolean isCircular_compute() {  return false;  }

    protected int componentType_visited = -1;
    protected boolean componentType_computed = false;
    protected TypeDecl componentType_value;
    // Declared in Arrays.jrag at line 21
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl componentType() {
        if(componentType_computed) {
            return componentType_value;
        }
        ASTNode$State state = state();
        if(componentType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: componentType in class: ");
        componentType_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        componentType_value = getParent().Define_TypeDecl_componentType(this, null);
        if(isFinal && num == state().boundariesCrossed)
            componentType_computed = true;
        componentType_visited = -1;
        return componentType_value;
    }

    protected int typeCloneable_visited = -1;
    // Declared in Arrays.jrag at line 50
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeCloneable() {
        ASTNode$State state = state();
        if(typeCloneable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeCloneable in class: ");
        typeCloneable_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeCloneable_value = getParent().Define_TypeDecl_typeCloneable(this, null);
        typeCloneable_visited = -1;
        return typeCloneable_value;
    }

    protected int typeSerializable_visited = -1;
    // Declared in Arrays.jrag at line 51
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeSerializable() {
        ASTNode$State state = state();
        if(typeSerializable_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeSerializable in class: ");
        typeSerializable_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl typeSerializable_value = getParent().Define_TypeDecl_typeSerializable(this, null);
        typeSerializable_visited = -1;
        return typeSerializable_value;
    }

    protected int compilationUnit_visited = -1;
    // Declared in ClassPath.jrag at line 31
 @SuppressWarnings({"unchecked", "cast"})     public CompilationUnit compilationUnit() {
        ASTNode$State state = state();
        if(compilationUnit_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: compilationUnit in class: ");
        compilationUnit_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        CompilationUnit compilationUnit_value = getParent().Define_CompilationUnit_compilationUnit(this, null);
        compilationUnit_visited = -1;
        return compilationUnit_value;
    }

    protected java.util.Map isDAbefore_Variable_visited;
    protected java.util.Map isDAbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 242
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDAbefore(Variable v) {
        Object _parameters = v;
if(isDAbefore_Variable_visited == null) isDAbefore_Variable_visited = new java.util.HashMap(4);
if(isDAbefore_Variable_values == null) isDAbefore_Variable_values = new java.util.HashMap(4);
        if(isDAbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDAbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDAbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDAbefore in class: ");
        isDAbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDAbefore_Variable_value = getParent().Define_boolean_isDAbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDAbefore_Variable_values.put(_parameters, Boolean.valueOf(isDAbefore_Variable_value));
        isDAbefore_Variable_visited.remove(_parameters);
        return isDAbefore_Variable_value;
    }

    protected java.util.Map isDUbefore_Variable_visited;
    protected java.util.Map isDUbefore_Variable_values;
    // Declared in DefiniteAssignment.jrag at line 708
 @SuppressWarnings({"unchecked", "cast"})     public boolean isDUbefore(Variable v) {
        Object _parameters = v;
if(isDUbefore_Variable_visited == null) isDUbefore_Variable_visited = new java.util.HashMap(4);
if(isDUbefore_Variable_values == null) isDUbefore_Variable_values = new java.util.HashMap(4);
        if(isDUbefore_Variable_values.containsKey(_parameters)) {
            return ((Boolean)isDUbefore_Variable_values.get(_parameters)).booleanValue();
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(isDUbefore_Variable_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: isDUbefore in class: ");
        isDUbefore_Variable_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isDUbefore_Variable_value = getParent().Define_boolean_isDUbefore(this, null, v);
        if(isFinal && num == state().boundariesCrossed)
            isDUbefore_Variable_values.put(_parameters, Boolean.valueOf(isDUbefore_Variable_value));
        isDUbefore_Variable_visited.remove(_parameters);
        return isDUbefore_Variable_value;
    }

    protected int typeException_visited = -1;
    protected boolean typeException_computed = false;
    protected TypeDecl typeException_value;
    // Declared in ExceptionHandling.jrag at line 14
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeException() {
        if(typeException_computed) {
            return typeException_value;
        }
        ASTNode$State state = state();
        if(typeException_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeException in class: ");
        typeException_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeException_value = getParent().Define_TypeDecl_typeException(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeException_computed = true;
        typeException_visited = -1;
        return typeException_value;
    }

    protected int typeRuntimeException_visited = -1;
    protected boolean typeRuntimeException_computed = false;
    protected TypeDecl typeRuntimeException_value;
    // Declared in ExceptionHandling.jrag at line 16
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeRuntimeException() {
        if(typeRuntimeException_computed) {
            return typeRuntimeException_value;
        }
        ASTNode$State state = state();
        if(typeRuntimeException_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeRuntimeException in class: ");
        typeRuntimeException_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeRuntimeException_value = getParent().Define_TypeDecl_typeRuntimeException(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeRuntimeException_computed = true;
        typeRuntimeException_visited = -1;
        return typeRuntimeException_value;
    }

    protected int typeError_visited = -1;
    protected boolean typeError_computed = false;
    protected TypeDecl typeError_value;
    // Declared in ExceptionHandling.jrag at line 18
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeError() {
        if(typeError_computed) {
            return typeError_value;
        }
        ASTNode$State state = state();
        if(typeError_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeError in class: ");
        typeError_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeError_value = getParent().Define_TypeDecl_typeError(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeError_computed = true;
        typeError_visited = -1;
        return typeError_value;
    }

    protected java.util.Map lookupMethod_String_visited;
    protected java.util.Map lookupMethod_String_values;
    // Declared in LookupMethod.jrag at line 26
 @SuppressWarnings({"unchecked", "cast"})     public Collection lookupMethod(String name) {
        Object _parameters = name;
if(lookupMethod_String_visited == null) lookupMethod_String_visited = new java.util.HashMap(4);
if(lookupMethod_String_values == null) lookupMethod_String_values = new java.util.HashMap(4);
        if(lookupMethod_String_values.containsKey(_parameters)) {
            return (Collection)lookupMethod_String_values.get(_parameters);
        }
        ASTNode$State state = state();
        if(Integer.valueOf(state().boundariesCrossed).equals(lookupMethod_String_visited.get(_parameters)))
            throw new RuntimeException("Circular definition of attr: lookupMethod in class: ");
        lookupMethod_String_visited.put(_parameters, Integer.valueOf(state().boundariesCrossed));
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        Collection lookupMethod_String_value = getParent().Define_Collection_lookupMethod(this, null, name);
        if(isFinal && num == state().boundariesCrossed)
            lookupMethod_String_values.put(_parameters, lookupMethod_String_value);
        lookupMethod_String_visited.remove(_parameters);
        return lookupMethod_String_value;
    }

    protected int typeInt_visited = -1;
    // Declared in LookupType.jrag at line 62
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

    protected int typeObject_visited = -1;
    protected boolean typeObject_computed = false;
    protected TypeDecl typeObject_value;
    // Declared in LookupType.jrag at line 65
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl typeObject() {
        if(typeObject_computed) {
            return typeObject_value;
        }
        ASTNode$State state = state();
        if(typeObject_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: typeObject in class: ");
        typeObject_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        typeObject_value = getParent().Define_TypeDecl_typeObject(this, null);
        if(isFinal && num == state().boundariesCrossed)
            typeObject_computed = true;
        typeObject_visited = -1;
        return typeObject_value;
    }

    protected java.util.Map lookupType_String_String_visited;
    // Declared in LookupType.jrag at line 98
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
    // Declared in LookupType.jrag at line 172
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

    protected java.util.Map lookupVariable_String_visited;
    protected java.util.Map lookupVariable_String_values;
    // Declared in LookupVariable.jrag at line 14
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

    protected java.util.Map hasPackage_String_visited;
    // Declared in NameCheck.jrag at line 237
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

    protected int enclosingBlock_visited = -1;
    // Declared in NameCheck.jrag at line 240
 @SuppressWarnings({"unchecked", "cast"})     public ASTNode enclosingBlock() {
        ASTNode$State state = state();
        if(enclosingBlock_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingBlock in class: ");
        enclosingBlock_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        ASTNode enclosingBlock_value = getParent().Define_ASTNode_enclosingBlock(this, null);
        enclosingBlock_visited = -1;
        return enclosingBlock_value;
    }

    protected int packageName_visited = -1;
    protected boolean packageName_computed = false;
    protected String packageName_value;
    // Declared in QualifiedNames.jrag at line 89
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
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        packageName_value = getParent().Define_String_packageName(this, null);
        if(isFinal && num == state().boundariesCrossed)
            packageName_computed = true;
        packageName_visited = -1;
        return packageName_value;
    }

    protected int isAnonymous_visited = -1;
    protected boolean isAnonymous_computed = false;
    protected boolean isAnonymous_value;
    // Declared in TypeAnalysis.jrag at line 216
 @SuppressWarnings({"unchecked", "cast"})     public boolean isAnonymous() {
        if(isAnonymous_computed) {
            return isAnonymous_value;
        }
        ASTNode$State state = state();
        if(isAnonymous_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isAnonymous in class: ");
        isAnonymous_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        isAnonymous_value = getParent().Define_boolean_isAnonymous(this, null);
        if(isFinal && num == state().boundariesCrossed)
            isAnonymous_computed = true;
        isAnonymous_visited = -1;
        return isAnonymous_value;
    }

    protected int enclosingType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 497
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl enclosingType() {
        ASTNode$State state = state();
        if(enclosingType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingType in class: ");
        enclosingType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl enclosingType_value = getParent().Define_TypeDecl_enclosingType(this, null);
        enclosingType_visited = -1;
        return enclosingType_value;
    }

    protected int enclosingBodyDecl_visited = -1;
    // Declared in TypeAnalysis.jrag at line 513
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

    protected int isNestedType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 519
 @SuppressWarnings({"unchecked", "cast"})     public boolean isNestedType() {
        ASTNode$State state = state();
        if(isNestedType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isNestedType in class: ");
        isNestedType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isNestedType_value = getParent().Define_boolean_isNestedType(this, null);
        isNestedType_visited = -1;
        return isNestedType_value;
    }

    protected int isMemberType_visited = -1;
    // Declared in TypeAnalysis.jrag at line 527
 @SuppressWarnings({"unchecked", "cast"})     public boolean isMemberType() {
        ASTNode$State state = state();
        if(isMemberType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isMemberType in class: ");
        isMemberType_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isMemberType_value = getParent().Define_boolean_isMemberType(this, null);
        isMemberType_visited = -1;
        return isMemberType_value;
    }

    protected int isLocalClass_visited = -1;
    // Declared in TypeAnalysis.jrag at line 541
 @SuppressWarnings({"unchecked", "cast"})     public boolean isLocalClass() {
        ASTNode$State state = state();
        if(isLocalClass_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: isLocalClass in class: ");
        isLocalClass_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        boolean isLocalClass_value = getParent().Define_boolean_isLocalClass(this, null);
        isLocalClass_visited = -1;
        return isLocalClass_value;
    }

    protected int hostPackage_visited = -1;
    // Declared in TypeAnalysis.jrag at line 566
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

    protected int unknownType_visited = -1;
    protected boolean unknownType_computed = false;
    protected TypeDecl unknownType_value;
    // Declared in TypeAnalysis.jrag at line 672
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl unknownType() {
        if(unknownType_computed) {
            return unknownType_value;
        }
        ASTNode$State state = state();
        if(unknownType_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: unknownType in class: ");
        unknownType_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        unknownType_value = getParent().Define_TypeDecl_unknownType(this, null);
        if(isFinal && num == state().boundariesCrossed)
            unknownType_computed = true;
        unknownType_visited = -1;
        return unknownType_value;
    }

    protected int typeVoid_visited = -1;
    // Declared in TypeCheck.jrag at line 402
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

    protected int enclosingInstance_visited = -1;
    // Declared in TypeCheck.jrag at line 505
 @SuppressWarnings({"unchecked", "cast"})     public TypeDecl enclosingInstance() {
        ASTNode$State state = state();
        if(enclosingInstance_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: enclosingInstance in class: ");
        enclosingInstance_visited = state().boundariesCrossed;
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        TypeDecl enclosingInstance_value = getParent().Define_TypeDecl_enclosingInstance(this, null);
        enclosingInstance_visited = -1;
        return enclosingInstance_value;
    }

    protected int inExplicitConstructorInvocation_visited = -1;
    protected boolean inExplicitConstructorInvocation_computed = false;
    protected boolean inExplicitConstructorInvocation_value;
    // Declared in TypeHierarchyCheck.jrag at line 127
 @SuppressWarnings({"unchecked", "cast"})     public boolean inExplicitConstructorInvocation() {
        if(inExplicitConstructorInvocation_computed) {
            return inExplicitConstructorInvocation_value;
        }
        ASTNode$State state = state();
        if(inExplicitConstructorInvocation_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inExplicitConstructorInvocation in class: ");
        inExplicitConstructorInvocation_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        inExplicitConstructorInvocation_value = getParent().Define_boolean_inExplicitConstructorInvocation(this, null);
        if(isFinal && num == state().boundariesCrossed)
            inExplicitConstructorInvocation_computed = true;
        inExplicitConstructorInvocation_visited = -1;
        return inExplicitConstructorInvocation_value;
    }

    protected int inStaticContext_visited = -1;
    protected boolean inStaticContext_computed = false;
    protected boolean inStaticContext_value;
    // Declared in TypeHierarchyCheck.jrag at line 135
 @SuppressWarnings({"unchecked", "cast"})     public boolean inStaticContext() {
        if(inStaticContext_computed) {
            return inStaticContext_value;
        }
        ASTNode$State state = state();
        if(inStaticContext_visited == state().boundariesCrossed)
            throw new RuntimeException("Circular definition of attr: inStaticContext in class: ");
        inStaticContext_visited = state().boundariesCrossed;
        int num = state.boundariesCrossed;
        boolean isFinal = this.is$Final();
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        inStaticContext_value = getParent().Define_boolean_inStaticContext(this, null);
        if(isFinal && num == state().boundariesCrossed)
            inStaticContext_computed = true;
        inStaticContext_visited = -1;
        return inStaticContext_value;
    }

    // Declared in Arrays.jrag at line 20
    public TypeDecl Define_TypeDecl_componentType(ASTNode caller, ASTNode child) {
        if(caller == arrayType_value){
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_componentType(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 20
    public boolean Define_boolean_isDest(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDest(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 30
    public boolean Define_boolean_isSource(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isSource(this, caller);
    }

    // Declared in DefiniteAssignment.jrag at line 247
    public boolean Define_boolean_isDAbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBodyDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    BodyDecl b = getBodyDecl(childIndex);
    //if(b instanceof MethodDecl || b instanceof MemberTypeDecl) {
    if(!v.isInstanceVariable() && !v.isClassVariable()) {
      if(v.hostType() != this)
        return isDAbefore(v);
      return false;
    }
    if(b instanceof FieldDeclaration && !((FieldDeclaration)b).isStatic() && v.isClassVariable())
      return true;

    if(b instanceof MethodDecl) {
      return true;
    }
    if(b instanceof MemberTypeDecl && v.isBlank() && v.isFinal() && v.hostType() == this)
      return true;
    if(v.isClassVariable() || v.isInstanceVariable()) {
      if(v.isFinal() &&  v.hostType() != this && instanceOf(v.hostType()))
        return true;
      int index = childIndex - 1;
      if(b instanceof ConstructorDecl)
        index = getNumBodyDecl() - 1;
        
      for(int i = index; i >= 0; i--) {
        b = getBodyDecl(i);
        if(b instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)b;
          if((v.isClassVariable() && f.isStatic()) || (v.isInstanceVariable() && !f.isStatic())) {
            boolean c = f.isDAafter(v);
            //System.err.println("DefiniteAssignment: is " + v.name() + " DA after index " + i + ", " + f + ": " + c);
            return c;
            //return f.isDAafter(v);
          }
        }
        else if(b instanceof StaticInitializer && v.isClassVariable()) {
          StaticInitializer si = (StaticInitializer)b;
          return si.isDAafter(v);
        }
        else if(b instanceof InstanceInitializer && v.isInstanceVariable()) {
          InstanceInitializer ii = (InstanceInitializer)b;
          return ii.isDAafter(v);
        }
      }
    }
    return isDAbefore(v);
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDAbefore(this, caller, v);
    }

    // Declared in DefiniteAssignment.jrag at line 715
    public boolean Define_boolean_isDUbefore(ASTNode caller, ASTNode child, Variable v) {
        if(caller == getBodyDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    BodyDecl b = getBodyDecl(childIndex);
    if(b instanceof MethodDecl || b instanceof MemberTypeDecl) {
      return false;
    }
    if(v.isClassVariable() || v.isInstanceVariable()) {
      int index = childIndex - 1;
      if(b instanceof ConstructorDecl)
        index = getNumBodyDecl() - 1;
        
      for(int i = index; i >= 0; i--) {
        b = getBodyDecl(i);
        if(b instanceof FieldDeclaration) {
          FieldDeclaration f = (FieldDeclaration)b;
          //System.err.println("  working on field " + f.name() + " which is child " + i);
          if(f == v)
            return !f.hasInit();
          if((v.isClassVariable() && f.isStatic()) || (v.isInstanceVariable() && !f.isStatic()))
            return f.isDUafter(v);
          //System.err.println("  field " + f.name() + " can not affect " + v.name());
        }
        else if(b instanceof StaticInitializer && v.isClassVariable()) {
          StaticInitializer si = (StaticInitializer)b;
          //System.err.println("  working on static initializer which is child " + i);
          return si.isDUafter(v);
        }
        else if(b instanceof InstanceInitializer && v.isInstanceVariable()) {
          InstanceInitializer ii = (InstanceInitializer)b;
          //System.err.println("  working on instance initializer which is child " + i);
          return ii.isDUafter(v);
        }
      }
    }
    //System.err.println("Reached TypeDecl when searching for DU for variable");
    return isDUbefore(v);
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isDUbefore(this, caller, v);
    }

    // Declared in LookupConstructor.jrag at line 16
    public Collection Define_Collection_lookupConstructor(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return constructors();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupConstructor(this, caller);
    }

    // Declared in LookupConstructor.jrag at line 20
    public Collection Define_Collection_lookupSuperConstructor(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return lookupSuperConstructor();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupSuperConstructor(this, caller);
    }

    // Declared in LookupMethod.jrag at line 34
    public Collection Define_Collection_lookupMethod(ASTNode caller, ASTNode child, String name) {
        if(caller == getBodyDeclListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return unqualifiedLookupMethod(name);
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_Collection_lookupMethod(this, caller, name);
    }

    // Declared in LookupType.jrag at line 270
    public SimpleSet Define_SimpleSet_lookupType(ASTNode caller, ASTNode child, String name) {
        if(caller == getBodyDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    SimpleSet c = memberTypes(name);
    if(!c.isEmpty()) 
      return c;
    if(name().equals(name))
      return SimpleSet.emptySet.add(this);

    c = lookupType(name);
    // 8.5.2
    if(isClassDecl() && isStatic() && !isTopLevelType()) {
      SimpleSet newSet = SimpleSet.emptySet;
      for(Iterator iter = c.iterator(); iter.hasNext(); ) {
        TypeDecl d = (TypeDecl)iter.next();
        //if(d.isStatic() || d.isTopLevelType() || this.instanceOf(d.enclosingType())) {
          newSet = newSet.add(d);
        //}
      }
      c = newSet;
    }
    return c;
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupType(this, caller, name);
    }

    // Declared in LookupVariable.jrag at line 27
    public SimpleSet Define_SimpleSet_lookupVariable(ASTNode caller, ASTNode child, String name) {
        if(caller == getBodyDeclListNoTransform()) { 
   int i = caller.getIndexOfChild(child);
{
    SimpleSet list = memberFields(name);
    if(!list.isEmpty()) return list;
    list = lookupVariable(name);
    if(inStaticContext() || isStatic())
      list = removeInstanceVariables(list);
    return list;
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_SimpleSet_lookupVariable(this, caller, name);
    }

    // Declared in Modifiers.jrag at line 299
    public boolean Define_boolean_mayBePublic(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBePublic(this, caller);
    }

    // Declared in Modifiers.jrag at line 300
    public boolean Define_boolean_mayBeProtected(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeProtected(this, caller);
    }

    // Declared in Modifiers.jrag at line 301
    public boolean Define_boolean_mayBePrivate(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBePrivate(this, caller);
    }

    // Declared in Modifiers.jrag at line 304
    public boolean Define_boolean_mayBeAbstract(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeAbstract(this, caller);
    }

    // Declared in Modifiers.jrag at line 302
    public boolean Define_boolean_mayBeStatic(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeStatic(this, caller);
    }

    // Declared in Modifiers.jrag at line 307
    public boolean Define_boolean_mayBeStrictfp(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(caller == getModifiersNoTransform()) {
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeStrictfp(this, caller);
    }

    // Declared in Modifiers.jrag at line 303
    public boolean Define_boolean_mayBeFinal(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeFinal(this, caller);
    }

    // Declared in Modifiers.jrag at line 305
    public boolean Define_boolean_mayBeVolatile(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeVolatile(this, caller);
    }

    // Declared in Modifiers.jrag at line 306
    public boolean Define_boolean_mayBeTransient(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeTransient(this, caller);
    }

    // Declared in Modifiers.jrag at line 308
    public boolean Define_boolean_mayBeSynchronized(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeSynchronized(this, caller);
    }

    // Declared in Modifiers.jrag at line 309
    public boolean Define_boolean_mayBeNative(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_mayBeNative(this, caller);
    }

    // Declared in NameCheck.jrag at line 292
    public VariableScope Define_VariableScope_outerScope(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_VariableScope_outerScope(this, caller);
    }

    // Declared in NameCheck.jrag at line 364
    public boolean Define_boolean_insideLoop(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_insideLoop(this, caller);
    }

    // Declared in NameCheck.jrag at line 371
    public boolean Define_boolean_insideSwitch(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int i = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_insideSwitch(this, caller);
    }

    // Declared in SyntacticClassification.jrag at line 118
    public NameType Define_NameType_nameType(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return NameType.EXPRESSION_NAME;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_NameType_nameType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 218
    public boolean Define_boolean_isAnonymous(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isAnonymous(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 495
    public TypeDecl Define_TypeDecl_enclosingType(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return this;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_enclosingType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 521
    public boolean Define_boolean_isNestedType(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isNestedType(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 543
    public boolean Define_boolean_isLocalClass(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return false;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_isLocalClass(this, caller);
    }

    // Declared in TypeAnalysis.jrag at line 572
    public TypeDecl Define_TypeDecl_hostType(ASTNode caller, ASTNode child) {
        if(caller == getModifiersNoTransform()) {
            return hostType();
        }
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return hostType();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_hostType(this, caller);
    }

    // Declared in TypeCheck.jrag at line 404
    public TypeDecl Define_TypeDecl_returnType(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return typeVoid();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_returnType(this, caller);
    }

    // Declared in TypeCheck.jrag at line 509
    public TypeDecl Define_TypeDecl_enclosingInstance(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) { 
   int childIndex = caller.getIndexOfChild(child);
{
    if(getBodyDecl(childIndex) instanceof MemberTypeDecl && !((MemberTypeDecl)getBodyDecl(childIndex)).typeDecl().isInnerType())
      return null;
    if(getBodyDecl(childIndex) instanceof ConstructorDecl)
      return enclosingInstance();
    return this;
  }
}
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_TypeDecl_enclosingInstance(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 12
    public String Define_String_methodHost(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return typeName();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_String_methodHost(this, caller);
    }

    // Declared in TypeHierarchyCheck.jrag at line 138
    public boolean Define_boolean_inStaticContext(ASTNode caller, ASTNode child) {
        if(caller == getBodyDeclListNoTransform()) {
      int childIndex = caller.getIndexOfChild(child);
            return isStatic() || inStaticContext();
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_inStaticContext(this, caller);
    }

    // Declared in UnreachableStatements.jrag at line 157
    public boolean Define_boolean_reportUnreachable(ASTNode caller, ASTNode child) {
        if(true) {
      int childIndex = this.getIndexOfChild(caller);
            return true;
        }
        if(getParent() == null) throw new RuntimeException("Trying to evaluate attribute in subtree not attached to main tree");
        return getParent().Define_boolean_reportUnreachable(this, caller);
    }

public ASTNode rewriteTo() {
    return super.rewriteTo();
}

}
