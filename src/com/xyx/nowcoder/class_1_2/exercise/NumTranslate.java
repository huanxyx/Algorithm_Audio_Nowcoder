package com.xyx.nowcoder.class_1_2.exercise;

import java.util.Scanner;

/**
 * 进制转换：num为需要转换的数，radix为相应的进制
 * 问题链接：https://www.nowcoder.com/questionTerminal/ac61207721a34b74b06597fe6eb67c52
 * @author huan
 * @date 2018年6月9日
 */
public class NumTranslate {
	public static String toString(int num, int radix) {
		if (num == 0)	return "0";
		char[] table = "0123456789ABCDEF".toCharArray();
		
		StringBuilder builder = new StringBuilder();
		boolean isNegative = false;
		if (num < 0) {
			num = -num;
			isNegative = true;
		}
		while (num != 0) {
			builder.append(table[num % radix]);
			num /= radix;
		}
		if (isNegative) builder.append('-');
		return builder.reverse().toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int radix = sc.nextInt();
		
		System.out.println(toString(num, radix));
		sc.close();
	}
}
