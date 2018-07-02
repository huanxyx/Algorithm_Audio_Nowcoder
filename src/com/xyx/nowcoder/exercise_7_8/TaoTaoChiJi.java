package com.xyx.nowcoder.exercise_7_8;

import java.util.Scanner;

/**
 * 问题：
 * 		Taotao的电脑带不动绝地求生，所以taotao只能去玩pc版的荒野行动
 * 		了，和绝地求生一样，游戏人物本身可以携带一定重量m的物品，装备背包
 * 		之后可以多携带h（h为0代表没有装备背包）重量的东西。玩了几天
 * 		taotao发现了一个BUG，当装备背包之后，如果可携带重量没有满，就
 * 		可以拿一个任意重的东西。（解释看样例）有一天taotao空降到了一个
 * 		奇怪的岛上，岛上有n件装备，每个装备都有重量Wi和威力值Vi,但taotao
 * 		不认识这些装备，所以他来求助你，挑选威力最大的装备，帮助他吃鸡。
 * 问题链接：https://www.nowcoder.com/acm/contest/74/B
 * @author huan
 * @date 2018年7月2日
 */
public class TaoTaoChiJi {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			if (n == 0)
				break;
			int m = sc.nextInt();
			int h = sc.nextInt();
			int[] weights = new int[n];
			int[] values = new int[n];
			for (int i = 0; i < n; i++) {
				weights[i] = sc.nextInt();
				values[i] = sc.nextInt();
			}
			//常规的背包问题
			if (0 == h)
				System.out.println(maxForce(m, weights, values));
			else 
				System.out.println(maxForceSpe(m + h, weights, values));
		}
		sc.close();
	}
	
	/*
	 * 常规情况求解
	 */
	public static int maxForce(int bag, int[] weights, int[] values) {
		int[] dp = new int[bag + 1];
		
		for (int i = 1; i <= weights.length; i++) {
			for (int j = bag; j >= weights[i - 1]; j--) {
				dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
			}
		}
		return dp[bag];
	}
	/*
	 * 特殊情况求解
	 */
	public static int maxForceSpe(int bag, int[] weights, int[] values) {
		int[] dp = new int[bag + 1];
		int max = 0;
		for (int extra = 1; extra <= weights.length; extra++) {			//extra是由于bug附加的那个武器
			zero(dp);
			for (int i = 1; i <= weights.length; i++) {					//i表示拥有武器的个数，同时也表示当前查看的那个武器
				if (i == extra)
					continue;
				//maxBag表示背包的最大容量，不用考虑满背包的情况
				//因为满背包的情况的最高收益必定小于等于不是满背包的最高收益
				for (int maxBag = bag - 1; maxBag >= weights[i - 1]; maxBag--) 	
					dp[maxBag] = Math.max(dp[maxBag], dp[maxBag - weights[i - 1]] + values[i - 1]); 
			}
			max = Math.max(max, dp[bag - 1] + values[extra - 1]);
		}
		return max;
	}
	
	private static void zero(int[] dp) {
		for (int i = 0; i < dp.length; i++)
			dp[i] = 0;
	} 

}
