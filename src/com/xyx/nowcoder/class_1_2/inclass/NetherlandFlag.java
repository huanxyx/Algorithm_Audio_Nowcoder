package com.xyx.nowcoder.class_1_2.inclass;

/**
 * 荷兰国旗问题
 * @author huan
 * @date 2018年6月9日
 */
public class NetherlandFlag {
	
	public static int[] partition(int[] arr, int low, int hig, int num) {
		int left = low - 1;
		int right = hig + 1;
		while (low < right) {
			if (num < arr[low]) 
				swap(arr, --right, low);
			else if (num > arr[low])
				swap(arr, ++left, low++);
			else 
				low++;
		}
		return new int[] {left + 1, right - 1};
	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);
	}
}
