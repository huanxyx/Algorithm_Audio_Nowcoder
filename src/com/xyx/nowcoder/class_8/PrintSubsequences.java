package com.xyx.nowcoder.class_8;

/**
 * 打印字符串的全部子序列
 * @author huan
 * @date 2018年7月1日
 */
public class PrintSubsequences {

	public static void print(String str, int cur, StringBuilder buffer) {
		
		if (cur == str.length())
			System.out.println(buffer.toString());
		else {
			print(str, cur + 1, buffer);
			buffer.append(str.charAt(cur));
			print(str, cur + 1, buffer);
			buffer.deleteCharAt(buffer.length() - 1);
		}
	}
	
	public static void main(String[] args) {
		String str = "123";
		StringBuilder buffer = new StringBuilder();
		
		print(str, 0, buffer);
	}

}
