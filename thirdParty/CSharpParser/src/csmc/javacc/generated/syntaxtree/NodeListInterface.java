//
// Generated by JTB 1.3.2
//

package csmc.javacc.generated.syntaxtree;

/**
 * The interface which NodeList, NodeListOptional, and NodeSequence
 * implement.
 */
public interface NodeListInterface extends Node {
   public void addNode(Node n);
   public Node elementAt(int i);
   public java.util.Enumeration<Node> elements();
   public int size();

   public void accept(csmc.javacc.generated.visitor.Visitor v);
   public <R,A> R accept(csmc.javacc.generated.visitor.GJVisitor<R,A> v, A argu);
   public <R> R accept(csmc.javacc.generated.visitor.GJNoArguVisitor<R> v);
   public <A> void accept(csmc.javacc.generated.visitor.GJVoidVisitor<A> v, A argu);
}

