public class Stack<A> {

	private int size;
	private Item<A> first;
	
	public Stack() {
		first = null;
		size  = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void push(Node<A> startNode) {
		Item<A> copy      = first;
		first             = new Item<A>(startNode, copy);
		size++;
	}
	
	public Node<A> pop() {
		assert(!isEmpty());
		Node<A> top = first.content;
		first = first.next;
		size--;
		return top;
	}
	
	public Node<A> peek() {
		return first.content;
	}
	
	public Node<A> findNode(Stack<A> stack, String nodeContent) {
		
		boolean notFound = true;
		
		while(notFound) {
			if(stack.getSize() > 0) {
				Node<A> node = stack.pop();
				//System.out.println(node.getNodeContent().toString());
				if(node.getNodeContent().toString().equals(nodeContent)) {
					notFound = false;
					return node;
				}
			} else {
				notFound = false;
				throw new RuntimeException("No node found");
			}
		}
		
		return new Node<A>();
	}
	

}
