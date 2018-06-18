package com.xyx.nowcoder.class_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先搜索
 * @author huan
 * @date 2018年6月18日
 */
public class BFS {
	
	/*
	 * 广度优先遍历，无需回到已经遍历过的点上：
	 * 			在从队列中取出元素的时候才算入输出序列中
	 * 			在进入队列的时候标记当前元素已经进入过队列了（set）
	 */
	public static List<Node> bfs(Node node) {
		ArrayList<Node> result = new ArrayList<Node>();	//结果集合
		Queue<Node> queue = new LinkedList<Node>();		//辅助队列
		HashSet<Node> set = new HashSet<Node>();		//用来表示经过队列的元素
		
		queue.add(node);
		set.add(node);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			result.add(cur);
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					queue.offer(next);
					set.add(next);
				}
			}
		}
		
		return result;
	}
	
	
	/*
	 * 返回广度优先遍历中每个节点的上一个节点
	 */
	public static Map<Node, Node> bfsLastNode (Node node) {
		Map<Node, Node> map = new HashMap<Node, Node>(); 	//存储结果
		Queue<Node> queue = new LinkedList<Node>();			//辅助队列
		HashSet<Node> set = new HashSet<Node>();			//用来表示经过队列的元素
		
		queue.add(node);
		set.add(node);
//		map.put(node, node);
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					queue.offer(next);
					set.add(next);
					map.put(next, cur);
				}
			}
		}
		
		return map;
	}
	
	//test
	public static void main(String[] args) {
		int[][] rels = new int[][] {
			{1, 7, 1},
			{1, 2, 1},
			{1, 8, 1},
			{1, 9, 1},
			{1, 10, 1},
			{2, 3, 1},
			{2, 4, 1},
			{3, 5, 1},
			{3, 4, 1},
			{4, 6, 1},
			{7, 4, 1},
			{7, 6, 1}
		};
		
		//测试广度优先遍历
		Graph graph = GraphicGenerator.generate(rels, true);
		List<Node> bfsResult = BFS.bfs(graph.getNode(1));
		System.out.println(bfsResult);
		
		//给定一个终结节点，求出在广度优先遍历中初始节点到该终结节点的路线。
		Map<Node, Node> bfsLastNodeMap = BFS.bfsLastNode(graph.getNode(1));
		Node endNode = graph.getNode(5);
		Stack<Node> reverse = new Stack<Node>(); 
		reverse.add(endNode);
		while (bfsLastNodeMap.containsKey(endNode)) {
			endNode = bfsLastNodeMap.get(endNode);
			reverse.add(endNode);
		}
		while (!reverse.isEmpty()) {
			System.out.print(reverse.pop() + "->");
		}
	}
}
