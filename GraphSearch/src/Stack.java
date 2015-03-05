import java.util.Iterator;

public class Stack<A> implements Iterable<Node<A>>, DataStructure<A> {

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
	
	public void push(Node<A> node) {
		Item<A> copy      = first;
		first             = new Item<A>(node, copy);
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
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Node<A> node : this) {
			s.append(node + " ");
		}
		
		return s.toString();
	}
	
	public Iterator<Node<A>> iterator() { 
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Node<A>> { 
		private Item<A> current = first;
		public boolean hasNext() { return current!=null; }
		
		public Node<A> next() {
			Node<A> item = current.content;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
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
