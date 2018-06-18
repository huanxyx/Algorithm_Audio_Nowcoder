package com.xyx.nowcoder.class_3;

/**
 * 链表的荷兰国旗问题，并且保证稳定性，空间复杂度为O(1)
 * @author huan
 * @date 2018年6月13日
 */
public class SmallerEqualBigger {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	
	//划分
	public static Node listPartition(Node head, int k) {
		Node dummyLessHead = new Node(0);
		Node dummyEqualHead = new Node(0);
		Node dummyMoreHead = new Node(0);
		Node lessCur = dummyLessHead;
		Node equalCur = dummyEqualHead;
		Node moreCur = dummyMoreHead;
		Node cur = head;
		
		while (cur != null) {
			if (cur.value < k) {
				lessCur.next = cur;
				lessCur = cur;
			} else if (cur.value > k) {
				moreCur.next = cur;
				moreCur = cur;
			} else {
				equalCur.next = cur;
				equalCur = cur;
			}
			cur = cur.next;
		}
		lessCur.next = dummyEqualHead.next != null ? dummyEqualHead.next : dummyMoreHead.next;
		equalCur.next = dummyMoreHead.next;
		moreCur.next = null;
		
		return dummyLessHead.next;
	}
	
	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 4);
		head1 = listPartition(head1, 3);
		printLinkedList(head1);

	}
}
