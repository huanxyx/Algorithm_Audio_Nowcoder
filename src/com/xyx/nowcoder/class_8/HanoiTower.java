package com.xyx.nowcoder.class_8;

/**
 * 汉诺塔问题
 * @author huan
 * @date 2018年7月1日
 */
public class HanoiTower {

	public static void hanoiTower(int n, String from, String help, String to) {
		if (n == 1)
			System.out.println(from + "->" + to);
		else {
			hanoiTower(n - 1, from, to, help);
			System.out.println(from + "->" + to);
			hanoiTower(n - 1, help, from, to);
		}
	}
	
	public static void main(String[] args) {
		hanoiTower(2, "A", "B", "C");
	}

}
