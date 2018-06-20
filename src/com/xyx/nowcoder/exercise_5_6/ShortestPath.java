package com.xyx.nowcoder.exercise_5_6;

import java.util.Scanner;

/**
 * HDU 2544 最短路
 * 问题链接：https://www.nowcoder.com/questionTerminal/24e8bcf010764cde9a87487232cc50c5
 * @author huan
 * @date 2018年6月19日
 */
public class ShortestPath {
	
	public static final int INIFITE = 100000;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
			int N = sc.nextInt();			//路口的数目（1为商店的位置，N为赛场的所在地）
			int M = sc.nextInt();			//代表有几条路
			if (N == 0 && M == 0) {
				break;
			}
			
			int[][] graph = initGraph(N);	
			for (int i = 0; i < M; i++) {
				int pos1 = sc.nextInt();
				int pos2 = sc.nextInt();
				int time = sc.nextInt();
				graph[pos1][pos2] = time;
				graph[pos2][pos1] = time;
			}
			int result = floyd(graph, N);
			System.out.println(result);
		}
		sc.close();
	
	}
	
	//迪杰斯特拉算法解决最短路径问题
	public static int dijkstra(int[][] graph, int N) {
		int[] minDis = new int[N + 1];
		boolean[] hasConfirm = new boolean[N + 1];
		//初始化
		for (int i = 1; i <= N; i++) 
			minDis[i] = INIFITE;
		
		minDis[1] = 0;
		
		//找到N个点的最短距离
		for (int i = 1; i <= N; i++) {
			//找到距离最小的点，然后确认
			int min = INIFITE;
			int minNode = 1;
			for (int j = 1; j <= N; j++) {
				if (!hasConfirm[j] && min > minDis[j]) {
					minNode = j;
					min = minDis[j];
				}
			}
			hasConfirm[minNode] = true;
			
			//松弛距离
			for (int j = 1; j <= N; j++) {
				//注意minDis[minNode] + graph[minNode][j] 有可能越界变成负数
				if (!hasConfirm[j] &&  minDis[j] - minDis[minNode] > graph[minNode][j]) {		
					minDis[j] = minDis[minNode] + graph[minNode][j];
				}
			}
		}
		
		return minDis[N];
	}
	
	//弗洛伊德算法解决最短路径问题
	public static int floyd(int[][] graph, int N) {
		int[][] minDis = new int[N + 1][N + 1];
		
		//初始化
		for (int i = 1; i <= N; i++) {			
			for (int j = 1; j <= N; j++) {
				if (i == j)
					minDis[i][j] = 0;
				else {
					minDis[i][j] = graph[i][j];
					minDis[j][i] = graph[j][i];
				}
			}
		}
		
		//计算
		for (int max = 1; max <= N; max++) {								//经过最大节点
			for (int start = 1; start <= N; start++) {						//起始节点
				for (int end = 1; end <= N; end++) {						//终止节点
					if (minDis[start][end] > minDis[start][max] + minDis[max][end])
						minDis[start][end] = minDis[start][max] + minDis[max][end];
				}
			}
		}
		return minDis[1][N];
	}
	

	public static int[][] initGraph(int N) {
		int[][] graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++)
				graph[i][j] = INIFITE;
		}
		return graph;
	}
}
