public class Item<A> {
	public Node<A> content;
	public Item<A> next;
	
	public Item() {
		
	}
	
	public Item(Node<A> content, Item<A> next) {
		this.content = content;
		this.next    = next;
	}
	
}
