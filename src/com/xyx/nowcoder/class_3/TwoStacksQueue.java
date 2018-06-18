package com.xyx.nowcoder.class_3;

import java.util.Stack;

/**
 * 使用两个栈实现队列
 * @author huan
 * @date 2018年6月10日
 */
public class TwoStacksQueue {
	private Stack<Integer> pushStack;
	private Stack<Integer> popStack;
	
	public TwoStacksQueue() {
		pushStack = new Stack<Integer>();
		popStack = new Stack<Integer>();
	}
	
	public void offer(Integer num) {
		pushStack.push(num);
	}
	
	public Integer poll() {
		transfer();
		if (popStack.isEmpty())
			throw new RuntimeException("The queue is empty");
		return popStack.pop();
	}
	
	public Integer peek() {
		transfer();
		if (popStack.isEmpty())
			throw new RuntimeException("The queue is empty");
		return popStack.peek();
	}
	
	//将push栈中的数据转移到pop栈中
	//当pop栈为空的时候才将push栈中所有的数据转移到pop栈中
	private void transfer() {
		if (!popStack.isEmpty())
			return ;
		while (!pushStack.isEmpty()) {
			popStack.push(pushStack.pop());
		}
	}
	
	//test
	public static void main(String[] args) {
		TwoStacksQueue queue = new TwoStacksQueue();
		for (int i = 0; i < 10; i++) 
			queue.offer(i);
		for (int i = 0; i < 10; i++)
			System.out.println(queue.poll());
		queue.poll();
	}
}
