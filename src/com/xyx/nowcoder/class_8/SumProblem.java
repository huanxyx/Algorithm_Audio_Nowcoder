package com.xyx.nowcoder.class_8;

/**
 * 	给你一个数组arr， 和一个整数aim。 如果可以任意选择arr中的
 * 	数字， 能不能累加得到aim， 返回true或者false
 * @author huan
 * @date 2018年7月1日
 */
public class SumProblem {

	/*
	 * 递归实现
	 */
	public static boolean canGetRecurse(int[] arr, int cur, int aim) {
		if (aim == 0)
			return true;
		if (cur == arr.length)
			return false;
		
		return canGetRecurse(arr, cur + 1, aim) || canGetRecurse(arr, cur + 1, aim - arr[cur]);
	}
	
	/*
	 * 动态规划实现
	 */
	public static boolean canGetDP(int[] arr, int aim) {
		//cur的变化范围为0 - arr.length
		//aim的变化范围为0 - aim
		boolean[][] dp = new boolean[arr.length + 1][aim + 1];
		for (int i = 0; i <= arr.length; i++) 
			dp[i][0] = true;
		
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 1; j <= aim; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j - arr[i] >= 0)
					//状态转移方程
					dp[i][j] = dp[i + 1][j - arr[i]] || dp[i][j];
			}
		}
		return dp[0][aim];
	}
	
	public static void main(String[] args) {
		int[] arr = { 1, 4, 8, 5};
		int aim = 11;
		System.out.println(canGetRecurse(arr, 0, aim));
		System.out.println(canGetDP(arr, aim));
	}

}
