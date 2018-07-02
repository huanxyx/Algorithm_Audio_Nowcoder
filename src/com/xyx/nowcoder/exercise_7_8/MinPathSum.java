package com.xyx.nowcoder.exercise_7_8;

/**
 * 问题：最短路径和
 * 问题链接：https://leetcode.com/problems/minimum-path-sum/description/
 * @author huan
 * @date 2018年7月2日
 */
public class MinPathSum {

	//在原数组上进行修改
    public int minPathSum(int[][] grid) {
    	int rows = grid.length;
    	int cols = grid[0].length;
    	
    	for (int i = 1; i < cols; i++) {
    		grid[0][i] += grid[0][i-1];
    	}
    	for (int i = 1; i < rows; i++) {
    		grid[i][0] += grid[i-1][0];
    		for (int j = 1; j < cols; j++) {
    			grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
    		}
    	}
    	return grid[rows - 1][cols - 1];
    }

}
