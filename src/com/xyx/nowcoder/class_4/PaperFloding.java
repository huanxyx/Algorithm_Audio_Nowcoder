package com.xyx.nowcoder.class_4;

/**
 * 折纸问题
 * @author huan
 * @date 2018年6月15日
 */
public class PaperFloding {

	public static void printAllFolds(int N) {
		printProcess(N, false);
	}
	
	private static void printProcess(int N, boolean up) {
		if (N == 0)
			return ;
		printProcess(N - 1, false);
		System.out.print(up ? "up " : "down ");
		printProcess(N - 1, true);
	}
	
	public static void main(String[] args) {
		printAllFolds(4);
	}
}
