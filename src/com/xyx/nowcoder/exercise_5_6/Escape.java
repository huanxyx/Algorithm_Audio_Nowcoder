package com.xyx.nowcoder.exercise_5_6;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * HDU 4857 逃生
 * 问题链接：https://www.nowcoder.com/questionTerminal/ea3a4f5dff3e487c8d9cd11eac4f58d2
 * 题解：https://blog.csdn.net/mengxiang000000/article/details/50503467
 * Java版未通过，C++可以通过
 * @author huan
 * @date 2018年6月20日
 */
public class Escape {

	/*
	 * 逆向拓扑排序，优先队列，逆序输出：
	 * 由于需要数字小的能有多先走就多先走，意味着反过来排序，数字越大的能有能走就走：
	 * 所以需要反向建图，然后使用优先队列让在同时能够走的时候，数较大的先走，最后将结果逆序输出
	 * 
	 * 先按照没有钱的在前边的顺序读入答案然后再逆序输出就变成了题目中要求的答案，先考虑穷的
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int[] inDegree = new int[30002];
		LinkedList<Integer>[] table = new LinkedList[30002];
		for (int i = 1; i <= 30000; i++) 
			table[i] = new LinkedList<Integer>();
		
		for (int i = 0; i < T && sc.hasNext(); i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			clearTable(table, n);
			
			for (int j = 0; j < m; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				table[b].add(a);
				inDegree[a]++;
			}
			
			topology(table, inDegree, n);
		}
		sc.close();
	}
	
	//初始化
	public static void clearTable(LinkedList<Integer>[] table, int N) {
		for (int i = 1; i <= N; i++)
			table[i].clear();
	}

	//拓扑排序解决问题
	public static void topology(LinkedList<Integer>[] table, int[] inDegree, int N) {
		//canGo表示当前能够走的人
		Queue<Integer> canGo = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		//初始化
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0)
				canGo.offer(i);
		}
		
		int[] result = new int[N];
		int index = 0;
		
		while (!canGo.isEmpty()) {
			int go = canGo.poll();
			result[index++] = go;
			for (int next : table[go]) {
				inDegree[next]--;
				if (inDegree[next] == 0)
					canGo.offer(next);
			}
		}
		
		for (int i = N - 1; i >= 0; i--) {
			if (i != 0)
				System.out.print(result[i] + " ");
			else
				System.out.println(result[i]);
		}
	}
}
