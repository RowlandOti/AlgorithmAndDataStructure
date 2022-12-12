package datastructures.linkedlist;

/*
 * Node class basically creates a node for the data which you enter to be included into Linked List.
 * Once the node is created, we use various functions to fit in that node into the Linked List.
 *
 * */
public class Node {
	// What this node contains
	private String data;
	// pointer to the next node
	private Node next;

	public Node() {
		this.data = null;
		this.next = null;
	}

	public Node(String x) {
		this.data = x;
		this.next = null;
	}

	public String getData() {
		return data;
	}

	public void setData(String x) {
		this.data = x;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node n) {
		this.next = n;
	}
}
