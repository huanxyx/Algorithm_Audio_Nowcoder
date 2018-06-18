package com.xyx.nowcoder.class_5;

import java.util.Collection;
import java.util.HashMap;

/**
 * 并查集的实现：
 * 		一个map记录每个节点的父节点，若是没有父节点则为他本身
 * 		一个map记录根节点所代表的树的节点的个数
 * @author huan
 * @date 2018年6月17日
 */
public class UnionFind<Node> {
	private HashMap<Node, Node> fatherMap;	//一个节点的父节点
	private HashMap<Node, Integer> sizeMap; //一个节点所在集合节点的数目（只有是根节点才有效）
	
	public UnionFind() {
		fatherMap = new HashMap<Node, Node>();
		sizeMap = new HashMap<Node, Integer>();
	}
	
	/*
	 * 将节点加入到集合中
	 */
	public void makeSets(Collection<Node> list) {
		fatherMap.clear();
		sizeMap.clear();
		for (Node node : list) {
			fatherMap.put(node, node);
			sizeMap.put(node, 1);
		}
	}

	/*
	 * 连接node1和node2
	 */
	public void union(Node node1, Node node2) {
		if (node1 == null || node2 == null)
			return ;

		Node root1 = findRoot(node1);
		Node root2 = findRoot(node2);
		if (root1 != root2) {
			int size1 = sizeMap.get(root1);
			int size2 = sizeMap.get(root2);
			if (size1 <= size2) {
				fatherMap.put(root1, root2);
				sizeMap.put(root2, size1 + size2);
			} else {
				fatherMap.put(root2, root1);
				sizeMap.put(root1, size1 + size2);
			}
		}
	}
	
	/*
	 * 判断node1和node2是否为同一个集合
	 */
	public boolean isSameSet(Node node1, Node node2) {
		return findRoot(node1) == findRoot(node2);
	}
	
	//找到一个节点的代表节点
	private Node findRoot(Node node) {
		Node father = fatherMap.get(node);
		if (father != node) {
			father = findRoot(father);
		}
		fatherMap.put(node, father);
		return father;
	}
	
}
