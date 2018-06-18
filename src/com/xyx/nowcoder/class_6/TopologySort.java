package com.xyx.nowcoder.class_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 拓扑排序
 * @author huan
 * @date 2018年6月18日
 */
public class TopologySort {
	/*
	 * 前提条件，无环，有向
	 */
	public static List<Node> sortedTopology(Graph graph) {
		Map<Node, Integer> inDegree = new HashMap<Node, Integer>();	//存储每个节点的入度
		Queue<Node> zeroInDegreeNode = new LinkedList<Node>();		//存储完成节点的先后顺序
		
		List<Node> result = new ArrayList<Node>();
		
		//初始化队列和map
		for (Node node : graph.nodes.values()) {
			inDegree.put(node, node.in);
			if (node.in == 0)
				zeroInDegreeNode.offer(node);
		}
		
		while (!zeroInDegreeNode.isEmpty()) {
			Node finishedNode = zeroInDegreeNode.poll();		//当前应该完成的节点
			result.add(finishedNode);
			//遍历所有能够到达的下一个节点
			for (Node next : finishedNode.nexts) {
				inDegree.put(next, inDegree.get(next) - 1);
				//当有新的节点可以被完成的时候
				if (inDegree.get(next) == 0) 
					zeroInDegreeNode.offer(next);
			}
		}
		
		return result;
	}
	
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
			{4, 5, 1},
			{7, 4, 1},
			{7, 6, 1}
		};
		
		//测试广度优先遍历
		Graph graph = GraphicGenerator.generate(rels, true);
		List<Node> sortedResult = TopologySort.sortedTopology(graph);
		System.out.println(sortedResult);
	}
}
