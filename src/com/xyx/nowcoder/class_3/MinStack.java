package com.xyx.nowcoder.class_3;

import java.util.Stack;

/**
 * 功能加强的栈，提供了一个新的方法，返回当前栈中最小值，时间复杂度为O(1)，其他操作跟栈的操作一样
 * @author huan
 * @date 2018年6月9日
 */
public class MinStack {
	private Stack<Integer> dataStack;
	private Stack<Integer> minStack;
	
	public MinStack() {
		dataStack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	public void push(Integer newNum) {
		if (minStack.isEmpty() || newNum < minStack.peek())
			minStack.push(newNum);
		else
			minStack.push(minStack.peek());
		dataStack.push(newNum);
	}
	
	public Integer pop() {
		if (dataStack.isEmpty())
			throw new RuntimeException("Your stack is empty.");
		minStack.pop();
		return dataStack.pop();
	}
	
	public Integer peek() {
		if (dataStack.isEmpty())
			throw new RuntimeException("Your stack is empty.");
		return dataStack.peek();
	}
	
	public Integer getMin() {
		if (dataStack.isEmpty())
			throw new RuntimeException("Your stack is empty.");
		return minStack.peek();
	}
	
	public static void main(String[] args) {
		MinStack stack1 = new MinStack();
		stack1.push(3);
		System.out.println(stack1.getMin());
		stack1.push(4);
		System.out.println(stack1.getMin());
		stack1.push(1);
		System.out.println(stack1.getMin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getMin());

		System.out.println("=============");

		MinStack stack2 = new MinStack();
		stack2.push(3);
		System.out.println(stack2.getMin());
		stack2.push(4);
		System.out.println(stack2.getMin());
		stack2.push(1);
		System.out.println(stack2.getMin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getMin());
	}
}
