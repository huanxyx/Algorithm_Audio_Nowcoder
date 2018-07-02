package com.xyx.nowcoder.class_7;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目。
 * 给定每一项目的开始时间和结束的时间，你来安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多。返回这个最多的宣讲场次。
 * @author huan
 * @date 2018年7月1日
 */
public class BestArrange {
	
	public static class Project {
		int start;
		int end;
		public Project(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static class MinEndComparator implements Comparator<Project> {
		@Override
		public int compare(Project o1, Project o2) {
			return o1.end - o2.end;
		}
	}

	public static int mostNum(Project[] projects) {
		
		Arrays.sort(projects, new MinEndComparator());
		int count = 0;
		int curTime = 0;
		for (Project project : projects) {
			if (project.start >= curTime) {
				count++;
				curTime = project.end;
			}
		}
		return count;
	}
}
