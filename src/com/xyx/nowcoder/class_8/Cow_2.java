package com.xyx.nowcoder.class_8;

/**
 * 如果每只母牛只能活10年， 求N年后， 母牛的数量。
 * @author huan
 * @date 2018年7月1日
 */
public class Cow_2 {

	public static int cow(int n) {
		if (n < 1)
			return 0;
		if (n == 1 || n == 2 || n == 3)
			return n;
		
		int[] num = new int[n + 1];
		for (int i = 1; i < 4; i++)
			num[i] = i;
		for (int i = 4; i <= n; i++) {
			num[i] = num[i - 1] + num[i - 3];
			if (i > 10)
				num[i] -= num[i - 10];
		}
		return num[n];
	}

}
