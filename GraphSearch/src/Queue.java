
public class Queue<A> {
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
	
	public void enqueue(Node<A> node) {
		Item<A> item = new Item();
		item.content = node;
		if(isEmpty()) { first = item; last = item; }
		else          { last.next = item; last = item; }
		size++;
	}
	
	public Node<A> dequeue() {
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
				Node<Coordinate> node = queue.dequeue();
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
	
	
	
	/*
	public void enqueue(Node<A> node) {
		if(size == 0) {
			first.content = node;
			size++;
		} else {
			Item<A> newItem = new Item<A>();
			newItem.content = node;
			newItem.next    = new Item<A>();
			
			last.next       = newItem;
			last            = newItem;
			
			size++;
		}
	}
	
	public Node<A> dequeue() {
		if(size == 0) {
			throw new RuntimeException("Queue underflow");
		} else {
			Item<A> item = new Item<A>();
			item = first;
			
			//System.out.println(item.content.toString());
			
			if(first.next != null) {
				first = first.next;
			} else {
				first = new Item<A>();
			}
			size--;
			return item.content;
		}
	}
		
	*/

	/*
	public void enqueue(Node<A> node) {
		if(size == 0) {
			first         = new Item<A>();
			first.content = node;
			first.next    = first;
			last          = new Item<A>();
		} else {
			Item<A> itemToAdd = new Item<A>();
			itemToAdd.content = node;
			itemToAdd.next    = new Item<A>();
			last.next         = itemToAdd;
			last              = itemToAdd;
		}
		
		size++;
	}
	
	public Node<A> dequeue() {
		
		assert(!this.isEmpty());	
		
		Item<A> item = new Item<A>();
		item = first;
		
		if(size == 1) {
			first = new Item<A>();
		} else if (size == 2) {
			first = last;
			last = new Item<A>();
		} else {
			first = first.next;
		}
		
		size--;
		return item.content;
		
	}
	*/
}

