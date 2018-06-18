package com.xyx.nowcoder.exercise_3_4;

import java.util.ArrayList;
import java.util.List;

/**
 * 折纸问题
 * 问题链接：https://www.nowcoder.com/questionTerminal/430180b66a7547e1963b69b1d0efbd3c
 * @author huan
 * @date 2018年6月17日
 */
public class FoldPaper {

	public static String[] foldPaper(int count) {
		List<String> result = new ArrayList<String>();
		midTraversal(result, count, false);
		
		return result.toArray(new String[result.size()]);
	}
	
	private static void midTraversal(List<String> result, int height, boolean up) {
		if (height == 0)
			return ;
		midTraversal(result, height - 1, false);
		result.add(up ? "up" : "down");
		midTraversal(result, height - 1, true);
	}
}
