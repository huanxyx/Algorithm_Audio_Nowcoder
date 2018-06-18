package com.xyx.nowcoder.class_6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Dijkstra
 * @author huan
 * @date 2018年6月18日
 */
public class Dijkstra {

	/*
	 * 基本实现
	 */
	public static Map[] dijkstra(Node root) {
		HashMap<Node, Integer> distance = new HashMap<Node, Integer>();		//每个节点的最短距离
		HashMap<Node, Node> precursor = new HashMap<Node, Node>();			//每个节点的前驱节点
		HashSet<Node> confirm = new HashSet<Node>();						//确定的节点
		
		distance.put(root, 0);
		precursor.put(root, null);
		
		//距离源点路径最短的节点
		Node minNode = getMinDistanceNode(distance, confirm);

		while (minNode != null) {
			//确认该节点
			confirm.add(minNode);
			//松弛操作
			int curNodeDis = distance.get(minNode);
			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;
				int newDis = curNodeDis + edge.weight;
				if (distance.getOrDefault(toNode, Integer.MAX_VALUE) > newDis) {
					//需要进行松弛操作的节点
					distance.put(toNode, newDis);
					precursor.put(toNode, minNode);
				}
			}
			//获取新的一轮节点
			minNode = getMinDistanceNode(distance, confirm);
		}
		
		return new Map[] {distance, precursor};		
	}
	
	/*
	 * 获取路径最短且没有被确定的节点
	 */
	private static Node getMinDistanceNode(HashMap<Node, Integer> distance, HashSet<Node> confirm) {
		
		Node minNode = null;
		int minDis = Integer.MAX_VALUE;
		for (Map.Entry<Node, Integer> entry : distance.entrySet()) {
			Node node = entry.getKey();
			int dis = entry.getValue();
			if (!confirm.contains(node) && dis < minDis) {
				minNode = node;
				minDis = dis;
			}
		}
		
		return minNode;
	}
	
	/*
	 * 借助自己实现的堆
	 */
	public static Map[] dijkstra_1(Node root) {
		HashMap<Node, Integer> distanceMap = new HashMap<Node, Integer>();
		HashMap<Node, Node> precursorMap = new HashMap<Node, Node>();
		
		MinDisHeap heap = new MinDisHeap(100);
		heap.offer(root, null, 0);
		while (!heap.isEmpty()) {
			Record record = heap.poll();
			distanceMap.put(record.curNode, record.dis);
			precursorMap.put(record.curNode, record.preNode);
			
			for (Edge edge : record.curNode.edges) 
				heap.offer(edge.to, edge.from, edge.weight + record.dis);
		}
		return new Map[] {distanceMap, precursorMap};
	}
	
	/*
	 * 记录
	 */
	public static class Record {
		Node curNode;
		Node preNode;
		int dis;
		
		public Record(Node curNode, Node preNode, int dis) {
			this.curNode = curNode;
			this.preNode = preNode;
			this.dis = dis;
		}
	}
	
	/*
	 * 最小路径堆，可以动态调节其中元素的数量
	 */
	public static class MinDisHeap {
		private Node[] heap;							
		private int N;
		private HashMap<Node, Integer> heapIndexMap;	//用于查询一个节点在堆中的位置，或者判断是否确认过
		private HashMap<Node, Integer> distanceMap;		//用于查询一个节点的最小距离
		private HashMap<Node, Node>	   precursorMap;	//用于查询一个节点的前驱节点
		
		public MinDisHeap(int size) {
			heap = new Node[size + 1];
			N = 0;
			heapIndexMap = new HashMap<Node, Integer>();
			distanceMap = new HashMap<Node, Integer>();
			precursorMap = new HashMap<Node, Node>();
		}
		
		//添加一条新边
		public void offer(Node toNode, Node fromNode, int dis) {
			//判断该节点是否已经确定
			if (hasPoll(toNode))
				return ;
			//判断该节点是否在优先队列中
			if (isInHeap(toNode)) {
				//在优先队列中，并需要调整堆
				if (distanceMap.get(toNode) > dis) {
					int index = heapIndexMap.get(toNode);		//找到该节点在堆中的位置
					distanceMap.put(toNode, dis);
					precursorMap.put(toNode, fromNode);
					swim(index);								//调整
				}
			} else {
				//不在该优先队列中
				setIndex(toNode, N++);
				distanceMap.put(toNode, dis);
				precursorMap.put(toNode, fromNode);
			}
		}
		
		//判断堆是否为空
		public boolean isEmpty() {
			return N == 0;
		}
		
		//从堆中选择路径最短的一个节点
		public Record poll() {
			//删除一个最短的节点
			Node pollNode = heap[0];
			Record record = new Record(pollNode, precursorMap.get(pollNode), distanceMap.get(pollNode));
			heapIndexMap.put(pollNode, -1);
			distanceMap.remove(pollNode);
			precursorMap.remove(pollNode);
			
			//调整堆
			setIndex(heap[--N], 0);
			heap[N] = null;
			sink(0);
			
			return record;
		}
		
		//往上浮
		private void swim(int pos) {
			Node curNode = heap[pos];
			int curDis = getMinDis(curNode);
			
			int cur = pos;
			int father = (pos - 1) / 2;
			while (curDis < getMinDis(heap[father])) {
				setIndex(heap[father], cur);
				cur = father;
				father = (cur - 1) / 2;
			}
			setIndex(curNode, cur);
		}
		
		//往下沉
		private void sink(int pos) {
			Node curNode = heap[pos];
			
			int cur = pos;
			int left = cur * 2 + 1;
			while (left < N) {
				int minIndex = (left + 1 > N) && getMinDis(heap[left]) > getMinDis(heap[left + 1]) ?
								(left + 1) : left;
				if (getMinDis(heap[minIndex]) < getMinDis(curNode)) {
					//下移
					setIndex(heap[minIndex], cur);
				} else 
					break;
				cur = minIndex;
				left = cur * 2 + 1;
			}
			
			setIndex(curNode, cur);
		}
		
		//判断一个节点是否在该优先队列中
		private boolean isInHeap(Node node) {
			return heapIndexMap.containsKey(node);
		}
		
		//判断一个节点是否已经出去了
		private boolean hasPoll(Node node) {
			return isInHeap(node) && heapIndexMap.get(node) == -1;
		}
		
		//获取一个节点的最短距离
		private int getMinDis(Node node) {
			return distanceMap.get(node);
		}
		
		//修改一个节点在堆中的索引
		private void setIndex(Node node, int index) {
			heap[index] = node;
			heapIndexMap.put(node, index);
		}
	}
	
	//test
	public static void main(String[] args) {
		int[][] rels = new int[][] {
			{1, 7, 1},
			{1, 2, 1},
			{2, 3, 1},
			{2, 4, 1},
			{3, 5, 1},
			{3, 4, 1},
			{4, 6, 1},
			{7, 4, 1},
			{7, 6, 1}
		};
		
		Graph graph = GraphicGenerator.generate(rels, true);
		Map[] dijkstra = Dijkstra.dijkstra_1(graph.getNode(1));
		System.out.println(dijkstra[0]);
		System.out.println(dijkstra[1]);
	}
}
