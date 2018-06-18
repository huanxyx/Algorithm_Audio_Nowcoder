package com.xyx.nowcoder.class_4;

/**
 * 获取一个节点的后继节点，获取前驱节点
 * @author huan
 * @date 2018年6月15日
 */
public class SuccessorOrPrecursorNode {
	public static class Node {
		int value;
		Node left;
		Node right;
		Node father;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	//获取一个节点的后继节点
	public static Node getSuccessorNode(Node node) {
		if (node == null)
			return null;
		if (node.right != null)
			return getMostNode(node.right);
		else {
			//找到第一个节点是他父节点的左儿子的情况
			Node father = node.father;
			while (father != null && father.left != node) {
				node = node.father;
				father = node.father;
			}
			
			return father;
		}
	}
	
	//找到当前子树第一个遍历的元素
	private static Node getMostNode(Node head) {
		while (head.left != null)
			head = head.left;
		return head;
	}
	
	//获取一个节点的前驱节点
	public static Node getPrecursorNode(Node node) {
		if (node == null)
			return null;
		if (node.left != null) 
			return getLastNode(node.left);
		else {
			Node father = node.father;
			while (father != null && father.right != node) {
				node = node.father;
				father = node.father;
			}
			return father;
		}
	}
	
	//找到当前子树最后一个遍历的元素
	private static Node getLastNode(Node head) {
		while (head.right != null)
			head = head.right;
		return head;
	}
	
	//for test
	public static void main(String[] args) {
		Node head = new Node(6);
		head.father = null;
		head.left = new Node(3);
		head.left.father = head;
		head.left.left = new Node(1);
		head.left.left.father = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.father = head.left.left;
		head.left.right = new Node(4);
		head.left.right.father = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.father = head.left.right;
		head.right = new Node(9);
		head.right.father = head;
		head.right.left = new Node(8);
		head.right.left.father = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.father = head.right.left;
		head.right.right = new Node(10);
		head.right.right.father = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " prev: " + getPrecursorNode(test));
		test = head.left.left.right;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.left;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.right;
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
		test = head.right.right; 
		System.out.println(test.value + " prev: " + getPrecursorNode(test).value);
	}
}
