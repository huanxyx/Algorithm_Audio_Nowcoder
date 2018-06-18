package com.xyx.nowcoder.exercise_3_4;

/**
 * 局部最小问题
 * 问题链接：https://www.nowcoder.com/questionTerminal/322eb1da892448f4b18d9b21a6d48c99
 * @author huan
 * @date 2018年6月17日
 */
public class GetPartSmallIndex {

	/*
	 * 二分查找部分的原理：
	 * 		能够执行到二分查找的部分的时候满足：arr[0] > arr[1] and arr[last - 1] < arr[last]
	 * 		这意味着中间肯定存在一个局部最小满足 arr[i - 1] > arr[i] < arr[i + 1]
	 * 		若是arr[mid - 1] < arr[mid]，那么就构成同样的 arr[0] > arr[1] and arr[last - 1] < arr[last]。
	 * 		就意味着在[low, mid-1]区间肯定存在解，同理在arr[mid + 1] < arr[mid]
	 */
	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length == 0)
			return -1;
		if (arr.length == 1)
			return 0;
		int len = arr.length;
		if (arr[0] < arr[1])
			return 0;
		if (arr[len - 1] < arr[len - 2])
			return len - 1;
		
		int hig = len - 2;
		int low = 1;
		while (low <= hig) {
			int mid = low + (hig - low) / 2;
			if (arr[mid - 1] < arr[mid])
				hig = mid - 1;
			else if (arr[mid + 1] < arr[mid])
				low = mid + 1;
			else 
				return mid;	
		}
		return low;
	}

}
