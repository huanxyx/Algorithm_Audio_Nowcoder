package com.xyx.nowcoder.class_1_2.inclass;

import java.util.Arrays;

/**
 * 问题描述：
 * 			给定一个数组，求如果排序之后，相邻两数的最大差值，
 * 			要求时间复杂度O(N)，且要求不能用基于比较的排序。
 * @author huan
 * @date 2018年6月9日
 */
public class MaxGap {
	public static int maxGap(int[] nums) {
		if (nums == null || nums.length < 2) 		
			return 0;
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		
		int len = nums.length;
		if (max == min)
			return 0;
		
		boolean[] hasNum = new boolean[len + 1];
		int[] maxNum = new int[len + 1];
		int[] minNum = new int[len + 1];
		//放入桶中
		for (int num : nums) {
			int index = bucket(num, min, max, len);
			maxNum[index] = hasNum[index] ? Math.max(maxNum[index], num) : num;
			minNum[index] = hasNum[index] ? Math.min(minNum[index], num) : num;
			hasNum[index] = true;
		}
		
		//计算最大相邻差值
		int res = 0;
		int lastMax = maxNum[0];
		for (int i = 1; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, minNum[i] - lastMax);
				lastMax = maxNum[i];
			}
		}
		
		return res;
	}
	
	//将min...max划分成len个区间，一共len+1个部分（0...len），其中只有max位于最后一个部分
	//每个区间是左闭右开，第len个区间只有max一个数
	private static int bucket(int num, int min, int max, int len) {
		return (num - min) * len / (max - min);
	}
	
	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (maxGap(arr1) != comparator(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
