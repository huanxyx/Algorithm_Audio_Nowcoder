package com.xyx.nowcoder.class_4;

import java.util.Stack;

/**
 * 先序，中序，后序遍历的递归和非递归实现：
 * 			
 * @author huan
 * @date 2018年6月15日
 */
public class PreInSubTraversal {
	public static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	//先序遍历的递归实现
	public static void preTraversalRecurse(Node root) {
		if (root != null) {
			System.out.print(root.value + " ");
			preTraversalRecurse(root.left);
			preTraversalRecurse(root.right);
		}
	}
	
	//中序遍历的递归实现
	public static void inTraversalRecurse(Node root) {
		if (root != null) {
			inTraversalRecurse(root.left);
			System.out.print(root.value + " ");
			inTraversalRecurse(root.right);
		}
	}
	
	//后序遍历的递归实现
	public static void subTraversalRecurse(Node root) {
		if (root != null) {
			subTraversalRecurse(root.left);
			subTraversalRecurse(root.right);
			System.out.print(root.value + " ");
		}
	}
	
	//先序遍历的非递归实现
	public static void preTraversalNoRecurse(Node root) {
		if (root != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(root);
			while (!stack.isEmpty()) {
				Node cur = stack.pop();
				System.out.print(cur.value + " ");
				//先压入右节点
				if (cur.right != null) 
					stack.push(cur.right);
				//再压入左节点
				if (cur.left != null)
					stack.push(cur.left);
			}
		}
		System.out.println();
	}
	
	/*
	 * 中序遍历的非递归实现：
	 * 		先将最左边的的全部压入栈中，然后再慢慢弹出，发现弹出来的节点有右节点，则将该右节点压入栈中，
	 * 		然后再将该右节点当作根节点重复以上操作。直到根节点的右子树都遍历完。
	 */
	public static void inTraversalNoRecurse(Node root) {
		if (root != null) {
			Node cur = root;							//遍历到的当前元素
			Stack<Node> stack = new Stack<Node>();		//还未遍历到的，
			while (!stack.isEmpty() || cur != null) {
				//当前遍历的节点不为空，则将当前节点压入栈中，然后遍历左子树
				if (cur != null) {
					stack.push(cur);
					cur = cur.left;
				} else {
					//当前遍历的节点为空时，表示左子树的部分遍历完了，应该遍历该左子树的父节点，然后再遍历右子树
					Node pop = stack.pop();
					System.out.print(pop.value + " ");
					cur = pop.right;
				}
			}
		}
		System.out.println();
	}
	
	/*
	 * 后序遍历的非递归实现：
	 * 			查看栈中的当前节点
	 * 				1）若是当前节点的左子树还没有遍历的，则将左子树的根节点放入栈中
	 * 				2）在1）不成立的前提下，若是当前节点的右子树还没有遍历的，则将右子树的根节点放入栈中
	 * 				3）在1），2）都不成立的前提下，也就是左右子树都已经遍历完了，则将当前节点输出。
	 * 			
	 */
	public static void subTraversalNoRecurse_1(Node root) {
		if (root != null) {
			Stack<Node> stack = new Stack<Node>();
			Node head = root;
			stack.push(root);
			while (!stack.isEmpty()) {
				Node cur = stack.peek(); 		//查看当前遍历到的节点
				//head表示已经遍历完的子树的根节点
				if (cur.left != null && head != cur.left && head != cur.right) 
					stack.push(cur.left);
				else if (cur.right != null && head != cur.right)
					stack.push(cur.right);
				else {
					System.out.print(stack.pop().value + " ");
					//标记已经遍历子树的根节点
					head = cur;
				}
					
			}
		}
		System.out.println();
	}
	
	//后序遍历：借助两个栈实现（中右左=》左右中）
	public static void subTraversalNoRecurse_2(Node root) {
		if (root != null) {
			Stack<Node> result_stack = new Stack<Node>();
			Stack<Node> stack = new Stack<Node>();
			stack.push(root);
			while (!stack.isEmpty()) {
				Node cur = stack.pop();
				result_stack.push(cur);
				
				if (cur.left != null)
					stack.push(cur.left);
				if (cur.right != null)
					stack.push(cur.right);
			}
			
			while (!result_stack.isEmpty())
				System.out.print(result_stack.pop().value + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		preTraversalRecurse(head);
		System.out.println();
		preTraversalNoRecurse(head);
		System.out.println();
		inTraversalRecurse(head);
		System.out.println();
		inTraversalNoRecurse(head);
		System.out.println();
		subTraversalRecurse(head);
		System.out.println();
		subTraversalNoRecurse_1(head);
		subTraversalNoRecurse_2(head);
	}
}
