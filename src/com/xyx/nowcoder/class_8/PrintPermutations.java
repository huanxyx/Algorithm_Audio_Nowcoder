package com.xyx.nowcoder.class_8;

/**
 * 打印一个字符串所有的全排序
 * @author huan
 * @date 2018年7月1日
 */
public class PrintPermutations {

	public static void print(char[] str, int cur) {
		if (cur == str.length)
			System.out.println(new String(str));
		else {
			for (int i = cur; i < str.length; i++) {
				swap(str, cur, i);
				print(str, cur + 1);
				swap(str, cur, i);
			}
			
		}
	} 

	private static void swap(char[] arr, int a, int b) {
		char t = arr[a];
		arr[a] = arr[b];
		arr[b] = t;
	}
	
	public static void main(String[] args) {
		print("1234".toCharArray(), 0);
	}
}
