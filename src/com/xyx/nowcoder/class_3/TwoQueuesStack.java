package com.xyx.nowcoder.class_3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈结构
 * @author huan
 * @date 2018年6月10日
 */
public class TwoQueuesStack {
	private Queue<Integer> dataQueue;
	private Queue<Integer> helpQueue;
	
	public TwoQueuesStack() {
		dataQueue = new LinkedList<>();
		helpQueue = new LinkedList<>();
	}
	
	//对dataqueue进行出队操作，只保留最后一个，这个便为最新加入的，也就是栈顶元素。
	public Integer pop() {
		if (dataQueue.isEmpty())
			throw new RuntimeException("The stack is empty!");
		while (dataQueue.size() > 1) {
			helpQueue.offer(dataQueue.poll());
		}
		int res = dataQueue.poll();
		swap();
		return res;
	}
	
	public void push(Integer num) {
		dataQueue.offer(num);
	}
	
	public Integer peek() {
		if (dataQueue.isEmpty())
			throw new RuntimeException("The stack is empty!");
		int res = this.pop();
		this.push(res);
		return res;
	}
	
	//交换两个队列的角色
	private void swap() {
		Queue<Integer> tmp = this.dataQueue;
		this.dataQueue = this.helpQueue;
		this.helpQueue = tmp;
	}
	
	//test
	public static void main(String[] args) {
		TwoQueuesStack stack = new TwoQueuesStack();
		for (int i = 0; i < 3; i++) {
			stack.push(i);
		}
		for (int i = 0; i < 3; i++) {
			System.out.println(stack.pop());
		}
	}
}
