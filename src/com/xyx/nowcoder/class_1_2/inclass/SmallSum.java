package com.xyx.nowcoder.class_1_2.inclass;

/**
 * 问题描述：小和问题
 * 		在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组的小和。
 * @author huan
 * @date 2018年6月6日
 */
public class SmallSum {
	private static int[] help;
	
	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2) 
			return 0;
		help = new int[arr.length];
		return mergeSort(arr, 0, arr.length-1);
	}
	
	private static int mergeSort(int[] arr, int low, int hig) {
		if (low == hig) 
			return 0;
		int mid = low + ((hig - low) >> 1);
		return mergeSort(arr, low, mid) + mergeSort(arr, mid + 1, hig) + 
				merge(arr, low, mid, hig);
	}
	
	private static int merge(int[] arr, int low, int mid, int hig) {
		int res = 0;
		
		int left = low;
		int right = mid + 1;
		int pos = low;
		while (left <= mid && right <= hig) {
			res += arr[left] < arr[right] ? (arr[left] * (hig - right + 1)) : 0;
			help[pos++] = arr[left] < arr[right] ? arr[left++] : arr[right++]; 
		}
		while (left <= mid)
			help[pos++] = arr[left++];
		while (right <= hig) 
			help[pos++] = arr[right++];
		
		for (int i = low; i <= hig; i++) 
			arr[i] = help[i];
		return res;
	}
	
	
	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
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
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}
}
