package datastructures.linkedlist;

public abstract class ALinkedList<T> {

	protected Node head;

	public ALinkedList() {
		this.head = null;
	}

	// function to add Node at front
	public abstract int addAtFront(Node n);

	// function to check whether Linked list is empty
	public abstract boolean isEmpty();

	// function to add Node at the End of list
	public abstract void addAtEnd(Node n);

	// function to search a value
	public abstract Node search(T k);

	// function to delete any Node
	public abstract Node deleteNode(T x);

	// function to get next Node
	public abstract Node getLastNode();
}
