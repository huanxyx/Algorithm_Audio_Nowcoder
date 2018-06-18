package com.xyx.nowcoder.class_3;

/**
 * 利用数组实现大小固定的栈
 * @author huan
 * @date 2018年6月9日
 */
public class ArrayStack {
	private int[] stack;
	private int stackTop;
	
	public ArrayStack(int initSize) {
		if (initSize < 0)
			throw new IllegalArgumentException("InitSize is less than 0!");
		stack = new int[initSize];
		stackTop = 0;
	}
	
	public int pop() {
		if (stackTop == 0)
			throw new ArrayIndexOutOfBoundsException("The stack is empty!");
		return stack[--stackTop];
	}
	
	public void push(int num) {
		if (stackTop == stack.length)
			throw new ArrayIndexOutOfBoundsException("The stack is full!");
		stack[stackTop++] = num;
	}
	
	public int peek() {
		if (stackTop == 0)
			throw new ArrayIndexOutOfBoundsException("The stack is empty!");
		return stack[stackTop - 1];
	}
	
	// test
	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(3);
		
		for (int i = 0; i < 3; i++) {
			stack.push(i);
		}
		for (int i = 0; i < 3; i++) {
			System.out.println(stack.pop());
		}
	}
}
