package com.xyx.nowcoder.class_3;

/**
 * 数组实现大小固定的队列
 * @author huan
 * @date 2018年6月12日
 */
public class ArrayQueue {
	private int[] queue;
	private int front;
	private int rear;
	private int size;
	
	public ArrayQueue(int initSize) {
		if (initSize < 0) 
			throw new IllegalArgumentException("The initsize is less than 0!");
		queue = new int[initSize];
		front = rear = size = 0;
	}
	
	public void offer(int num) {
		if (size >= queue.length)
			throw new ArrayIndexOutOfBoundsException("The queue is full!");
		queue[rear] = num;
		rear = rear == queue.length - 1 ? 0 : rear + 1;
		size++;
	}
	
	public int poll() {
		if (size <= 0)
			throw new ArrayIndexOutOfBoundsException("The queue is empty!");
		int res = queue[front];
		front = front == queue.length - 1 ? 0 : front + 1;
		size--;
		return res;
	}
	
	public int peek() {
		if (size <= 0)
			throw new ArrayIndexOutOfBoundsException("The queue is empty!");
		return queue[front];
	}
	
	//test
	public static void main(String[] args) {
		ArrayQueue queue = new ArrayQueue(3);
		for (int i = 0; i < 4; i++) 
			queue.offer(i);
		for (int i = 0; i < 4; i++) 
			System.out.println(queue.poll());
	}
}
