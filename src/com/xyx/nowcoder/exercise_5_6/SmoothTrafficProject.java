package com.xyx.nowcoder.exercise_5_6;

import java.util.Scanner;

/**
 * HDU 1232 畅通工程
 * 问题链接：https://www.nowcoder.com/questionTerminal/fd2751556ba04cb6b30665b6dc24e4fe
 * @author huan
 * @date 2018年6月19日
 */
public class SmoothTrafficProject {
	
	public static void main(String[] args) {
		main1(args);		//并查集实现：用于查找连通分量的个数
		main2(args);		//深度优先搜索：用于查找当前图的每个连通分量最小生成树的总的边的数目，然后利用当图是连通的最小生成树的边的数目减去该数。
	}

    /*
     * 并查集实现
     */
	public static void main1(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int N = sc.nextInt();

			if (N == 0)
				break ;
			
			int M = sc.nextInt();
			
			UF uf = new UF(N);
			
			for (int i = 0; i < M; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				uf.union(start, end);
			}
			System.out.println(uf.getDifferentSetNum() - 1);
		}
		sc.close();
	}
	
	
	//并查集
	public static class UF {
		private int[] parent;
		private int[] weight;
		private int N;
		private int differentSet;		//不同的集合
		
		public UF(int initSize) {
			parent = new int[initSize + 1];
			weight = new int[initSize + 1];
			N = initSize;
			for (int i = 1; i <= initSize; i++) {
				parent[i] = i;
				weight[i] = 1;
			}
			differentSet = N;
		}
		
		public int getDifferentSetNum() {
			return differentSet;
		}
		
		public void union(int a, int b) {
			int rootA = findRoot(a);
			int rootB = findRoot(b);
			if (rootA == rootB)
				return ;
			if (weight[rootA] < weight[rootB]) {
				parent[rootA] = rootB;
				weight[rootB] += weight[rootA];
			} else {
				parent[rootB] = rootA;
				weight[rootA] += weight[rootB];
			}
			differentSet--;
		}
		
		public int findRoot(int a) {
			int root = parent[a];
			if (root != a) {
				root = findRoot(root);
			}
			parent[a] = root;
			return root;
		}
		
		public boolean isSameSet(int a, int b) {
			return findRoot(a) == findRoot(b);
		}
	}
	
	/*
	 * 深度优先搜索：找到构成最小生成树的边的个数（可以存在多个连通分量），然将一个连通图需要的最小生成树的边数减去该数即可
	 */
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
	        int N = sc.nextInt();
	        if (N == 0)
	        	break;
	        int M = sc.nextInt();
	         
	        boolean[][] graph = new boolean[N][N];
	         
	        for (int i = 0; i < M; i++) {
	            int start = sc.nextInt() - 1;
	            int end = sc.nextInt() - 1;
	            graph[start][end] = true;
	            graph[end][start] = true;
	        }
	         
	        boolean[] hasTraverse = new boolean[N];
	        int sum = 0;
	        for (int i = 0; i < N; i++) {
	        	int onePart = dfs(graph, hasTraverse, i, N);
	            sum += onePart == 0 ? 0 : onePart - 1;
	        }
	        System.out.println(N - 1 - sum);
        }
        sc.close();
    }
 
    //深度优先遍历
    public static int dfs(boolean[][] graph, boolean[] hasTraverse, int cur, int N) {
        if (hasTraverse[cur])
            return 0;
        int sum = 1;
        hasTraverse[cur] = true;
        for (int i = 0; i < N; i++) {
            //遍历所有的邻边
            if (graph[cur][i])
                sum += dfs(graph, hasTraverse, i, N);
        }
         
        return sum;
    }

}
