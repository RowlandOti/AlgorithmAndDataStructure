package datastructures.linkedlist;

public class LinkedList extends ALinkedList<String> {

	@Override
	public int addAtFront(Node n) {
		int i = 0;
		// making the next of the new Node point to Head
		n.setNext(head);
		// making the new Node as Head
		head = n;
		i++;
		// returning the position where Node is added
		return i;
	}

	@Override
	public boolean isEmpty() {
        // check if list is empty
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addAtEnd(Node n) {
		// If list is empty
		if (head == null) {
			// making the new Node as Head
			head = n;
			// making the next pointer of the new Node as Null
			n.setNext(null);
		} else {
			// getting the last node
			Node n2 = getLastNode();
			n2.setNext(n);
		}
	}

	@Override
	public Node search(String k) {
		Node ptr = head;
		while (ptr != null && ptr.getData() != k) {
			// Until we reach the end or we find a Node with data k, we keep
			// moving
			ptr = ptr.getNext();
		}
		return ptr;
	}

	@Override
	public Node deleteNode(String x) {
		// Searching the Node with data x
		Node n = search(x);
		Node ptr = head;
		if (ptr == n) {
			ptr.setNext(n.getNext());
			return n;
		} else {
			while (ptr.getNext() != n) {
				ptr = ptr.getNext();
			}
			ptr.setNext(n.getNext());
			return n;
		}
	}

	@Override
	public Node getLastNode() {
		// creating a pointer pointing to Head
		Node ptr = head;
		// Iterate over the list till the node whose Next pointer points to null
		// Return that node, because that will be the last node.
		while (ptr.getNext() != null) {
			// if Next is not Null, take the pointer one step forward
			ptr = ptr.getNext();
		}
		return ptr;
	}

}
