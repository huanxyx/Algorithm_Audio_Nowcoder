package com.xyx.nowcoder.class_6;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 * @author huan
 * @date 2018年6月17日
 */
public class Graph {
	/*
	 * 一个图有两个集合：
	 * 			一个是值与节点的映射关系
	 * 			一个是边的集合
	 */
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<Integer, Node>();
		edges = new HashSet<Edge>();
	}
	
	/*
	 * 在图中添加一条新的边
	 */
	public void addNewEdge(Integer from, Integer to, int weight) {
		//里面没有包含节点
		if (!nodes.containsKey(from))
			nodes.put(from, new Node(from));
		if (!nodes.containsKey(to))
			nodes.put(to, new Node(to));
		//获取节点
		Node fromNode = nodes.get(from);
		Node toNode = nodes.get(to);
		Edge newEdge = new Edge(fromNode, toNode, weight);
		
		//添加边的信息
		fromNode.addNewOutEdge(newEdge);
		toNode.addNewInEdge(newEdge);
		
		edges.add(newEdge);
	}
	
	/**
	 * 获取一个值对应的节点
	 * @param value
	 * @return
	 */
	public Node getNode(int value) {
		return nodes.get(value);
	}
}
