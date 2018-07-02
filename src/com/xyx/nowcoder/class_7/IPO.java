package com.xyx.nowcoder.class_7;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 	一共有n个项目，每个项目包含了cost(做项目的消耗)和profit(做项目的利润)，
 * 	给定初始金额W，求在能做k个项目的情况下最大利润为多少。
 * @author huan
 * @date 2018年7月1日
 */
public class IPO {
	
	public static class Project {
		int cost;
		int profit;
		
		public Project(int cost, int profit) {
			this.cost = cost;
			this.profit = profit;
		}
	}
	
	public static class MinCostComparator implements Comparator<Project> {
		@Override
		public int compare(Project o1, Project o2) {
			return o1.cost - o2.cost;
		}
	}
	
	public static class MaxProfitComparator implements Comparator<Project> {
		@Override
		public int compare(Project o1, Project o2) {
			return o2.profit - o1.profit;
		}
	}

	public static int maxProfit(Project[] projects, int initMoney, int transaction) {
		
		PriorityQueue<Project> minCostQ = new PriorityQueue<Project>(new MinCostComparator());
		PriorityQueue<Project> maxProfit = new PriorityQueue<Project>(new MaxProfitComparator());
		
		for (Project project : projects) 
			minCostQ.offer(project);
		
		int curMoney = initMoney;
		for (int i = 0; i < transaction; i++) {
			
			//解锁能够执行的项目
			while (!minCostQ.isEmpty() && minCostQ.peek().cost <= curMoney)
				maxProfit.offer(minCostQ.poll());
			
			if (maxProfit.isEmpty())
				return curMoney - initMoney;
			//执行能够执行的利润最高的项目
			curMoney += maxProfit.poll().profit;
		}
		return curMoney - initMoney;
	}

}
