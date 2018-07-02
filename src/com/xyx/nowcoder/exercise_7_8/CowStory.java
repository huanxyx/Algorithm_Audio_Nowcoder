package com.xyx.nowcoder.exercise_7_8;

import java.util.Scanner;

/**
 * 问题：有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，每年年初也生一头小母牛。请编程实现在第n年的时候，共有多少头母牛？ 
 * 问题链接：https://www.nowcoder.com/questionTerminal/d948f2f33a5f49bc800c5a85fe001d9a
 * @author huan
 * @date 2018年7月2日
 */
public class CowStory {

	public static int cow(int n) {
		if (n < 1)
			return 0;
		if (n == 1 || n == 2 || n == 3)
			return n;
		int three = 1;
		int two = 2;
		int one = 3;
		
		for (int i = 4; i <= n; i++) {
			int cur = one + three;
			three = two;
			two = one;
			one = cur;
		}
		return one;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			int year = sc.nextInt();
			System.out.println(cow(year));
		}
		sc.close();
	}

}
