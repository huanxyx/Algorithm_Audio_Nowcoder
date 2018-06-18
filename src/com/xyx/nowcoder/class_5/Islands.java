package com.xyx.nowcoder.class_5;

/**
 * 判断存在多少个岛
 * @author huan
 * @date 2018年6月17日
 */
public class Islands {
	/*
	 * 遍历每个点，若是这个点为陆地，且没有被纳入到其他岛中，
	 * 则对他所有周围的点进行感染。意味着将相邻的点纳入该岛。
	 * 
	 */
	public static int getIslandNum(int[][] lands) {
		if (lands == null || lands.length == 0 ||
				lands[0] == null || lands[0].length == 0)
			return 0;
			
		int count = 0;
		for (int i = 0; i < lands.length; i++) {
			for (int j = 0; j < lands[0].length; j++) {
				if (lands[i][j] == 1) {//为陆地，且未被计算
					count++;
					infect(lands, i, j);
				}
			}
		}
		return count;
	}
	
	private static void infect(int[][] lands, int row, int col) {
		//越界
		if (row < 0 || row >= lands.length || col < 0 || col >= lands[0].length)
			return ;
		//若是为不为陆地，或者已经算过了，则结束
		if (lands[row][col] != 1) 
			return ;
		lands[row][col] = 2;
		infect(lands, row - 1, col);
		infect(lands, row + 1, col);
		infect(lands, row, col - 1);
		infect(lands, row, col + 1);
	}
	
	//test
	public static void main(String[] args) {
		int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, 
				        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				        { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, 
				        { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
				        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(getIslandNum(m1));

		int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						{ 0, 1, 1, 1, 1, 1, 1, 1, 0 }, 
						{ 0, 1, 1, 1, 0, 0, 0, 1, 0 },
						{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, 
						{ 0, 0, 0, 0, 0, 1, 1, 0, 0 }, 
						{ 0, 0, 0, 0, 1, 1, 1, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(getIslandNum(m2));

	}
}
