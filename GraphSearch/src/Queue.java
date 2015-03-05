import java.util.Iterator;


public class Queue<A> implements Iterable<Node<A>>, DataStructure<A>  {
	private int size;
	Item<A> first;
	Item<A> last;
	
	public Queue() {
		first = new Item<A>();
		last = new Item<A>();
		size = 0;
	}
	
	public Queue(Node<A> node) {
		first = new Item<A>();
		first.content = node;
		first.next = first;
		last = new Item<A>();
		size = 1;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}
	
	public Node<A> peek() {
		return first.content;
	}
	
	public void push(Node<A> node) {
		Item<A> item = new Item<A>();
		item.content = node;
		if(isEmpty()) { first = item; last = item; }
		else          { last.next = item; last = item; }
		size++;
	}
	
	public Node<A> pop() {
		if(isEmpty()) { throw new RuntimeException("Queue underflow"); } 
		
		Node<A> node = first.content;
		first = first.next;
		size--;
		if (isEmpty()) {
			last = null;
		}
		return node;
	}

	public Node<Coordinate> findNode(Queue<Coordinate> queue, String string) {
		boolean notFound = true;
		
		while(notFound) {
			if(queue.getSize() > 0) {
				Node<Coordinate> node = queue.pop();
				//System.out.println(node.getNodeContent().toString());
				if(node.getNodeContent().toString().equals(string)) {
					notFound = false;
					return node;
				}
			} else {
				notFound = false;
				throw new RuntimeException("No node found");
			}
		}
		
		return new Node<Coordinate>();
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

	
}

