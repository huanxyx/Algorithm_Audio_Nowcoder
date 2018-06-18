package com.xyx.nowcoder.class_3;

import java.util.Stack;

/**
 * 判断一个链表是否为回文链表
 * 
 * @author huan
 * @date 2018年6月13日
 */
public class PalindromeList {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 空间复杂度为O(1)
	public static boolean isPalindrome1(Node head) {
		Node dummy = new Node(0);
		dummy.next = head;

		// 奇数个点：找到中点。偶数个点：找到中间两个点的前一个点
		Node slow = dummy;
		Node fast = dummy;
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next.next == null ? fast.next : fast.next.next;
		}
		Node mid = slow;
		Node end = fast;

		// 逆转后半部分的节点的方向
		Node cur = mid.next;
		Node pre = mid;
		while (cur != null) {
			Node next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		mid.next = null;

		// 开始判断是否为回文链表
		Node left = dummy.next;
		Node right = end;
		boolean isPalindrome = true;
		while (left != null) {
			if (left.value != right.value) {
				isPalindrome = false;
				break;
			}
			left = left.next;
			right = right.next;
		}

		// 复原后半部分的节点
		cur = end;
		pre = null;
		while (cur != null) {
			Node next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}

		return isPalindrome;
	}

	// 使用栈实现
	public static boolean isPalindrome2(Node head) {
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			if (stack.pop().value != cur.value)
				return false;
			cur = cur.next;
		}
		return true;
	}

	// for test
	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		head.next.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");
	}
}
