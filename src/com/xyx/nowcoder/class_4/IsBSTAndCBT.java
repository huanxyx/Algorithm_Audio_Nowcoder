package com.xyx.nowcoder.class_4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 判断一颗二叉树是否为搜索二叉树
 * 判断一颗二叉树是否为完全二叉树
 * @author huan
 * @date 2018年6月16日
 */
public class IsBSTAndCBT {
	public static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	//判断一棵二叉树是否为搜索二叉树
	public static boolean isBST(Node root) {		
		int last = Integer.MIN_VALUE;
		Stack<Node> stack = new Stack<Node>();
		Node curNode = root;
		
		while (!stack.isEmpty() || curNode != null) {
			if (curNode != null) {
				stack.push(curNode);
				curNode = curNode.left;
			} else {
				Node pop = stack.pop();
				int value = pop.value;
				if (value < last)
					return false;
				last = value;
				curNode = pop.right;
			}
		}
		return true;
	}
	
	//判断一棵树是否为完全二叉树（按层遍历）
	public static boolean isCBT(Node root) {
		if (root == null)
			return true;
		
		boolean leaf = false;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			//若是左儿子为空，右儿子不为空，则一定不为完全二叉树
			if (curNode.right != null && curNode.left == null)
				return false;
			//必须是叶子节点的情况
			if (leaf && (curNode.left != null || curNode.right != null))
				return false;
			//若是他的儿子节点不为空，则添加到队列中
			if (curNode.left != null)
				queue.offer(curNode.left);
			if (curNode.right != null)
				queue.offer(curNode.right);
			//若是都为空，或者只有左儿子为空
			else 
				leaf = true;
		}
		return true;
	}	
	
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

	//test
	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(5);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.right = new Node(6);

		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));

	}
}
