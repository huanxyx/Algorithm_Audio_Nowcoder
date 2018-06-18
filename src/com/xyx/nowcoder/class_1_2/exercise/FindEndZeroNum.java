package com.xyx.nowcoder.class_1_2.exercise;

import java.util.Scanner;

/**
 * 问题：末尾0的个数
 * 		输入一个正整数n,求n!(即阶乘)末尾有多少个0？ 
 * 		比如: n = 10; n! = 3628800,所以答案为2
 * 分析：
 * 		构成一个末尾0，则表示可以分解成2和5两个因子。
 * 		本质上，可以将N！的数分成质因数，其中2和5的组合对的个数就是末尾0的个数。
 * 		其中偶数的个数必定远远大于5的个数
 * 		5表示一个5，25表示2个5，125表示3个5，等等...
 * 		所以结果就可以表示为能够分解成（5的倍数的数，25的倍数的数，125的倍数的数...）的个数
 * 		若是一个是能够整除5，那么计数加一，若是这个数还能够整除25，则计数再加一，一直类推。
 * 问题链接：https://www.nowcoder.com/questionTerminal/6ffdd7e4197c403e88c6a8aa3e7a332a
 * @author huan
 * @date 2018年6月9日
 */
public class FindEndZeroNum {	
	public static int findEndZero(int num) {
		int count = 0;
		for (int i = 5; i <= num; i *= 5) {
			//求得能够整除i的个数。
			//也就是能够通过因式分解得到i的数的数目
			count += num / i;	
		}
		return count;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		System.out.println(findEndZero(num));
		sc.close();
	}
}
