import java.util.ArrayList;

public class Node<A> {
	
	// private boolean explored;
	private int nodeNum;
	private A nodeContent;
	private ArrayList<Node<A>> successors;
	
	public Node() {
		this.successors = new ArrayList<Node<A>>();
	}
	
	public Node(A nodeContent) {
		this.nodeContent = nodeContent;
		this.successors  = new ArrayList<Node<A>>();
	}
	
	public Node(A nodeContent, int nodeNum) {
		// this.explored = false;
		this.nodeContent = nodeContent;
		this.nodeNum     = nodeNum;
		this.successors  = new ArrayList<Node<A>>();
	}
	
	/*
	public boolean getExplored() {
		return explored;
	}
	
	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	*/
	
	public int getNodeNum() {
		return nodeNum;
	}
	
	public A getNodeContent() {
		return nodeContent;
	}
	
	public String toString() {
		return "" + nodeContent;
	}
	
	public void addSuccessor(Node<A> succ) {
		successors.add(succ);
	}
	
	public ArrayList<Node<A>> getSuccessors() {
		return this.successors;
	}
}
