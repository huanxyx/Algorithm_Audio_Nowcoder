package com.xyx.nowcoder.class_3;

/**
 * 旋转矩阵
 * @author huan
 * @date 2018年6月12日
 */
public class RotateMatrix {
	/**
	 * before：matrix是一个n*n的矩阵
	 * @param matrix
	 */
	public static void rotateMatrix(int[][] matrix) {
		int lX = 0;
		int lY = 0;
		int rX = matrix[0].length - 1;
		int rY = matrix.length - 1;
		while (lX < rX) {
			rotateBorder(matrix, lX++, lY++, rX--, rY--);
		}
	}
	
	//(lX,lY)：左上角的位置，(rX,rY)：右下角的位置
	private static void rotateBorder(int[][] matrix, int lX, int lY, int rX, int rY) {
		int len = rX - lX;
		for (int i = 0; i < len; i++) {
			int tmp = matrix[lY][lX + i];
			matrix[lY][lX + i] = matrix[rY - i][lX];
			matrix[rY - i][lX] = matrix[rY][rX - i];
			matrix[rY][rX - i] = matrix[lY + i][rX];
			matrix[lY + i][rX] = tmp;
		}
	}
	
	//for test
	private static void printMatrix(int[][] matrix) {
		for (int[] line : matrix) {
			for (int ele : line) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}
	
	//test
	public static void main(String[] args) {
		int[][] matrix = { {1, 2}, {3, 4}};
		printMatrix(matrix);
		rotateMatrix(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}
}
