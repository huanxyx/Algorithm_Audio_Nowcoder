package com.xyx.nowcoder.class_6;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim算法求解最小生成树
 * @author huan
 * @date 2018年6月18日
 */
public class Prim {
	
	public static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
	}
	
	public static LinkedList<Edge> prim(Graph graph) {
		LinkedList<Edge> result = new LinkedList<Edge>();
		//需要个优先队列，用来在所有解锁的边中获取最短的边
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>(new EdgeComparator());
		//需要个set，用来记录已经遍历过的点，意味着去除无效边
		HashSet<Node> set = new HashSet<Node>();
		
		for (Node node : graph.nodes.values()) {
			//若是当前节点没有被遍历过
			if (!set.contains(node)) {
				//将节点算入集合中
				set.add(node);
				//同时将该节点的边加入到优先队列中
				for (Edge edge : node.edges) 
					priorityQueue.offer(edge);
				//当优先队列中没有边的时候，则遍历完毕
				while (!priorityQueue.isEmpty()) {
					//找到最小的那条边
					Edge edge = priorityQueue.poll();
					//找到通过这条边能够到达的节点
					Node toNode = edge.to;
					//若是该节点未曾遍历过，则加入遍历的集合中，也意味着当前边在最小生成树中
					if (!set.contains(toNode)) {
						result.add(edge);
						set.add(toNode);
						for (Edge newEdge : toNode.edges) 
							priorityQueue.offer(newEdge);
					}
				}
			}
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
		int[][] rels = new int[][] {
			{1, 2, 3},
			{1, 3, 5},
			{2, 3, 4}
		};
		
		Graph graph = GraphicGenerator.generate(rels, true);
		List<Edge> sortedResult = Prim.prim(graph);
		System.out.println(sortedResult);
	}
}
