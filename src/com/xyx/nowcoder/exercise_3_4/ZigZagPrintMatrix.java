package com.xyx.nowcoder.exercise_3_4;

/**
 * 之字打印
 * 问题链接：https://www.nowcoder.com/questionTerminal/7df39c7556424eada267d8f793961a1e
 * @author huan
 * @date 2018年6月16日
 */
public class ZigZagPrintMatrix {
	
	public int[] printMatrix(int[][] matrix, int m, int n) {
		int[] result = new int[m * n];
		int index = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result[index++] = matrix[i][(i % 2 == 0) ? j : (n - j - 1)];
			}
		}
		return result;
	}
}
