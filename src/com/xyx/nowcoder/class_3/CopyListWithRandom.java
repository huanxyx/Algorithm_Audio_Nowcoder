package com.xyx.nowcoder.class_3;

import java.util.HashMap;
import java.util.Map;

/**
 * 深度拷贝一个链表，该链表每个节点额外有另一个rand属性，表示随机的下一个节点
 * @author huan
 * @date 2018年6月13日
 */
public class CopyListWithRandom {
	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}
	
	//使用map建立原始节点和新节点之间的映射关系
	public static Node copyListWithRand1(Node head) {
		Map<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}
	
	//不借助map，空间复杂度为O(1)
	public static Node copyListWithRand2(Node head) {
		if (head == null)
			return null;
		
		//在每个节点后面创建一个拷贝节点
		Node cur = head;
		while (cur != null) {
			Node newNode = new Node(cur.value);
			newNode.next = cur.next;
			cur.next = newNode;
			cur = newNode.next;
		}
		
		//赋值rand
		cur = head;
		while (cur != null) {
			cur.next.rand = cur.rand == null ? null : cur.rand.next;
			cur = cur.next.next;
		}
		
		//分割成两条链表（记住先赋值rand，再分割）
		Node dummyCopy = new Node(0);
		Node curCopy = dummyCopy;
		cur = head;
		while (cur != null) {
			curCopy.next = cur.next;
			cur.next = cur.next.next;
			curCopy = curCopy.next;
			cur = cur.next;
		}
		
		return dummyCopy.next;
	}
	
	//for test
	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	//test
	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}
}
