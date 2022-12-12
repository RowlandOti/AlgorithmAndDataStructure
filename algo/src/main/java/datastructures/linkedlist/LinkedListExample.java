package datastructures.linkedlist;

import java.util.Scanner;

public class LinkedListExample {

	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();

		Scanner sc = new Scanner(System.in);
		System.out.println("How many Nodes are Needed?");
		int x = sc.nextInt();
		for (int i = 0; i < x; i++) {
			String data = sc.toString();
			l1.addAtFront(createNode(data));
		}
		sc.close();
	}

	static Node createNode(String data) {
		Node node = new Node(data);
		return node;
	}

}
