import java.util.ArrayList;

public class Node<A>{
	
	private int nodeNum;
	private A nodeContent;
	private ArrayList<Node<A>> successors;
	int fVal; // hVal + gVal
	private int hVal; // distance from node to goal
	// Assume movements between nodes have a cost of 10
	private final int gVal = 10;
	
	
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
	
	public Node(A nodeContent, int nodeNum, int hVal) {
		this.nodeContent = nodeContent;
		this.nodeNum     = nodeNum;
		this.successors  = new ArrayList<Node<A>>();
		this.fVal        = hVal + gVal;
		this.hVal        = hVal;
	}
	
	public Node<A> getFirstSuccessor() {
		assert(successors.size() > 0);
		return successors.get(0);
	}
	
	public int getNodeNum() {
		return nodeNum;
	}
	
	public int gethVal() {
		return hVal;
	}
	
	public int getfVal() {
		return fVal;
	}
	
	public void increasefVal() {
		this.fVal += gVal;
	}
	
	public A getNodeContent() {
		return nodeContent;
	}
	
	public void setHVal(int hVal) {
		this.hVal = hVal;
		this.fVal = hVal;
	}
	
	public String toString() {
		return "" + nodeContent;
	}
	
	public void addSuccessor(Node<A> succ) {
		successors.add(succ);
	}
	
	public boolean isLeaf() {
		if(this.successors.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Node<A>> getSuccessors() {
		return this.successors;
	}


}
