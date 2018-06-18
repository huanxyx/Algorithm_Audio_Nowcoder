package com.xyx.nowcoder.class_1_2.inclass;

/**
 * 优先队列：
 * 		下标从1开始
 * @author huan
 * @date 2018年6月8日
 */
public class PriorityQueue<Key extends Comparable<Key>> {
	private Key[] heap;
	private int size;
	
	public PriorityQueue() {
		this(10);
	}
	public PriorityQueue(int len) {
		heap = (Key[]) new Comparable[len + 1];
	}

	public PriorityQueue(Key[] arr) {
		this(arr.length);
		for (int i = 0; i < arr.length; i++) {
			insert(arr[i]);
		}
	}
	
	public void insert(Key num) {
		heap[++size] = num;
		swim(size);
	}
	
	public Key delMax() {
		if (isEmpty())
			throw new IllegalArgumentException("当前队列为空");
		Key max = heap[1];
		swap(1, size--);
		heap[size+1] = null; 
		sink(1);
		return max;
	} 
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public Key[] getHeap() {
		Key[] res = (Key[]) new Comparable[size];
		for (int i = 0; i < size; i++) {
			res[i] = heap[i+1];
		}
		return res;
	}
	
	//上浮
	private void swim(int k) {
		int father = k / 2;
		while (father > 0 && less(father, k)){
			swap(father, k);
			k = father;
			father = k / 2;
		}
	}
	
	//下沉
	private void sink(int k) {
		int nextPos = k * 2;
		while (nextPos <= size) {
			if (nextPos < size && less(nextPos, nextPos+1)) 
				nextPos++;
			if (less(k, nextPos))
				swap(k, nextPos);
			else 
				break;
			k = nextPos;
			nextPos = k * 2;
		}
	}
	
	private void swap(int a, int b) {
		Key tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}
	
	private boolean less(int a, int b) {
		return heap[a].compareTo(heap[b]) < 0;
	}
	
	//test
	public static void main(String[] args) {
		Integer[] arr = new Integer[] {1,41,5,6,1,2,4,2,6,8,3};
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(priorityQueue.delMax());
		}
	}
}
