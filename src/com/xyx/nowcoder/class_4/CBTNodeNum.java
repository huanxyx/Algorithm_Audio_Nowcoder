package com.xyx.nowcoder.class_4;

/**
 * 获得完全二叉树节点的个数：时间复杂度小于O(N)
 * @author huan
 * @date 2018年6月16日
 */
public class CBTNodeNum {
	public static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	/*
	 * before：head是一个完全二叉树的头节点
	 */
	public static int getNodeNum(Node node) {
		return getNodeNum(node, getEdgeHeight(node));
	}

	private static int getNodeNum(Node head, int height) {
		if (height == 1)
			return 1;
		int leftHeight = height - 1;
		int rightHeight = getEdgeHeight(head.right);
		//若是左子树的高度和右子树的高度相同，那么意味着左子树是满二叉树
		if (leftHeight == rightHeight) 
			return (1 << leftHeight) + getNodeNum(head.right, rightHeight);
		else 
		//若是左子树的高度和右子树的高度不相同(即左子树比右子树高)，那么意味着右子树是满二叉树
			return (1 << rightHeight) + getNodeNum(head.left, leftHeight);
	}
	
	//获得一颗完全二叉树的高度(通过边界)
	private static int getEdgeHeight(Node head) {
		Node cur = head;
		int height = 0;
		while (cur != null) {
			cur = cur.left;
			height++;
		}
		return height;
	}
	

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(getNodeNum(head));

	}

}
