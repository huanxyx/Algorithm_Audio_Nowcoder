package com.xyx.nowcoder.class_6;

/**
 * 图中的边
 * @author huan
 * @date 2018年6月17日
 */
public class Edge {
	public Node from;		
	public Node to;
	public int weight;
	
	public Edge(Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public String toString() {
		return from + "=>" + to;// + "：" + weight;
	}

}
