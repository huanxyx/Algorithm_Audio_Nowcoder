package com.xyx.nowcoder.class_4;

/**
 * 判断一颗二叉树是否为平衡二叉树
 * @author huan
 * @date 2018年6月16日
 */
public class IsBalancedTree {
	
	public static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	public static boolean isBalancedTree(Node root) {
		return getBalancedTreeHeight(root) != -1;
	}
	
	//获取一个平衡二叉树的高度，若是这棵树不是平衡二叉树，则返回-1
	private static int getBalancedTreeHeight(Node root) {
		if (root == null)
			return 0;
		int leftHeight = getBalancedTreeHeight(root.left);
		if (leftHeight == -1)
			return -1;
		int rightHeight = getBalancedTreeHeight(root.right);
		if (rightHeight == -1)
			return -1;		
		if (Math.abs(rightHeight - leftHeight) > 1)
			return -1;
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	//test
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.right.right = new Node(8);

		System.out.println(isBalancedTree(head));
	}
}
