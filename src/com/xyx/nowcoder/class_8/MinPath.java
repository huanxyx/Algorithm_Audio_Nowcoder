package com.xyx.nowcoder.class_8;

/**
 * 	给你一个二维数组， 二维数组中的每个数都是正数， 要求从左上
 * 	角走到右下角， 每一步只能向右或者向下。 沿途经过的数字要累
 * 	加起来。 返回最小的路径和。
 * @author huan
 * @date 2018年7月1日
 */
public class MinPath {

	/*
	 * 递归实现
	 */
	public static int minPathRecurse(int[][] weight, int curRow, int curCol) {
		int curWeight = weight[curRow][curCol];
		if (curRow == weight.length - 1 && curCol == weight[0].length - 1) 
			return curWeight;
		if (curRow == weight.length - 1)
			return curWeight + minPathRecurse(weight, curRow, curCol + 1);
		if (curCol == weight[0].length - 1)
			return curWeight + minPathRecurse(weight, curRow + 1, curCol);
		return curWeight + 
				Math.min(minPathRecurse(weight, curRow + 1, curCol), minPathRecurse(weight, curRow, curCol + 1));
	}
	
	/*
	 * 动态规划实现
	 */
	public static int minPathDP(int[][] weight) {
		int rows = weight.length;
		int cols = weight[0].length;
		int[][] dp = new int[rows][cols];
		
		//初始化(基准状态)
		dp[rows - 1][cols - 1] = weight[rows - 1][cols - 1];
		
		//状态转移，定义了每个状态之间的关系
		for (int i = cols - 2; i >= 0; i--) {
			dp[rows - 1][i] = dp[rows - 1][i + 1] + weight[rows - 1][i];
		}
		for (int i = rows - 2; i >= 0; i--) {
			dp[i][cols - 1] = dp[i + 1][cols - 1] + weight[i][cols - 1];
		}
		for (int i = rows - 2; i >= 0; i--) {
			for (int j = cols - 2; j >= 0; j--) {
				dp[i][j] = weight[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
			}
		}
		
		//得出最终状态
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		int[][] weight = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
		System.out.println(minPathRecurse(weight, 0, 0));
		System.out.println(minPathDP(weight));
	}

}
