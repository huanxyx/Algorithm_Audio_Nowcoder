package com.xyx.nowcoder.class_6;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.xyx.nowcoder.class_5.UnionFind;

/**
 * Kruskal算法
 * @author huan
 * @date 2018年6月18日
 */
public class Kruskal {
	
	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}
		
	}
	
	public static LinkedList<Edge> kruskal(Graph graph) {
		LinkedList<Edge> result = new LinkedList<Edge>();
		//并查集用来判断是否形成回路（也就是新增的一条边的两个节点是否相连）
		UnionFind<Node> nodeUF = new UnionFind<Node>();
		nodeUF.makeSets(graph.nodes.values());
		//优先队列用来选择最小的边
		PriorityQueue<Edge> priorityQueue = 
				new PriorityQueue<Edge>(new EdgeComparator());
		for (Edge edge : graph.edges) 
			priorityQueue.offer(edge);
		
		while (!priorityQueue.isEmpty()) {
			Edge curEdge = priorityQueue.poll();
			//当不形成回路的时候，则将该边添加进最小生成树中
			if (!nodeUF.isSameSet(curEdge.from, curEdge.to)) {
				nodeUF.union(curEdge.from, curEdge.to);
				result.add(curEdge);
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
		List<Edge> sortedResult = Kruskal.kruskal(graph);
		System.out.println(sortedResult);
	}

}
