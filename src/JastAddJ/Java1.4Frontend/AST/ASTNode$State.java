
package AST;
import java.util.HashSet;import java.util.LinkedHashSet;import java.io.FileNotFoundException;import java.io.File;import java.util.*;import beaver.*;import java.util.ArrayList;import java.util.zip.*;import java.io.*;

public class ASTNode$State extends java.lang.Object {
    // Declared in ASTNode at line 2

  public boolean IN_CIRCLE = false;

    // Declared in ASTNode at line 3

  public int CIRCLE_INDEX = 1;

    // Declared in ASTNode at line 4

  public boolean CHANGE = false;

    // Declared in ASTNode at line 5

  public boolean LAST_CYCLE = false;

    // Declared in ASTNode at line 6

  public boolean RESET_CYCLE = false;

    // Declared in ASTNode at line 7

  static public class CircularValue {
    Object value;
    int visited = -1;
  }

    // Declared in ASTNode at line 11

  public static final int REWRITE_CHANGE = 1;

    // Declared in ASTNode at line 12

  public static final int REWRITE_NOCHANGE = 2;

    // Declared in ASTNode at line 13

  public static final int REWRITE_INTERRUPT = 3;

    // Declared in ASTNode at line 14

  public java.util.HashMap debugRewrite = new java.util.HashMap(4);

    // Declared in ASTNode at line 16


  public int boundariesCrossed = 0;

    // Declared in ASTNode at line 18


   private int[] stack;

    // Declared in ASTNode at line 19

   private int pos;

    // Declared in ASTNode at line 20

   public ASTNode$State() {
     stack = new int[64];
     pos = 0;
   }

    // Declared in ASTNode at line 24

   private void ensureSize(int size) {
     if(size < stack.length)
       return;
     int[] newStack = new int[stack.length * 2];
     System.arraycopy(stack, 0, newStack, 0, stack.length);
     stack = newStack;
   }

    // Declared in ASTNode at line 31

   public void push(int i) {
     ensureSize(pos+1);
     stack[pos++] = i;
   }

    // Declared in ASTNode at line 35

   public int pop() {
     return stack[--pos];
   }

    // Declared in ASTNode at line 38

   public int peek() {
     return stack[pos-1];
   }

    // Declared in ASTNode at line 41

  static class IdentityHashSet extends java.util.AbstractSet implements java.util.Set {
    public IdentityHashSet(int initialCapacity) {
      map = new java.util.IdentityHashMap(initialCapacity);
    }
    private java.util.IdentityHashMap map;
    private static final Object PRESENT = new Object();
    public java.util.Iterator iterator() { return map.keySet().iterator(); }
    public int size() { return map.size(); }
    public boolean isEmpty() { return map.isEmpty(); }
    public boolean contains(Object o) { return map.containsKey(o); }
    public boolean add(Object o) { return map.put(o, PRESENT)==null; }
    public boolean remove(Object o) { return map.remove(o)==PRESENT; }
    public void clear() { map.clear(); }
  }

    // Declared in Options.jadd at line 13

  public Options options = new Options();

    // Declared in ASTNode.ast at line 169

    protected int duringLookupConstructor = 0;

    // Declared in ASTNode.ast at line 180

    protected int duringBoundNames = 0;

    // Declared in ASTNode.ast at line 191

    protected int duringResolveAmbiguousNames = 0;

    // Declared in ASTNode.ast at line 202

    protected int duringSyntacticClassification = 0;

    // Declared in ASTNode.ast at line 213

    protected int duringAnonymousClasses = 0;

    // Declared in ASTNode.ast at line 224

    protected int duringVariableDeclaration = 0;

    // Declared in ASTNode.ast at line 235

    protected int duringConstantExpression = 0;

    // Declared in ASTNode.ast at line 246

    protected int duringDefiniteAssignment = 0;

    // Declared in ASTNode.ast at line 257

    public void reset() {
        IN_CIRCLE = false;
        CIRCLE_INDEX = 1;
        CHANGE = false;
        LAST_CYCLE = false;
        boundariesCrossed = 0;
        if(duringLookupConstructor != 0) {
            System.out.println("Warning: resetting duringLookupConstructor");
            duringLookupConstructor = 0;
        }
        if(duringBoundNames != 0) {
            System.out.println("Warning: resetting duringBoundNames");
            duringBoundNames = 0;
        }
        if(duringResolveAmbiguousNames != 0) {
            System.out.println("Warning: resetting duringResolveAmbiguousNames");
            duringResolveAmbiguousNames = 0;
        }
        if(duringSyntacticClassification != 0) {
            System.out.println("Warning: resetting duringSyntacticClassification");
            duringSyntacticClassification = 0;
        }
        if(duringAnonymousClasses != 0) {
            System.out.println("Warning: resetting duringAnonymousClasses");
            duringAnonymousClasses = 0;
        }
        if(duringVariableDeclaration != 0) {
            System.out.println("Warning: resetting duringVariableDeclaration");
            duringVariableDeclaration = 0;
        }
        if(duringConstantExpression != 0) {
            System.out.println("Warning: resetting duringConstantExpression");
            duringConstantExpression = 0;
        }
        if(duringDefiniteAssignment != 0) {
            System.out.println("Warning: resetting duringDefiniteAssignment");
            duringDefiniteAssignment = 0;
        }
        debugRewrite = new java.util.HashMap(4);

    }


}
