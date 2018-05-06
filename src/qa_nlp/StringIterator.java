package qa_nlp;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/* Taken from http://www.cs.virginia.edu/cs201j/lectures/graph/ */

public class StringIterator {
    /*@non_null@*/ Enumeration entries;
    //@invariant entries.elementType == \type(String) ;
    //@invariant entries.returnsNull == false ; 
    
    public StringIterator (/*@non_null@*/ Enumeration e)
       //@requires e.elementType == \type(String) ;
       //@requires e.returnsNull == false ; 
    {
        entries = e;
    }
    
    public boolean hasNext ()
    {
        return entries.hasMoreElements ();
    }

    public /*@non_null@*/ String next () throws NoSuchElementException 
    {
        //@assume entries.moreElements
        return (String) entries.nextElement (); 
    }
}
