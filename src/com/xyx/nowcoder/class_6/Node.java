package com.xyx.nowcoder.class_6;

import java.util.LinkedList;
import java.util.List;

/**
 * 图中的节点
 * @author huan
 * @date 2018年6月17日
 */
public class Node {
	public int in;								//入度
	public int out;							//出度
	public int value;							//存储的值
	public List<Node> nexts;					//能够到达的节点
	public List<Edge> edges;					//出边
	
	public Node(int value) {
		this.value = value;
		this.in = 0;
		this.out = 0;
		this.nexts = new LinkedList<Node>();
		this.edges = new LinkedList<Edge>();
	}
	
	/*
	 * 在一个节点上添加一条出边
	 */
	public void addNewOutEdge(Edge newEdge) {
		nexts.add(newEdge.to);
		edges.add(newEdge);
		out++;
	}
	
	/*
	 * 在一个节点上添加一条进边
	 */
	public void addNewInEdge(Edge newEdge) {
		in++;
	}
	
	public String toString() {
		return value + "";
	}
}
