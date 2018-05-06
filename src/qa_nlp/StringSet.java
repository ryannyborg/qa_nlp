package qa_nlp;

import java.util.Vector;
import java.util.Enumeration;

/* Taken from http://www.cs.virginia.edu/cs201j/lectures/graph/ */

// Based on IntSet From Liskov and Guttag, Figure 5.6.

public class StringSet {
    // OVERVIEW: StringSets are unbounded, mutable sets of Strings.
    //    A typical StringSet is { x1, ..., xn }

    private Vector els;
    //@invariant els != null;
    //@invariant els.elementType == \type(String)
    //@invariant els.containsNull == false
    // invariant els does not contain duplicates

    public StringSet () {
	// EFFECTS: Initializes this to be empty: { }
	els = new Vector ();
	//@set els.elementType = \type(String)
	//@set els.containsNull = false
    }

    public /*@non_null@*/ StringSet copy () {
	// EFFECTS: Returns a copy of this StringSet.
	StringSet res = new StringSet ();
	res.els = (Vector) els.clone ();
	//@set res.els.elementType = \type(String)
	//@set res.els.containsNull = false
	return res;
    } //@nowarn Invariant // ESC/Java doesn't understand clone sufficiently.

    public void insert (/*@non_null@*/ String s) {
	// MODIFIES: this
	// EFFECTS: Adds x to the elements of this: this_post = this_pre U { s }
	if (getIndex (s) < 0) els.add (s); 
    }

    public void remove (/*@non_null@*/ String s) {
	// MODIFIES: this
	// EFFECTS: Removes s from this: this_post = this_pre - { s }
	int i = getIndex (s);
	if (i < 0) return;
	els.removeElementAt (i);
    }
	
    public boolean isIn (/*@non_null@*/ String s) {
	// EFFECTS: Returns true iff s is an element of this.  
	return getIndex (s) >= 0;
    }

    private int getIndex (/*@non_null@*/ String s) 
	//@ensures \result < els.elementCount
    {
	// EFFECTS: If x is in this returns index where x appears, else returns -1.
	for (int i = 0; i < els.size (); i++) {
	    if (s.equals (els.elementAt (i))) {
		return i;
	    }
	}
	return -1;
    }

    public int size () {
	// EFFECTS: Returns the number of elements in this.
	return els.size ();
    }
    
    public /*@non_null@*/ Enumeration elements () {
	return els.elements ();
    }
}
