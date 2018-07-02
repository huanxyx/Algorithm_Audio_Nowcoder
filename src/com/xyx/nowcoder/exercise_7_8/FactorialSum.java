package com.xyx.nowcoder.exercise_7_8;

/**
 * 问题：n! ， sum=n!+(n+1)!+(n+2)!+...+m!,n,m 值由键盘输入 , 要求调用函数。 
 * 问题链接：https://www.nowcoder.com/questionTerminal/1abe85d4eac04fb784dbb67f38a0ec8d
 * @author huan
 * @date 2018年7月2日
 */
public class FactorialSum {

	public static int recursFactorial(int n) {
		if (n == 1 || n == 0)
			return 1;
		return recursFactorial(n - 1) * n;
	}
	
	public static void main(String[] args) {
		int n = 10;
		int m = 20;
		int sum = 0;
		for (int i = n; i <= m; i++)
			sum += recursFactorial(i);
		System.out.println(sum);
	}

}
