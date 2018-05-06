package qa_nlp;

import java.util.Vector;
import java.util.Enumeration;

/* Taken from http://www.cs.virginia.edu/cs201j/lectures/graph/ */

class Edge {
    // OVERVIEW: Record type for representing an edge.
    String node1, node2;
    //@invariant node1 != null;
    //@invariant node2 != null;
    
    Edge (String n1, String n2) 
	//@requires n1 != null
        //@requires n2 != null
        { node1 = n1; node2 = n2; }
    
    public String toString () {
	return node1 + " <-> " + node2;
    }
}

public class SemanticNetwork {
    // OVERVIEW: 
    //      A Graph is a mutable type that represents an undirected
    //      graph.  It consists of nodes that are named by Strings,
    //      and edges that connect a pair of nodes.
    //      A typical Graph is: < Nodes, Edges >
    //       where
    //         Nodes = { n1, n2, , nm }
    //       and 
    //         Edges = { {from_1, to_1}, ..., {from_n, to_n} }
    
    // Rep:
    Vector nodes; // Vector of String objects
    Vector edges; // Vector of Edge objects

    // Rep Invariant:
    //
    // RI (c) =    c.nodes != null && c.edges != null
    //          && !c.nodes.containsNull && !c.edges.containsNull
    //          && elements of c.nodes are String objects
    //          && elements of c.edges are Edge objects
    //          && no duplicates in c.nodes
    //                  No duplicate edges, node1/node2 are interchangable:
    //          && ((c.edges[i].node1 = c.edges[j].node1
    //               && c.edges[i].node2 = c.edges[j].node2)
    //              || (c.edges[i].node1 = c.edges[j].node2
    //                  && c.edges[i].node2 = c.edges[j].node1)) 
    //             ==> i == j
    //          && every node mentioned in c.edges is also in c.nodes
    //

    //@invariant nodes != null
    //@invariant edges != null

    //@invariant nodes.containsNull == false
    //@invariant edges.containsNull == false

    //@invariant nodes.elementType == \type(String)
    //@invariant edges.elementType == \type(Edge)

    // Abstraction Function:
    //
    // AF (c) = < Nodes, Edges > where
    //     Nodes = { c.nodes[i] | 0 <= i < c.nodes.size () } 
    //     Edges = { { c.edges[i].node1, c.edges[i].node2 }  | 0 <= i < c.edges.size () } 
    //
    
    // Creator 
    public SemanticNetwork () { 
	// EFFECTS: Initializes this to a graph
	//      with no nodes or edges: < {}, {} >.

	nodes = new Vector ();
	//@set nodes.elementType = \type(String) ;
	//@set nodes.containsNull = false;

	edges = new Vector ();
	//@set edges.elementType = \type(Edge) ;
	//@set edges.containsNull = false;
    } //@nowarn Invariant 
    // ESC/Java is not able to establish the invariant here, but we know its true, so we use
    // nowarn to suppress the warning.
	
    // Mutators

    public void addNode (String name) 
        //@requires name != null
	// REQUIRES: name is not the name of a node in this
	//  MODIFIES: this
	// EFFECTS: adds a node named name to this:
	//     this_post = < this_pre.nodes U { name }, this_pre.edges >
    {
	nodes.addElement (name);
    }

    public void addEdge (String fnode, String tnode) 
	//@requires fnode != null ;
	//@requires tnode != null ;
    {
	// REQUIRES: fnode and tnode are names of nodes in this, there is no
	//    edge between fnode and tnode already in the graph.
	// MODIFIES: this
	// EFFECTS: Adds an edge from fnode to tnode to this:
	//       this_post = < this_pre.nodes, this_pre.edges U { {fnode, tnode} } >
	
	edges.addElement (new Edge (fnode, tnode));
    }

    // Observers
    public boolean hasNode (String node) {
	// EFFECTS: Returns true iff node is a node in this.

	for (Enumeration e = nodes.elements (); e.hasMoreElements (); ) {
	    String name = (String) e.nextElement ();
	    if (name.equals (node)) {
		return true;
	    }
	}
	
	return false;
    }

    public StringIterator nodes () {
	// EFFECTS: Returns the StringIterator that
	//      yields all nodes in this in arbitrary order.
	
	return (new StringIterator (nodes.elements ()));
    }

    public StringSet getNeighbors (String node) {
	// REQUIRES: node is a node in this
	// EFFECTS: Returns the StringSet consisting of all nodes in this
	//      that are directly connected to node:
	//         \result =  { n | {node, n} is in this.edges }
	StringSet res = new StringSet ();
	Enumeration edgeenum = edges.elements ();
	while (edgeenum.hasMoreElements ()) {
	    Edge e = (Edge) edgeenum.nextElement ();
	    if (e.node1.equals (node)) { res.insert (e.node2); } 
	    else if (e.node2.equals (node)) { res.insert (e.node1); }
	}

	return res;
    }

    public String toString () {
	// EFFECTS: Returns a string representation of this.
	String res = "Graph:<Nodes: { ";
	boolean firstone = true;

	for (Enumeration e = nodes.elements (); e.hasMoreElements (); ) {
	    if (firstone) {
		firstone = false;
	    } else {
		res += ", ";
	    }
	    res += (String) e.nextElement ();
	}

	res += "}, Edges: { ";

	firstone = true;
	for (Enumeration e = edges.elements (); e.hasMoreElements (); ) {
	    if (firstone) {
		firstone = false;
	    } else {
		res += ", ";
	    }
	    
	    Edge edge = (Edge) e.nextElement ();
	    res += edge.node1 + " <-> " + edge.node2;
	}
	
	res += "}>";
	return res;
    }
}
