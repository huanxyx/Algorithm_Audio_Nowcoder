package com.xyx.nowcoder.class_1_2.inclass;

import java.util.Arrays;

public class QuickSort {
	public static void sort(int[] arr) {
		if (arr == null || arr.length < 2)
			return ;
		quickSort(arr, 0, arr.length - 1);
	}
	
	//打乱顺序
	private static void quickSort(int[] arr, int low, int hig) {
		if (low < hig) {
			//打乱数据，绕开原数据对算法的影响
			swap(arr, hig, (int)(Math.random() * (hig - low + 1)) + low);
			int[] mid = partition(arr, low, hig);
			quickSort(arr, low, mid[0] - 1);
			quickSort(arr, mid[1] + 1, hig);
		}
	}
	
	//三路分割
	private static int[] partition(int[] arr, int low, int hig) {
		int value = arr[hig];
		int smaller = low - 1;
		int bigger = hig;
		while (low < bigger) {
			if (arr[low] > value) {
				swap(arr, --bigger, low);
			} else if (arr[low] < value) {
				swap(arr, ++smaller, low++);
			} else {
				low++;
			}
		}
		swap(arr, hig, bigger);
		return new int[] {smaller + 1, bigger};
	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	
	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
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
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			
			long startTime = System.nanoTime();
			sort(arr1);
			long endTime = System.nanoTime();
			System.out.println("自己实现的排序：" + (endTime - startTime));
			startTime = System.nanoTime();
			comparator(arr2);
			endTime = System.nanoTime();
			System.out.println("系统排序：" + (endTime - startTime));

			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

//		int[] arr = generateRandomArray(maxSize, maxValue);
//		printArray(arr);
//		sort(arr);
//		printArray(arr);
	}
}
