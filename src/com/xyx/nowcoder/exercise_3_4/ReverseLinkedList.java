package com.xyx.nowcoder.exercise_3_4;

import java.util.Scanner;

/**
 * 反转链表
 * 问题链接地址：https://www.nowcoder.com/questionTerminal/5f44c42fd21a42b8aeb889ab83b17ad0
 * @author huan
 * @date 2018年6月18日
 */
public class ReverseLinkedList {
	
	public static class ListNode{
		int cur;
		int value;
		int next;
		public ListNode (int cur, int value, int next) {
			this.cur = cur;
			this.value = value;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return String.format("%05d", cur) + " " 
						+ value + " " + (next == -1 ? "-1" : String.format("%05d", next));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int firstAddr = sc.nextInt();
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		//读取数据
		ListNode[] map = new ListNode[100002];
		for (int i = 0; i < N; i++) {
			int curAddr = sc.nextInt();
			int curValue = sc.nextInt();
			int nextAddr = sc.nextInt();
			
			ListNode node = new ListNode(curAddr, curValue, nextAddr);
			map[curAddr] = node;
		}
		
		//计算该链表的总长度
		int len = getLen(map, firstAddr);
		
		//借助一个数组将长度为K的每个部分进行逆转，
		//逆转后需要将上一个部分的最后一个元素进行处理，
		ListNode dummyHead = new ListNode(0, 0, firstAddr);
		ListNode lastPartTail = dummyHead;
		int[] helper = new int[K];
		for (int i = 0; i + K < len; i += K) {
			
			//读取K个节点到数组中
			int cur = lastPartTail.next;
			for (int j = 0; j < K; j++, cur = map[cur].next) 
				helper[j] = cur;
			
			//保留下一个部分的头元素
			int nextPartHead = map[helper[K-1]].next;
			
			//逆转K个节点的指向
			for (int j = K-1; j > 0; j--) {
				map[helper[j]].next = helper[j - 1];
			}

			//将逆转后的这一部分插入到前面和后面的两个部分
			map[helper[0]].next = nextPartHead;
			lastPartTail.next = helper[K - 1];
			
			lastPartTail = map[helper[0]];
		}
		
		printResult(map, dummyHead.next);
		sc.close();
	}
	
	public static void printResult(ListNode[] map, int headAddr) {
		while (headAddr != -1) {
			ListNode curNode = map[headAddr];
			System.out.println(curNode);
			headAddr = curNode.next;
		}
	}
	
	public static int getLen(ListNode[] map, int startAddr) {
		int len = 0;
		while (startAddr != -1) {
			len++;
			startAddr = map[startAddr].next;
		}
		return len;
	}
}
