package com.xyx.nowcoder.class_7;

import java.util.PriorityQueue;

/**
 * 给定一个数组[a1,a2,...an]，求出将（a1+a2...+an）分成a1,a2,...an的最小代价
 * 将b分成成a1和a2的代价为b
 * @author huan
 * @date 2018年7月1日
 */
public class LeastMoney {

	public static int leastMoney(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		
		PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
		for (int price : prices) {
			minQ.offer(price);
		}
		
		int sum = 0;
		while (minQ.size() > 1) {
			int cur = minQ.poll() + minQ.poll();
			sum += cur;
			minQ.offer(cur);
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(leastMoney(new int[] {10, 20, 103}));
	}

}
