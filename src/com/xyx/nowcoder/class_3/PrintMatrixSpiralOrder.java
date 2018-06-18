package com.xyx.nowcoder.class_3;

import java.util.LinkedList;
import java.util.List;

/**
 * 旋转打印矩阵：给定一个矩阵，按照旋转的方式打印它
 * @author huan
 * @date 2018年6月12日
 */
public class PrintMatrixSpiralOrder {
	public static List<Integer> printMatrixSpiralOrder(int[][] matrix) {
		int ltX = 0;
		int ltY = 0;
		int rdX = matrix[0].length - 1;
		int rdY = matrix.length - 1;
		
		List<Integer> list = new LinkedList<Integer>();
		while (ltX <= rdX && ltY <= rdY) {
			list.addAll(printBorder(matrix, ltX++, ltY++, rdX--, rdY--));
		}
		
		return list;
	}
	
	public static List<Integer> printBorder(int[][] matrix, 
			int ltX, int ltY, int rdX, int rdY) {
		List<Integer> list = new LinkedList<Integer>();
		int posX = ltX;
		int posY = ltY;
		if (ltX == rdX) {
			while (posY <= rdY) {
				list.add(matrix[posY++][posX]);
			}
		} else if (ltY == rdY) {
			while (posX <= rdX) {
				list.add(matrix[posY][posX++]);
			}
		} else {
			while (posX < rdX) 
				list.add(matrix[posY][posX++]);
			while (posY < rdY)
				list.add(matrix[posY++][posX]);
			while (posX > ltX)
				list.add(matrix[posY][posX--]);
			while (posY > ltY)
				list.add(matrix[posY--][posX]);
		}
		return list;
	}
	
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 }, {17, 18, 19, 20} };
		List<Integer> result = printMatrixSpiralOrder(matrix);
		System.out.println(result);
	}
}
