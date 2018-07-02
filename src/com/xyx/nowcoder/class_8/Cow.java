package com.xyx.nowcoder.class_8;

/**
 * 	母牛每年生一只母牛， 新出生的母牛成长三年后也能每年生一只
 *	母牛， 假设不会死。 求N年后， 母牛的数量
 * @author huan
 * @date 2018年7月1日
 */
public class Cow {

	/*
	 * 递归实现
	 */
	public static int cow(int n) {
		if (n < 1)
			return 0;
		if (n == 1 || n == 2 || n == 3)
			return n;
		return cow(n - 1) + cow(n - 3);
	}
	
	/*
	 * 动态规划实现
	 */
	public static int cow_dp(int n) {
		if (n < 1)
			return 0;
		if (n == 1 || n == 2 || n == 3)
			return n;
		
		int[] num = new int[n + 1];
		for (int i = 1; i < 4; i++)
			num[i] = i;
		for (int i = 4; i <= n; i++)
			num[i] = num[i - 1] + num[i - 3];
		return num[n];
	}
	
	public static void main(String[] args) {
		System.out.println(cow(20));
		System.out.println(cow_dp(20));
	}
	
}
