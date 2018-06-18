package com.xyx.nowcoder.class_6;

/**
 * 图的生成器
 * @author huan
 * @date 2018年6月17日
 */
public class GraphicGenerator {
	
	/**
	 * from to weight
	 * @param rels			边的集合，数组表示形式
	 * @param directed		当前图是否有向
	 * @return
	 */
	public static Graph generate(int[][] rels, boolean directed) {
		Graph graph = new Graph();
		for (int[] rel : rels) {
			graph.addNewEdge(rel[0], rel[1], rel[2]);
			if (!directed)
				graph.addNewEdge(rel[1], rel[0], rel[2]);
		}

		return graph;
	}
}
