package com.xyx.nowcoder.class_4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化的反序列化（先序，按层）
 * @author huan
 * @date 2018年6月15日
 */
public class SerializeAndReconstructTree {
	
	public static final String SPLIT_STR = "!";
	public static final String NULL_STR = "#";
	
	public static class Node {
		private int value;
		private Node left;
		private Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 根据先序遍历序列化
	 * @param head
	 * @return
	 */
	public static String serializeByPre(Node head) {
		if (head == null)
			return NULL_STR ;
		return head.value + SPLIT_STR + serializeByPre(head.left) 
				+ SPLIT_STR + serializeByPre(head.right);
	}
	
	/**
	 * 根据先序遍历反序列化
	 * @param preStr
	 * @return
	 */
	public static Node reconstructByPreStr(String preStr) {
		String[] values = preStr.split(SPLIT_STR);
		Queue<String> queue = new LinkedList<String>();
		for (String value : values) {
			queue.offer(value);
		}
		return reconstructByPreQueue(queue);
	}
	
	private static Node reconstructByPreQueue(Queue<String> queue) {
		if (queue.isEmpty())
			return null;
		String curStr = queue.poll();
		Node cur;
		if (NULL_STR.equals(curStr)) 
			return null;
		
		cur = new Node(Integer.parseInt(curStr));
		cur.left = reconstructByPreQueue(queue);
		cur.right = reconstructByPreQueue(queue);
		
		return cur;
	}
	
	/**
	 * 按层级序列化
	 * @param head
	 * @return
	 */
	public static String serializeByLevel(Node head) {
		Queue<Node> queue = new LinkedList<Node>();
		StringBuilder builder = new StringBuilder();
		queue.offer(head);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (cur == null)
				builder.append("#!");
			else {
				builder.append(cur.value + SPLIT_STR);
				queue.offer(cur.left);
				queue.offer(cur.right);
			}
		}
		return builder.toString();
	}
	
	/**
	 * 按层反序列化
	 * @param levelStr
	 * @return
	 */
	public static Node reconstructByLevel(String levelStr) {
		int cur = 0;
		//分割
		String[] values = levelStr.split(SPLIT_STR);
		Node head = getNodeByStr(values[cur++]);
		
		//若是为空
		if (head == null)
			return null;
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(head);
		
		//利用队列获取每一层的节点（从左往右），并将接下来它们的子节点添加到上面
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			curNode.left = getNodeByStr(values[cur++]);
			curNode.right = getNodeByStr(values[cur++]);
			
			if (curNode.left != null)
				queue.offer(curNode.left);
			if (curNode.right != null)
				queue.offer(curNode.right);
		}
		return head;
	}
	
	private static Node getNodeByStr(String str) {
		if (NULL_STR.equals(str))
			return null;
		return new Node(Integer.parseInt(str));
	}
	
	
//	_______________________________________________________________________
	
	
	
	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}
	

	public static void main(String[] args) {
		Node head = null;
		printTree(head);

		String pre = serializeByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconstructByPreStr(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		String level = serializeByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconstructByLevel(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		printTree(head);

		pre = serializeByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconstructByPreStr(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serializeByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconstructByLevel(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.right = new Node(5);
		printTree(head);

		pre = serializeByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconstructByPreStr(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serializeByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconstructByLevel(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		printTree(head);

		pre = serializeByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconstructByPreStr(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serializeByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconstructByLevel(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

	}
}
