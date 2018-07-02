package com.xyx.nowcoder.class_8;

/**
 * 给定两个数组w和v， 两个数组长度相等， w[i]表示第i件商品的
 * 重量， v[i]表示第i件商品的价值。 再给定一个整数bag， 要求
 * 你挑选商品的重量加起来一定不能超 过bag， 返回满足这个条件
 * 下， 你能获得的最大价值。
 * @author huan
 * @date 2018年7月1日
 */
public class Knapsack {

	/*
	 * 递归求解背包问题：
	 * bag：剩余的可放置的重量
	 * curNum：物品的数量
	 */
	public static int knapsackRecurs(int[] weights, int[] values, int bag, int curNum) {
		//没有空间了
		if (bag == 0)
			return 0;
		//没有物品了
		if (curNum == 0)
			return 0;
									
		//最后一个物品放不进背包
		if (weights[curNum - 1] > bag)
			return knapsackRecurs(weights, values, bag, curNum - 1);
		
		//比较放入当前物品和不放入当前物品两种情况下那种价格最大
		return Math.max(knapsackRecurs(weights, values, bag, curNum - 1), 
				knapsackRecurs(weights, values, bag - weights[curNum - 1], curNum - 1) + values[curNum - 1]);
	}
	
	/*
	 * 动态规划实现
	 */
	public static int knapsackDP(int[] weights, int[] values, int bag) {
		int num = weights.length;
		//dp[a][b]：表示有0...a-1个物品，背包容量为b的时候的最大价值
		int[][] dp = new int[num + 1][bag + 1];
		
		for (int i = 1; i <= num; i++) {
			for (int j = 0; j <= bag; j++) {
				if (j - weights[i - 1] >= 0)
					dp[i][j] = Math.max(
							dp[i-1][j], 
							dp[i-1][j - weights[i - 1]] + values[i - 1]);
				else
					dp[i][j] = dp[i-1][j];
			}
		}
		
		return dp[weights.length][bag];
	}
	
	//test
	public static void main(String[] args) {
		int[] c = { 3, 2, 4, 7 };
		int[] p = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(knapsackRecurs(c, p, bag, c.length));
		System.out.println(knapsackDP(c, p, bag));

	}

}
