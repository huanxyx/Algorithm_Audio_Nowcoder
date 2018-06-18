package com.xyx.nowcoder.class_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 之字打印矩阵
 * @author huan
 * @date 2018年6月12日
 */
public class ZigZagPrintMatrix {
	public static List<Integer> zigzagPrint(int[][] matrix) {
		int topX = 0, topY = 0, downX = 0, downY = 0;
		int endY = matrix.length - 1;
		int endX = matrix[0].length - 1;
		boolean fromUp = true;
		
		List<Integer> list = new ArrayList<Integer>();
		
		while (topY <= endY) {
//			System.out.println(topY + " " + topX);
			list.addAll(printDiagonal(matrix, topX, topY, downX, downY, fromUp));
			//上面那个点先判断行，再判断列
			topY = topX != endX ? topY : (topY + 1);
			topX = topX == endX ? topX : (topX + 1);
			//下面那个点先判断列，再判断行
			downX = downY != endY ? downX : (downX + 1);
			downY = downY == endY ? downY : (downY + 1);
			fromUp = !fromUp;
		}
		
		return list;
	}
	
	//打印一条斜线
	private static List<Integer> printDiagonal(int[][] matrix, 
			int topX, int topY, int downX, int downY, boolean fromUp) {
		List<Integer> list = new ArrayList<Integer>();
		if (fromUp) {
			while (topX >= downX) {
				list.add(matrix[topY++][topX--]);
			}
		} else {
			while (downX <= topX) {
				list.add(matrix[downY--][downX++]);
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		System.out.println(zigzagPrint(matrix));
	}
}
