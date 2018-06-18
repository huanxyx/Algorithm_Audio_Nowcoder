package com.xyx.nowcoder.class_3;

/**
 * 找到两个链表第一个交叉节点
 * @author huan
 * @date 2018年6月14日
 */
public class FindFirstIntersectNode {
	
	public static class Node {
		int value;
		Node next;
		public Node(int value) {
			this.value = value;
		}
	}
	
	//获取两个链表第一个交叉节点
	public static Node getFirstIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null)
			return null;
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		
		if (loop1 != null && loop2 != null) 
			return bothLoop(head1, loop1, head2, loop2);
		else if (loop1 == null && loop2 == null)
			return noLoop(head1, head2);

		return null;
	}
	
	//找到链表的入环节点
	private static Node getLoopNode(Node head) {
		if (head == null || head.next == null)
			return null;
		
		Node dummy = new Node(0);
		dummy.next = head;
		//最初的出发点为dummy，初始化第一步的原因是，避免一开始dummy == dummy
		Node slow = dummy.next, fast = dummy.next.next;
		
		while (fast != null && fast != slow) {
			slow = slow.next;
			fast = fast.next == null ? null : fast.next.next;
		}
		
		if (fast == null)
			return null;
		
		fast = dummy;
		
		while (fast != slow) {
			slow = slow.next;
			fast = fast.next;
		}
		
		return fast;
	}
	
	//以end1为终结点的无环链表head1，和以end2为终结点的无环链表head2
	private static Node noLoop(Node head1, Node head2, Node end1, Node end2) {
		if (head1 == null || head2 == null) 
			return null;
		
		//找到两个链表的最后一个节点，计算链表的长度
		Node cur1 = head1;
		Node cur2 = head2;
		int len1 = 0;
		int len2 = 0;
		while (cur1.next != end1) {
			cur1 = cur1.next;
			len1++;
		}
		while (cur2.next != end2) {
			cur2 = cur2.next;
			len2++;
		}
		
		//两个链表的末节点不相同则一定不相交
		if (cur1 != cur2) 
			return null;
		cur1 = head1;
		cur2 = head2;
		if (len1 < len2) {
			for (int i = 0; i < (len2 - len1); i++)
				cur2 = cur2.next;
		} else if (len1 > len2) {
			for (int i = 0; i < (len1 - len2); i++)
				cur1 = cur1.next;
		}
		
		//找到两个链表第一个相交的节点
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		} 
		
		return cur1;
	}
	
	//两个无环的链表
	private static Node noLoop(Node head1, Node head2) {
		return noLoop(head1, head2, null, null);
	}
	
	//两个都有环
	private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		//交叉点在单枝上
		if (loop1 == loop2) {
			return noLoop(head1, head2, loop1, loop2);
		} else {
			Node curLoop1 = loop1.next;
			while (curLoop1 != loop1) {
				if (curLoop1 == loop2)
					return loop1;
				curLoop1 = curLoop1.next; 
			}
			return null;
		}
	}
	
	//test
	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getFirstIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getFirstIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getFirstIntersectNode(head1, head2).value);

	}
	
}
