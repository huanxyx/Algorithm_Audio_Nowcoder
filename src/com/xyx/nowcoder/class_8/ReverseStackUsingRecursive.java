package com.xyx.nowcoder.class_8;

import java.util.Stack;

/**
 * 	给你一个栈， 请你逆序这个栈， 不能申请额外的数据结构， 只能
 * 	使用递归函数。 如何实现
 * @author huan
 * @date 2018年7月1日
 */
public class ReverseStackUsingRecursive {
	
	/*
	 * 1.找到最后一个元素，并删除
	 * 2.将剩下的元素逆序
	 * 3.将首先获取的最后一个元素放在栈顶
	 */
	public static void reverseStack(Stack<Integer> stack) {
		if (stack.isEmpty())
			return ;
		else {
			int last = getLastAndRemove(stack);
			reverseStack(stack);
			stack.push(last);
		}
	}
	
	/*
	 * 从栈中弹出一个元素
	 * 			若是此时栈为空，则这个元素为最后一个元素，直接返回
	 * 			若是此时栈不为空，则递归调用获取最后一个元素，然后再将该元素放到栈顶，接着返回之前递归返回的元素
	 */
	private static int getLastAndRemove(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty())
			return result;
		else {
			int last = getLastAndRemove(stack);
			stack.push(result);
			return last;
		}
	}
	
	//test
	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverseStack(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
	}

}
