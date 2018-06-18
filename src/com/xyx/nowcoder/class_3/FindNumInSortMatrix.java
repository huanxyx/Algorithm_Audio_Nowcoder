package com.xyx.nowcoder.class_3;

/**
 * 从排好序的矩阵中查找一个数
 * @author huan
 * @date 2018年6月12日
 */
public class FindNumInSortMatrix {
	
	/**
	 * before：matrix行列都排好序了
	 * @param matrix
	 * @return
	 */
	public static int[] findNum(int[][] matrix, int num) {
		int curX = matrix[0].length - 1;
		int curY = 0;
		
		while (curX >= 0 && curY >= 0) {
			int curNum = matrix[curY][curX];
			if (curNum == num) 
				return new int[] {curY, curX};
			else if (curNum < num) 
				curY++;
			else 
				curX--;
		}
		return null;
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
				{ 10, 12, 13, 15, 16, 17, 18 },// 1
				{ 23, 24, 25, 26, 27, 28, 29 },// 2
				{ 44, 45, 46, 47, 48, 49, 50 },// 3
				{ 65, 66, 67, 68, 69, 70, 71 },// 4
				{ 96, 97, 98, 99, 100, 111, 122 },// 5
				{ 166, 176, 186, 187, 190, 195, 200 },// 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int K = 97;
		int[] result = findNum(matrix, K);
		if (result == null)
			System.out.println("Not Found!");
		else 
			System.out.println("matrix[" + result[0] + "][" + result[1] + "]");
	}
}
