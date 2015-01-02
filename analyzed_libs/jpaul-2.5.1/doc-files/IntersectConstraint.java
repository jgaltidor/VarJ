// IntersectConstraint.java, created Tue Aug 16 10:29:12 2005 by salcianu
// Copyright (C) 2005 Alexandru Salcianu <salcianu@alum.mit.edu>
// Licensed under the Modified BSD Licence; see COPYING for details.
package jpaul.Constraints.SetConstraints;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collection;
import java.util.Arrays;

import jpaul.Constraints.ConstraintSystem;
import jpaul.Constraints.Constraint;
import jpaul.Constraints.SolReader;
import jpaul.Constraints.SolAccessor;
import jpaul.Constraints.Var;

import jpaul.DataStructs.UnionFind;

/**
   <code>IntersectConstraint</code> models a set intersection constraint.
   Mathematically, such a constraint has the form:

   <blockquote>
   <code>
   vIn1 /\ vIn2 &lt;= vDest
   </code>
   </blockquote>

   where <code>/\</code> stands for set intersection,
   <code>&lt;=</code> stands for set inclusion, and <code>vIn1</code>,
   <code>vIn2</code>, <code>vDest</code> are set-valued variables.

   <p>
   NOTE: This is a simplified version of the code, meant to be a
   Constraint example.
   
 * @author  Alexandru Salcianu - salcianu@alum.mit.edu
 * @version $Id: IntersectConstraint.java,v 1.3 2006/01/07 02:33:56 salcianu Exp $ */
public class IntersectConstraint<T> extends Constraint<SVar<T>,Set<T>> {
    
    /** Creates a <code>IntersectConstraint</code> with the meaning
	<code>vIn1 /\ vIn2 &lt;= vDest</code>.  */
    public IntersectConstraint(SVar<T> vIn1, SVar<T> vIn2, SVar<T> vDest) {
	this.vIn1  = vIn1;
	this.vIn2  = vIn2;
	this.vDest = vDest;
        this.in  = Arrays.asList(vIn1, vIn2);
	this.out = Arrays.asList(vDest);
    }

    private final SVar<T> vIn1;
    private final SVar<T> vIn2;
    private final SVar<T> vDest;
    
    private final Collection<SVar<T>> in;
    private final Collection<SVar<T>> out;
	
    public Collection<SVar<T>> in()  { return this.in;  }
    public Collection<SVar<T>> out() { return this.out; }

    /** Returns {@link jpaul.Constraints.Constraint#HIGH_COST HIGH_COST}. */
    public int cost() { return Constraint.HIGH_COST; }


    public void action(SolAccessor<SVar<T>,Set<T>> sa) {
	// Notice the three standard steps of an action method
	// 1. Read the values of the input variables
	Set<T> s_in1 = sa.get(vIn1);
	// null stands for botton, i.e., the empty set.  In this case,
	// the constraint does not generate any new elements into
	// vDest; hence, we can return immediately.
	if(s_in1 == null) return;
	Set<T> s_in2 = sa.get(vIn2);
	if(s_in2 == null) return;

	// Just an optimization.
	if(s_in1.size() < s_in2.size()) {
	    Set<T> temp = s_in1;
	    s_in1 = s_in2;
	    s_in2 = temp;
	}

	// 2. Compute the intersection
	Set<T> intersect = new LinkedHashSet<T>();
	for(T elem : s_in1) {
	    if(s_in2.contains(elem)) {
		intersect.add(elem);
	    }
	}

	// 3. Update the destination variable
	sa.join(vDest, intersect);
    }


    // THE REST OF THIS CLASS IS JUST FOR PERFORMANCE REASONS

    /** We implemented {@link #rewrite}, {@link #equals}, and {@link
        #hashCode}, such that constraints that are identical after
        variable unification are not duplicated needlessly. */
    public Constraint<SVar<T>,Set<T>> rewrite(UnionFind<SVar<T>> uf) {
	SVar<T> vIn1_p  = uf.find(vIn1);
	SVar<T> vIn2_p  = uf.find(vIn2);
	SVar<T> vDest_p = uf.find(vDest);	
	return new IntersectConstraint(vIn1_p, vIn2_p, vDest_p);
    }

    public boolean equals(Object o) {
	if(o == null) return false;
	if(o == this) return true;
	if(!(o instanceof IntersectConstraint/*<T>*/)) return false;
	if(this.hashCode() != o.hashCode()) return false;

	IntersectConstraint<T> ic2 = (IntersectConstraint<T>) o;
	return 
	    this.vIn1.equals(ic2.vIn1) &&
	    this.vIn2.equals(ic2.vIn2) &&
	    this.vDest.equals(ic2.vDest);
    }


    public int hashCode() {
	if(hashCode == 0) {
	    hashCode = 3 * vIn1.hashCode() + 5 * vIn2.hashCode() + 7 * vDest.hashCode();
	}
	return hashCode;
    }
    private int hashCode = 0; // cached hashCode

    
    public String toString() {
	return vIn1 + " /\\ " + vIn2 + " <= " + vDest;
    }
    
}
