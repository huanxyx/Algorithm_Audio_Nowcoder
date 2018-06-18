package com.xyx.nowcoder.exercise_3_4;

import java.util.Stack;

/**
 * 判断一个链表是否为回文链表
 * 问题链接：https://www.nowcoder.com/questionTerminal/baefd05def524a92bcfa6e1f113ed4f0
 * @author huan
 * @date 2018年6月16日
 */
public class PlalindromeList {
	
	public static class ListNode{
		int val;
		ListNode next;
		public ListNode(int value) {
			this.val = value;
		}
	}
	
	//栈+快慢指针
	public boolean isPalindrome(ListNode head) {
		if (head == null)
			return true;
		ListNode slow = head;
		ListNode fast = head;
		//将前半部分压入栈中（slow所处的位置为中点，或者中间两点的前一个）
		Stack<Integer> stack = new Stack<Integer>();
		while (fast.next != null && fast.next.next != null) {
			stack.push(slow.val);
			slow = slow.next;
			fast = fast.next.next;
		}
		//链表节点为奇数个
		if (fast.next == null) 
			slow = slow.next;
		else {
		//链表节点为偶数个
			stack.push(slow.val);
			slow = slow.next;
		}
		while (!stack.isEmpty()) {
			if (stack.pop() != slow.val)
				return false;
			slow = slow.next;
		}
		return true;
	}

}
